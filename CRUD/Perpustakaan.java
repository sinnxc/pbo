import java.sql.*;
import java.util.Scanner;

public class Perpustakaan {

    private final Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public Perpustakaan() {
        // Saat class ini dipanggil, langsung buka koneksi
        conn = Koneksi.getConnection();
    }

    // --- CREATE (Insert Data) ---
    public void insertBuku(String judul, String pengarang) {
        String sql = "INSERT INTO buku (judul, pengarang) VALUES('%s', '%s')";
        try {
            stmt = conn.createStatement();
            stmt.execute(String.format(sql, judul, pengarang));
            System.out.println("Berhasil menyimpan data buku!");
        } catch (SQLException e) {
            System.err.println("Gagal menyimpan data buku!");
            e.printStackTrace();
        }
    }

    // --- READ (Show Data) ---
    public void showData() {
        String sql = "SELECT * FROM buku";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            System.out.println("+--------------------------------+");
            System.out.println("| DATA BUKU DI PERPUSTAKAAN      |");
            System.out.println("+--------------------------------+");

            while (rs.next()) {
                int idBuku = rs.getInt("id_buku");
                String judul = rs.getString("judul");
                String pengarang = rs.getString("pengarang");
                System.out.printf("%d. %s -- (%s)%n", idBuku, judul, pengarang);
            }
        } catch (SQLException e) {
            System.err.println("Gagal menampilkan data buku!");
            e.printStackTrace();
        }
    }

    // --- UPDATE (Edit Data) ---
    public void updateBuku(int idBuku, String judul, String pengarang) {
        String sql = "UPDATE buku SET judul='%s', pengarang='%s' WHERE id_buku=%d";
        try {
            stmt = conn.createStatement();
            stmt.execute(String.format(sql, judul, pengarang, idBuku));
            System.out.println("Data berhasil diubah!");
        } catch (SQLException e) {
            System.err.println("Gagal mengubah data buku!");
            e.printStackTrace();
        }
    }

    // --- DELETE (Hapus Data) ---
    public void deleteBuku(int idBuku) {
        String sql = String.format("DELETE FROM buku WHERE id_buku=%d", idBuku);
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Data telah terhapus...");
        } catch (SQLException e) {
            System.err.println("Gagal menghapus data buku!");
            e.printStackTrace();
        }
    }
}