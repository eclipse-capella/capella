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
package org.polarsys.capella.common.ui.toolkit.services.behaviors;

import org.polarsys.capella.common.ui.toolkit.services.formats.IFormat;
import org.polarsys.capella.common.ui.toolkit.services.validators.RegExpValidator;

/**
 * The abstract implementation of the input behavior.
 */
public abstract class AbstractInputBehavior implements IInputBehavior {
  // The validator.
  private RegExpValidator _validator = null;
  // The format.
  private IFormat _format = null;
  // The default values array.
  private String[] _defaultValues = null;

  /**
   * Constructs the abstract implementation of the input behavior.
   * @param validator_p The validator.
   * @param format_p The format.
   */
  protected AbstractInputBehavior(RegExpValidator validator_p, IFormat format_p) {
    _validator = validator_p;
    _format = format_p;
  }

  /**
   * Constructs the abstract implementation of the input behavior.
   * @param validator_p The validator.
   * @param format_p The format.
   * @param defaultValues_p The default values.
   */
  protected AbstractInputBehavior(RegExpValidator validator_p, IFormat format_p, String[] defaultValues_p) {
    _validator = validator_p;
    _format = format_p;
    _defaultValues = defaultValues_p;
  }

  /**
   * Gets the validator.
   * @return The validator.
   */
  protected RegExpValidator getValidator() {
    return _validator;
  }

  /**
   * Gets the format.
   * @return The format.
   */
  protected IFormat getFormat() {
    return _format;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.services.behaviors.IInputBehavior#format(java.lang.String)
   */
  public String format(String input_p) {
    if ((null == _format) || (null == _validator)) {
      return input_p;
    }
    return _format.format(input_p, _validator.getPattern());
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.services.behaviors.IInputBehavior#isValid(java.lang.String)
   */
  public boolean isValid(String value_p) {
    if (null != _validator) {
      return (null == _validator.isValid(value_p));
    }
    return false;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.services.behaviors.IInputBehavior#getDefaultValues()
   */
  public String[] getDefaultValues() {
    return _defaultValues;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.services.behaviors.IInputBehavior#getValue(java.lang.String)
   */
  public abstract Object getValue(String input_p);
}
