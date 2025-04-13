import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../styles/welcome.css';

function Welcome() {
    const [username, setUsername] = useState('');
    const [vacante, setVacante] = useState({
        titulo: '',
        empresa: '',
        ubicacion: '',
        salario: '',
        contrato: '',
        descripcion: ''
    });
    const [error, setError] = useState(null);
    const [isSubmitting, setIsSubmitting] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        // Verificar autenticación al cargar el componente
        const storedUsername = localStorage.getItem('username');
        if (!storedUsername) {
            navigate('/login', { state: { from: '/welcome' } });
        } else {
            setUsername(storedUsername);
        }
    }, [navigate]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setVacante(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setIsSubmitting(true);
        setError(null);

        try {
            const response = await axios.post(
                'http://localhost:8080/devConnect3_war/publicarVacante',
                vacante,  // Usamos el estado vacante en lugar de formData
                {
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    withCredentials: true
                }
            );

            if (response.data && response.data.id) {
                navigate('/vacante-publicada', {
                    state: { vacante: response.data }
                });
            } else {
                setError('La vacante se creó pero no se recibieron datos de confirmación');
            }
        } catch (err) {
            console.error('Error detallado:', err.response?.data || err.message);
            setError(err.response?.data?.error || 'Error al conectar con el servidor');
        } finally {
            setIsSubmitting(false);
        }
    };

    const handleLogout = () => {
        // Limpiar localStorage y redirigir
        localStorage.removeItem('isAuthenticated');
        localStorage.removeItem('username');
        navigate('/');
    };

    return (
        <div className="welcome-container">
            <header>
                <nav className="navbar">
                    <div className="logo">DevConnect</div>
                    <ul className="nav-links">
                        <li>¡Hola, {username}!</li>
                        <li><Link to="/listaVacantes">Tus vacantes</Link></li>
                        <li><button onClick={handleLogout} className="logout-btn">Salir</button></li>
                    </ul>
                </nav>
            </header>

            <main className="container">
                <h1>Nueva Vacante</h1>
                <p>Llena el formulario y publica tus vacantes</p>

                {error && <div className="error-message">{error}</div>}

                <form onSubmit={handleSubmit}>
                    {/* Sección 1: Información General */}
                    <section className="form-section">
                        <h2>Información General</h2>
                        <div className="form-group">
                            <label htmlFor="titulo">Título:</label>
                            <input
                                type="text"
                                id="titulo"
                                name="titulo"
                                value={vacante.titulo}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="empresa">Empresa:</label>
                            <input
                                type="text"
                                id="empresa"
                                name="empresa"
                                value={vacante.empresa}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="ubicacion">Ubicación:</label>
                            <input
                                type="text"
                                id="ubicacion"
                                name="ubicacion"
                                value={vacante.ubicacion}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="salario">Salario:</label>
                            <input
                                type="number"
                                id="salario"
                                name="salario"
                                value={vacante.salario}
                                onChange={handleChange}
                                required
                                min="0"
                                step="0.01"
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="contrato">Tipo de Contrato:</label>
                            <select
                                id="contrato"
                                name="contrato"
                                value={vacante.contrato}
                                onChange={handleChange}
                                required
                            >
                                <option value="">Seleccione un tipo</option>
                                <option value="Tiempo Completo">Tiempo Completo</option>
                                <option value="Medio Tiempo">Medio Tiempo</option>
                                <option value="Freelance">Freelance</option>
                                <option value="Practicas">Prácticas</option>
                            </select>
                        </div>
                    </section>

                    {/* Sección 2: Descripción del Puesto */}
                    <section className="form-section">
                        <h2>Descripción del Puesto</h2>
                        <div className="form-group">
                            <label htmlFor="descripcion">Descripción:</label>
                            <textarea
                                id="descripcion"
                                name="descripcion"
                                rows="5"
                                value={vacante.descripcion}
                                onChange={handleChange}
                                required
                            ></textarea>
                        </div>
                    </section>

                    {/* Botón de envío */}
                    <button
                        type="submit"
                        className="submit-button"
                        disabled={isSubmitting}
                    >
                        {isSubmitting ? 'Publicando...' : 'Publicar Vacante'}
                    </button>
                </form>
            </main>
        </div>
    );
}

export default Welcome;