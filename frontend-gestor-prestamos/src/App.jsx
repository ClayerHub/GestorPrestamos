// src/App.jsx
import React from 'react';
import { Box, Typography, Container } from '@mui/material';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Menu from './Components/Menu'; // Importa el nuevo componente Menu
import Cliente from './Components/Cliente';
import SimulacionCredito from './Components/SimulacionCredito';
import SolicitudCredito from './Components/SolicitudCredito';
import EvaluacionCredito from './Components/EvaluacionCredito';
import SeguimientoSolicitud from './Components/SeguimientoSolicitud';
import CostoTotalCalculo from './Components/CostoTotalCalculo';

function App() {
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
                        <Route path="/" element={<Menu />} />
                        <Route path="/registro-cliente" element={<Cliente />} />
                        <Route path="/calculo-costo-total" element={<CostoTotalCalculo />} />
                        <Route path="/evaluacion-credito" element={<EvaluacionCredito />} />
                        <Route path="/seguimiento-solicitud" element={<SeguimientoSolicitud />} />
                        <Route path="/simulacion-credito" element={<SimulacionCredito />} />
                        <Route path="/solicitud-credito" element={<SolicitudCredito />} />
                    </Routes>
                </Box>
            </Container>
        </Router>
    );
}

export default App;
