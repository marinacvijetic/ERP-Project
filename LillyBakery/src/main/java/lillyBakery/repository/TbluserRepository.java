package lillyBakery.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import lillyBakery.jpa.Tbluser;

public interface TbluserRepository extends JpaRepository<Tbluser, String>{

	Collection<Tbluser> findByUsernameContainingIgnoreCase(String username);

}
