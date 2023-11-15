package Lab4.System;

import Lab4.bean.Usuario;
import Lab4.bean.Libro;
import Lab4.bean.Prestamo;
import Lab4.bean.Revista;
import Lab4.Controller.ControllerReaderJson;
import Lab4.Controller.ControllerWriterJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgramDriver {

    private List<Usuario> listaUsuarios = new ArrayList<>();
    private Usuario usuarioActual;
    private List<Libro> listaLibros = new ArrayList<>();
    private List<Revista> listaRevistas = new ArrayList<>();
    private List<Prestamo> listaPrestamos = new ArrayList<>();

    private ControllerReaderJson<Usuario> usuarioControllerReader = new ControllerReaderJson<>(Usuario.class);
    private ControllerWriterJson<Usuario> usuarioControllerWriter = new ControllerWriterJson<>();

    public static void main(String[] args) {
        ProgramDriver program = new ProgramDriver();
        program.run();
    }

    void run() {
        cargarUsuariosDesdeJson(); // Cargar usuarios desde el archivo JSON al iniciar el programa

        Scanner scanner = new Scanner(System.in);

        boolean salir = false;

        do {
            mostrarMenu();
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    modoRegistro();
                    break;
                case 2:
                    ingresarSalir();
                    break;
                case 3:
                    modoSeleccion();
                    break;
                case 4:
                    modoPrestamo();
                    break;
                case 5:
                    modoPerfil();
                    break;
                case 6:
                    guardarUsuariosEnJson(); // Guardar usuarios en el archivo JSON al salir del programa
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

        } while (!salir);

        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("==== Menú ====");
        System.out.println("1. Modo Registro");
        System.out.println("2. Ingresar/Salir");
        System.out.println("3. Modo Selección");
        System.out.println("4. Modo Préstamo");
        System.out.println("5. Modo Perfil");
        System.out.println("6. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private void modoRegistro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Modo Registro ===");
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = scanner.next();
        System.out.print("Ingrese contraseña: ");
        String contraseña = scanner.next();
        System.out.print("Seleccione plan (base/premium): ");
        String plan = scanner.next();

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombreUsuario);
        nuevoUsuario.setContraseña(contraseña);
        nuevoUsuario.setPlan(plan);

        listaUsuarios.add(nuevoUsuario);
        System.out.println("Usuario creado con éxito: " + nuevoUsuario.getNombre());
        scanner.close();
    }

    private void ingresarSalir() {
        if (usuarioActual == null) {
            System.out.println("Debe registrarse antes de ingresar/salir.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Ingresar/Salir ===");
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = scanner.next();
        System.out.print("Ingrese contraseña: ");
        String contraseña = scanner.next();

        if (usuarioActual.getNombre().equals(nombreUsuario) && usuarioActual.getContraseña().equals(contraseña)) {
            System.out.println("Usuario autenticado con éxito: " + usuarioActual.getNombre());
        } else {
            System.out.println("Autenticación fallida. Inténtelo de nuevo.");
        }
        scanner.close();
    }

    private void modoSeleccion() {
        if (usuarioActual == null) {
            System.out.println("Debe registrarse antes de seleccionar libros o revistas.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Modo Selección ===");
        System.out.println("1. Agregar libro");
        System.out.println("2. Agregar revista");
        System.out.println("3. Vaciar lista");
        int opcionSeleccion = scanner.nextInt();

        switch (opcionSeleccion) {
            case 1:
                if (usuarioActual.getPlan().equals("premium")) {
                    if (listaLibros.size() < 5) {
                        // Lógica para agregar libro premium
                        System.out.println("Agregando libro premium...");
                        Libro libro = new Libro(/* Detalles del libro */);
                        listaLibros.add(libro);
                    } else {
                        System.out.println("No puede prestar más de 5 libros.");
                    }
                } else {
                    if (listaLibros.size() < 3) {
                        // Lógica para agregar libro base
                        System.out.println("Agregando libro base...");
                        Libro libro = new Libro(/* Detalles del libro */);
                        listaLibros.add(libro);
                    } else {
                        System.out.println("No puede prestar más de 3 libros.");
                    }
                }
                break;
            case 2:
                // Lógica para agregar revista
                System.out.println("Agregando revista...");
                Revista revista = new Revista(/* Detalles de la revista */);
                listaRevistas.add(revista);
                break;
            case 3:
                // Lógica para vaciar lista
                System.out.println("Vaciando lista...");
                listaLibros.clear();
                listaRevistas.clear();
                break;
            default:
                System.out.println("Opción no válida. Inténtalo de nuevo.");
        }
        scanner.close();
    }

    private void modoPrestamo() {
        if (usuarioActual == null) {
            System.out.println("Debe registrarse antes de realizar un préstamo.");
            return;
        }

        if (listaLibros.isEmpty() && listaRevistas.isEmpty()) {
            System.out.println("No hay libros o revistas para realizar un préstamo.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Modo Préstamo ===");
        System.out.print("Definir días de entrega: ");
        int diasEntrega = scanner.nextInt();

        if (usuarioActual.getPlan().equals("premium") && diasEntrega > 50) {
            System.out.println("Los usuarios premium no pueden prestar más de 50 días.");
            scanner.close();
            return;
        } else if (!usuarioActual.getPlan().equals("premium") && diasEntrega > 30) {
            System.out.println("Los usuarios base no pueden prestar más de 30 días.");
            scanner.close();
            return;
        }
        Prestamo prestamo = new Prestamo(/* Detalles del préstamo */);
        listaPrestamos.add(prestamo);
        System.out.println("Préstamo registrado con éxito.");
        scanner.close();
    }

    private void modoPerfil() {
        if (usuarioActual == null) {
            System.out.println("Debe registrarse antes de acceder al perfil.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Modo Perfil ===");
        System.out.println("1. Modificar tipo de cliente");
        System.out.println("2. Aplicar cupón de 15 días adicionales");
        System.out.println("3. Cambiar contraseña");
        int opcionPerfil = scanner.nextInt();

        switch (opcionPerfil) {
            case 1:
                // Lógica para modificar tipo de cliente
                System.out.println("Modificando tipo de cliente...");
                System.out.print("Seleccione nuevo plan (base/premium): ");
                String nuevoPlan = scanner.next();
                usuarioActual.setPlan(nuevoPlan);
                System.out.println("Tipo de cliente modificado con éxito.");
                break;
            case 2:
                // Lógica para aplicar cupón de 15 días adicionales
                if (usuarioActual.getPlan().equals("premium")) {
                    System.out.println("Aplicando cupón de 15 días adicionales...");
                    // Lógica específica para usuarios premium
                    // ...
                } else {
                    System.out.println("Esta opción solo está disponible para usuarios premium.");
                }
                break;
            case 3:
                // Lógica para cambiar contraseña
                System.out.println("Cambiando contraseña...");
                System.out.print("Ingrese nueva contraseña: ");
                String nuevaContraseña = scanner.next();
                usuarioActual.setContraseña(nuevaContraseña);
                System.out.println("Contraseña cambiada con éxito.");
                break;
            default:
                System.out.println("Opción no válida. Inténtalo de nuevo.");
        }
        scanner.close();
    }

    private void cargarUsuariosDesdeJson() {
        listaUsuarios = usuarioControllerReader.leerArchivo("usuarios.json");
    }

    private void guardarUsuariosEnJson() {
        usuarioControllerWriter.setType(Usuario.class);
        usuarioControllerWriter.escribirArchivo(listaUsuarios, "usuarios.json");
    }
    
}

