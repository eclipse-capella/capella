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
package org.polarsys.capella.core.data.menu.contributions.capellacore;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.core.data.capellacore.CapellacorePackage;

public class IntegerPropertyValueItemContribution extends AbstractPropertyValueItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return CapellacorePackage.Literals.INTEGER_PROPERTY_VALUE;
  }
}
