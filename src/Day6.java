import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Day6 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resources/day6.txt"));
        List<List<String>> input = new ArrayList<>();
        List<String> rows = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            rows.add(line);
            List<String> row = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                row.add(st.nextToken());
            }
            input.add(row);
            line = br.readLine();
        }
        System.out.println("Part 1: " + totalSum(input));
        System.out.println("Part 2: " + totalSumCephalopodMath(rows));
    }

    /*
    Part 1
    add up all sums of the math columns
    last column is operation
     */
    public static long totalSum(List<List<String>> input) {
        long result = 0;
        for (int j = 0; j < input.get(0).size(); j++) {
            String operation = input.get(input.size()-1).get(j);
            long total = 0;
            if (operation.equals("*")) {
                total = 1;
            }
            for (int i = 0; i < input.size()-1; i++){
                if (operation.equals("*")) {
                    total *= Integer.parseInt(input.get(i).get(j));
                }
                else if (operation.equals("+")) {
                    total += Integer.parseInt(input.get(i).get(j));
                }
            }
            result += total;
        }
        return result;
    }

    /*
    Part 2
    use operation row for indexing
    top to bottom to form digits
     */
    public static long totalSumCephalopodMath(List<String> rows) {
        long result = 0;
        // padding
        List<String> paddedRows = pad(rows);
        StringBuilder sb;
        long currTotal = 0;
        char currOp = ' ';
        for (int j = 0; j < paddedRows.get(0).length(); j++) {
            char op = paddedRows.get(paddedRows.size()-1).charAt(j);
            if (op != ' '){
                currOp = op;
                if (op == '*') {
                    currTotal = 1;
                }
                else if (op == '+') {
                    currTotal = 0;
                }
            }
            sb = new StringBuilder();
            for (int i = 0; i < paddedRows.size()-1; i++) {
                char c = paddedRows.get(i).charAt(j);
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            if (!sb.isEmpty()){
                if (currOp == '+') {
                    currTotal += Integer.parseInt(sb.toString());
                }
                else if (currOp == '*') {
                    currTotal *= Integer.parseInt(sb.toString());
                }
            }
            else{
                result += currTotal;
            }
        }
        result += currTotal;
        return result;
    }

    public static List<String> pad(List<String> rows) {
        List<String> result = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < rows.size(); i++) {
            max = Math.max(max, rows.get(i).length());
        }
        for (int i = 0; i < rows.size(); i++) {
            String row = rows.get(i);
            for (int j = row.length(); j < max; j++){
                row += " ";
            }
            result.add(i, row);
        }
        return result;
    }
}
