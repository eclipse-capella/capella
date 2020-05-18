/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.la.ui.quickfix.resolver;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * 
 * TC_DC_12 : Add Component realization to Root System Component
 *
 */
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
          if (architecture != null) {
            systemComponent = (SystemComponent) architecture.getSystem();
            for (EObject obj : modelElements) {
              if (obj instanceof LogicalComponent) {
                logicalComponent = (LogicalComponent) obj;
              }
            }
            if (logicalComponent != null) {
              EList<ComponentRealization> componentRealizationList = logicalComponent.getOwnedComponentRealizations();
              ComponentRealization cr = null;
              if (!componentRealizationList.isEmpty()) {
                cr = componentRealizationList.get(0);
                if (cr.getSourceElement() != logicalComponent) {
                  cr.setSourceElement(logicalComponent);
                }
                if (cr.getTargetElement() != systemComponent) {
                  cr.setTargetElement(systemComponent);
                }
              } else {
                cr = CsFactory.eINSTANCE.createComponentRealization();
                cr.setSourceElement(logicalComponent);
                cr.setTargetElement(systemComponent);
                logicalComponent.getOwnedComponentRealizations().add(cr);
              }
            }
          }
        }
      });
    }
    deleteMarker(marker);
  }

  /**
   * Disabled if System Architecture does not exist.
   */
  @Override
  public boolean enabled(Collection<IMarker> markers) {
    for (IMarker iMarker : markers) {
      final List<EObject> modelElements = getModelElements(iMarker);
      if (!modelElements.isEmpty()) {
        Project project = ProjectExt.getProject(modelElements.get(0));
        BlockArchitecture architecture = BlockArchitectureExt.getBlockArchitecture(CtxPackage.Literals.SYSTEM_ANALYSIS,
            project);
        if (architecture == null) {
          return false;
        }
        Component cpkg = architecture.getSystem();
        if (cpkg == null) {
          return false;
        }
      }

    }
    return super.enabled(markers);
  }
}
