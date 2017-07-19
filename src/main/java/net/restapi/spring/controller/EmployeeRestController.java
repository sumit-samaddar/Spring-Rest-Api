package net.restapi.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.restapi.spring.dao.EmployeeDAO;
import net.restapi.spring.model.Employee;

/**
 * @author Sumit
 *
 */
@RestController
public class EmployeeRestController {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeRestController.class);
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/employees", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Employee>> getemployees() {
		logger.debug("welcome() is executed, value {}", "mkyong");

		logger.error("This is Error message", new Exception("Testing"));
		
		return new ResponseEntity<List<Employee>>(employeeDAO.list(),HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/employees/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Employee> getemployee(@PathVariable("id") Long id) {
		Employee employee = employeeDAO.get(id);
		if (employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	/**
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "/employees", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Employee> createemployee(@RequestBody Employee employee) {
		employeeDAO.create(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/employees/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity deleteemployee(@PathVariable Long id) {
		if (null == employeeDAO.delete(id)) {
			return new ResponseEntity("No employee found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(id, HttpStatus.OK);
	}

	/**
	 * @param id
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "/employees/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity updateemployee(@PathVariable Long id, @RequestBody Employee employee) {
		employee = employeeDAO.update(id, employee);
		if (null == employee) {
			return new ResponseEntity("No employee found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(employee, HttpStatus.OK);
	}

}