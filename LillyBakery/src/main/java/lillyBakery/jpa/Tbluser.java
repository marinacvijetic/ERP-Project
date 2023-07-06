package lillyBakery.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the tbluser database table.
 * 
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="Tbluser.findAll", query="SELECT t FROM Tbluser t")
public class Tbluser implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_email")
	private String userEmail;

	@Column(unique=true)
	private String username;
	
	@Column(name="password")
	private String password;


	//bi-directional many-to-one association to Tbladministrator
	@JsonIgnore
	@OneToMany(mappedBy="tbluser", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<Tbladministrator> tbladministrators;

	//bi-directional many-to-one association to Tblcustomer
	@JsonIgnore
	@OneToMany(mappedBy="tbluser", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<Tblcustomer> tblcustomers;

	@Column(name = "_role", nullable = false)
	@Enumerated(EnumType.STRING)
	private Roles role;
	
	@Column(name = "locked")
	private Boolean locked = false;

	@Column(name = "enabled")
	private Boolean enabled = false;
	
	
	public Tbluser() {
	}

	
	public Tbluser(String userEmail, String username, String password, Roles user) {
	    this.userEmail = userEmail;
	    this.username = username;
	    this.password = password;
	    this.role = user;
	}


	public Roles getRole() {
		return role;
	}


	public void setRole(Roles role) {
		this.role = role;
	}


	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override
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
	
	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public Boolean getLocked() {
		return locked;
	}


	public void setLocked(Boolean locked) {
		this.locked = locked;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
		return Collections.singletonList(authority);
	}


	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		
		return !locked;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}


	@Override
	public boolean isEnabled() {
		
		return enabled;
	}





}