package be.kdg.prog6.MatcherContext.adapters.out;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class ProductJPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client", nullable = false)
    private String client;

    @Column(name = "order_number_edi", nullable = false)
    private String orderNumberEdi;

    @Column(name = "refo_edi")
    private String refoEdi;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "quantity_ordered", nullable = false)
    private Integer quantityOrdered;

    @Column(name = "batch")
    private String batch;

    @Column(name = "line", nullable = false)
    private String line;

    @Column(name = "stock_inventory")
    private String stockInventory;

    @Column(name = "description1")
    private String description1;

    @Column(name = "pack_code")
    private String packCode;

    @Column(name = "product_code", nullable = false)
    private String productCode;

    @Column(name = "description2")
    private String description2;

    @Column(name = "product_extension")
    private String productExtension;

    @Column(name = "description_packaging")
    private String descriptionPackaging;

    @Column(name = "pallet_size")
    private String palletSize;

    @Column(name = "sales_code")
    private String salesCode;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getOrderNumberEdi() {
        return orderNumberEdi;
    }

    public void setOrderNumberEdi(String orderNumberEdi) {
        this.orderNumberEdi = orderNumberEdi;
    }

    public String getRefoEdi() {
        return refoEdi;
    }

    public void setRefoEdi(String refoEdi) {
        this.refoEdi = refoEdi;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getStockInventory() {
        return stockInventory;
    }

    public void setStockInventory(String stockInventory) {
        this.stockInventory = stockInventory;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getPackCode() {
        return packCode;
    }

    public void setPackCode(String packCode) {
        this.packCode = packCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getProductExtension() {
        return productExtension;
    }

    public void setProductExtension(String productExtension) {
        this.productExtension = productExtension;
    }

    public String getDescriptionPackaging() {
        return descriptionPackaging;
    }

    public void setDescriptionPackaging(String descriptionPackaging) {
        this.descriptionPackaging = descriptionPackaging;
    }

    public String getPalletSize() {
        return palletSize;
    }

    public void setPalletSize(String palletSize) {
        this.palletSize = palletSize;
    }

    public String getSalesCode() {
        return salesCode;
    }

    public void setSalesCode(String salesCode) {
        this.salesCode = salesCode;
    }
}