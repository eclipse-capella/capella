/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;

public class ConstraintItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  @Override
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return !feature.equals(ActivityPackage.Literals.ABSTRACT_ACTION__LOCAL_PRECONDITION)
        && !feature.equals(ActivityPackage.Literals.ABSTRACT_ACTION__LOCAL_POSTCONDITION);
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  @Override
  public Command executionContribution(EditingDomain editingDomain, ModelElement containerElement,
      final ModelElement createdElement, EStructuralFeature feature) {
    return new RecordingCommand((TransactionalEditingDomain) editingDomain) {
      @Override
      protected void doExecute() {
        if (createdElement instanceof AbstractNamedElement) {
          String name = ((AbstractNamedElement) createdElement).getName();
          if ((name == null) || name.startsWith(createdElement.eClass().getName())) {
            ((Constraint) createdElement).setName(ICommonConstants.EMPTY_STRING);
          }
        }
        if (((Constraint) createdElement).getOwnedSpecification() == null) {
          final OpaqueExpression oe = DatavalueFactory.eINSTANCE.createOpaqueExpression();
          ((Constraint) createdElement).setOwnedSpecification(oe);
          Command opaqueExpressionContribution = CreationHelper.getContributorsCommand(oe, createdElement, oe.eClass(),
              oe.eContainingFeature());
          if (opaqueExpressionContribution.canExecute()) {
            opaqueExpressionContribution.execute();
          }
        }
      }
    };
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  @Override
  public EClass getMetaclass() {
    return CapellacorePackage.Literals.CONSTRAINT;
  }
}
