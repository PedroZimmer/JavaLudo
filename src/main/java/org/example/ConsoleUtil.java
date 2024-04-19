package org.example;

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

}
