package net.restapi.spring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import net.restapi.spring.dao.UserDAO;
import net.restapi.spring.model.User;

/**
 * @author Sumit
 *
 */
@RestController
public class UserRestController {

	@Autowired
	private UserDAO userDAO;

	/**
	 * @return
	 */
	//@GetMapping("/users")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getusers() {
		return new ResponseEntity<List<User>>(userDAO.list(), HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	//@GetMapping("/users/{id}")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getuser(@PathVariable("id") Long id) {
		User User = userDAO.get(id);
		if (User == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(User, HttpStatus.OK);
	}

	/**
	 * @param User
	 * @return
	 */
	//@PostMapping(value = "/users")
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<User> createuser(@RequestBody User User) {
		userDAO.create(User);
		return new ResponseEntity<User>(User, HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	//@DeleteMapping("/users/{id}")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteuser(@PathVariable Long id) {
		if (null == userDAO.delete(id)) {
			return new ResponseEntity("No User found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(id, HttpStatus.OK);
	}

	/**
	 * @param id
	 * @param User
	 * @return
	 */
	//@PutMapping("/users/{id}")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public ResponseEntity updateuser(@PathVariable Long id, @RequestBody User User) {
		User = userDAO.update(id, User);
		if (null == User) {
			return new ResponseEntity("No User found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(User, HttpStatus.OK);
	}

}