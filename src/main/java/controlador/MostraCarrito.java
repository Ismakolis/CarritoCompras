package controlador;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Carrito;
import modelo.Productos;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/mostrarCarrito")
public class MostraCarrito extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // se definje una variable para el total
        double total = 0;
        double iva = 0.12;

        // Obtener la sesión actual. Si no existe, se crea una nueva sesión.
        HttpSession session = req.getSession();

        //Aqui se recupera el objeto carrito de la sesion que se creo anteior mente si es que existe
        //Con session.getAtribute y se guarda el carrito con la palabra calve carrito
        Carrito carrito = (Carrito) session.getAttribute("carrito");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            // Inicia la estructura HTML de la página.
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Carrito de Compras</title>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("</head>");
            out.println("<body class=\"bg-light\">");

            out.println("<div class=\"container my-5\">");
            out.println("<h1 class=\"text-center\">Carrito de Compras</h1>");

            // Lo que hace esta condicion es verificar si es que existe un carrito o no se obtiene esa varible
            //de esta liena de aqui  session.getAttribute("carrito"); y se muestrana los valores del carrito si no es nullo
            if (carrito != null) {
                out.println("<table class=\"table table-bordered table-hover\">");
                out.println("<thead class=\"table-dark\">");
                out.println("<tr>");
                out.println("<th>Producto</th>");
                out.println("<th>Cantidad</th>");
                out.println("<th>Precio</th>");
                out.println("<th>Total</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");

                //Se utiliza un for para recorrer la lista de productos del carrito que esta en el modelo
                //recuerda que con el .size se obtiene el total de
                for (int i = 0; i < carrito.getProductos().size(); i++) {
                    // Obtener el producto y la cantidad correspondiente en el carrito.
                    /*En estas lineas de codigo lo que se hace es se obtiene el producto en la posision i que es la
                   variable para recorrer la lista de productos del carrito Productos p = carrito.getProductos().get(i);
                   en la siguiente liena lo que se hace es que se obtiene la cantidad del producto de la misma
                   posiscion i de la lista cantidades que esta en el modelo Carritotodo esto con la clave de la sesion carrito*/
                    Productos p = carrito.getProductos().get(i);
                    int cantidad = carrito.getCantidades().get(i);
                    //Aqui se calcula el precio total
                     /* se define una variable para el precio total y eso va ser igual a al precio del producto
                     que viene del modelo de producto .getPrecio y se multiplica por la cantidad*/
                    double precioTotal = p.getPrecio() * cantidad;
                    double precioIva = p.getPrecio()*iva;
                    //aqui se utiliza la variable para el total que se vaya sumndo el precio total a la variable total
                    precioIva += precioIva+precioTotal;
                    total += precioTotal;
                    //Por esta parte solol se obtiene los valores del producto como el no0mbre y el precio eso viene del modelo de productos
                    out.println("<tr>");
                    out.println("<td>" + p.getNombre() + "</td>");
                    out.println("<td>" + cantidad + "</td>");
                    out.println("<td>" + p.getPrecio() + "</td>");
                    out.println("<td>" + precioTotal + "</td>");
                    out.println("</tr>");
                }
                out.println("</tbody>");
                out.println("</table>");
                // Mostrar el total general del carrito.
                out.println("<h3>Iva: "+iva+"</h3>");
                out.println("<h3>Total: "+total+"</h3>");
                out.println("<h3>Total: " + total + "</h3>");


            } else {
                // Caso contrario de no tener nada en el carrito me muestra este mensaje
                out.println("<p>No hay productos en el carrito.</p>");
            }
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
