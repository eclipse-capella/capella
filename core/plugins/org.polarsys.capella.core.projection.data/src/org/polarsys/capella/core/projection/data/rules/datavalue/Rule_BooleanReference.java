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
import org.polarsys.capella.core.data.information.datavalue.AbstractBooleanValue;
import org.polarsys.capella.core.data.information.datavalue.BooleanReference;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.tiger.helpers.Query;

public class Rule_BooleanReference extends Rule_DataValue {

  public Rule_BooleanReference() {
    super(DatavaluePackage.Literals.BOOLEAN_REFERENCE, DatavaluePackage.Literals.BOOLEAN_REFERENCE);
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    BooleanReference booleanRefSrc = (BooleanReference) element_p;
    BooleanReference booleanRefTgt = (BooleanReference) Query.retrieveFirstTransformedElement(element_p, context_p.getTransfo());

    // Update the BooleanValue referenced
    AbstractBooleanValue booleanValueReferenced = booleanRefSrc.getReferencedValue();
    if (null != booleanValueReferenced) {
      booleanRefTgt.setReferencedValue(booleanValueReferenced);
    }

    // Update the Property referenced
    Property propertyReferenced = booleanRefSrc.getReferencedProperty();
    if (null != propertyReferenced) {
      booleanRefTgt.setReferencedProperty(propertyReferenced);
    }
    super.attachRelated(element_p, result_p, context_p);

  }
}
