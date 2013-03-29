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

import static org.elixirian.kommonlee.util.Objects.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
public class ServletRequestWrapper<T extends ServletRequest> implements ServletRequest
{
  protected final T request;

  public ServletRequestWrapper(final T servletRequest)
  {
    this.request = servletRequest;
  }

  public T getRequest()
  {
    return request;
  }

  @Override
  public Object getAttribute(final String name)
  {
    return request.getAttribute(name);
  }

  @Override
  public Enumeration<String> getAttributeNames()
  {
    final Enumeration<String> attributeNames = request.getAttributeNames();
    return attributeNames;
  }

  @Override
  public String getCharacterEncoding()
  {
    return request.getCharacterEncoding();
  }

  @Override
  public void setCharacterEncoding(final String env) throws UnsupportedEncodingException
  {
    request.setCharacterEncoding(env);
  }

  @Override
  public int getContentLength()
  {
    return request.getContentLength();
  }

  @Override
  public String getContentType()
  {
    return request.getContentType();
  }

  @Override
  public ServletInputStream getInputStream() throws IOException
  {
    return request.getInputStream();
  }

  @Override
  public String getParameter(final String name)
  {
    return request.getParameter(name);
  }

  @Override
  public Enumeration<String> getParameterNames()
  {
    final Enumeration<String> parameterNames = request.getParameterNames();
    return parameterNames;
  }

  @Override
  public String[] getParameterValues(final String name)
  {
    return request.getParameterValues(name);
  }

  @Override
  public Map<String, String[]> getParameterMap()
  {
    final Map<String, String[]> parameterMap = request.getParameterMap();
    return parameterMap;
  }

  @Override
  public String getProtocol()
  {
    return request.getProtocol();
  }

  @Override
  public String getScheme()
  {
    return request.getScheme();
  }

  @Override
  public String getServerName()
  {
    return request.getServerName();
  }

  @Override
  public int getServerPort()
  {
    return request.getServerPort();
  }

  @Override
  public BufferedReader getReader() throws IOException
  {
    return request.getReader();
  }

  @Override
  public String getRemoteAddr()
  {
    return request.getRemoteAddr();
  }

  @Override
  public String getRemoteHost()
  {
    return request.getRemoteHost();
  }

  @Override
  public void setAttribute(final String name, final Object o)
  {
    request.setAttribute(name, o);
  }

  @Override
  public void removeAttribute(final String name)
  {
    request.removeAttribute(name);
  }

  @Override
  public Locale getLocale()
  {
    return request.getLocale();
  }

  @Override
  public Enumeration<Locale> getLocales()
  {
    final Enumeration<Locale> locales = request.getLocales();
    return locales;
  }

  @Override
  public boolean isSecure()
  {
    return request.isSecure();
  }

  @Override
  public RequestDispatcher getRequestDispatcher(final String path)
  {
    return request.getRequestDispatcher(path);
  }

  /**
   * @deprecated As of Version 2.1 of the Java Servlet API, use {@link ServletContext#getRealPath} instead.
   */
  @Deprecated
  @Override
  public String getRealPath(final String path)
  {
    return request.getRealPath(path);
  }

  @Override
  public int getRemotePort()
  {
    return request.getRemotePort();
  }

  @Override
  public String getLocalName()
  {
    return request.getLocalName();
  }

  @Override
  public String getLocalAddr()
  {
    return request.getLocalAddr();
  }

  @Override
  public int getLocalPort()
  {
    return request.getLocalPort();
  }

  @Override
  public int hashCode()
  {
    return hashCodeOf(request);
  }

  @Override
  public boolean equals(final Object request)
  {
    if (this == request)
    {
      return true;
    }
    final ServletRequestWrapper<?> that = castIfInstanceOf(ServletRequestWrapper.class, request);
    /* @formatter:off */
    return null != that &&
              equal(this.request, that.getRequest());
    /* @formatter:on */
  }

  @Override
  public String toString()
  {
    /* @formatter:off */
    return toStringBuilder(this)
            .add("request", request)
          .toString();
    /* @formatter:on */
  }

  @Override
  public AsyncContext getAsyncContext()
  {
    return request.getAsyncContext();
  }

  @Override
  public DispatcherType getDispatcherType()
  {
    return request.getDispatcherType();
  }

  @Override
  public ServletContext getServletContext()
  {
    return request.getServletContext();
  }

  @Override
  public boolean isAsyncStarted()
  {
    return request.isAsyncStarted();
  }

  @Override
  public boolean isAsyncSupported()
  {
    return request.isAsyncSupported();
  }

  @Override
  public AsyncContext startAsync() throws IllegalStateException
  {
    return request.startAsync();
  }

  @Override
  public AsyncContext startAsync(final ServletRequest arg0, final ServletResponse arg1) throws IllegalStateException
  {
    return request.startAsync(arg0, arg1);
  }
}
