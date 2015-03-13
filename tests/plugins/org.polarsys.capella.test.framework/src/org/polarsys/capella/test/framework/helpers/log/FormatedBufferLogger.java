/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.framework.helpers.log;

/**
 * @author Erwan Brottier
 */
public class FormatedBufferLogger extends FormatedLogger {

  protected StringBuilder buffer = new StringBuilder();

  public FormatedBufferLogger() {
  	super();
  }
  
  public FormatedBufferLogger(String indentString) {
  	super(indentString);
  }
  
  @Override
  protected void basicPrint(Object object) {
    buffer.append(object.toString());
  }

	public String getText() {
		return buffer.toString();
	}

}
