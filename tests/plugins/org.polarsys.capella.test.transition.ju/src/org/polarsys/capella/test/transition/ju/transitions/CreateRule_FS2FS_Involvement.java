/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.transitions;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * OA scenario should be transitioned to FA scenario and capability
 */
public class CreateRule_FS2FS_Involvement extends TopDownTransitionTestCase {
  private String id_s11 = "f11b4103-4d3e-4e56-861d-8e4e23ab56a0";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }

  @Override
  public void performTest() throws Exception {
    performFStoFSTransition(Arrays.asList(getObject(id_s11)));
    Scenario sc = (Scenario) mustBeTransitioned(id_s11);
    assertTrue(NLS.bind(Messages.ShouldBeA, "SC1", "FUNCTIONAL"), sc.getKind() == ScenarioKind.FUNCTIONAL);
    AbstractCapability capability = (AbstractCapability) sc.eContainer();
    assertTrue(capability.getInvolvedAbstractFunctions().stream().filter(Objects::nonNull).count() > 0);
  }

}
