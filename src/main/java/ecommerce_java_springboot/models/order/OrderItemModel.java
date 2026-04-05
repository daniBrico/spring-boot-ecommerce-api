package ecommerce_java_springboot.models.order;

import ecommerce_java_springboot.models.product.ProductModel;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderModel order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductModel product;

    @Column(nullable = false)
    private Integer units;

    @Column(nullable = false, precision =  10, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false, precision =  10, scale = 2)
    private BigDecimal subtotal;
}
