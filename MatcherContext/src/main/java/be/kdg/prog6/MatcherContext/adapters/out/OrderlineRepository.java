package be.kdg.prog6.MatcherContext.adapters.out;

import be.kdg.prog6.MatcherContext.domain.Orderline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderlineRepository extends JpaRepository<Orderline, String> {
    List<Orderline> findByOrdernummerEdi(String ordernummerEdi);
    List<Orderline> findByProductCode(String text);
    List<Orderline> findByBatch(String batch);
}
