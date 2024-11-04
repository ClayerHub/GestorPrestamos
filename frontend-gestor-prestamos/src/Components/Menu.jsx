// src/Components/Menu.jsx
import React from 'react';
import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import { Link } from 'react-router-dom';

const Menu = () => {
    return (
        <Box>
            <AppBar position="static">
                <Toolbar>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        PrestaBanco - Opciones
                    </Typography>
                </Toolbar>
            </AppBar>
            <Box sx={{ mt: 2, textAlign: 'center' }}>
                <Button component={Link} to="/registro-cliente" variant="contained" sx={{ m: 1 }}>
                    Registro Cliente
                </Button>
                <Button component={Link} to="/calculo-costo-total" variant="contained" sx={{ m: 1 }}>
                    Calculo Costo Total
                </Button>
                <Button component={Link} to="/evaluacion-credito" variant="contained" sx={{ m: 1 }}>
                    Evaluacion Credito
                </Button>
                <Button component={Link} to="/seguimiento-solicitud" variant="contained" sx={{ m: 1 }}>
                    Seguimiento Solicitud
                </Button>
                <Button component={Link} to="/simulacion-credito" variant="contained" sx={{ m: 1 }}>
                    Simulacion Credito
                </Button>
                <Button component={Link} to="/solicitud-prestamo" variant="contained" sx={{ m: 1 }}>
                    Solicitud Prestamo
                </Button>
            </Box>
        </Box>
    );
};

export default Menu;
