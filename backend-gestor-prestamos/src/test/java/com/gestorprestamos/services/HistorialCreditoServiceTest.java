package com.gestorprestamos.services;

import com.gestorprestamos.entities.HistorialCreditoEntity;
import com.gestorprestamos.repositories.HistorialCreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HistorialCreditoServiceTest {

    @InjectMocks
    private HistorialCreditoService historialCreditoService;

    @Mock
    private HistorialCreditoRepository historialCreditoRepository;

    private HistorialCreditoEntity historialCredito;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        historialCredito = new HistorialCreditoEntity();
        historialCredito.setId(1L);
        historialCredito.setEstadoCredito("Bueno");
        historialCredito.setDescripcion("Pago puntual");

        // Convertir LocalDate a Date
        LocalDate localDate = LocalDate.parse("2024-11-05");
        Date fechaActualizacion = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        historialCredito.setFechaActualizacion(fechaActualizacion);
    }

    @Test
    void getHistorialCreditos_ShouldReturnList() {
        // Given
        ArrayList<HistorialCreditoEntity> listaHistorial = new ArrayList<>();
        listaHistorial.add(historialCredito);
        when(historialCreditoRepository.findAll()).thenReturn(listaHistorial);

        // When
        ArrayList<HistorialCreditoEntity> result = historialCreditoService.getHistorialCreditos();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(historialCredito, result.get(0));
    }

    @Test
    void saveHistorialCredito_ShouldReturnSavedEntity() {
        // Given
        when(historialCreditoRepository.save(any(HistorialCreditoEntity.class))).thenReturn(historialCredito);

        // When
        HistorialCreditoEntity result = historialCreditoService.saveHistorialCredito(historialCredito);

        // Then
        assertNotNull(result);
        assertEquals(historialCredito, result);
    }

    @Test
    void getHistorialCreditoById_ShouldReturnEntity() {
        // Given
        when(historialCreditoRepository.findById(1L)).thenReturn(Optional.of(historialCredito));

        // When
        HistorialCreditoEntity result = historialCreditoService.getHistorialCreditoById(1L);

        // Then
        assertNotNull(result);
        assertEquals(historialCredito, result);
    }

    @Test
    void updateHistorialCredito_ShouldReturnUpdatedEntity() {
        // Given
        when(historialCreditoRepository.save(any(HistorialCreditoEntity.class))).thenReturn(historialCredito);

        // When
        HistorialCreditoEntity result = historialCreditoService.updateHistorialCredito(historialCredito);

        // Then
        assertNotNull(result);
        assertEquals(historialCredito, result);
    }

    @Test
    void deleteHistorialCredito_ShouldReturnTrue() throws Exception {
        // Given
        doNothing().when(historialCreditoRepository).deleteById(1L);

        // When
        boolean result = historialCreditoService.deleteHistorialCredito(1L);

        // Then
        assertTrue(result);
        verify(historialCreditoRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteHistorialCredito_ShouldThrowException() {
        // Given
        doThrow(new RuntimeException("Error en la eliminación")).when(historialCreditoRepository).deleteById(1L);

        // When & Then
        Exception exception = assertThrows(Exception.class, () -> {
            historialCreditoService.deleteHistorialCredito(1L);
        });
        assertEquals("Error en la eliminación", exception.getMessage());
    }
}
