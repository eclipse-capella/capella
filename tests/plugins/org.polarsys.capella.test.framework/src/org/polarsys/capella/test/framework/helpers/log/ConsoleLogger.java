/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.helpers.log;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * @author Erwan Brottier
 */
public class ConsoleLogger extends FormatedLogger {

	private static final String CONSOLE_NAME = "dev tools console"; //$NON-NLS-1$
	protected MessageConsole console;
	protected MessageConsoleStream out;
	
  public ConsoleLogger(List<ObjectPrinter> printers) {
  	super(printers);
  }
  
  public ConsoleLogger() {
  	super();
  	console = findConsole(CONSOLE_NAME);
  	out = console.newMessageStream();
  	out.setActivateOnWrite(true);
  }

	private MessageConsole findConsole(String name) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (name.equals(existing[i].getName()))
				return (MessageConsole) existing[i];
		// no console found, so create a new one
		MessageConsole myConsole = new MessageConsole(name, null);
		conMan.addConsoles(new IConsole[] { myConsole });
		return myConsole;
	}

	
  @SuppressWarnings("nls")
	@Override
  protected void basicPrint(Object object) {
    if (object instanceof Collection<?>) {
    	Collection<?> collection = (Collection<?>) object;
    	if (collection.size() == 0) {
    		addTextLn("[]");
    	} else {
    		addText("[");
    		incIndentLn();
    		for (Object element : collection) {
    			addTextLn(element);
    		}
    		decIndent();
    		addText("]");
    	}
    } else {
    	out.print(object.toString());
    	try {
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
    }    
  }

}
