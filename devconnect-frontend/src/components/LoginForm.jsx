import React, { useState } from 'react';
import { useNavigate, useLocation, Link } from 'react-router-dom';
import axios from 'axios';
import '../styles/login.css';

function LoginForm() {
    const [formData, setFormData] = useState({
        username: '',
        password: ''
    });
    const [error, setError] = useState(null);
    const [isSubmitting, setIsSubmitting] = useState(false);
    const navigate = useNavigate();
    const location = useLocation();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value.trim()
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setIsSubmitting(true);
        setError(null);

        // Validación básica
        if (!formData.username || !formData.password) {
            setError('Por favor complete todos los campos');
            setIsSubmitting(false);
            return;
        }

        try {
            const response = await axios.post(
                'http://localhost:8080/devConnect3_war/login',
                JSON.stringify(formData),
                {
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    withCredentials: true // Importante para las cookies de sesión
                }
            );

            if (response.data.success) {
                // Guardar el estado de autenticación
                localStorage.setItem('isAuthenticated', 'true');
                localStorage.setItem('username', response.data.username);

                // Redirigir a la página protegida o a la ruta previa
                navigate(location.state?.from || '/welcome', { replace: true });
            } else {
                setError('Usuario o contraseña incorrectos');
            }
        } catch (err) {
            console.error('Error en login:', err);

            let errorMessage = 'Error al iniciar sesión';
            if (err.response) {
                switch (err.response.data.error) {
                    case 'invalid_credentials':
                        errorMessage = '❌ Usuario o contraseña incorrectos';
                        break;
                    case 'not_registered':
                        errorMessage = '⚠️ Aún no estás registrado.';
                        break;
                    case 'no_autenticado':
                        errorMessage = '🔐 Debes iniciar sesión para continuar';
                        break;
                    default:
                        errorMessage = 'Error en el servidor';
                }
            }

            setError(errorMessage);

            // Auto-ocultar mensaje de error después de 4 segundos
            setTimeout(() => setError(null), 4000);
        } finally {
            setIsSubmitting(false);
        }
    };

    return (
        <div className="login-container">
            <h2>Iniciar Sesión</h2>

            {/* Mensaje de error */}
            {error && (
                <div className="mensaje-error" style={{ opacity: 1 }}>
                    {error}
                    {error.includes('Aún no estás registrado') && (
                        <Link to="/register"> Crear cuenta</Link>
                    )}
                </div>
            )}

            <form onSubmit={handleSubmit}>
                <div className="input-group">
                    <label htmlFor="username">Usuario:</label>
                    <input
                        type="text"
                        id="username"
                        name="username"
                        value={formData.username}
                        onChange={handleChange}
                        required
                        autoFocus
                    />
                </div>

                <div className="input-group">
                    <label htmlFor="password">Contraseña:</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />
                </div>

                <button
                    type="submit"
                    className="btn-login"
                    disabled={isSubmitting}
                >
                    {isSubmitting ? 'Ingresando...' : 'Ingresar'}
                </button>
            </form>

            <div className="links-container">
                <p>¿No tienes cuenta? <Link to="/register">Regístrate</Link></p>
                <Link to="/" className="back-home">← Volver al inicio</Link>
            </div>
        </div>
    );
}

export default LoginForm;