import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Simulator {
    private Field field;
    private List<Animal> animals;
    private int step;
    
    private SimulatorView view;
    private JFrame frame;
    private JLabel stepLabel, populationLabel;
    
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;
    
    private static final int DEFAULT_DEPTH = 100;
    private static final int DEFAULT_WIDTH = 100;
    
    public Simulator() {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }
    
    public Simulator(int depth, int width){
        step = 0;
        animals = new ArrayList<>();
        field = new Field(depth, width);
        
        frame = new JFrame("Fox and Rabbit Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        stepLabel = new JLabel("Step: 0", JLabel.CENTER);
        populationLabel = new JLabel("Population: ", JLabel.CENTER);
        
        view = new SimulatorView(depth, width);
        
        frame.add(stepLabel, BorderLayout.NORTH);
        frame.add(view, BorderLayout.CENTER);
        frame.add(populationLabel, BorderLayout.SOUTH);
        
        frame.pack();
        frame.setVisible(true);
        
        populate();
        updateView();
    }
    
    public void runLongSimulation() {
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulateOneStep();
            }
        });
        timer.start();
    }
    
    public void simulateOneStep(){
        step++;
        List<Animal> newAnimals = new ArrayList<>();
        
        for (Animal animal : new ArrayList<>(animals)){
            if(animal.isAlive()){
                animal.act(newAnimals);
            }
            else {
                animals.remove(animal);
            }
        }
        animals.addAll(newAnimals);
        
        updateView();
    }
    
    private void updateView() {
        stepLabel.setText("Step: " + step);
        populationLabel.setText(getPopulationDetails());
        view.showStatus(field);
    }
    
    private String getPopulationDetails() {
        int rabbitCount = 0;
        int foxCount = 0;
        for (Animal animal : animals) {
            if (animal instanceof Rabbit) {
                rabbitCount++;
            } else if (animal instanceof Fox) {
                foxCount++;
            }
        }
        return "Population: Rabbit: " + rabbitCount + " Fox: " + foxCount;
    }
    
    private void populate(){
        Random rand = new Random();
        field.clear();
        animals.clear();
        
        int depth = field.getDepth();
        int width = field.getWidth();
        
        for(int row = 0; row < depth; row++){
            for(int col = 0; col < width; col++){
                Location location = new Location(row, col);
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY){
                    Fox fox = new Fox(field, location);
                    animals.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY){
                    Rabbit rabbit = new Rabbit(field, location);
                    animals.add(rabbit);
                }
            }
        }
    }

    public static void main(String[] args) {
        Simulator simulator = new Simulator();
        simulator.runLongSimulation();
    }
}