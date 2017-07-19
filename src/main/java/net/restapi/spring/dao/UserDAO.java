package net.restapi.spring.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import net.restapi.spring.model.User;

/**
 * @author Sumit
 *
 */
@Component
public class UserDAO {

	private static List<User> users;

	{
		users = new ArrayList<User>();
		users.add(new User(101, "John", "Doe", "djohn@gmail.com", "121-232-3435"));
		users.add(new User(201, "Russ", "Smith", "sruss@gmail.com", "343-545-2345"));
		users.add(new User(301, "Kate", "Williams", "kwilliams@gmail.com", "876-237-2987"));
	}

	/**
	 * Returns list of users from dummy database.
	 * 
	 * @return list of users
	 */
	public List<User> list() {
		return users;
	}

	/**
	 * Return customer object for given id from dummy database. If customer is
	 * not found for id, returns null.
	 * 
	 * @param id
	 *            customer id
	 * @return customer object for given id
	 */
	public User get(Long id) {

		for (int i = 0; i < users.size(); i++) {
			User c = (User) users.get(i);
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
	public User create(User User) {
		User.setId(System.currentTimeMillis());
		users.add(User);
		return User;
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

		for (int i = 0; i < users.size(); i++) {
			User c = (User) users.get(i);
			if (c.getId().equals(id)) {
				users.remove(c);
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
	public User update(Long id, User User) {

		for (int i = 0; i < users.size(); i++) {
			User c = (User) users.get(i);
			if (c.getId().equals(id)) {
				User.setId(c.getId());
				users.remove(c);
				users.add(User);
				return User;
			}
		}

		return null;
	}

}
