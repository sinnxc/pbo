import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Perpustakaan perpus = new Perpustakaan();
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            printMenu();
            int pilihan = getUserChoice(input);

            switch (pilihan) {
                case 1 -> handleInsert(input, perpus);
                case 2 -> perpus.showData();
                case 3 -> handleUpdate(input, perpus);
                case 4 -> handleDelete(input, perpus);
                case 0 -> {
                    System.out.println("Keluar program.");
                    isRunning = false;
                }
                default -> System.out.println("Pilihan salah!");
            }
        }
        
        input.close();
    }

    private static void printMenu() {
        System.out.println("\n========= MENU UTAMA =========");
        System.out.println("1. Insert Data");
        System.out.println("2. Show Data");
        System.out.println("3. Edit Data");
        System.out.println("4. Delete Data");
        System.out.println("0. Keluar");
        System.out.print("PILIHAN> ");
    }

    private static int getUserChoice(Scanner input) {
        try {
            return Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input harus angka!");
            return -1;
        }
    }

    private static void handleInsert(Scanner input, Perpustakaan perpus) {
        System.out.print("Judul: ");
        String judul = input.nextLine();
        System.out.print("Pengarang: ");
        String pengarang = input.nextLine();
        perpus.insertBuku(judul, pengarang);
    }

    private static void handleUpdate(Scanner input, Perpustakaan perpus) {
        try {
            System.out.print("ID yang mau diedit: ");
            int idEdit = Integer.parseInt(input.nextLine());
            System.out.print("Judul Baru: ");
            String judulBaru = input.nextLine();
            System.out.print("Pengarang Baru: ");
            String pengarangBaru = input.nextLine();
            perpus.updateBuku(idEdit, judulBaru, pengarangBaru);
        } catch (NumberFormatException e) {
            System.out.println("ID harus berupa angka!");
        }
    }

    private static void handleDelete(Scanner input, Perpustakaan perpus) {
        try {
            System.out.print("ID yang mau dihapus: ");
            int idHapus = Integer.parseInt(input.nextLine());
            perpus.deleteBuku(idHapus);
        } catch (NumberFormatException e) {
            System.out.println("ID harus berupa angka!");
        }
    }
}