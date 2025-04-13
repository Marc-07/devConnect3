import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

function VacantePublicada() {
    const navigate = useNavigate();
    const { state } = useLocation();
    const vacante = state?.vacante;

    if (!vacante) {
        navigate('/publicar-vacante'); // Redirige si no hay datos
        return null;
    }

    return (
        <div className="vacante-publicada-container">
            <h1>¡Vacante Publicada Exitosamente!</h1>
            <h2>{vacante.titulo}</h2>
            <p><strong>Empresa:</strong> {vacante.empresa}</p>
            <p><strong>Ubicación:</strong> {vacante.ubicacion}</p>
            <p><strong>Tipo contrato:</strong> {vacante.contrato}</p>
            <p><strong>Salario:</strong> {vacante.salario}</p>
            <p><strong>Descripción del puesto:</strong> {vacante.descripcion}</p>
            {/* Resto de los detalles de la vacante */}

            <button onClick={() => navigate('/listaVacantes')}>
                Ver todas las vacantes
            </button>
        </div>
    );
}

export default VacantePublicada;