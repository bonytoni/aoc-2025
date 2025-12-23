import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resources/day3.txt"));
        List<String> banks = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            banks.add(line);
            line = br.readLine();
        }
        System.out.println("Part 1: " + sumMaxJoltage(banks));
        System.out.println("Part 2: " + sumMaxJoltage2(banks));
    }

    /*
    Part 1
    turn on 2 batteries in the bank to make max joltage, order matters
    sum all the joltage
     */
    public static int sumMaxJoltage(List<String> banks) {
        int total = 0;
        for (String bank : banks) {
            int max = -1;
            for (int i = 0; i < bank.length()-1; i++) {
                max = Math.max(max, bank.charAt(i) - '0');
            }
            int secondDigit = -1;
            for (int i = bank.indexOf((char)(max+'0'))+1; i < bank.length(); i++) {
                secondDigit = Math.max(secondDigit, bank.charAt(i) - '0');
            }
            total += Integer.parseInt("" + max + secondDigit);
        }
        return total;
    }

    /*
    Part 2
    turn on 12 batteries in the bank to make max joltage, order matters
    sum all the joltage
     */
    public static long sumMaxJoltage2(List<String> banks) {
        long total = 0;
        StringBuilder sb = new StringBuilder();
        for (String bank : banks) {
            int max = bank.charAt(0)-'0';
            int maxIndex = 0;
            int batteriesLeft = 12;
            while (batteriesLeft > 0) {
                for (int i = maxIndex; i < bank.length() - batteriesLeft + 1; i++) {
                    if (bank.charAt(i) - '0' > max) {
                        max = bank.charAt(i) - '0';
                        maxIndex = i;
                    }
                }
                sb.append(max);
                if (maxIndex != bank.length()-1){
                    max = bank.charAt(maxIndex+1) - '0';
                }
                maxIndex++;
                batteriesLeft--;
            }
            total += Long.parseLong(sb.toString());
            sb = new StringBuilder();
        }
        return total;
    }
}
