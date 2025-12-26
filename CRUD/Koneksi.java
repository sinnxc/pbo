import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Koneksi {
    // Konfigurasi koneksi database
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // Driver baru biasanya pakai .cj.
    private static final String DB_URL = "jdbc:mysql://localhost/perpustakaan";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Register Driver
            Class.forName(JDBC_DRIVER);
            // Buat Koneksi
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            System.err.println("Gagal koneksi database!");
            e.printStackTrace();
        }
        return conn;
    }
}