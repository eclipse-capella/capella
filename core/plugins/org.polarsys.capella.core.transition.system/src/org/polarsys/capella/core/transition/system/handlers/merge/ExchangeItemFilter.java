/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class ExchangeItemFilter extends EObjectCategoryFilter {

  public ExchangeItemFilter(IContext context) {
    super(context, InformationPackage.Literals.EXCHANGE_ITEM);
  }

  @Override
  public boolean keepElement(Object element) {
    return element instanceof ExchangeItem || element instanceof ExchangeItemElement;
  }

}
