package com.gestorprestamos.services;

import com.gestorprestamos.entities.PrestamoEntity;
import com.gestorprestamos.repositories.PrestamoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrestamoServiceTest {

    @InjectMocks
    private PrestamoService prestamoService;

    @Mock
    private PrestamoRepository prestamoRepository;

    private PrestamoEntity prestamo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        prestamo = new PrestamoEntity();
        prestamo.setId(1L);
        prestamo.setMonto(10000.0);
        prestamo.setPlazo(12);
        prestamo.setTasaInteres(5.0);
    }

    @Test
    void testGetPrestamos() {
        when(prestamoRepository.findAll()).thenReturn(new ArrayList<>());

        ArrayList<PrestamoEntity> prestamos = prestamoService.getPrestamos();
        assertNotNull(prestamos);
        assertTrue(prestamos.isEmpty());

        verify(prestamoRepository, times(1)).findAll();
    }

    @Test
    void testSavePrestamo() {
        when(prestamoRepository.save(any(PrestamoEntity.class))).thenReturn(prestamo);

        PrestamoEntity savedPrestamo = prestamoService.savePrestamo(prestamo);
        assertNotNull(savedPrestamo);
        assertEquals(prestamo.getId(), savedPrestamo.getId());

        verify(prestamoRepository, times(1)).save(prestamo);
    }

    @Test
    void testRegisterLoanApplication() {
        when(prestamoRepository.save(any(PrestamoEntity.class))).thenReturn(prestamo);

        PrestamoEntity registeredPrestamo = prestamoService.registerLoanApplication(prestamo);

        assertNotNull(registeredPrestamo);
        assertEquals(LocalDate.now(), registeredPrestamo.getFechaSolicitud());
        assertEquals("En Revisión", registeredPrestamo.getEstado());

        verify(prestamoRepository, times(1)).save(prestamo);
    }

    @Test
    void testGetPrestamoById() {
        when(prestamoRepository.findById(1L)).thenReturn(Optional.of(prestamo));

        PrestamoEntity foundPrestamo = prestamoService.getPrestamoById(1L);

        assertNotNull(foundPrestamo);
        assertEquals(prestamo.getId(), foundPrestamo.getId());

        verify(prestamoRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdatePrestamo() {
        when(prestamoRepository.save(any(PrestamoEntity.class))).thenReturn(prestamo);

        PrestamoEntity updatedPrestamo = prestamoService.updatePrestamo(prestamo);

        assertNotNull(updatedPrestamo);
        assertEquals(prestamo.getId(), updatedPrestamo.getId());

        verify(prestamoRepository, times(1)).save(prestamo);
    }

    @Test
    void testDeletePrestamo() throws Exception {
        doNothing().when(prestamoRepository).deleteById(1L);

        boolean result = prestamoService.deletePrestamo(1L);
        assertTrue(result);

        verify(prestamoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeletePrestamoThrowsException() {
        doThrow(new RuntimeException("Error")).when(prestamoRepository).deleteById(1L);

        Exception exception = assertThrows(Exception.class, () -> {
            prestamoService.deletePrestamo(1L);
        });

        assertEquals("Error", exception.getMessage());
        verify(prestamoRepository, times(1)).deleteById(1L);
    }
}
