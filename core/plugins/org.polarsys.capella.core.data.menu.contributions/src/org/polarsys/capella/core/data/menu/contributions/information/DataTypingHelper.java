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
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datavalue.ComplexValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.ValuePart;

/**
 */
public class DataTypingHelper {
  /**
   * @param editingDomain_p
   * @param containerElement_p
   * @param createdElement_p
   * @param feature_p
   * @return
   */
  public static Command getTypingCommand(EditingDomain editingDomain_p, ModelElement containerElement_p, ModelElement createdElement_p,
      EStructuralFeature feature_p) {

    if (createdElement_p instanceof ComplexValue) {
      if (containerElement_p.eClass().equals(InformationPackage.Literals.CLASS)) {
        return new SetCommand(editingDomain_p, createdElement_p, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, containerElement_p);

      } else if (DatavaluePackage.Literals.VALUE_PART.isInstance(containerElement_p)) {

        Property property = ((ValuePart) containerElement_p).getReferencedProperty();
        if (property != null) {
          AbstractType type = property.getAbstractType();
          if (type.eClass().equals(InformationPackage.Literals.CLASS)) {
            return new SetCommand(editingDomain_p, createdElement_p, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, type);
          }
        }
      }
    } else {
      if (feature_p.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE)
          || feature_p.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE)
          || feature_p.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE)
          || feature_p.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE)
          || feature_p.equals(DatatypePackage.Literals.NUMERIC_TYPE__OWNED_MIN_VALUE)
          || feature_p.equals(DatatypePackage.Literals.NUMERIC_TYPE__OWNED_MAX_VALUE)
          || feature_p.equals(DatatypePackage.Literals.NUMERIC_TYPE__OWNED_NULL_VALUE)
          || feature_p.equals(DatatypePackage.Literals.NUMERIC_TYPE__OWNED_DEFAULT_VALUE)
          || feature_p.equals(DatatypePackage.Literals.ENUMERATION__OWNED_MIN_VALUE) || feature_p.equals(DatatypePackage.Literals.ENUMERATION__OWNED_MAX_VALUE)
          || feature_p.equals(DatatypePackage.Literals.ENUMERATION__OWNED_NULL_VALUE)
          || feature_p.equals(DatatypePackage.Literals.ENUMERATION__OWNED_DEFAULT_VALUE)
          || feature_p.equals(DatatypePackage.Literals.STRING_TYPE__OWNED_NULL_VALUE)
          || feature_p.equals(DatatypePackage.Literals.STRING_TYPE__OWNED_DEFAULT_VALUE)
          || feature_p.equals(DatatypePackage.Literals.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE)) {
        if (containerElement_p instanceof TypedElement) {
          AbstractType type = ((TypedElement) containerElement_p).getAbstractType();
          if (type != null) {
            return new SetCommand(editingDomain_p, createdElement_p, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, type);
          }
        } else {
          return new SetCommand(editingDomain_p, createdElement_p, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, containerElement_p);
        }
      }
    }

    return new IdentityCommand();
  }
}
