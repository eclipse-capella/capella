/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.platform.eclipse.tools.report.console;

import java.io.OutputStream;
import java.util.HashMap;

import org.apache.log4j.Level;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import org.polarsys.capella.common.tools.report.appenders.console.IReportConsole;

public class ReportConsole implements IReportConsole {
	private static final String CAPELLA_CONSOLE = "Capella"; //$NON-NLS-1$
	private static final RGB rgb_BLACK = new RGB(0, 0, 0);
	private static final RGB rgb_DARK_GREEN = new RGB(0, 102, 0);
	private static final RGB rgb_DARK_MAGENTA = new RGB(153,0,204);
	private static final RGB rgb_RED = new RGB(204,0,0);
	private static final RGB rgb_DARK_YELLOW = new RGB(153, 153, 153);

  public OutputStream getErrorStream() {
    MessageConsole messageConsole = getCapellaConsole();
    MessageConsoleStream consoleStream = new MessageConsoleStream(messageConsole);
    return consoleStream;
  }

  /**
   * Return OutPutStreams connected to the same Capella Console, colored for each Level log.
   */
	public HashMap<Level,MessageConsoleStream> getOutputStreams() {
		HashMap<Level, MessageConsoleStream> conStreamsMap;
		conStreamsMap =  createOutputStreamsColor(getCapellaConsole());
		return conStreamsMap;
	}
	
	/**
	 * Return a MessageConsole for Capella
	 * @return
	 */
	public MessageConsole getCapellaConsole() {
		MessageConsole capellaCons = null;
		IConsole[] consoles = ConsolePlugin.getDefault().getConsoleManager().getConsoles();
		for (IConsole currentConsole : consoles) {
			if (currentConsole.getName().equalsIgnoreCase(CAPELLA_CONSOLE)) {
				capellaCons = (MessageConsole) currentConsole;
			}
		}
		if (null == capellaCons) {
			capellaCons = new MessageConsole(CAPELLA_CONSOLE, ImageDescriptor.getMissingImageDescriptor());
			ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { capellaCons });
//			ConsolePlugin.getDefault().getConsoleManager().showConsoleView(capellaCons);
		}
		return capellaCons;
	}
	
	
	/**
	 * Return OutPutStreams, colored for each Level log.
	 */
	private HashMap<Level,MessageConsoleStream> createOutputStreamsColor(MessageConsole messageCons) {
		HashMap<Level, MessageConsoleStream> conStreamsMap = new HashMap<Level, MessageConsoleStream>();
		
		conStreamsMap.put(Level.DEBUG, createOutputStreamColor(rgb_BLACK, messageCons));
		conStreamsMap.put(Level.INFO,  createOutputStreamColor(rgb_DARK_GREEN, messageCons));
		conStreamsMap.put(Level.WARN,  createOutputStreamColor(rgb_DARK_MAGENTA, messageCons));
		conStreamsMap.put(Level.ERROR, createOutputStreamColor(rgb_RED, messageCons));
		conStreamsMap.put(Level.FATAL, createOutputStreamColor(rgb_DARK_YELLOW, messageCons));

		return conStreamsMap;
	}
  
	/**
	 * Return OutPutStream with the color given in parameter.
	 */
	private MessageConsoleStream createOutputStreamColor(RGB rgb_color, MessageConsole messageCons) {
		MessageConsoleStream consoleStream = messageCons.newMessageStream();
		Display standardDisplay = ConsolePlugin.getStandardDisplay();
		Color systemColor = new Color(standardDisplay, rgb_color);
		consoleStream.setColor(systemColor);
		
		return consoleStream;
	}
}
