package co.mde.android.tddexample;

public class LoginPresenter {
  private final LoginView view;
  private final LoginInteractor interactor;

  public LoginPresenter(LoginView view, LoginInteractor interactor) {
    this.view = view;
    this.interactor = interactor;
  }

  public void login() {
    view.showLoading();
    interactor.auth(view.username(), view.password(), new LoginInteractor.Callback() {
      @Override public void onFailure() {
        view.hideLoading();
        view.showLogin();
        view.showLoginError();
      }

      @Override public void onSuccess() {
        view.showHomeScreen();
      }
    });
  }
}
