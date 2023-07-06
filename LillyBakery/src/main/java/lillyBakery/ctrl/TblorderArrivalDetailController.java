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

import lillyBakery.jpa.TblorderArrivalDetail;
import lillyBakery.repository.TblorderArrivalDetailRepository;

@RestController
public class TblorderArrivalDetailController {
	
	@Autowired
	private TblorderArrivalDetailRepository repoArrivalDetail;
	
	@GetMapping("/orderArrival")
	public Collection<TblorderArrivalDetail> getAllOrderArrivalDetail(){
		return repoArrivalDetail.findAll();
	}
	
	@GetMapping("/orderArrival/{id}")
	public TblorderArrivalDetail getOrderArrivalDetailById(@PathVariable int id) {
		return repoArrivalDetail.getById(id);
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/orderArrival")
	public ResponseEntity<TblorderArrivalDetail> createOrderArrivalDetail(@RequestBody TblorderArrivalDetail orderArrival){
		if(orderArrival.getArrivalDetailsId() != null && repoArrivalDetail.existsById(orderArrival.getArrivalDetailsId()))
		{
			return new ResponseEntity<TblorderArrivalDetail>(HttpStatus.CONFLICT);
		}else
		{
			Integer arrivalId = repoArrivalDetail.getNextOrderArrivalDetailId();
			orderArrival.setArrivalDetailsId(arrivalId);
			TblorderArrivalDetail savedOrderArrival = repoArrivalDetail.save(orderArrival);
			
			return new ResponseEntity<TblorderArrivalDetail>(savedOrderArrival, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasRole('USER')")
	@PutMapping("/orderArrival")
	public ResponseEntity<TblorderArrivalDetail> updateOrderArrivalDetail(@RequestBody TblorderArrivalDetail orderArrivalDetail){
		if(repoArrivalDetail.existsById(orderArrivalDetail.getArrivalDetailsId())) {
			repoArrivalDetail.save(orderArrivalDetail);
			return new ResponseEntity<TblorderArrivalDetail>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<TblorderArrivalDetail>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/orderArrival/{id}")
	public ResponseEntity<TblorderArrivalDetail> deleteOrderArrivalDetail(@PathVariable int id)
	{
		if(repoArrivalDetail.existsById(id))
		{
			repoArrivalDetail.deleteById(id);
			return new ResponseEntity<TblorderArrivalDetail>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<TblorderArrivalDetail>(HttpStatus.NOT_FOUND);
		}
	}
}
