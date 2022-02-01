package capstone.addressmanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import capstone.addressmanagementsystem.controller.AddressController;



@SpringBootTest
class SmokeTest {
	@Autowired
	AddressController contr;
	
	@Test
	void contextLoads() {
		assertThat(contr).isNotNull();
	}
}
