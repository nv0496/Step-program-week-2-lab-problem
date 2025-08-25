import java.util.Scanner;

public class StringPerformanceTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of iterations: ");
        int iterations = sc.nextInt();

        Result stringResult = testStringConcat(iterations);
        Result builderResult = testStringBuilder(iterations);
        Result bufferResult = testStringBuffer(iterations);

        System.out.println("\n=== PERFORMANCE COMPARISON ===");
        System.out.printf("%-15s %-15s %-20s %-15s\n", 
                          "Method", "Time (ms)", "Final Length", "Memory Used (KB)");
        System.out.println("---------------------------------------------------------------");
        displayResult("String", stringResult);
        displayResult("StringBuilder", builderResult);
        displayResult("StringBuffer", bufferResult);

        sc.close();
    }

    static class Result {
        long timeTaken;
        int length;
        long memoryUsed;

        Result(long timeTaken, int length, long memoryUsed) {
            this.timeTaken = timeTaken;
            this.length = length;
            this.memoryUsed = memoryUsed;
        }
    }

    public static Result testStringConcat(int iterations) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long beforeMem = (runtime.totalMemory() - runtime.freeMemory());

        long start = System.currentTimeMillis();
        String text = "";
        for (int i = 0; i < iterations; i++) {
            text = text + "a";
        }
        long end = System.currentTimeMillis();

        long afterMem = (runtime.totalMemory() - runtime.freeMemory());
        return new Result(end - start, text.length(), (afterMem - beforeMem) / 1024);
    }

    public static Result testStringBuilder(int iterations) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long beforeMem = (runtime.totalMemory() - runtime.freeMemory());

        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("a");
        }
        long end = System.currentTimeMillis();

        long afterMem = (runtime.totalMemory() - runtime.freeMemory());
        return new Result(end - start, sb.length(), (afterMem - beforeMem) / 1024);
    }

    public static Result testStringBuffer(int iterations) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long beforeMem = (runtime.totalMemory() - runtime.freeMemory());

        long start = System.currentTimeMillis();
        StringBuffer sbuf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sbuf.append("a");
        }
        long end = System.currentTimeMillis();

        long afterMem = (runtime.totalMemory() - runtime.freeMemory());
        return new Result(end - start, sbuf.length(), (afterMem - beforeMem) / 1024);
    }

    public static void displayResult(String method, Result r) {
        System.out.printf("%-15s %-15d %-20d %-15d\n", 
                          method, r.timeTaken, r.length, r.memoryUsed);
    }
}
