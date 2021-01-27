package com.vmware.employeeDataManagement.employeeData;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.vmware.employeeDataManagement.service.EmployeeService;
import com.vmware.employeeDataManagement.service.FileUploadService;

@WebMvcTest
public class FileUploadTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	FileUploadService fileUploadService;
	
	@MockBean
	EmployeeService empService;
	
	@Test
	public void testUploadMultiPartLocal() throws Exception {
		
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
	public void testUploadMultiPartDB() throws Exception {
		
		MockMultipartFile file 
	      = new MockMultipartFile(
	        "file", 
	        "employee.csv", 
	        MediaType.MULTIPART_FORM_DATA_VALUE, 
	        "Ram 22".getBytes()
	      );
		
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		 mockMvc.perform(
				multipart("/api/v1/upload/db").file(file)).andExpect(status().isOk());
						
	}
	
	
	@Test
	public void testUploadEmptyMultiPartDB() throws Exception {
		
		MockMultipartFile file 
	      = new MockMultipartFile(
	        "file", 
	        "", 
	        MediaType.MULTIPART_FORM_DATA_VALUE, 
	        "".getBytes()
	      );
		
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		 mockMvc.perform(
				multipart("/api/v1/upload/db").file(file)).andExpect(status().isOk());
						
	}
	
	@Test
	public void testUploadIncorrectMultiPartDB() throws Exception {
		
		MockMultipartFile file 
	      = new MockMultipartFile(
	        "file", 
	        "employee.txt", 
	        MediaType.MULTIPART_FORM_DATA_VALUE, 
	        "Ram,22".getBytes()
	      );
		
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		 mockMvc.perform(
				multipart("/api/v1/upload/db").file(file)).andExpect(status().isOk());
						
	}
	
	@Test
	public void testgetUploadStatus() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/v1//getUploadStatus/1"))
				.andExpect(status().isOk());
	}
	
}
