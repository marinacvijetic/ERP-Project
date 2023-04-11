package lillyBakery.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the tblpayment_method database table.
 * 
 */
@Entity
@Table(name="tblpayment_method")
@NamedQuery(name="TblpaymentMethod.findAll", query="SELECT t FROM TblpaymentMethod t")
public class TblpaymentMethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBLPAYMENT_METHOD_PAYMENTMETHODID_GENERATOR", sequenceName="PAYMENT_METHOD_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBLPAYMENT_METHOD_PAYMENTMETHODID_GENERATOR")
	@Column(name="payment_method_id")
	private Integer paymentMethodId;

	@Column(name="payment_method")
	private String paymentMethod;

	//bi-directional many-to-one association to Tblorder
	@JsonIgnore
	@OneToMany(mappedBy="tblpaymentMethod")
	private List<Tblorder> tblorders;

	public TblpaymentMethod() {
	}

	public Integer getPaymentMethodId() {
		return this.paymentMethodId;
	}

	public void setPaymentMethodId(Integer paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<Tblorder> getTblorders() {
		return this.tblorders;
	}

	public void setTblorders(List<Tblorder> tblorders) {
		this.tblorders = tblorders;
	}

	public Tblorder addTblorder(Tblorder tblorder) {
		getTblorders().add(tblorder);
		tblorder.setTblpaymentMethod(this);

		return tblorder;
	}

	public Tblorder removeTblorder(Tblorder tblorder) {
		getTblorders().remove(tblorder);
		tblorder.setTblpaymentMethod(null);

		return tblorder;
	}

}