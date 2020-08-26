/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.scenario.helpers;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityExt;
import org.polarsys.capella.core.tiger.IFinalizer;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

public class ScenarioInvolvementSynchronizer implements IFinalizer {

  /**
   * @see org.polarsys.capella.core.tiger.IFinalizer#finalize(org.polarsys.capella.core.tiger.ITransfo)
   */
  public void finalize(ITransfo transfo_p) {
    Scenario scenario = (Scenario) transfo_p.get(TransfoEngine.TRANSFO_TARGET);
    if (scenario != null) {
      EObject container = scenario.eContainer();
      if (container instanceof AbstractCapability) {
        for (InstanceRole role : scenario.getOwnedInstanceRoles()) {
          if (role.getRepresentedInstance() instanceof Part) {
            Component component = (Component) role.getRepresentedInstance().getAbstractType();
            AbstractCapabilityExt.addInvolvedComponent((AbstractCapability) container, component);

          } else if (role.getRepresentedInstance() instanceof AbstractFunction) {
            AbstractFunction function = (AbstractFunction) role.getRepresentedInstance();
            AbstractCapabilityExt.addInvolvedFunction((AbstractCapability) container, function);
          }
        }
      }
    }
  }

}
