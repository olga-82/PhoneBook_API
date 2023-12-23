package okhttp;

import com.google.gson.Gson;
import dto.ContactDTO;
import dto.ContactResponseDTO;
import dto.ErrorDTO;
import helper.Helper;
import helper.HelperMethod;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static okhttp.LoginTests.JSON;

public class PostAddContact extends HelperMethod implements Helper {
String token;


    @Test
    public void AddNewContact() throws IOException {
        token = getToken();
        RequestBody requestBody = RequestBody.create( gson.toJson(CONTACT_DTO),JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + PATH + "/" + endpointContacts)
                .addHeader(authHeader,token)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

            ContactResponseDTO responseDTO = gson.fromJson( response.body().string(), ContactResponseDTO.class);

            Assert. assertTrue(response.isSuccessful());
            String message = responseDTO.getMessage();
            System.out.println(message);
            String id = message.substring(message.lastIndexOf(" ") + 1);
            System.out.println(id);


    }
}
