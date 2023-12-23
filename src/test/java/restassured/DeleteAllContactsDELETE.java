package restassured;

import dto.ContactResponseDTO;
import helper.Helper;
import helper.HelperMethod;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteAllContactsDELETE extends HelperMethod implements Helper {

    String endpoint = "contacts/clear";

    @BeforeMethod

    public void preconditions() {

        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = PATH;

        method.getId();
    }

    @Test
    public void deleteAllContactPositive() {

        ContactResponseDTO responseDTO = given()

                .contentType(ContentType.JSON)
                .header(authHeader,getToken())
                .when()
                .delete(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactResponseDTO.class);
        String message = responseDTO.getMessage();
        System.out.println(message);
        method.getAllContact();


    }
}