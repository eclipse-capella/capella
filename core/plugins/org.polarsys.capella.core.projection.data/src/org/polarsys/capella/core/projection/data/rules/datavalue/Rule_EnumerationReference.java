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
package org.polarsys.capella.core.projection.data.rules.datavalue;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.AbstractEnumerationValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.EnumerationReference;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.tiger.helpers.Query;

public class Rule_EnumerationReference extends Rule_DataValue {

  public Rule_EnumerationReference() {
    super(DatavaluePackage.Literals.ENUMERATION_REFERENCE, DatavaluePackage.Literals.ENUMERATION_REFERENCE);
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    EnumerationReference enumRefSrc = (EnumerationReference) element_p;
    EnumerationReference enumRefTgt = (EnumerationReference) Query.retrieveFirstTransformedElement(element_p, context_p.getTransfo());

    // Update the AbstractEnumerationValue referenced
    AbstractEnumerationValue enumValueReferencedSrc = enumRefSrc.getReferencedValue();
    if (null != enumValueReferencedSrc) {
      AbstractEnumerationValue enumValueReferencedTgt =
          (AbstractEnumerationValue) Query.retrieveFirstTransformedElement(enumValueReferencedSrc, context_p.getTransfo());
      if (enumValueReferencedTgt != null) {
        enumRefTgt.setReferencedValue(enumValueReferencedTgt);
      } else {
        enumRefTgt.setReferencedValue(enumValueReferencedSrc);
      }
    }

    // Update the Property referenced
    Property propertyReferencedSrc = enumRefSrc.getReferencedProperty();
    if (null != propertyReferencedSrc) {
      Property propertyReferencedTgt = (Property) Query.retrieveFirstTransformedElement(propertyReferencedSrc, context_p.getTransfo());
      if (propertyReferencedTgt != null) {
        enumRefTgt.setReferencedProperty(propertyReferencedTgt);
      } else {
        enumRefTgt.setReferencedProperty(propertyReferencedSrc);
      }
    }
  }

}
