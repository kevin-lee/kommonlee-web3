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

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.elixirian.kommonlee.util.CommonConstants;

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
 * @version 0.0.1 (2013-02-07)
 */
public class ServletUtilForNginx
{
  public ServletUtilForNginx() throws IllegalAccessException
  {
    throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
  }

  public static String getRemoteAddr(final HttpServletRequest request)
  {
    final String realIp = request.getHeader(HEADER_NAME_X_REAL_IP);
    /* @formatter:off */
    return null == realIp ?
            request.getRemoteAddr() :
            realIp;
    /* @formatter:on */
  }

  public static String getRemoteHost(final HttpServletRequest request)
  {
    final String host = request.getHeader(HEADER_NAME_HOST);
    if (null != host)
    {
      return host;
    }
    final String remoteAddr = getRemoteAddr(request);
    try
    {
      return InetAddress.getByName(remoteAddr)
          .getHostName();
    }
    catch (final UnknownHostException e)
    {
      return remoteAddr;
    }
  }

  public static String getForwardRequestUri(final HttpServletRequest request)
  {
    final String forwardFor = request.getHeader(HEADER_NAME_X_FORWARDED_FOR);
    return null == forwardFor ? ServletUtil.getForwardRequestUri(request) : forwardFor;
  }

  public static String getProtocol(final HttpServletRequest request)
  {
    final String protocol = request.getHeader(HEADER_NAME_X_FORWARDED_PROTOCOL);
    return null == protocol ? request.getProtocol() : protocol;
  }
}
