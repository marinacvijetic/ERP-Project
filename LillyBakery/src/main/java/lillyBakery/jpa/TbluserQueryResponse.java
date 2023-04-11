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

	@Column(name="tblcustomer_customer_id", insertable=false, updatable=false)
	private Integer tblcustomerCustomerId;

	@Column(name="tblquery_query_id", insertable=false, updatable=false)
	private Integer tblqueryQueryId;

	@Column(name="tblresponse_response_id", insertable=false, updatable=false)
	private Integer tblresponseResponseId;

	//bi-directional many-to-one association to Tblcustomer
	@ManyToOne
	private Tblcustomer tblcustomer;

	//bi-directional many-to-one association to Tblquery
	@ManyToOne
	private Tblquery tblquery;

	//bi-directional many-to-one association to Tblresponse
	@ManyToOne
	private Tblresponse tblresponse;

	public TbluserQueryResponse() {
	}

	public TbluserQueryResponsePK getId() {
		return this.id;
	}

	public void setId(TbluserQueryResponsePK id) {
		this.id = id;
	}

	public Integer getTblcustomerCustomerId() {
		return this.tblcustomerCustomerId;
	}

	public void setTblcustomerCustomerId(Integer tblcustomerCustomerId) {
		this.tblcustomerCustomerId = tblcustomerCustomerId;
	}

	public Integer getTblqueryQueryId() {
		return this.tblqueryQueryId;
	}

	public void setTblqueryQueryId(Integer tblqueryQueryId) {
		this.tblqueryQueryId = tblqueryQueryId;
	}

	public Integer getTblresponseResponseId() {
		return this.tblresponseResponseId;
	}

	public void setTblresponseResponseId(Integer tblresponseResponseId) {
		this.tblresponseResponseId = tblresponseResponseId;
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