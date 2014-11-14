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
package org.polarsys.capella.core.tiger;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.core.tiger.helpers.DebugHelper;

/**
 */
@SuppressWarnings("nls")
public class TransfoException extends Exception {

  /**
   * Serial Version UID
   */
  private static final long serialVersionUID = 2417957329101515981L;

  /**
   * Constructor using the provided error message.
   * @param message_p
   */
  public TransfoException(String message_p) {
    super(message_p);
  }

  /**
   * @param message_p
   * @param object_p
   * @param throwable
   */
  public TransfoException(String message_p, org.eclipse.emf.ecore.EObject object_p, Throwable throwable) {
    super(message_p + " while parsing element '" + DebugHelper.elementToString(object_p) + "'", throwable);
  }

  /**
   * @param message_p
   */
  public TransfoException(String message_p, org.eclipse.emf.ecore.EObject object_p, ITransfoRule rule_p) {
    super(message_p + " while running '" + rule_p.getName() + "' on element '" + DebugHelper.elementToString(object_p) + "'");
  }

  public TransfoException(String message_p, org.eclipse.emf.ecore.EObject object_p) {
    super(message_p + " while parsing element '" + DebugHelper.elementToString(object_p) + "'");
  }

  public TransfoException(EClass object_p) {
    super("Impossible to instantiate the following EClass for traceabilty : '" + object_p + "'");
  }

}
