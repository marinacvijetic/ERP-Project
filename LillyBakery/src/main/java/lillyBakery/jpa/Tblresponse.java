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
 * The persistent class for the tblresponse database table.
 * 
 */
@Entity
@NamedQuery(name="Tblresponse.findAll", query="SELECT t FROM Tblresponse t")
public class Tblresponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBLRESPONSE_RESPONSEID_GENERATOR", sequenceName="RESPONSE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBLRESPONSE_RESPONSEID_GENERATOR")
	@Column(name="response_id")
	private Integer responseId;

	@Temporal(TemporalType.DATE)
	@Column(name="_date")
	private Date date;

	private String response;

	//bi-directional many-to-one association to Tblquery
	@ManyToOne
	@JoinColumn(name="query_id")
	private Tblquery tblquery;

	//bi-directional many-to-one association to TbluserQueryResponse
	@JsonIgnore
	@OneToMany(mappedBy="tblresponse")
	private List<TbluserQueryResponse> tbluserQueryResponses;

	public Tblresponse() {
	}

	public Integer getResponseId() {
		return this.responseId;
	}

	public void setResponseId(Integer responseId) {
		this.responseId = responseId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Tblquery getTblquery() {
		return this.tblquery;
	}

	public void setTblquery(Tblquery tblquery) {
		this.tblquery = tblquery;
	}

	public List<TbluserQueryResponse> getTbluserQueryResponses() {
		return this.tbluserQueryResponses;
	}

	public void setTbluserQueryResponses(List<TbluserQueryResponse> tbluserQueryResponses) {
		this.tbluserQueryResponses = tbluserQueryResponses;
	}

	public TbluserQueryResponse addTbluserQueryRespons(TbluserQueryResponse tbluserQueryRespons) {
		getTbluserQueryResponses().add(tbluserQueryRespons);
		tbluserQueryRespons.setTblresponse(this);

		return tbluserQueryRespons;
	}

	public TbluserQueryResponse removeTbluserQueryRespons(TbluserQueryResponse tbluserQueryRespons) {
		getTbluserQueryResponses().remove(tbluserQueryRespons);
		tbluserQueryRespons.setTblresponse(null);

		return tbluserQueryRespons;
	}

}