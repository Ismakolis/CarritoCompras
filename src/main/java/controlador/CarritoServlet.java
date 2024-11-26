package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Carrito;
import modelo.Productos;
import servicios.ProductoServicio;
import servicios.ProductoServicesImplement;

import java.io.IOException;

@WebServlet("/Carrito")
public class CarritoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Se obtiene el ID del producto y la cantidad desde los parámetros del formulario enviado por el cliente.
        // El formulario de la página web probablemente tiene un campo "idProducto" y otro "cantidad".
        /*
        En estas lineas de codigo lo que se haces transformar el parametro enviado desde el cliente y transformarlo a entero
        por que cuando yo doy en añadir al carrito desde el formualrio se ennia como string y para poder hacer calculos
        con la cantidad necesito un enero y para hacer com paraciones con idProducto igual necesito un entero
        entonces lo que hago es definir una varible y en esa variable guardar el dato transformato o convertido de string a entero
        */
        int idProducto = Integer.parseInt(req.getParameter("idProducto"));
        int cantidad = Integer.parseInt(req.getParameter("cantidad"));


        /*Se crea una instancia de Productos servicios y Productos servicios implement pior que ahi se maneja los
        productos*/
        ProductoServicio servicio = new ProductoServicesImplement();

        /*
        Esta parte del codigo lo que hace es que obtiene el producto que sea igual con el id del producto entonces el
         servicio.listar() me devuelve la lista de productos que estan regitrados en la lista de servicios Productos y con Stram lo que se hace es que
         stream() recorre los elementos de la lista en secuencia y con stream se puede utilizar filtros para buscar coinsidencias.
         *Lo que hace filter(p -> p.getIdProductos() == idProducto) busca las coincidencias de los productos haciendo referencia al id
         y mediante el id busca las coincidencias para traer lo productos
         *lo que hace findFirst() es obtener el primer valor que cumpla con las condiciones si es que en el caso existe,
         *aqui lo que hace el orElse(null) es que si no se encuentra el producto me regresa un nulo lo que queire decir es que
         si no hay el producto me envia un dato nulo para evitar errores.
         */
        Productos producto = servicio.listar().stream()
                .filter(p -> p.getIdProductos() == idProducto)
                .findFirst()
                .orElse(null);

        //este if lo que hace es verificar si un producto en nulo o no o si existe por el la condicion de si producto es distinto a nulo
        if (producto != null) {
            //si el producto existe se obtiene la sesion actual o la sesion con la que se inicio antes
            HttpSession session = req.getSession();
            // Recuperamos el carrito de la sesión, si ya existe. La clave usada para almacenar el carrito es "carrito".
            //Tambien se trae el carrito asisiado a la sesion con la palabra clave carrito que ya se definio antes.
            Carrito carrito = (Carrito) session.getAttribute("carrito");
            //Lo qe hace este if es que si el carrito no existe entonces crea y se le asigna a la sesion
            // esta parte del codigo eita errores por que antes no se creo un carrito como tal para agregar los productos se usa la clave carrito
            if(carrito==null){
                carrito=new Carrito();
                session.setAttribute("carrito",carrito);
            }
            //se llama el metodo de agerar los productos del carritoServlet par agregar los productos si es que se cumple las condiones
            carrito.agregarProducto(producto, cantidad);

        }

       //Lo que hace esta parte del codigo es redireccionar a la apgina de producos
        resp.sendRedirect(req.getContextPath() + "/productohtml");
    }
}
