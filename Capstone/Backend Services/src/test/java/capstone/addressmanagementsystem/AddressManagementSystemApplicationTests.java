package capstone.addressmanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import capstone.addressmanagementsystem.entity.Address;
import capstone.addressmanagementsystem.repository.AddressRepository;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressManagementSystemApplicationTests {
    @Autowired
    AddressRepository repo;

    int id;

    @Test
    @Order(1)
    void testAdd() {
        Address addr = new Address(1, 456, "test", "test city", "test dist", "test state", "landmark", "country", 508);
        repo.save(addr);
        id = repo.findByFlatNumber(456).getId();
        assertNotNull(repo.findById(id));
    }

    @Test
    @Order(2)
    void testReadAll() {
        Iterable<Address> list = repo.findAll();
        assertThat(list).size().isPositive();
    }

    @Test
    @Order(3)
    void testSearch() {
        id = repo.findByFlatNumber(456).getId();
        Address addr = repo.findById(id).get();
        assertEquals("test", addr.getBuildingName());
    }

    @Test
    @Order(4)
    void testUpdate() {
        id = repo.findByFlatNumber(456).getId();
        Address addr = repo.findById(id).get();
        addr.setBuildingName("test Update");
        repo.save(addr);
        assertNotEquals("test", repo.findById(id).get().getBuildingName());
    }

    @Test
    @Order(5)
    void testDelete() {
        id = repo.findByFlatNumber(456).getId();
        repo.deleteById(id);
        assertThat(repo.existsById(id)).isFalse();
    }
}
