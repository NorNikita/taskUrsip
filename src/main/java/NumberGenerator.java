import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class NumberGenerator {

    public static String generateNum(List<Integer> numbers) {
        Collections.sort(numbers);
        Integer max = Collections.max(numbers);

        int result = IntStream
                .range(1, max + 1)
                .filter(i -> binarySearch(numbers, i, 0, numbers.size() - 1) == -1)
                .min()
                .orElse(max + 1);

        return transformTo3String(result);
    }

    private static int binarySearch(List<Integer> array, int key, int left, int right) {
        int index = -1;

        while (left <= right) {
            index = (left + right) >> 1;

            if(array.get(index) > key) {
                right = index - 1;
            } else if(array.get(index) == key) {
                break;
            } else if(key > array.get(index)) {
                left = index + 1;
            }

            if(left > right) {
                index = -1;
                break;
            }
        }

        return index;
    }

    private static String transformTo3String(Integer value) {

        String[] splitValue = new StringBuilder(value.toString())
                .reverse()
                .toString()
                .split("(?<=\\G\\d{3})");

        for(int i = 0; i < 3 - splitValue[splitValue.length-1].length() + 1; i++) {
            splitValue[splitValue.length - 1] += "0";
        }

        StringBuilder result = new StringBuilder();
        for(String s: splitValue) result.append(s);

        return result.reverse().toString();
    }
}