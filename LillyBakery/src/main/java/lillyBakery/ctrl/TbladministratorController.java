package lillyBakery.ctrl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lillyBakery.jpa.Tbladministrator;
import lillyBakery.repository.TbladministratorRepository;

@RestController
public class TbladministratorController {
	
	@Autowired
	private TbladministratorRepository repoAdmin;

	
	@GetMapping("/admin")
	public Collection<Tbladministrator> getAllAdministrator (){
		
		return repoAdmin.findAll();
	}
	
	@SuppressWarnings({ "deprecation" })
	@GetMapping("/admin/{id}")
	public Tbladministrator getAdministratorById(@PathVariable int id) {
		
		return repoAdmin.getOne(id);
	}
	
	@GetMapping("/admin/name/{adminName}")
	public Collection<Tbladministrator> getAdministratorByName(@PathVariable String adminName ) {
		
		return repoAdmin.findByAdminNameContainingIgnoreCase(adminName);
	}
	
	@PostMapping("/admin")
	public ResponseEntity<Tbladministrator> createAdministrator(@RequestBody Tbladministrator admin) {
	    if (admin.getAdminId() != null && repoAdmin.existsById(admin.getAdminId())) {
	        return new ResponseEntity<Tbladministrator>(HttpStatus.CONFLICT);
	    }else
	    {
	        Integer adminId = repoAdmin.getNextAdminId();
	        admin.setAdminId(adminId);
	        Tbladministrator savedAdmin = repoAdmin.save(admin);

	        return new ResponseEntity<Tbladministrator>(savedAdmin, HttpStatus.CREATED);
	    }
	}
	
	@PutMapping("/admin")
	public ResponseEntity<Tbladministrator> updateAdministrator(@RequestBody Tbladministrator admin){
		if(repoAdmin.existsById(admin.getAdminId()))
		{
			repoAdmin.save(admin);
			return new ResponseEntity<Tbladministrator>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Tbladministrator>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/admin/{id}")
	public ResponseEntity<Tbladministrator> deleteAdministrator(@PathVariable Integer id){
		
		if(repoAdmin.existsById(id))
		{
			repoAdmin.deleteById(id);
			return new ResponseEntity<Tbladministrator>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Tbladministrator>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}
