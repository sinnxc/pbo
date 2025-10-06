import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Dosen pakIlham = new Dosen("Pak Ilham Gurat A", "00004");
        Dosen pakIrfan = new Dosen("Pak Irfan Subakti", "00003");
        Dosen buLicha = new Dosen("Bu Adhatus Solicha", "00001");
        Dosen pakFB = new Dosen("Pak Fajar Baskoro", "00002");

        ArrayList<MataKuliah> daftarMK = new ArrayList<>();
        daftarMK.add(new MataKuliah("Matematika Diskrit (A)", 3, pakIlham));
        daftarMK.add(new MataKuliah("Pemrograman Berorientasi Obyek (A)", 3, pakFB));
        daftarMK.add(new MataKuliah("Pemrograman Web (D)", 3, pakIrfan));
        daftarMK.add(new MataKuliah("Sistem Basis Data (C)", 3, buLicha));

        System.out.println("=== PENGATURAN MAHASISWA ===");
        System.out.print("Masukkan nama mahasiswa: ");
        String namaMhs = sc.nextLine();
        System.out.print("Masukkan NRP mahasiswa: ");
        String nimMhs = sc.nextLine();
        Mahasiswa mhs = new Mahasiswa(namaMhs, nimMhs);
        System.out.println("Selamat datang, " + mhs.nama + "!");

        int pilihan;
        do {
            System.out.println("\n=== SISTEM AKADEMIK FRS ===");
            System.out.println("1. Info Mata Kuliah");
            System.out.println("2. Ambil Mata Kuliah");
            System.out.println("3. Drop Mata Kuliah");
            System.out.println("4. Lihat FRS");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            try {
                pilihan = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                pilihan = -1;
            }

            switch (pilihan) {
                case 1:
                    System.out.println("\n--- DAFTAR MATA KULIAH ---");
                    for (int i = 0; i < daftarMK.size(); i++) {
                        System.out.println((i + 1) + ". " + daftarMK.get(i));
                    }
                    break;

                case 2:
                    System.out.println("\n--- AMBIL MATA KULIAH ---");
                    for (int i = 0; i < daftarMK.size(); i++) {
                        System.out.println((i + 1) + ". " + daftarMK.get(i));
                    }
                    System.out.print("Pilih nomor: ");
                    try {
                        int pilih = Integer.parseInt(sc.nextLine());
                        if (pilih > 0 && pilih <= daftarMK.size()) {
                            mhs.ambilMatkul(daftarMK.get(pilih - 1));
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input harus angka.");
                    }
                    break;

                case 3:
                    if (mhs.matkulDiambil.isEmpty()) {
                        System.out.println("Belum ada mata kuliah yang diambil.");
                        break;
                    }
                    System.out.println("\n--- DROP MATA KULIAH ---");
                    for (int i = 0; i < mhs.matkulDiambil.size(); i++) {
                        System.out.println((i + 1) + ". " + mhs.matkulDiambil.get(i).namaMK);
                    }
                    System.out.print("Pilih nomor: ");
                    try {
                        int drop = Integer.parseInt(sc.nextLine());
                        if (drop > 0 && drop <= mhs.matkulDiambil.size()) {
                            mhs.dropMatkul(mhs.matkulDiambil.get(drop - 1).namaMK);
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input harus angka.");
                    }
                    break;

                case 4:
                    mhs.tampilkanFRS();
                    break;

                case 0:
                    System.out.println("Terima kasih. Keluar dari sistem FRS.");
                    break;

                default:
                    System.out.println("Menu tidak dikenal.");
            }
        } while (pilihan != 0);

        sc.close();
    }
}