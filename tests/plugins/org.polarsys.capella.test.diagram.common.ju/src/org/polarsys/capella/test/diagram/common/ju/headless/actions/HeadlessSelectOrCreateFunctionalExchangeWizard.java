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
package org.polarsys.capella.test.diagram.common.ju.headless.actions;

import java.util.List;
import java.util.Set;

import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.sirius.analysis.actions.extensions.SelectOrCreateFunctionalExchangeWizard;
import org.polarsys.capella.test.diagram.common.ju.headless.HeadlessResultOpProvider;
import org.polarsys.capella.test.diagram.common.ju.headless.IHeadlessResult;

public class HeadlessSelectOrCreateFunctionalExchangeWizard extends SelectOrCreateFunctionalExchangeWizard {

  protected void selectOrCreateFunctionalExchangeData(Set<FunctionalExchange> availableFEs) {

    IHeadlessResult itwr = HeadlessResultOpProvider.INSTANCE.getCurrentOp();

    Object result = itwr.getResult(null, null);

    FunctionalExchange involvedFE = null;
    AbstractFunction feSource = null;
    AbstractFunction feTarget = null;

    // We simulate the user's choice of either SELECT or CREATE
    if (result instanceof FunctionalExchange) {

      involvedFE = (FunctionalExchange) result;
      feSource = FunctionalExchangeExt.getSourceFunction(involvedFE);
      feTarget = FunctionalExchangeExt.getTargetFunction(involvedFE);
    }

    else if (result instanceof List) {

      @SuppressWarnings("unchecked")
      List<AbstractFunction> functionalExchangeEnds = (List<AbstractFunction>) result;

      feSource = functionalExchangeEnds.get(0);
      feTarget = functionalExchangeEnds.get(1);
      involvedFE = FunctionalExchangeExt.createFunctionalExchange(feSource, feTarget);
      involvedFE.setName("TestExchange");
      ((AbstractFunction) feSource.eContainer()).getOwnedFunctionalExchanges().add(involvedFE);
    }

    // The first parameter is null because it is not needed, since all the information is provided in this method
    createFunctionalExchange(null, feSource, feTarget, involvedFE);
  }
}
