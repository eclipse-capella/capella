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

package org.polarsys.capella.common.ui.toolkit.services.behaviors;

import org.polarsys.capella.common.ui.toolkit.services.validators.RegExpValidator;
import org.polarsys.capella.common.ui.toolkit.services.validators.RegExpValidatorFactory;

/**
 * The IP address input behavior.
 */
public class IPInputBehavior extends AbstractInputBehavior {

  /**
   * Constructs the IP address input behavior.
   */
  public IPInputBehavior() {
    super(RegExpValidatorFactory.IP_DIGIT.create("",RegExpValidator.SUPPORT_EMPTY_VALUE), null); //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.services.behaviors.AbstractInputBehavior#format(java.lang.String)
   */
  @Override
  public String format(String input) {
    return null;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.services.behaviors.AbstractInputBehavior#getValue(java.lang.String)
   */
  @Override
  public Object getValue(String input) {
    return null;
  }
}
