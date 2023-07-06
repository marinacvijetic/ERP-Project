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

import lillyBakery.jpa.TblpaymentMethod;
import lillyBakery.repository.TblpaymentMethodRepository;

@RestController
public class TblpaymentMethodController {
	
	@Autowired
	private TblpaymentMethodRepository repoPayment;
	
	@GetMapping("/paymentMethod")
	public Collection<TblpaymentMethod> getAllPaymentMethod(){
		return repoPayment.findAll();
	}
	
	@GetMapping("/paymentMethod/{id}")
	public TblpaymentMethod getPaymentMethodById(@PathVariable int id) {
		return repoPayment.getById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/paymentMethod")
	public ResponseEntity<TblpaymentMethod> createPaymentMethod (@RequestBody TblpaymentMethod payment){
		if(payment.getPaymentMethodId() != null && repoPayment.existsById(payment.getPaymentMethodId())) {
			
			return new ResponseEntity<TblpaymentMethod>(HttpStatus.CONFLICT);
		}else
		{
			Integer paymentId = repoPayment.getNextPaymentMethodId();
			payment.setPaymentMethodId(paymentId);
			TblpaymentMethod savedPayment = repoPayment.save(payment);
			
			return new ResponseEntity<TblpaymentMethod>(savedPayment, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/paymentMethod")
	public ResponseEntity<TblpaymentMethod> updatePaymentMethod(@RequestBody TblpaymentMethod payment){
		if(repoPayment.existsById(payment.getPaymentMethodId()))
		{
			repoPayment.save(payment);
			return new ResponseEntity<TblpaymentMethod>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<TblpaymentMethod>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/paymentMethod/{id}")
	public ResponseEntity<TblpaymentMethod> deletePaymentMethod(@PathVariable int id){
		if(repoPayment.existsById(id))
		{
			repoPayment.deleteById(id);
			return new ResponseEntity<TblpaymentMethod>(HttpStatus.OK);
		}else
		{
			return new ResponseEntity<TblpaymentMethod>(HttpStatus.NOT_FOUND);
		}
	}

}
