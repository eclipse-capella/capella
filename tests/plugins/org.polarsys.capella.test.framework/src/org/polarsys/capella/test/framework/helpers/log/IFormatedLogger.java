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
public interface IFormatedLogger {

	public void printException(String message, Exception exception);

	public void printException(Exception exception);

	public void addTextLn(Object text);

	public void addText(Object text);

	public void carriageReturn();

	public void incIndent();

	public void incIndentLn();

	public void decIndent();

	public void decIndentLn();	

	public void resetIndentation();
}