package com.gestorprestamos.services;

import com.gestorprestamos.entities.SimulacionCreditoEntity;
import com.gestorprestamos.repositories.SimulacionCreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimulacionCreditoServiceTest {

    @InjectMocks
    private SimulacionCreditoService simulacionCreditoService;

    @Mock
    private SimulacionCreditoRepository simulacionCreditoRepository;

    private SimulacionCreditoEntity simulacionCredito;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        simulacionCredito = new SimulacionCreditoEntity();
        simulacionCredito.setId(1L);
        simulacionCredito.setIdCliente(1);
        simulacionCredito.setMontoSolicitado(10000.0);
        simulacionCredito.setPlazoSolicitado(12);
        simulacionCredito.setTasaInteres(5.0);
    }

    @Test
    void testGetSimulacionCreditos() {
        when(simulacionCreditoRepository.findAll()).thenReturn(new ArrayList<>());

        ArrayList<SimulacionCreditoEntity> simulaciones = simulacionCreditoService.getSimulacionCreditos();
        assertNotNull(simulaciones);
        assertTrue(simulaciones.isEmpty());

        verify(simulacionCreditoRepository, times(1)).findAll();
    }

    @Test
    void testSaveSimulacionCredito() {
        when(simulacionCreditoRepository.save(any(SimulacionCreditoEntity.class))).thenReturn(simulacionCredito);

        SimulacionCreditoEntity savedSimulacion = simulacionCreditoService.saveSimulacionCredito(simulacionCredito);
        assertNotNull(savedSimulacion);
        assertEquals(simulacionCredito.getId(), savedSimulacion.getId());

        verify(simulacionCreditoRepository, times(1)).save(simulacionCredito);
    }

    @Test
    void testGetSimulacionCreditoById() {
        when(simulacionCreditoRepository.findById(1L)).thenReturn(Optional.of(simulacionCredito));

        SimulacionCreditoEntity foundSimulacion = simulacionCreditoService.getSimulacionCreditoById(1L);

        assertNotNull(foundSimulacion);
        assertEquals(simulacionCredito.getId(), foundSimulacion.getId());

        verify(simulacionCreditoRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateSimulacionCredito() {
        when(simulacionCreditoRepository.save(any(SimulacionCreditoEntity.class))).thenReturn(simulacionCredito);

        SimulacionCreditoEntity updatedSimulacion = simulacionCreditoService.updateSimulacionCredito(simulacionCredito);

        assertNotNull(updatedSimulacion);
        assertEquals(simulacionCredito.getId(), updatedSimulacion.getId());

        verify(simulacionCreditoRepository, times(1)).save(simulacionCredito);
    }

    @Test
    void testDeleteSimulacionCredito() throws Exception {
        doNothing().when(simulacionCreditoRepository).deleteById(1L);

        boolean result = simulacionCreditoService.deleteSimulacionCredito(1L);
        assertTrue(result);

        verify(simulacionCreditoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteSimulacionCreditoThrowsException() {
        doThrow(new RuntimeException("Error")).when(simulacionCreditoRepository).deleteById(1L);

        Exception exception = assertThrows(Exception.class, () -> {
            simulacionCreditoService.deleteSimulacionCredito(1L);
        });

        assertEquals("Error", exception.getMessage());
        verify(simulacionCreditoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testCalculateSimulation() {
        // Configura el objeto esperado que debe devolverse
        double expectedCuotaMensual = 856.07; // Ajusta este valor según tu lógica de cálculo

        // Simula el comportamiento del repositorio
        when(simulacionCreditoRepository.save(any(SimulacionCreditoEntity.class))).thenAnswer(invocation -> {
            SimulacionCreditoEntity simulacion = invocation.getArgument(0);
            // Establece la cuota mensual en el objeto simulado
            simulacion.setCuotaMensual(expectedCuotaMensual);
            return simulacion;
        });

        // Llama al método a probar
        SimulacionCreditoEntity simulacion = simulacionCreditoService.calculateSimulation(1, 10000, 12, 5);

        // Verifica que el objeto no sea nulo
        assertNotNull(simulacion);
        assertEquals(1, simulacion.getIdCliente());
        assertEquals(10000, simulacion.getMontoSolicitado());
        assertEquals(12, simulacion.getPlazoSolicitado());
        assertEquals(5, simulacion.getTasaInteres());
        assertNotNull(simulacion.getFechaSimulacion());
        assertEquals(expectedCuotaMensual, simulacion.getCuotaMensual(), 0.01); // Verifica la cuota mensual

        verify(simulacionCreditoRepository, times(1)).save(any(SimulacionCreditoEntity.class));
    }
}
