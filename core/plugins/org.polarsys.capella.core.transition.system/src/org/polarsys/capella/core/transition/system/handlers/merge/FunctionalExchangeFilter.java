/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.polarsys.capella.core.data.fa.ExchangeCategory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class FunctionalExchangeFilter extends EObjectCategoryFilter {

  public FunctionalExchangeFilter(IContext context) {
    super(context, FaPackage.Literals.FUNCTIONAL_EXCHANGE);
  }

  @Override
  public boolean keepElement(Object element) {
    return element instanceof FunctionalExchange || element instanceof ExchangeCategory;
  }

}
