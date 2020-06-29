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

package org.polarsys.capella.common.ui.toolkit.services.validators;

/**
 * The cardinality boundary validator.
 */
public class CardinalityBoundaryValidator extends RegExpValidator {

  /**
   * Constructs the cardinality boundary validator.
   * @param errorMessage The error message.
   */
  public CardinalityBoundaryValidator(String errorMessage, int style) {
    super(errorMessage, "\\*|\\d*", style); //$NON-NLS-1$
  }
}