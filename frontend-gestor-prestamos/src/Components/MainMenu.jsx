// src/Components/MainMenu.jsx
import React from 'react';
import { Box, Typography, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const MainMenu = ({ setUserRole }) => {
    const navigate = useNavigate();

    const handleRoleSelection = (role) => {
        setUserRole(role);
        navigate(role === 'ejecutivo' ? '/login-ejecutivo' : '/login-cliente');
    };

    return (
        <Box sx={{ textAlign: 'center', mt: 4 }}>
            <Typography variant="h5" gutterBottom>
                Bienvenido a PrestaBanco
            </Typography>
            <Button variant="contained" sx={{ m: 1 }} onClick={() => handleRoleSelection('ejecutivo')}>
                Ingresar como Ejecutivo
            </Button>
            <Button variant="contained" sx={{ m: 1 }} onClick={() => handleRoleSelection('cliente')}>
                Ingresar como Cliente
            </Button>
            <Button variant="contained" sx={{ m: 1 }} onClick={() => navigate('/registro-usuario')}>
                Registrar Usuario
            </Button>
        </Box>
    );
};

export default MainMenu;
