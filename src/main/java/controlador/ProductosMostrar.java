package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Productos;
import servicios.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

//Anotacion
@WebServlet({"/productohtml"})

public class ProductosMostrar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoServicio servicios = new ProductoServicesImplement();
        List<Productos> productos = servicios.listar();

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        LoginServices auth = new LoginServicesSesionImplement();
        Optional<String> usernameOptional = auth.getUsername(req);



        try (PrintWriter out = resp.getWriter()) {
            // Iniciar la estructura HTML
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Listado de productos</title>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("</head>");
            out.println("<body class=\"bg-light\">");

            // Contenedor principal
            out.println("<div class=\"container my-5\">");
            out.println("<h1 class=\"text-center mb-4\">Listado de Productos</h1>");

            // Mostrar el mensaje de bienvenida solo si el usuario está autenticado
            if (usernameOptional.isPresent()) {
                out.println("<div style='color:blue;'> Hola " + usernameOptional.get() + " Bienvenido</div>");
            }
            out.println("<a href=\"" + req.getContextPath() + "/mostrarCarrito\" class=\"btn btn-primary\">Ver Carrito</a>");
            out.println("<br>");
            out.println("<a href=\"" + req.getContextPath() + "/index.html\" class=\"btn btn-primary\">Ir al inicio</a>");
            // Mostrar la tabla de productos
            out.println("<table class=\"table table-bordered table-hover\">");
            out.println("<thead class=\"table-dark\">");
            out.println("<tr>");
            out.println("<th>ID PRODUCTO</th>");
            out.println("<th>NOMBRE</th>");
            out.println("<th>CATEGORIA</th>");
            // Se muestra el precio y el añadir al carrito si este se añade
            if (usernameOptional.isPresent()) {
                out.println("<th>PRECIO</th>");
                out.println("<th>Añadir al carrito</th>");
            }
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            // Se hace un for each para poder obtener todos los valores de productos sin necesidad de poner un inicio y un final
            //se obtiene los valores del modelo Productos con la vartiabl p sobre la lista de productos
            for (Productos p : productos) {
                out.println("<tr>");
                out.println("<td>" + p.getIdProductos() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getCategoria() + "</td>");

                // Mostrar el precio solo si el usuario está autenticado
                if (usernameOptional.isPresent()) {
                    out.println("<td>" + p.getPrecio() + "</td>");
                    // Formulario para añadir al carrito solo si el usuario está autenticado
                    out.println("<td>");
                    out.println("<form action=\"" + req.getContextPath() + "/Carrito\" method=\"post\">");
                    out.println("<input type=\"hidden\" name=\"idProducto\" value=\"" + p.getIdProductos() + "\">");
                    out.println("<input type=\"hidden\" name=\"nombre\" value=\"" + p.getNombre() + "\">");
                    out.println("<input type=\"hidden\" name=\"precio\" value=\"" + p.getPrecio() + "\">");
                    //Aqui se define value=1 por que la cantidad que se envia es 1 entonces com aqui no tengo
                    //parametros definidios como los tros no tengo una variable para concatenar
                    out.println("<input type=\"hidden\" name=\"cantidad\" value=\"1\">");
                    out.println("<button type=\"submit\" class=\"btn btn-success\">Añadir al carrito</button>");
                    out.println("</form>");
                    out.println("</td>");
                }
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");

            // Cerrar la estructura HTML
            out.println("</body>");
            out.println("</html>");
        }
    }
}
