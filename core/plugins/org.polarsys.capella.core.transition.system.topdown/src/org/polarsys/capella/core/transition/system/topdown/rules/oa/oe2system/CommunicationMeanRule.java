/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.rules.oa.oe2system;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeKind;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.transition.system.topdown.rules.fa.ComponentExchangeRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class CommunicationMeanRule extends ComponentExchangeRule{

  @Override
  protected void updateElement(EObject element, EObject result, IContext context) {
    super.updateElement(element, result, context);
    if (element instanceof CommunicationMean && result instanceof ComponentExchange) {
      CommunicationMean mean = (CommunicationMean) element;
      ComponentExchange exchange = (ComponentExchange) result;
      if (ComponentExchangeKind.UNSET.equals(mean.getKind())) {
        exchange.setKind(ComponentExchangeKind.FLOW);
      }
    }
  }
}
