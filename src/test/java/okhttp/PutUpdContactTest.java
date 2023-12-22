package okhttp;

import dto.*;
import helper.Helper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class PutUpdContactTest implements Helper {
//
//    d9452605-cf56-448a-bde2-d1203452e792
//    cher_308@gmail.com
//            Sara_308
//    Braun
//65840033308

    String endpoint = "contacts";
    String id = "d9452605-cf56-448a-bde2-d1203452e792";

    @Test
    public void UpdateContactTestPositive() throws IOException {
        ContactDTO contactDTO = ContactDTO.builder()
                .id(id)
                .name("Sara_Maria")
                .lastName("Braun")
                .email("cher_308@gmail.com")
                .phone("65840033308")
                .address("Bat yam")
                .description("sister")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(contactDTO), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + PATH + "/" + endpoint)
                .addHeader(authHeader, TOKEN)
                .put(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {

            ContactResponseDTO responseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);
            System.out.println("Response cod is " + response.code());
            Assert.assertTrue(response.isSuccessful());
            System.out.println(responseDTO.getMessage());

        } else {
            System.out.println("Response cod is " + response.code());
            ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
            System.out.println(errorDTO.getStatus() + " " + errorDTO.getMessage() + " " + errorDTO.getError());
            Assert.assertTrue(response.isSuccessful());
        }
    }
}