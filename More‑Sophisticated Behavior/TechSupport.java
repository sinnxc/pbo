import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class TechSupport {
    private Map<String, String> responses;
    private Random random;

    public TechSupport() {
        responses = new HashMap<>();
        random = new Random();

        // Daftar kata kunci dan respons
        responses.put("bantu", "Apa yang bisa saya bantu hari ini?");
        responses.put("siakad", "Apakah Anda mengalami masalah dengan SIAKAD? Pastikan server tidak sedang maintenance.");
        responses.put("daftar", "Coba periksa apakah kelas sudah penuh atau prasyarat belum terpenuhi.");
        responses.put("ipk", "IPK dapat dilihat di menu Laporan > Transkrip.");
        responses.put("penuh", "Jika kelas penuh, silakan pilih kelas lain yang masih tersedia.");
        responses.put("prasyarat", "Anda mungkin belum memenuhi prasyarat. Periksa di menu Laporan > Prasyarat Matakuliah.");
        responses.put("loading", "Periksa koneksi internet Anda atau coba muat ulang halaman.");
        responses.put("login", "Apakah Anda sudah mengaktifkan authenticator untuk login?");
        responses.put("authenticator", "Unduh aplikasi Microsoft Authenticator, lalu ikuti langkah login di SIAKAD.");
        responses.put("kode", "Kode autentikasi muncul di aplikasi Authenticator Anda.");
        responses.put("presensi", "Presensi bisa diakses melalui portal myITSPresensi.");
        responses.put("izin", "Isi keterangan izin atau sakit dengan bukti valid, dan ikuti arahan dosen bila ada.");
        responses.put("sakit", "Kirim surat dokter melalui sistem izin, agar kehadiran Anda tetap tercatat.");
        responses.put("nilai", "Nilai akhir bisa dilihat di menu Laporan > KHS.");
        responses.put("krs", "Pengisian KRS bisa dilakukan di SIAKAD selama masa pengisian KRS dibuka.");
    }

    public String getResponse(String userInput) {
        if (userInput == null || userInput.isBlank()) {
            return "Coba tulis pertanyaan yang lebih jelas.";
        }

        String[] words = userInput.toLowerCase().split("\\s+");
        for (String w : words) {
            if (responses.containsKey(w)) {
                return responses.get(w);
            }
        }

        // Jika tidak ada kata kunci yang cocok
        String[] generic = {
            "Bisa jelaskan lebih detail?",
            "Saya belum mengerti, bisa diulangi?",
            "Coba tambahkan informasi lebih spesifik."
        };
        return generic[random.nextInt(generic.length)];
    }

    private static void printWelcome() {
        System.out.println("========================================");
        System.out.println("   Selamat datang di Academic Support");
        System.out.println("   Ketik 'bye' untuk mengakhiri percakapan");
        System.out.println("========================================");
    }

    private static void printGoodbye() {
        System.out.println("Terima kasih telah menggunakan Academic Support.");
        System.out.println("Semoga masalah Anda segera terselesaikan.");
        System.out.println("Sampai jumpa!");
    }

    public static void main(String[] args) {
        TechSupport as = new TechSupport();
        Scanner scanner = new Scanner(System.in);
        printWelcome();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                break;
            }

            String response = as.getResponse(input);
            System.out.println(response);
        }

        printGoodbye();
        scanner.close();
    }
}