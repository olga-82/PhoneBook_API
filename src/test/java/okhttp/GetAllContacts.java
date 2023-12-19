package okhttp;


import dto.ContactDTO;
import dto.ContactListDTO;
import helper.Helper;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAllContacts  implements Helper {
  String endpoint = "contacts";
@Test
    public void testGetAllContactsPositive() throws IOException {
    Request request = new Request.Builder()
            .url(BASE_URI + "/" + PATH + "/" + endpoint)
            .addHeader(authHeader,TOKEN)
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
