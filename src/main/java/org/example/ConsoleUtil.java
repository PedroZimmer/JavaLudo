package org.example;

import java.util.Scanner;

public class ConsoleUtil {

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Trate qualquer exceção que possa ocorrer ao limpar o console
            e.printStackTrace();
        }
    }

    public static void printHeader() {
        System.out.println(
                "██╗      ██╗   ██╗██████╗  ██████╗ \n" +
                "██║      ██║   ██║██╔══██╗██╔═══██╗\n" +
                "██║      ██║   ██║██║  ██║██║   ██║\n" +
                "██║      ██║   ██║██║  ██║██║   ██║\n" +
                "███████╗ ╚██████╔╝██████╔╝╚██████╔╝\n" +
                "╚══════╝  ╚═════╝ ╚═════╝  ╚═════╝ \n"
        );
    }

    public static String getResposta() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int getInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }





}
