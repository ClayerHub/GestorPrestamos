// src/components/SeguimientoSolicitud.js
import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Box, Typography, Alert } from '@mui/material';

function SeguimientoSolicitud() {
    const [idPrestamo, setIdPrestamo] = useState('');
    const [estado, setEstado] = useState(null);
    const [mensaje, setMensaje] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.get(`${process.env.REACT_APP_API_URL}/prestamos/seguimiento/${idPrestamo}`);
            if (response.data) {
                setEstado(response.data.estado);
                setMensaje('');
            } else {
                setMensaje('No se encontró ninguna solicitud con el ID proporcionado.');
                setEstado(null);
            }
        } catch (error) {
            console.error("Error al obtener el estado de la solicitud", error);
            setMensaje('Hubo un error al obtener el estado de la solicitud.');
        }
    };

    return (
        <Box sx={{ maxWidth: 600, mx: 'auto', mt: 4, p: 3, boxShadow: 3, borderRadius: 2 }}>
            <Typography variant="h5" component="h2" gutterBottom>
                Seguimiento de Solicitud de Crédito
            </Typography>
            
            <form onSubmit={handleSubmit}>
                <TextField 
                    label="ID de la Solicitud"
                    value={idPrestamo}
                    onChange={(e) => setIdPrestamo(e.target.value)}
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
                    Consultar Estado
                </Button>
            </form>
            
            {estado && <Alert severity="success" sx={{ mt: 2 }}>Estado Actual de la Solicitud: {estado}</Alert>}
            {mensaje && <Alert severity="error" sx={{ mt: 2 }}>{mensaje}</Alert>}
        </Box>
    );
}

export default SeguimientoSolicitud;
