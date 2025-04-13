import React, { useState } from 'react';
import axios from 'axios';
import '../styles/register.css';

function RegisterForm() {
    const [formData, setFormData] = useState({
        nombre: '',
        apellido: '',
        correo: '',
        username: '',
        password: '',
    });

    const [error, setError] = useState('');

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/devConnect3_war/register',
                formData,
                {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }
            );
            console.log('Usuario registrado:', response.data);
            // Redirige a login o muestra mensaje de éxito
            window.location.href = '/login'; // O usa react-router-dom
        } catch (error) {
            console.error('Error detallado:', error.response?.data || error.message);
            setError(error.response?.data?.error || 'Error en el registro. Inténtalo de nuevo.');
        }
    };

    return (
        <div className="register-container">
            <div className="register-image">
                <img src="/img/empleo.jpg" alt="Imagen de registro" />
            </div>

            <div className="register-form">
                <h2>Regístrate</h2>
                {error && <p className="error-message">{error}</p>}
                <form onSubmit={handleSubmit}>
                    <div className="input-group">
                        <label>Nombre:</label>
                        <input
                            type="text"
                            name="nombre"
                            value={formData.nombre}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="input-group">
                        <label>Apellido:</label>
                        <input
                            type="text"
                            name="apellido"
                            value={formData.apellido}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="input-group">
                        <label>Correo:</label>
                        <input
                            type="email"
                            name="correo"
                            value={formData.correo}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="input-group">
                        <label>Usuario:</label>
                        <input
                            type="text"
                            name="username"
                            value={formData.username}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="input-group">
                        <label>Contraseña:</label>
                        <input
                            type="password"
                            name="password"
                            value={formData.password}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <button type="submit" className="btn-register">
                        Registrar
                    </button>
                </form>
                <p>¿Ya tienes una cuenta? <a href="/login">Inicia sesión</a></p>
            </div>
        </div>
    );
}

export default RegisterForm;

