package com.practica;

import java.util.Scanner;

public class Menu {
    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        MongoDBConnectionManager.LlibreDAO llibreDAO = new MongoDBConnectionManager.LlibreDAO("llibres");

        while (true) {
            System.out.println("1. Afegir llibre");
            System.out.println("2. Mostrar llibres");
            System.out.println("3. Cercar per ID");
            System.out.println("4. Actualitzar llibre");
            System.out.println("5. Eliminar llibre");
            System.out.println("6. Sortir");
            System.out.print("Opció: ");
            int opcio = scanner.nextInt(); scanner.nextLine();

            switch (opcio) {
                case 1 -> {
                    System.out.print("Títol: ");
                    String titol = scanner.nextLine();
                    Llibre nouLlibre = new Llibre();
                    nouLlibre.setTitol(titol);
                    llibreDAO.create(nouLlibre);
                    System.out.println("Llibre afegit!");
                }
                case 2 -> {
                    for (Llibre llibre : llibreDAO.findAll()) {
                        System.out.println(llibre.getTitol());
                    }
                }
                case 3 -> {
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    Llibre llibre = llibreDAO.findById(id);
                    if (llibre != null) System.out.println(llibre.getTitol());
                    else System.out.println("No trobat!");
                }
                case 4 -> {
                    System.out.print("ID: ");
                    String idUpdate = scanner.nextLine();
                    System.out.print("Nou títol: ");
                    String nouTitol = scanner.nextLine();
                    Llibre llibreUpdate = new Llibre();
                    llibreUpdate.setTitol(nouTitol);
                    llibreDAO.update(idUpdate, llibreUpdate);
                    System.out.println("Llibre actualitzat!");
                }
                case 5 -> {
                    System.out.print("ID: ");
                    String idDelete = scanner.nextLine();
                    llibreDAO.delete(idDelete);
                    System.out.println("Llibre eliminat!");
                }
                case 6 -> {
                    System.out.println("Sortint...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opció no vàlida");
            }
        }
    }
}