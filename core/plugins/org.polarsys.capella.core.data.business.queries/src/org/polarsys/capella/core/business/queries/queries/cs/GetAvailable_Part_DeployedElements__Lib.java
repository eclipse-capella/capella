/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.business.queries.queries.cs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.ExtendingQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.queries.QueryExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

@SuppressWarnings("restriction")
@ExtendingQuery(extendingQuery = GetAvailable_Part_DeployedElements.class)
public class GetAvailable_Part_DeployedElements__Lib extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    List<Object> availableParts = new ArrayList<>();

    Part deployTargetPart = (Part) input;
    AbstractType typeOfDeployTargetPart = deployTargetPart.getAbstractType();

    if (typeOfDeployTargetPart instanceof PhysicalComponent) {
      PhysicalComponent deployTargetComponent = (PhysicalComponent) typeOfDeployTargetPart;
      
      EList<Part> deployedPartsOnTarget = deployTargetPart.getDeployedParts();
      Collection<Part> ancestorsOfTarget = ComponentExt.getPartAncestors(deployTargetPart);
      
      IModel currentProject = ILibraryManager.INSTANCE.getModel(deployTargetComponent);
      BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(deployTargetComponent);
      Collection<IModel> libraries = LibraryManagerExt.getAllActivesReferences(currentProject);

      for (IModel library : libraries) {
        CapellaElement correspondingInput = (CapellaElement) QueryExt.getCorrespondingElementInLibrary(architecture,
            (CapellaModel) library);

        List<PhysicalComponent> allComponents = SystemEngineeringExt.getAllPhysicalComponents(correspondingInput);
        for (PhysicalComponent deployComponent : allComponents) {
          if (PhysicalComponentExt.canDeploy(deployComponent, deployTargetComponent)) {
            for (Part deployPart : deployComponent.getRepresentingParts()) {
              boolean deployable = PartExt.isDeployable(deployPart);
              boolean deployedInTarget = deployedPartsOnTarget.contains(deployPart);
              boolean isNotAncestorOfTarget = !ancestorsOfTarget.contains(deployPart);
              
              if ((deployable || deployedInTarget) && isNotAncestorOfTarget) {
                availableParts.add(deployPart);
              }
            }
          }
        }
        
      }
    }
    
    return availableParts;
  }

}
