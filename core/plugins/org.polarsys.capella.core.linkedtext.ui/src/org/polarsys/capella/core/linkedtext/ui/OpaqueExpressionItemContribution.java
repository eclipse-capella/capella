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

/**
 * Automatically add an empty "LinkedText" specification to newly created OpaqueExpression elements.
 */
public class OpaqueExpressionItemContribution implements IMDEMenuItemContribution {

  @Override
  public EClass getMetaclass() {
    return DatavaluePackage.Literals.OPAQUE_EXPRESSION;
  }

  @Override
  public Command executionContribution(EditingDomain editingDomain_p, ModelElement containerElement_p, ModelElement createdElement_p,
      EStructuralFeature feature_p) {
      OpaqueExpression oe = (OpaqueExpression) createdElement_p;
      CompoundCommand cc = new CompoundCommand();
      cc.append(AddCommand.create(editingDomain_p, oe, DatavaluePackage.Literals.OPAQUE_EXPRESSION__LANGUAGES, Collections.singleton(CapellaLinkedTextConstants.OPAQUE_EXPRESSION_LINKED_TEXT)));
      cc.append(AddCommand.create(editingDomain_p, oe, DatavaluePackage.Literals.OPAQUE_EXPRESSION__BODIES, Collections.singleton(""))); //$NON-NLS-1$
      return cc;
  }

  @Override
  public boolean selectionContribution(ModelElement modelElement_p, EClass cls_p, EStructuralFeature feature_p) {
    return true;
  }

}
