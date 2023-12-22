package helper;

import dto.ContactDTO;
import dto.ContactResponseDTO;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;

import java.io.IOException;

public class HelperMethod implements Helper{
    String endpoint = "contacts";
    String id;


    public String addNewContact() throws IOException {


        ContactDTO contactDTO = ContactDTO.builder()
                .name("QA38")
                .lastName("Automation")
                .email("qa38_" + i + "@mail.com")
                .phone("12345678" + i)
                .address("Rehovot")
                .description("Students")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(contactDTO), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + "/" + PATH + "/" + endpoint)
                .addHeader(authHeader, TOKEN)
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

}
