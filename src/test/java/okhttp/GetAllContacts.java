package okhttp;

import com.google.gson.Gson;
import dto.ContactDTO;
import dto.ContactListDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAllContacts {
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiY2hlcnJ5MjdAbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTcwMzU4OTM2MiwiaWF0IjoxNzAyOTg5MzYyfQ.LMxM_LTPXoQUgK9-4ejBttO8nxClmAm5YErtoJmHs7Y";
@Test
    public void testGetAllContactsPositive() throws IOException {
    Request request = new Request.Builder()
            .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
            .addHeader("Authorization",token)
            .build();
    Response response = client.newCall(request).execute();
    Assert.assertTrue(response.isSuccessful(),"200");

    ContactListDTO contacts = gson.fromJson(response.body().string(),ContactListDTO.class);
    for (ContactDTO contactDTO:contacts.getContacts()) {

        System.out.println(contactDTO.getId());
        System.out.println(contactDTO.getName());
        System.out.println(contactDTO.getLastName());
        System.out.println(contactDTO.getEmail());
        System.out.println(contactDTO.getPhone());
        System.out.println(contactDTO.getAddress());
        System.out.println(contactDTO.getDescription());
        System.out.println("============================================================================");

    }


}



}
