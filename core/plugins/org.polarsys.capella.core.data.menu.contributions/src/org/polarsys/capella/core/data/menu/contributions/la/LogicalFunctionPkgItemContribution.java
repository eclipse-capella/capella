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
package org.polarsys.capella.core.data.menu.contributions.la;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.menu.contributions.fa.FunctionPkgItemContribution;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public class LogicalFunctionPkgItemContribution extends FunctionPkgItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  @Override
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return super.selectionContribution(modelElement, cls, feature)
        && ((modelElement instanceof LogicalArchitecture)
          || EcoreUtil2.isOrIsContainedBy(modelElement, LaPackage.Literals.LOGICAL_FUNCTION));
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return LaPackage.Literals.LOGICAL_FUNCTION_PKG;
  }
}
