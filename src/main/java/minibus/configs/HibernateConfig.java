package minibus.configs;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import minibus.entities.AdminUser;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource source = new BasicDataSource();
		source.setUrl("jdbc:mysql://localhost:3306/minibus?serverTimezone=UTC");
		source.setPassword("");
		source.setUsername("root");
		
		return source;
	}
	
	@Autowired
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource, Properties hibernateProperties) {
		LocalSessionFactoryBean session = new LocalSessionFactoryBean();
		session.setDataSource(dataSource);
		session.setHibernateProperties(hibernateProperties);
		session.setAnnotatedClasses(AdminUser.class);
		return session;
	}
	
	@Autowired
	@Bean
	public PlatformTransactionManager hibernateTransactionManager(LocalSessionFactoryBean sessionFactory) {
		HibernateTransactionManager transactionManager
        = new HibernateTransactionManager();
      transactionManager.setSessionFactory(sessionFactory.getObject());
      return transactionManager;
	}
	
	@Bean
	public Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
          "hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty(
          "hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
 
        return hibernateProperties;
	}
}
