package lillyBakery.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lillyBakery.jpa.Tblorder;

public interface TblorderRepository extends JpaRepository<Tblorder, Integer> {
	
	Collection<Tblorder> findByOrderId(int orderId);

	@Query(value = "SELECT nextval('order_seq')", nativeQuery = true)
	Integer getNextOrderId();
}
