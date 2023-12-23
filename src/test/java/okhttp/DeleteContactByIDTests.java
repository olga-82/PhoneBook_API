package okhttp;

import dto.ContactDTO;
import dto.ContactResponseDTO;
import helper.Helper;
import helper.HelperMethod;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteContactByIDTests implements Helper {
     String token;

     String id;
    HelperMethod method = new HelperMethod();
    @BeforeMethod
    public void precondition() throws IOException {
     id =  method.addNewContact();
     token= method.getToken();
    }
    @Test
    public void deleteContactByIDPositive() throws IOException {

        Request request = new Request.Builder()
                .url(BASE_URL + "/" + PATH + "/" + endpointContacts + "/" + id)
                .addHeader(authHeader, token)
                .delete()
                .build();

        Response response = client.newCall(request).execute();

        ContactResponseDTO contactResponseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);

        Assert.assertTrue(response.isSuccessful());
        String message = contactResponseDTO.getMessage();
        System.out.println(message);

    }
}