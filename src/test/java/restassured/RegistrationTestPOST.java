package restassured;

import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import helper.Helper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class RegistrationTestPOST implements Helper {




    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = PATH;

    }
    @Test
    public void testRegistrationPositive(){
        AuthResponseDTO responseDTO = given()
                .log().all()
                .body(REG_BODY)
                .contentType(ContentType.JSON)
                .when()
                .post(endpointReg)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(AuthResponseDTO.class);


        System.out.println(responseDTO.getToken());
        System.out.println(responseDTO);




    }
    @Test
    public void testRegistrationNegative(){
        ErrorDTO errorDTO= given()
                .log().all()
                .body(INVALID_REG_BODY)
                .contentType(ContentType.JSON)
                .when()
                .post(endpointReg)
                .then()
                .assertThat().statusCode(401)
                .extract()
                .as(ErrorDTO.class);
        System.out.println(errorDTO.getMessage());



    }



}
