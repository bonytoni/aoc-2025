import java.io.BufferedReader;
import java.io.FileReader;

public class Day2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resources/day2.txt"));
        String line = br.readLine();
        String[] ranges = line.split(",");
        System.out.println("Part 1: " + sumInvalidIDs(ranges));
        System.out.println("Part 2: " + sumInvalidIDs2(ranges));
    }

    /*
    Part 1
    ranges i.e., 11-22
    find invalid ids that repeat twice i.e. 11 and 22
    sum them up
     */
    public static long sumInvalidIDs(String[] ranges){
        long sum = 0;
        for (String range : ranges) {
            String[] arr =  range.split("-");
            long min  = Long.parseLong(arr[0]);
            long max = Long.parseLong(arr[1]);
            for (long i = min; i <= max; i++) {
               String str = "" + i;
               if (str.length() % 2 == 0) {
                   String str1 =  str.substring(0, str.length()/2);
                   String str2 =  str.substring(str.length()/2);
                   if (str1.equals(str2)) {
                       sum += i;
                   }
               }
            }
        }
        return sum;
    }

    /*
    Part 2
    now we find invalid ids that repeat at least twice
    i.e., 123123123, 1212121212, 1111111
     */
    public static long sumInvalidIDs2(String[] ranges){
        long sum = 0;
        for (String range : ranges) {
            String[] arr =  range.split("-");
            long min  = Long.parseLong(arr[0]);
            long max = Long.parseLong(arr[1]);
            for (long i = min; i <= max; i++) {
                String str = "" + i;
                int repeat = 2;
                while (repeat <= str.length()) {
                    if (str.length() % repeat == 0) {
                        String str1 =  str.substring(0, str.length()/repeat).repeat(repeat);
                        if (str1.equals(str)) {
                            sum += i;
                            break;
                        }
                    }
                    repeat++;
                }
            }
        }
        return sum;
    }
}
