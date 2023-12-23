package helper;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.ContactDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.util.Random;

public interface Helper {
    public static final MediaType JSON = MediaType.get("application/json; charset=UTF-8");
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
   // String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiY2hlcnJ5MjdAbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTcwMzU4OTM2MiwiaWF0IjoxNzAyOTg5MzYyfQ.LMxM_LTPXoQUgK9-4ejBttO8nxClmAm5YErtoJmHs7Y";

    String BASE_URL = "https://contactapp-telran-backend.herokuapp.com";
    String PATH = "v1";
    String endpointLogin="user/login/usernamepassword";
    String endpointReg="user/login/usernamepassword";

    String endpointContacts = "contacts";

    String authHeader = "Authorization";

    int i = new Random().nextInt(1000) + 1000;


    ContactDTO CONTACT_DTO = ContactDTO.builder()
            .name("rita")
            .lastName("jenkins")
            .email("jenk"+i+"@gmail.com")
            .phone("69848888"+i)
            .address("Tel Aviv")
            .description("sister")
            .build();

    AuthRequestDTO LOGIN_BODY = AuthRequestDTO.builder()
            .username("cherry27@mail.com")
            .password("Ch12349$")
            .build();

    AuthRequestDTO REG_BODY=AuthRequestDTO.builder()
            .username("sandra"+i+"@mail.com")
            .password("Ch123477$")
            .build();

    AuthRequestDTO INVALID_REG_BODY=AuthRequestDTO.builder()
            .username("sierra"+i+"@mail.com")
            .password("Ch123459")
            .build();


}
