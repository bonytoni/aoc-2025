import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resources/day1.txt"));
        List<String> rotations = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            rotations.add(line);
            line = br.readLine();
        }
        System.out.println("Part 1: " + countZerosInEndRotations(rotations));
        System.out.println("Part 2: " + countZerosInAllRotations(rotations));
    }

    /*
    Part 1
    starts at 50, 0-99 inclusive
     */
    public static int countZerosInEndRotations(List<String> rotations) {
        int count = 0;
        int current = 50;
        for (String rotation : rotations) {
            char direction  = rotation.charAt(0);
            int amount = Integer.parseInt(rotation.substring(1));
            if (direction == 'L') {
                current -= amount;
            }
            else if (direction == 'R') {
                current += amount;
            }
            while (current < 0) {
                current += 100;
            }
            current %= 100;
            if (current == 0){
                count++;
            }
        }
        return count;
    }

    /*
    Part 2
    starts at 50, 0-99 inclusive
    count 0s whenever passed in rotation as well as pointing to it
     */
    public static int countZerosInAllRotations(List<String> rotations) {
        int count = 0;
        int current = 50;
        for (String rotation : rotations) {
            char direction  = rotation.charAt(0);
            int amount = Integer.parseInt(rotation.substring(1));
            int prev = current;
            if (direction == 'L') {
                current -= amount;
            }
            else if (direction == 'R') {
                current += amount;
            }
            if (current < 0) {
                if (prev > 0) {
                    count++;
                }
                while (current < 0) {
                    if (prev == 0 && current <= -100 || prev != 0 && current <= -100) {
                        count++;
                    }
                    current += 100;
                }
            }
            else if (current == 0){
                count++;
            }
            count += current/100;
            current %= 100;
        }
        return count;
    }
}
