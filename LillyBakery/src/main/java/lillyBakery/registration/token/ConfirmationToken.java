package lillyBakery.registration.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lillyBakery.jpa.Tbluser;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table(name="confirmation_token")
@Entity
public class ConfirmationToken {

	@Id
	@SequenceGenerator(name="CONFIRMATION_TOKEN_TOKENID_GENERATOR", sequenceName="CONFIRMATION_TOKEN_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONFIRMATION_TOKEN_TOKENID_GENERATOR")
	@Column(name="token_id")
	private Integer tokenId;
	
	@Column(nullable = false)
	private String token;
	
	@Column(name="created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name="expires_at", nullable = false)
	private LocalDateTime expiresAt;
	
	@Column(name="confirmed_at")
	private LocalDateTime confirmedAt;
	
	@ManyToOne
	@JoinColumn(name="user_email", nullable = false)
	private Tbluser tbluser;
	
	public ConfirmationToken(){
		
	}
	
    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, Tbluser user) {
    		this.token = token;
    		this.createdAt = createdAt;
    		this.expiresAt = expiresAt;
    		this.tbluser = user;
}
	
	public Integer getTokenId() {
		return tokenId;
	}

	public void setTokenId(Integer tokenId) {
		this.tokenId = tokenId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public LocalDateTime getConfirmedAt() {
		return confirmedAt;
	}

	public void setConfirmedAt(LocalDateTime confirmedAt) {
		this.confirmedAt = confirmedAt;
	}

	public Tbluser getTbluser() {
		return tbluser;
	}

	public void setTbluser(Tbluser tbluser) {
		this.tbluser = tbluser;
	}
	
}
