package tk.codetroopers.erscheduler.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tk.codetroopers.erscheduler.models.BaseReponse;
import tk.codetroopers.erscheduler.models.Shift;
import tk.codetroopers.erscheduler.models.User;

public interface ApiService {

    @GET("/api/login")
    Call<BaseReponse> login(@Query("username") String username,
                            @Query("password") String password);

    @GET("/api/signup")
    Call<BaseReponse> signup(@Query("username") String username,
                             @Query("password") String password,
                             @Query("email") String email);

    @GET("/api/logout")
    Call<BaseReponse> logout(@Query("token") String token);

    @GET("/api/find")
    Call<User> getUser(@Query("modelName") String modelName,
                       @Query("token") String token);

    // TODO create new response model
    @GET("/api/get-schedule")
    Call<Shift> getSchedule(@Query("token") String token);

}
