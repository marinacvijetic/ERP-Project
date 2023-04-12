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

import lillyBakery.jpa.TblorderItem;
import lillyBakery.repository.TblorderItemRepository;

@RestController
public class TblorderItemController {
	
	@Autowired
	private TblorderItemRepository repoItem;
	
	@GetMapping("/orderItem")
	public Collection<TblorderItem> getAllOrderItem(){
		
		return repoItem.findAll();
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping("/orderItem/{id}")
	public TblorderItem getOrderItemById(@PathVariable int id) {
		
		return repoItem.getById(id);
	}
	
	@PostMapping("/orderItem")
	public ResponseEntity<TblorderItem> createOrderItem(@RequestBody TblorderItem orderItem){
		if(orderItem.getOrderItemId() != null && repoItem.existsById(orderItem.getOrderItemId())) {
			
			return new ResponseEntity<TblorderItem>(HttpStatus.CONFLICT);
			
		}else
		{
			Integer itemId = repoItem.getNextOrderItemId();
			orderItem.setOrderItemId(itemId);
			TblorderItem savedItem = repoItem.save(orderItem);
			
			return new ResponseEntity<TblorderItem>(savedItem, HttpStatus.CREATED);
		}
	}
	
	@PutMapping("/orderItem")
	public ResponseEntity<TblorderItem> updateorderItem(@RequestBody TblorderItem orderItem){
		if(repoItem.existsById(orderItem.getOrderItemId()))
		{
			repoItem.save(orderItem);
			return new ResponseEntity<TblorderItem>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<TblorderItem>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "orderItem/{id}", produces = "application/json", method = RequestMethod.DELETE)
	public ResponseEntity<TblorderItem> deleteorderItem(@PathVariable Integer id){
		if(repoItem.existsById(id))
		{
			repoItem.deleteById(id);
			return new ResponseEntity<TblorderItem>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<TblorderItem>(HttpStatus.NOT_FOUND);
		}
	}

}
