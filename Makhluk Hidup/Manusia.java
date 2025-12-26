public class Manusia extends MakhlukHidup {
    @Override
    public void makan() {
        System.out.println("Manusia makan nasi, sayur, dan lauk pauk.");
    }
    
    @Override
    public void bergerak() {
        System.out.println("Manusia bergerak dengan berjalan menggunakan dua kaki.");
    }

    public void berpikir() {
        System.out.println("Manusia sedang berpikir menggunakan akalnya.");
    }
}