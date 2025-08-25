import java.util.*;

public class CaseConverterASCII {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        String asciiUpper = toUpperASCII(text);
        String asciiLower = toLowerASCII(text);
        String asciiTitle = toTitleASCII(text);

        String builtInUpper = text.toUpperCase();
        String builtInLower = text.toLowerCase();

        System.out.println("\n=== CASE CONVERSION RESULTS ===");
        System.out.printf("%-15s %-30s %-30s\n", "Conversion", "ASCII Method", "Built-in Method");
        System.out.println("----------------------------------------------------------------------------");
        System.out.printf("%-15s %-30s %-30s\n", "UPPERCASE", asciiUpper, builtInUpper);
        System.out.printf("%-15s %-30s %-30s\n", "LOWERCASE", asciiLower, builtInLower);
        System.out.printf("%-15s %-30s %-30s\n", "TITLECASE", asciiTitle, toTitleBuiltIn(text));

        sc.close();
    }

    public static String toUpperASCII(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                result.append((char) (ch - 32));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String toLowerASCII(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                result.append((char) (ch + 32));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String toTitleASCII(String text) {
        StringBuilder result = new StringBuilder();
        boolean newWord = true;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch == ' ') {
                result.append(ch);
                newWord = true;
            } else {
                if (newWord) {
                    if (ch >= 'a' && ch <= 'z') {
                        result.append((char) (ch - 32));
                    } else {
                        result.append(ch);
                    }
                    newWord = false;
                } else {
                    if (ch >= 'A' && ch <= 'Z') {
                        result.append((char) (ch + 32));
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }

    public static String toTitleBuiltIn(String text) {
        String[] words = text.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    sb.append(word.substring(1));
                }
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
}
