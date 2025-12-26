import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class SimulatorView extends JPanel {
    private static final int GRID_VIEW_SCALING_FACTOR = 6;

    private int gridWidth, gridHeight;
    private int xScale, yScale;
    private Field field;

    public SimulatorView(int height, int width) {
        gridHeight = height;
        gridWidth = width;
        setPreferredSize(new Dimension(width * GRID_VIEW_SCALING_FACTOR, 
                                     height * GRID_VIEW_SCALING_FACTOR));
    }

    public void showStatus(Field field) {
        this.field = field;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (field != null) {
            xScale = getWidth() / gridWidth;
            if (xScale < 1) {
                xScale = GRID_VIEW_SCALING_FACTOR;
            }
            yScale = getHeight() / gridHeight;
            if (yScale < 1) {
                yScale = GRID_VIEW_SCALING_FACTOR;
            }

            for (int row = 0; row < gridHeight; row++) {
                for (int col = 0; col < gridWidth; col++) {
                    Object animal = field.getObjectAt(new Location(row, col));
                    if (animal != null) {
                        if (animal instanceof Rabbit) {
                            g.setColor(Color.ORANGE);
                        } else if (animal instanceof Fox) {
                            g.setColor(Color.BLUE);
                        }
                        g.fillRect(col * xScale, row * yScale, xScale, yScale);
                    }
                }
            }
        }
    }
}