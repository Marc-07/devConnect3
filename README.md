#  🚀 DevConnect - Plataforma de Empleo

🔒 DevConnect es una plataforma en desarrollo que conectará a profesionales con oportunidades laborales. Actualmente, se está construyendo el módulo de autenticación, que permite a los usuarios registrarse e iniciar sesión de manera segura.

---

### 📌Características Actuales

- Registro de usuarios con almacenamiento en MySQL.
- Inicio de sesión con validación de credenciales.
- Manejo de sesiones con HttpSession.
- Redirección dinámica a diferentes páginas según el resultado del login.
- Mensajes de error personalizados en caso de credenciales incorrectas.

---

### 🛠️ Tecnologías Utilizadas

🖥️ Backend: Java EE, Servlets, JDBC
💾 Base de datos: MySQL
🌐 Frontend: HTML, CSS
🛠️ Herramientas: IntelliJ IDEA, Apache Tomcat

---

### 🧪 Pruebas
✔️ Usuario registrado → Puede iniciar sesión correctamente.
❌ Usuario inexistente → Mensaje de error en login.jsp.
⚠️ Usuario ya registrado → Mensaje de error en register.jsp.

---
## 📚 Documentación de la API - DevConnect

Esta API permite el **registro** y **autenticación (login)** de usuarios en la plataforma DevConnect. Se desarrolló utilizando Java, Servlets, Hibernate y MySQL.

---

## 🌐 URL Base
http://localhost:8080/devConnect3_war/api/usuario


---

## 📌 Endpoints disponibles

### 1. 🔐 POST `/register`

**Descripción:**  
Registra un nuevo usuario en la base de datos.  
Si el nombre de usuario ya existe, se devuelve un error.

### Respuesta esperada exito

{
  "mensaje": "Usuario registrado exitosamente"
}

❌ Respuesta si el usuario ya existe
Código: 409 Conflict

Cuerpo:
{
  "error": "El usuario ya está registrado"
}

## 2. 🔓 POST /login
Descripción:
Permite autenticar un usuario.
Verifica si el nombre de usuario existe y si la contraseña es correcta.

✅ Respuesta exitosa
Código: 200 OK

{
  "mensaje": "Inicio de sesión exitoso"
}

### ❌ Respuesta si las credenciales son incorrectas
Código: 401 Unauthorized

{
  "error": "Credenciales incorrectas"
}


### 📝 Notas adicionales
Esta API actualmente no encripta contraseñas (se recomienda usar BCrypt en producción).
El control de sesiones o tokens de autenticación no está implementado (podría añadirse con JWT o sesiones HTTP).
El proyecto se despliega en Tomcat.

### 📜 Licencia
MIT © 2025 - Desarrollado por 
##### María Ramos - Aprendiz de ADSO - SENA
