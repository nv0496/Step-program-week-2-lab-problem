import java.util.*;

public class TextFormatter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text to format:");
        String text = sc.nextLine();
        System.out.println("Enter desired line width:");
        int width = sc.nextInt();
        sc.nextLine();

        List<String> words = extractWords(text);
        List<String> justified = justifyText(words, width);
        List<String> centered = centerAlign(words, width);

        System.out.println("\n=== Original Text ===");
        System.out.println(text);

        System.out.println("\n=== Justified Text ===");
        displayFormatted(justified);

        System.out.println("\n=== Center Aligned Text ===");
        displayFormatted(centered);

        performanceComparison(words, width);
    }

    public static List<String> extractWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (start < i) words.add(text.substring(start, i));
                start = i + 1;
            }
        }
        if (start < text.length()) words.add(text.substring(start));
        return words;
    }

    public static List<String> justifyText(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.size()) {
            int lineLen = words.get(i).length();
            int j = i + 1;
            while (j < words.size() && lineLen + 1 + words.get(j).length() <= width) {
                lineLen += 1 + words.get(j).length();
                j++;
            }
            StringBuilder sb = new StringBuilder();
            int gaps = j - i - 1;
            if (j == words.size() || gaps == 0) {
                for (int k = i; k < j; k++) {
                    sb.append(words.get(k));
                    if (k < j - 1) sb.append(" ");
                }
                while (sb.length() < width) sb.append(" ");
            } else {
                int totalSpaces = width - (lineLen - gaps);
                int spacePerGap = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;
                for (int k = i; k < j; k++) {
                    sb.append(words.get(k));
                    if (k < j - 1) {
                        for (int s = 0; s < spacePerGap; s++) sb.append(" ");
                        if (extraSpaces-- > 0) sb.append(" ");
                    }
                }
            }
            lines.add(sb.toString());
            i = j;
        }
        return lines;
    }

    public static List<String> centerAlign(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.size()) {
            int lineLen = words.get(i).length();
            int j = i + 1;
            while (j < words.size() && lineLen + 1 + words.get(j).length() <= width) {
                lineLen += 1 + words.get(j).length();
                j++;
            }
            StringBuilder sb = new StringBuilder();
            for (int k = i; k < j; k++) {
                sb.append(words.get(k));
                if (k < j - 1) sb.append(" ");
            }
            int padding = width - sb.length();
            int left = padding / 2;
            int right = padding - left;
            StringBuilder centered = new StringBuilder();
            for (int x = 0; x < left; x++) centered.append(" ");
            centered.append(sb);
            for (int x = 0; x < right; x++) centered.append(" ");
            lines.add(centered.toString());
            i = j;
        }
        return lines;
    }

    public static void performanceComparison(List<String> words, int width) {
        long start1 = System.nanoTime();
        justifyText(words, width);
        long time1 = System.nanoTime() - start1;

        long start2 = System.nanoTime();
        justifyWithConcatenation(words, width);
        long time2 = System.nanoTime() - start2;

        System.out.println("\n=== Performance Comparison ===");
        System.out.printf("%-20s %-15s%n", "Method", "Time (ns)");
        System.out.printf("%-20s %-15d%n", "StringBuilder", time1);
        System.out.printf("%-20s %-15d%n", "Concatenation", time2);
    }

    public static List<String> justifyWithConcatenation(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.size()) {
            int lineLen = words.get(i).length();
            int j = i + 1;
            while (j < words.size() && lineLen + 1 + words.get(j).length() <= width) {
                lineLen += 1 + words.get(j).length();
                j++;
            }
            String line = "";
            int gaps = j - i - 1;
            if (j == words.size() || gaps == 0) {
                for (int k = i; k < j; k++) {
                    line += words.get(k);
                    if (k < j - 1) line += " ";
                }
                while (line.length() < width) line += " ";
            } else {
                int totalSpaces = width - (lineLen - gaps);
                int spacePerGap = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;
                for (int k = i; k < j; k++) {
                    line += words.get(k);
                    if (k < j - 1) {
                        for (int s = 0; s < spacePerGap; s++) line += " ";
                        if (extraSpaces-- > 0) line += " ";
                    }
                }
            }
            lines.add(line);
            i = j;
        }
        return lines;
    }

    public static void displayFormatted(List<String> lines) {
        int lineNo = 1;
        for (String line : lines) {
            System.out.printf("%2d | %s | (%d chars)%n", lineNo++, line, line.length());
        }
    }
}
