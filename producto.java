/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arreglos;

/**
 *
 * @author ESTUDIANTES
 */
import java.util.Scanner;

public class producto {

    static String[] nombres = new String[5];
    static double[] precios = new double[5];
    static int contador = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n -- MENU CRUD PRODUCTOS ");
            System.out.println("1. AGREGAR PRODUCTO (CREATE)");
            
            System.out.println("o. salir");
            System.out.println("SELECCIONE UNA OPCION: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Nombre del Producto: ");
                    String nombre = sc.nextLine();

                    System.out.println("Precio del Producto: ");

                    double precio = sc.nextDouble();
                    agregar(nombre, precio);
                }

                case 2 ->{
                    Listar();
                }
                case 3 ->{ 
            }

        }
            
        }while (opcion != 0);

        sc.close();
    }

   
    static boolean agregar(String nombre, double Precio) {
        if (contador >= nombres.length) {
            System.out.println("no hay espacio en el vector para almacenar: ");
            return false;
        }

        nombres[contador] = nombre;
        precios[contador] = Precio;
        contador++;

        return true;
    }

    // read - Listar
    static void Listar() {
        if (contador == 0) {
            System.out.println("No hay productos registrados");
            return;
        }

        for (int i = 0; i < contador; i++) {
            System.out.println(i + " " + nombres[i] + " -$ " + precios[i]);
        }
    }
}

