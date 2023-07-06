package lillyBakery.ctrl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lillyBakery.jpa.TblshippingMethod;
import lillyBakery.repository.TblshippingMethodRepository;

@RestController
public class TblshippingMethodController {
	
	@Autowired
	private TblshippingMethodRepository repoShipping;
	
	@GetMapping("/shipping")
	public Collection<TblshippingMethod> getAllShippingMethod(){
		return repoShipping.findAll();
	}
	

	@GetMapping("/shipping/{id}")
	public TblshippingMethod getShippingMethodById(@PathVariable int id) {
		
		return repoShipping.getById(id);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/shipping")
	public ResponseEntity<TblshippingMethod> createShippingMethod (@RequestBody TblshippingMethod shipping){
		if(shipping.getShippingId() != null && repoShipping.existsById(shipping.getShippingId())) {
			
			return new ResponseEntity<TblshippingMethod>(HttpStatus.CONFLICT);
		}else
		{
			Integer shippingId = repoShipping.getNextShippingMethodId();
			shipping.setShippingId(shippingId);
			TblshippingMethod savedShipping = repoShipping.save(shipping);
			
			return new ResponseEntity<TblshippingMethod>(savedShipping, HttpStatus.OK); 
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/shipping")
	public ResponseEntity<TblshippingMethod> updateShippingMethod(@RequestBody TblshippingMethod shipping){
		if(repoShipping.existsById(shipping.getShippingId()))
		{
			repoShipping.save(shipping);
			return new ResponseEntity<TblshippingMethod>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<TblshippingMethod>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/shipping/{id}")
	public ResponseEntity<TblshippingMethod> deleteShippingMethod(@PathVariable Integer id){
		if(repoShipping.existsById(id))
		{
			repoShipping.deleteById(id);
			return new ResponseEntity<TblshippingMethod>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<TblshippingMethod>(HttpStatus.NOT_FOUND);
		}
	}

}
