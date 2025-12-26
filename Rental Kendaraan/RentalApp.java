public class RentalApp {
    public static void main(String[] args) {
        Kendaraan mobil = new Mobil("Toyota", "Avanza", 2022, 300000, 4);
        Kendaraan motor = new Motor("Honda", "Vario 150", 2023, 100000, "Otomatis");
        Kendaraan sepeda = new Sepeda("Polygon", "Xtrada 7", 2021, 50000, "Gunung");

        Penyewa penyewa1 = new Penyewa("Dhoni", "081234567890");

        mobil.tampilkanInfo();
        System.out.println();
        motor.tampilkanInfo();
        System.out.println();
        sepeda.tampilkanInfo();
        System.out.println();

        System.out.println("=========================================");
        System.out.println("PROSES PENYEWAAN");
        System.out.println("=========================================");
        penyewa1.tampilkanInfo();
        
        mobil.sewa();
        System.out.println();
        
        mobil.tampilkanInfo();
        System.out.println();

        mobil.sewa();
        System.out.println();
        
        System.out.println("=========================================");
        System.out.println("PROSES PENGEMBALIAN");
        System.out.println("=========================================");
        mobil.kembalikan();
        System.out.println();
        
        mobil.tampilkanInfo();
        System.out.println();
    }
}