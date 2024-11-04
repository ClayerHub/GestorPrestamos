// src/components/SolicitudPrestamo.js
import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Box, Typography, Alert } from '@mui/material';

function SolicitudPrestamo() {
    const [prestamo, setPrestamo] = useState({
        idCliente: '',
        monto: '',
        plazo: '',
        tasaInteres: '',
        tipoPrestamo: ''
    });

    const [mensaje, setMensaje] = useState('');

    const handleChange = (e) => {
        setPrestamo({ ...prestamo, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(`${process.env.REACT_APP_API_URL}/prestamos/solicitar`, prestamo);
            setMensaje(`Solicitud registrada con éxito. Estado inicial: ${response.data.estado}`);
            setPrestamo({
                idCliente: '',
                monto: '',
                plazo: '',
                tasaInteres: '',
                tipoPrestamo: ''
            });
        } catch (error) {
            console.error("Error al registrar la solicitud de préstamo", error);
            setMensaje('Hubo un error al registrar la solicitud de préstamo.');
        }
    };

    return (
        <Box sx={{ maxWidth: 600, mx: 'auto', mt: 4, p: 3, boxShadow: 3, borderRadius: 2 }}>
            <Typography variant="h5" component="h2" gutterBottom>
                Solicitud de Crédito Hipotecario
            </Typography>
            
            {mensaje && <Alert severity="info" sx={{ mb: 2 }}>{mensaje}</Alert>}
            
            <form onSubmit={handleSubmit}>
                <TextField 
                    label="ID Cliente"
                    name="idCliente"
                    value={prestamo.idCliente}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Monto"
                    name="monto"
                    type="number"
                    value={prestamo.monto}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Plazo (años)"
                    name="plazo"
                    type="number"
                    value={prestamo.plazo}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Tasa de Interés (% anual)"
                    name="tasaInteres"
                    type="number"
                    value={prestamo.tasaInteres}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Tipo de Préstamo"
                    name="tipoPrestamo"
                    value={prestamo.tipoPrestamo}
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
                    Solicitar Préstamo
                </Button>
            </form>
        </Box>
    );
}

export default SolicitudPrestamo;
