package template.java17.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import template.java17.domain.enumeration.LanguageCode;
import template.java17.domain.enumeration.Role;
import template.java17.domain.enumeration.TransactionType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity(name = "payment")
@Table(name = "payment")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Payment implements Serializable {

    @Column(columnDefinition = "bigserial")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_sequence")
    @SequenceGenerator(name = "payment_sequence", sequenceName = "payment_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 128)
    private Long r_user_id;

    @NotNull
    @CreationTimestamp
    private ZonedDateTime paymentDate = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(nullable = false)
    @NotNull
    private BigDecimal price;
}
