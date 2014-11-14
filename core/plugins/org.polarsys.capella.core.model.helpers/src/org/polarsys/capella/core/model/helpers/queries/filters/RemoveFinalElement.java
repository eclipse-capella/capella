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
package org.polarsys.capella.core.model.helpers.queries.filters;

import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;

/**
 */
public class RemoveFinalElement implements IQueryFilter {

  @Override
  public boolean keepElement(Object element_p, IQueryContext context_p) {
	FinalizableElement finalizableElt = (FinalizableElement) element_p;
    return !finalizableElt.isFinal();
  }

}
