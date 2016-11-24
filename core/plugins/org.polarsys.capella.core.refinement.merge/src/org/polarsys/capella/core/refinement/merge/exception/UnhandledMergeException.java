/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.merge.exception;


/**
 * Exception raised by tools used during the merge process.
 */
public class UnhandledMergeException extends MergeException {

  /**
   * Default constructor
   * @param messageString
   */
  public UnhandledMergeException(String messageString) {
    super(messageString);
  }
  
  /**
   * The serial version UID
   */
  private static final long serialVersionUID = 4605940123181626054L;
  
}
