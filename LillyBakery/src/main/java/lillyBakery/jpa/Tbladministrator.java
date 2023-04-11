package lillyBakery.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbladministrator database table.
 * 
 */
@Entity
@NamedQuery(name="Tbladministrator.findAll", query="SELECT t FROM Tbladministrator t")
public class Tbladministrator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBLADMINISTRATOR_ADMINID_GENERATOR", sequenceName="ADMINISTRATOR_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBLADMINISTRATOR_ADMINID_GENERATOR")
	@Column(name="admin_id")
	private Integer adminId;

	@Column(name="admin_name", unique=true)
	private String adminName;

	//bi-directional many-to-one association to Tbluser
	@ManyToOne
	@JoinColumn(name="user_email")
	private Tbluser tbluser;

	public Tbladministrator() {
	}

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Tbluser getTbluser() {
		return this.tbluser;
	}

	public void setTbluser(Tbluser tbluser) {
		this.tbluser = tbluser;
	}

}