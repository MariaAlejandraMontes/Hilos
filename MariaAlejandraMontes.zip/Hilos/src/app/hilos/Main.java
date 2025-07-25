// Participante: Maria Alejandra Montes
package app.hilos;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Validar número para conteo regresivo
        int numero;
        while (true) {
            System.out.print("Ingrese un número entero positivo para el conteo regresivo: ");
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                if (numero > 0) break;
            } else {
                scanner.next(); // Limpiar entrada inválida
            }
            System.out.println("Entrada inválida. Intente nuevamente.");
        }

        // Validar letra para el alfabeto
        char letraFinal;
        while (true) {
            System.out.print("Ingrese una letra (A-Z) para detener el alfabeto: ");
            String entrada = scanner.next().toUpperCase();
            if (entrada.matches("[A-Z]")) {
                letraFinal = entrada.charAt(0);
                break;
            }
            System.out.println("Entrada inválida. Intente nuevamente.");
        }

        // Crear e iniciar hilos
        final int numeroFinal = numero;
        Thread hiloConteo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = numeroFinal; i >= 0; i--) {
                        System.out.println("[" + Thread.currentThread().getName() + "] " + i);
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Trabajo del hilo " + Thread.currentThread().getName() + " terminado");
            }
        }, "Conteo");

        Thread hiloAlfabeto = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (char c = 'A'; c <= letraFinal; c++) {
                        System.out.println("[" + Thread.currentThread().getName() + "] " + c);
                        Thread.sleep(600);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Trabajo del hilo " + Thread.currentThread().getName() + " terminado");
            }
        }, "Alfabeto");

        hiloConteo.start();
        hiloAlfabeto.start();
    }
}
