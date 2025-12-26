public class Kendaraan {
    protected String merk;
    protected String model;
    protected int tahunProduksi;
    protected double hargaSewaPerHari;
    protected boolean sedangDisewa;

    public Kendaraan(String merk, String model, int tahunProduksi, double hargaSewaPerHari) {
        this.merk = merk;
        this.model = model;
        this.tahunProduksi = tahunProduksi;
        this.hargaSewaPerHari = hargaSewaPerHari;
        this.sedangDisewa = false;
    }

    public void tampilkanInfo() {
        System.out.println("Merk: " + merk);
        System.out.println("Model: " + model);
        System.out.println("Tahun Produksi: " + tahunProduksi);
        System.out.println("Harga Sewa per Hari: Rp" + hargaSewaPerHari);
        System.out.println("Status: " + (sedangDisewa ? "Disewa" : "Tersedia"));
    }

    public void sewa() {
        if (!sedangDisewa) {
            this.sedangDisewa = true;
            System.out.println(merk + " " + model + " berhasil disewa.");
        } else {
            System.out.println("Maaf, " + merk + " " + model + " sedang tidak tersedia.");
        }
    }

    public void kembalikan() {
        if (sedangDisewa) {
            this.sedangDisewa = false;
            System.out.println(merk + " " + model + " telah dikembalikan.");
        } else {
            System.out.println("Kendaraan ini tidak sedang dalam status disewa.");
        }
    }
}