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

import lillyBakery.jpa.Tblproduct;
import lillyBakery.repository.TblproductRepository;

@RestController
public class TblproductController {
	
	@Autowired
	private TblproductRepository repoProduct;
	
	@GetMapping("/product")
	public Collection<Tblproduct> getAllProduct(){
		return repoProduct.findAll();
	}
	
	@GetMapping("/product/{id}")
	public Tblproduct getProductById(@PathVariable int id) {
		
		return repoProduct.getById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/product")
	public ResponseEntity<Tblproduct> createProduct(@RequestBody Tblproduct product){
		if(product.getProductId() != null && repoProduct.existsById(product.getProductId()))
		{
			return new ResponseEntity<Tblproduct>(HttpStatus.CONFLICT);
		}else
		{
			Integer productId = repoProduct.getNextProductId();
			product.setProductId(productId);
			Tblproduct savedProduct = repoProduct.save(product);
			
			return new ResponseEntity<Tblproduct>(savedProduct, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/product")
	public ResponseEntity<Tblproduct> updateProduct(@RequestBody Tblproduct product){
		if(repoProduct.existsById(product.getProductId()))
		{
			repoProduct.save(product);
			return new ResponseEntity<Tblproduct>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Tblproduct>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Tblproduct> deleteProduct(@PathVariable int id)
	{
		if(repoProduct.existsById(id))
		{
			repoProduct.deleteById(id);
			return new ResponseEntity<Tblproduct>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Tblproduct>(HttpStatus.NOT_FOUND);
		}
	}

}
