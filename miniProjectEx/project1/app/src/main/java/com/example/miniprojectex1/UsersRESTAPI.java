package com.example.miniprojectex1;

import retrofit2.http.GET;
import retrofit2.Call;

public interface UsersRESTAPI {
    @GET("users/")
    Call<Users> getUsers();
}
