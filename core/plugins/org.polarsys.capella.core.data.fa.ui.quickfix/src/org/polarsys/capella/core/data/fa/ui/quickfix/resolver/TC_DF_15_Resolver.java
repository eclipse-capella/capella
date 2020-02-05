/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.ui.quickfix.resolver;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * 
 * TC_DF_15 - This rule ensures the realization consistency between Root Functions.
 *
 */
public class TC_DF_15_Resolver extends AbstractCapellaMarkerResolution {
  @Override
  public void run(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);
    if (!modelElements.isEmpty()) {
      // execute a read write command
      TransactionHelper.getExecutionManager(modelElements).execute(new AbstractReadWriteCommand() {
        @Override
        public void run() {
          Project project = ProjectExt.getProject(modelElements.get(0));
          for (EObject obj : modelElements) {
            addFunctionRealization(project, obj);
          }
        }
      });
    }
    deleteMarker(marker);
  }

  private void addFunctionRealization(Project project, EObject obj) {
    AbstractFunction targetFunction = null;
    AbstractFunction sourceFunction = null;
    BlockArchitecture currentLevelArchitecture = BlockArchitectureExt.getRootBlockArchitecture(obj);
    List<BlockArchitecture> previousArchitectures = BlockArchitectureExt
        .getPreviousBlockArchitectures(currentLevelArchitecture);
    if(!previousArchitectures.isEmpty()) {
      AbstractFunction rootfunc = BlockArchitectureExt.getRootFunction(currentLevelArchitecture);
      if(rootfunc.getOwnedFunctionRealizations().isEmpty()) {
        targetFunction = BlockArchitectureExt.getRootFunction(previousArchitectures.get(previousArchitectures.size() - 1));
        sourceFunction = (AbstractFunction) obj;
        FunctionRealization rlz = FaFactory.eINSTANCE.createFunctionRealization();
        rlz.setSourceElement(sourceFunction);
        rlz.setTargetElement(targetFunction);
        sourceFunction.getOwnedFunctionRealizations().add(rlz);
      }
    }
  }

  @Override
  protected boolean enabled(Collection<IMarker> markers) {
    for (IMarker iMarker : markers) {
      return isEnabled(iMarker);
    }
    return super.enabled(markers);
  }
  
  private boolean isEnabled(IMarker marker) {
    final List<EObject> modelElements = getModelElements(marker);
    if(!modelElements.isEmpty()) {
      BlockArchitecture currentLevelArchitecture = BlockArchitectureExt.getRootBlockArchitecture(modelElements.get(0));
      List<BlockArchitecture> previousArchitectures = BlockArchitectureExt
          .getPreviousBlockArchitectures(currentLevelArchitecture);
      if(previousArchitectures.isEmpty()) {
        return false;
      }
      if (previousArchitectures.get(previousArchitectures.size()-1) == null) {
        return false;
      }
      
      AbstractFunction rootfunc = BlockArchitectureExt.getRootFunction(currentLevelArchitecture);
      if (!rootfunc.getOwnedFunctionRealizations().isEmpty()) {
        return false;
      }
    }
    return true;
  }

}