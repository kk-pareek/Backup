package capstone.addressmanagementsystem.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import capstone.addressmanagementsystem.repository.AddressRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import capstone.addressmanagementsystem.controller.UploadResponseMessage;
import capstone.addressmanagementsystem.entity.Address;

import static capstone.addressmanagementsystem.utils.AddressValidator.isValidAddress;


@Service
public class AddressService {

    @Autowired
    private AddressRepository theAddressRepository;

    Logger logger = LoggerFactory.getLogger(AddressService.class);

    public List<Address> getAllAddress() {
        return (List<Address>) theAddressRepository.findAll();
    }

    public Optional<Address> getAddressById(Integer id) {
        return theAddressRepository.findById(id);
    }

    public void addNewAddress(Address theAddress) {
        theAddressRepository.save(theAddress);
    }

    public void updateAddress(Integer id, Address theAddress) throws NullPointerException {
        Address existingAddress;
        Optional<Address> value = this.getAddressById(id);
        try {
        	if(value.isPresent()) {
            existingAddress = value.get();
            existingAddress.setFlatNumber(theAddress.getFlatNumber());
            existingAddress.setCity(theAddress.getCity());
            existingAddress.setDistrict(theAddress.getDistrict());
            existingAddress.setState(theAddress.getState());
            existingAddress.setBuildingName(theAddress.getBuildingName());
            existingAddress.setPinCode(theAddress.getPinCode());
            existingAddress.setLandMark(theAddress.getLandMark());
            existingAddress.setCountry(theAddress.getCountry());
            theAddressRepository.save(existingAddress);
        	}
        } catch (Exception e) {
            logger.error("!!!", e);
        }
    }

    public void deleteAddress(Integer id) {
        theAddressRepository.deleteById(id);
    }

    public List<Address> getAllAddressByParameters(String search) {
        return theAddressRepository.searchIn(search);
    }

    public ResponseEntity<UploadResponseMessage> saveAddress(String targetUrl) throws IOException {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(targetUrl))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                Address addr = new Address();
                if (data[0].length() > 0) {
                    addr.setFlatNumber(Integer.parseInt(data[0]));
                }
                addr.setBuildingName(data[1]);
                addr.setCity(data[2]);
                addr.setDistrict(data[3]);
                addr.setState(data[4]);
                if (data[6].length() > 0) {
                    addr.setPinCode(Integer.parseInt(data[6]));
                }
                addr.setLandMark(data[5]);
                if (data.length == 8) {
                    addr.setCountry(data[7]);
                }

                if (addr.getFlatNumber() != null && addr.getCity() != null && addr.getDistrict() != null && addr.getState() != null && addr.getPinCode() != null && data.length == 8) {
                    theAddressRepository.save(addr);
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new UploadResponseMessage("Address Saved!"));
                } else {
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new UploadResponseMessage("Please enter all the mandatory fields!"));
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("!!!!", e);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new UploadResponseMessage("Data Saved..."));
    }


    public ResponseEntity<UploadResponseMessage> saveExcel(String targetUrl) throws IOException {
        try (Workbook wb = WorkbookFactory.create(new File(targetUrl))) {
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            rows.next();
            while (rows.hasNext()) {
                Row row = rows.next();
                Address address = new Address();
                address.setFlatNumber((int) row.getCell(0).getNumericCellValue());
                address.setBuildingName(row.getCell(1).getStringCellValue());
                address.setCity(row.getCell(2).getStringCellValue());
                address.setDistrict(row.getCell(3).getStringCellValue());
                address.setState(row.getCell(4).getStringCellValue());
                address.setLandMark(row.getCell(5).getStringCellValue());
                address.setPinCode((int) row.getCell(6).getNumericCellValue());
                address.setCountry(row.getCell(7).getStringCellValue());
                if (isValidAddress(address)) {
                    theAddressRepository.save(address);
                }
                else {
                    continue;
                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new UploadResponseMessage("Data Saved..."));
    }


    @Value("${upload.path}")
    private String uploadPath;

    public ResponseEntity<UploadResponseMessage> saveMultipleAddresses(MultipartFile file) throws IllegalArgumentException {
        try {
            Path root = Paths.get(uploadPath);
            Path resolve = root.resolve(file.getOriginalFilename());
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());

            if (resolve.toFile().exists())
                logger.warn("File already exists");

            Files.copy(file.getInputStream(), resolve);
            if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx"))
                saveExcel(resolve.toString());
            else
                saveAddress(resolve.toString());

            if (Files.deleteIfExists(resolve))
                logger.info("File deleted successfully");
            else
                logger.warn("File not deleted");

        } catch (Exception e) {
            logger.error("!!!!", e);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new UploadResponseMessage("File Uploaded Successfully..."));
    }
}
