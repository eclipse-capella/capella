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
