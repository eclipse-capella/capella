/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.scenario;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.extension.ITransfoEngineExecuteExt;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

/**
 * A pre-transfo extension that notifies the user when about to launch
 * a scenario transition for multi-instancerole scenarios. Such scenarios
 * are not yet supported. The notification shows a dialog that allows to
 * abort or confirm the launch of the transition.
 */
public class MultiInstanceRoleExtension implements ITransfoEngineExecuteExt {

  @Override
  public void preExecute(ITransfo itransfo) throws Exception {
    if (itransfo instanceof ScenarioTransfo) {
      if (itransfo.get(TransfoEngine.TRANSFO_SOURCE) instanceof Scenario) {
        Scenario scenario = (Scenario) itransfo.get(TransfoEngine.TRANSFO_SOURCE);
        if (ScenarioExt.isMultiInstanceRole(scenario)) {
          IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
          if (window != null) {
            if (!MessageDialog.openConfirm(window.getShell(), MultiInstanceRoleMessages.MultiInstanceRoleExtension_title,
                MultiInstanceRoleMessages.MultiInstanceRoleExtension_message)) {
              throw new OperationCanceledException(MultiInstanceRoleMessages.MultiInstanceRoleExtension_logmsg);
            } else {
              Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);
              logger.warn(MultiInstanceRoleMessages.MultiInstanceRoleExtension_logmsg_confirm + EObjectLabelProviderHelper.getText(scenario));
              return;
            }
          }
        }
      }
    }
  }

  @Override
  public void postExecute(ITransfo transfo) throws Exception {
    // no post execute code
  }

}
