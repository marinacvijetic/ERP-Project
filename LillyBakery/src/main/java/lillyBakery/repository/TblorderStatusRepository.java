package lillyBakery.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lillyBakery.jpa.TblorderStatus;

public interface TblorderStatusRepository extends JpaRepository<TblorderStatus, Integer> {
	
	Collection<TblorderStatus> findByOrderStatusContainingIgnoreCase(String status);

	@Query(value = "SELECT nextval('order_status_seq')", nativeQuery = true)
	Integer getNextOrderStatusId();
}
