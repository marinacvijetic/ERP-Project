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

import lillyBakery.jpa.TblorderItem;
import lillyBakery.repository.TblorderItemRepository;

@RestController
public class TblorderItemController {

	@Autowired
	private TblorderItemRepository repoOrderItem;
	
	@GetMapping("/orderItem")
	public Collection<TblorderItem> getAllOrderItem(){
		return repoOrderItem.findAll();
	}
	
	@GetMapping("/orderItem/{id}")
	public TblorderItem getOrderItemById(@PathVariable int id)
	{
		return repoOrderItem.getById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/orderItem")
	public ResponseEntity<TblorderItem> createOrderItem(@RequestBody TblorderItem orderItem){
		if(orderItem.getOrderItemId() != null && repoOrderItem.existsById(orderItem.getOrderItemId()))
		{
			return new ResponseEntity<TblorderItem>(HttpStatus.CONFLICT);
		}else
		{
			Integer orderItemId = repoOrderItem.getNextOrderItemId();
			orderItem.setOrderItemId(orderItemId);
			TblorderItem savedOrderItem = repoOrderItem.save(orderItem);
			
			return new ResponseEntity<TblorderItem>(savedOrderItem, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/orderItem")
	public ResponseEntity<TblorderItem> updateOrderItem(@RequestBody TblorderItem orderItem){
		if(repoOrderItem.existsById(orderItem.getOrderItemId()))
		{
			repoOrderItem.save(orderItem);
			return new ResponseEntity<TblorderItem>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<TblorderItem>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/orderItem/{id}")
	public ResponseEntity<TblorderItem> deleteOrderItem(@PathVariable int id){
		if(repoOrderItem.existsById(id))
		{
			repoOrderItem.deleteById(id);
			return new ResponseEntity<TblorderItem>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<TblorderItem>(HttpStatus.NOT_FOUND);
		}
	}
}
