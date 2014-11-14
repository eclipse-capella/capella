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
   * @param editingDomain_p
   * @param createdElement_p
   * @param feature_p
   * @return
   */
  public static Command getNamingCommand(EditingDomain editingDomain_p, ModelElement createdElement_p, EStructuralFeature feature_p) {
    String name = null;

    if (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE.equals(feature_p)
     || DatatypePackage.Literals.NUMERIC_TYPE__OWNED_MIN_VALUE.equals(feature_p)
     || DatatypePackage.Literals.ENUMERATION__OWNED_MIN_VALUE.equals(feature_p)
     || InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE.equals(feature_p)
     || DatatypePackage.Literals.NUMERIC_TYPE__OWNED_MAX_VALUE.equals(feature_p)
     || DatatypePackage.Literals.ENUMERATION__OWNED_MAX_VALUE.equals(feature_p)
     || InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD.equals(feature_p)
     || InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD.equals(feature_p)
     || InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH.equals(feature_p)
     || DatatypePackage.Literals.STRING_TYPE__OWNED_MIN_LENGTH.equals(feature_p)
     || InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH.equals(feature_p)
     || DatatypePackage.Literals.STRING_TYPE__OWNED_MAX_LENGTH.equals(feature_p)
     || InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE.equals(feature_p)
     || DatatypePackage.Literals.NUMERIC_TYPE__OWNED_NULL_VALUE.equals(feature_p)
     || DatatypePackage.Literals.STRING_TYPE__OWNED_NULL_VALUE.equals(feature_p)
     || DatatypePackage.Literals.ENUMERATION__OWNED_NULL_VALUE.equals(feature_p)
     || InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE.equals(feature_p)
     || DatatypePackage.Literals.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE.equals(feature_p)
     || DatatypePackage.Literals.NUMERIC_TYPE__OWNED_DEFAULT_VALUE.equals(feature_p)
     || DatatypePackage.Literals.STRING_TYPE__OWNED_DEFAULT_VALUE.equals(feature_p)
     || DatatypePackage.Literals.ENUMERATION__OWNED_DEFAULT_VALUE.equals(feature_p)
     || DatavaluePackage.Literals.ENUMERATION_LITERAL__DOMAIN_VALUE.equals(feature_p))
    {
      name = ICommonConstants.EMPTY_STRING;
    }

    if (name != null) {
      return new SetCommand(editingDomain_p, createdElement_p, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, name);
    }

    return new IdentityCommand();
  }
}
