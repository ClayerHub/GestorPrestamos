// src/Components/MenuCliente.jsx
import React from 'react';
import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import { Link } from 'react-router-dom';

const MenuCliente = () => {
    return (
        <Box>
            <AppBar position="static">
                <Toolbar>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        PrestaBanco - Menú Cliente
                    </Typography>
                </Toolbar>
            </AppBar>
            <Box sx={{ mt: 2, textAlign: 'center' }}>
                <Button component={Link} to="/simulacion-credito" variant="contained" sx={{ m: 1 }}>
                    Simulación de Crédito
                </Button>
                <Button component={Link} to="/solicitud-credito" variant="contained" sx={{ m: 1 }}>
                    Solicitud de Crédito
                </Button>
            </Box>
        </Box>
    );
};

export default MenuCliente;
