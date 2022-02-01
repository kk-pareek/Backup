package capstone.addressmanagementsystem.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import capstone.addressmanagementsystem.entity.Address;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository <Address,Integer>{
	
	public Address findByFlatNumber(Integer flatNumber);

	@Query(value="select * from Address s where s.building_name LIKE CONCAT('%',:keyword,'%')"
			+ "OR s.city LIKE CONCAT('%',:keyword,'%')"
			+ "OR s.district LIKE CONCAT('%',:keyword,'%')"
			+ "OR s.flat_number LIKE CONCAT('%',:keyword,'%')"
			+ "OR s.pin_code LIKE CONCAT('%',:keyword,'%')"
			+ "OR s.land_mark LIKE CONCAT('%',:keyword,'%')"
			+ "OR s.country LIKE CONCAT('%',:keyword,'%')"
			+ "OR s.state LIKE CONCAT('%',:keyword,'%') LIMIT 5",
			nativeQuery = true)
	
	public List<Address> searchIn(@Param("keyword") String keyword);

	
}
