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
package org.polarsys.capella.core.transition.system.retrievers;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.transition.common.handlers.scope.TypedScopeRetriever;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class PartTypeRetriever extends TypedScopeRetriever<Part> {

  public PartTypeRetriever() {
    super(Part.class);
  }

  @Override
  public Collection<? extends EObject> doRetrieveRelatedElements(Part part, IContext context_p) {
    if (part.getAbstractType() != null) {
      return Collections.singleton(part.getAbstractType());
    }
    return Collections.emptyList();
  }

}
