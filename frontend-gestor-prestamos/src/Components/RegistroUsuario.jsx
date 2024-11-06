// src/Components/RegistroUsuario.jsx
import React, { useState } from 'react';
import { Box, TextField, Button, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const RegistroUsuario = () => {
    const [formData, setFormData] = useState({ nombre: '', rut: '', email: '' });
    const [error, setError] = useState(null); // Para mostrar errores, como RUT duplicado
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
    
        // Asignar el rol de "cliente" automáticamente antes de enviar los datos
        const usuarioConRol = { ...formData, rol: 'cliente' }; // Se asigna el rol cliente
    
        try {
            await axios.post(`${process.env.REACT_APP_API_URL}/usuarios/save`, usuarioConRol);
            alert('Usuario registrado exitosamente');
            navigate('/');
        } catch (error) {
            console.error(error);
            // Mostrar el error recibido, si existe
            setError('Error al registrar usuario. ' + (error.response?.data?.message || 'Inténtalo de nuevo.'));
        }
    };    

    return (
        <Box sx={{ textAlign: 'center', mt: 4 }}>
            <Typography variant="h5">Registrar Usuario</Typography>
            <form onSubmit={handleSubmit}>
                <TextField
                    label="Nombre"
                    name="nombre"
                    value={formData.nombre}
                    onChange={handleChange}
                    required
                    sx={{ m: 1 }}
                />
                <TextField
                    label="RUT"
                    name="rut"
                    value={formData.rut}
                    onChange={handleChange}
                    required
                    sx={{ m: 1 }}
                />
                <TextField
                    label="Email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                    sx={{ m: 1 }}
                />
                <Button type="submit" variant="contained" sx={{ m: 1 }}>
                    Registrar
                </Button>
            </form>

            {error && (
                <Typography color="error" sx={{ mt: 2 }}>
                    {error}
                </Typography>
            )}
        </Box>
    );
};

export default RegistroUsuario;
