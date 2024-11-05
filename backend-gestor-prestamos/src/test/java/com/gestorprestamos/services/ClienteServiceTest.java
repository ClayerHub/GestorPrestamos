package com.gestorprestamos.services;

import com.gestorprestamos.entities.ClienteEntity;
import com.gestorprestamos.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository; // Mock del repositorio

    @InjectMocks
    private ClienteService clienteService; // Servicio a probar

    private ClienteEntity cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
        cliente = new ClienteEntity(); // Inicializa un cliente de ejemplo
        cliente.setRut("12.345.678-9");
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setEmail("juan.perez@example.com");
        cliente.setTelefono("123456789");
    }

    @Test
    void whenGetClientes_thenReturnListOfClientes() {
        // Given
        ArrayList<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(cliente);
        when(clienteRepository.findAll()).thenReturn(clientes); // Simula el comportamiento del repositorio

        // When
        ArrayList<ClienteEntity> found = clienteService.getClientes();

        // Then
        assertThat(found).isNotNull();
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getRut()).isEqualTo(cliente.getRut());
    }

    @Test
    void whenSaveCliente_thenClienteIsSaved() {
        // Given
        when(clienteRepository.save(cliente)).thenReturn(cliente); // Simula el comportamiento del repositorio

        // When
        ClienteEntity savedCliente = clienteService.saveCliente(cliente);

        // Then
        assertThat(savedCliente).isNotNull();
        assertThat(savedCliente.getRut()).isEqualTo(cliente.getRut());
    }

    @Test
    void whenRegistrarCliente_thenClienteIsSavedWithFechaRegistro() {
        // Given
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        // When
        ClienteEntity registeredCliente = clienteService.registrarCliente(cliente);

        // Then
        assertThat(registeredCliente).isNotNull();
        assertThat(registeredCliente.getFechaRegistro()).isEqualTo(LocalDate.now());
    }

    @Test
    void whenGetClienteById_thenReturnCliente() {
        // Given
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente)); // Simula el comportamiento del repositorio

        // When
        ClienteEntity foundCliente = clienteService.getClienteById(1L);

        // Then
        assertThat(foundCliente).isNotNull();
        assertThat(foundCliente.getRut()).isEqualTo(cliente.getRut());
    }

    @Test
    void whenGetClienteByRut_thenReturnCliente() {
        // Given
        when(clienteRepository.findByRut(cliente.getRut())).thenReturn(cliente); // Simula el comportamiento del repositorio

        // When
        ClienteEntity foundCliente = clienteService.getClienteByRut(cliente.getRut());

        // Then
        assertThat(foundCliente).isNotNull();
        assertThat(foundCliente.getRut()).isEqualTo(cliente.getRut());
    }

    @Test
    void whenUpdateCliente_thenClienteIsUpdated() {
        // Given
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        // When
        ClienteEntity updatedCliente = clienteService.updateCliente(cliente);

        // Then
        assertThat(updatedCliente).isNotNull();
        assertThat(updatedCliente.getRut()).isEqualTo(cliente.getRut());
    }

    @Test
    void whenDeleteCliente_thenReturnTrue() throws Exception {
        // Given
        doNothing().when(clienteRepository).deleteById(1L); // Simula el comportamiento del repositorio

        // When
        boolean result = clienteService.deleteCliente(1L);

        // Then
        assertThat(result).isTrue();
        verify(clienteRepository, times(1)).deleteById(1L); // Verifica que se llamó al método deleteById
    }
}
