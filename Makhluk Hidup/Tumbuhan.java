public class Tumbuhan extends MakhlukHidup {
    @Override
    public void makan() {
        System.out.println("Tumbuhan 'makan' dengan melakukan fotosintesis menggunakan cahaya matahari.");
    }
    
    @Override
    public void bergerak() {
        System.out.println("Tumbuhan bergerak secara pasif, seperti tumbuh mengikuti arah cahaya.");
    }
    
    public void tumbuh() {
        System.out.println("Tumbuhan ini sedang tumbuh...");
    }
}