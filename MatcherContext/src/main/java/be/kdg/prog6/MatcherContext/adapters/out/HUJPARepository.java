package be.kdg.prog6.MatcherContext.adapters.out;

import be.kdg.prog6.MatcherContext.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HUJPARepository extends JpaRepository<HUJPAEntity, Long> {

    Optional<HUJPAEntity> findByHuNumber(String huNumber);

    @Modifying
    @Transactional
    @Query("UPDATE HUJPAEntity h SET h.product = :product WHERE h.huNumber = :huNumber")
    void linkHuToProduct(String huNumber, ProductJPAEntity product);


    @Query("SELECT h FROM HUJPAEntity h WHERE h.huNumber LIKE %:searchString%")
    List<HUJPAEntity> findByHuNumberContaining(@Param("searchString") String searchString);
}
