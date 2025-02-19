package be.kdg.prog6.MatcherContext.adapters.out;

import be.kdg.prog6.MatcherContext.domain.Orderline;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;

@Service
public class CsvDataLoader {

    @Autowired
    private OrderlineRepository orderlineRepository;

    public void loadData(){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource("data/label-dataset.CSV").getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Client")) { // Skip header row
                    continue;
                }
                String[] values = line.split(";");

                // Validate the row to ensure it has the required number of fields
                if (values.length < 16) {
                    System.out.println("Skipping invalid row: " + line);
                    continue;
                }

                Orderline orderline = new Orderline();
                orderline.setClient(values[0]);
                orderline.setOrdernummerEdi(values[1]);
                orderline.setRefoEdi(values[2]);
                orderline.setOrderdatum(LocalDate.parse(values[3]));
                orderline.setName1(values[4]);
                orderline.setQtyOrd(Integer.parseInt(values[5]));
                orderline.setBatch(values[6]);
                orderline.setLineAlfa(values[7]);
                orderline.setStockInventory(values[8]);
                orderline.setDesc1(values[9]);
                orderline.setPackCode(values[10]);
                orderline.setProductCode(values[11]);
                orderline.setOmschrijving(values[12]);
                orderline.setProductExtention1(values[13]);
                orderline.setOmschrijvingVerpak(values[14]);
                orderline.setPalletSize(values[15]);
                orderline.setSalescode(values[16]);
                orderline.setProcessed(false); // Assuming default value

                orderlineRepository.save(orderline);
            }
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
