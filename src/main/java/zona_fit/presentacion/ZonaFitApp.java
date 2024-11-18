package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp(){
        boolean salir = false;
        Scanner consola = new Scanner(System.in);

        IClienteDAO clienteDao = new ClienteDAO();

        while(!salir){
            try{
                int opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, clienteDao);

            } catch (Exception e){
                System.out.println("Error al ejecutar opciones: " + e.getMessage());
            }
            //Salto de linea al imprimir menú
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola){

        System.out.print("""
                *** Zona Fit (GYM)
                1. Listar Clientes
                2. Buscar Cliente
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                Elija una opcion:\s""");

        //Obtenemos la opcion elegida por el usuario y la convertimos a int
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion, IClienteDAO clienteDao){

        boolean salir = false;
        Cliente cliente;
        int idCliente;

        switch (opcion){
            case 1:
                var clientes = clienteDao.listarClientes();
                System.out.println("--- Listado de CLientes ---");
                clientes.forEach(System.out::println);
                break;
            case 2:
                System.out.println("Introduce la ID del cliente: ");
                idCliente = Integer.parseInt(consola.nextLine());
                cliente = new Cliente(idCliente);
                var encontrado = clienteDao.buscarClientePorId(cliente);
                if(encontrado)
                    System.out.println("Cliente encontrado: " + cliente);
                else
                    System.out.println("Cliente NO encontrado: " + cliente);
                break;
            case 3:
                System.out.println("--- Agregar Cliente ---");

                System.out.println("Introduce el nombre del cliente: ");
                String nombre = consola.nextLine();

                System.out.println("Introduce el apellido del cliente: ");
                String apellido = consola.nextLine();

                System.out.println("Introduce membresia: ");
                int membresia = Integer.parseInt(consola.nextLine());

                cliente = new Cliente(nombre,apellido,membresia);
                var agregado = clienteDao.agregarCliente(cliente);
                if (agregado)
                    System.out.println("Cliente agregado: " + cliente);
                else
                    System.out.println("Cliente NO agregado: " + cliente);
                break;
            case 4:
                System.out.println("--- Modificar Cliente ---");

                System.out.println("Introduce ID del cliente: ");
                idCliente = Integer.parseInt(consola.nextLine());

                System.out.println("Introduce el nombre del cliente: ");
                String nuevoNombre = consola.nextLine();

                System.out.println("Introduce el apellido del cliente: ");
                String nuevoApellido = consola.nextLine();

                System.out.println("Introduce membresia: ");
                int nuevaMembresia = Integer.parseInt(consola.nextLine());

                cliente = new Cliente(idCliente,nuevoNombre,nuevoApellido,nuevaMembresia);

                var modificado = clienteDao.modificarCliente(cliente);
                if (modificado)
                    System.out.println("Cliente modificado: " + cliente);
                else
                    System.out.println("Cliente NO modificado: " + cliente);
                break;
            case 5:
                System.out.println("--- Eliminar Cliente ---");

                System.out.println("Introduce la ID del cliente a eliminar: ");
                idCliente = Integer.parseInt(consola.nextLine());

                cliente = new Cliente(idCliente);

                var eliminado = clienteDao.eliminarCliente(cliente);
                if (eliminado)
                    System.out.println("Cliente eliminado: " + cliente);
                else
                    System.out.println("Cliente NO eliminado: " + cliente);
                break;
            case 6:
                System.out.println("Saliendo...");
                salir = true;
                break;
            default:
                System.out.println("Opcion no reconocida: " + opcion);
                break;
        }
        return salir;
    }
}
