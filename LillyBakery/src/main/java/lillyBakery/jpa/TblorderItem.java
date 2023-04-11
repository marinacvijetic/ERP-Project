package lillyBakery.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tblorder_item database table.
 * 
 */
@Entity
@Table(name="tblorder_item")
@NamedQuery(name="TblorderItem.findAll", query="SELECT t FROM TblorderItem t")
public class TblorderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBLORDER_ITEM_ORDERITEMID_GENERATOR", sequenceName="ORDER_ITEM_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBLORDER_ITEM_ORDERITEMID_GENERATOR")
	@Column(name="order_item_id")
	private Integer orderItemId;

	private Integer quantity;

	//bi-directional many-to-one association to Tblorder
	@ManyToOne
	@JoinColumn(name="order_id")
	private Tblorder tblorder;

	//bi-directional many-to-one association to Tblproduct
	@ManyToOne
	@JoinColumn(name="product_id")
	private Tblproduct tblproduct;

	public TblorderItem() {
	}

	public Integer getOrderItemId() {
		return this.orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Tblorder getTblorder() {
		return this.tblorder;
	}

	public void setTblorder(Tblorder tblorder) {
		this.tblorder = tblorder;
	}

	public Tblproduct getTblproduct() {
		return this.tblproduct;
	}

	public void setTblproduct(Tblproduct tblproduct) {
		this.tblproduct = tblproduct;
	}

}