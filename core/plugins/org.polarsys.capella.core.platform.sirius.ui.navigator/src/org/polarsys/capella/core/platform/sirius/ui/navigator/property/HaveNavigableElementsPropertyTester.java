/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.property;

import org.eclipse.core.expressions.PropertyTester;
import org.polarsys.capella.core.platform.sirius.ui.navigator.internal.navigate.NavigationAdvisor;

public class HaveNavigableElementsPropertyTester extends PropertyTester {

  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
    return !NavigationAdvisor.getInstance().getNavigableElements(receiver).isEmpty();
  }
}
