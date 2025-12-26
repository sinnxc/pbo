public class Penyewa {
    private String nama;
    private String nomorTelepon;

    public Penyewa(String nama, String nomorTelepon) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
    }

    public void tampilkanInfo() {
        System.out.println("Nama Penyewa: " + nama);
        System.out.println("Nomor Telepon: " + nomorTelepon);
    }
}