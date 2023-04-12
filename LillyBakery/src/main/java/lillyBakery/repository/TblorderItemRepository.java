package lillyBakery.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lillyBakery.jpa.TblorderItem;

public interface TblorderItemRepository extends JpaRepository<TblorderItem, Integer> {
	Collection<TblorderItem> findByQuantityContainingIgnoreCase(Integer quantity);

	@Query(value = "SELECT nextval('order_item_seq')", nativeQuery = true)
	Integer getNextOrderItemId();

}
