package ua.edu.nuos.lab5_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.edu.nuos.lab5_spring.model.Watch;

import java.util.List;

public interface WatchRepository extends JpaRepository<Watch, Long> {
    // Отримання всіх годинників за даними виробника
    @Query("""
        SELECT w FROM Watch w
        JOIN FETCH w.manufacturer m
        WHERE m.manufacturerId = :manufacturerId
    """)
    List<Watch> findByManufacturerId(
            @Param("manufacturerId") Long manufacturerId
    );

    // Фільтрація годинників за країною виробника
    @Query("""
        SELECT w FROM Watch w
        JOIN FETCH w.manufacturer m
        WHERE LOWER(m.country) LIKE LOWER(CONCAT('%', :country, '%'))
    """)
    List<Watch> findByManufacturerCountry(
            @Param("country") String country
    );
}
