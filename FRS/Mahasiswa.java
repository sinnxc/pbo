import java.util.ArrayList;

public class Mahasiswa {
    public String nama;
    public String nim;
    public ArrayList<MataKuliah> matkulDiambil;
    private final int SKS_MAX = 24;

    public Mahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
        this.matkulDiambil = new ArrayList<>();
    }

    public void ambilMatkul(MataKuliah mk) {
        if (matkulDiambil.contains(mk)) {
            System.out.println("Gagal: Mata kuliah " + mk.namaMK + " sudah diambil.");
            return;
        }
        if (mk.kuota <= 0) {
            System.out.println("Gagal: Kuota " + mk.namaMK + " sudah habis.");
            return;
        }
        if (hitungTotalSKS() + mk.sks > SKS_MAX) {
            System.out.println("Gagal: Melebihi batas maksimum SKS (" + SKS_MAX + ").");
            return;
        }
        matkulDiambil.add(mk);
        mk.kurangiKuota();
        System.out.println("Berhasil mengambil: " + mk.namaMK + ". Total SKS: " + hitungTotalSKS());
    }

    public void dropMatkul(String namaMK) {
        MataKuliah mkToRemove = null;
        for (MataKuliah mk : matkulDiambil) {
            if (mk.namaMK.equalsIgnoreCase(namaMK)) {
                mkToRemove = mk;
                break;
            }
        }
        if (mkToRemove != null) {
            matkulDiambil.remove(mkToRemove);
            mkToRemove.tambahKuota();
            System.out.println("Berhasil drop: " + mkToRemove.namaMK);
        } else {
            System.out.println("Gagal: Mata kuliah tidak ditemukan.");
        }
    }

    private int hitungTotalSKS() {
        int total = 0;
        for (MataKuliah mk : matkulDiambil) {
            total += mk.sks;
        }
        return total;
    }

    public void tampilkanFRS() {
        System.out.println("\n==================================");
        System.out.println("FRS Mahasiswa: " + nama + " (" + nim + ")");
        System.out.println("==================================");
        if (matkulDiambil.isEmpty()) {
            System.out.println("Belum ada mata kuliah yang diambil.");
        } else {
            int no = 1;
            for (MataKuliah mk : matkulDiambil) {
                System.out.println(no++ + ". " + mk);
            }
            System.out.println("----------------------------------");
            System.out.println("Total SKS Diambil: " + hitungTotalSKS());
        }
        System.out.println("==================================");
    }
}