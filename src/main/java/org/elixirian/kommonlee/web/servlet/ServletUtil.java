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
package org.elixirian.kommonlee.web.servlet;

import static org.elixirian.kommonlee.web.CommonWebConstants.*;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.elixirian.kommonlee.util.CommonConstants;
import org.elixirian.kommonlee.web.CommonWebConstants;

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
 * @version 0.0.1 (2010-04-19)
 */
public final class ServletUtil
{
	/**
	 * {@link CommonWebConstants#HEADER_FOR_XML_HTTP_REQUEST_NAME} == "X-Requested-With"
	 */
	public static final String HEADER_FOR_XML_HTTP_REQUEST_NAME = CommonWebConstants.HEADER_FOR_XML_HTTP_REQUEST_NAME;

	/**
	 * {@link CommonWebConstants#HEADER_XML_HTTP_REQUEST_VALUE} == "XMLHttpRequest"
	 */
	public static final String HEADER_XML_HTTP_REQUEST_VALUE = CommonWebConstants.HEADER_XML_HTTP_REQUEST_VALUE;

	private static final Pattern ABSOLUTE_URL_PATTERN = Pattern.compile("\\A[a-z0-9.+-]+://.*", Pattern.CASE_INSENSITIVE);

	private ServletUtil() throws IllegalAccessException
	{
		throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
	}

	public static boolean isAbsoluteUrl(final String url)
	{
		return ABSOLUTE_URL_PATTERN.matcher(url)
				.matches();
	}

	public static boolean isNotAbsoluteUrl(final String url)
	{
		return !isAbsoluteUrl(url);
	}

	/**
	 * Returns the value of the named attribute in the given HttpServletRequest, or null if no attribute of the given name
	 * exists. It casts the object to the generic type <code><T></code>.
	 *
	 * @param <T>
	 *          type to cast
	 * @param request
	 *          the given {@link HttpServletRequest}
	 * @param attributeName
	 *          the attribute name.
	 * @return the value of the named attribute in the given {@link HttpServletRequest}, or null if no attribute of the
	 *         given name exists. It casts the object to the generic type <code>T</code>.
	 */
	public static <T> T getAttribute(final HttpServletRequest request, final String attributeName)
	{
		@SuppressWarnings("unchecked")
		final T t = (T) request.getAttribute(attributeName);
		return t;
	}

	/**
	 * Returns the value of a request parameter as a <code>String</code>, or <code>null</code> if the parameter does not
	 * exist. Request parameters are extra information sent with the request. For HTTP servlets, parameters are contained
	 * in the query string or posted form data.
	 * <p>
	 * You should only use this method when you are sure the parameter has only one value. If the parameter might have
	 * more than one value, use {@link #getParameterValues}.
	 * <p>
	 * If you use this method with a multivalued parameter, the value returned is equal to the first value in the array
	 * returned by <code>getParameterValues</code>.
	 * <p>
	 * If the parameter data was sent in the request body, such as occurs with an HTTP POST request, then reading the body
	 * directly via {@link ServletRequest#getInputStream()} or {@link ServletRequest#getReader()} can interfere with the
	 * execution of this method.
	 *
	 * @param request
	 *          {@link HttpServletRequest}
	 * @param parameterName
	 *          a <code>String</code> specifying the name of the parameter
	 * @return a <code>String</code> representing the single value of the parameter
	 * @see #getParameterValues
	 */
	public static String getParameter(final HttpServletRequest request, final String parameterName)
	{
		return request.getParameter(parameterName);
	}

	/**
	 * Returns an array of <code>String</code> objects containing all of the values the given request parameter has, or
	 * <code>null</code> if the parameter does not exist.
	 * <p>
	 * If the parameter has a single value, the array has a length of 1.
	 *
	 * @param request
	 *          {@link HttpServletRequest}
	 * @param parametersName
	 *          a <code>String</code> containing the name of the parameter whose value is requested
	 * @return an array of <code>String</code> objects containing the parameter's values
	 * @see #getParameter
	 */
	public static String[] getParameterValues(final HttpServletRequest request, final String parametersName)
	{
		return request.getParameterValues(parametersName);
	}

	/**
	 * Stores an attribute in the given request.
	 * <p>
	 * Attribute names should follow the same conventions as package names. Names beginning with <code>java.*</code>,
	 * <code>javax.*</code>, and <code>com.sun.*</code>, are reserved for use by Sun Microsystems. <br>
	 * If the object passed in is null, the effect is the same as calling {@link #removeAttribute}. <br>
	 * It is warned that when the request is dispatched from the servlet resides in a different web application by
	 * <code>RequestDispatcher</code>, the object set by this method may not be correctly retrieved in the caller servlet.
	 * </p>
	 * After storing the given attribute object, it returns the object.
	 *
	 * @param <T>
	 *          the type of attribute object
	 * @param request
	 *          the given {@link HttpServletRequest} object
	 * @param attributeName
	 *          a <code>String</code> specifying the name of the attribute
	 * @param attribute
	 *          the <code>Object</code> to be stored
	 * @return the stored attribute object.
	 * @see #setEscapedAttribute(HttpServletRequest, String, String)
	 */
	public static <T> T setAttribute(final HttpServletRequest request, final String attributeName, final T attribute)
	{
		request.setAttribute(attributeName, attribute);
		return attribute;
	}

	// /**
	// * Stores an attribute String value in the given request. The given String is escaped by
	// * {@link StringEscapeUtil#escapeHtml(String)} before it is stored.
	// * <p>
	// * Attribute names should follow the same conventions as package names. Names beginning with <code>java.*</code>,
	// * <code>javax.*</code>, and <code>com.sun.*</code>, are reserved for use by Sun Microsystems. <br>
	// * If the object passed in is null, the effect is the same as calling {@link #removeAttribute}. <br>
	// * It is warned that when the request is dispatched from the servlet resides in a different web application by
	// * <code>RequestDispatcher</code>, the object set by this method may not be correctly retrieved in the caller
	// servlet.
	// * </p>
	// * The given String attribute value is escaped by {@link StringEscapeUtil#escapeHtml(String)} then the escaped
	// String
	// * is returned.
	// *
	// * @param request
	// * the given {@link HttpServletRequest} object
	// * @param attributeName
	// * a {@code String } specifying the name of the attribute
	// * @param attribute
	// * the {@code String} value to be stored
	// * @return The String attribute value which is escaped by {@link StringEscapeUtil#escapeHtml(String)}.
	// * @see #setAttribute(HttpServletRequest, String, Object)
	// */
	// public static String setEscapedAttribute(final HttpServletRequest request, final String attributeName,
	// final String attribute)
	// {
	// // TODO: remove dependency: HtmlUtils
	// final String escapedString = HtmlUtils.escapeHtml(attribute);
	// request.setAttribute(attributeName, escapedString);
	// return escapedString;
	// }

	public static void removeAttribute(final HttpServletRequest request, final String attributeName)
	{
		request.removeAttribute(attributeName);
	}

	/**
	 * Returns forward request URI. It is the same as
	 * <code>request.getAttribute("javax.servlet.forward.request_uri")</code>
	 *
	 * @param request
	 *          the given {@link HttpServletRequest}
	 * @return The forward request URI. null if not forward request URI is found.
	 */
	public static String getForwardRequestUri(final HttpServletRequest request)
	{
		return getAttribute(request, JAVAX_SERVLET_FORWARD_REQUEST_URI);
	}

	public static String getLastUriPath(final HttpServletRequest request)
	{
		final String uri = request.getRequestURI();
		final int position = uri.lastIndexOf('/');
		return -1 < position ? uri.substring(position + 1) : uri;
	}

	public static String getServerUrl(final HttpServletRequest request)
	{
		final String requestUrl = request.getRequestURL()
				.toString();

		int index = requestUrl.indexOf("http://");
		int startIndex = "http://".length();

		if (0 > index)
		{
			index = requestUrl.indexOf("https://");
			startIndex = "https://".length();
		}

		if (0 > index)
		{
			index = requestUrl.indexOf('/');
			return 0 > index ? "http://" + requestUrl : "http://" + requestUrl.substring(0, index);
		}
		final String substring = requestUrl.substring(startIndex);
		index = substring.indexOf('/');
		return 0 > index ? requestUrl.substring(0, startIndex) + substring : requestUrl.substring(0, startIndex)
				+ substring.substring(0, index);
	}

	public static String getServerUrlWithContextPath(final HttpServletRequest request)
	{
		final String requestUrl = request.getRequestURL()
				.toString();

		int index = requestUrl.indexOf("http://");
		int startIndex = "http://".length();

		if (0 > index)
		{
			index = requestUrl.indexOf("https://");
			startIndex = "https://".length();
		}

		if (0 > index)
		{
			index = requestUrl.indexOf('/');
			/* @formatter:off */
			return (0 > index ?
								"http://" + requestUrl :
								"http://" + requestUrl
															.substring(0, index)) +
							request.getContextPath();
			/* @formatter:on */
		}
		final String substring = requestUrl.substring(startIndex);
		index = substring.indexOf('/');
		/* @formatter:off */
		return (0 > index ?
							requestUrl.substring(0, startIndex) + substring :
							requestUrl.substring(0, startIndex) + substring.substring(0, index)) +
						request.getContextPath();
		/* @formatter:on */
	}

	/**
	 * Same as <code>request.getSession()</code>. So it returns the current session associated with this request, or if
	 * the request does not have a session, creates one.
	 *
	 * @param request
	 * @return returns the result of <code>request.getSession()</code>.
	 */
	public static HttpSession getSession(final HttpServletRequest request)
	{
		return request.getSession();
	}

	/**
	 * Same as <code>request.getSession(false)</code>. So it returns the current {@link HttpSession} associated with this
	 * request or, if there is no current session, returns <code>null</code>.
	 *
	 * @param request
	 *          the given {@link HttpServletRequest}
	 * @return returns the result of <code>request.getSession(false)</code>.
	 */
	public static HttpSession findSession(final HttpServletRequest request)
	{
		return request.getSession(false);
	}

	/**
	 * Returns the object bound with the specified name in the given session, or null if no object is bound under the
	 * name. It casts the object to the generic type T.
	 *
	 * @param <T>
	 *          type to cast
	 * @param session
	 *          the given {@link HttpSession}
	 * @param attributeName
	 *          the attribute name.
	 * @return the generic type T object bound with the specified name in the given session.
	 */
	public static <T> T getAttribute(final HttpSession session, final String attributeName)
	{
		@SuppressWarnings("unchecked")
		final T t = (T) session.getAttribute(attributeName);
		return t;
	}

	/**
	 * Binds an object to the given session, using the name specified. If an object of the same name is already bound to
	 * the session, the object is replaced.
	 * <p>
	 * After this method executes, and if the new object implements <code>HttpSessionBindingListener</code>, the container
	 * calls <code>HttpSessionBindingListener.valueBound</code>. The container then notifies any
	 * <code>HttpSessionAttributeListener</code>s in the web application.
	 * </p>
	 * <p>
	 * If an object was already bound to the given session of this name that implements
	 * <code>HttpSessionBindingListener</code>, its <code>HttpSessionBindingListener.valueUnbound</code> method is called.
	 * </p>
	 * <p>
	 * If the value passed in is null, this has the same effect as calling <code>removeAttribute()<code>.
	 * </p>
	 * <p>
	 * After binding it returns the binded attribute object. If the object is String, it is escaped by
	 * {@link StringEscapeUtil#escapeHtml(String)} before binding.
	 * </p>
	 *
	 * @param <T>
	 *          the type of the given attribute object.
	 * @param session
	 *          the given {@link HttpSession} object
	 * @param attributeName
	 *          the name to which the object is bound; cannot be null
	 * @param attribute
	 *          the object to be bound
	 * @return the binded attribute object <T>. If the object is String, it is escaped by
	 *         {@link StringEscapeUtil#escapeHtml(String)} before binding which means this method returns the escaped
	 *         String.
	 * @exception IllegalStateException
	 *              if this method is called with an invalidated session
	 * @see #setEscapedAttribute(HttpSession, String, String)
	 */
	public static <T> T setAttribute(final HttpSession session, final String attributeName, final T attribute)
	{
		session.setAttribute(attributeName, attribute);
		return attribute;
	}

	public static void removeAttribute(final HttpSession session, final String attributeName)
	{
		session.removeAttribute(attributeName);
	}

	public static String getReferrer(final HttpServletRequest request)
	{
		return request.getHeader(HEADER_REFERRER_NAME);
	}

	// /**
	// * Binds an attribute String value to the given session, using the name specified. If an object of the same name is
	// * already bound to the session, the object is replaced. If the value passed in is null, this has the same effect as
	// * calling <code>removeAttribute()<code>.
	// *
	// * After binding it returns the binded String attribute value which is escaped by {@link
	// StringEscapeUtil#escapeHtml(String)} before binding.
	// *
	// * @param session
	// * the given {@link HttpSession} object
	// * @param attributeName
	// * the name to which the object is bound; cannot be null
	// * @param attribute
	// * the String value to be bound
	// * @return the binded attribute String which is is escaped by {@link StringEscapeUtil#escapeHtml(String)} before
	// * binding which means this method returns the escaped String.
	// * @exception IllegalStateException
	// * if this method is called with an invalidated session
	// * @see #setAttribute(HttpSession, String, Object)
	// */
	// public static String
	// setEscapedAttribute(final HttpSession session, final String attributeName, final String attribute)
	// {
	// // TODO: remove dependency: HtmlUtils
	// final String escapedString = HtmlUtils.escapeHtml(attribute);
	// session.setAttribute(attributeName, escapedString);
	// return escapedString;
	// }

	public static String findUserAgent(final HttpServletRequest request)
	{
		return request.getHeader(HEADER_USER_AGENT_NAME);
	}

	public static String getUriForForwarding(final HttpServletRequest request, final String uri)
	{
		final String contextPath = request.getContextPath();
		return uri.startsWith(contextPath) ? uri.substring(uri.indexOf(contextPath) + contextPath.length()) : uri;

	}

	public static TreeMap<String, List<String>> newHttpHeaderMap(final HttpServletRequest request)
	{
		return newHttpHeaderMap(request, new TreeMap<String, List<String>>(String.CASE_INSENSITIVE_ORDER));
	}

	private static <M extends Map<String, List<String>>> M newHttpHeaderMap(final HttpServletRequest request,
			final M httpHeadersMap)
	{
		@SuppressWarnings("unchecked")
		final Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements())
		{
			final String headerName = headerNames.nextElement();
			@SuppressWarnings("unchecked")
			final Enumeration<String> valueEnumeration = request.getHeaders(headerName);
			if (null != valueEnumeration)
			{
				/*
				 * It does not add header info if there is no value found with the given header name although the name is found
				 * in the HttpServletRequest object. In other words, if valueEnumeration contains a null reference, it does not
				 * even put the header name.
				 */
				final List<String> valueList = new ArrayList<String>();
				httpHeadersMap.put(headerName, valueList);
				while (valueEnumeration.hasMoreElements())
				{
					valueList.add(valueEnumeration.nextElement());
				}
			}
		}
		return httpHeadersMap;
	}

	/**
	 * Check if the request is sent by XMLHttpRequest object by checking the header with the name "X-Requested-With".
	 *
	 * @param request
	 * @return true if the header has the value of "X-Requested-With" and it is "XMLHttpRequest". false if the value is
	 *         not equal to "XMLHttpRequest" or the header value doesn't exist at all.
	 */
	public static boolean isXmlHttpRequest(final HttpServletRequest request)
	{
		return HEADER_XML_HTTP_REQUEST_VALUE.equals(request.getHeader(HEADER_FOR_XML_HTTP_REQUEST_NAME));
	}

	/**
	 * Check if the given header value is equal to {@link #HEADER_XML_HTTP_REQUEST_VALUE} that is "XMLHttpRequest" to
	 * check if it is sent by XMLHttpRequest object.
	 *
	 * @param headerValue
	 * @return true if the headerValue is equal to "XMLHttpRequest". false if the value is not equal to "XMLHttpRequest".
	 *         false otherwise..
	 */
	public static boolean isXmlHttpRequest(final String headerValue)
	{
		return HEADER_XML_HTTP_REQUEST_VALUE.equals(headerValue);
	}

	/**
	 * Exactly same as {@link ServletUtil#isXmlHttpRequest(HttpServletRequest)}
	 *
	 * @param request
	 * @return
	 */
	public static boolean isAjax(final HttpServletRequest request)
	{
		return isXmlHttpRequest(request);
	}
}
