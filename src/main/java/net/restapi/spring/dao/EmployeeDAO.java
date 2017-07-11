package net.restapi.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import net.restapi.spring.model.Employee;

/**
 * @author Sumit
 *
 */
@Component
public class EmployeeDAO {

	// Dummy database. Initialize with some dummy values.
	private static List<Employee> employees;

	{
		employees = new ArrayList<Employee>();
		employees.add(new Employee(101, "John", "Doe", "djohn@gmail.com", "121-232-3435"));
		employees.add(new Employee(201, "Russ", "Smith", "sruss@gmail.com", "343-545-2345"));
		employees.add(new Employee(301, "Kate", "Williams", "kwilliams@gmail.com", "876-237-2987"));
	}

	/**
	 * Returns list of employees from dummy database.
	 * 
	 * @return list of employees
	 */
	public List<Employee> list() {
		return employees;
	}

	/**
	 * Return customer object for given id from dummy database. If customer is
	 * not found for id, returns null.
	 * 
	 * @param id
	 *            customer id
	 * @return customer object for given id
	 */
	public Employee get(Long id) {

		for (int i = 0; i < employees.size(); i++) {
			Employee c = (Employee) employees.get(i);
			if (c.getId().equals(id)) {
				return c;
			}
		}

		return null;
	}

	/**
	 * Create new customer in dummy database. Updates the id and insert new
	 * customer in list.
	 * 
	 * @param customer
	 *            Customer object
	 * @return customer object with updated id
	 */
	public Employee create(Employee employee) {
		employee.setId(System.currentTimeMillis());
		employees.add(employee);
		return employee;
	}

	/**
	 * Delete the customer object from dummy database. If customer not found for
	 * given id, returns null.
	 * 
	 * @param id
	 *            the customer id
	 * @return id of deleted customer object
	 */
	public Long delete(Long id) {

		for (int i = 0; i < employees.size(); i++) {
			Employee c = (Employee) employees.get(i);
			if (c.getId().equals(id)) {
				employees.remove(c);
				return id;
			}
		}

		return null;
	}

	/**
	 * Update the customer object for given id in dummy database. If customer
	 * not exists, returns null
	 * 
	 * @param id
	 * @param customer
	 * @return customer object with id
	 */
	public Employee update(Long id, Employee employee) {

		for (int i = 0; i < employees.size(); i++) {
			Employee c = (Employee) employees.get(i);
			if (c.getId().equals(id)) {
				employee.setId(c.getId());
				employees.remove(c);
				employees.add(employee);
				return employee;
			}
		}

		return null;
	}

}
