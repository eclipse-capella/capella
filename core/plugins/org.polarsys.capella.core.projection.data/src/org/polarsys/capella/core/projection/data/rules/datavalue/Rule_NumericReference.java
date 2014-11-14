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
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.NumericReference;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.tiger.helpers.Query;

public class Rule_NumericReference extends Rule_DataValue {

  public Rule_NumericReference() {
    super(DatavaluePackage.Literals.NUMERIC_REFERENCE, DatavaluePackage.Literals.NUMERIC_REFERENCE);
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    NumericReference numericRefSrc = (NumericReference) element_p;
    NumericReference numericRefTgt = (NumericReference) Query.retrieveFirstTransformedElement(element_p, context_p.getTransfo());

    // Update the NumericValue referenced
    NumericValue numericValueReferenced = numericRefSrc.getReferencedValue();
    if (null != numericValueReferenced) {
      numericRefTgt.setReferencedValue(numericValueReferenced);
    }

    // Update the Property referenced
    Property propertyReferenced = numericRefSrc.getReferencedProperty();
    if (null != propertyReferenced) {
      numericRefTgt.setReferencedProperty(propertyReferenced);
    }
    super.attachRelated(element_p, result_p, context_p);

  }

}
