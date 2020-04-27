/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.tiger;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.common.helpers.EObjectExt;

/**
 */
public class TransfoException extends Exception {

  /**
   * Serial Version UID
   */
  private static final long serialVersionUID = 2417957329101515981L;

  /**
   * Constructor using the provided error message.
   * @param message
   */
  public TransfoException(String message) {
    super(message);
  }

  /**
   * @param message
   * @param object
   * @param throwable
   */
  public TransfoException(String message, org.eclipse.emf.ecore.EObject object, Throwable throwable) {
    super(message + " while parsing element '" + EObjectExt.getText(object) + "'", throwable); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * @param message
   */
  public TransfoException(String message, org.eclipse.emf.ecore.EObject object, ITransfoRule rule) {
    super(message + " while running '" + rule.getName() + "' on element '" + EObjectExt.getText(object) + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  public TransfoException(String message, org.eclipse.emf.ecore.EObject object) {
    super(message + " while parsing element '" + EObjectExt.getText(object) + "'"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public TransfoException(EClass object) {
    super("Impossible to instantiate the following EClass for traceabilty : '" + object + "'"); //$NON-NLS-1$ //$NON-NLS-2$
  }

}
