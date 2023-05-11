package lillyBakery.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lillyBakery.jpa.Tblresponse;

public interface TblresponseRepository extends JpaRepository<Tblresponse, Integer> {
	
	Collection<Tblresponse> findByResponseId(int responseId);
	
	@Query(value = "SELECT nextval('response_seq')", nativeQuery = true)
	Integer getNextResponseId();

}
