// src/components/SimulacionCredito.js

import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Box, Typography, Alert } from '@mui/material';

function SimulacionCredito() {
    // Estado para los datos de simulación de crédito
    const [simulacion, setSimulacion] = useState({
        idCliente: '',
        montoSolicitado: '',
        plazoSolicitado: '',
        tasaInteres: ''
    });

    // Estado para manejar el resultado de la simulación
    const [cuotaMensual, setCuotaMensual] = useState(null);
    const [mensaje, setMensaje] = useState('');

    // Manejar cambios en los campos del formulario
    const handleChange = (e) => {
        setSimulacion({ ...simulacion, [e.target.name]: e.target.value });
    };

    // Manejar el envío del formulario
    const handleSubmit = async (e) => {
        e.preventDefault();

        // Validación de los datos del formulario
        if (isNaN(simulacion.montoSolicitado) || isNaN(simulacion.plazoSolicitado) || isNaN(simulacion.tasaInteres)) {
            setMensaje('Por favor ingresa valores válidos.');
            return;
        }

        try {
            // Realizar la solicitud POST al backend para calcular la cuota mensual
            const response = await axios.post(`${process.env.REACT_APP_API_URL}/simulacion-creditos/calculate`, {
                idCliente: simulacion.idCliente,
                montoSolicitado: parseFloat(simulacion.montoSolicitado),
                plazoSolicitado: parseInt(simulacion.plazoSolicitado),
                tasaInteres: parseFloat(simulacion.tasaInteres),
            });

            // Obtener la cuota mensual calculada
            const cuota = response.data.cuotaMensual;

            // Validar si la cuota mensual es un número válido
            if (isNaN(cuota)) {
                setMensaje('La cuota mensual no es un valor válido.');
            } else {
                setCuotaMensual(cuota.toFixed(2)); // Formatear la cuota mensual
                setMensaje('');
            }

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

            {/* Mostrar mensaje de error si existe */}
            {mensaje && <Alert severity="error" sx={{ mb: 2 }}>{mensaje}</Alert>}

            {/* Formulario para la simulación de crédito */}
            <form onSubmit={handleSubmit}>
                <TextField 
                    label="ID Cliente"
                    name="idCliente"
                    value={simulacion.idCliente}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Monto Solicitado"
                    name="montoSolicitado"
                    value={simulacion.montoSolicitado}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Plazo (años)"
                    name="plazoSolicitado"
                    value={simulacion.plazoSolicitado}
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

            {/* Mostrar el resultado de la simulación (cuota mensual) */}
            {cuotaMensual !== null && (
                <Alert severity="success" sx={{ mt: 2 }}>
                    Cuota Mensual: ${cuotaMensual}
                </Alert>
            )}
        </Box>
    );
}

export default SimulacionCredito;
