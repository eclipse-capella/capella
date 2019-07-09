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
package org.polarsys.capella.core.business.queries.queries.interaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.ExtendingQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.queries.QueryExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

@ExtendingQuery (extendingQuery = GetAvailable_InstanceRole_RepresentedInstance.class)
public class GetAvailable_InstanceRole_RepresentedInstance__Lib extends AbstractQuery {

  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (capellaElement instanceof InstanceRole) {
      InstanceRole ir = (InstanceRole) capellaElement;
      Scenario scenario = (Scenario) capellaElement.eContainer();
      SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(capellaElement);
      if (sysEng != null) {
        IModel currentProject =  ILibraryManager.INSTANCE.getModel(capellaElement);
        Collection<IModel> libraries = LibraryManagerExt.getAllActivesReferences(currentProject);
        for (IModel library : libraries) {
          BlockArchitecture block = (BlockArchitecture) QueryExt.getCorrespondingElementInLibrary(capellaElement, (CapellaModel) library);
          Component rootComponent = BlockArchitectureExt.getFirstComponent(block, false);
          if (rootComponent != null) {
            getAllOwnedPart(availableElements, rootComponent, Collections.emptyList());
          }
          if ((scenario.getKind() == ScenarioKind.DATA_FLOW) || (scenario.getKind() == ScenarioKind.INTERFACE)) {
            // in the case of an epbs architecture, we must use the physical actors
            if (block instanceof EPBSArchitecture) {
              SystemEngineering se = (SystemEngineering) block.eContainer();
              block = SystemEngineeringExt.getPhysicalArchitecture(se);
            }
            final Iterator<EObject> iterContents = block.eAllContents();
            while (iterContents.hasNext()) {
              final EObject next = iterContents.next();
              if (next instanceof Component) {
                Component actor = (Component) next;
                for (AbstractTypedElement ate : actor.getAbstractTypedElements()) {
                  if (ate instanceof Part) {
                    availableElements.add((Part) ate);
                  }
                }
              }
            }
          } else if (scenario.getKind() == ScenarioKind.INTERACTION) {
            if (!(ir.getRepresentedInstance() instanceof OperationalActivity)) {
              final Iterator<EObject> iterContents = block.eAllContents();
              while (iterContents.hasNext()) {
                final EObject next = iterContents.next();
                if (CsPackage.eINSTANCE.getPart().isInstance(next)) {
                  final Part currentPart = (Part) next;
                  if (CsPackage.eINSTANCE.getComponent().isInstance(currentPart.getAbstractType())
                      && (!currentPart.getAbstractType().equals(rootComponent))) {
                    availableElements.add(currentPart);
                  }
                }
              }
            }
          }
        }
      }
    }
    return (List) availableElements;
  }

  private static void getAllOwnedPart(Collection<CapellaElement> result, Component rootComponent, Collection<Part> filter) {
    EList<Part> ownedPartitions = rootComponent.getContainedParts();
    for (Part partition : ownedPartitions) {
      if (partition instanceof Part) {
        if (!filter.contains(partition)) {
          result.add(partition);
          if ((partition.getAbstractType() != null) && (partition.getAbstractType() instanceof Component)) {
            getAllOwnedPart(result, (Component) partition.getAbstractType(), filter);
          }
        }
      }
    }
  }

}
