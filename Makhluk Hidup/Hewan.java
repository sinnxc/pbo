public class Hewan extends MakhlukHidup {
    @Override
    public void makan() {
        System.out.println("Hewan makan daging (karnivora) atau tumbuhan (herbivora).");
    }
    
    @Override
    public void bergerak() {
        System.out.println("Hewan bergerak dengan berjalan, terbang, atau berenang.");
    }
    
    public void bersuara() {
        System.out.println("Hewan ini mengeluarkan suara...");
    }
}