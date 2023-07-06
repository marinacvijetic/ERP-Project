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

import lillyBakery.jpa.TblorderStatus;
import lillyBakery.repository.TblorderStatusRepository;

@RestController
public class TblorderStatusController {
	
	@Autowired
	private TblorderStatusRepository repoStatus;
	
	@GetMapping("/orderStatus")
	public Collection<TblorderStatus> getAllOrderStatus(){
		
		return repoStatus.findAll();
	}
	

	@GetMapping("/orderStatus/{id}")
	public TblorderStatus getOrderStatusById(@PathVariable int id) {
		
		return repoStatus.getById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/orderStatus")
	public ResponseEntity<TblorderStatus> createOrderStatus(@RequestBody TblorderStatus orderStatus){
		if(orderStatus.getStatusId() != null && repoStatus.existsById(orderStatus.getStatusId())) {
			
			return new ResponseEntity<TblorderStatus>(HttpStatus.CONFLICT);
			
		}else
		{
			Integer statusId = repoStatus.getNextOrderStatusId();
			orderStatus.setStatusId(statusId);
			TblorderStatus savedStatus = repoStatus.save(orderStatus);
			
			return new ResponseEntity<TblorderStatus>(savedStatus, HttpStatus.CREATED);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/orderStatus")
	public ResponseEntity<TblorderStatus> updateOrderStatus(@RequestBody TblorderStatus orderStatus){
		if(repoStatus.existsById(orderStatus.getStatusId()))
		{
			repoStatus.save(orderStatus);
			return new ResponseEntity<TblorderStatus>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<TblorderStatus>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "orderStatus/{id}", produces = "application/json", method = RequestMethod.DELETE)
	public ResponseEntity<TblorderStatus> deleteOrderStatus(@PathVariable Integer id){
		if(repoStatus.existsById(id))
		{
			repoStatus.deleteById(id);
			return new ResponseEntity<TblorderStatus>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<TblorderStatus>(HttpStatus.NOT_FOUND);
		}
	}

}
