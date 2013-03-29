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

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
 * @version 0.0.1 (2013-02-11)
 */
public class HttpServletRequestWrapper extends ServletRequestWrapper<HttpServletRequest> implements HttpServletRequest
{

  public HttpServletRequestWrapper(final HttpServletRequest servletRequest)
  {
    super(servletRequest);
  }

  @Override
  public String getAuthType()
  {
    return request.getAuthType();
  }

  @Override
  public Cookie[] getCookies()
  {
    return request.getCookies();
  }

  @Override
  public long getDateHeader(final String name)
  {
    return request.getDateHeader(name);
  }

  @Override
  public String getHeader(final String name)
  {
    return request.getHeader(name);
  }

  @Override
  public Enumeration<String> getHeaders(final String name)
  {
    final Enumeration<String> headers = request.getHeaders(name);
    return headers;
  }

  @Override
  public Enumeration<String> getHeaderNames()
  {
    final Enumeration<String> headerNames = request.getHeaderNames();
    return headerNames;
  }

  @Override
  public int getIntHeader(final String name)
  {
    return request.getIntHeader(name);
  }

  @Override
  public String getMethod()
  {
    return request.getMethod();
  }

  @Override
  public String getPathInfo()
  {
    return request.getPathInfo();
  }

  @Override
  public String getPathTranslated()
  {
    return request.getPathTranslated();
  }

  @Override
  public String getContextPath()
  {
    return request.getContextPath();
  }

  @Override
  public String getQueryString()
  {
    return request.getQueryString();
  }

  @Override
  public String getRemoteUser()
  {
    return request.getRemoteUser();
  }

  @Override
  public boolean isUserInRole(final String role)
  {
    return request.isUserInRole(role);
  }

  @Override
  public Principal getUserPrincipal()
  {
    return request.getUserPrincipal();
  }

  @Override
  public String getRequestedSessionId()
  {
    return request.getRequestedSessionId();
  }

  @Override
  public String getRequestURI()
  {
    return request.getRequestURI();
  }

  @Override
  public StringBuffer getRequestURL()
  {
    return request.getRequestURL();
  }

  @Override
  public String getServletPath()
  {
    return request.getServletPath();
  }

  @Override
  public HttpSession getSession(final boolean create)
  {
    return request.getSession(create);
  }

  @Override
  public HttpSession getSession()
  {
    return request.getSession();
  }

  @Override
  public boolean isRequestedSessionIdValid()
  {
    return request.isRequestedSessionIdValid();
  }

  @Override
  public boolean isRequestedSessionIdFromCookie()
  {
    return request.isRequestedSessionIdFromCookie();
  }

  @Override
  public boolean isRequestedSessionIdFromURL()
  {
    return request.isRequestedSessionIdFromURL();
  }

  /**
   * @deprecated As of Version 2.1 of the Java Servlet API, use {@link #isRequestedSessionIdFromURL} instead.
   */
  @Deprecated
  @Override
  public boolean isRequestedSessionIdFromUrl()
  {
    return request.isRequestedSessionIdFromUrl();
  }

  @Override
  public boolean authenticate(final HttpServletResponse response) throws IOException, ServletException
  {
    return request.authenticate(response);
  }

  @Override
  public void login(final String username, final String password) throws ServletException
  {
    request.login(username, password);

  }

  @Override
  public void logout() throws ServletException
  {
    request.logout();
  }

  @Override
  public Collection<Part> getParts() throws IOException, ServletException
  {
    return request.getParts();
  }

  @Override
  public Part getPart(final String name) throws IOException, ServletException
  {
    return request.getPart(name);
  }
}
