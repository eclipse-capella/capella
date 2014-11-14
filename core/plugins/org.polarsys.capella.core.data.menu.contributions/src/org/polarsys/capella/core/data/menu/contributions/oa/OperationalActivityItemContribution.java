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
package org.polarsys.capella.core.data.menu.contributions.oa;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.menu.contributions.fa.AbstractFunctionItemContribution;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalActivityPkg;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public class OperationalActivityItemContribution extends AbstractFunctionItemContribution {

  /**
   * This menu item shall be available only if:<br>
   * <br>
   * The current element's level is 'Operational Analysis'<br>
   * AND<br>
   * &nbsp;&nbsp;(The current element is a 'System Activity'<br>
   * &nbsp;&nbsp;OR<br>
   * &nbsp;&nbsp;The current element is contained by a 'Operational Activity'<br>
   * &nbsp;&nbsp;OR<br>
   * &nbsp;&nbsp;&nbsp;&nbsp;(The current element is a 'Operational Activity Package'<br>
   * &nbsp;&nbsp;&nbsp;&nbsp;AND<br>
   * &nbsp;&nbsp;&nbsp;&nbsp;is not contained by a 'Operational Activity'<br>
   * &nbsp;&nbsp;&nbsp;&nbsp;AND<br>
   * &nbsp;&nbsp;&nbsp;&nbsp;doesn't contain any 'Operational Activity')<br>
   * &nbsp;&nbsp;)<br>
   * <br>
   * @see org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution#selectionContribution()
   */
  @Override
  public boolean selectionContribution(ModelElement modelElement_p, EClass cls_p, EStructuralFeature feature_p) {
    return super.selectionContribution(modelElement_p, cls_p, feature_p)
        && EcoreUtil2.isContainedBy(modelElement_p, OaPackage.Literals.OPERATIONAL_ANALYSIS)
        && ((modelElement_p instanceof OperationalActivity)
          || EcoreUtil2.isContainedBy(modelElement_p, OaPackage.Literals.OPERATIONAL_ACTIVITY)
          || ((modelElement_p instanceof OperationalActivityPkg)
           && !EcoreUtil2.isContainedBy(modelElement_p, OaPackage.Literals.OPERATIONAL_ACTIVITY)
           && ((OperationalActivityPkg) modelElement_p).getOwnedOperationalActivities().isEmpty()));
  }

  /**
   * @see org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return OaPackage.Literals.OPERATIONAL_ACTIVITY;
  }
}
