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

import org.polarsys.capella.core.data.information.CollectionValueReference;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;

/**
 */
public class Rule_CollectionValueReference extends Rule_DataValue {

  
  public Rule_CollectionValueReference() {
    super(InformationPackage.Literals.COLLECTION_VALUE_REFERENCE,InformationPackage.Literals.COLLECTION_VALUE_REFERENCE);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    CollectionValueReference element = (CollectionValueReference) source_p;
    result_p.add(element.getReferencedProperty());
    result_p.add(element.getReferencedValue());
  }

  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p,
        InformationPackage.Literals.COLLECTION_VALUE_REFERENCE__REFERENCED_PROPERTY, context_p);
    AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InformationPackage.Literals.COLLECTION_VALUE_REFERENCE__REFERENCED_VALUE,
        context_p);
  }
}
