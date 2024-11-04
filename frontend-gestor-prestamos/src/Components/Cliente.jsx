// src/components/Cliente.js
import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button, Box, Typography, Alert } from '@mui/material';

function Cliente() {
    const [cliente, setCliente] = useState({
        rut: '',
        nombre: '',
        apellido: '',
        email: '',
        telefono: '',
        tipoDocumento: '',
        numeroDocumento: '',
        direccion: ''
    });

    const [mensaje, setMensaje] = useState('');

    const handleChange = (e) => {
        setCliente({ ...cliente, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(`${process.env.REACT_APP_API_URL}/clientes/registro`, cliente);
            setMensaje(`Cliente registrado con éxito: ${response.data.nombre} ${response.data.apellido}`);
            setCliente({
                rut: '',
                nombre: '',
                apellido: '',
                email: '',
                telefono: '',
                tipoDocumento: '',
                numeroDocumento: '',
                direccion: ''
            });
        } catch (error) {
            console.error("Error al registrar el cliente", error);
            setMensaje('Hubo un error al registrar el cliente');
        }
    };

    return (
        <Box sx={{ maxWidth: 600, mx: 'auto', mt: 4, p: 3, boxShadow: 3, borderRadius: 2 }}>
            <Typography variant="h5" component="h2" gutterBottom>
                Registro de Nuevo Cliente
            </Typography>
            
            {mensaje && <Alert severity="info" sx={{ mb: 2 }}>{mensaje}</Alert>}
            
            <form onSubmit={handleSubmit}>
                <TextField 
                    label="RUT"
                    name="rut"
                    value={cliente.rut}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Nombre"
                    name="nombre"
                    value={cliente.nombre}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Apellido"
                    name="apellido"
                    value={cliente.apellido}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Email"
                    name="email"
                    type="email"
                    value={cliente.email}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField 
                    label="Teléfono"
                    name="telefono"
                    value={cliente.telefono}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                />
                <TextField 
                    label="Tipo de Documento"
                    name="tipoDocumento"
                    value={cliente.tipoDocumento}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                />
                <TextField 
                    label="Número de Documento"
                    name="numeroDocumento"
                    value={cliente.numeroDocumento}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                />
                <TextField 
                    label="Dirección"
                    name="direccion"
                    value={cliente.direccion}
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
                    Registrar Cliente
                </Button>
            </form>
        </Box>
    );
}

export default Cliente;
