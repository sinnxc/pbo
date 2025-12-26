public class Mobil extends Kendaraan {
    private int jumlahPintu;

    public Mobil(String merk, String model, int tahunProduksi, double hargaSewaPerHari, int jumlahPintu) {
        super(merk, model, tahunProduksi, hargaSewaPerHari);
        this.jumlahPintu = jumlahPintu;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("--- Informasi Mobil ---");
        super.tampilkanInfo();
        System.out.println("Jumlah Pintu: " + jumlahPintu);
    }
}