import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ImageViewer extends JFrame {
    private JButton buttonOpenFile = new JButton("Buka Gambar");
    private JLabel labelImage = new JLabel();

    public ImageViewer() {
        super("Image Viewer");

        setSize(800, 600);
        
        setLayout(new BorderLayout());

        add(buttonOpenFile, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(labelImage);
        add(scrollPane, BorderLayout.CENTER);
        
        labelImage.setHorizontalAlignment(JLabel.CENTER);

        buttonOpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                
                // Membuat filter agar hanya file gambar yang bisa dipilih
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Image Files (JPG, PNG, GIF)", "jpg", "png", "gif");
                fileChooser.setFileFilter(filter);

                // Menampilkan dialog untuk memilih file
                int returnValue = fileChooser.showOpenDialog(null);

                // Jika user memilih sebuah file
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    
                    // Membuat ImageIcon dari file yang dipilih
                    ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                    
                    // Menampilkan gambar di JLabel
                    labelImage.setIcon(imageIcon);
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new ImageViewer();
    }
}