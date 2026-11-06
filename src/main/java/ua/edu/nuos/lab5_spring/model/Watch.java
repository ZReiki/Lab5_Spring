package ua.edu.nuos.lab5_spring.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="watches")
public class Watch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "watch_id", nullable = false, unique = true)
    private Long watchId;

    @Column(name = "brand")
    private String brand;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private double price;

    @Column(name = "case_diameter_mm")
    private double caseDiameter;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "warranty_months")
    private int warrantyMonths;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToMany(mappedBy = "watches")
    private List<Order> orders = new ArrayList<>();
}

