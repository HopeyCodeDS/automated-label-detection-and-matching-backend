package be.kdg.prog6;


import org.opencv.core.Core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "be.kdg.prog6"
})
public class CompleteApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompleteApplication.class, args);

        // Load OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
}
