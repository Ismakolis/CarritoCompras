package modelo;
//se importa la libreria ArrayList por que la lista de producto y cantidades va a ir cambiando dinamicamente
import java.util.ArrayList;
import java.util.List;

public class Carrito {

    //Defino una variable para el total de los productos
    protected double total;

    // Se define una lista para los productos que van a añadirse al carrito
    private List<Productos> productos;

    // Esta lista va a contener las cantidades de cada prodducto para envarlas al carrito, no se define como int
    //Por que en parte no es compatible para el uso de arraylist entonces integer lo que hace es convertir el dato
    //int que entra en cantidades y lo convierte en un objeto o encapsula al int como objeto para el uso de
    //arraylist
    private List<Integer> cantidades;
    //Se define los metodos get y set de las listas
    public List<Productos> getProductos() {
        return productos;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }

    public List<Integer> getCantidades() {
        return cantidades;
    }

    public void setCantidades(List<Integer> cantidades) {
        this.cantidades = cantidades;
    }

    //Este es el constructor de la clase carrito y aqui se inicializa las variables de productos y cantidades.
    public Carrito() {
        //ArrayList<>()lo que hace es crear una lista vacia sin elementos por que los productos y las cantidades
        //se vana a ingresar desde otra interfas y lo que se va ahacer es capturar esso valores
        // y guardarlos en esa lista.
        this.productos = new ArrayList<>();
        this.cantidades = new ArrayList<>();
    }

    // Este metodo es para agregar los productos y las cantidade al carrito
    /*Se define Productos producto por que es eel parametro que recive el metodo un objeto de tipo Producto que
    represwenta el prodcuto que se va agregar al carrito y la cantidad como entrero que es la cantidad que se va agregar*/
    public void agregarProducto(Productos producto, int cantidad) {
        // este for lo que hace es mantener un bucle para el calculo del total del preicio los productos
        /*Utilizo la i para espeficiar el valor inicial final y el incrementable por que la i se utiliza
        comunmente en el for.......    productos.size me da el numero actual de valores en la lista de productos
        que voy agreganndo en la lista del carrito */
        for (int i = 0; i < productos.size(); i++) {
            // Se verifica si el producto ya esta en el carrito entonces con productos.get(i).getIdProductos() se
            //obtiene el producto y a sun ves la id del producto que esta en la posicion (i) por que esa es
            //la variable para recorreer el for. Se hace la compara de == para saber si el producto ya esta en la lista
            //y seguir sumando loa cantidad.
            if (productos.get(i).getIdProductos() == producto.getIdProductos()) {
                // Aqui se setea el valor de cantidades de la lista y se actuliza la cantidad
                //en el caso de que se siga ingresando ese mismo producto
                cantidades.set(i, cantidades.get(i) + cantidad);
                  return;
            }
        }
        //Se agrega el producto al carrito si es que no existe y la cantidad de ese producto
        productos.add(producto);
        cantidades.add(cantidad);
    }



}

