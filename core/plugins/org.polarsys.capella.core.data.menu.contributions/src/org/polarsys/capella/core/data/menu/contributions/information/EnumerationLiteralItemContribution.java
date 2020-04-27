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
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    return DatatypePackage.Literals.ENUMERATION.isInstance(modelElement)
           && !feature.equals(DatavaluePackage.Literals.DATA_VALUE_CONTAINER__OWNED_DATA_VALUES)
           && !feature.equals(DatatypePackage.Literals.ENUMERATION__OWNED_DEFAULT_VALUE)
           && !feature.equals(DatatypePackage.Literals.ENUMERATION__OWNED_MAX_VALUE)
           && !feature.equals(DatatypePackage.Literals.ENUMERATION__OWNED_MIN_VALUE)
           && !feature.equals(DatatypePackage.Literals.ENUMERATION__OWNED_NULL_VALUE);
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(final EditingDomain editingDomain, ModelElement containerElement, final ModelElement createdElement,
      EStructuralFeature feature) {
    CompoundCommand cmd = new CompoundCommand();
    cmd.append(new SetCommand(editingDomain, createdElement, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, containerElement));
    cmd.append(DataNamingHelper.getNamingCommand(editingDomain, createdElement, feature));
    return cmd;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return DatavaluePackage.Literals.ENUMERATION_LITERAL;
  }
}
