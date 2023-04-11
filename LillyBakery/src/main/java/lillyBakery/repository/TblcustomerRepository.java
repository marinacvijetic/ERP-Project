package lillyBakery.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lillyBakery.jpa.Tblcustomer;

public interface TblcustomerRepository extends JpaRepository<Tblcustomer, Integer> {
	
	Collection<Tblcustomer> findByNameContainingIgnoreCase(String customerName);
	
	@Query(value = "SELECT nextval('customer_seq')", nativeQuery = true)
	Integer getNextCustomerId();

}
