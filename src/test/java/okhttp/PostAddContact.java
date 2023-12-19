package okhttp;

import com.google.gson.Gson;
import dto.ContactDTO;
import dto.ContactResponseDTO;
import dto.ErrorDTO;
import helper.Helper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static okhttp.LoginTests.JSON;

public class PostAddContact implements Helper {

 String endpoint="contacts";

    @Test
    public void AddNewContact() throws IOException {
        ContactDTO contactDTO =  ContactDTO.builder()
                .name("rita")
                .lastName("pupkina")
                .email("pupkina"+i+"@gmail.com")
                .phone("65748396"+i)
                .address("Tel Aviv")
                .description("sister")
                .build();
       // ID: c4eb123e-7cd2-4864-8d06-b8dcb49bf217

        RequestBody requestBody = RequestBody.create( gson.toJson(contactDTO),JSON);
        Request request = new Request.Builder()
                .url(BASE_URI + "/" + PATH + "/" + endpoint)
                .addHeader(authHeader,TOKEN)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){

            ContactResponseDTO responseDTO = gson.fromJson( response.body().string(), ContactResponseDTO.class);
            String message = responseDTO.getMessage();
            System.out.println("Response cod is "+response.code());
            System.out.println(message);
            Assert. assertTrue(response.isSuccessful());


//            String id = message.substring(message.lastIndexOf(" ") + 1);
//            System.out.println(id);


        }else{

            System.out.println("Response cod is "+response.code());
            ErrorDTO errorDTO = gson.fromJson( response.body().string(), ErrorDTO.class);
            System.out.println( errorDTO.getStatus()+" "+errorDTO.getMessage()+" "+errorDTO.getError());
            Assert. assertTrue(response.isSuccessful());
        }
    }
}
