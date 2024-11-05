package com.gestorprestamos.services;

import com.gestorprestamos.entities.EvaluacionCreditoEntity;
import com.gestorprestamos.repositories.EvaluacionCreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EvaluacionCreditoServiceTest {

    @Mock
    private EvaluacionCreditoRepository evaluacionCreditoRepository;

    @InjectMocks
    private EvaluacionCreditoService evaluacionCreditoService;

    private EvaluacionCreditoEntity evaluacionCredito;

    @BeforeEach
    void setUp() {
        evaluacionCredito = new EvaluacionCreditoEntity();
        evaluacionCredito.setId(1L);
        evaluacionCredito.setCuotaIngreso(0.30);
        evaluacionCredito.setHistorialCredito("Bueno");
        evaluacionCredito.setAntiguedadLaboral(2);
        evaluacionCredito.setDeudaIngreso(0.20);
        evaluacionCredito.setMontoFinanciamiento(70);
    }

    @Test
    void whenGetEvaluacionCreditos_thenReturnListOfEvaluacionCreditos() {
        // Given
        ArrayList<EvaluacionCreditoEntity> evaluaciones = new ArrayList<>();
        evaluaciones.add(evaluacionCredito);
        when(evaluacionCreditoRepository.findAll()).thenReturn(evaluaciones);

        // When
        ArrayList<EvaluacionCreditoEntity> result = evaluacionCreditoService.getEvaluacionCreditos();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(evaluacionCredito.getId(), result.get(0).getId());
    }

    @Test
    void whenSaveEvaluacionCredito_thenReturnEvaluacionCredito() {
        // Given
        when(evaluacionCreditoRepository.save(evaluacionCredito)).thenReturn(evaluacionCredito);

        // When
        EvaluacionCreditoEntity result = evaluacionCreditoService.saveEvaluacionCredito(evaluacionCredito);

        // Then
        assertNotNull(result);
        assertEquals(evaluacionCredito.getId(), result.getId());
    }

    @Test
    void whenGetEvaluacionCreditoById_thenReturnEvaluacionCredito() {
        // Given
        when(evaluacionCreditoRepository.findById(1L)).thenReturn(Optional.of(evaluacionCredito));

        // When
        EvaluacionCreditoEntity result = evaluacionCreditoService.getEvaluacionCreditoById(1L);

        // Then
        assertNotNull(result);
        assertEquals(evaluacionCredito.getId(), result.getId());
    }

    @Test
    void whenUpdateEvaluacionCredito_thenReturnEvaluacionCredito() {
        // Given
        when(evaluacionCreditoRepository.save(evaluacionCredito)).thenReturn(evaluacionCredito);

        // When
        EvaluacionCreditoEntity result = evaluacionCreditoService.updateEvaluacionCredito(evaluacionCredito);

        // Then
        assertNotNull(result);
        assertEquals(evaluacionCredito.getId(), result.getId());
    }

    @Test
    void whenDeleteEvaluacionCredito_thenReturnTrue() throws Exception {
        // Given
        doNothing().when(evaluacionCreditoRepository).deleteById(1L);

        // When
        boolean result = evaluacionCreditoService.deleteEvaluacionCredito(1L);

        // Then
        assertTrue(result);
        verify(evaluacionCreditoRepository, times(1)).deleteById(1L);
    }

    @Test
    void whenRequestEvaluationApproved_thenReturnsEvaluacionCredito() {
        // Given
        when(evaluacionCreditoRepository.save(evaluacionCredito)).thenReturn(evaluacionCredito);

        // When
        EvaluacionCreditoEntity result = evaluacionCreditoService.requestEvaluation(evaluacionCredito);

        // Then
        assertNotNull(result);
        assertEquals("Aprobado", result.getResultado());
    }

    @Test
    void whenRequestEvaluationRejectedDueToCuotaIngreso_thenReturnsEvaluacionCredito() {
        // Given
        EvaluacionCreditoEntity evaluacionCredito = new EvaluacionCreditoEntity();
        evaluacionCredito.setCuotaIngreso(0.4); // Valor mayor que 0.35 para que sea rechazado
        evaluacionCredito.setHistorialCredito("Bueno");
        evaluacionCredito.setAntiguedadLaboral(2);
        evaluacionCredito.setDeudaIngreso(0.4);
        evaluacionCredito.setMontoFinanciamiento(50);

        // Mock del repositorio
        when(evaluacionCreditoRepository.save(any(EvaluacionCreditoEntity.class))).thenReturn(evaluacionCredito);

        // When
        EvaluacionCreditoEntity result = evaluacionCreditoService.requestEvaluation(evaluacionCredito);

        // Then
        assertNotNull(result);
        assertEquals("Rechazado", result.getResultado());
    }

    @Test
    void whenRequestEvaluationRejectedDueToHistorialCredito_thenReturnsEvaluacionCredito() {
        // Given
        EvaluacionCreditoEntity evaluacionCredito = new EvaluacionCreditoEntity();
        evaluacionCredito.setHistorialCredito("Malo"); // Historial no bueno
        evaluacionCredito.setCuotaIngreso(0.2); // Esta condición es válida
        evaluacionCredito.setAntiguedadLaboral(2); // Esta condición es válida
        evaluacionCredito.setDeudaIngreso(0.4); // Esta condición es válida
        evaluacionCredito.setMontoFinanciamiento(50); // Esta condición es válida

        // Configuramos el mock para que devuelva el objeto evaluacionCredito
        when(evaluacionCreditoRepository.save(any(EvaluacionCreditoEntity.class))).thenReturn(evaluacionCredito);

        // When
        EvaluacionCreditoEntity result = evaluacionCreditoService.requestEvaluation(evaluacionCredito);

        // Then
        assertNotNull(result);
        assertEquals("Rechazado", result.getResultado());
    }



    @Test
    void whenRequestEvaluationRejectedDueToAntiguedadLaboral_thenReturnsEvaluacionCredito() {
        // Given
        EvaluacionCreditoEntity evaluacionCredito = new EvaluacionCreditoEntity();
        evaluacionCredito.setCuotaIngreso(0.4); // Este valor no debería causar rechazo
        evaluacionCredito.setHistorialCredito("Bueno"); // Este valor no debería causar rechazo
        evaluacionCredito.setAntiguedadLaboral(0); // Este valor debe causar rechazo
        evaluacionCredito.setDeudaIngreso(0.3); // Este valor no debería causar rechazo
        evaluacionCredito.setMontoFinanciamiento(50); // Este valor no debería causar rechazo

        // Mock del repositorio
        when(evaluacionCreditoRepository.save(any(EvaluacionCreditoEntity.class))).thenReturn(evaluacionCredito);

        // When
        EvaluacionCreditoEntity result = evaluacionCreditoService.requestEvaluation(evaluacionCredito);

        // Then
        assertNotNull(result); // Asegúrate de que no sea null
        assertEquals("Rechazado", result.getResultado()); // Verifica que el resultado sea "Rechazado"
    }

    @Test
    void whenRequestEvaluationRejectedDueToDeudaIngreso_thenReturnsEvaluacionCredito() {
        // Given
        EvaluacionCreditoEntity evaluacionCredito = new EvaluacionCreditoEntity();
        evaluacionCredito.setCuotaIngreso(0.3); // No debería ser rechazado por cuotaIngreso
        evaluacionCredito.setHistorialCredito("Bueno");
        evaluacionCredito.setAntiguedadLaboral(2);
        evaluacionCredito.setDeudaIngreso(0.6); // Este valor debe causar rechazo
        evaluacionCredito.setMontoFinanciamiento(50); // Este valor no afecta el rechazo por deudaIngreso

        // Mock del repositorio
        when(evaluacionCreditoRepository.save(any(EvaluacionCreditoEntity.class))).thenReturn(evaluacionCredito);

        // When
        EvaluacionCreditoEntity result = evaluacionCreditoService.requestEvaluation(evaluacionCredito);

        // Then
        assertNotNull(result); // Asegúrate de que no sea null
        assertEquals("Rechazado", result.getResultado()); // Verifica que el resultado sea "Rechazado"
    }

    @Test
    void whenRequestEvaluationRejectedDueToMontoFinanciamiento_thenReturnsEvaluacionCredito() {
        // Given
        EvaluacionCreditoEntity evaluacionCredito = new EvaluacionCreditoEntity();
        evaluacionCredito.setMontoFinanciamiento(100); // Este monto debería provocar un rechazo
        evaluacionCredito.setCuotaIngreso(0.2);
        evaluacionCredito.setHistorialCredito("Bueno");
        evaluacionCredito.setAntiguedadLaboral(2);
        evaluacionCredito.setDeudaIngreso(0.4);

        // Configura el mock para que devuelva el objeto evaluacionCredito
        when(evaluacionCreditoRepository.save(any(EvaluacionCreditoEntity.class))).thenReturn(evaluacionCredito);

        // When
        EvaluacionCreditoEntity result = evaluacionCreditoService.requestEvaluation(evaluacionCredito);

        // Then
        assertNotNull(result);
        assertEquals("Rechazado", result.getResultado());
    }
}
