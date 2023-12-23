package okhttp;

import dto.ContactResponseDTO;
import helper.Helper;
import helper.HelperMethod;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteAllContacts implements Helper {
    HelperMethod method = new HelperMethod();
    String endpoint = "contacts/clear";
    String token;
    @BeforeMethod
    public void preconditions() throws IOException {
        token=   method.getToken();
         method.addNewContact();
    }


    @Test
    public void deleteContactByIDPositive() throws IOException {

        Request request = new Request.Builder()
                .url(BASE_URL + "/" + PATH + "/" + endpoint)
                .addHeader(authHeader,token)
                .delete()
                .build();

        Response response = client.newCall(request).execute();

        ContactResponseDTO contactResponseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);

        Assert.assertTrue(response.isSuccessful());
        String message = contactResponseDTO.getMessage();
        System.out.println(message);

    }
}
