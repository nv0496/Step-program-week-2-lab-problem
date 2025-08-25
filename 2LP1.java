import java.util.*;

public class ManualSubstringReplace {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter main text: ");
        String text = sc.nextLine();

        System.out.print("Enter substring to find: ");
        String find = sc.nextLine();

        System.out.print("Enter replacement substring: ");
        String replace = sc.nextLine();

        int[] positions = findOccurrences(text, find);

        String manualResult = manualReplace(text, find, replace, positions);
        String builtInResult = text.replace(find, replace);

        System.out.println("\nManual Result:   " + manualResult);
        System.out.println("Built-in Result: " + builtInResult);
        System.out.println("Are they same?  " + manualResult.equals(builtInResult));

        sc.close();
    }

    public static int[] findOccurrences(String text, String find) {
        List<Integer> list = new ArrayList<>();
        int index = text.indexOf(find);
        while (index != -1) {
            list.add(index);
            index = text.indexOf(find, index + find.length());
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public static String manualReplace(String text, String find, String replace, int[] positions) {
        if (positions.length == 0) return text;

        StringBuilder result = new StringBuilder();
        int currentIndex = 0;

        for (int pos : positions) {
            while (currentIndex < pos) {
                result.append(text.charAt(currentIndex));
                currentIndex++;
            }
            result.append(replace);
            currentIndex += find.length();
        }

        while (currentIndex < text.length()) {
            result.append(text.charAt(currentIndex));
            currentIndex++;
        }

        return result.toString();
    }
}
