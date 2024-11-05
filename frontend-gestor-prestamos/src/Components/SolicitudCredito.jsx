import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Box, Typography, Alert } from '@mui/material';

function SolicitudCredito() {
    const [solicitud, setSolicitud] = useState({
        nombre: '',
        direccion: '',
        email: '',
        telefono: '',
        ingresos: '',
        montoSolicitado: '',
        plazo: '',
        tipoPrestamo: ''
    });

    const [mensaje, setMensaje] = useState('');

    const handleChange = (e) => {
        setSolicitud({ ...solicitud, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(`${process.env.REACT_APP_API_URL}/solicitudes`, solicitud);
            setMensaje(`Solicitud de crédito enviada con éxito. ID de solicitud: ${response.data.id}`);
            setSolicitud({
                nombre: '',
                direccion: '',
                email: '',
                telefono: '',
                ingresos: '',
                montoSolicitado: '',
                plazo: '',
                tipoPrestamo: ''
            });
        } catch (error) {
            console.error("Error al enviar la solicitud", error);
            setMensaje('Hubo un error al enviar la solicitud de crédito');
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
                    label="Nombre"
                    name="nombre"
                    value={solicitud.nombre}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Dirección"
                    name="direccion"
                    value={solicitud.direccion}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Email"
                    name="email"
                    type="email"
                    value={solicitud.email}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Teléfono"
                    name="telefono"
                    value={solicitud.telefono}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                />
                <TextField 
                    label="Ingresos Mensuales"
                    name="ingresos"
                    type="number"
                    value={solicitud.ingresos}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Monto Solicitado"
                    name="montoSolicitado"
                    type="number"
                    value={solicitud.montoSolicitado}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Plazo (en años)"
                    name="plazo"
                    type="number"
                    value={solicitud.plazo}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Tipo de Préstamo"
                    name="tipoPrestamo"
                    value={solicitud.tipoPrestamo}
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
                    Enviar Solicitud
                </Button>
            </form>
        </Box>
    );
}

export default SolicitudCredito;
