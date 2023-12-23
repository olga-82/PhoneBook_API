package restassured;

import dto.ContactDTO;
import dto.ContactResponseDTO;
import helper.Helper;
import helper.HelperMethod;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateContactPUT extends HelperMethod implements Helper {
HelperMethod method = new HelperMethod();

    String id;

    @BeforeMethod

    public void preconditions() {

        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = PATH;
        method.getAllContact();

    }

    @Test
    public void updateContactPositive(){
        ContactDTO contactDTO = ContactDTO.builder()
                .id(getId())
                .name("Maria")
                .lastName("Braun")
                .email("cher_900@gmail.com")
                .phone("65840033307")
                .address("Tel Aviv")
                .description("sister")
                .build();

        ContactResponseDTO responseDTO = given()
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .header(authHeader,getToken())
                .when()
                .put(endpointContacts)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactResponseDTO.class);
          String message = responseDTO.getMessage();
          System.out.println(message);
            method.getAllContact();


    }


}