import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Field
{
    private Object[][] field;
    private int depth, width;
    
    public Field(int depth, int width){
        this.depth = depth;
        this.width = width;
        field = new Object[depth][width];
    }
    
    public int getDepth() {
        return depth;
    }

    public int getWidth() {
        return width;
    }
    
    public void clear(Location location){
        field[location.getRow()][location.getCol()] = null;
    }
    
    public void clear() {
        for (int row = 0; row < depth; row++) {
            for (int col = 0; col < width; col++) {
                field[row][col] = null;
            }
        }
    }
    
    public void place(Object object, Location location){
        field[location.getRow()][location.getCol()] = object;
    }
    
    public Object getObjectAt(Location location){
        return field[location.getRow()][location.getCol()];
    }
    
    public Location freeAdjacentLocation(Location location){
        List<Location> free = new ArrayList<>();
        List<Location> adjacent = adjacentLocations(location);
        for(Location loc : adjacent){
            if(getObjectAt(loc) == null){
                free.add(loc);
            }
        }
        Collections.shuffle(free);
        return free.isEmpty() ? null : free.get(0);
    }
    
    public List<Location> adjacentLocations(Location location){
        List<Location> locations = new ArrayList<>();
        int row = location.getRow();
        int col = location.getCol();
        
        for(int roffset = -1; roffset <= 1; roffset++){
            int nextRow = row + roffset;
            if(nextRow >= 0 && nextRow < depth){
                for(int coffset = -1; coffset <= 1; coffset++){
                    int nextCol = col + coffset;
                    if(nextCol >= 0 && nextCol < width && (roffset != 0 || coffset != 0)){
                        locations.add(new Location(nextRow, nextCol));
                    }
                }
            }
        }
        return locations;
    }
}