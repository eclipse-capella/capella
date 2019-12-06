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
package org.polarsys.capella.core.data.pa.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class PhysicalComponentRealizedLogicalComponentsResolver extends AbstractCapellaMarkerResolution {

  @Override
  public void run(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);
    if (!modelElements.isEmpty()) {
      // execute a read write command
      TransactionHelper.getExecutionManager(modelElements).execute(new AbstractReadWriteCommand() {
        @Override
        public void run() {
          LogicalComponent logicalComponent = null;
          PhysicalComponent physicalComponent = null;
          Project project = ProjectExt.getProject(modelElements.get(0));
          BlockArchitecture architecture = BlockArchitectureExt
              .getBlockArchitecture(LaPackage.Literals.LOGICAL_ARCHITECTURE, project);
          logicalComponent = (LogicalComponent) architecture.getSystem();
          for (EObject obj : modelElements) {
            if (obj instanceof PhysicalComponent) {
              physicalComponent = (PhysicalComponent) obj;
            }
          }
          if (physicalComponent != null) {
            ComponentRealization cr = CsFactory.eINSTANCE.createComponentRealization();
            cr.setSourceElement(physicalComponent);
            cr.setTargetElement(logicalComponent);
            physicalComponent.getOwnedComponentRealizations().add(cr);
          }
        }
      });
    }
    deleteMarker(marker);
  }
}
