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

import lillyBakery.jpa.Tblquery;
import lillyBakery.repository.TblqueryRepository;

@RestController
public class TblqueryController {

	@Autowired 
	private TblqueryRepository repoQuery;
	
	@GetMapping("/query")
	public Collection<Tblquery> getAllQuery(){
		return repoQuery.findAll();
	}
	
	@GetMapping("/query/{id}")
	public Tblquery getQueryById(@PathVariable Integer id){
		
		return repoQuery.getById(id);
	}
	
	@PostMapping("/query")
	public ResponseEntity<Tblquery> createQuery(@RequestBody Tblquery query)
	{
		if(query.getQueryId() != null && repoQuery.existsById(query.getQueryId())) {
			
			return new ResponseEntity<Tblquery>(HttpStatus.CONFLICT);
		}else
		{
			Integer queryId = repoQuery.getNextQueryId();
			query.setQueryId(queryId);
			Tblquery savedQuery = repoQuery.save(query);
			
			return new ResponseEntity<Tblquery>(savedQuery, HttpStatus.OK);
		}
	}
	
	@PutMapping("/query")
	public ResponseEntity<Tblquery> updateQuery(@RequestBody Tblquery query)
	{
		if(repoQuery.existsById(query.getQueryId()))
		{
			repoQuery.save(query);
			return new ResponseEntity<Tblquery>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Tblquery>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/query/{id}")
	public ResponseEntity<Tblquery> deleteQuery(@PathVariable int id){
		if(repoQuery.existsById(id))
		{
			repoQuery.deleteById(id);
			return new ResponseEntity<Tblquery>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Tblquery>(HttpStatus.NOT_FOUND);
		}
	}
}
