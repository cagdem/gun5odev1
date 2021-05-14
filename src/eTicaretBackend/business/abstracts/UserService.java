package eTicaretBackend.business.abstracts;

import java.util.List;

import eTicaretBackend.entities.concretes.Login;
import eTicaretBackend.entities.concretes.User;

public interface UserService {

	void add(User user);
	List<User> getAll();
	boolean emailCheck(String email);
	void sendVerificationEmail();
	boolean verificationButton();
	void login(Login login);
}
