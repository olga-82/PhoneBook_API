package helper;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.util.Random;

public interface Helper {
    public  static final MediaType JSON = MediaType.get("application/json; charset=UTF-8");
    Gson gson = new Gson();
    OkHttpClient client=new OkHttpClient();
    String TOKEN= "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiY2hlcnJ5MjdAbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTcwMzU4OTM2MiwiaWF0IjoxNzAyOTg5MzYyfQ.LMxM_LTPXoQUgK9-4ejBttO8nxClmAm5YErtoJmHs7Y";

    String BASE_URI = "https://contactapp-telran-backend.herokuapp.com";
    String PATH = "v1";

    String authHeader = "Authorization";

    int i = new Random().nextInt(1000) + 1000;


}
