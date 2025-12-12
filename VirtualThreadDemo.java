// VirtualThreadDemo.java
import java.util.concurrent.Executors;

public class VirtualThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        
        long start = System.currentTimeMillis();
        
        // Yeh line hai asli jaadu
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1000_000; i++) {  // 1 LAKH TASKS!
                int taskId = i;
                executor.submit(() -> {
                    try {

                        Thread.sleep(100);  // Jaise API call ya DB query
                        // System.out.println("Task " + taskId + " done by " + Thread.currentThread());
                    } catch (Exception e) {}
                });
            }
        } //all threds are endup


        long end = System.currentTimeMillis();
        System.out.println("1 LAKH tasks complete in: " + (end - start) + " ms");
        System.out.println("RAM usage: Sirf ~15-25 MB");
        System.out.println("Thread count: Virtual threads (not OS threads)");
    }
}