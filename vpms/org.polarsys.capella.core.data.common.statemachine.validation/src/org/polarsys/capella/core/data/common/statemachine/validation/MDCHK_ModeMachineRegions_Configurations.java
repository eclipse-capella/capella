/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.common.statemachine.validation;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.common.tools.report.util.ReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.StateMachine;

public class MDCHK_ModeMachineRegions_Configurations extends AbstractModelConstraint {
  /*
   * (non-Javadoc)
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    if (ctx.getTarget() instanceof StateMachine) {
      StateMachine stateMachine = (StateMachine) ctx.getTarget();
      if (!stateMachine.getOwnedRegions().isEmpty()) {
        EList<EList<Mode>> modesOfRegions = new BasicEList<EList<Mode>>();
        for (Region region : stateMachine.getOwnedRegions()) {
          EList<Mode> modesOfRegion = new BasicEList<Mode>();
          modesOfRegion.addAll(StateMachineUtils.getAllModes(region));
          modesOfRegions.add(modesOfRegion);
        }
        CartesianProduct<Mode> cartesianProduct = new CartesianProduct<Mode>(modesOfRegions);

        EList<GeneralMode> generalModes = new BasicEList<GeneralMode>();

        EList<EList<Mode>> cartesianProductResult = cartesianProduct.compute();
        for (EList<Mode> modes : cartesianProductResult) {
          GeneralMode generalMode = new GeneralMode();
          generalMode.getModes().addAll(modes);
          generalMode.getConfigurations().addAll(StateMachineUtils.getCommonConfigurations(modes));
          generalModes.add(generalMode);
        }

        display(generalModes);
      }
      return ctx.createSuccessStatus();
    }
    return null;
  }

  /**
   * TODO: remove this method once the output is used in a dedicated GUI.
   * @param generalModes
   */
  public void display(EList<GeneralMode> generalModes) {
    Logger modelLogger = ReportManagerDefaultComponents.getReportManagerForModel();   
    for (int i = 0; i < generalModes.size(); i++){
      GeneralMode generalMode = generalModes.get(i);
      modelLogger.info(("'Global Mode" + i + "' composed of:" + generalMode.getName())); //$NON-NLS-1$ //$NON-NLS-2$
      modelLogger.info(" the configurations available for this Global Mode: "); //$NON-NLS-1$
      modelLogger.info(generalMode.getConfigurationsName());
    }
  }
}
