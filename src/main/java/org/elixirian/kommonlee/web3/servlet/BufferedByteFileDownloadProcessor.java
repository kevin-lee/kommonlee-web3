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
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.elixirian.kommonlee.exception.ElixirianRuntimeException;
import org.elixirian.kommonlee.io.IoCommonConstants;
import org.elixirian.kommonlee.io.exception.RuntimeIoException;
import org.elixirian.kommonlee.io.util.IoUtil;
import org.elixirian.kommonlee.type.Source;

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
 * @version 0.0.1 (2010-03-18)
 */
public class BufferedByteFileDownloadProcessor<T extends InputStream> implements BufferedFileDownloadProcessor<T>
{
  public static final Charset UTF_8 = IoCommonConstants.UTF_8;
  public static final Charset ISO_8859_1 = IoCommonConstants.ISO_8859_1;

  public static final String DOWNLOAD_CONTENT_TYPE = "application/octet-stream";
  public static final String DOWNLOAD_HEADER_NAME_FOR_CONTENT_DISPOSITION = "Content-Disposition";

  private final int bufferSize;

  /**
   * @param bufferSize
   */
  public BufferedByteFileDownloadProcessor(final int bufferSize)
  {
    this.bufferSize = bufferSize;
  }

  @Override
  public int getBufferSize()
  {
    return bufferSize;
  }

  @Override
  public void processDownload(final HttpServletResponse response, final String fileName, final Source<T> source)
      throws RuntimeIoException
  {
    processDownload0(response, fileName, source, this.bufferSize);
  }

  @Override
  public void processDownload(final HttpServletResponse response, final String fileName, final Source<T> source,
      final int bufferSize) throws RuntimeIoException
  {
    processDownload0(response, fileName, source, bufferSize);
  }

  private void processDownload0(final HttpServletResponse response, final String fileName, final Source<T> source,
      final int bufferSize) throws RuntimeIoException
  {
    final String fileNameForHeader = new String(fileName.getBytes(UTF_8), ISO_8859_1);
    final String fileNameString = "attachment; filename=\"" + fileNameForHeader + "\"";

    // LOGGER.debug(format("[download][fileName: %s][fileNameForHeader: %s]\n[fileNameString: %s]", fileName,
    // fileNameForHeader, fileNameString));

    response.setContentType(DOWNLOAD_CONTENT_TYPE);
    response.setHeader(DOWNLOAD_HEADER_NAME_FOR_CONTENT_DISPOSITION, fileNameString);

    T sourceStream;
    try
    {
      sourceStream = source.getSource();
      /* @formatter:off */
      IoUtil.copyStream(sourceStream,
                        response.getOutputStream(),
                        bufferSize);
      /* @formatter:on */
    }
    catch (final IOException e)
    {
      throw new RuntimeIoException(e);
    }
    catch (final Exception e)
    {
      throw new ElixirianRuntimeException(e);
    }
  }
}
