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
package org.polarsys.capella.core.projection.data.rules.propertyvalue;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;

public class Rule_EnumerationPropertyValue extends Rule_AbstractPropertyValue {

  public Rule_EnumerationPropertyValue() {
    super(CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE, CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    EnumerationPropertyValue value = (EnumerationPropertyValue) source_p;
    result_p.add(value.getType());
    result_p.add(value.getValue());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachToBestElement(element_p, result_p, CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE__TYPE, context_p);
    AttachmentHelper.getInstance(context_p).attachToBestElement(element_p, result_p, CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE__VALUE, context_p);
  }

}
