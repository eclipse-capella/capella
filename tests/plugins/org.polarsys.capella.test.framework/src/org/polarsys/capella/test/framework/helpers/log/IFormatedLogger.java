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