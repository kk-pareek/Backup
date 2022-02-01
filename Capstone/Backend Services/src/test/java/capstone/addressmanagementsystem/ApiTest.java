package capstone.addressmanagementsystem;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import capstone.addressmanagementsystem.controller.AddressController;
import capstone.addressmanagementsystem.entity.Address;
import capstone.addressmanagementsystem.repository.AddressRepository;
import capstone.addressmanagementsystem.service.AddressService;



@ComponentScan(basePackages = "capstone.addressmanagementsystem")
@SpringBootTest(classes = AddressManagementSystemApplication.class)
@AutoConfigureMockMvc
public class ApiTest {
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	AddressService service;
	
	@MockBean
	AddressRepository repo;
	
	@Test
	void getAllAddresses() throws Exception {

		List<Address> addrList = new ArrayList<>();
		Address Address1= new Address(1,1,"address 1", "city 1", "dist 1", "state 1","landmark 1","country 1", 123451);
	    Address Address2=new Address(2,2, "address 2", "city 2", "dist 2", "state 2","landmark 2","country 2", 123452);
		addrList.add(Address1);
		addrList.add(Address2);

		// Mocking out the Address service
		when(service.getAllAddress()).thenReturn(addrList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/addresses").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].city", is("city 1"))).andExpect(jsonPath("$[0].district", is("dist 1")))
				.andExpect(jsonPath("$[1].city", is("city 2")))
				.andExpect(jsonPath("$[1].district", is("dist 2")));
	}	
	
	
	@Test
	void testAddAddress_success() throws Exception {
		Address addr= new Address(1,1,"address 1", "city 1", "dist 1", "state 1","landmark 1","country 1", 123451);    
	    mockMvc.perform(post("/api/addresses")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(toJson(addr)))
	            .andExpect(status().isOk());
	}
	
	
	@Test
	void updateAddress_success() throws Exception {
		Address currentAddr=new Address(2,2, "address 2", "city 2", "dist 2", "state 2","landmark 2","country 2", 123452);
	    when(repo.findById(2)).thenReturn(Optional.of(currentAddr));
	    currentAddr.setCity("New Updated City");
	    when(repo.save(currentAddr)).thenReturn(currentAddr);
	    assertEquals("New Updated City", repo.findById(2).get().getCity());
	    		
	}
	
	
	@Test
	void testDeleteAddress_success() throws Exception {
		Address addr= new Address(1,1,"address 1", "city 1", "dist 1", "state 1","landmark 1","country 1", 123451);
		mockMvc.perform(delete("/api/addresses/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(addr)))
				.andExpect(status().isOk());
		verify(service, times(1)).deleteAddress(1);
	}
	
	
	
	public static String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
