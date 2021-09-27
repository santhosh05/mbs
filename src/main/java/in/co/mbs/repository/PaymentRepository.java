package in.co.mbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.co.mbs.model.Payment;

/**
 * 
 * @author santhosh
 *
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}