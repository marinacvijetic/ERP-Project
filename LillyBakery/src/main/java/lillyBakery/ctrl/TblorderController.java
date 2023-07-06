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

import lillyBakery.jpa.Tblorder;
import lillyBakery.repository.TblorderRepository;

@RestController
public class TblorderController {
	
	@Autowired
	private TblorderRepository repoOrder;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/order")
	public Collection<Tblorder> getAllOrder(){
		
		return repoOrder.findAll();
	}
	
	@GetMapping("/order/{id}")
	public Tblorder getOrderById(@PathVariable int id) {
		return repoOrder.getById(id);
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/order")
	public ResponseEntity<Tblorder> createOrder (@RequestBody Tblorder order){
		if(order.getOrderId() != null && repoOrder.existsById(order.getOrderId()))
		{
			return new ResponseEntity<Tblorder>(HttpStatus.CONFLICT);
		}else
		{
			Integer orderId = repoOrder.getNextOrderId();
			order.setOrderId(orderId);
			Tblorder savedOrder = repoOrder.save(order);
			
			return new ResponseEntity<Tblorder>(savedOrder, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasRole('USER')")
	@PutMapping("/order")
	public ResponseEntity<Tblorder> updateOrder(@RequestBody Tblorder order){
		if(repoOrder.existsById(order.getOrderId()))
		{
			repoOrder.save(order);
			return new ResponseEntity<Tblorder>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Tblorder>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/order/{id}")
	public ResponseEntity<Tblorder> deleteOrder(@PathVariable int id){
		if(repoOrder.existsById(id))
		{
			repoOrder.deleteById(id);
			return new ResponseEntity<Tblorder>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Tblorder>(HttpStatus.NOT_FOUND);
		}
	}

}
