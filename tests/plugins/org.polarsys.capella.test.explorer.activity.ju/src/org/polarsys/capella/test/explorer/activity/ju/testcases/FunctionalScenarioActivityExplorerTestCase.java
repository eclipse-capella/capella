/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.test.explorer.activity.ju.testcases;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;

public abstract class FunctionalScenarioActivityExplorerTestCase extends DiagramActivityExplorerTestCase {
  Scenario scenario;
  EList<? extends AbstractCapability> listOfCapabilities;

  @Override
  protected boolean getResultOfCreateDiagram() {
    return link.createDiagram(listOfCapabilities.get(0), session);
  }
  
  @Override
  protected void preTest() {
    ModelElement modelElement = getTestModelElement();
    listOfCapabilities = getOwnedCapabilities();
    EList<Scenario> listOfScenarios = listOfCapabilities.get(0).getOwnedScenarios();
    String scenarioID = listOfScenarios.get(0).getId();
    scenario = context.getSemanticElement(scenarioID);
    assertEquals((Scenario) modelElement, scenario);
    
  }

  private EList<? extends AbstractCapability> getOwnedCapabilities() {
    if (structure instanceof OperationalCapabilityPkg) {
      OperationalCapabilityPkg oc = (OperationalCapabilityPkg) structure;
      return oc.getOwnedOperationalCapabilities();
    }
    if (structure instanceof CapabilityRealizationPkg) {
      CapabilityRealizationPkg cr = (CapabilityRealizationPkg) structure;
      return cr.getOwnedCapabilityRealizations();
    }
    if (structure instanceof CapabilityPkg) {
      CapabilityPkg cp = (CapabilityPkg) structure;
      return cp.getOwnedCapabilities();
    }

    return null;
  }
}
