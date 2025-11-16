public class SalesItemTest {
    public static void main(String[] args) {
        SalesItem item = new SalesItem("Kipas Tangan", 350000);

        item.addComment("Rafiz", "Performanya bagus!", 5);
        item.addComment("Rafiz", "Mau nambahin review lain.", 4); //ga bisa karena author sama dengan sebelumnya
        item.addComment("Tiya", "Biasa aja kipasnya, mahal", 2); //ga valid
        item.addComment("Ayu", "Anginnya kenceng, tapi berat kipasnya.", 4);

        System.out.println("\n=== Informasi Item ===");
        item.showInfo();
        
        Comment RafizComment = item.getCommentByAuthor("Rafiz");
        if (RafizComment != null) {
            System.out.println("Menambah upvote pada komentar Rafiz");
            RafizComment.upvote();
            System.out.println("Menambah upvote pada komentar Rafiz");
            RafizComment.upvote();
            System.out.println("Menambah downvote pada komentar Rafiz");
            RafizComment.downvote();
        } else {
            System.out.println("Komentar Rafiz tidak ditemukan.");
        }
        
        System.out.println("");
        
        Comment TiyaComment = item.getCommentByAuthor("Tiya");
        if (TiyaComment != null) {
            System.out.println("Menambah upvote pada komentar Tiya");
            TiyaComment.upvote();
            System.out.println("Menambah upvote pada komentar Tiya");
            TiyaComment.upvote();
            System.out.println("Menambah downvote pada komentar Tiya");
            TiyaComment.downvote();
            System.out.println("Menambah downvote pada komentar Tiya");
            TiyaComment.downvote();
        } else {
            System.out.println("Komentar Tiya tidak ditemukan.");
        }

        System.out.println("\n=== Komentar Paling Membantu ===");
        Comment best = item.findMostHelpfulComment();
        if (best != null) {
            best.print();
        } else {
            System.out.println("Belum ada komentar.");
        }
    }
}