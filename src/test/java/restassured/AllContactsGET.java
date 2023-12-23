package restassured;

import dto.ContactDTO;
import dto.ContactListDTO;
import helper.Helper;
import helper.HelperMethod;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AllContactsGET extends HelperMethod implements Helper {


@BeforeMethod
    public void precondition(){
    RestAssured.baseURI = BASE_URL;
    RestAssured.basePath=PATH;


}

@Test
    public void getAllContactsTestPositive(){
   ContactListDTO list =  given()
             .header(authHeader,getToken())
             .when()
             .get(endpointContacts)
             .then()
             .assertThat().statusCode(200)
             .extract()
             .as(ContactListDTO.class);

   for(ContactDTO contactDTO : list.getContacts()) {
       System.out.println(contactDTO.getId());
       System.out.println(contactDTO.getEmail());
       System.out.println(contactDTO.getName());
       System.out.println(contactDTO.getLastName());
       System.out.println(contactDTO.getPhone());
       System.out.println("==============================================");



   }



}



}
