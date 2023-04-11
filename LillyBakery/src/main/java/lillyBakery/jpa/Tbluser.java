package lillyBakery.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the tbluser database table.
 * 
 */
@Entity
@NamedQuery(name="Tbluser.findAll", query="SELECT t FROM Tbluser t")
public class Tbluser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_email")
	private String userEmail;

	private String password;

	@Column(unique=true)
	private String username;

	//bi-directional many-to-one association to Tbladministrator
	@JsonIgnore
	@OneToMany(mappedBy="tbluser", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<Tbladministrator> tbladministrators;

	//bi-directional many-to-one association to Tblcustomer
	@JsonIgnore
	@OneToMany(mappedBy="tbluser", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<Tblcustomer> tblcustomers;

	public Tbluser() {
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Tbladministrator> getTbladministrators() {
		return this.tbladministrators;
	}

	public void setTbladministrators(List<Tbladministrator> tbladministrators) {
		this.tbladministrators = tbladministrators;
	}

	public Tbladministrator addTbladministrator(Tbladministrator tbladministrator) {
		getTbladministrators().add(tbladministrator);
		tbladministrator.setTbluser(this);

		return tbladministrator;
	}

	public Tbladministrator removeTbladministrator(Tbladministrator tbladministrator) {
		getTbladministrators().remove(tbladministrator);
		tbladministrator.setTbluser(null);

		return tbladministrator;
	}

	public List<Tblcustomer> getTblcustomers() {
		return this.tblcustomers;
	}

	public void setTblcustomers(List<Tblcustomer> tblcustomers) {
		this.tblcustomers = tblcustomers;
	}

	public Tblcustomer addTblcustomer(Tblcustomer tblcustomer) {
		getTblcustomers().add(tblcustomer);
		tblcustomer.setTbluser(this);

		return tblcustomer;
	}

	public Tblcustomer removeTblcustomer(Tblcustomer tblcustomer) {
		getTblcustomers().remove(tblcustomer);
		tblcustomer.setTbluser(null);

		return tblcustomer;
	}

}