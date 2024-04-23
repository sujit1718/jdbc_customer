package jdbc_customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchExecution {
  public static void main(String[] args) throws ClassNotFoundException, SQLException {
	  
	Customer customer1 = new Customer();
	customer1.setId(4);
	customer1.setName("Sujit");
	customer1.setPhone(876712);
	customer1.setAddress("Latur");
	
	Customer customer2 = new Customer();
	customer2.setId(5);
	customer2.setName("Sudhir");
	customer2.setPhone(876612);
	customer2.setAddress("Jewari");
	
	Customer customer3 = new Customer();
	customer3.setId(6);
	customer3.setName("Sita");
	customer3.setPhone(876621);
	customer3.setAddress("Nilanga");
	
	List<Customer> list = new ArrayList<Customer>();
	list.add(customer1);
	list.add(customer2);
	list.add(customer3);
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb","root","root");
	PreparedStatement preparedStatement = connection.prepareStatement("insert into customer(id, name, phone, address)values(?,?,?,?)");
	for(Customer customer : list) {
		preparedStatement.setInt(1, customer.getId());
		preparedStatement.setString(2, customer.getName());
		preparedStatement.setLong(3, customer.getPhone());
		preparedStatement.setString(4, customer.getAddress());
		
		preparedStatement.addBatch();
	}
	preparedStatement.executeBatch();
	System.out.println("Batch Executed!");
	connection.close();
  }
}
