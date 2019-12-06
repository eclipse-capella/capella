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
package org.polarsys.capella.core.data.la.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class LogicalComponentRealizedSystemComponentsResolver extends AbstractCapellaMarkerResolution {

  @Override
  public void run(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);
    if (!modelElements.isEmpty()) {
      // execute a read write command
      TransactionHelper.getExecutionManager(modelElements).execute(new AbstractReadWriteCommand() {
        @Override
        public void run() {
          SystemComponent systemComponent = null;
          LogicalComponent logicalComponent = null;
          Project project = ProjectExt.getProject(modelElements.get(0));
          BlockArchitecture architecture = BlockArchitectureExt
              .getBlockArchitecture(CtxPackage.Literals.SYSTEM_ANALYSIS, project);
          systemComponent = (SystemComponent) architecture.getSystem();
          for (EObject obj : modelElements) {
            if (obj instanceof LogicalComponent) {
              logicalComponent = (LogicalComponent) obj;
            }
          }
          if (logicalComponent != null) {
            ComponentRealization cr = CsFactory.eINSTANCE.createComponentRealization();
            cr.setSourceElement(logicalComponent);
            cr.setTargetElement(systemComponent);
            logicalComponent.getOwnedComponentRealizations().add(cr);
          }
        }
      });
    }
    deleteMarker(marker);
  }
}
