// src/App.jsx
import React, { useState } from 'react';
import { Box, Typography, Container } from '@mui/material';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import MainMenu from './Components/MainMenu';
import MenuEjecutivo from './Components/MenuEjecutivo';
import MenuCliente from './Components/MenuCliente';
import RegistroUsuario from './Components/RegistroUsuario'; // Nuevo componente para registrar usuario
import LoginCliente from './Components/LoginCliente';   // Componente para ingresar como cliente con RUT
import LoginEjecutivo from './Components/LoginEjecutivo'; // Componente para ingresar como ejecutivo
import SimulacionCredito from './Components/SimulacionCredito';
import SolicitudCredito from './Components/SolicitudCredito';
import EvaluacionCredito from './Components/EvaluacionCredito';
import SeguimientoSolicitud from './Components/SeguimientoSolicitud';
import CostoTotalCalculo from './Components/CostoTotalCalculo';

function App() {
    const [userRole, setUserRole] = useState(null); // Estado que almacena el rol del usuario (ejecutivo o cliente)

    return (
        <Router>
            <Container>
                <Box sx={{ my: 4, textAlign: 'center' }}>
                    <Typography variant="h3" component="h1" gutterBottom>
                        PrestaBanco - Sistema de Créditos Hipotecarios
                    </Typography>
                </Box>

                <Box sx={{ mt: 4 }}>
                    <Routes>
                        <Route path="/" element={<MainMenu setUserRole={setUserRole} />} />
                        <Route path="/registro-usuario" element={<RegistroUsuario />} />
                        <Route path="/login-cliente" element={<LoginCliente setUserRole={setUserRole} />} />
                        <Route path="/login-ejecutivo" element={<LoginEjecutivo setUserRole={setUserRole} />} /> {/* Nueva ruta para LoginEjecutivo */}

                        {/* Rutas para el usuario ejecutivo */}
                        {userRole === 'ejecutivo' && (
                            <>
                                <Route path="/menu-ejecutivo" element={<MenuEjecutivo />} />
                                <Route path="/calculo-costo-total" element={<CostoTotalCalculo />} />
                                <Route path="/evaluacion-credito" element={<EvaluacionCredito />} />
                                <Route path="/seguimiento-solicitud" element={<SeguimientoSolicitud />} />
                                <Route path="/simulacion-credito" element={<SimulacionCredito />} />
                                <Route path="/solicitud-credito" element={<SolicitudCredito />} />
                            </>
                        )}

                        {/* Rutas para el cliente autenticado */}
                        {userRole === 'cliente' && (
                            <>
                                <Route path="/menu-cliente" element={<MenuCliente />} />
                                <Route path="/simulacion-credito" element={<SimulacionCredito />} />
                                <Route path="/solicitud-credito" element={<SolicitudCredito />} />
                            </>
                        )}

                        <Route path="*" element={<Navigate to="/" />} /> {/* Ruta de fallback */}
                    </Routes>
                </Box>
            </Container>
        </Router>
    );
}

export default App;
