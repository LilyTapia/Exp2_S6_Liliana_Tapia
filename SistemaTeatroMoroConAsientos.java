package sistemateatromoroconasientos;

import java.util.Scanner;

public class SistemaTeatroMoroConAsientos {
    // Constantes para los porcentajes de descuento

    private static final double DESCUENTO_ESTUDIANTE = 0.10; // 10%
    private static final double DESCUENTO_ADULTO_MAYOR = 0.15; // 15%
    private static int totalVentas = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        int totalEntradasVendidas = 0;
        int totalEntradasDisponibles = 100;

        // Asientos para cada ubicación
        int asientosVIP = 10;
        int asientosPlateaBaja = 20;
        int asientosPlateaAlta = 30;
        int asientosPalcos = 40;

        // Entrada del sistema
        System.out.println("## Bienvenido al sistema de ventas del Teatro Moro ##");
        System.out.println();

        // Inicio del sistema
        System.out.println("- Para comprar entradas, presione: 1");
        System.out.println("- Para salir presione cualquier numero.");
        System.out.println();

        if (scanner.nextInt() == 1) {
            do {
                // Mostrar opciones disponibles
                mostrarOpciones(asientosVIP, asientosPlateaBaja, asientosPlateaAlta, asientosPalcos);

                // Solicitar ubicación hasta que sea válida
                int ubicacion;
                do {
                    ubicacion = scanner.nextInt();
                } while (ubicacion < 1 || ubicacion > 4);

                // Solicitar cantidad de entradas hasta que sea válida
                int cantidadEntradas;
                switch (ubicacion) {
                    case 1 -> {
                        cantidadEntradas = solicitarCantidadEntradas(scanner, "VIP", asientosVIP);
                        totalEntradasVendidas += cantidadEntradas;
                        calcularTotal(cantidadEntradas, 25000, scanner);
                        asientosVIP -= cantidadEntradas;
                    }
                    case 2 -> {
                        cantidadEntradas = solicitarCantidadEntradas(scanner, "Platea Baja", asientosPlateaBaja);
                        totalEntradasVendidas += cantidadEntradas;
                        calcularTotal(cantidadEntradas, 19000, scanner);
                        asientosPlateaBaja -= cantidadEntradas;
                    }
                    case 3 -> {
                        cantidadEntradas = solicitarCantidadEntradas(scanner, "Platea Alta", asientosPlateaAlta);
                        totalEntradasVendidas += cantidadEntradas;
                        calcularTotal(cantidadEntradas, 11000, scanner);
                        asientosPlateaAlta -= cantidadEntradas;
                    }
                    case 4 -> {
                        cantidadEntradas = solicitarCantidadEntradas(scanner, "Palcos", asientosPalcos);
                        totalEntradasVendidas += cantidadEntradas;
                        calcularTotal(cantidadEntradas, 7200, scanner);
                        asientosPalcos -= cantidadEntradas;
                    }

                }

                // Consulta para volver a comprar o salir
                System.out.println("Desea hacer otra compra?");
                System.out.println("1: Si");
                System.out.println("cualquier otro numero: No");
                opcion = scanner.nextInt();
            } while (opcion == 1);

            // Salida en caso de que el usuario envíe la opción 2 para salir
            System.out.println("    ");
            System.out.println("-----------------------------------");
            System.out.println("Total de entradas vendidas: " + totalEntradasVendidas);
            System.out.println("Total de entradas disponibles: " + (totalEntradasDisponibles - totalEntradasVendidas));
            System.out.println("-----------------------------------");
            System.out.println("    ");
            System.out.println("Gracias por su compra!!");
            System.out.println("    ");

            // Cierre Scanner al final del programa
            scanner.close();
        } else {
            System.out.println("Saliendo del sistema.");
        }
    }

    // Método para mostrar las opciones disponibles
    private static void mostrarOpciones(int asientosVIP, int asientosPlateaBaja, int asientosPlateaAlta, int asientosPalcos) {
        System.out.println("Indique la ubicacion que desea:");
        System.out.println("1: VIP - precio: $25.000 - Asientos disponibles: " + asientosVIP);
        System.out.println("2: Platea Baja - precio: $19.000 - Asientos disponibles: " + asientosPlateaBaja);
        System.out.println("3: Platea Alta - precio: $11.000 - Asientos disponibles: " + asientosPlateaAlta);
        System.out.println("4: Palcos - precio: $7.200 - Asientos disponibles: " + asientosPalcos);
    }
     
    // Método para solicitar la cantidad de entradas hasta que sea válida
    private static int solicitarCantidadEntradas(Scanner scanner, String ubicacion, int asientosDisponibles) {
        int cantidad;
        do {
            System.out.println("Ha elegido la ubicacion: " + ubicacion);
            System.out.println("cuantas entradas desea comprar?");
            cantidad = scanner.nextInt();
            if (cantidad <= 0 || cantidad > asientosDisponibles) {
                System.out.println("Cantidad de entradas invalida. Intente nuevamente.");
            }
        } while (cantidad <= 0 || cantidad > asientosDisponibles);
        return cantidad;
    }

    // Método para calcular el total a pagar, considerando descuentos si corresponde
    private static int calcularTotal(int cantidad, int precioUnitario, Scanner scanner) {
        System.out.println("Indique su edad:");
        int edad = scanner.nextInt();
        int descuento;
        if (edad <= 17) {
            System.out.println("Usted posee un descuento del 10% por cada entrada.");
            descuento = (int) (precioUnitario * (1 - DESCUENTO_ESTUDIANTE));
        } else if (edad >= 60) {
            System.out.println("Usted posee un descuento del 15% por cada entrada.");
            descuento = (int) (precioUnitario * (1 - DESCUENTO_ADULTO_MAYOR));
        } else {
            descuento = precioUnitario;
        }
        int total = cantidad * descuento;
        // Incrementa el total acumulado de ventas
        totalVentas += total;

        System.out.println("    ");
        System.out.println("========== BOLETA ==========");
        System.out.println("    ");
        System.out.println("- Cantidad de entradas: " + cantidad);
        System.out.println("- Precio de entrada unitario: $" + precioUnitario);
        System.out.println("- Precio de entrada con descuento aplicado: $" + descuento);
        System.out.println("- El total de su compra: $" + totalVentas);
        System.out.println("    ");
        System.out.println("===========================");
        System.out.println("    ");
        return total;
    }
}
