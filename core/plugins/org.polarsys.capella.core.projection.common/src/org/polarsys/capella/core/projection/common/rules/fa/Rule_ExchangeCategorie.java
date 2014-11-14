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
package org.polarsys.capella.core.projection.common.rules.fa;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;

public class Rule_ExchangeCategorie extends Rule_CapellaElement {

  public Rule_ExchangeCategorie() {
    super(FaPackage.Literals.EXCHANGE_CATEGORY, FaPackage.Literals.EXCHANGE_CATEGORY);
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject parent = element_p.eContainer();
    while (parent != null) {

      List<?> elements = TraceabilityHandlerHelper.getInstance(context_p).retrieveTracedElements(parent, context_p);
      if (elements.size() > 0) {
        return (EObject) elements.get(0);
      }

      parent = parent.eContainer();
    }
    return null;
  }
}
