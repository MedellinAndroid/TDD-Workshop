package co.mde.android.tddexample.login;

public interface LoginInteractor {
  void auth(String email, String password, Callback callback);

  interface Callback {
    void onFailure();

    void onSuccess();
  }
}
