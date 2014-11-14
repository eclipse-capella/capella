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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

public abstract class Rule_DataValue extends Rule_CapellaElement {

  public Rule_DataValue(EClass eSrc, EClass eTgt) {
    super(eSrc, eTgt);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    DataValue dataValue = (DataValue) source_p;

    result_p.addAll(dataValue.getOwnedPropertyValues());
    result_p.addAll(dataValue.getOwnedPropertyValueGroups());
    result_p.addAll(dataValue.getOwnedConstraints());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    // Update the AbstractType reference
    DataValue dataValueSrc = (DataValue) element_p;
    DataValue dataValueTgt = (DataValue) Query.retrieveFirstTransformedElement(element_p, context_p.getTransfo());
    if (null != dataValueTgt) {
      AbstractType typeSrc = dataValueSrc.getAbstractType();
      if (null != typeSrc) {
        // Try to find an Type transformed
        AbstractType typeTarget = (AbstractType) Query.retrieveFirstTransformedElement(typeSrc, context_p.getTransfo());
        if (null != typeTarget) {
          dataValueTgt.setAbstractType(typeTarget);
        } else {
          dataValueTgt.setAbstractType(typeSrc);
        }
      }
    }
  }

}
