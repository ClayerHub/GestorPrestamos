// src/components/EvaluacionCredito.js
import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Box, Typography, Alert } from '@mui/material';

function EvaluacionCredito() {
    const [evaluacion, setEvaluacion] = useState({
        idPrestamo: '',
        cuotaIngreso: '',
        historialCredito: '',
        antiguedadLaboral: '',
        deudaIngreso: '',
        montoFinanciamiento: '',
        capacidadAhorro: ''
    });

    const [resultado, setResultado] = useState('');
    const [mensaje, setMensaje] = useState('');

    const handleChange = (e) => {
        setEvaluacion({ ...evaluacion, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(`${process.env.REACT_APP_API_URL}/evaluaciones/evaluar`, evaluacion);
            setResultado(`Resultado de la Evaluación: ${response.data.resultado}`);
            setMensaje('');
        } catch (error) {
            console.error("Error en la evaluación del crédito", error);
            setMensaje('Hubo un error en la evaluación del crédito.');
        }
    };

    return (
        <Box sx={{ maxWidth: 600, mx: 'auto', mt: 4, p: 3, boxShadow: 3, borderRadius: 2 }}>
            <Typography variant="h5" component="h2" gutterBottom>
                Evaluación de Crédito
            </Typography>
            
            {mensaje && <Alert severity="error" sx={{ mb: 2 }}>{mensaje}</Alert>}
            {resultado && <Alert severity="success" sx={{ mb: 2 }}>{resultado}</Alert>}
            
            <form onSubmit={handleSubmit}>
                <TextField 
                    label="ID Préstamo"
                    name="idPrestamo"
                    value={evaluacion.idPrestamo}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Relación Cuota/Ingreso"
                    name="cuotaIngreso"
                    type="number"
                    value={evaluacion.cuotaIngreso}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Historial Crediticio"
                    name="historialCredito"
                    value={evaluacion.historialCredito}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Antigüedad Laboral (años)"
                    name="antiguedadLaboral"
                    type="number"
                    value={evaluacion.antiguedadLaboral}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Relación Deuda/Ingreso"
                    name="deudaIngreso"
                    type="number"
                    value={evaluacion.deudaIngreso}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Monto de Financiamiento (%)"
                    name="montoFinanciamiento"
                    type="number"
                    value={evaluacion.montoFinanciamiento}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Capacidad de Ahorro"
                    name="capacidadAhorro"
                    value={evaluacion.capacidadAhorro}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                />
                
                <Button 
                    type="submit"
                    variant="contained"
                    color="primary"
                    fullWidth
                    sx={{ mt: 2 }}
                >
                    Evaluar Crédito
                </Button>
            </form>
        </Box>
    );
}

export default EvaluacionCredito;
