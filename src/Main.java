import empleados.Empleado;
import gestion.EmpleadoConProyectos;
import empleados.EmpleadoFreelance;
import empleados.EmpleadoTiempoCompleto;
import empleados.EmpleadoTiempoParcial;
import gestion.GestorTiposEmpleado;
import interfaces.IEmpleado;
import interfaces.IRepositorioEmpleados;
import repositorio.RepositorioEmpleados;
import servicio.ServicioGestionEmpleados;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que maneja la interfaz de usuario del Sistema de Gestión de Empleados.
 * Permite gestionar empleados, tipos de empleados y sus proyectos a través de un menú interactivo.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ServicioGestionEmpleados servicio;
    private static final GestorTiposEmpleado gestorTipos;

    static {
        IRepositorioEmpleados repositorio = new RepositorioEmpleados();
        servicio = new ServicioGestionEmpleados(repositorio);
        gestorTipos = new GestorTiposEmpleado();
    }

    /**
     * Punto de entrada principal del programa.
     * Muestra un menú interactivo y maneja las selecciones del usuario.
     */
    public static void main(String[] args) {
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        agregarEmpleado();
                        break;
                    case 2:
                        agregarNuevoTipoEmpleado();
                        break;
                    case 3:
                        listarTiposEmpleado();
                        break;
                    case 4:
                        listarEmpleados();
                        break;
                    case 5:
                        asignarProyecto();
                        break;
                    case 6:
                        verProyectosEmpleado();
                        break;
                    case 7:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor ingrese un número válido.");
                scanner.nextLine(); // Limpiar buffer
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                scanner.nextLine(); // Limpiar buffer
            }
        }
        System.out.println("¡Gracias por usar el Sistema de Gestión de Empleados!");
        scanner.close();
    }

    /**
     * Muestra el menú principal del sistema con todas las opciones disponibles.
     */
    private static void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTIÓN DE EMPLEADOS ===");
        System.out.println("1. Agregar empleado");
        System.out.println("2. Agregar nuevo tipo de empleado");
        System.out.println("3. Ver tipos de empleado disponibles");
        System.out.println("4. Listar empleados");
        System.out.println("5. Asignar proyecto a empleado");
        System.out.println("6. Ver proyectos de empleado");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Permite al usuario agregar un nuevo tipo de empleado al sistema.
     * Verifica que el tipo no esté duplicado antes de agregarlo.
     */
    private static void agregarNuevoTipoEmpleado() {
        System.out.println("\n== AGREGAR NUEVO TIPO DE EMPLEADO ==");
        System.out.print("Nombre del nuevo tipo: ");
        String nuevoTipo = scanner.nextLine().trim();

        if (nuevoTipo.isEmpty()) {
            System.out.println("Error: El tipo de empleado no puede estar vacío.");
            return;
        }

        gestorTipos.agregarNuevoTipo(nuevoTipo);
        System.out.println("Tipo de empleado agregado exitosamente.");
    }

    /**
     * Muestra una lista numerada de todos los tipos de empleado disponibles en el sistema.
     */
    private static void listarTiposEmpleado() {
        System.out.println("\n== TIPOS DE EMPLEADO DISPONIBLES ==");
        List<String> tipos = gestorTipos.obtenerTipos();

        if (tipos.isEmpty()) {
            System.out.println("No hay tipos de empleado registrados.");
            return;
        }

        for (int i = 0; i < tipos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tipos.get(i));
        }
    }

    /**
     * Permite al usuario agregar un nuevo empleado al sistema.
     * El usuario debe proporcionar nombre, ID y seleccionar un tipo de empleado existente.
     */
    private static void agregarEmpleado() {
        System.out.println("\n== AGREGAR NUEVO EMPLEADO ==");

        List<String> tipos = gestorTipos.obtenerTipos();
        if (tipos.isEmpty()) {
            System.out.println("Error: No hay tipos de empleado registrados. Por favor, agregue al menos un tipo primero.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío.");
            return;
        }

        System.out.print("ID: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("Error: El ID no puede estar vacío.");
            return;
        }

        System.out.println("Tipos de empleado disponibles:");
        for (int i = 0; i < tipos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tipos.get(i));
        }

        System.out.print("Seleccione el tipo: ");
        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (seleccion < 1 || seleccion > tipos.size()) {
            System.out.println("Error: Tipo no válido.");
            return;
        }

        String tipoSeleccionado = tipos.get(seleccion - 1);
        IEmpleado empleado = new Empleado(nombre, id, tipoSeleccionado);
        servicio.registrarEmpleado(empleado);
        System.out.println("Empleado agregado exitosamente.");
    }

    /**
     * Muestra una lista de todos los empleados registrados en el sistema.
     */
    private static void listarEmpleados() {
        System.out.println("\n== LISTA DE EMPLEADOS ==");
        List<IEmpleado> empleados = servicio.obtenerEmpleados();

        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        for (IEmpleado empleado : empleados) {
            System.out.printf("ID: %s | Nombre: %s | Tipo: %s%n",
                    empleado.getId(),
                    empleado.getNombre(),
                    empleado.getTipo());
        }
    }

    /**
     * Permite asignar un proyecto a un empleado existente.
     * El usuario debe proporcionar el ID del empleado y el nombre del proyecto.
     */
    private static void asignarProyecto() {
        System.out.println("\n== ASIGNAR PROYECTO ==");

        System.out.print("ID del empleado: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("Error: El ID no puede estar vacío.");
            return;
        }

        System.out.print("Nombre del proyecto: ");
        String proyecto = scanner.nextLine().trim();
        if (proyecto.isEmpty()) {
            System.out.println("Error: El nombre del proyecto no puede estar vacío.");
            return;
        }

        try {
            EmpleadoConProyectos empleadoGestor = servicio.convertirAGestorProyectos(id);
            empleadoGestor.getGestionProyectos().asignarProyecto(proyecto);
            System.out.println("Proyecto asignado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Muestra todos los proyectos asignados a un empleado específico.
     * El usuario debe proporcionar el ID del empleado.
     */
    private static void verProyectosEmpleado() {
        System.out.println("\n== PROYECTOS DEL EMPLEADO ==");

        System.out.print("ID del empleado: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("Error: El ID no puede estar vacío.");
            return;
        }

        try {
            EmpleadoConProyectos empleadoGestor = servicio.convertirAGestorProyectos(id);
            List<String> proyectos = empleadoGestor.getGestionProyectos().obtenerProyectos();

            if (proyectos.isEmpty()) {
                System.out.println("El empleado no tiene proyectos asignados.");
                return;
            }

            System.out.println("Proyectos asignados:");
            for (String proyecto : proyectos) {
                System.out.println("- " + proyecto);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}