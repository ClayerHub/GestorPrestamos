import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Box, Typography, Alert } from '@mui/material';
import { useNavigate } from 'react-router-dom';

function LogCliente() {
    const [rut, setRut] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleChange = (e) => {
        setRut(e.target.value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            // Hacer la solicitud a la API para verificar si el cliente existe y si su rol es 'cliente'
            const response = await axios.get(`${process.env.REACT_APP_API_URL}/find-{rut}`);

            // Verificar que el rol sea 'cliente'
            if (response.data.rol === 'cliente') {
                // Si el rol es 'cliente', redirigir a la página de funcionalidades del cliente
                navigate('/cliente');  // Redirige al cliente a su página principal
            } else {
                setError('Este RUT no corresponde a un cliente.');
            }
        } catch (err) {
            console.error(err);
            setError('Error al verificar el cliente. Por favor, revisa el RUT e intenta nuevamente.');
        }
    };

    return (
        <Box sx={{ maxWidth: 600, mx: 'auto', mt: 4, p: 3, boxShadow: 3, borderRadius: 2 }}>
            <Typography variant="h5" component="h2" gutterBottom>
                Ingreso de Cliente
            </Typography>
            
            {error && <Alert severity="error" sx={{ mb: 2 }}>{error}</Alert>}

            <form onSubmit={handleSubmit}>
                <TextField 
                    label="RUT"
                    name="rut"
                    value={rut}
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
                    Ingresar
                </Button>
            </form>
        </Box>
    );
}

export default LogCliente;
