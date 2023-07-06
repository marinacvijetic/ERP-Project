package lillyBakery.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private  String userEmail;
	private  String username;
	private  String password;
	
	public String getUserEmail() {
		return userEmail;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}


}
