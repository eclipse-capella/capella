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
package org.polarsys.capella.core.data.ctx.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class SystemAnalysisRealizedOperationalAnalysisResolver extends AbstractCapellaMarkerResolution {

  @Override
  public void run(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);
    if (!modelElements.isEmpty()) {
      // execute a read write command
      TransactionHelper.getExecutionManager(modelElements).execute(new AbstractReadWriteCommand() {
        @Override
        public void run() {
          OperationalAnalysis operationalAnalysis = null;
          SystemAnalysis systemAnalysis = null;
          Project project = ProjectExt.getProject(modelElements.get(0));
          BlockArchitecture architecture = BlockArchitectureExt
              .getBlockArchitecture(OaPackage.Literals.OPERATIONAL_ANALYSIS, project);
          operationalAnalysis = (OperationalAnalysis) architecture;
          for (EObject obj : modelElements) {
            if (obj instanceof SystemAnalysis) {
              systemAnalysis = (SystemAnalysis) obj;
            }
          }
          if (systemAnalysis != null) {
            OperationalAnalysisRealization lar = CtxFactory.eINSTANCE.createOperationalAnalysisRealization();
            lar.setSourceElement(systemAnalysis);
            lar.setTargetElement(operationalAnalysis);
            systemAnalysis.getOwnedOperationalAnalysisRealizations().add(lar);
          }
        }
      });
    }
    deleteMarker(marker);
  }
}
