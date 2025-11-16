import java.util.ArrayList;

public class SalesItem {
    private String name;
    private int price;
    private ArrayList<Comment> comments;

    public SalesItem(String name, int price) {
        this.name = name;
        this.price = price;
        this.comments = new ArrayList<Comment>();
    }

    public boolean addComment(String author, String text, int rating) {
        if (rating < 1 || rating > 5) {
            return false; // rating tidak valid
        }

        for (Comment comment : comments) {
            if (comment.getAuthor().equals(author)) {
                return false;
            }
        }

        Comment newComment = new Comment(author, text, rating);
        comments.add(newComment);
        return true;
    }

    public boolean removeComment(Comment comment) {
        return comments.remove(comment);
    }

    public int getNumberOfComments() {
        return comments.size();
    }

    public void showInfo() {
        System.out.println("=================================");
        System.out.println("Item: " + name);
        System.out.println("Price: " + price);
        System.out.println("Number of comments: " + getNumberOfComments());
        System.out.println("---------------------------------");

        for (Comment comment : comments) {
            comment.print();
            System.out.println();
        }
    }

    public Comment findMostHelpfulComment() {
        if (comments.isEmpty()) {
            return null;
        }

        Comment best = comments.get(0);
        for (Comment current : comments) {
            if (current.getVoteBalance() > best.getVoteBalance()) {
                best = current;
            }
        }
        return best;
    }
    
    //Methode tambahan untuk testing
    public Comment getCommentByAuthor(String author) {
    for (Comment comment : comments) {
        if (comment.getAuthor().equals(author)) {
            return comment;
        }
    }
    return null; 
    }
}