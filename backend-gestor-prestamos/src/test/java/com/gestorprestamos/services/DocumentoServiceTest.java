package com.gestorprestamos.services;

import com.gestorprestamos.entities.DocumentoEntity;
import com.gestorprestamos.repositories.DocumentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DocumentoServiceTest {

    @InjectMocks
    private DocumentoService documentoService;

    @Mock
    private DocumentoRepository documentoRepository;

    private DocumentoEntity documento;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        documento = new DocumentoEntity();
        documento.setId(1L);
        documento.setTipoDocumento("Factura");
        documento.setRutaArchivo("/ruta/del/documento");
        documento.setFechaCarga(new Date());
    }

    @Test
    void whenGetDocumentos_thenReturnListOfDocumentos() {
        // Given
        ArrayList<DocumentoEntity> documentos = new ArrayList<>();
        documentos.add(documento);
        when(documentoRepository.findAll()).thenReturn(documentos);

        // When
        ArrayList<DocumentoEntity> result = documentoService.getDocumentos();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(documento.getId(), result.get(0).getId());
    }

    @Test
    void whenSaveDocumento_thenReturnSavedDocumento() {
        // Given
        when(documentoRepository.save(documento)).thenReturn(documento);

        // When
        DocumentoEntity result = documentoService.saveDocumento(documento);

        // Then
        assertNotNull(result);
        assertEquals(documento.getId(), result.getId());
    }

    @Test
    void whenGetDocumentoById_thenReturnDocumento() {
        // Given
        when(documentoRepository.findById(1L)).thenReturn(Optional.of(documento));

        // When
        DocumentoEntity result = documentoService.getDocumentoById(1L);

        // Then
        assertNotNull(result);
        assertEquals(documento.getId(), result.getId());
    }

    @Test
    void whenUpdateDocumento_thenReturnUpdatedDocumento() {
        // Given
        when(documentoRepository.save(documento)).thenReturn(documento);

        // When
        DocumentoEntity result = documentoService.updateDocumento(documento);

        // Then
        assertNotNull(result);
        assertEquals(documento.getId(), result.getId());
    }

    @Test
    void whenDeleteDocumento_thenReturnTrue() throws Exception {
        // Given
        Long documentoId = 1L;
        doNothing().when(documentoRepository).deleteById(documentoId);

        // When
        boolean result = documentoService.deleteDocumento(documentoId);

        // Then
        assertTrue(result);
        verify(documentoRepository, times(1)).deleteById(documentoId);
    }

    @Test
    void whenDeleteDocumentoNotFound_thenThrowException() {
        // Given
        Long documentoId = 1L;
        doThrow(new RuntimeException("Documento no encontrado")).when(documentoRepository).deleteById(documentoId);

        // When & Then
        Exception exception = assertThrows(Exception.class, () -> {
            documentoService.deleteDocumento(documentoId);
        });
        assertEquals("Documento no encontrado", exception.getMessage());
    }
}
