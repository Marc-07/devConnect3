import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../styles/listaVacantes.css';

const ListaVacantes = () => {
    const [vacantes, setVacantes] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    // Fetch de las vacantes
    useEffect(() => {
        axios.get('http://localhost:8080/devConnect3_war/listaVacantes', {
            withCredentials: true
        })
            .then(res => {
                console.log('Vacantes:', res.data);
                setVacantes(res.data);
                setLoading(false); // ✅ aquí
            })
            .catch(err => {
                console.error('Error al traer vacantes:', err);
                setError("Error al cargar vacantes");
                setLoading(false); // ✅ y aquí también
            });
    }, []);



    // Función para eliminar vacante
    const handleEliminar = async (id) => {
        if (!window.confirm('¿Estás seguro de eliminar esta vacante?')) return;

        try {
            await axios.delete(
                `http://localhost:8080/listaVacantes?id=${id}`, // Asegúrate de que la URL sea correcta
                {
                    withCredentials: true,
                    headers: {
                        'Authorization': `Bearer ${localStorage.getItem('token')}`
                    }
                }
            );
            setVacantes(prevVacantes => prevVacantes.filter(vacante => vacante.id !== id)); // Actualiza el estado de vacantes
        } catch (err) {
            alert(err.response?.data?.error || 'Error al eliminar');
        }
    };

    // Mostrar carga o error
    if (loading) return <div className="loading">Cargando vacantes...</div>;
    if (error) return (
        <div className="error">
            <p>{error}</p>
            <button onClick={() => window.location.reload()}>Reintentar</button>
        </div>
    );

    return (
        <div className="lista-vacantes-container">
            <header className="navbar">
                <div className="logo">DevConnect</div>
                <ul className="nav-links">
                    <li>¡Hola, {localStorage.getItem('username')}!</li>
                    <li><button className="nav-link" onClick={() => navigate('/lista-vacantes')}>Tus vacantes</button></li>
                    <li><button className="nav-link" onClick={() => {
                        localStorage.removeItem('token');
                        localStorage.removeItem('username');
                        navigate('/login');
                    }}>Salir</button></li>
                </ul>
            </header>

            <table className="vacantes-table">
                <thead>
                <tr>
                    <th>Título</th>
                    <th>Empresa</th>
                    <th>Ubicación</th>
                    <th>Salario</th>
                    <th>Contrato</th>
                    <th>Descripción</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                {vacantes.map((vacante) => (
                    <tr key={vacante.id}>
                        <td>{vacante.titulo}</td>
                        <td>{vacante.empresa}</td>
                        <td>{vacante.ubicacion}</td>
                        <td>{vacante.salario}</td>
                        <td>{vacante.contrato}</td>
                        <td>{vacante.descripcion}</td>
                        <td className="acciones">
                            <button
                                onClick={() => navigate(`/editar-vacante/${vacante.id}`)}
                                className="btn-editar"
                            >
                                ✏️ Editar
                            </button>
                            <button
                                onClick={() => handleEliminar(vacante.id)}
                                className="btn-eliminar"
                            >
                                🗑️ Eliminar
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListaVacantes;
