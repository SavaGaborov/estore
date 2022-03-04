package store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
