package net.restapi.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	/**
	 * @return
	 */
	@GetMapping("/employees")
	public List getemployees() {
		return employeeDAO.list();
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/employees/{id}")
	public ResponseEntity getemployee(@PathVariable("id") Long id) {
		Employee employee = employeeDAO.get(id);
		if (employee == null) {
			return new ResponseEntity("No employee found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(employee, HttpStatus.OK);
	}

	/**
	 * @param employee
	 * @return
	 */
	@PostMapping(value = "/employees")
	public ResponseEntity createemployee(@RequestBody Employee employee) {
		employeeDAO.create(employee);
		return new ResponseEntity(employee, HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("/employees/{id}")
	public ResponseEntity deleteemployee(@PathVariable Long id) {
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
	@PutMapping("/employes/{id}")
	public ResponseEntity updateemployee(@PathVariable Long id, @RequestBody Employee employee) {
		employee = employeeDAO.update(id, employee);
		if (null == employee) {
			return new ResponseEntity("No employee found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(employee, HttpStatus.OK);
	}

}