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

import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;

public class Rule_I_37_38_PhysicalLink extends Rule_I37_38{

  PhysicalComponent pc1;
  PhysicalComponent pc2;
  PhysicalLink pl;

  PhysicalComponent libraryRootComponent;

  @Override
  public void setUp() throws Exception {
    super.setUp();

    executeCommand(() -> {

      PhysicalComponent rootPC = (PhysicalComponent)projectSkeleton.getPhysicalArchitecture().getSystem();

      pc1 = PaFactory.eINSTANCE.createPhysicalComponent();
      rootPC.getOwnedPhysicalComponents().add(pc1);
      CapellaElementExt.creationService(pc1);

      PhysicalPort pp1 = CsFactory.eINSTANCE.createPhysicalPort();
      pc1.getOwnedFeatures().add(pp1);
      CapellaElementExt.creationService(pp1);

      pc2 = PaFactory.eINSTANCE.createPhysicalComponent();
      rootPC.getOwnedPhysicalComponents().add(pc2);
      CapellaElementExt.creationService(pc2);

      PhysicalPort pp2 = CsFactory.eINSTANCE.createPhysicalPort();
      pc2.getOwnedFeatures().add(pp2);
      CapellaElementExt.creationService(pp2);

      pl = CsFactory.eINSTANCE.createPhysicalLink();
      rootPC.getOwnedPhysicalLinks().add(pl);
      CapellaElementExt.creationService(pl);

      pl.getLinkEnds().add(pp1);
      pl.getLinkEnds().add(pp2);

      libraryRootComponent = (PhysicalComponent)librarySkeleton.getPhysicalArchitecture().getSystem();

    });

  }

  @SuppressWarnings("nls")
  @Override
  public void test() throws Exception {

      // can't move just one of the components
      expectRollback(() -> {
        libraryRootComponent.getOwnedPhysicalComponents().add(pc1);
      }, "'PL 1' cannot reference moved element 'PP 1' via 'linkEnds'");

      expectRollback(() -> {
        libraryRootComponent.getOwnedPhysicalComponents().add(pc2);
      }, "'PL 1' cannot reference moved element 'PP 1' via 'linkEnds'");

      // can't move one component + the exchange
      expectRollback(() -> {
        libraryRootComponent.getOwnedPhysicalComponents().add(pc1);
        libraryRootComponent.getOwnedPhysicalLinks().add(pl);
      }, "'PL 1' cannot reference moved element 'PP 1' via 'linkEnds'");

      // can't move one component + the exchange
      expectRollback(() -> {
        libraryRootComponent.getOwnedPhysicalComponents().add(pc2);
        libraryRootComponent.getOwnedPhysicalLinks().add(pl);
      }, "'PL 1' cannot reference moved element 'PP 1' via 'linkEnds'");

      // can move both components + exchange
      expectNoRollback(() -> {
        libraryRootComponent.getOwnedPhysicalComponents().add(pc1);
        libraryRootComponent.getOwnedPhysicalComponents().add(pc2);
        libraryRootComponent.getOwnedPhysicalLinks().add(pl);
      });

  }

}
