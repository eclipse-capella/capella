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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.CollectionValue;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class NumericReferenceItemContribution extends DataNamingHelperBasedContribution {
  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  @Override
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {

    if (feature == ModellingcorePackage.Literals.ABSTRACT_CONSTRAINT__OWNED_SPECIFICATION){
      return false;
    }

    boolean showMe = !(modelElement instanceof AbstractFunction);
    if (showMe &&
        (InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_LENGTH.equals(feature) ||
         InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_LENGTH.equals(feature)))
    {
      if (modelElement instanceof Property) {
        AbstractType type = ((Property) modelElement).getAbstractType();
        if (null != type && !(type instanceof StringType)) {
          showMe = false;
        }
      }
    }

    if (feature.equals(InformationPackage.Literals.COLLECTION_VALUE__OWNED_ELEMENTS)
     || feature.equals(InformationPackage.Literals.COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT))
    {
      if (modelElement instanceof CollectionValue) {
        AbstractType cvType = ((CollectionValue) modelElement).getAbstractType();
        if (cvType instanceof Collection) {
          Type cType = ((Collection) cvType).getType(); {
            if (!(cType instanceof NumericType)) {
              showMe = false;
            }
          }
        }
      }
    }

    if (feature.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE)
     || feature.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE)
     || feature.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE)
     || feature.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE))
    {
      if (modelElement instanceof Collection) {
        Type cType = ((Collection) modelElement).getType();
        if (!(cType instanceof NumericType)) {
          showMe = false;
        }
      }
    }

    return showMe;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  @Override
  public EClass getMetaclass() {
    return DatavaluePackage.Literals.NUMERIC_REFERENCE;
  }
}
