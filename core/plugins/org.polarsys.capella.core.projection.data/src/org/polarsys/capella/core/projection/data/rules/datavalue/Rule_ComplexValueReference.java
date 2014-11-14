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
import org.polarsys.capella.core.data.information.datavalue.AbstractComplexValue;
import org.polarsys.capella.core.data.information.datavalue.ComplexValueReference;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.tiger.helpers.Query;

public class Rule_ComplexValueReference extends Rule_DataValue {

  public Rule_ComplexValueReference() {
    super(DatavaluePackage.Literals.COMPLEX_VALUE_REFERENCE, DatavaluePackage.Literals.COMPLEX_VALUE_REFERENCE);
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    ComplexValueReference complexRefSrc = (ComplexValueReference) element_p;
    ComplexValueReference complexRefTgt = (ComplexValueReference) Query.retrieveFirstTransformedElement(element_p, context_p.getTransfo());

    // Update the AbstractComplexValue referenced
    AbstractComplexValue complexValueReferenced = complexRefSrc.getReferencedValue();
    if (null != complexValueReferenced) {
      complexRefTgt.setReferencedValue(complexValueReferenced);
    }

    // Update the Property referenced
    Property propertyReferenced = complexRefSrc.getReferencedProperty();
    if (null != propertyReferenced) {
      complexRefTgt.setReferencedProperty(propertyReferenced);
    }
    super.attachRelated(element_p, result_p, context_p);

  }

}
