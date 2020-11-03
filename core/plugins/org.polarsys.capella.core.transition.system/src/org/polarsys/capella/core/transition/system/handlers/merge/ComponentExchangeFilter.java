/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeCategory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class ComponentExchangeFilter extends EObjectCategoryFilter {

  public ComponentExchangeFilter(IContext context) {
    super(context, FaPackage.Literals.COMPONENT_EXCHANGE);
  }

  @Override
  public boolean keepElement(Object element) {
    return element instanceof ComponentExchange || element instanceof ComponentExchangeCategory;
  }

}
