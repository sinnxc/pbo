import java.util.ArrayList;

public class Log {
    private static ArrayList<String> logTransaksi = new ArrayList<>();

    public static void simpanLog(String jenisKopi, int harga) {
        logTransaksi.add("Transaksi: " + jenisKopi + " - Rp" + harga);
    }

    public static void tampilkanLog() {
        System.out.println("Log Transaksi:");
        if (logTransaksi.isEmpty()) {
            System.out.println("Belum ada transaksi.");
        } else {
            for (String log : logTransaksi) {
                System.out.println(log);
            }
        }
    }
}