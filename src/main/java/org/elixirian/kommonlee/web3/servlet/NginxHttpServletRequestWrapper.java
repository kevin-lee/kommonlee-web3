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

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.elixirian.kommonlee.web3.CommonWebConstants;

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
 * @version 0.0.1 (2013-02-10)
 */
public class NginxHttpServletRequestWrapper extends HttpServletRequestWrapper
{
  public NginxHttpServletRequestWrapper(final HttpServletRequest request)
  {
    super(request);
  }

  @Override
  public String getScheme()
  {
    return ServletUtilForNginx.getScheme(request);
  }

  @Override
  public StringBuffer getRequestURL()
  {
    final boolean isXForwardedProtocolHttps =
      CommonWebConstants.HTTPS.equals(ServletUtilForNginx.getXForwardedProtocol(request));

    if (!isXForwardedProtocolHttps)
    {
      return request.getRequestURL();
    }

    final StringBuffer url = new StringBuffer();
    final String scheme = getScheme();
    int port = getServerPort();
    if (port < 0)
    {
      port = 80; // Work around java.net.URL bug
    }

    url.append(scheme)
        .append("://")
        .append(getServerName());

    /* @formatter:off */
    if ((scheme.equals("http") && port != 80) ||
        (scheme.equals("https") && (port != 443 && port != 80)))
    {
      url.append(':')
         .append(port);
    }
    url.append(
               getRequestURI());
    /* @formatter:on */

    return url;
  }

  @Override
  public String getRemoteAddr()
  {
    return getRemoteAddr0();
  }

  private String getRemoteAddr0()
  {
    return ServletUtilForNginx.getRemoteAddr(request);
  }

  @Override
  public String getRemoteHost()
  {
    return ServletUtilForNginx.getRemoteHost(request);
  }

  /**
   * Returns true if the isSecure() method in the {@link HttpServletRequest} object this wrapper contains returns true.
   * Otherwise, it checks the header value for the name, "X-Forwarded-Proto" then if it's https, it returns true. If
   * not, false.
   */
  @Override
  public boolean isSecure()
  {
    return ServletUtilForNginx.isSecure(request);
  }

  public static ServletRequest newInstanceIfHttpServletRequest(final ServletRequest request)
  {
    if (request instanceof HttpServletRequest)
    {
      final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
      return new NginxHttpServletRequestWrapper(httpServletRequest);
    }
    return request;
  }

  public static HttpServletRequest newInstance(final HttpServletRequest request)
  {
    return new NginxHttpServletRequestWrapper(request);
  }
}
