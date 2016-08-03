/*************************************************************************
 * CONFIDENTIAL
 * __________________
 *
 * [2016] All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of {The Company} and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to {The Company}
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from {The Company}.
 */
package co.mde.android.tddexample.login;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RetrofitLoginInteractor implements LoginInteractor {
  private final String baseUrl;
  private final LoginService service;

  public RetrofitLoginInteractor(String baseUrl) {
    this.service = new Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LoginService.class);
    this.baseUrl = baseUrl;
  }

  @Override public void auth(String email, String password, final Callback callback) {
    service.login(email, password).enqueue(new retrofit2.Callback<LoginData>() {
      @Override public void onResponse(Call<LoginData> call, Response<LoginData> response) {
        if (response.isSuccessful()) {
          callback.onSuccess();
        } else {
          callback.onFailure();
        }
      }

      @Override public void onFailure(Call<LoginData> call, Throwable t) {
        callback.onFailure();
      }
    });
  }

  private interface LoginService {
    @GET("login") Call<LoginData> login(@Query("username") String username,
        @Query("password") String password);
  }
}
