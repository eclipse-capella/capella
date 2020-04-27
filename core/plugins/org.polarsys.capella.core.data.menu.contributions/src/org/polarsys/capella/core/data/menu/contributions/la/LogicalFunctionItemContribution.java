/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.la.LogicalFunctionPkg;
import org.polarsys.capella.core.data.menu.contributions.fa.AbstractFunctionItemContribution;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public class LogicalFunctionItemContribution extends AbstractFunctionItemContribution {

  /**
   * This menu item shall be available only if:<br>
   * <br>
   * The current element's level is 'Logical Architecture'<br>
   * AND<br>
   * &nbsp;&nbsp;(The current element is a 'Logical Function'<br>
   * &nbsp;&nbsp;OR<br>
   * &nbsp;&nbsp;The current element is contained by a 'Logical Function'<br>
   * &nbsp;&nbsp;OR<br>
   * &nbsp;&nbsp;&nbsp;&nbsp;(The current element is a 'Logical Function Package'<br>
   * &nbsp;&nbsp;&nbsp;&nbsp;AND<br>
   * &nbsp;&nbsp;&nbsp;&nbsp;is not contained by a 'Logical Function'<br>
   * &nbsp;&nbsp;&nbsp;&nbsp;AND<br>
   * &nbsp;&nbsp;&nbsp;&nbsp;doesn't contain any 'Logical Function')<br>
   * &nbsp;&nbsp;)<br>
   * <br>
   * @see org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution#selectionContribution()
   */
  @Override
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return super.selectionContribution(modelElement, cls, feature)
        && EcoreUtil2.isContainedBy(modelElement, LaPackage.Literals.LOGICAL_ARCHITECTURE)
        && ((modelElement instanceof LogicalFunction)
          || EcoreUtil2.isContainedBy(modelElement, LaPackage.Literals.LOGICAL_FUNCTION)
          || ((modelElement instanceof LogicalFunctionPkg)
           && !EcoreUtil2.isContainedBy(modelElement, LaPackage.Literals.LOGICAL_FUNCTION)
           && ((LogicalFunctionPkg) modelElement).getOwnedLogicalFunctions().isEmpty()));
  }

  /**
   * @see org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return LaPackage.Literals.LOGICAL_FUNCTION;
  }
}
