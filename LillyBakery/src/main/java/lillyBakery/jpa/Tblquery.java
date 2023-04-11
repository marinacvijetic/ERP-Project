package lillyBakery.jpa;

import java.io.Serializable;
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
 * The persistent class for the tblquery database table.
 * 
 */
@Entity
@NamedQuery(name="Tblquery.findAll", query="SELECT t FROM Tblquery t")
public class Tblquery implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBLQUERY_QUERYID_GENERATOR", sequenceName="QUERY_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBLQUERY_QUERYID_GENERATOR")
	@Column(name="query_id")
	private Integer queryId;

	@Temporal(TemporalType.DATE)
	@Column(name="_date")
	private Date date;

	@Column(name="_query")
	private String query;

	//bi-directional many-to-one association to Tblcustomer
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Tblcustomer tblcustomer;

	//bi-directional many-to-one association to Tblresponse
	@JsonIgnore
	@OneToMany(mappedBy="tblquery")
	private List<Tblresponse> tblresponses;

	//bi-directional many-to-one association to TbluserQueryResponse
	@JsonIgnore
	@OneToMany(mappedBy="tblquery")
	private List<TbluserQueryResponse> tbluserQueryResponses;

	public Tblquery() {
	}

	public Integer getQueryId() {
		return this.queryId;
	}

	public void setQueryId(Integer queryId) {
		this.queryId = queryId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getQuery() {
		return this.query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Tblcustomer getTblcustomer() {
		return this.tblcustomer;
	}

	public void setTblcustomer(Tblcustomer tblcustomer) {
		this.tblcustomer = tblcustomer;
	}

	public List<Tblresponse> getTblresponses() {
		return this.tblresponses;
	}

	public void setTblresponses(List<Tblresponse> tblresponses) {
		this.tblresponses = tblresponses;
	}

	public Tblresponse addTblrespons(Tblresponse tblrespons) {
		getTblresponses().add(tblrespons);
		tblrespons.setTblquery(this);

		return tblrespons;
	}

	public Tblresponse removeTblrespons(Tblresponse tblrespons) {
		getTblresponses().remove(tblrespons);
		tblrespons.setTblquery(null);

		return tblrespons;
	}

	public List<TbluserQueryResponse> getTbluserQueryResponses() {
		return this.tbluserQueryResponses;
	}

	public void setTbluserQueryResponses(List<TbluserQueryResponse> tbluserQueryResponses) {
		this.tbluserQueryResponses = tbluserQueryResponses;
	}

	public TbluserQueryResponse addTbluserQueryRespons(TbluserQueryResponse tbluserQueryRespons) {
		getTbluserQueryResponses().add(tbluserQueryRespons);
		tbluserQueryRespons.setTblquery(this);

		return tbluserQueryRespons;
	}

	public TbluserQueryResponse removeTbluserQueryRespons(TbluserQueryResponse tbluserQueryRespons) {
		getTbluserQueryResponses().remove(tbluserQueryRespons);
		tbluserQueryRespons.setTblquery(null);

		return tbluserQueryRespons;
	}

}