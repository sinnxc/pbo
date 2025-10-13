import java.util.ArrayList;
import java.util.Scanner;

public class LibraryCatalog {
    private static ArrayList<Book> catalog = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            tampilkanMenu();
            choice = bacaAngka("Pilih menu: ");
            switch (choice) {
                case 1:
                    tambahBuku();
                    break;
                case 2:
                    tampilkanSemuaBuku();
                    break;
                case 3:
                    cariBuku();
                    break;
                case 4:
                    hapusBuku();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan katalog.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 5);
        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n=== Katalog Perpustakaan ===");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Lihat Semua Buku");
        System.out.println("3. Cari Buku");
        System.out.println("4. Hapus Buku");
        System.out.println("5. Keluar");
    }

    private static void tambahBuku() {
        System.out.print("Masukkan judul: ");
        String title = scanner.nextLine();
        System.out.print("Masukkan penulis: ");
        String author = scanner.nextLine();
        int year = bacaAngka("Masukkan tahun terbit: ");
        catalog.add(new Book(title, author, year));
        System.out.println("Buku berhasil ditambahkan.");
    }

    private static void tampilkanSemuaBuku() {
        if (catalog.isEmpty()) {
            System.out.println("Belum ada buku di katalog.");
            return;
        }
        System.out.println("\nDaftar Buku:");
        for (int i = 0; i < catalog.size(); i++) {
            System.out.println((i + 1) + ". " + catalog.get(i));
        }
    }

    private static void cariBuku() {
        if (catalog.isEmpty()) {
            System.out.println("Katalog kosong.");
            return;
        }
        System.out.print("Masukkan judul yang dicari: ");
        String keyword = scanner.nextLine().toLowerCase();
        boolean ditemukan = false;
        for (Book b : catalog) {
            if (b.getTitle().toLowerCase().contains(keyword)) {
                System.out.println(b);
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Buku tidak ditemukan.");
        }
    }

    private static void hapusBuku() {
        if (catalog.isEmpty()) {
            System.out.println("Katalog kosong.");
            return;
        }
        tampilkanSemuaBuku();
        int index = bacaAngka("Masukkan nomor buku yang akan dihapus: ");
        if (index >= 1 && index <= catalog.size()) {
            catalog.remove(index - 1);
            System.out.println("Buku berhasil dihapus.");
        } else {
            System.out.println("Nomor tidak valid.");
        }
    }

    private static int bacaAngka(String pesan) {
        while (true) {
            System.out.print(pesan);
            if (scanner.hasNextInt()) {
                int nilai = scanner.nextInt();
                scanner.nextLine();
                return nilai;
            } else {
                System.out.println("Masukkan angka yang valid.");
                scanner.nextLine();
            }
        }
    }
}