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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implements a compound validator that contains other validators.<br>
 * The validation is done in applying all contained validators.<br>
 * Overall validation is stopped as soon as a contained validator returns an error message.
 */
public class CompoundValidator extends AbstractValidator {
  /**
   * Validators involved in validation process.
   */
  List<IValidator> _validators;

  /**
   * Constructor.<br>
   * Construct an empty compound validator.
   */
  public CompoundValidator() {
    super(null);
    _validators = new ArrayList<IValidator>(0);
  }

  /**
   * Constructor.<br>
   * Construct a compound validator with given validator as first one.
   * @param validator
   */
  public CompoundValidator(IValidator validator) {
    this();
    addValidator(validator);
  }

  /**
   * Add given validator into the list of {@link IValidator} tested when {@link #isValid(Object)} method is called.
   * @param validator
   */
  public void addValidator(IValidator validator) {
    _validators.add(validator);
  }

  /**
   * Validate given value against all contained validators.
   * @see IValidator#isValid(java.lang.Object)
   */
  public String isValid(Object value) {
    String errorMessage = null;
    Iterator<IValidator> validators = _validators.iterator();
    // Iterate over all contained validators, stop as soon as an error is encountered.
    while (validators.hasNext() && (null == errorMessage)) {
      IValidator currentValidator = validators.next();
      errorMessage = currentValidator.isValid(value);
    }
    return errorMessage;
  }
}
