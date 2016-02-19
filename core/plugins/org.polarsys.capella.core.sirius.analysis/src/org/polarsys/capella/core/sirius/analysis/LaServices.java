/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.helpers.la.services.CapabilityRealizationPkgExt;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;

public class LaServices {

  private static LaServices instance = new LaServices();

  public static LaServices getInstance() {
    if(instance == null){
      instance = new LaServices();
    }
    return instance;
  }

  public List<EObject> getCRBCapabilityRealizations(DSemanticDecorator diagram) {
    List<EObject> result = new ArrayList<EObject>();
    EObject target = diagram.getTarget();
    if (diagram instanceof DDiagram && target instanceof CapabilityRealizationPkg) {
      BlockArchitecture rootBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(target);
      if (rootBlockArchitecture instanceof LogicalArchitecture) {
        result.addAll(CapabilityRealizationPkgExt
            .getAllCapabilityRealization(((LogicalArchitecture) rootBlockArchitecture)
                .getContainedCapabilityRealizationPkg()));
      }
    }
    return result;
  }

  public List<EObject> getAvailableLogicalActorsToInsertInCRB(DSemanticDecorator diagram) {
    List<EObject> result = new ArrayList<EObject>();
    EObject target = diagram.getTarget();
    if (diagram instanceof DDiagram && target instanceof CapabilityRealizationPkg) {
      Structure structure = BlockArchitectureExt.getActorPkg(BlockArchitectureExt.getRootBlockArchitecture(target));
      if(structure instanceof LogicalActorPkg){
        result.addAll(ActorPkgExt.getAllActors((LogicalActorPkg)structure));
      }
    }
    return result;
  
  }

  public List<EObject> getCRBComponents(DSemanticDecorator decorator) {

    List<EObject> components = new ArrayList<EObject>();

    if ((decorator.getTarget() instanceof Component)) {
      components.addAll(CsServices.getService().getCCIIShowHideComponent(decorator));
      return components;
    }

    EObject parentContainer = CsServices.getService().getParentContainer(decorator.getTarget());
    if (null == parentContainer) {
      return components;
    }

    if (parentContainer instanceof Component) {
      components.addAll(CsServices.getService().getSubComponents(parentContainer));
      return components;
    } else if (parentContainer instanceof BlockArchitecture) {
      Component firstComponent = BlockArchitectureExt.getFirstComponent((ModellingArchitecture) parentContainer);
      if (null != firstComponent) {
        components.addAll(CsServices.getService().getSubComponents(firstComponent));
        return components;
      }
    }
    return components;
  }
}
