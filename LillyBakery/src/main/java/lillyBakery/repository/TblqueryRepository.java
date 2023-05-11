package lillyBakery.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lillyBakery.jpa.Tblquery;

public interface TblqueryRepository extends JpaRepository<Tblquery, Integer> {
	
	Collection<Tblquery> findByQueryId(Integer queryId);
	
	@Query(value = "SELECT nextval('query_seq')", nativeQuery = true)
	Integer getNextQueryId();

}
