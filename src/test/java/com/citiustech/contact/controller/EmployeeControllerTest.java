package com.citiustech.contact.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;   // ...or...
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.citiustech.contact.model.Address;
import com.citiustech.contact.model.Employee;
import com.citiustech.contact.repository.AddressRepository;
import com.citiustech.contact.repository.EmployeeRepository;
import com.citiustech.contact.service.EmployeeService;


@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class) 	
@WebAppConfiguration
public class EmployeeControllerTest {
	@Autowired
    private MockMvc mockMvc;
 
	@MockBean
    private EmployeeService empService;
    @MockBean
    private EmployeeRepository repository;
    @MockBean
    private AddressRepository adrRepository;
    
    @InjectMocks
    private EmployeeController emc;
    
    @Before
    public void setup()
    {
    	MockitoAnnotations.initMocks(this);
    	mockMvc=MockMvcBuilders.standaloneSetup(emc).build();
    

    }
    @Test
    public void getAllEmployees() throws Exception
    {
    	Employee emp=new Employee((long) 1,
        		new Address((long) 1,"MH",200100),
        		"CTS","Test","java");
    	List<Employee> lst=new ArrayList<Employee>();
    	lst.add(emp);
    	when(empService.findAll()).thenReturn(lst);
    	
    	String expected="[{id: 1,name: CTS, designation: Test,expertise: java,adr: {id: 1,state: MH,pincode:200100} }]";
    	
    	mockMvc.perform(get("/employees")
    			.accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isOk())
    			.andExpect(content().json(expected));
    	//assertEquals(expected, empService.findAll());;										
    											;
    /*	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/employees").accept(
				MediaType.APPLICATION_JSON);

    	MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    	String expected="[{id: 1,name: CTS, designation: Test,expertise: java,adr: {id: 1,state: MH,pincode:200100} }]";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);			*/	
    	
    	
    	
    }
    
   @Test
    public void createEmployee() throws Exception
    {
    	Employee emp=new Employee((long) 1,
        		new Address((long) 1,"MH",200100),
        		"CTS","Test","java");
    
    	String addEmpJson = "{\"id\":\"1\",\"name\":\"CTS\",\"designation\":\"Test\",\"expertise\":\"java\",\"adr\" : {\"state\":\"MH\",\"pincode\":200100}}";
    	String expected="{id: 1,name: CTS, designation: Test,expertise: java,adr: {id: 1,state: MH,pincode:200100} }";
    	
    	Mockito.when(empService.save(Mockito.any(Employee.class))).thenReturn(emp);
    	
    	mockMvc.perform(post("/employees")
    			.accept(MediaType.APPLICATION_JSON)
    			.content(addEmpJson)
    			.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(expected));
	//	verify(empService,times(1)).save(emp);
    	
    	
    	/*RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
    			"/employees").accept(
    					MediaType.APPLICATION_JSON).content(addEmpJson)
				.contentType(MediaType.APPLICATION_JSON);
    	
    					
    	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    	
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());	
		JSONAssert.assertEquals(expected,result.getResponse().getContentAsString() , false);	
*/
    }
   // @Test
    public void updateEmployee() throws Exception
    {
    	
    	
    	
    	Employee emp=new Employee((long) 1,
        		new Address((long) 1,"MH",200100),
        		"CTS","Test","java");
    	String addEmpJson = "{\"id\":\"1\",\"name\":\"CTS\",\"designation\":\"Test\",\"expertise\":\"java\",\"adr\" : {\"state\":\"MH\",\"pincode\":200100}}";
    	
    	
    	when(empService.findOne(emp.getId())).thenReturn(emp);
    	 
        mockMvc.perform(
                put("/employees/{id}", emp.getId())               
                .contentType(MediaType.APPLICATION_JSON)
                .content(addEmpJson))
        		.andExpect(status().isOk());

        verify(empService, times(1)).findOne(emp.getId());
        verify(empService, times(1)).save(emp);
        verifyNoMoreInteractions(empService);
    	
    	
	
    }
  //  @Test
    public void deleteEmployee() throws Exception
    {
    	
    	Employee emp=new Employee((long) 1,
        		new Address((long) 1,"MH",200100),
        		"CTS","Test","java");


    	 when(empService.findOne(emp.getId())).thenReturn(emp);
    	    doNothing().when(empService).delete(emp);
    	    
    	    mockMvc.perform(
    	            delete("/employees/{id}", emp.getId()))
    	            .andExpect(status().isOk());
    	    verify(empService, times(1)).findOne(emp.getId());
    	    verify(empService, times(1)).delete(emp);
    	    verifyNoMoreInteractions(empService);
    	 
    	
    }
    
    
   
}
