package capstone.addressmanagementsystem.controller;

import java.util.List;

import capstone.addressmanagementsystem.entity.AddressDTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import capstone.addressmanagementsystem.entity.Address;
import capstone.addressmanagementsystem.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api")
@Api(value = "/api", tags = "Address Management")
public class AddressController {
    @Autowired
    private AddressService theAddressService;


    @GetMapping
    @ApiOperation(value = "Get API Status", notes = "Endpoint to know whether the theAddressService is up or not.", tags = "Address Management")
    public String getServiceStatus() {
        return ("The Service is running.");
    }


    @GetMapping("/addresses")
    @ApiOperation(value = "Get All Addresses", notes = "Endpoint to get/read all addresses.", tags = "Address Management")
    public List<Address> getAllAddresses() {
        return theAddressService.getAllAddress();
    }


    @PostMapping("/addresses")
    @ApiOperation(value = "Add New Address", notes = "Endpoint to add a new address.", tags = "Address Management")
    public ResponseEntity<UploadResponseMessage> addNewAddress(@RequestBody AddressDTO theAddressDTO) {
        Address theAddress = new Address(theAddressDTO);
        theAddressService.addNewAddress(theAddress);
        return ResponseEntity.status(HttpStatus.OK).body(new UploadResponseMessage("Address Added !"));
    }


    @PutMapping("addresses/{id}")
    @ApiOperation(value = "Update Address By Id", notes = "Endpoint to update an address by id.", tags = "Address Management")
    public ResponseEntity<UploadResponseMessage> updateAddressById(@PathVariable int id, @RequestBody AddressDTO theAddressDTO) {
        Address theAddress = new Address(theAddressDTO);
        theAddressService.updateAddress(id, theAddress);
        return ResponseEntity.status(HttpStatus.OK).body(new UploadResponseMessage("Address Updated !"));
    }


    @DeleteMapping("addresses/{id}")
    @ApiOperation(value = "Delete Address By Id", notes = "Endpoint to delete an address by id.", tags = "Address Management")
    public ResponseEntity<UploadResponseMessage> deleteAddressById(@PathVariable int id) {
        theAddressService.deleteAddress(id);
        return ResponseEntity.status(HttpStatus.OK).body(new UploadResponseMessage("Address Deleted !"));
    }


    @PostMapping("/uploadAddressesFile")
    @ApiOperation(value = "Add Multiple Addresses Through File", notes = "Endpoint to add multiple addresses via file uploaded by user.", tags = "Address Management")
    public ResponseEntity<UploadResponseMessage> addMultipleAddressesViaFile(@RequestParam("file") MultipartFile file) {
        return theAddressService.saveMultipleAddresses(file);
    }


    @GetMapping("search/{keyword}")
    @ApiOperation(value = "Search Addresses", notes = "Endpoint to search addresses.", tags = "Address Management")
    public List<Address> searchAllAddressesContainingKeyword(@PathVariable("keyword") String keyword) {
        return theAddressService.getAllAddressByParameters(keyword);
    }
}
