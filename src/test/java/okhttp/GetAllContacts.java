package okhttp;


import dto.ContactDTO;
import dto.ContactListDTO;
import helper.Helper;
import helper.HelperMethod;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAllContacts extends HelperMethod implements Helper {
  String token;

  @BeforeMethod
  public void precondition() {
      token=getToken();
  }
@Test
    public void testGetAllContactsPositive() throws IOException {
    Request request = new Request.Builder()
            .url(BASE_URL + "/" + PATH + "/" + endpointContacts)
            .addHeader(authHeader,token)
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
