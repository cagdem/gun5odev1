package eTicaretBackend.business.concretes;

import java.util.List;

import eTicaretBackend.core.LoginService;
import eTicaretBackend.dataAccess.concretes.HibernateUserDao;
import eTicaretBackend.entities.concretes.Login;
import eTicaretBackend.entities.concretes.User;

public class LoginManager implements LoginService{

	HibernateUserDao userDao = new HibernateUserDao();
	
	@Override
	public void login(Login login) {

		List<User> users = this.userDao.getAll();
		for(User user:users) {
			if(user.getEmail()==login.getEmail()) {
				if (user.getPassword()==login.getPassword()) {
					System.out.println("Giris basarili");
					return;
				}else {
					System.out.println("Sifreniz hatali");
					return;
				}
			}else {
				System.out.println("Email bulunamadi.");
				return;
			}
		}
	}

}
