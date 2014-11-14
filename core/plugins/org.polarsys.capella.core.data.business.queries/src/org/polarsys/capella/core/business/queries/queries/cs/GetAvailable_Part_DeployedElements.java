/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.util.EList;

import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;

public class GetAvailable_Part_DeployedElements extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    Part currentPart = (Part) input;
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    Collection<Part> parts = ComponentExt.getPartAncestors(currentPart);
    AbstractType abstractType = currentPart.getAbstractType();
    if ((null != abstractType) && ((abstractType instanceof PhysicalComponent) || (abstractType instanceof PhysicalActor))) {
      List<PhysicalComponent> behaviourComps = SystemEngineeringExt.getAllPhysicalComponents((CapellaElement) abstractType);
      if (abstractType instanceof PhysicalComponent) {
        for (PhysicalComponent physicalComponent : behaviourComps) {
          PhysicalComponent currentPC = (PhysicalComponent) abstractType;
          if (!(currentPC.getNature().equals(PhysicalComponentNature.BEHAVIOR) && physicalComponent.getNature().equals(PhysicalComponentNature.NODE))
              && !(currentPC.getNature().equals(PhysicalComponentNature.UNSET)) && !(physicalComponent.getNature().equals(PhysicalComponentNature.UNSET))
              && !physicalComponent.equals(currentPC)) {
            getValidDeployablePart(availableElements, parts, physicalComponent);
          }
        }
      } else if (abstractType instanceof PhysicalActor) {
        for (PhysicalComponent physicalComponent : behaviourComps) {
          if (!(physicalComponent.getNature().equals(PhysicalComponentNature.NODE)) && !(physicalComponent.getNature().equals(PhysicalComponentNature.UNSET))) {
            getValidDeployablePart(availableElements, parts, physicalComponent);
          }
        }
      }
    }
    availableElements.removeAll(QueryInterpretor.executeQuery("GetCurrent_Part_DeployedElements", currentPart, context));//$NON-NLS-1$
    return (List) availableElements;
  }

  private void getValidDeployablePart(List<CapellaElement> availableElements, Collection<Part> parts, Component physicalComponent) {
    EList<Partition> representingPartitions = physicalComponent.getRepresentingPartitions();
    for (Partition partition : representingPartitions) {
      if (partition instanceof Part) {
        Part part = (Part) partition;
        if (!parts.contains(part)) {
          EList<AbstractDeploymentLink> deployingLinks = part.getDeployingLinks();
          if (CapellaModelPreferencesPlugin.getDefault().isMultipleDeploymentAllowed() || deployingLinks.isEmpty()) {
            availableElements.add(part);
          }
        }
      }
    }
  }

}
