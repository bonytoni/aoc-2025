import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resources/day4.txt"));
        List<String> input = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            input.add(line);
            line = br.readLine();
        }
        // padded with .
        char[][] grid = new char[input.size()+2][input.size()+2];
        for (int j = 0; j < grid[0].length; j++){
            grid[0][j] = '.';
            grid[input.size()+1][j] = '.';
        }
        for (int i = 1; i < grid.length-1; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (j == 0 || j == grid[0].length-1){
                    grid[i][j] = '.';
                }
                else{
                    grid[i][j] = input.get(i-1).charAt(j-1);
                }
            }
        }
        System.out.println("Part 1: " + getRolls(grid));
        System.out.println("Part 2: " + getRollsUntilEnd(grid));
    }

    /*
    Part 1
    fewer than 4 neighbors are '@'
     */
    public static int  getRolls(char[][] grid){
        int count = 0;
        int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {-1,-1}, {1,-1}, {-1, 1}};
        for (int i = 1 ; i < grid.length-1; i++) {
            for (int j = 1 ; j < grid[0].length-1; j++) {
                if (grid[i][j] == '@' || grid[i][j] == 'x'){
                    int neighbors = 0;
                    for (int[] d : directions) {
                        int x = i + d[0];
                        int y = j + d[1];
                        if (grid[x][y] == '@' || grid[x][y] == 'x'){
                            neighbors++;
                        }
                    }
                    if (neighbors < 4){
                        grid[i][j] = 'x';
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /*
    Part 2
    keep getting rolls until cant do it anymore
     */
    public static int getRollsUntilEnd(char[][] grid){
        char[][] oldState = grid;
        int count = getRolls(oldState);
        char[][] newState = removeX(oldState);
        while (!compareGrid(oldState, newState)){
            oldState = newState;
            count += getRolls(oldState);
            newState = removeX(oldState);
        }
        return count;
    }

    public static char[][] removeX(char[][] grid){
        char[][] newState = new char[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 'x'){
                    newState[i][j] = '.';
                }
                else{
                    newState[i][j] = grid[i][j];
                }
            }
        }
        return newState;
    }

    public static boolean compareGrid(char[][] oldState, char[][] newState){
        for (int i = 0; i < oldState.length; i++){
            for (int j = 0; j < oldState[0].length; j++){
                if (oldState[i][j] != newState[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
