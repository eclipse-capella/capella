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
   * @param editingDomain
   * @param containerElement
   * @param createdElement
   * @param feature
   * @return
   */
  public static Command getTypingCommand(EditingDomain editingDomain, ModelElement containerElement, ModelElement createdElement,
      EStructuralFeature feature) {

    if (createdElement instanceof ComplexValue) {
      if (containerElement.eClass().equals(InformationPackage.Literals.CLASS)) {
        return new SetCommand(editingDomain, createdElement, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, containerElement);

      } else if (DatavaluePackage.Literals.VALUE_PART.isInstance(containerElement)) {

        Property property = ((ValuePart) containerElement).getReferencedProperty();
        if (property != null) {
          AbstractType type = property.getAbstractType();
          if (type.eClass().equals(InformationPackage.Literals.CLASS)) {
            return new SetCommand(editingDomain, createdElement, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, type);
          }
        }
      }
    } else {
      if (feature.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE)
          || feature.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE)
          || feature.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE)
          || feature.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE)
          || feature.equals(DatatypePackage.Literals.NUMERIC_TYPE__OWNED_MIN_VALUE)
          || feature.equals(DatatypePackage.Literals.NUMERIC_TYPE__OWNED_MAX_VALUE)
          || feature.equals(DatatypePackage.Literals.NUMERIC_TYPE__OWNED_NULL_VALUE)
          || feature.equals(DatatypePackage.Literals.NUMERIC_TYPE__OWNED_DEFAULT_VALUE)
          || feature.equals(DatatypePackage.Literals.ENUMERATION__OWNED_MIN_VALUE) || feature.equals(DatatypePackage.Literals.ENUMERATION__OWNED_MAX_VALUE)
          || feature.equals(DatatypePackage.Literals.ENUMERATION__OWNED_NULL_VALUE)
          || feature.equals(DatatypePackage.Literals.ENUMERATION__OWNED_DEFAULT_VALUE)
          || feature.equals(DatatypePackage.Literals.STRING_TYPE__OWNED_NULL_VALUE)
          || feature.equals(DatatypePackage.Literals.STRING_TYPE__OWNED_DEFAULT_VALUE)
          || feature.equals(DatatypePackage.Literals.BOOLEAN_TYPE__OWNED_DEFAULT_VALUE)) {
        if (containerElement instanceof TypedElement) {
          AbstractType type = ((TypedElement) containerElement).getAbstractType();
          if (type != null) {
            return new SetCommand(editingDomain, createdElement, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, type);
          }
        } else {
          return new SetCommand(editingDomain, createdElement, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, containerElement);
        }
      }
    }

    return new IdentityCommand();
  }
}
