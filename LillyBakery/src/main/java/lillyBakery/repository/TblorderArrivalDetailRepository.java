package lillyBakery.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lillyBakery.jpa.TblorderArrivalDetail;

public interface TblorderArrivalDetailRepository extends JpaRepository<TblorderArrivalDetail, Integer>{
	
	Collection<TblorderArrivalDetail> findByCityContainingIgnoreCase(String city);
	
	@Query(value = "SELECT nextval('order_arrival_details_seq')", nativeQuery = true)
	Integer getNextOrderArrivalDetailId();
}
