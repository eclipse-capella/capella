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
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;

public class GetAvailable_Part_DeployedElements extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    Part currentPart = (Part) input;
    List<CapellaElement> availableElements = new ArrayList<>(1);
    Collection<Part> parts = ComponentExt.getPartAncestors(currentPart);
    AbstractType abstractType = currentPart.getAbstractType();
    boolean isMultipleDeploymentAllowed = CapellaModelPreferencesPlugin.getDefault().isMultipleDeploymentAllowed();
    if (abstractType instanceof PhysicalComponent) {
      List<PhysicalComponent> behaviourComps = SystemEngineeringExt
          .getAllPhysicalComponents((CapellaElement) abstractType);
      if (!ComponentExt.isActor(abstractType)) {
        for (PhysicalComponent physicalComponent : behaviourComps) {
          PhysicalComponent currentPC = (PhysicalComponent) abstractType;
          if (!(currentPC.getNature().equals(PhysicalComponentNature.BEHAVIOR)
              && physicalComponent.getNature().equals(PhysicalComponentNature.NODE))
              && !(currentPC.getNature().equals(PhysicalComponentNature.UNSET))
              && !(physicalComponent.getNature().equals(PhysicalComponentNature.UNSET))
              && !physicalComponent.equals(currentPC)) {
            getValidDeployablePart(availableElements, parts, physicalComponent, currentPart,
                isMultipleDeploymentAllowed);
          }
        }
      } else {
        for (PhysicalComponent physicalComponent : behaviourComps) {
          if (!(physicalComponent.getNature().equals(PhysicalComponentNature.NODE))
              && !(physicalComponent.getNature().equals(PhysicalComponentNature.UNSET))) {
            getValidDeployablePart(availableElements, parts, physicalComponent, currentPart,
                isMultipleDeploymentAllowed);
          }
        }
      }
    }
    return (List) availableElements;

  }

  public static void getValidDeployablePart(List<CapellaElement> availableElements, Collection<Part> parts,
      Component physicalComponent, Part currentPart, boolean isMultipleDeploymentAllowed) {
    for (Part part : physicalComponent.getRepresentingParts()) {
      if (!parts.contains(part)) {
        if (isMultipleDeploymentAllowed) {
          availableElements.add(part);
        } else {
          boolean alreadyDeployedElsewhere = false;
          for (AbstractDeploymentLink link : part.getDeployingLinks()) {
            if (link.getLocation() != currentPart) {
              alreadyDeployedElsewhere = true;
              break;
            }
          }
          if (!alreadyDeployedElsewhere) {
            availableElements.add(part);
          }
        }
      }
    }
  }
}
