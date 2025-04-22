/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package teatromoro;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author gustavo.dominguez
 */
public class TeatroMoro {

    // Variables estáticas globales
    static final int CAPACIDAD_SALA = 10;
    static final String NOMBRE_TEATRO = "Teatro Moro";
    static final double PRECIO_UNITARIO = 10000;

    static int[] asientos = new int[CAPACIDAD_SALA]; // 0 = disponible, 1 = reservado, 2 = comprado
    static String[] ubicaciones = new String[CAPACIDAD_SALA];
    static int[] cantidades = new int[CAPACIDAD_SALA];
    static double[] precios = new double[CAPACIDAD_SALA];
    static int totalVendidas = 0;
    static double ingresosTotales = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== Sistema de Venta de Entradas - " + NOMBRE_TEATRO + " ===");
            System.out.println("1. Reservar entradas");
            System.out.println("2. Comprar entradas");
            System.out.println("3. Modificar una venta existente");
            System.out.println("4. Imprimir boleta");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEntero(scanner);
            switch (opcion) {
                case 1:
                    reservarAsiento(scanner);
                    break;
                case 2:
                    comprarAsiento(scanner);
                    break;
                case 3:
                    modificarVenta(scanner);
                    break;
                case 4:
                    imprimirBoleta(scanner);
                    break;
                case 5:
                    continuar = false;
                    System.out.println("Gracias por utilizar el sistema de " + NOMBRE_TEATRO);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    static void reservarAsiento(Scanner scanner) {
        mostrarDisponibilidad();
        System.out.print("Seleccione un número de asiento para reservar (0 a " + (CAPACIDAD_SALA - 1) + "): ");
        int asiento = leerEntero(scanner);
        if (asiento >= 0 && asiento < CAPACIDAD_SALA && asientos[asiento] == 0) {
            asientos[asiento] = 1;
            System.out.println("Seleccione la ubicación:");
            System.out.println("1. VIP");
            System.out.println("2. Platea");
            System.out.println("3. General");
            int ubicacionSeleccionada = leerEntero(scanner);
            String ubicacion = "";
            switch (ubicacionSeleccionada) {
                case 1: ubicacion = "VIP"; break;
                case 2: ubicacion = "Platea"; break;
                case 3: ubicacion = "General"; break;
                default:
                    System.out.println("Opción inválida. Se asignará 'General' por defecto.");
                    ubicacion = "General";
            }
            ubicaciones[asiento] = ubicacion;
            System.out.println("Asiento " + asiento + " reservado exitosamente.");
        } else {
            System.out.println("Asiento no disponible o inválido.");
        }
    }

    static void comprarAsiento(Scanner scanner) {
        mostrarDisponibilidad();
        System.out.print("Seleccione un número de asiento para comprar (0 a " + (CAPACIDAD_SALA - 1) + "): ");
        int asiento = leerEntero(scanner);
        if (asiento >= 0 && asiento < CAPACIDAD_SALA && (asientos[asiento] == 0 || asientos[asiento] == 1)) {
            if (asientos[asiento] == 1 && ubicaciones[asiento] != null) {
                System.out.println("Está completando una compra previamente reservada.");
                System.out.println("Ubicación ya asignada: " + ubicaciones[asiento]);
            } else {
                System.out.println("Seleccione la ubicación:");
                System.out.println("1. VIP");
                System.out.println("2. Platea");
                System.out.println("3. General");
                int ubicacionSeleccionada = leerEntero(scanner);
                String ubicacion = "";
                switch (ubicacionSeleccionada) {
                    case 1: ubicacion = "VIP"; break;
                    case 2: ubicacion = "Platea"; break;
                    case 3: ubicacion = "General"; break;
                    default:
                        System.out.println("Opción inválida. Se asignará 'General' por defecto.");
                        ubicacion = "General";
                }
                ubicaciones[asiento] = ubicacion;
            }

            System.out.print("Ingrese su edad: ");
            int edad = leerEntero(scanner);
            System.out.print("¿Es estudiante? (s/n): ");
            boolean estudiante = scanner.next().equalsIgnoreCase("s");

            double descuento = 0;
            if (estudiante) descuento = 0.10;
            else if (edad >= 65) descuento = 0.15;
            double precioFinal = PRECIO_UNITARIO * (1 - descuento);

            asientos[asiento] = 2;
            cantidades[asiento] = 1;
            precios[asiento] = precioFinal;

            totalVendidas++;
            ingresosTotales += precioFinal;

            System.out.println("Compra realizada exitosamente. Total: $" + (int) precioFinal);
        } else {
            System.out.println("Asiento no disponible o inválido.");
        }
    }

    static void modificarVenta(Scanner scanner) {
        System.out.print("Ingrese el número de asiento a modificar: ");
        int asiento = leerEntero(scanner);
        if (asiento >= 0 && asiento < CAPACIDAD_SALA && asientos[asiento] == 2) {
            System.out.println("Modificando venta para asiento " + asiento + ". Precio original: $" + (int) precios[asiento]);
            System.out.print("Ingrese nueva edad del comprador: ");
            int edad = leerEntero(scanner);
            System.out.print("¿Es estudiante? (s/n): ");
            boolean estudiante = scanner.next().equalsIgnoreCase("s");

            double descuento = 0;
            if (estudiante) descuento = 0.10;
            else if (edad >= 65) descuento = 0.15;
            double nuevoPrecio = PRECIO_UNITARIO * (1 - descuento);

            ingresosTotales -= precios[asiento];
            precios[asiento] = nuevoPrecio;
            ingresosTotales += nuevoPrecio;

            System.out.println("Venta modificada. Nuevo total: $" + (int) nuevoPrecio);
        } else {
            System.out.println("No hay una venta registrada en ese asiento para modificar.");
        }
    }

    static void imprimirBoleta(Scanner scanner) {
        System.out.print("Ingrese el número de asiento para imprimir boleta: ");
        int asiento = leerEntero(scanner);
        if (asiento >= 0 && asiento < CAPACIDAD_SALA && asientos[asiento] == 2) {
            System.out.println("\n--- BOLETA ---");
            System.out.println("Teatro: " + NOMBRE_TEATRO);
            System.out.println("Asiento: " + asiento);
            System.out.println("Ubicación: " + (ubicaciones[asiento] != null ? ubicaciones[asiento] : "No definida"));
            System.out.println("Cantidad: " + cantidades[asiento]);
            System.out.println("Total pagado: $" + (int) precios[asiento]);
            System.out.println("------------------\n");
        } else {
            System.out.println("No hay una compra registrada para ese asiento.");
        }
    }

    static void mostrarDisponibilidad() {
        System.out.println("\nAsientos disponibles:");
        for (int i = 0; i < CAPACIDAD_SALA; i++) {
            String estado = asientos[i] == 0 ? "[Libre]" : asientos[i] == 1 ? "[Reservado]" : "[Comprado]";
            System.out.println("Asiento " + i + ": " + estado);
        }
    }

    static int leerEntero(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Entrada inválida. Se tomará como -1.");
            return -1;
        }
    }
}