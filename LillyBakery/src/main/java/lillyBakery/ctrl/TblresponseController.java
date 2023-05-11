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

import lillyBakery.jpa.Tblresponse;
import lillyBakery.repository.TblresponseRepository;

@RestController
public class TblresponseController {

	@Autowired
	private TblresponseRepository repoResponse;
	
	@GetMapping("/response")
	public Collection<Tblresponse> getAllResponse(){
		return repoResponse.findAll();
	}
	
	@GetMapping("/response/{id}")
	public Tblresponse getResponseById(@PathVariable int id) {
		return repoResponse.getById(id);
	}
	
	@PostMapping("/response")
	public ResponseEntity<Tblresponse> createResponse (@RequestBody Tblresponse response){
		if(response.getResponseId() != null && repoResponse.existsById(response.getResponseId()))
		{
			return new ResponseEntity<Tblresponse>(HttpStatus.CONFLICT);
		}else
		{
			Integer responseId = repoResponse.getNextResponseId();
			response.setResponseId(responseId);
			Tblresponse savedResponse = repoResponse.save(response);
			
			return new ResponseEntity<Tblresponse>(savedResponse, HttpStatus.OK);
		}
	}
	
	@PutMapping("/response")
	public ResponseEntity<Tblresponse> updateResponse(@RequestBody Tblresponse response){
		if(repoResponse.existsById(response.getResponseId()))
		{
			repoResponse.save(response);
			return new ResponseEntity<Tblresponse>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Tblresponse>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/response/{id}")
	public ResponseEntity<Tblresponse> deleteResponse(@PathVariable int id){
		if(repoResponse.existsById(id))
		{
			repoResponse.deleteById(id);
			return new ResponseEntity<Tblresponse>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Tblresponse>(HttpStatus.NOT_FOUND);
		}
	}
}
