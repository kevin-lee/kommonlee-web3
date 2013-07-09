/**
 * This project is licensed under the Apache License, Version 2.0
 * if the following condition is met:
 * (otherwise it cannot be used by anyone but the author, Kevin, only)
 *
 * The original KommonLee project is owned by Lee, Seong Hyun (Kevin).
 *
 * -What does it mean to you?
 * Nothing, unless you want to take the ownership of
 * "the original project" (not yours or forked & modified one).
 * You are free to use it for both non-commercial and commercial projects
 * and free to modify it as the Apache License allows.
 *
 * -So why is this condition necessary?
 * It is only to protect the original project (See the case of Java).
 *
 *
 * Copyright 2009 Lee, Seong Hyun (Kevin)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.elixirian.kommonlee.web3.servlet;

import static org.elixirian.kommonlee.web3.servlet.NginxConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * <pre>
 *     ___  _____                                              _____
 *    /   \/    / ______ __________________  ______ __ ______ /    /   ______  ______
 *   /        / _/ __  // /  /   / /  /   /_/ __  // //     //    /   /  ___ \/  ___ \
 *  /        \ /  /_/ _/  _  _  /  _  _  //  /_/ _/   __   //    /___/  _____/  _____/
 * /____/\____\/_____//__//_//_/__//_//_/ /_____//___/ /__//________/\_____/ \_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____                                _____
 *    /   \/    /_________  ___ ____ __ ______  /    /   ______  ______
 *   /        / /  ___ \  \/  //___// //     / /    /   /  ___ \/  ___ \
 *  /        \ /  _____/\    //   //   __   / /    /___/  _____/  _____/
 * /____/\____\\_____/   \__//___//___/ /__/ /________/\_____/ \_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2013-07-07)
 */
public class NginxHttpServletRequestWrapperTest
{

  @BeforeClass
  public static void setUpBeforeClass() throws Exception
  {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {
  }

  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public void testGetScheme()
  {
    /* given */
    final String expected = "http";

    final HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getScheme()).then(new Answer<String>() {
      @Override
      public String answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return expected;
      }
    });

    when(request.getHeader(HEADER_NAME_X_FORWARDED_PROTOCOL)).then(new Answer<String>() {
      @Override
      public String answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return null;
      }
    });

    /* when */
    final NginxHttpServletRequestWrapper requestWrapper = new NginxHttpServletRequestWrapper(request);
    final String actual = requestWrapper.getScheme();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    verify(request, times(1)).getHeader(HEADER_NAME_X_FORWARDED_PROTOCOL);
    verify(request, times(1)).getScheme();
  }

  @Test
  public void testGetScheme2()
  {
    /* given */
    final String expected = "https";

    final HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getScheme()).then(new Answer<String>() {
      @Override
      public String answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return expected;
      }
    });

    when(request.getHeader(HEADER_NAME_X_FORWARDED_PROTOCOL)).then(new Answer<String>() {
      @Override
      public String answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return "http";
      }
    });

    /* when */
    final NginxHttpServletRequestWrapper requestWrapper = new NginxHttpServletRequestWrapper(request);
    final String actual = requestWrapper.getScheme();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    verify(request, never()).getHeader(HEADER_NAME_X_FORWARDED_PROTOCOL);
    verify(request, times(1)).getScheme();
  }

  @Test
  public void testGetSchemeWithHeader()
  {
    /* given */
    final String expected = "https";

    final HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getScheme()).then(new Answer<String>() {
      @Override
      public String answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return "http";
      }
    });

    when(request.getHeader(HEADER_NAME_X_FORWARDED_PROTOCOL)).then(new Answer<String>() {
      @Override
      public String answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return expected;
      }
    });

    /* when */
    final NginxHttpServletRequestWrapper requestWrapper = new NginxHttpServletRequestWrapper(request);
    final String actual = requestWrapper.getScheme();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    verify(request, times(1)).getHeader(HEADER_NAME_X_FORWARDED_PROTOCOL);
    verify(request, times(1)).getScheme();
  }

  @Test
  public void testGetSchemeWithHeader2()
  {
    /* given */
    final String expected = "https";

    final HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getScheme()).then(new Answer<String>() {
      @Override
      public String answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return expected;
      }
    });

    when(request.getHeader(HEADER_NAME_X_FORWARDED_PROTOCOL)).then(new Answer<String>() {
      @Override
      public String answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return expected;
      }
    });

    /* when */
    final NginxHttpServletRequestWrapper requestWrapper = new NginxHttpServletRequestWrapper(request);
    final String actual = requestWrapper.getScheme();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    verify(request, never()).getHeader(HEADER_NAME_X_FORWARDED_PROTOCOL);
    verify(request, times(1)).getScheme();
  }

  @Test
  public final void testGetRemoteAddr()
  {
    /* given */
    final String headerNameXRealIp = HEADER_NAME_X_REAL_IP;
    final String expected = "1.2.3.4";

    final HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getRemoteAddr()).then(new Answer<String>() {
      @Override
      public String answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return expected;
      }
    });

    /* when */
    final NginxHttpServletRequestWrapper requestWrapper = new NginxHttpServletRequestWrapper(request);
    final String actual = requestWrapper.getRemoteAddr();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    verify(request, times(1)).getHeader(headerNameXRealIp);
    verify(request, times(1)).getRemoteAddr();
  }

  @Test
  public final void testGetRemoteAddrWithHeader()
  {
    /* given */
    final String headerNameXRealIp = HEADER_NAME_X_REAL_IP;
    final String expected = "1.2.3.4";

    final HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getHeader(headerNameXRealIp)).then(new Answer<String>() {
      @Override
      public String answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return expected;
      }
    });

    /* when */
    final NginxHttpServletRequestWrapper requestWrapper = new NginxHttpServletRequestWrapper(request);
    final String actual = requestWrapper.getRemoteAddr();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    verify(request, never()).getRemoteAddr();
    verify(request, only()).getHeader(headerNameXRealIp);
  }

  @Test
  public final void testGetRemoteHost() throws UnknownHostException
  {
    /* given */
    final String headerNameHost = HEADER_NAME_HOST;
    final String expected = InetAddress.getByName(null)
        .getHostName();

    final HttpServletRequest request = mock(HttpServletRequest.class);

    when(request.getHeader(HEADER_NAME_X_REAL_IP)).then(new Answer<String>() {
      @Override
      public String answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return null;
      }
    });

    when(request.getRemoteAddr()).then(new Answer<String>() {
      @Override
      public String answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return null;
      }
    });

    /* when */
    final NginxHttpServletRequestWrapper requestWrapper = new NginxHttpServletRequestWrapper(request);
    final String actual = requestWrapper.getRemoteHost();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    verify(request, times(1)).getHeader(headerNameHost);
    verify(request, times(1)).getHeader(HEADER_NAME_X_REAL_IP);
    verify(request, times(1)).getRemoteAddr();
    verify(request, never()).getRemoteHost();
  }

  @Test
  public final void testGetRemoteHostWithHeader()
  {
    /* given */
    final String headerNameHost = HEADER_NAME_HOST;
    final String expected = "Some-HOSTNAME";

    final HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getHeader(headerNameHost)).then(new Answer<String>() {
      @Override
      public String answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return expected;
      }
    });

    /* when */
    final NginxHttpServletRequestWrapper requestWrapper = new NginxHttpServletRequestWrapper(request);
    final String actual = requestWrapper.getRemoteHost();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    verify(request, only()).getHeader(headerNameHost);
    verify(request, never()).getHeader(HEADER_NAME_X_REAL_IP);
    verify(request, never()).getRemoteAddr();
    verify(request, never()).getRemoteHost();
  }

  @Test
  public final void testIsSecure()
  {
    /* given */
    final Boolean expected = Boolean.TRUE;

    final HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.isSecure()).then(new Answer<Boolean>() {
      @Override
      public Boolean answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return Boolean.TRUE;
      }
    });

    /* when */
    final NginxHttpServletRequestWrapper requestWrapper = new NginxHttpServletRequestWrapper(request);
    @SuppressWarnings("boxing")
    final Boolean actual = requestWrapper.isSecure();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    verify(request, only()).isSecure();
    verify(request, never()).getHeader(HEADER_NAME_X_FORWARDED_PROTOCOL);
  }

  @Test
  public final void testIsSecureWithHeader()
  {
    /* given */
    final Boolean expected = Boolean.FALSE;
    final String headerNameXForwardedProtocol = HEADER_NAME_X_FORWARDED_PROTOCOL;

    final HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.isSecure()).then(new Answer<Boolean>() {
      @Override
      public Boolean answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return Boolean.FALSE;
      }
    });

    when(request.getHeader(headerNameXForwardedProtocol)).then(new Answer<String>() {
      @Override
      public String answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return null;
      }
    });

    /* when */
    final NginxHttpServletRequestWrapper requestWrapper = new NginxHttpServletRequestWrapper(request);
    @SuppressWarnings("boxing")
    final Boolean actual = requestWrapper.isSecure();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    verify(request, times(1)).isSecure();
    verify(request, times(1)).getHeader(headerNameXForwardedProtocol);
  }

  @Test
  public final void testIsSecureWithHeader2()
  {
    /* given */
    final Boolean expected = Boolean.FALSE;
    final String headerNameXForwardedProtocol = HEADER_NAME_X_FORWARDED_PROTOCOL;

    final HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.isSecure()).then(new Answer<Boolean>() {
      @Override
      public Boolean answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return Boolean.FALSE;
      }
    });

    when(request.getHeader(headerNameXForwardedProtocol)).then(new Answer<String>() {
      @Override
      public String answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return "http";
      }
    });

    /* when */
    final NginxHttpServletRequestWrapper requestWrapper = new NginxHttpServletRequestWrapper(request);
    @SuppressWarnings("boxing")
    final Boolean actual = requestWrapper.isSecure();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    verify(request, times(1)).isSecure();
    verify(request, times(1)).getHeader(headerNameXForwardedProtocol);
  }

  @Test
  public final void testIsSecureWithHeader3()
  {
    /* given */
    final Boolean expected = Boolean.TRUE;
    final String headerNameXForwardedProtocol = HEADER_NAME_X_FORWARDED_PROTOCOL;

    final HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.isSecure()).then(new Answer<Boolean>() {
      @Override
      public Boolean answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return Boolean.FALSE;
      }
    });

    when(request.getHeader(headerNameXForwardedProtocol)).then(new Answer<String>() {
      @Override
      public String answer(@SuppressWarnings("unused") final InvocationOnMock invocation) throws Throwable
      {
        return "https";
      }
    });

    /* when */
    final NginxHttpServletRequestWrapper requestWrapper = new NginxHttpServletRequestWrapper(request);
    @SuppressWarnings("boxing")
    final Boolean actual = requestWrapper.isSecure();

    /* then */
    assertThat(actual, is(equalTo(expected)));
    verify(request, times(1)).isSecure();
    verify(request, times(1)).getHeader(headerNameXForwardedProtocol);
  }

  @Test
  public final void testNginxHttpServletRequestWrapper()
  {
    /* given */
    final HttpServletRequest expected = mock(HttpServletRequest.class);

    /* when */
    final NginxHttpServletRequestWrapper requestWrapper = new NginxHttpServletRequestWrapper(expected);
    final HttpServletRequest actual = requestWrapper.getRequest();

    /* then */
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testNewInstanceIfHttpServletRequest()
  {
    /* given */
    final HttpServletRequest expected = mock(HttpServletRequest.class);

    /* when */
    final ServletRequest actual = NginxHttpServletRequestWrapper.newInstanceIfHttpServletRequest(expected);

    /* then */
    assertThat(actual, is(instanceOf(NginxHttpServletRequestWrapper.class)));

    final NginxHttpServletRequestWrapper actualHttpServletRequestWrapper = (NginxHttpServletRequestWrapper) actual;
    final HttpServletRequest actualRequest = actualHttpServletRequestWrapper.getRequest();

    assertThat(actualRequest, is(equalTo(expected)));
  }

  @Test
  public final void testNewInstanceIfHttpServletRequest2()
  {
    /* given */
    final ServletRequest expected = mock(ServletRequest.class);

    /* when */
    final ServletRequest actual = NginxHttpServletRequestWrapper.newInstanceIfHttpServletRequest(expected);

    /* then */
    assertThat(actual, is(instanceOf(ServletRequest.class)));
    assertThat(actual, is(not(instanceOf(NginxHttpServletRequestWrapper.class))));
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public final void testNewInstance()
  {
    /* given */
    final HttpServletRequest expected = mock(HttpServletRequest.class);

    /* when */
    final HttpServletRequest request = NginxHttpServletRequestWrapper.newInstance(expected);

    /* then */
    assertThat(request, is(instanceOf(NginxHttpServletRequestWrapper.class)));

    final NginxHttpServletRequestWrapper actualHttpServletRequestWrapper = (NginxHttpServletRequestWrapper) request;
    final HttpServletRequest actual = actualHttpServletRequestWrapper.getRequest();

    assertThat(actual, is(equalTo(expected)));
  }

}
