package com.vmware.employeeDataManagement.employeeData;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.vmware.employeeDataManagement.service.EmployeeService;
import com.vmware.employeeDataManagement.service.FileUploadService;


@WebMvcTest
class EmployeeDataApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	FileUploadService fileUploadService;
	
	@MockBean
	EmployeeService empService;
	
	@Test
	public void testUploadMultiPart() throws Exception {
		
		MockMultipartFile file 
	      = new MockMultipartFile(
	        "file", 
	        "employee.csv", 
	        MediaType.TEXT_PLAIN_VALUE, 
	        "Ram 22".getBytes()
	      );
		
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		 mockMvc.perform(
				multipart("/api/v1/upload/local").file(file)).andExpect(status().isOk());
						
	}
	
	@Test
	public void testDeleteApi() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mockMvc.perform(MockMvcRequestBuilders
	            .delete("/api/v1/delete/1"))
	            .andExpect(status().isOk());
	}
	
	@Test
	public void testgetEmployeeInfo() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/v1/employeeInfo"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testUpdate() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MultiValueMap<String, String> params= new HttpHeaders();
		params.add("name ", "ram");
		params.add("age","22");
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/v1/update")
				.param("name","Ram")
				.param("age","22"))
				.andExpect(status().isOk());
	}
	
	

}
