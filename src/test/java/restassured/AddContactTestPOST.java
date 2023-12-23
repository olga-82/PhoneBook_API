package restassured;

import dto.ContactDTO;
import dto.ContactResponseDTO;
import helper.Helper;
import helper.HelperMethod;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static helper.HelperMethod.method;
import static io.restassured.RestAssured.given;

public class AddContactTestPOST extends HelperMethod implements Helper {

    @BeforeMethod

    public void preconditions(){

        RestAssured.baseURI=BASE_URL;
        RestAssured.basePath= PATH;


    }

    @Test
    public void addNewContactPositive() {

        ContactResponseDTO responseDTO = given()
                .body(CONTACT_DTO)
                .contentType(ContentType.JSON)
                .header(authHeader,getToken())
                .when()
                .post(endpointContacts)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactResponseDTO.class);
                 String message =responseDTO.getMessage();
                 System.out.println(message);
                 String id = message.substring(message.lastIndexOf(" ") + 1);
                 System.out.println(id);

    }


}
