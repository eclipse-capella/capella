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
 * The cardinality boundaries validator. The cardinality boundaries validator is used wherever cardinality boundaries are displayed in the same text field.
 */
public class CardinalityBoundariesValidator extends RegExpValidator {
  /**
   * Constructs the cardinality validator with the specified error message.
   * @param errorMessage The message displayed when {@link #isValid(Object)} returned <code>false</code>.
   */
  public CardinalityBoundariesValidator(String errorMessage, int style) {
    super(errorMessage, "\\*|\\d|(\\d+|[*])(\\s|[.]{2})(\\d+|[*])", style); //$NON-NLS-1$
  }
}
