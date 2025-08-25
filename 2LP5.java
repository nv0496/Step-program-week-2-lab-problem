import java.util.*;

public class EmailAnalyzerApp {

    static class EmailInfo {
        String email, username, domain, domainName, extension;
        boolean isValid;

        EmailInfo(String email, String username, String domain, String domainName, String extension, boolean isValid) {
            this.email = email;
            this.username = username;
            this.domain = domain;
            this.domainName = domainName;
            this.extension = extension;
            this.isValid = isValid;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of emails to process:");
        int n = sc.nextInt();
        sc.nextLine();
        List<EmailInfo> emails = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter email " + (i + 1) + ": ");
            String email = sc.nextLine();
            boolean isValid = validateEmail(email);
            String username = "", domain = "", domainName = "", extension = "";
            if (isValid) {
                String[] parts = extractComponents(email);
                username = parts[0];
                domain = parts[1];
                domainName = parts[2];
                extension = parts[3];
            }
            emails.add(new EmailInfo(email, username, domain, domainName, extension, isValid));
        }
        displayResults(emails);
        analyzeStatistics(emails);
        sc.close();
    }

    public static boolean validateEmail(String email) {
        int atPos = email.indexOf('@');
        int lastAtPos = email.lastIndexOf('@');
        if (atPos == -1 || atPos != lastAtPos) return false;
        int dotPos = email.indexOf('.', atPos);
        if (dotPos == -1) return false;
        String username = email.substring(0, atPos);
        String domain = email.substring(atPos + 1);
        if (username.isEmpty() || domain.isEmpty()) return false;
        return true;
    }

    public static String[] extractComponents(String email) {
        int atPos = email.indexOf('@');
        String username = email.substring(0, atPos);
        String domain = email.substring(atPos + 1);
        int dotPos = domain.lastIndexOf('.');
        String domainName = (dotPos != -1) ? domain.substring(0, dotPos) : domain;
        String extension = (dotPos != -1) ? domain.substring(dotPos + 1) : "";
        return new String[]{username, domain, domainName, extension};
    }

    public static void displayResults(List<EmailInfo> emails) {
        System.out.println("\n=== EMAIL ANALYSIS TABLE ===");
        System.out.printf("%-25s %-15s %-20s %-15s %-10s %-10s%n",
                "Email", "Username", "Domain", "Domain Name", "Extension", "Valid");
        for (EmailInfo e : emails) {
            System.out.printf("%-25s %-15s %-20s %-15s %-10s %-10s%n",
                    e.email, e.username, e.domain, e.domainName, e.extension, e.isValid ? "Yes" : "No");
        }
    }

    public static void analyzeStatistics(List<EmailInfo> emails) {
        int validCount = 0, invalidCount = 0, totalUsernameLength = 0;
        Map<String, Integer> domainCount = new HashMap<>();
        for (EmailInfo e : emails) {
            if (e.isValid) {
                validCount++;
                totalUsernameLength += e.username.length();
                domainCount.put(e.domain, domainCount.getOrDefault(e.domain, 0) + 1);
            } else {
                invalidCount++;
            }
        }
        String mostCommonDomain = "N/A";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : domainCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostCommonDomain = entry.getKey();
            }
        }
        double avgUsernameLength = (validCount > 0) ? (double) totalUsernameLength / validCount : 0;
        System.out.println("\n=== EMAIL STATISTICS ===");
        System.out.println("Total Valid Emails   : " + validCount);
        System.out.println("Total Invalid Emails : " + invalidCount);
        System.out.println("Most Common Domain   : " + mostCommonDomain);
        System.out.printf("Average Username Length : %.2f%n", avgUsernameLength);
    }
}
