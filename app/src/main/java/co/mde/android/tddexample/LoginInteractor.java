package co.mde.android.tddexample;

public interface LoginInteractor {
  void auth(String email, String password, Callback callback);

  interface Callback {
    void onFailure();
  }
}
