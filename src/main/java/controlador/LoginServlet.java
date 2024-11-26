package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import servicios.LoginServices;
import servicios.LoginServicesImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/logins","/login.html"})

public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    //sobre esxcribimos el metodo doGet
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // se hace una preguinta si la cookie es distinta a null
        //si es verdadero obtengo la cookie caso contrario creo un  nuebo objeto
        //de la cookie
        //Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];
        //busco en todo el arreglo de cookie si existe la cookie solicitada y la convierto en string
        /*Optional<String> cookieOptional = Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst();*/
        // creo un nuevo objeto de la cookie

        //implementamos el objeto sesion
        LoginServices auth = new LoginServicesImplement();
        Optional<String> usernameOptional = auth.getUsername(req);
        if (usernameOptional.isPresent()) {
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Bienvenido usuario</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Hola nuevamente " + usernameOptional.get() + " ya iniciaste sesion </h1>");
                out.println("<ul>");
                out.println("</ul>");
                out.println("<a href='" + req.getContextPath() + "/index.html'>Volver al inicio</a>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            //resp.setContentType("text/html;charset=UTF-8 ");
           /* try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Bienvenido a la aplicacion</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Bienvenido a mi aplicacion</h1>");
                out.println("</body>");
                out.println("</html>");
            }*/


            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            resp.getWriter().write("Error: Credenciales incorrectas.");

        }
    }
}
