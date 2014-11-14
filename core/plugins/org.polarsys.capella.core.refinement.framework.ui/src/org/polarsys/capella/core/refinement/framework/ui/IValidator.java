/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.framework.ui;

/**
 */
public interface IValidator {
  /**
   * Checks if the validation rule is valid
   * 
   * @param page_p
   */
  boolean isValid(SelectionPage page_p);

  /**
   * Returns an error message
   *
   * @return String
   */
  String getMessage();
}
