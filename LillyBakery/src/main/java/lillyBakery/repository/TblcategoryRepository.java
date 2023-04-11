package lillyBakery.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lillyBakery.jpa.Tblcategory;

public interface TblcategoryRepository extends JpaRepository<Tblcategory, Integer> {

	Collection<Tblcategory> findByCategoryNameContainingIgnoreCase(String categoryName);
	
    @Query(value = "SELECT nextval('category_seq')", nativeQuery = true)
    Integer getNextCategoryId();
}
