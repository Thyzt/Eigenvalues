import java.util.Scanner;

public class findit {

    private final static void clearConsole() {

        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            // Handle any exceptions.
            System.out.println("Error clearing the console.");
        }
    }

    private final static void printLogo() {

        System.out.println();
        System.out.println("            ___           ___           ___           ___           ___");
        System.out.println("           /\\  \\         /\\  \\         /\\__\\         /\\  \\         /\\  \\");
        System.out.println("          /::\\  \\       /::\\  \\       /:/  /        /::\\  \\       /::\\  \\");
        System.out.println("         /:/\\:\\  \\     /:/\\:\\  \\     /:/  /        /:/\\ \\  \\     /:/\\ \\  \\");
        System.out.println("        /:/  \\:\\  \\   /::\\~\\:\\  \\   /:/  /  ___   _\\:\\~\\ \\  \\   _\\:\\~\\ \\  \\");
        System.out.println("       /:/__/_\\:\\__\\ /:/\\:\\ \\:\\__\\ /:/__/  /\\__\\ /\\ \\:\\ \\ \\__\\ /\\ \\:\\ \\ \\__\\");
        System.out.println("       \\:\\  /\\ \\/__/ \\/__\\:\\/:/  / \\:\\  \\ /:/  / \\:\\ \\:\\ \\/__/ \\:\\ \\:\\ \\/__/");
        System.out.println("        \\:\\ \\:\\__\\        \\::/  /   \\:\\  /:/  /   \\:\\ \\:\\__\\    \\:\\ \\:\\__\\");
        System.out.println("         \\:\\/:/  /        /:/  /     \\:\\/:/  /     \\:\\/:/  /     \\:\\/:/  /");
        System.out.println("          \\::/  /        /:/  /       \\::/  /       \\::/  /       \\::/  /");
        System.out.println("           \\/__/         \\/__/         \\/__/         \\/__/         \\/__/");
        System.out.println("             ___         ___           ___           ___           ___           ___");
        System.out.println("            /\\  \\       /\\  \\         /\\  \\         /\\  \\         /\\  \\         /\\__\\");
        System.out.println("            \\:\\  \\     /::\\  \\       /::\\  \\       /::\\  \\       /::\\  \\       /::|  |");
        System.out.println("        ___ /::\\__\\   /:/\\:\\  \\     /:/\\:\\  \\     /:/\\:\\  \\     /:/\\:\\  \\     /:|:|  |");
        System.out.println("       /\\  /:/\\/__/  /:/  \\:\\  \\   /::\\~\\:\\  \\   /:/  \\:\\__\\   /::\\~\\:\\  \\   /:/|:|  |__");
        System.out.println("       \\:\\/:/  /    /:/__/ \\:\\__\\ /:/\\:\\ \\:\\__\\ /:/__/ \\:|__| /:/\\:\\ \\:\\__\\ /:/ |:| /\\__\\");
        System.out.println("        \\::/  /     \\:\\  \\ /:/  / \\/_|::\\/:/  / \\:\\  \\ /:/  / \\/__\\:\\/:/  / \\/__|:|/:/  /");
        System.out.println("         \\/__/       \\:\\  /:/  /     |:|::/  /   \\:\\  /:/  /       \\::/  /      |:/:/  /");
        System.out.println("                      \\:\\/:/  /      |:|\\/__/     \\:\\/:/  /        /:/  /       |::/  /");
        System.out.println("    By: Musang         \\::/  /       |:|  |        \\::/__/        /:/  /        /:/  /");
        System.out.println("        Jon Fahleraz    \\/__/         \\|__|         ~~            \\/__/         \\/__/");
        System.out.println("        Hunnie");       
        System.out.println();
    }

    public static void main(String[] args) {

        // Variable initialization
        Scanner scan = new Scanner(System.in);
        boolean endMenu = false;
        boolean endCase;

        while (!endMenu) {

            clearConsole();

            printLogo();

            System.out.println("||=============================== WELCOME TO GAUSS-JORDAN ================================||");
            System.out.println();
            System.out.println(">> Choose a menu option:");
            System.out.println("1. Regular Matrix");
            System.out.println("2. Hilbert Matrix");
            System.out.println("3. Interpolation");
            System.out.println("4. Function e");
            System.out.println("5. Exit");
            System.out.print(">> ");

            String menuOption = scan.nextLine();
            switch (menuOption) {
                case "1":
                    // Regular Matrix menu implementation here.
                    break;

                case "2":
                    // Hilbert Matrix menu implementation here.
                    break;

                case "3":
                    // Interpolation menu implementation here.
                    break;

                case "4":
                    // Function e menu implementation here.
                    break;

                case "5":
                    endMenu = true;
                    break;

                default:
                    System.out.println();
                    System.out.println("Invalid input, please try again.");
                    break;
            }
        }
    }
}
