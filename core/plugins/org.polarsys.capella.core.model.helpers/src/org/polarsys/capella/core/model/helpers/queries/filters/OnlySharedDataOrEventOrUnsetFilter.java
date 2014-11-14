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

import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;

/**
 */
public class OnlySharedDataOrEventOrUnsetFilter implements IQueryFilter {

  @Override
  public boolean keepElement(Object element_p, IQueryContext context_p) {
    ExchangeItem item = (ExchangeItem) element_p;
    ExchangeMechanism mechanism = item.getExchangeMechanism();
    return (mechanism == ExchangeMechanism.EVENT) || (mechanism == ExchangeMechanism.SHARED_DATA) || (mechanism == ExchangeMechanism.UNSET);
  }

}
