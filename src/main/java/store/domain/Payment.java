package store.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import store.domain.enumeration.SubscriptionType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity(name = "payment")
@Table(name = "payment")
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Payment implements Serializable {

    @Column(columnDefinition = "bigserial")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_sequence")
    @SequenceGenerator(name = "payment_sequence", sequenceName = "payment_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "userId")
    private User user;

    @NotNull
    @CreationTimestamp
    private ZonedDateTime paymentDate = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    @Column(nullable = false)
    @NotNull
    private BigDecimal price;
}
