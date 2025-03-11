package be.kdg.prog6.MatcherContext.adapters.out;

import jakarta.persistence.*;

@Entity
@Table(name = "handling_unit")
public class HUJPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hu_number", nullable = false, unique = true)
    private String huNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_hu_product"))
    private ProductJPAEntity product;

    public HUJPAEntity() {
    }

    public HUJPAEntity(String huNumber, ProductJPAEntity product) {
        this.huNumber = huNumber;
        this.product = product;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHuNumber() {
        return huNumber;
    }

    public void setHuNumber(String huNumber) {
        this.huNumber = huNumber;
    }

    public ProductJPAEntity getProduct() {
        return product;
    }

    public void setProduct(ProductJPAEntity product) {
        this.product = product;
    }
}
