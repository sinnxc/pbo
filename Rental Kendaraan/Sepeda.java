public class Sepeda extends Kendaraan {
    private String jenisSepeda;

    public Sepeda(String merk, String model, int tahunProduksi, double hargaSewaPerHari, String jenisSepeda) {
        super(merk, model, tahunProduksi, hargaSewaPerHari);
        this.jenisSepeda = jenisSepeda;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("--- Informasi Sepeda ---");
        super.tampilkanInfo();
        System.out.println("Jenis Sepeda: " + jenisSepeda);
    }
}