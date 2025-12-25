import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day7 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resources/day7.txt"));
        List<String> input = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            input.add(line);
            line = br.readLine();
        }
        char[][] grid =  new char[input.size()][input.get(0).length()];
        for (int i = 0; i < grid.length; i++){
            grid[i] = input.get(i).toCharArray();
        }
        System.out.println("Part 1: " + countBeams(grid));
        System.out.println("Part 2: " + countTimelines(grid));
    }

    /*
    Part 1
    count when encounter ^
     */
    public static int countBeams(char[][] grid){
        int count = 0;
        for (int j = 0; j < grid[0].length; j++){
            if (grid[0][j] == 'S'){
                grid[1][j] = '|';
            }
        }
        for (int i = 2; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                char above = grid[i-1][j];
                if (above == '|' && grid[i][j] == '^'){
                    grid[i][j-1] = '|';
                    grid[i][j+1] = '|';
                    count++;
                }
                else if (above == '|'){
                    grid[i][j] = above;
                }
            }
        }
        return count;
    }

    /*
    Part 2
    use dp to count number of paths
    if above is |, current cell will have previous number of paths
    if above is | and current is ^, the left and right cells will contain previous number of paths
     */
    public static long countTimelines(char[][] grid){
        long[][] dp = new long[grid.length][grid[0].length];
        for (int j = 0; j < grid[0].length; j++){
            if (grid[0][j] == 'S'){
                dp[1][j] = 1;
            }
        }
        for (int i = 2; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                char above = grid[i-1][j];
                if (above == '|' && grid[i][j] == '^'){
                    dp[i][j-1] += dp[i-1][j];
                    dp[i][j+1] += dp[i-1][j];
                }
                if (above == '|'){
                    dp[i][j] += dp[i-1][j];
                }
            }
        }
        long count = 0;
        for (int j = 0; j < grid[0].length; j++){
            count += dp[grid.length-1][j];
        }
        return count;
    }
}
