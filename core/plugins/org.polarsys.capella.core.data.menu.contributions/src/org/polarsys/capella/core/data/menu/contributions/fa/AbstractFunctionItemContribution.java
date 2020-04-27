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
package org.polarsys.capella.core.data.menu.contributions.fa;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionKind;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;

/**
 */
public abstract class AbstractFunctionItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    EObject owner =
        (modelElement instanceof AbstractFunction) ? modelElement : EcoreUtil2.getFirstContainer(modelElement, FaPackage.Literals.ABSTRACT_FUNCTION);
    if (null != owner) {
      Object kind = owner.eGet(FaPackage.Literals.ABSTRACT_FUNCTION__KIND);
      if (!FunctionKind.FUNCTION.equals(kind)) {
        return false;
      }
    }
    return true;
  }

  /**
   * @see org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(EditingDomain editingDomain, ModelElement containerElement, ModelElement createdElement,
      EStructuralFeature feature) {

    if (createdElement instanceof AbstractFunction) {
      AbstractFunction function = (AbstractFunction) createdElement;

      String name = null;
      if (function.getKind() == FunctionKind.DUPLICATE) {
        name = NamingConstants.Function_FunctionKind_Duplicate;

      } else if (function.getKind() == FunctionKind.GATHER) {
        name = NamingConstants.Function_FunctionKind_Gather;

      } else if (function.getKind() == FunctionKind.ROUTE) {
        name = NamingConstants.Function_FunctionKind_Route;

      } else if (function.getKind() == FunctionKind.SELECT) {
        name = NamingConstants.Function_FunctionKind_Select;

      } else if (function.getKind() == FunctionKind.SPLIT) {
        name = NamingConstants.Function_FunctionKind_Split;
      }

      if (name != null) {
        return CreationHelper.getNamingCommand(editingDomain, (AbstractNamedElement) createdElement, containerElement, feature, name);
      }
    }

    return null;
  }
}
