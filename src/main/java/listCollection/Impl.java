package listCollection;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Impl {

  public static void main(String[] args) {
  // Load the configuration and build the SessionFactory
  Configuration configuration = HibernateConfig.getConfig();
  configuration.addAnnotatedClass(Department.class);
  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
  		.applySettings(configuration.getProperties())
  		.build();
  SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
  System.out.println("SessionFactory created successfully.");
  // Create a session
  Session session = sessionFactory.openSession();
  Transaction transaction = null;

  
  try {
      transaction = session.beginTransaction();
   // Create a Department
      Department department = new Department();
      department.setName("IT");
      department.setEmployeeNames(Arrays.asList("John", "Jane", "Smith", "Emily"));

      // Save Department
      session.persist(department);

      transaction.commit();
  } catch (Exception e) {
      if (transaction != null) transaction.rollback();
      e.printStackTrace();
  } finally {
      session.close();
      sessionFactory.close();
  }
}
}