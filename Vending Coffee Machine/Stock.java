public class Stock {
    public static int air = 1000;
    public static int susu = 1000;
    public static int bijiKopi = 500;
    public static int gula = 500;
    public static int gelas = 10;
    public static int saldo = 0;

    public static void tampilkanSisa() {
        System.out.println("Mesin kopi memiliki:\n" +
                air + " ml air\n" +
                susu + " ml susu\n" +
                bijiKopi + " gram biji kopi\n" +
                gula + " gram gula\n" +
                gelas + " gelas\n" +
                "Saldo: Rp" + saldo);
    }

    public static void ingatkanAdmin(String stokHabis) {
        System.out.println("Peringatan: Stok " + stokHabis + " habis! Harap segera diisi ulang.");
    }
}