public class PengamatanDunia {
    public static void main(String[] args) {
        Manusia manusia = new Manusia();
        Hewan hewan = new Hewan();
        Tumbuhan tumbuhan = new Tumbuhan();
        
        System.out.println(">>>>> PENGAMATAN MAKHLUK HIDUP <<<<<");
        
        System.out.println("\n>>> Kategori: Manusia");
        manusia.bernapas();
        manusia.makan();   
        manusia.bergerak(); 
        manusia.berpikir(); 
        
        System.out.println("\n>>> Kategori: Hewan");
        hewan.bernapas();  
        hewan.makan();    
        hewan.bergerak();  
        hewan.bersuara();   
        
        System.out.println("\n>>> Kategori: Tumbuhan");
        tumbuhan.bernapas();
        tumbuhan.makan();
        tumbuhan.bergerak();
        tumbuhan.tumbuh();
        
        System.out.println("\n>>>>> PENGAMATAN SELESAI <<<<<");
    }
}