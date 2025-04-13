import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function VacanteForm() {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        titulo: '',
        empresa: '',
        ubicacion: '',
        salario: '',
        contrato: '',
        descripcion: ''
    });
    const [error, setError] = useState(null);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/devConnect3_war/publicarVacante',
                formData,
                {
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    withCredentials: true
                }
            );

            navigate('/vacante-publicada', {
                state: { vacante: response.data }
            });
        } catch (error) {
            console.error("Error:", error.response?.data?.error || error.message);
            setError("Error al publicar: " + (error.response?.data?.error || "Intente nuevamente"));
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            {/* Tus campos de formulario aquí */}
            <button type="submit">Publicar</button>
            {error && <div className="error">{error}</div>}
        </form>
    );
}

export default VacanteForm;