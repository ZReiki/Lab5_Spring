package ua.edu.nuos.lab5_spring.model;

import lombok.*;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, unique = true)
    private Long orderId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "items_count")
    private int itemsCount;

    @Column(name = "discount_rate")
    private double discountRate;

    @Column(name = "total_amount")
    private double totalAmount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "order_date")
    private LocalDate orderDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "status")
    private String status;

    @ManyToMany
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "watch_id")
    )
    private List<Watch> watches = new ArrayList<>();
}
