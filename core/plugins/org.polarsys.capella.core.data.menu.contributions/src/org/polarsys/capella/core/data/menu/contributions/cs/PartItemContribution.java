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
package org.polarsys.capella.core.data.menu.contributions.cs;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;

public class PartItemContribution implements IMDEMenuItemContribution {

  //  private static final String _DEFAULT_CARDINALITY = "1"; //$NON-NLS-1$

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement_p, EClass cls_p, EStructuralFeature feature_p) {
    if (InformationPackage.Literals.CLASS.isInstance(modelElement_p)) {
      return false;
    }
    if (InformationPackage.Literals.UNION.isInstance(modelElement_p)) {
      return false;
    }
    if (InformationPackage.Literals.COLLECTION.isInstance(modelElement_p)) {
      return false;
    }
    if (CommunicationPackage.Literals.SIGNAL.isInstance(modelElement_p)) {
      return false;
    }
    if (CommunicationPackage.Literals.EXCEPTION.isInstance(modelElement_p)) {
      return false;
    }
    if (CsPackage.Literals.INTERFACE.isInstance(modelElement_p)) {
      return false;
    }
    return true;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(final EditingDomain editingDomain_p, final ModelElement containerElement_p, final ModelElement createdElement_p,
      EStructuralFeature feature_p) {
    return null;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return CsPackage.Literals.PART;
  }
}
