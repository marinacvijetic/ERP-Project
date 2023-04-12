package lillyBakery.ctrl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lillyBakery.jpa.Tbluser;
import lillyBakery.repository.TbluserRepository;

@RestController
public class TbluserController {
	
	@Autowired
	private TbluserRepository repoUser;
	
	@GetMapping("/user")
	public Collection<Tbluser> getAllUser(){
		return repoUser.findAll();
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping("/user/{email}")
	public Tbluser getUserById(@PathVariable String email) {
		
		return repoUser.getById(email);
	}
	
	@PostMapping("/user")
	public ResponseEntity<Tbluser> createUser(@RequestBody Tbluser user){
		if(user.getUserEmail() != null && repoUser.existsById(user.getUserEmail())) {
			
			return new ResponseEntity<Tbluser>(HttpStatus.CONFLICT);
		}else
		{
			Tbluser temp = repoUser.save(user);
			return new ResponseEntity<Tbluser>(temp, HttpStatus.CREATED);
		}
	}
	
	@PutMapping("/user")
	public ResponseEntity<Tbluser> updateUser(@RequestBody Tbluser user){
		if(repoUser.existsById(user.getUserEmail()))
		{
			repoUser.save(user);
			return new ResponseEntity<Tbluser>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Tbluser>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="user/{email}", produces = "application/json", method = RequestMethod.DELETE)
	public ResponseEntity<Tbluser> deleteUser(@PathVariable String email){
		
		if(repoUser.existsById(email)) {
			repoUser.deleteById(email);
			return new ResponseEntity<Tbluser>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Tbluser>(HttpStatus.NOT_FOUND);
		}
	}

}
