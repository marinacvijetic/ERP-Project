package lillyBakery.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the tblproduct database table.
 * 
 */
@Entity
@NamedQuery(name="Tblproduct.findAll", query="SELECT t FROM Tblproduct t")
public class Tblproduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBLPRODUCT_PRODUCTID_GENERATOR", sequenceName="PRODUCT_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBLPRODUCT_PRODUCTID_GENERATOR")
	@Column(name="product_id")
	private Integer productId;

	private String description;

	@Column(name="price_per_kilogram")
	private BigDecimal pricePerKilogram;

	@Column(name="product_image")
	private String productImage;

	@Column(name="product_name")
	private String productName;

	//bi-directional many-to-one association to TblorderItem
	@JsonIgnore
	@OneToMany(mappedBy="tblproduct")
	private List<TblorderItem> tblorderItems;

	//bi-directional many-to-one association to Tblcategory
	@ManyToOne
	@JoinColumn(name="category_id")
	private Tblcategory tblcategory;

	public Tblproduct() {
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPricePerKilogram() {
		return this.pricePerKilogram;
	}

	public void setPricePerKilogram(BigDecimal pricePerKilogram) {
		this.pricePerKilogram = pricePerKilogram;
	}

	public String getProductImage() {
		return this.productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<TblorderItem> getTblorderItems() {
		return this.tblorderItems;
	}

	public void setTblorderItems(List<TblorderItem> tblorderItems) {
		this.tblorderItems = tblorderItems;
	}

	public TblorderItem addTblorderItem(TblorderItem tblorderItem) {
		getTblorderItems().add(tblorderItem);
		tblorderItem.setTblproduct(this);

		return tblorderItem;
	}

	public TblorderItem removeTblorderItem(TblorderItem tblorderItem) {
		getTblorderItems().remove(tblorderItem);
		tblorderItem.setTblproduct(null);

		return tblorderItem;
	}

	public Tblcategory getTblcategory() {
		return this.tblcategory;
	}

	public void setTblcategory(Tblcategory tblcategory) {
		this.tblcategory = tblcategory;
	}

}