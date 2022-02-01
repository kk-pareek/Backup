package capstone.addressmanagementsystem.entity;

public class AddressDTO {
    private Integer flatNumber;
    private String buildingName;
    private String city;
    private String district;
    private String state;
    private String landMark;
    private String country;
    private Integer pinCode;

    public AddressDTO(Integer flatNumber, String buildingName, String city, String district, String state, String landMark, String country, Integer pinCode) {
        this.flatNumber = flatNumber;
        this.buildingName = buildingName;
        this.city = city;
        this.district = district;
        this.state = state;
        this.landMark = landMark;
        this.country = country;
        this.pinCode = pinCode;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }
}
