/*******************************************************************************
 * Copyright (c) 2007, 2018 IBM Corporation and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Lars Vogel <Lars.Vogel@vogella.com> - Bug 430848
 *     Thales - Contributor 
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.perspective;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.branding.IProductConstants;
import org.eclipse.ui.splash.BasicSplashHandler;

/**
 * Quick attempt to integrate this class provided by MDK.
 * Does not work well currently: extension point disabled (see plugin.xml)
 * TODO : [Splash] Integration correct / complete
 * 
 */
public class CapellaSplashHandler extends BasicSplashHandler {

  /**
   * 
   */
  public CapellaSplashHandler() {
    // Do nothing
  }

  /**
   * Copied from EclipseSplashHandler {@inheritDoc}
   */
  @Override
  public void init(Shell splash) {
    super.init(splash);
    String progressRectString = null;
    String messageRectString = null;
    String foregroundColorString = null;
    IProduct product = Platform.getProduct();
    if (product != null) {
      progressRectString = product.getProperty(IProductConstants.STARTUP_PROGRESS_RECT);
      messageRectString = product.getProperty(IProductConstants.STARTUP_MESSAGE_RECT);
      foregroundColorString = product.getProperty(IProductConstants.STARTUP_FOREGROUND_COLOR);
    }
    Rectangle progressRect = StringConverter.asRectangle(progressRectString, new Rectangle(10, 10, 300, 15));
    setProgressRect(progressRect);

    Rectangle messageRect = StringConverter.asRectangle(messageRectString, new Rectangle(10, 35, 300, 15));
    setMessageRect(messageRect);

    int foregroundColorInteger;
    try {
      foregroundColorInteger = Integer.parseInt(foregroundColorString, 16);
    } catch (Exception ex) {
      foregroundColorInteger = 0xD2D7FF; // off white
    }

    setForeground(new RGB((foregroundColorInteger & 0xFF0000) >> 16, (foregroundColorInteger & 0xFF00) >> 8, foregroundColorInteger & 0xFF));

    // Custom
    String buildId = "Unknown"; //$NON-NLS-1$
    String version = "Unknown"; //$NON-NLS-1$
    BufferedReader bufferedReader = null;
    if(product != null) {
    	try {
    		InputStream inputStream = product.getDefiningBundle().getEntry("about.mappings").openConnection().getInputStream(); //$NON-NLS-1$
    		bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    		String line = null;
    		while ((line = bufferedReader.readLine()) != null) {
    			if (line.startsWith("0=")) //$NON-NLS-1$
    				version = line.substring(2);
    			if (line.startsWith("1=")) //$NON-NLS-1$
    				buildId = line.substring(2);
    		}
    	} catch (IOException e1) {
    		// ignore
    		e1.printStackTrace();
    	} finally {
    		if (bufferedReader != null) {
    			try {
    				bufferedReader.close();
    			} catch (IOException e1) {
    				// ignore
    			}
    		}
    	}    	
    }

    StringBuilder builder = new StringBuilder();
    builder.append("Version : "); //$NON-NLS-1$
    builder.append(version);
    builder.append("\n"); //$NON-NLS-1$
    builder.append("Build id : "); //$NON-NLS-1$
    builder.append(buildId);
    final String text = builder.toString();

    final Point buildIdPoint = new Point(200, 225);
    getContent().addPaintListener(new PaintListener() {
      @SuppressWarnings("synthetic-access")
      public void paintControl(PaintEvent e) {
        e.gc.setForeground(getForeground());
        FontData[] fontData = e.gc.getFont().getFontData();
        for (int i = 0; i < fontData.length; ++i)
          fontData[i].setHeight(12);
        final Font newFont = new Font(e.display, fontData);
        e.gc.setFont(newFont);
        e.gc.drawText(text, buildIdPoint.x, buildIdPoint.y, true);
      }
    });
  }
}
