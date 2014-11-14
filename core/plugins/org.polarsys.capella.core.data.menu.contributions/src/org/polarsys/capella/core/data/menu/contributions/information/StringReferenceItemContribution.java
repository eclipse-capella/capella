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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.CollectionValue;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.StringType;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

public class StringReferenceItemContribution extends DataNamingHelperBasedContribution {
  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#selectionContribution()
   */
  @Override
  public boolean selectionContribution(ModelElement modelElement_p, EClass cls_p, EStructuralFeature feature_p) {

    if (feature_p == ModellingcorePackage.Literals.ABSTRACT_CONSTRAINT__OWNED_SPECIFICATION){
      return false;
    }

    boolean select = !(modelElement_p instanceof AbstractFunction);

    if (feature_p.equals(InformationPackage.Literals.COLLECTION_VALUE__OWNED_ELEMENTS)
     || feature_p.equals(InformationPackage.Literals.COLLECTION_VALUE__OWNED_DEFAULT_ELEMENT))
    {
      if (modelElement_p instanceof CollectionValue) {
        AbstractType cvType = ((CollectionValue) modelElement_p).getAbstractType();
        if (cvType instanceof Collection) {
          Type cType = ((Collection) cvType).getType(); {
            if (!(cType instanceof StringType)) {
              select = false;
            }
          }
        }
      }
    }

    if (feature_p.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE)
     || feature_p.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_VALUE)
     || feature_p.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_VALUE)
     || feature_p.equals(InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_NULL_VALUE))
    {
      if (modelElement_p instanceof Collection) {
        Type cType = ((Collection) modelElement_p).getType();
        if (!(cType instanceof StringType)) {
          select = false;
        }
      }
    }

    return select;
  }

  /**
   * @see org.polarsys.capella.common.ui.menu.IMDEMenuItemContribution#getMetaclass()
   */
  @Override
  public EClass getMetaclass() {
    return DatavaluePackage.Literals.STRING_REFERENCE;
  }
}
