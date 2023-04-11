package lillyBakery.jpa;

import java.io.Serializable;
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
 * The persistent class for the tblcustomer database table.
 * 
 */
@Entity
@NamedQuery(name="Tblcustomer.findAll", query="SELECT t FROM Tblcustomer t")
public class Tblcustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBLCUSTOMER_CUSTOMERID_GENERATOR", sequenceName="CUSTOMER_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBLCUSTOMER_CUSTOMERID_GENERATOR")
	@Column(name="customer_id")
	private Integer customerId;

	@Column(name="_name")
	private String name;

	@Column(name="number_of_orders")
	private Integer numberOfOrders;

	@Column(name="phone_number")
	private String phoneNumber;

	private String surname;

	//bi-directional many-to-one association to Tbluser
	@ManyToOne
	@JoinColumn(name="user_email")
	private Tbluser tbluser;

	//bi-directional many-to-one association to Tblorder
	@JsonIgnore
	@OneToMany(mappedBy="tblcustomer")
	private List<Tblorder> tblorders;

	//bi-directional many-to-one association to Tblquery
	@JsonIgnore
	@OneToMany(mappedBy="tblcustomer")
	private List<Tblquery> tblqueries;

	//bi-directional many-to-one association to TbluserQueryResponse
	@JsonIgnore
	@OneToMany(mappedBy="tblcustomer")
	private List<TbluserQueryResponse> tbluserQueryResponses;

	public Tblcustomer() {
	}

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberOfOrders() {
		return this.numberOfOrders;
	}

	public void setNumberOfOrders(Integer numberOfOrders) {
		this.numberOfOrders = numberOfOrders;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Tbluser getTbluser() {
		return this.tbluser;
	}

	public void setTbluser(Tbluser tbluser) {
		this.tbluser = tbluser;
	}

	public List<Tblorder> getTblorders() {
		return this.tblorders;
	}

	public void setTblorders(List<Tblorder> tblorders) {
		this.tblorders = tblorders;
	}

	public Tblorder addTblorder(Tblorder tblorder) {
		getTblorders().add(tblorder);
		tblorder.setTblcustomer(this);

		return tblorder;
	}

	public Tblorder removeTblorder(Tblorder tblorder) {
		getTblorders().remove(tblorder);
		tblorder.setTblcustomer(null);

		return tblorder;
	}

	public List<Tblquery> getTblqueries() {
		return this.tblqueries;
	}

	public void setTblqueries(List<Tblquery> tblqueries) {
		this.tblqueries = tblqueries;
	}

	public Tblquery addTblquery(Tblquery tblquery) {
		getTblqueries().add(tblquery);
		tblquery.setTblcustomer(this);

		return tblquery;
	}

	public Tblquery removeTblquery(Tblquery tblquery) {
		getTblqueries().remove(tblquery);
		tblquery.setTblcustomer(null);

		return tblquery;
	}

	public List<TbluserQueryResponse> getTbluserQueryResponses() {
		return this.tbluserQueryResponses;
	}

	public void setTbluserQueryResponses(List<TbluserQueryResponse> tbluserQueryResponses) {
		this.tbluserQueryResponses = tbluserQueryResponses;
	}

	public TbluserQueryResponse addTbluserQueryRespons(TbluserQueryResponse tbluserQueryRespons) {
		getTbluserQueryResponses().add(tbluserQueryRespons);
		tbluserQueryRespons.setTblcustomer(this);

		return tbluserQueryRespons;
	}

	public TbluserQueryResponse removeTbluserQueryRespons(TbluserQueryResponse tbluserQueryRespons) {
		getTbluserQueryResponses().remove(tbluserQueryRespons);
		tbluserQueryRespons.setTblcustomer(null);

		return tbluserQueryRespons;
	}

}