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
import org.polarsys.capella.core.data.information.datavalue.AbstractStringValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.StringReference;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.tiger.helpers.Query;

public class Rule_StringReference extends Rule_DataValue {

  public Rule_StringReference() {
    super(DatavaluePackage.Literals.STRING_REFERENCE, DatavaluePackage.Literals.STRING_REFERENCE);
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    StringReference stringRefSrc = (StringReference) element_p;
    StringReference stringRefTgt = (StringReference) Query.retrieveFirstTransformedElement(element_p, context_p.getTransfo());

    // Update the StringValue referenced
    AbstractStringValue physicalValueReferenced = stringRefSrc.getReferencedValue();
    if (null != physicalValueReferenced) {
      stringRefTgt.setReferencedValue(physicalValueReferenced);
    }

    // Update the Property referenced
    Property propertyReferenced = stringRefSrc.getReferencedProperty();
    if (null != propertyReferenced) {
      stringRefTgt.setReferencedProperty(propertyReferenced);
    }
    super.attachRelated(element_p, result_p, context_p);

  }
}
