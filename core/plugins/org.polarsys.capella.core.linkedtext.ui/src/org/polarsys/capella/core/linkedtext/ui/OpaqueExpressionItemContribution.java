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
package org.polarsys.capella.core.linkedtext.ui;

import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.OpaqueExpression;
import org.polarsys.capella.core.model.helpers.ConstraintExt;

/**
 * Automatically add an empty "LinkedText" specification to newly created OpaqueExpression elements.
 */
public class OpaqueExpressionItemContribution implements IMDEMenuItemContribution {

  @Override
  public EClass getMetaclass() {
    return DatavaluePackage.Literals.OPAQUE_EXPRESSION;
  }

  @Override
  public Command executionContribution(EditingDomain editingDomain, ModelElement containerElement, ModelElement createdElement,
      EStructuralFeature feature) {
    OpaqueExpression oe = (OpaqueExpression) createdElement;
    CompoundCommand cc = new CompoundCommand();
    cc.append(AddCommand.create(editingDomain, oe, DatavaluePackage.Literals.OPAQUE_EXPRESSION__LANGUAGES,
        Collections.singleton(ConstraintExt.OPAQUE_EXPRESSION_LINKED_TEXT)));
    cc.append(AddCommand.create(editingDomain, oe, DatavaluePackage.Literals.OPAQUE_EXPRESSION__BODIES, Collections.singleton(""))); //$NON-NLS-1$
    return cc;
  }

  @Override
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return true;
  }

}
