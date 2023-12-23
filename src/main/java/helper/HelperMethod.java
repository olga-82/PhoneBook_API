package helper;

import dto.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class HelperMethod implements Helper {

 public static HelperMethod method = new HelperMethod();

    String id;



    public String addNewContact() throws IOException {
       // String token =getToken();

        RequestBody requestBody = RequestBody.create(gson.toJson(CONTACT_DTO), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + "/" + PATH + "/" + endpointContacts)
                .addHeader(authHeader,getToken())
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        ContactResponseDTO contactResponseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);

        Assert.assertTrue(response.isSuccessful());
        String message = contactResponseDTO.getMessage();
        System.out.println(message);
        id = message.substring(message.lastIndexOf(" ") + 1);

        return id;
    }

    public String getId() {

        ContactResponseDTO responseDTO = given()
                .body(CONTACT_DTO)
                .contentType(ContentType.JSON)
                .header(authHeader, getToken())
                .when()
                .post(endpointContacts)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactResponseDTO.class);
        String message = responseDTO.getMessage();
        System.out.println(message);
        String id = message.substring(message.lastIndexOf(" ") + 1);
        System.out.println(id);
        return id;
    }

    public ContactListDTO getAllContact(){
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
        return list;

    }
    public String getToken(){

        RestAssured.baseURI=BASE_URL;
        RestAssured.basePath= PATH;

        String token = RestAssured
                .given()
                .log().all()
                .when()
                .contentType(ContentType.JSON)
                .body(LOGIN_BODY)
                .post(endpointLogin)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response().jsonPath().getString("token");
        return token;

    }

}





