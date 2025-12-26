public class Motor extends Kendaraan {
    private String jenisTransmisi;

    public Motor(String merk, String model, int tahunProduksi, double hargaSewaPerHari, String jenisTransmisi) {
        super(merk, model, tahunProduksi, hargaSewaPerHari);
        this.jenisTransmisi = jenisTransmisi;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("--- Informasi Motor ---");
        super.tampilkanInfo();
        System.out.println("Jenis Transmisi: " + jenisTransmisi);
    }
}