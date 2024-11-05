package com.example.GestorPrestamos;

import com.gestorprestamos.entities.ClienteEntity;
import com.gestorprestamos.repositories.ClienteRepository;
import com.gestorprestamos.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetClientes() {
        // Arrange
        ClienteEntity cliente1 = new ClienteEntity();
        ClienteEntity cliente2 = new ClienteEntity();
        ArrayList<ClienteEntity> clientes = new ArrayList<>(Arrays.asList(cliente1, cliente2));
        when(clienteRepository.findAll()).thenReturn(clientes);

        // Act
        ArrayList<ClienteEntity> result = clienteService.getClientes();

        // Assert
        assertEquals(2, result.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    public void testSaveCliente() {
        // Arrange
        ClienteEntity cliente = new ClienteEntity();
        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(cliente);

        // Act
        ClienteEntity result = clienteService.saveCliente(cliente);

        // Assert
        assertNotNull(result);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void testRegistrarCliente() {
        // Arrange
        ClienteEntity cliente = new ClienteEntity();
        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(cliente);

        // Act
        ClienteEntity result = clienteService.registrarCliente(cliente);

        // Assert
        assertNotNull(result);
        assertEquals(LocalDate.now(), result.getFechaRegistro());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void testGetClienteById() {
        // Arrange
        Long id = 1L;
        ClienteEntity cliente = new ClienteEntity();
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        // Act
        ClienteEntity result = clienteService.getClienteById(id);

        // Assert
        assertNotNull(result);
        verify(clienteRepository, times(1)).findById(id);
    }

    @Test
    public void testGetClienteByRut() {
        // Arrange
        String rut = "12345678-9";
        ClienteEntity cliente = new ClienteEntity();
        when(clienteRepository.findByRut(rut)).thenReturn(cliente);

        // Act
        ClienteEntity result = clienteService.getClienteByRut(rut);

        // Assert
        assertNotNull(result);
        verify(clienteRepository, times(1)).findByRut(rut);
    }

    @Test
    public void testUpdateCliente() {
        // Arrange
        ClienteEntity cliente = new ClienteEntity();
        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(cliente);

        // Act
        ClienteEntity result = clienteService.updateCliente(cliente);

        // Assert
        assertNotNull(result);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    public void testDeleteCliente() throws Exception {
        // Arrange
        Long id = 1L;

        // Act
        boolean result = clienteService.deleteCliente(id);

        // Assert
        assertTrue(result);
        verify(clienteRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteClienteThrowsException() {
        // Arrange
        Long id = 1L;
        doThrow(new RuntimeException("Error al eliminar")).when(clienteRepository).deleteById(id);

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> clienteService.deleteCliente(id));
        assertEquals("Error al eliminar", exception.getMessage());
    }
}
