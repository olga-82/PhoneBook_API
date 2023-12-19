package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests {

  //  https://contactapp-telran-backend.herokuapp.com/
public  static final MediaType JSON = MediaType.get("application/json; charset=UTF-8");
Gson gson = new Gson();
OkHttpClient client=new OkHttpClient();

@Test
  public void loginPositive() throws IOException {
  AuthRequestDTO requestDTO=AuthRequestDTO.builder()
          .username("cherry27@mail.com")
          .password("Ch12349$")
          .build();

  RequestBody requestBody = RequestBody.create( gson.toJson(requestDTO),JSON);
  Request request = new Request.Builder()
          .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
          .post(requestBody)
          .build();

  Response response = client.newCall(request).execute();
  if(response.isSuccessful()){

  AuthResponseDTO responseDTO = gson.fromJson( response.body().string(),AuthResponseDTO.class);
    System.out.println( responseDTO.getToken());
    System.out.println("Response cod is "+response.code());
    Assert. assertTrue(response.isSuccessful());

  }else{

    System.out.println("Response cod is "+response.code());
    ErrorDTO errorDTO = gson.fromJson( response.body().string(), ErrorDTO.class);
    System.out.println( errorDTO.getStatus()+" "+errorDTO.getMessage()+" "+errorDTO.getError());
    Assert. assertTrue(response.isSuccessful());
  }

//  eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiY2hlcnJ5MjdAbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTcwMzU4OTM2MiwiaWF0IjoxNzAyOTg5MzYyfQ.LMxM_LTPXoQUgK9-4ejBttO8nxClmAm5YErtoJmHs7Y





//  cherry27@mail.com,Ch12349$

}


}
