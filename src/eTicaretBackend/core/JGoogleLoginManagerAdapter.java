package eTicaretBackend.core;

import eTicaretBackend.entities.concretes.Login;
import jGoogle.JGoogleLoginManager;

public class JGoogleLoginManagerAdapter implements LoginService{

	@Override
	public void login(Login login) {
		JGoogleLoginManager manager = new JGoogleLoginManager();
		manager.loginWithGoogle(login.getEmail(), login.getPassword());
	}

}
