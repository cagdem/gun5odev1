package eTicaretBackend.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eTicaretBackend.business.abstracts.UserService;
import eTicaretBackend.core.LoginService;
import eTicaretBackend.dataAccess.abstracts.UserDao;
import eTicaretBackend.entities.concretes.Login;
import eTicaretBackend.entities.concretes.User;

public class UserManager implements UserService {

	private UserDao userDao;
	private LoginService loginService;
	
	public UserManager(UserDao userDao, LoginService loginService) {
		super();
		this.userDao = userDao;
		this.loginService = loginService;
	}

	@Override
	public void add(User user) {

		if (user.getPassword().length()<6) {
			System.out.println("Sifreniz en az 6 karakterli olmali.");
			return;
		}
		if (user.getFirstName().length()<2 || user.getLastName().length()<2) {
			System.out.println("Isminiz ve soyadiniz en az 2 karakterli olmali.");
			return;
		}
		
		if(checkEmailUsedBefore(user.getEmail())) {
			System.out.println("Bu mail daha once kullanilmis.");
			return;
		}
		if(checkEmailIsValid(user.getEmail())) {
			System.out.println("Lutfen dogru formatta bir mail giriniz.");
			return;
		}
		
		sendVerificationEmail();
		this.userDao.add(user);
		System.out.println("Uyelik isleminiz tamamlanmistir.");
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return this.userDao.getAll();
	}

	@Override
	public boolean checkEmailUsedBefore(String email) {

		List<User> users = this.userDao.getAll();
		for(User user: users) {
			if (user.getEmail() == email) {
				return false;
			} 
		}
		return true;
	}

	@Override
	public void sendVerificationEmail() {

		System.out.println("Dogrulama maili yollandi.");
		if (verificationButton()) {
			System.out.println("Dogrulama onaylandi. Uyelik tamamlandi.");

		} 
	}

	@Override
	public boolean verificationButton() {
		return true;
	}

	@Override
	public void login(Login login) {
		this.loginService.login(login);
		
	}

	@Override
	public boolean checkEmailIsValid(String email) {

		   String mailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"+"[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		   Pattern pattern = Pattern.compile(mailPattern);
		   Matcher emailMatcher = pattern.matcher(email);
		   return emailMatcher.matches();
	}

}
