/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.i;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExchangeExt;

public class Rule_I_37_38_ComponentExchange extends Rule_I37_38{

  LogicalComponent lc1;
  LogicalComponent lc2;
  ComponentExchange ce;

  LogicalComponent libraryRootComponent;

  @Override
  public void setUp() throws Exception {
    super.setUp();

    executeCommand(() -> {

      LogicalComponent rootLC = (LogicalComponent)projectSkeleton.getLogicalArchitecture().getSystem();

      lc1 = LaFactory.eINSTANCE.createLogicalComponent();
      rootLC.getOwnedLogicalComponents().add(lc1);
      CapellaElementExt.creationService(lc1);

      lc2 = LaFactory.eINSTANCE.createLogicalComponent();
      lc2 = LaFactory.eINSTANCE.createLogicalComponent();
      rootLC.getOwnedLogicalComponents().add(lc2);
      CapellaElementExt.creationService(lc2);

      Part sourcePart = (Part) lc1.getAbstractTypedElements().iterator().next();
      Part targetPart = (Part) lc2.getAbstractTypedElements().iterator().next();

      ce = ComponentExchangeExt.createComponentExchange(sourcePart, null, targetPart, null, null, null, null, null, null, false);

      libraryRootComponent = (LogicalComponent)librarySkeleton.getLogicalArchitecture().getSystem();

    });

  }

  @SuppressWarnings("nls")
  @Override
  public void test() throws Exception {

      // can't move just one of the components
      expectRollback(() -> {
        libraryRootComponent.getOwnedLogicalComponents().add(lc1);
      }, "'C 1' cannot reference moved element 'CP 1' via 'source'");

      expectRollback(() -> {
        libraryRootComponent.getOwnedLogicalComponents().add(lc2);
      }, "'C 1' cannot reference moved element 'CP 1' via 'target'");

      // can't move one component + the exchange
      expectRollback(() -> {
        libraryRootComponent.getOwnedLogicalComponents().add(lc1);
        libraryRootComponent.getOwnedComponentExchanges().add(ce);
      }, "'C 1' cannot reference moved element 'CP 1' via 'target'");

      // can't move one component + the exchange
      expectRollback(() -> {
        libraryRootComponent.getOwnedLogicalComponents().add(lc2);
        libraryRootComponent.getOwnedComponentExchanges().add(ce);
      }, "'C 1' cannot reference moved element 'CP 1' via 'source'");

      // can move both components + exchange
      expectNoRollback(() -> {
        libraryRootComponent.getOwnedLogicalComponents().add(lc1);
        libraryRootComponent.getOwnedLogicalComponents().add(lc2);
        libraryRootComponent.getOwnedComponentExchanges().add(ce);
      });

  }

}
