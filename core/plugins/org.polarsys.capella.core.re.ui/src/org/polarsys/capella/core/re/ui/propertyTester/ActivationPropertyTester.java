/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.ui.propertyTester;

import org.eclipse.core.expressions.PropertyTester;

/**
 * A simple property tester to force activation of this plugin, to allow IHandlers 
 * to be loaded by HandlerProxy when calling setEnabled.
 * Without that, IHandler of these 
 *
 */
public class ActivationPropertyTester extends PropertyTester {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean test(Object receiver_p, String property_p, Object[] args_p, Object expectedValue_p) {
    return "activation".equals(property_p);
  }

}
