package lillyBakery.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lillyBakery.jpa.TblpaymentMethod;

public interface TblpaymentMethodRepository extends JpaRepository<TblpaymentMethod, Integer>{
	
	Collection<TblpaymentMethod> findByPaymentMethodContainingIgnoreCase(String paymentMethod);

	@Query(value = "SELECT nextval('payment_method_seq')", nativeQuery = true)
	Integer getNextPaymentMethodId();
}
