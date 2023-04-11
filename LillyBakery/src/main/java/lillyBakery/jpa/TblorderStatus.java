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
 * The persistent class for the tblorder_status database table.
 * 
 */
@Entity
@Table(name="tblorder_status")
@NamedQuery(name="TblorderStatus.findAll", query="SELECT t FROM TblorderStatus t")
public class TblorderStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBLORDER_STATUS_STATUSID_GENERATOR", sequenceName="ORDER_STATUS_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBLORDER_STATUS_STATUSID_GENERATOR")
	@Column(name="status_id")
	private Integer statusId;

	@Column(name="order_status")
	private String orderStatus;

	//bi-directional many-to-one association to Tblorder
	@JsonIgnore
	@OneToMany(mappedBy="tblorderStatus")
	private List<Tblorder> tblorders;

	public TblorderStatus() {
	}

	public Integer getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<Tblorder> getTblorders() {
		return this.tblorders;
	}

	public void setTblorders(List<Tblorder> tblorders) {
		this.tblorders = tblorders;
	}

	public Tblorder addTblorder(Tblorder tblorder) {
		getTblorders().add(tblorder);
		tblorder.setTblorderStatus(this);

		return tblorder;
	}

	public Tblorder removeTblorder(Tblorder tblorder) {
		getTblorders().remove(tblorder);
		tblorder.setTblorderStatus(null);

		return tblorder;
	}

}