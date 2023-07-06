package lillyBakery.ctrl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lillyBakery.jpa.Tblcustomer;
import lillyBakery.repository.TblcustomerRepository;

@RestController
public class TblcustomerController {
	
	@Autowired
	private TblcustomerRepository repoCustomer;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/customer")
	public Collection<Tblcustomer> getAllCustomer(){
		return repoCustomer.findAll();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/customer/{id}")
	public Tblcustomer getCustomerById(@PathVariable int id) {
		
		return repoCustomer.getById(id);
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/customer")
	public ResponseEntity<Tblcustomer> createCustomer(@RequestBody Tblcustomer customer){
		if(customer.getCustomerId() != null && repoCustomer.existsById(customer.getCustomerId()))
		{
			return new ResponseEntity<Tblcustomer>(HttpStatus.CONFLICT);
		}else
		{
			Integer customerId = repoCustomer.getNextCustomerId();
			customer.setCustomerId(customerId);
			Tblcustomer savedCustomer = repoCustomer.save(customer);
			
			return new ResponseEntity<Tblcustomer>(savedCustomer, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasRole('USER')")
	@PutMapping("/customer")
	public ResponseEntity<Tblcustomer> updateCustomer(@RequestBody Tblcustomer customer){
		if(repoCustomer.existsById(customer.getCustomerId()))
		{
			repoCustomer.save(customer);
			return new ResponseEntity<Tblcustomer>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Tblcustomer>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "customer/{id}", produces = "application/json", method = RequestMethod.DELETE)
	public ResponseEntity<Tblcustomer> deleteCustomer(@PathVariable Integer id){
		
		if(repoCustomer.existsById(id))
		{
			repoCustomer.deleteById(id);
			return new ResponseEntity<Tblcustomer>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Tblcustomer>(HttpStatus.NOT_FOUND);
		}
	}


}
