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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.ValuePart;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

public class Rule_ValuePart extends Rule_CapellaElement {

  public Rule_ValuePart() {
    super(DatavaluePackage.Literals.VALUE_PART, DatavaluePackage.Literals.VALUE_PART);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    ValuePart valuePart = (ValuePart) source_p;

    DataValue dataValue = valuePart.getOwnedValue();
    if (dataValue != null) {
      result_p.add(dataValue);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return DatavaluePackage.Literals.COMPLEX_VALUE__OWNED_PARTS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    ValuePart valuePartSrc = (ValuePart) element_p;
    ValuePart valuePartTgt = (ValuePart) result_p;
    // Update the Property referenced
    Property propertyReferencedSrc = valuePartSrc.getReferencedProperty();
    Property propertyReferencedTgt = (Property) Query.retrieveFirstTransformedElement(propertyReferencedSrc, context_p.getTransfo());
    if (null != propertyReferencedTgt) {
      TigerRelationshipHelper.attachElementByRel(valuePartTgt, propertyReferencedTgt, DatavaluePackage.Literals.VALUE_PART__REFERENCED_PROPERTY);
    }
  }

}
