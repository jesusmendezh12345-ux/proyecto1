package com.mycompany.gestor_de_proyecto;

import java.util.Scanner;

public class Gestor_de_proyecto {
    static String[] nombres = new String[20];
    static double[] precios = new double[20];
    static int contador = 0;

    // Credenciales
    static final String ADMIN_USUARIO = "101";
    static final String ADMIN_CLAVE = "1";
    static final String USUARIO_USUARIO = "usuario";
    static final String USUARIO_CLAVE = "2";
    static final String CONSULTA_USUARIO = "consulta";
    static final String CONSULTA_CLAVE = "3";

    static int rol = 0; // 0 = visitante, 1 = administrador, 2 = usuario, 3 = consulta

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n--- MENÚ CRUD PRODUCTOS ---");

            if (rol == 1) {
                System.out.println("Perfil actual: Administrador");
                System.out.println("1. Agregar producto (Create)");
                System.out.println("2. Listar productos (Read)");
                System.out.println("3. Buscar producto (Read)");
                System.out.println("4. Actualizar precio (Update)");
                System.out.println("5. Eliminar producto (Delete)");
                System.out.println("6. Cerrar sesión");
            } else if (rol == 2) {
                System.out.println("Perfil actual: Usuario");
                System.out.println("1. Listar productos (Read)");
                System.out.println("2. Buscar producto (Read)");
                System.out.println("3. Cerrar sesión");
            } else if (rol == 3) {
                System.out.println("Perfil actual: Consulta");
                System.out.println("1. Buscar producto (Read)");
                System.out.println("2. Cerrar sesión");
            } else {
                System.out.println("Perfil actual: Visitante");
                System.out.println("1. Iniciar sesión");
            }

            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            if (!sc.hasNextInt()) {
                System.out.println("Debe ingresar un número.");
                sc.nextLine();
                continue;
            }

            opcion = sc.nextInt();
            sc.nextLine();

            if (rol == 1) {
                switch (opcion) {
                    case 1 -> {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Precio: ");
                        double precio = leerPrecio(sc);
                        agregar(nombre, precio);
                    }
                    case 2 -> listar();
                    case 3 -> buscarYMostrar(sc);
                    case 4 -> {
                        System.out.print("Nombre a actualizar: ");
                        String nombre = sc.nextLine();
                        System.out.print("Nuevo precio: ");
                        double nuevo = leerPrecio(sc);
                        actualizarPrecio(nombre, nuevo);
                    }
                    case 5 -> {
                        System.out.print("Nombre a eliminar: ");
                        String nombre = sc.nextLine();
                        eliminar(nombre);
                    }
                    case 6 -> cerrarSesion();
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            } else if (rol == 2) {
                switch (opcion) {
                    case 1 -> listar();
                    case 2 -> buscarYMostrar(sc);
                    case 3 -> cerrarSesion();
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            } else if (rol == 3) {
                switch (opcion) {
                    case 1 -> buscarYMostrar(sc);
                    case 2 -> cerrarSesion();
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            } else {
                switch (opcion) {
                    case 1 -> iniciarSesion(sc);
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            }
        } while (opcion != 0);

        sc.close();
    }

    static void iniciarSesion(Scanner sc) {
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String clave = sc.nextLine();

        if (usuario.equals(ADMIN_USUARIO) && clave.equals(ADMIN_CLAVE)) {
            rol = 1;
            System.out.println("¡Sesión de administrador iniciada con éxito!");
        } else if (usuario.equals(USUARIO_USUARIO) && clave.equals(USUARIO_CLAVE)) {
            rol = 2;
            System.out.println("¡Sesión de usuario iniciada con éxito!");
        } else if (usuario.equals(CONSULTA_USUARIO) && clave.equals(CONSULTA_CLAVE)) {
            rol = 3;
            System.out.println("¡Sesión de consulta iniciada con éxito!");
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }

    static void cerrarSesion() {
        rol = 0;
        System.out.println("Sesión cerrada correctamente.");
    }

    static double leerPrecio(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.println("Precio inválido. Ingrese un número, por ejemplo: 25000.50");
            sc.nextLine();
            System.out.print("Precio: ");
        }
        double precio = sc.nextDouble();
        sc.nextLine();
        return precio;
    }

    static boolean agregar(String nombre, double precio) {
        if (contador == nombres.length) {
            System.out.println("No hay espacio disponible.");
            return false;
        }
        if (nombre.isBlank() || precio < 0) {
            System.out.println("Datos del producto inválidos.");
            return false;
        }

        nombres[contador] = nombre;
        precios[contador] = precio;
        contador++;
        System.out.println("Producto agregado con éxito.");
        return true;
    }

    static void listar() {
        if (contador == 0) {
            System.out.println("No hay productos registrados.");
            return;
        }

        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        for (int i = 0; i < contador; i++) {
            System.out.println(i + ". " + nombres[i] + " - $" + precios[i]);
        }
    }

    static int buscar(String nombre) {
        for (int i = 0; i < contador; i++) {
            if (nombres[i].equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

    static void buscarYMostrar(Scanner sc) {
        System.out.print("Nombre a buscar: ");
        String nombre = sc.nextLine();
        int idx = buscar(nombre);

        if (idx == -1) {
            System.out.println("Producto no encontrado.");
        } else {
            System.out.println("Producto encontrado: " + nombres[idx] + " - $" + precios[idx]);
        }
    }

    static boolean actualizarPrecio(String nombre, double nuevoPrecio) {
        if (nuevoPrecio < 0) {
            System.out.println("El precio no puede ser negativo.");
            return false;
        }

        int indice = buscar(nombre);
        if (indice == -1) {
            System.out.println("Producto no encontrado.");
            return false;
        }

        precios[indice] = nuevoPrecio;
        System.out.println("Precio actualizado con éxito.");
        return true;
    }

    static boolean eliminar(String nombre) {
        int indice = buscar(nombre);
        if (indice == -1) {
            System.out.println("Producto no encontrado.");
            return false;
        }

        for (int i = indice; i < contador - 1; i++) {
            nombres[i] = nombres[i + 1];
            precios[i] = precios[i + 1];
        }

        nombres[contador - 1] = null;
        precios[contador - 1] = 0.0;
        contador--;
        System.out.println("Producto eliminado con éxito.");
        return true;
    }
}
