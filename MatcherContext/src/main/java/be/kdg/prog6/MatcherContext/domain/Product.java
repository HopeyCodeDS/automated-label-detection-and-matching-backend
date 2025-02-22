package be.kdg.prog6.MatcherContext.domain;
import java.time.LocalDate;

public class Product {


    private String client;

    private String orderNumberEdi;

    private String refoEdi;

    private LocalDate orderDate;

    private String customerName;

    private Integer quantityOrdered;

    private String batch;

    private String line;

    private String stockInventory;

    private String description1;

    private String packCode;

    private String productCode;

    private String description2;

    private String productExtension;

    private String descriptionPackaging;

    private String palletSize;

    private String salesCode;
    public void initialiseProduct(String client, String orderNumberEdi, String refoEdi, LocalDate orderDate, String customerName, Integer quantityOrdered, String batch, String line, String stockInventory, String description1, String packCode, String productCode, String description2, String productExtension, String descriptionPackaging, String palletSize, String salesCode) {
        this.client = client;
        this.orderNumberEdi = orderNumberEdi;
        this.refoEdi = refoEdi;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.quantityOrdered = quantityOrdered;
        this.batch = batch;
        this.line = line;
        this.stockInventory = stockInventory;
        this.description1 = description1;
        this.packCode = packCode;
        this.productCode = productCode;
        this.description2 = description2;
        this.productExtension = productExtension;
        this.descriptionPackaging = descriptionPackaging;
        this.palletSize = palletSize;
        this.salesCode = salesCode;
    }

    public String getClient() {
        return client;
    }

    public String getOrderNumberEdi() {
        return orderNumberEdi;
    }

    public String getRefoEdi() {
        return refoEdi;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public String getBatch() {
        return batch;
    }

    public String getLine() {
        return line;
    }

    public String getStockInventory() {
        return stockInventory;
    }

    public String getDescription1() {
        return description1;
    }

    public String getPackCode() {
        return packCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getDescription2() {
        return description2;
    }

    public String getProductExtension() {
        return productExtension;
    }

    public String getDescriptionPackaging() {
        return descriptionPackaging;
    }

    public String getPalletSize() {
        return palletSize;
    }

    public String getSalesCode() {
        return salesCode;
    }
}
