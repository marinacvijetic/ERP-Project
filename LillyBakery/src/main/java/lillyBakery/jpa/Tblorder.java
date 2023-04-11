package lillyBakery.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the tblorder database table.
 * 
 */
@Entity
@NamedQuery(name="Tblorder.findAll", query="SELECT t FROM Tblorder t")
public class Tblorder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBLORDER_ORDERID_GENERATOR", sequenceName="ORDER_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBLORDER_ORDERID_GENERATOR")
	@Column(name="order_id")
	private Integer orderId;

	@Temporal(TemporalType.DATE)
	@Column(name="order_date")
	private Date orderDate;

	private BigDecimal total;

	//bi-directional many-to-one association to Tblcustomer
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Tblcustomer tblcustomer;

	//bi-directional many-to-one association to TblorderArrivalDetail
	@ManyToOne
	@JoinColumn(name="arrival_details_id")
	private TblorderArrivalDetail tblorderArrivalDetail;

	//bi-directional many-to-one association to TblorderStatus
	@ManyToOne
	@JoinColumn(name="status_id")
	private TblorderStatus tblorderStatus;

	//bi-directional many-to-one association to TblpaymentMethod
	@ManyToOne
	@JoinColumn(name="payment_method_id")
	private TblpaymentMethod tblpaymentMethod;

	//bi-directional many-to-one association to TblshippingMethod
	@ManyToOne
	@JoinColumn(name="shipping_id")
	private TblshippingMethod tblshippingMethod;

	//bi-directional many-to-one association to TblorderItem
	@JsonIgnore
	@OneToMany(mappedBy="tblorder")
	private List<TblorderItem> tblorderItems;

	public Tblorder() {
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Tblcustomer getTblcustomer() {
		return this.tblcustomer;
	}

	public void setTblcustomer(Tblcustomer tblcustomer) {
		this.tblcustomer = tblcustomer;
	}

	public TblorderArrivalDetail getTblorderArrivalDetail() {
		return this.tblorderArrivalDetail;
	}

	public void setTblorderArrivalDetail(TblorderArrivalDetail tblorderArrivalDetail) {
		this.tblorderArrivalDetail = tblorderArrivalDetail;
	}

	public TblorderStatus getTblorderStatus() {
		return this.tblorderStatus;
	}

	public void setTblorderStatus(TblorderStatus tblorderStatus) {
		this.tblorderStatus = tblorderStatus;
	}

	public TblpaymentMethod getTblpaymentMethod() {
		return this.tblpaymentMethod;
	}

	public void setTblpaymentMethod(TblpaymentMethod tblpaymentMethod) {
		this.tblpaymentMethod = tblpaymentMethod;
	}

	public TblshippingMethod getTblshippingMethod() {
		return this.tblshippingMethod;
	}

	public void setTblshippingMethod(TblshippingMethod tblshippingMethod) {
		this.tblshippingMethod = tblshippingMethod;
	}

	public List<TblorderItem> getTblorderItems() {
		return this.tblorderItems;
	}

	public void setTblorderItems(List<TblorderItem> tblorderItems) {
		this.tblorderItems = tblorderItems;
	}

	public TblorderItem addTblorderItem(TblorderItem tblorderItem) {
		getTblorderItems().add(tblorderItem);
		tblorderItem.setTblorder(this);

		return tblorderItem;
	}

	public TblorderItem removeTblorderItem(TblorderItem tblorderItem) {
		getTblorderItems().remove(tblorderItem);
		tblorderItem.setTblorder(null);

		return tblorderItem;
	}

}