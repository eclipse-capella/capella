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

import org.polarsys.capella.core.data.information.CollectionValue;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.projection.common.context.IContext;

/**
 */
public class Rule_CollectionValue extends Rule_DataValue {

  public Rule_CollectionValue() {
    super(InformationPackage.Literals.COLLECTION_VALUE, InformationPackage.Literals.COLLECTION_VALUE);
  }

  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);
    CollectionValue element = (CollectionValue) source_p;
    result_p.add(element.getOwnedDefaultElement());
    result_p.addAll(element.getOwnedElements());
  }
  
  
}
