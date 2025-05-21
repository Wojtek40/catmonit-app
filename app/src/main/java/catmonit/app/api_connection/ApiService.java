package catmonit.app.api_connection;

import catmonit.app.api_connection.models.CreateUserRequest;
import catmonit.app.api_connection.models.GenericResponse;
import catmonit.app.api_connection.models.LoginRequest;
import catmonit.app.api_connection.models.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/Login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("api/Login/userCheck")
    Call<?> userCheck();

    @POST("admin/createUser")
    Call<GenericResponse> createUser(@Body CreateUserRequest createUserRequest);
}
