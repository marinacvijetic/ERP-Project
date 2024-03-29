package lillyBakery.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lillyBakery.jpa.Tbladministrator;

public interface TbladministratorRepository extends JpaRepository<Tbladministrator, Integer> {
		
	Collection<Tbladministrator> findByAdminNameContainingIgnoreCase(String adminName);
    @Query(value = "SELECT nextval('administrator_seq')", nativeQuery = true)
    Integer getNextAdminId();

}
