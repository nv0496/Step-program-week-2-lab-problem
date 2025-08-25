import java.util.Scanner;

public class CaesarCipherApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter shift value: ");
        int shift = sc.nextInt();

        System.out.println("\n=== CAESAR CIPHER SYSTEM ===");

        System.out.println("\nOriginal Text with ASCII:");
        displayASCII(text);

        String encrypted = encrypt(text, shift);
        System.out.println("\nEncrypted Text with ASCII:");
        displayASCII(encrypted);

        String decrypted = decrypt(encrypted, shift);
        System.out.println("\nDecrypted Text with ASCII:");
        displayASCII(decrypted);

        System.out.println("\nValidation: " + text.equals(decrypted));

        sc.close();
    }

    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                result.append((char) ((c - 'A' + shift) % 26 + 'A'));
            } else if (c >= 'a' && c <= 'z') {
                result.append((char) ((c - 'a' + shift) % 26 + 'a'));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    public static void displayASCII(String text) {
        for (char c : text.toCharArray()) {
            System.out.println(c + " -> " + (int) c);
        }
        System.out.println("Result: " + text);
    }
}
