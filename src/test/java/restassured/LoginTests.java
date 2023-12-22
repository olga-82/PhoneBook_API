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

public class LoginTests implements Helper {
    String endpoint = "user/login/usernamepassword";
    @BeforeMethod
    public void precondition(){
       RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = PATH;

    }

    @Test
    public void loginTestPositive(){
        AuthRequestDTO requestDTO=AuthRequestDTO.builder()
                .username("cherry27@mail.com")
                .password("Ch12349$")
                .build();

        AuthResponseDTO responseDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
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
                .post(endpoint)
                .then()
                .assertThat().statusCode(401)
                .extract()
                .as(ErrorDTO.class);
        System.out.println(errorDTO.getMessage());



    }







}
