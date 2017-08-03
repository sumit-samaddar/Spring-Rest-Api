package net.restapi.spring.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.CoreMatchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import net.restapi.spring.config.Application;
import net.restapi.spring.model.Employee;

import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.springframework.restdocs.snippet.Attributes.key;

/**
 * @author sumit
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class EmployeeControllerTest {

	@Rule
	public RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private RestDocumentationResultHandler document;

	/**
	 * 
	 */
	@Before
	public void setUp() {
		this.document = document("{class-name}/{method-name}");
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration(this.restDocumentation)).build();
	}

	@Test
	public void getEmployees() throws Exception {
		this.mockMvc.perform(get("/employees")).andExpect(status().isOk())
				.andDo(document("{class-name}/{method-name}",
						responseFields(fieldWithPath("[].id").description("The employee id"),
								fieldWithPath("[].firstName").description("The employee first name"),
								fieldWithPath("[].lastName").description("The employee last name"),
								fieldWithPath("[].email").description("The employee email"),
								fieldWithPath("[].mobile").description("The employee mobile"),
								fieldWithPath("[].dateOfBirth").description("The employee date of birth"))));
	}

	@Test
	public void getEmployee() throws Exception {
		this.mockMvc.perform(get("/employees/{id}", 101)).andExpect(status().isOk())
				.andDo(document("{class-name}/{method-name}",
						responseFields(fieldWithPath("id").description("The employee id"),
								fieldWithPath("firstName").description("The employee first name"),
								fieldWithPath("lastName").description("The employee last name"),
								fieldWithPath("email").description("The employee email"),
								fieldWithPath("mobile").description("The employee mobile"),
								fieldWithPath("dateOfBirth").description("The employee date of birth"))));
	}

	@Test
	public void postEmployee() throws Exception {

		Date dt = new Date();

		Employee anObject = new Employee(Long.parseLong("781"), "Sumit", "Samaddar", "babisumit@hmail.com",
				"9883318811", dt);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(anObject);

		System.out.println(requestJson);

		ConstrainedFields fields = new ConstrainedFields(Employee.class);

		this.mockMvc.perform(post("/employees").content(requestJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(781))).andExpect(jsonPath("$.firstName", is("Sumit")))
				.andExpect(jsonPath("$.lastName", is("Samaddar")))
				.andExpect(jsonPath("$.email", is("babisumit@hmail.com")))
				.andExpect(jsonPath("$.mobile", is("9883318811"))).andDo(document("{class-name}/{method-name}",

						requestFields(fields.withPath("id").description("The employee id"),
								fields.withPath("firstName").description("The employee first name"),
								fields.withPath("lastName").description("The employee last name"),
								fields.withPath("email").description("The employee email"),
								fields.withPath("dateOfBirth").description("The employee date of birth"),
								fields.withPath("mobile").description("The employee mobile")),
						responseFields(fieldWithPath("id").description("The employee id"),
								fieldWithPath("firstName").description("The employee first name"),
								fieldWithPath("lastName").description("The employee last name"),
								fieldWithPath("email").description("The employee email"),
								fieldWithPath("mobile").description("The employee mobile"),
								fieldWithPath("dateOfBirth").description("The employee date of birth"))));

	}

	@Test
	public void putEmployee() throws Exception {

		Date dt = new Date();

		Employee anObject = new Employee(Long.parseLong("101"), "Sumit", "Samaddar", "babisumit@hmail.com",
				"9883318811", dt);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(anObject);

		System.out.println(requestJson);

		ConstrainedFields fields = new ConstrainedFields(Employee.class);

		this.mockMvc
				.perform(put("/employees/{id}", 101).content(requestJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(101))).andExpect(jsonPath("$.firstName", is("Sumit")))
				.andExpect(jsonPath("$.lastName", is("Samaddar")))
				.andExpect(jsonPath("$.email", is("babisumit@hmail.com")))
				.andExpect(jsonPath("$.mobile", is("9883318811")))
				.andDo(document("{class-name}/{method-name}",
						responseFields(fieldWithPath("id").description("The employee id"),
								fieldWithPath("firstName").description("The employee first name"),
								fieldWithPath("lastName").description("The employee last name"),
								fieldWithPath("email").description("The employee email"),
								fieldWithPath("mobile").description("The employee mobile"),
								fieldWithPath("dateOfBirth").description("The employee date of birth"))));
	}

	/*
	 * @Test public void deleteEmployee() throws Exception {
	 * this.mockMvc.perform(delete("/employees/{id}",101)).andExpect(status().
	 * isOk()) .andDo(document("{class-name}/{method-name}",
	 * responseFields(fieldWithPath("id").description("The employee id") ) ) );
	 * }
	 */

	private static class ConstrainedFields {

		private final ConstraintDescriptions constraintDescriptions;

		/**
		 * @param input
		 */
		ConstrainedFields(Class<?> input) {
			this.constraintDescriptions = new ConstraintDescriptions(input);
		}

		/**
		 * @param path
		 * @return
		 */
		private FieldDescriptor withPath(String path) {
			return fieldWithPath(path).attributes(key("constraints").value(StringUtils
					.collectionToDelimitedString(this.constraintDescriptions.descriptionsForProperty(path), ". ")));
		}
	}

}
