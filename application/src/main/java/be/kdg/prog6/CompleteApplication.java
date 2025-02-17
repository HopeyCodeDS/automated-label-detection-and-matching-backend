package be.kdg.prog6;


import be.kdg.prog6.MatcherContext.adapters.out.CsvDataLoader;
import org.opencv.core.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "be.kdg.prog6"
})
public class CompleteApplication implements CommandLineRunner {

    @Autowired
    private CsvDataLoader csvDataLoader;

    public static void main(String[] args) {
        SpringApplication.run(CompleteApplication.class, args);

        // Load OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Override
    public void run(String... args) throws Exception {
        csvDataLoader.loadData();
    }
}
