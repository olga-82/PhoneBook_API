package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import helper.Helper;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTests implements Helper {
    String endpoint = "user/registration/usernamepassword";


    @Test
    public void registrationPositive() throws IOException {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("scarlet" + i + "@mail.com")
                .password("Sc12349$")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + PATH + "/" + endpoint)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();


        AuthResponseDTO responseDTO = gson.fromJson(response.body().string(), AuthResponseDTO.class);
        System.out.println(responseDTO.getToken());
        System.out.println("Response cod is " + response.code());
        Assert.assertTrue(response.isSuccessful());


    }
    @Test
    public void registrationNegative() throws IOException {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("scarlet" + i + "mail.com")
                .password("Sc12349$")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + PATH + "/" + endpoint)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println("Response cod is "+response.code());
        ErrorDTO errorDTO = gson.fromJson( response.body().string(), ErrorDTO.class);
        System.out.println( errorDTO.getStatus()+" "+errorDTO.getMessage()+" "+errorDTO.getError());

    }
}