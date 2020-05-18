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
package org.polarsys.capella.core.data.cs.ui.quickfix.generator;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IMarkerResolution;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.PortExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractMarkerResolutionGenerator;
import org.polarsys.capella.core.validation.ui.ide.quickfix.DeleteCommandResolver;

public class DWF_DC_30_Generator extends AbstractMarkerResolutionGenerator {

  @Override
  protected IMarkerResolution[] doGetResolutions(IMarker marker) {

    List<EObject> modelElements = MarkerViewHelper.getModelElementsFromMarker(marker);
    if (modelElements.isEmpty() || !(modelElements.get(0) instanceof PhysicalPort)) {
      return new IMarkerResolution[0];
    }

    PhysicalPort port = (PhysicalPort) modelElements.get(0);
    PhysicalComponent physicalComponent = (PhysicalComponent) PortExt.getRelatedComponent(port);

    DeleteCommandResolver deletePortResolver = new DeleteCommandResolver("Delete Physical Port", port);

    AbstractCapellaMarkerResolution updateComponentToNodeResolver = new AbstractCapellaMarkerResolution() {

      @Override
      public void run(IMarker marker) {
        ExecutionManager executionManager = TransactionHelper.getExecutionManager(physicalComponent);

        executionManager.execute(new AbstractReadWriteCommand() {
          @Override
          public void run() {
            physicalComponent.setNature(PhysicalComponentNature.NODE);
          }
        });

      }
    };

    updateComponentToNodeResolver.setLabel("Switch Component to NODE");

    return new IMarkerResolution[] { deletePortResolver, updateComponentToNodeResolver };
  }

  @Override
  protected String getRuleId() {
    return "org.polarsys.capella.core.data.cs.validation.DWF_DC_30";
  }
}
