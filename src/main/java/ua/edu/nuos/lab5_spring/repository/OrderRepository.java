package ua.edu.nuos.lab5_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.edu.nuos.lab5_spring.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Пошук замовлень, які містять годинники певного бренду
    @Query("""
        SELECT DISTINCT o FROM Order o
        JOIN FETCH o.watches w
        JOIN FETCH w.manufacturer
        WHERE LOWER(w.brand) LIKE LOWER(CONCAT('%', :brandName, '%'))
    """)
    List<Order> findByWatchBrand(
            @Param("brandName") String brandName
    );
}
