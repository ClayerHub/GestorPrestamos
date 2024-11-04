// src/components/SimulacionCredito.js
import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Box, Typography, Alert } from '@mui/material';

function SimulacionCredito() {
    const [simulacion, setSimulacion] = useState({
        monto: '',
        plazo: '',
        tasaInteres: ''
    });

    const [cuotaMensual, setCuotaMensual] = useState(null);
    const [mensaje, setMensaje] = useState('');

    const handleChange = (e) => {
        setSimulacion({ ...simulacion, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(`${process.env.REACT_APP_API_URL}/simulacion/calcular`, simulacion);
            setCuotaMensual(response.data.cuotaMensual);
            setMensaje('');
        } catch (error) {
            console.error("Error en la simulación", error);
            setMensaje('Hubo un error en la simulación de crédito.');
        }
    };

    return (
        <Box sx={{ maxWidth: 600, mx: 'auto', mt: 4, p: 3, boxShadow: 3, borderRadius: 2 }}>
            <Typography variant="h5" component="h2" gutterBottom>
                Simulación de Crédito Hipotecario
            </Typography>
            
            {mensaje && <Alert severity="error" sx={{ mb: 2 }}>{mensaje}</Alert>}
            
            <form onSubmit={handleSubmit}>
                <TextField 
                    label="Monto"
                    name="monto"
                    value={simulacion.monto}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Plazo (años)"
                    name="plazo"
                    value={simulacion.plazo}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Tasa de Interés (% anual)"
                    name="tasaInteres"
                    value={simulacion.tasaInteres}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                
                <Button 
                    type="submit"
                    variant="contained"
                    color="primary"
                    fullWidth
                    sx={{ mt: 2 }}
                >
                    Calcular Cuota Mensual
                </Button>
            </form>

            {cuotaMensual !== null && (
                <Alert severity="success" sx={{ mt: 2 }}>
                    Cuota Mensual: ${cuotaMensual.toFixed(2)}
                </Alert>
            )}
        </Box>
    );
}

export default SimulacionCredito;
