package lillyBakery.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lillyBakery.jpa.Tblproduct;

public interface TblproductRepository extends JpaRepository<Tblproduct, Integer> {
	
	Collection<Tblproduct> findByProductNameContainingIgnoreCase(String productName);
	
	@Query(value = "SELECT nextval('product_seq')", nativeQuery = true)
	Integer getNextProductId();

}
