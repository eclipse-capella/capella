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
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

public class EnumerationLiteralItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement_p, EClass cls_p, EStructuralFeature feature_p) {
    return DatatypePackage.Literals.ENUMERATION.isInstance(modelElement_p)
           && !feature_p.equals(DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES)
           && !feature_p.equals(DatatypePackage.Literals.ENUMERATION__OWNED_DEFAULT_VALUE)
           && !feature_p.equals(DatatypePackage.Literals.ENUMERATION__OWNED_MAX_VALUE)
           && !feature_p.equals(DatatypePackage.Literals.ENUMERATION__OWNED_MIN_VALUE)
           && !feature_p.equals(DatatypePackage.Literals.ENUMERATION__OWNED_NULL_VALUE);
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(final EditingDomain editingDomain_p, ModelElement containerElement_p, final ModelElement createdElement_p,
      EStructuralFeature feature_p) {
    CompoundCommand cmd = new CompoundCommand();
    cmd.append(new SetCommand(editingDomain_p, createdElement_p, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, containerElement_p));
    cmd.append(DataNamingHelper.getNamingCommand(editingDomain_p, createdElement_p, feature_p));
    return cmd;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return DatavaluePackage.Literals.ENUMERATION_LITERAL;
  }
}
