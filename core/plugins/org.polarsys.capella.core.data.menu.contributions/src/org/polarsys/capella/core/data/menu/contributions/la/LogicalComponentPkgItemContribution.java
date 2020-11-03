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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.la.LaPackage;

public class LogicalComponentPkgItemContribution implements IMDEMenuItemContribution {

  public Command executionContribution(final EditingDomain editingDomain, ModelElement containerElement, final ModelElement createdElement,
      EStructuralFeature feature) {
    return null;
  }

  public EClass getMetaclass() {
    return LaPackage.Literals.LOGICAL_COMPONENT_PKG;
  }

  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    if (modelElement instanceof BlockArchitecture) {
      return false;
    }
    return true;
  }
}
