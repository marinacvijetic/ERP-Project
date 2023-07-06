package lillyBakery.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
	
	@Autowired
	private ConfirmationTokenRepository tokenConfirmRepo;

	public void saveConfirmationToken(ConfirmationToken token) {
		tokenConfirmRepo.save(token);
	}
	
    public Optional<ConfirmationToken> getToken(String token) {
        return tokenConfirmRepo.findByToken(token);
    }
	
    public int setConfirmedAt(String token) {
        return tokenConfirmRepo.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
