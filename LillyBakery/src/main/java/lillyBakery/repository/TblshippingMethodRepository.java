package lillyBakery.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lillyBakery.jpa.TblshippingMethod;

public interface TblshippingMethodRepository extends JpaRepository<TblshippingMethod, Integer>{
	
	Collection<TblshippingMethod> findByShippingOptionContainingIgnoreCase(String shippingOption);
	
	@Query(value = "SELECT nextval('shipping_method_seq')", nativeQuery = true)
	Integer getNextShippingMethodId();

}
