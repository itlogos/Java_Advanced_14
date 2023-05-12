package ua.lviv.lgs.hbm.xml;

import java.util.Properties;
import java.util.TimeZone;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateSessionFactory {
	private static SessionFactory hibernateSessionFactory;

	public static SessionFactory getSessionFactory() {
		if (hibernateSessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
//				configuration.configure("hibernate.cfg.xml");
				
				Properties properties = new Properties();
				properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				properties.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate?serverTimezone=" + TimeZone.getDefault().getID());
				properties.put(Environment.USER, "root");
				properties.put(Environment.PASS, "tekla");
				properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				properties.put(Environment.SHOW_SQL, "true");
				properties.put(Environment.HBM2DDL_AUTO, "create");
				configuration.setProperties(properties);
				
				configuration.addResource("Cart.hbm.xml");
				configuration.addResource("Item.hbm.xml");

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				hibernateSessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return hibernateSessionFactory;
	}
}
