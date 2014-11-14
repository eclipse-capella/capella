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
package org.polarsys.capella.core.libraries.ui.propertyTester;

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
