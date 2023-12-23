package restassured;

import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import helper.Helper;
import helper.HelperMethod;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTestsPOST extends HelperMethod implements Helper {


    @BeforeMethod
    public void precondition(){
       RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = PATH;

    }

    @Test
    public void loginTestPositive(){

        AuthResponseDTO responseDTO = given()
                .body(LOGIN_BODY )
                .contentType(ContentType.JSON)
                .when()
                .post(endpointLogin)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(AuthResponseDTO.class);
        System.out.println(responseDTO.getToken());
        System.out.println(responseDTO);


    }
    @Test
    public void loginTestNegative(){
        AuthRequestDTO requestDTO=AuthRequestDTO.builder()
                .username("cherry27mail.com")
                .password("Ch12349$")
                .build();

       ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpointLogin)
                .then()
                .assertThat().statusCode(401)
                .extract()
                .as(ErrorDTO.class);
        System.out.println(errorDTO.getMessage());



    }







}
