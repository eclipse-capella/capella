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
package org.polarsys.capella.core.data.ctx.ui.quickfix.resolver;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.impl.SystemEngineeringImpl;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.model.skeleton.ISkeletonServices;
import org.polarsys.capella.core.model.skeleton.impl.SkeletonServicesImpl;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * 
 * TJ_SA_04 - Create Operational Analysis and/or create realization link to Operational Analysis
 *
 */
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
            // Add Operational Analysis
            if (operationalAnalysis == null) {
              ISkeletonServices skeletonServices = new SkeletonServicesImpl();
              EList<ModelRoot> modelRoot = project.getOwnedModelRoots();
              if (!modelRoot.isEmpty()) {
                SystemEngineeringImpl eng = (SystemEngineeringImpl) modelRoot.get(0);
                skeletonServices.doOperationalAnalysis(eng);
                architecture = BlockArchitectureExt.getBlockArchitecture(OaPackage.Literals.OPERATIONAL_ANALYSIS,
                    project);
                operationalAnalysis = (OperationalAnalysis) architecture;
              }
            }

            OperationalAnalysisRealization lar = CtxFactory.eINSTANCE.createOperationalAnalysisRealization();
            lar.setSourceElement(systemAnalysis);
            lar.setTargetElement(operationalAnalysis);
            systemAnalysis.getOwnedOperationalAnalysisRealizations().add(lar);

            // Root FunctionRealization
            AbstractFunction sourceFunction = null;
            sourceFunction = BlockArchitectureExt.getRootFunction(systemAnalysis);

            if (sourceFunction.getOwnedFunctionRealizations().isEmpty()) {
              FunctionRealization rlz = FaFactory.eINSTANCE.createFunctionRealization();
              rlz.setSourceElement(sourceFunction);
              rlz.setTargetElement(BlockArchitectureExt.getRootFunction(operationalAnalysis));
              sourceFunction.getOwnedFunctionRealizations().add(rlz);

            }
          }
        }
      });
    }
    deleteMarker(marker);
  }
}
