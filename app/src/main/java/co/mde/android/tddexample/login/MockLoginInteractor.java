package co.mde.android.tddexample.login;

import android.os.Handler;

public class MockLoginInteractor implements LoginInteractor {

  private final Handler handler;

  public MockLoginInteractor() {
    handler = new Handler();
  }

  @Override public void auth(final String email, final String password, final Callback callback) {
    handler.postDelayed(new Runnable() {
      @Override public void run() {
        if (email.equals("enrolled@example.com") && password.equals("valid")) {
          callback.onSuccess();
        } else {
          callback.onFailure();
        }
      }
    }, 5000);
  }
}
