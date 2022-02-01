package capstone.addressmanagementsystem.utils;

import capstone.addressmanagementsystem.entity.Address;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;


public class AddressValidator {


    private AddressValidator() {
        throw new IllegalStateException("Utility class");
    }


    public static boolean isValidAddress(Address theAddressToBeValidated) throws IOException {
        URL urlForAddressValidation = new URL("https://api.postalpincode.in/pincode/" + theAddressToBeValidated.getPinCode().toString());
        String jsonResponseString = getJsonResponseString(urlForAddressValidation);
        JSONObject theJsonResponse = convertJsonStringToJSONObject(jsonResponseString);
        return theJsonResponse.get("Status").equals("Success");
    }


    private static String getJsonResponseString(URL url) throws IOException {
        StringBuilder responseString = new StringBuilder();
        Scanner sc = new Scanner(url.openStream());

        while (sc.hasNext()) {
            responseString.append(sc.nextLine());
        }
        sc.close();
        return responseString.substring(1, responseString.length() - 1);
    }


    private static JSONObject convertJsonStringToJSONObject(String jsonString) {
        JSONObject theJsonObject = new JSONObject();
        JSONParser theJsonParser = new JSONParser();

        if ((jsonString != null) && !(jsonString.isEmpty())) {
            try {
                theJsonObject = (JSONObject) theJsonParser.parse(jsonString);
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }
        return theJsonObject;
    }
}
