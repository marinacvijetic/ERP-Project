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

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the tblcategory database table.
 * 
 */
@Entity
@NamedQuery(name="Tblcategory.findAll", query="SELECT t FROM Tblcategory t")
public class Tblcategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBLCATEGORY_CATEGORYID_GENERATOR", sequenceName="CATEGORY_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBLCATEGORY_CATEGORYID_GENERATOR")
	@Column(name="category_id")
	private Integer categoryId;

	@Column(name="category_name")
	private String categoryName;

	//bi-directional many-to-one association to Tblproduct
	@JsonIgnore
	@OneToMany(mappedBy="tblcategory")
	private List<Tblproduct> tblproducts;

	public Tblcategory() {
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Tblproduct> getTblproducts() {
		return this.tblproducts;
	}

	public void setTblproducts(List<Tblproduct> tblproducts) {
		this.tblproducts = tblproducts;
	}

	public Tblproduct addTblproduct(Tblproduct tblproduct) {
		getTblproducts().add(tblproduct);
		tblproduct.setTblcategory(this);

		return tblproduct;
	}

	public Tblproduct removeTblproduct(Tblproduct tblproduct) {
		getTblproducts().remove(tblproduct);
		tblproduct.setTblcategory(null);

		return tblproduct;
	}

}