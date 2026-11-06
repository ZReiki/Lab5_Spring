package ua.edu.nuos.lab5_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.edu.nuos.lab5_spring.model.Manufacturer;
import ua.edu.nuos.lab5_spring.model.ManufacturerStats;

import java.util.List;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    // Статистика годинників за виробниками
    @Query("""
        SELECT
            m.name AS manufacturerName,
            m.country AS country,
            COUNT(w) AS watchCount,
            COALESCE(SUM(w.quantity), 0) AS totalStock,
            COALESCE(AVG(w.price), 0.0) AS averagePrice
        FROM Manufacturer m
        LEFT JOIN m.watches w
        GROUP BY m.manufacturerId, m.name, m.country
        ORDER BY m.name
    """)
    List<ManufacturerStats> findManufacturerStatistics();
}
