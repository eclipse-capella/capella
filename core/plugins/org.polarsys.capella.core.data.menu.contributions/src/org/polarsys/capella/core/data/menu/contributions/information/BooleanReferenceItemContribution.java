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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.CollectionValue;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public class BooleanReferenceItemContribution extends DataNamingHelperBasedContribution {
  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  @Override
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    boolean select = !(modelElement instanceof AbstractFunction);

    if (feature.equals(InformationPackage.Literals.COLLECTION_VALUE__OWNED_ELEMENTS)
     || feature.equals(InformationPackage.Literals.COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT))
    {
      if (modelElement instanceof CollectionValue) {
        AbstractType cvType = ((CollectionValue) modelElement).getAbstractType();
        if (cvType instanceof Collection) {
          Type cType = ((Collection) cvType).getType(); {
            if (!(cType instanceof BooleanType)) {
              select = false;
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
        if (!(cType instanceof BooleanType)) {
          select = false;
        }
      }
    }

    return select;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return DatavaluePackage.Literals.BOOLEAN_REFERENCE;
  }
}
