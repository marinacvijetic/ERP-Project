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

import lillyBakery.jpa.Tblcategory;
import lillyBakery.repository.TblcategoryRepository;

@RestController
public class TblcategoryController {
	
	@Autowired
	private TblcategoryRepository repoCategory;
	
	@GetMapping("/category")
	public Collection<Tblcategory> getAllCategory(){
		
		return repoCategory.findAll();
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping("/category/{id}")
	public Tblcategory getCategoryById(@PathVariable int id){
		
		return repoCategory.getOne(id);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/category")
	public ResponseEntity<Tblcategory> createCategory(@RequestBody Tblcategory category){
	    if (category.getCategoryId() != null && repoCategory.existsById(category.getCategoryId())) {
	        return new ResponseEntity<Tblcategory>(HttpStatus.CONFLICT);
	    }else
	    {
	        Integer categoryId = repoCategory.getNextCategoryId();
	        category.setCategoryId(categoryId);
	        Tblcategory savedCategory = repoCategory.save(category);

	        return new ResponseEntity<Tblcategory>(savedCategory, HttpStatus.CREATED);
	    }
	    
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/category")
	public ResponseEntity<Tblcategory> updateCategory(@RequestBody Tblcategory category){
		if(repoCategory.existsById(category.getCategoryId()))
		{
			repoCategory.save(category);
			return new ResponseEntity<Tblcategory>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Tblcategory>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "category/{id}", produces = "application/json", method = RequestMethod.DELETE)
	public ResponseEntity<Tblcategory> deleteCategory(@PathVariable Integer id){
		
		if(repoCategory.existsById(id))
		{
			repoCategory.deleteById(id);
			return new ResponseEntity<Tblcategory>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<Tblcategory>(HttpStatus.NOT_FOUND);
		}
	}

}
