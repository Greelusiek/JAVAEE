package com.mycompany.lab5;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;

import java.util.Scanner;

public class Lab5 {

    private static final String BASE_URL = "http://localhost:8080/Lab5-JavaEE-Web/resources";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Wybierz opcje:");
            System.out.println("1 - add");
            System.out.println("2 - sub");
            System.out.println("3 - mul");
            System.out.println("4 - div");
            System.out.println("5 - switch");
            System.out.println("6 - exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Konsumuje znak końca linii

            switch (choice) {
                case 1 -> processOperation(scanner, "add");
                case 2 -> processOperation(scanner, "sub");
                case 3 -> processOperation(scanner, "mul");
                case 4 -> processOperation(scanner, "div");
                case 5 -> processSwitch(scanner);
                case 6 -> {
                    System.out.println("Zakonczono dzialanie programu.");
                    running = false;
                }
                default -> System.out.println("Nieprawidlowa opcja, sprobuj ponownie.");
            }
        }
    }

    private static void processOperation(Scanner scanner, String operation) {
        System.out.println("Wprowadz wartość dla x:");
        String x = scanner.nextLine();

        System.out.println("Wprowadz wartość dla y:");
        String y = scanner.nextLine();

        String response = sendGetRequest(String.format("/%s", operation), new String[][]{
                {"x", x},
                {"y", y}
        });
        System.out.println("Odpowiedz serwera: " + response);
    }

    private static void processSwitch(Scanner scanner) {
        System.out.println("Wprowadz wartość dla op1:");
        String op1 = scanner.nextLine();

        System.out.println("Wprowadz wartość dla op2:");
        String op2 = scanner.nextLine();

        String response = sendGetRequest(String.format("/switch/%s/%s", op1, op2), null);
        System.out.println("Odpowiedz serwera: " + response);
    }

    private static String sendGetRequest(String path, String[][] queryParams) {
    Client client = ClientBuilder.newClient();
    WebTarget base = client.target(BASE_URL);

    WebTarget target = base.path(path);
    if (queryParams != null) {
        for (String[] param : queryParams) {
            target = target.queryParam(param[0], param[1]);
        }
    }

    try {
        return target.request().get(String.class);
    } catch (jakarta.ws.rs.ClientErrorException e) {
        int status = e.getResponse().getStatus();
        String errorMessage = e.getResponse().readEntity(String.class);
        return "Error " + status + ": " + errorMessage;
    } catch (Exception e) {
        return "Error: Unexpected failure";
    }
}


    public static void formPostExample(Scanner scanner) {
        System.out.println("Wprowadz wartość dla key1:");
        String key1 = scanner.nextLine();
        System.out.println("Wprowadz wartość dla key2:");
        String key2 = scanner.nextLine();
        System.out.println("Wprowadz wartość dla key3:");
        String key3 = scanner.nextLine();

        MultivaluedMap<String, String> formData = new MultivaluedHashMap<>();
        formData.add("key1", key1);
        formData.add("key2", key2);
        formData.add("key3", key3);

        Client client = ClientBuilder.newClient();
        WebTarget base = client.target(BASE_URL);
        WebTarget resource = base.path("/formResource");

        resource.request().post(Entity.form(formData));
        System.out.println("Zadanie POST zostalo wyslane.");
    }
}
