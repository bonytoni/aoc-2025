import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resources/day5.txt"));
        List<String> ranges = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        String line = br.readLine();
        while (!line.isEmpty()) {
            ranges.add(line);
            line = br.readLine();
        }
        line = br.readLine();
        while (line != null) {
            ids.add(Long.parseLong(line));
            line = br.readLine();
        }
        System.out.println("Part 1: " + getFreshCount(ranges, ids));
        System.out.println("Part 2: " + getNumIdsByFreshRanges(ranges));
    }

    /*
    Part 1
    ids that fall within the ranges inclusive are fresh
     */
    public static int getFreshCount(List<String> ranges, List<Long> ids){
        int count = 0;
        for (long id : ids){
            for (String range : ranges){
                long min = Long.parseLong(range.split("-")[0]);
                long max = Long.parseLong(range.split("-")[1]);
                if (id >= min && id <= max){
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    /*
    Part 2
    merge these fresh ranges
    get total number of ids in these ranges
     */
    public static long getNumIdsByFreshRanges(List<String> ranges){
        long count = 0;
        List<long[]> freshRangesList = new ArrayList<>();
        for (String range : ranges){
            long min = Long.parseLong(range.split("-")[0]);
            long max = Long.parseLong(range.split("-")[1]);
            freshRangesList.add(new long[]{min, max});
        }
        freshRangesList.sort((a, b) -> Long.compare(a[0], b[0]));
        List<long[]> mergedRanges = mergeRanges(freshRangesList);
        for (long[] range : mergedRanges){
            long min = range[0];
            long max = range[1];
            count += (max-min+1);
        }
        return count;
    }

    public static List<long[]> mergeRanges(List<long[]> ranges){
        List<long[]> mergedRanges = new ArrayList<>();
        long[] interval = ranges.get(0);
        for (int i = 1; i < ranges.size(); i++){
            long[] newInterval = ranges.get(i);
            if (newInterval[0] > interval[1]){
                mergedRanges.add(interval);
                interval = newInterval;
            }
            else {
                interval[0] = Math.min(interval[0], newInterval[0]);
                interval[1] = Math.max(interval[1], newInterval[1]);
            }
        }
        mergedRanges.add(interval);
        return mergedRanges;
    }
}
