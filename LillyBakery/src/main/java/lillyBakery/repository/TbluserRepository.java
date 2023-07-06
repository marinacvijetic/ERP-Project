package lillyBakery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import lillyBakery.jpa.Tbluser;

@Transactional(readOnly = true)
public interface TbluserRepository extends JpaRepository<Tbluser, String>{

	Optional<Tbluser> findByUserEmail(String userEmail);
	
    @Transactional
    @Modifying
    @Query("UPDATE Tbluser a " +
            "SET a.enabled = TRUE WHERE a.userEmail = ?1")
    int enableTbluser(String email);

}
