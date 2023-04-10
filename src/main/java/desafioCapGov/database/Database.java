package desafioCapGov.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Database {
	private static Database database = new Database();
	private SessionFactory connection;

	public static Database getInstance() {
		return database;
	}

	private Database() {
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		connection = config.buildSessionFactory();
	}

	public static Session getSession() {
		Session session = getInstance().connection.openSession();
		return session;
	}

}
