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
package co.mde.android.tddexample;

import co.mde.android.tddexample.login.LoginInteractor;
import co.mde.android.tddexample.login.RetrofitLoginInteractor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class RetrofitLoginInteractorTest {

  private MockWebServer server = new MockWebServer();
  private RetrofitLoginInteractor interactor;
  @Mock private LoginInteractor.Callback callback;

  @Before public void setup() throws Exception {
    MockitoAnnotations.initMocks(this);
    server.start();
    interactor = new RetrofitLoginInteractor(server.url("/").toString());
  }

  @After public void tearDown() throws Exception {
    server.shutdown();
  }

  @Test public void callFailureWhenNoResponse() throws Exception {
    server.enqueue(new MockResponse().setResponseCode(500));
    interactor.auth("enrolled@example.com", "valid", callback);
    Thread.sleep(200);
    Mockito.verify(callback).onFailure();
  }

  @Test public void callSuccess() throws Exception {
    server.enqueue(new MockResponse().setResponseCode(200).setBody("{\"username\":\"Android\"}"));
    interactor.auth("enrolled@example.com", "valid", callback);
    Thread.sleep(200);
    Mockito.verify(callback).onSuccess();
  }
}
