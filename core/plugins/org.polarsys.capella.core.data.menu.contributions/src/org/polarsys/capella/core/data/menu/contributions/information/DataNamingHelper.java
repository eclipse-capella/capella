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
package org.polarsys.capella.core.data.menu.contributions.information;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class DataNamingHelper {
  /**
   * @param editingDomain
   * @param createdElement
   * @param feature
   * @return
   */
  public static Command getNamingCommand(EditingDomain editingDomain, ModelElement createdElement, EStructuralFeature feature) {
    String name = null;

    if (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE.equals(feature)
     || DatatypePackage.Literals.NUMERIC_TYPE__OWNED_MIN_VALUE.equals(feature)
     || DatatypePackage.Literals.ENUMERATION__OWNED_MIN_VALUE.equals(feature)
     || InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE.equals(feature)
     || DatatypePackage.Literals.NUMERIC_TYPE__OWNED_MAX_VALUE.equals(feature)
     || DatatypePackage.Literals.ENUMERATION__OWNED_MAX_VALUE.equals(feature)
     || InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD.equals(feature)
     || InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD.equals(feature)
     || InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH.equals(feature)
     || DatatypePackage.Literals.STRING_TYPE__OWNED_MIN_LENGTH.equals(feature)
     || InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH.equals(feature)
     || DatatypePackage.Literals.STRING_TYPE__OWNED_MAX_LENGTH.equals(feature)
     || InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE.equals(feature)
     || DatatypePackage.Literals.NUMERIC_TYPE__OWNED_NULL_VALUE.equals(feature)
     || DatatypePackage.Literals.STRING_TYPE__OWNED_NULL_VALUE.equals(feature)
     || DatatypePackage.Literals.ENUMERATION__OWNED_NULL_VALUE.equals(feature)
     || InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE.equals(feature)
     || DatatypePackage.Literals.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE.equals(feature)
     || DatatypePackage.Literals.NUMERIC_TYPE__OWNED_DEFAULT_VALUE.equals(feature)
     || DatatypePackage.Literals.STRING_TYPE__OWNED_DEFAULT_VALUE.equals(feature)
     || DatatypePackage.Literals.ENUMERATION__OWNED_DEFAULT_VALUE.equals(feature)
     || DatavaluePackage.Literals.ENUMERATION_LITERAL__DOMAIN_VALUE.equals(feature))
    {
      name = ICommonConstants.EMPTY_STRING;
    }

    if (name != null) {
      return new SetCommand(editingDomain, createdElement, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, name);
    }

    return new IdentityCommand();
  }
}
