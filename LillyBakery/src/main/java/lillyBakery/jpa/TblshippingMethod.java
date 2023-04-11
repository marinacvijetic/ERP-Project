package lillyBakery.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * The persistent class for the tblshipping_method database table.
 * 
 */
@Entity
@Table(name="tblshipping_method")
@NamedQuery(name="TblshippingMethod.findAll", query="SELECT t FROM TblshippingMethod t")
public class TblshippingMethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBLSHIPPING_METHOD_SHIPPINGID_GENERATOR", sequenceName="SHIPPING_METHOD_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBLSHIPPING_METHOD_SHIPPINGID_GENERATOR")
	@Column(name="shipping_id")
	private Integer shippingId;

	private BigDecimal price;

	@Column(name="shipping_option")
	private String shippingOption;

	//bi-directional many-to-one association to Tblorder
	@JsonIgnore
	@OneToMany(mappedBy="tblshippingMethod")
	private List<Tblorder> tblorders;

	public TblshippingMethod() {
	}

	public Integer getShippingId() {
		return this.shippingId;
	}

	public void setShippingId(Integer shippingId) {
		this.shippingId = shippingId;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getShippingOption() {
		return this.shippingOption;
	}

	public void setShippingOption(String shippingOption) {
		this.shippingOption = shippingOption;
	}

	public List<Tblorder> getTblorders() {
		return this.tblorders;
	}

	public void setTblorders(List<Tblorder> tblorders) {
		this.tblorders = tblorders;
	}

	public Tblorder addTblorder(Tblorder tblorder) {
		getTblorders().add(tblorder);
		tblorder.setTblshippingMethod(this);

		return tblorder;
	}

	public Tblorder removeTblorder(Tblorder tblorder) {
		getTblorders().remove(tblorder);
		tblorder.setTblshippingMethod(null);

		return tblorder;
	}

}