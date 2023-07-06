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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lillyBakery.jpa.Tbluser;
import lillyBakery.registration.RegistrationRequest;
import lillyBakery.repository.TbluserRepository;
import lillyBakery.services.TbluserService;

@RestController
public class TbluserController{
	
	@Autowired
	private TbluserRepository repoUser;
	
	@Autowired
	private TbluserService userService;

	

	@GetMapping("/user")
	public Collection<Tbluser> getAllUser(){
		return repoUser.findAll();
	}

	@PostMapping("/api/registration")
	public String register(@RequestBody RegistrationRequest request) {
		
		return userService.register(request);
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
	

    @GetMapping(path = "/api/registration/confirm")
    public String confirm(@RequestParam("token") String token) {
        return userService.confirmToken(token);
    }
	



	

	
	

}
