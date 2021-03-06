package co.mde.android.tddexample;

import co.mde.android.tddexample.login.LoginInteractor;
import co.mde.android.tddexample.login.LoginPresenter;
import co.mde.android.tddexample.login.LoginView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginPresenterTest {

  //Collaborators
  @Mock LoginView view;
  @Mock LoginInteractor interactor;
  @Captor ArgumentCaptor<LoginInteractor.Callback> captor;

  private LoginPresenter presenter;

  @Before public void setup() {
    MockitoAnnotations.initMocks(this);
    presenter = new LoginPresenter(view, interactor);
  }

  @Test public void noActionsShowsLogin() {
    verify(view).showLogin();
    verify(view).hideLoading();
  }

  @Test public void showLoadingOnClick() throws Exception {
    //Given
    when(view.username()).thenReturn("registered@example.com");
    when(view.password()).thenReturn("valid");
    //When
    presenter.login();
    //Then
    verify(view).showLoading();
    verify(view).hideLogin();
  }

  @Test public void showLoginScreenAndErrorOnInteractorFailure() {
    //Given
    when(view.username()).thenReturn("non-registered@example.com");
    when(view.password()).thenReturn("invalid");
    //When
    presenter.login();
    verify(interactor).auth(eq("non-registered@example.com"), eq("invalid"), captor.capture());
    captor.getValue().onFailure();

    //Then
    verify(view, times(2)).showLogin();
    verify(view, times(2)).hideLoading();
    verify(view).showLoginError();
  }

  @Test public void showHomeScreenOnInteractorSuccess() {
    //Given
    when(view.username()).thenReturn("registered@example.com");
    when(view.password()).thenReturn("valid");
    //When
    presenter.login();
    verify(interactor).auth(eq("registered@example.com"), eq("valid"), captor.capture());
    captor.getValue().onSuccess();

    //Then
    verify(view).showHomeScreen();
  }
}