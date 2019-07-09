/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries.queries.cs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.ExtendingQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.business.queries.queries.cs.GetAvailable_Part_DeployedElements;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.queries.QueryExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;

@ExtendingQuery (extendingQuery = GetAvailable_Part_DeployedElements.class)
public class GetAvailable_Part_DeployedElements__Lib extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    Part currentPart = (Part) input;
    List<CapellaElement> availableElements = new ArrayList<>(1);
    Collection<Part> parts = ComponentExt.getPartAncestors(currentPart);
    AbstractType abstractType = currentPart.getAbstractType();

    IModel currentProject =  ILibraryManager.INSTANCE.getModel(currentPart);
    BlockArchitecture block = BlockArchitectureExt.getRootBlockArchitecture(currentPart);
    Collection<IModel> libraries = LibraryManagerExt.getAllActivesReferences(currentProject);
    boolean isMultipleDeploymentAllowed = CapellaModelPreferencesPlugin.getDefault().isMultipleDeploymentAllowed();
    for (IModel library : libraries) {
      CapellaElement correspondingInput = (CapellaElement) QueryExt.getCorrespondingElementInLibrary(block, (CapellaModel) library);
      if (abstractType instanceof PhysicalComponent) {
        List<PhysicalComponent> behaviourComps = SystemEngineeringExt.getAllPhysicalComponents(correspondingInput);
        if (abstractType instanceof PhysicalComponent) {
          for (PhysicalComponent physicalComponent : behaviourComps) {
            PhysicalComponent currentPC = (PhysicalComponent) abstractType;
            if (!(currentPC.getNature().equals(PhysicalComponentNature.BEHAVIOR) && physicalComponent.getNature().equals(PhysicalComponentNature.NODE))
                && !(currentPC.getNature().equals(PhysicalComponentNature.UNSET)) && !(physicalComponent.getNature().equals(PhysicalComponentNature.UNSET))
                && !physicalComponent.equals(currentPC)) {
              getValidDeployablePart(availableElements, parts, physicalComponent, currentPart, isMultipleDeploymentAllowed);
            }
          }
        }
      }
    }
    return (List) availableElements;
  }

  private void getValidDeployablePart(List<CapellaElement> availableElements, Collection<Part> parts, Component physicalComponent, Part currentPart, boolean isMultipleDeploymentAllowed) {
    GetAvailable_Part_DeployedElements.getValidDeployablePart(availableElements, parts, physicalComponent, currentPart, isMultipleDeploymentAllowed);
  }
}
