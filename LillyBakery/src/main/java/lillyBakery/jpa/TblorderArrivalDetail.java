package lillyBakery.jpa;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the tblorder_arrival_details database table.
 * 
 */
@Entity
@Table(name="tblorder_arrival_details")
@NamedQuery(name="TblorderArrivalDetail.findAll", query="SELECT t FROM TblorderArrivalDetail t")
public class TblorderArrivalDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBLORDER_ARRIVAL_DETAILS_ARRIVALDETAILSID_GENERATOR", sequenceName="ORDER_ARRIVAL_DETAILS_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBLORDER_ARRIVAL_DETAILS_ARRIVALDETAILSID_GENERATOR")
	@Column(name="arrival_details_id")
	private Integer arrivalDetailsId;

	@Temporal(TemporalType.DATE)
	@Column(name="arrival_date")
	private Date arrivalDate;

	@Column(name="arrival_time")
	private Time arrivalTime;

	private String city;

	private String country;

	@Column(name="postal_code")
	private String postalCode;

	@Column(name="street_name")
	private String streetName;

	@Column(name="street_number")
	private String streetNumber;

	//bi-directional many-to-one association to Tblorder
	@JsonIgnore
	@OneToMany(mappedBy="tblorderArrivalDetail")
	private List<Tblorder> tblorders;

	public TblorderArrivalDetail() {
	}

	public Integer getArrivalDetailsId() {
		return this.arrivalDetailsId;
	}

	public void setArrivalDetailsId(Integer arrivalDetailsId) {
		this.arrivalDetailsId = arrivalDetailsId;
	}

	public Date getArrivalDate() {
		return this.arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Time getArrivalTime() {
		return this.arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return this.streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public List<Tblorder> getTblorders() {
		return this.tblorders;
	}

	public void setTblorders(List<Tblorder> tblorders) {
		this.tblorders = tblorders;
	}

	public Tblorder addTblorder(Tblorder tblorder) {
		getTblorders().add(tblorder);
		tblorder.setTblorderArrivalDetail(this);

		return tblorder;
	}

	public Tblorder removeTblorder(Tblorder tblorder) {
		getTblorders().remove(tblorder);
		tblorder.setTblorderArrivalDetail(null);

		return tblorder;
	}

}