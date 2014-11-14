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
package org.polarsys.capella.core.data.menu.contributions.information;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.information.InformationPackage;

public class PropertyItemContribution extends MultiplicityElementItemContribution {

  /**
   * 
   */

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement_p, EClass cls_p, EStructuralFeature feature_p) {

    if (InformationPackage.Literals.COLLECTION.isInstance(modelElement_p)) {
      return false;
    }
    if (InformationPackage.Literals.UNION.isInstance(modelElement_p)) {
      return false;
    }

    return true;

  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(final EditingDomain editingDomain_p, ModelElement containerElement_p, final ModelElement createdElement_p,
      EStructuralFeature feature_p) {

    if (createdElement_p.eClass().equals(InformationPackage.Literals.PROPERTY)) {
      CompoundCommand cmd = new CompoundCommand();

      cmd.append(getCardinalitiesCommand(editingDomain_p, createdElement_p, _ONE_CARDINALITY, _ONE_CARDINALITY));

      cmd.append(getUniqueCommand(editingDomain_p, createdElement_p));

      return cmd;
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return InformationPackage.Literals.PROPERTY;
  }
}
