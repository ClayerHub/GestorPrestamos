// src/components/CostoTotalCalculo.js
import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Box, Typography, Alert } from '@mui/material';

function CostoTotalCalculo() {
    const [datosPrestamo, setDatosPrestamo] = useState({
        monto: '',
        plazo: '',
        tasaInteres: ''
    });

    const [costoTotal, setCostoTotal] = useState(null);
    const [mensaje, setMensaje] = useState('');

    const handleChange = (e) => {
        setDatosPrestamo({ ...datosPrestamo, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.get(`${process.env.REACT_APP_API_URL}/costo-total/calculate`, {
                params: {
                    monto: datosPrestamo.monto,
                    plazo: datosPrestamo.plazo,
                    tasaInteres: datosPrestamo.tasaInteres
                }
            });
            setCostoTotal(response.data);
            setMensaje('');
        } catch (error) {
            console.error("Error al calcular el costo total del préstamo", error);
            setMensaje('Hubo un error al calcular el costo total del préstamo.');
        }
    };

    return (
        <Box sx={{ maxWidth: 600, mx: 'auto', mt: 4, p: 3, boxShadow: 3, borderRadius: 2 }}>
            <Typography variant="h5" component="h2" gutterBottom>
                Cálculo del Costo Total del Préstamo
            </Typography>
            
            <form onSubmit={handleSubmit}>
                <TextField 
                    label="Monto del Préstamo"
                    name="monto"
                    value={datosPrestamo.monto}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Plazo (años)"
                    name="plazo"
                    value={datosPrestamo.plazo}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Tasa de Interés (% anual)"
                    name="tasaInteres"
                    value={datosPrestamo.tasaInteres}
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
                    Calcular Costo Total
                </Button>
            </form>

            {costoTotal !== null && (
                <Alert severity="success" sx={{ mt: 2 }}>
                    Costo Total del Préstamo: ${costoTotal.toFixed(2)}
                </Alert>
            )}
            {mensaje && <Alert severity="error" sx={{ mt: 2 }}>{mensaje}</Alert>}
        </Box>
    );
}

export default CostoTotalCalculo;
