package lillyBakery.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbluser_query_response database table.
 * 
 */
@Entity
@Table(name="tbluser_query_response")
@NamedQuery(name="TbluserQueryResponse.findAll", query="SELECT t FROM TbluserQueryResponse t")
public class TbluserQueryResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TbluserQueryResponsePK id;

	// Define the relationships without the additional attributes
	@ManyToOne
	@JoinColumn(name="query_id", referencedColumnName="query_id", insertable=false, updatable=false)
	private Tblquery tblquery;

	@ManyToOne
	@JoinColumn(name="customer_id", referencedColumnName="customer_id", insertable=false, updatable=false)
	private Tblcustomer tblcustomer;

	@ManyToOne
	@JoinColumn(name="response_id", referencedColumnName="response_id", insertable=false, updatable=false)
	private Tblresponse tblresponse;

	public TbluserQueryResponse() {
	}

	public TbluserQueryResponsePK getId() {
		return this.id;
	}

	public void setId(TbluserQueryResponsePK id) {
		this.id = id;
	}


	public Tblcustomer getTblcustomer() {
		return this.tblcustomer;
	}

	public void setTblcustomer(Tblcustomer tblcustomer) {
		this.tblcustomer = tblcustomer;
	}

	public Tblquery getTblquery() {
		return this.tblquery;
	}

	public void setTblquery(Tblquery tblquery) {
		this.tblquery = tblquery;
	}

	public Tblresponse getTblresponse() {
		return this.tblresponse;
	}

	public void setTblresponse(Tblresponse tblresponse) {
		this.tblresponse = tblresponse;
	}

}