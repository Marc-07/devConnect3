// Importaciones necesarias para el funcionamiento del servlet y la conexión con la base de datos
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/devconnect";
    private static final String USER = "root";
    private static final String PASSWORD = "M@r14iC4rl05";
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtener los parámetros ingresados por el usuario
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LOGGER.info("Intento de inicio de sesión para usuario: " + username);

        // Si el usuario es válido, se inicia sesión y se redirige a la página de bienvenida
        if (username != null && password != null) {
            int validacion = validarUsuario(username, password);

            if (validacion == 1) {
                HttpSession session = request.getSession();
                session.setAttribute("user", username);
                response.sendRedirect(request.getContextPath() + "/view/welcome.jsp");
                return;
            } else if (validacion == -1) {
                response.sendRedirect(request.getContextPath() + "/view/login.jsp?error=not_registered");
                return;
            }
        }

        // Si las credenciales son incorrectas, redirigir con mensaje de error
        response.sendRedirect(request.getContextPath() + "/view/login.jsp?error=invalid_credentials");
    }

    /**
     * Valida el usuario en la base de datos.
     * @return 1 si el usuario y contraseña son correctos, 0 si la contraseña es incorrecta, -1 si el usuario no existe.
     */
    private int validarUsuario(String username, String password) {
        String query = "SELECT password FROM usuarios WHERE username = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String storedPassword = rs.getString("password");
                        return password.equals(storedPassword) ? 1 : 0;
                    } else {
                        return -1; // Usuario no registrado
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en la validación del usuario", e);
        }

        return 0; // Por defecto, credenciales incorrectas
    }
}
