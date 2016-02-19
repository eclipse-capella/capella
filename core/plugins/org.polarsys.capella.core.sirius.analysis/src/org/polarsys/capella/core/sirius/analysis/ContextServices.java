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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.helpers.ctx.services.CapabilityPkgExt;
import org.polarsys.capella.core.data.helpers.ctx.services.MissionPkgExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.sirius.analysis.showhide.AbstractShowHide;
import org.polarsys.capella.core.sirius.analysis.showhide.AbstractShowHide.DiagramContext;

public class ContextServices {
  private static ContextServices singleton = null;

  public static ContextServices getServices() {
    if (singleton == null) {
      singleton = new ContextServices();
    }
    return singleton;
  }

  /**
   * @param current
   * @return
   */
  public List<EObject> getMCBCapabilities(DSemanticDecorator current) {
    List<EObject> result = new ArrayList<EObject>();

    EObject target = current.getTarget();
    if (current instanceof DDiagram) {
      if (target instanceof CapabilityPkg) {
        result.addAll(CapabilityPkgExt.getAllCapabilities((CapabilityPkg) target));
      }
    }

    return result;
  }

  /**
   * @param current
   * @return
   */
  public List<EObject> getMCBMissions(DSemanticDecorator current) {
    List<EObject> result = new ArrayList<EObject>();

    EObject target = current.getTarget();
    if (current instanceof DDiagram) {
      if (target instanceof CapabilityPkg) {
        SystemEngineering sysEng = SystemEngineeringExt.getSystemEngineering((CapellaElement) target);
        SystemAnalysis sysAnalysis = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);

        result.addAll(MissionPkgExt.getAllMissions(sysAnalysis.getOwnedMissionPkg()));
      }
    }

    return result;
  }

  public List<EObject> getAvailableActorsToInsertInMCB2(DSemanticDecorator current) {

    List<EObject> actors = new ArrayList<EObject>();

    EObject target = current.getTarget();
    if (current instanceof DDiagram) {
      if (target instanceof CapabilityPkg) {
        actors.addAll(SystemEngineeringExt.getAllActors((CapellaElement) target));
      }
    }
    if ((null != target) && (current instanceof DNodeContainer) && (target instanceof Actor)) {
      actors.addAll(SystemEngineeringExt.getAllActors((CapellaElement) target));
    }

    return actors;
  }

  public List<Actor> getAvailableActorsToInsertInMCB(DSemanticDecorator current) {

    List<Actor> actors = new ArrayList<Actor>();

    EObject target = current.getTarget();
    if (current instanceof DDiagram) {
      if (target instanceof CapabilityPkg) {
        actors.addAll(SystemEngineeringExt.getAllActors((CapellaElement) target));
      }
    }
    if ((null != target) && (current instanceof DNodeContainer) && (target instanceof Actor)) {
      actors.addAll(SystemEngineeringExt.getAllActors((CapellaElement) target));
    }

    return actors;
  }

  /**
   * @param view
   * @param selectedElements
   * @param visibleElements
   * @param visibleElementViews
   * @param showHideHandler
   */
  public EObject showHideMCBActors2(DSemanticDecorator view, List<EObject> selectedElements,
      List<EObject> visibleElements, List<DDiagramElement> visibleElementViews, AbstractShowHide showHideHandler) {

    DiagramContext diagramContext = showHideHandler.new DiagramContext();

    // show
    for (EObject actor : selectedElements) {
      showHideHandler.show(actor, diagramContext);
    }

    // hide
    Set<EObject> toBeRemoved = new HashSet<EObject>();
    for (DDiagramElement node : visibleElementViews) {
      if (!selectedElements.contains(node.getTarget())) {
        toBeRemoved.add(node.getTarget());
      }
    }
    for (EObject aView : toBeRemoved) {
      showHideHandler.hide(aView, diagramContext);
    }

    return view;
  }

  public Actor createCtxActor(EObject container) {
    Actor actor = CtxFactory.eINSTANCE.createActor();
    if (actor != null) {
      if (container instanceof ActorPkg) {
        ActorPkg actorPkg = (ActorPkg) container;
        actorPkg.getOwnedActors().add(actor);
      }
      CapellaServices.getService().creationService(actor);
    }
    return actor;
  }

  public List<EObject> getMBCapabilities(DSemanticDecorator diagram) {
    List<EObject> result = new ArrayList<EObject>();
    EObject target = diagram.getTarget();
    if (diagram instanceof DDiagram && target instanceof MissionPkg) {
      AbstractCapabilityPkg abstractCapabilityPkg = BlockArchitectureExt.getAbstractCapabilityPkg(BlockArchitectureExt.getRootBlockArchitecture(target));
      result.addAll(CapabilityPkgExt.getAllAbstractCapabilities(abstractCapabilityPkg));
    }
    return result;
  }

  public List<EObject> getAvailableActorsToInsertInMB(DSemanticDecorator diagram) {
    List<EObject> result = new ArrayList<EObject>();
    EObject target = diagram.getTarget();
    if (diagram instanceof DDiagram && target instanceof MissionPkg) {
      Structure structure = BlockArchitectureExt.getActorPkg(BlockArchitectureExt.getRootBlockArchitecture(target));
      if(structure instanceof ActorPkg){
        result.addAll(ActorPkgExt.getAllActors((ActorPkg)structure));
      }
    }
    return result;
  }

  public List<EObject> getMBMissions(DSemanticDecorator diagram) {
    List<EObject> result = new ArrayList<EObject>();
    EObject target = diagram.getTarget();
    if (diagram instanceof DDiagram && target instanceof MissionPkg) {
      result.addAll(MissionPkgExt.getAllMissions((MissionPkg)target));      
    }
    return result;
  }

  public List<EObject> getCCCapabilities(DSemanticDecorator diagram) {
    List<EObject> result = new ArrayList<EObject>();
    EObject target = diagram.getTarget();
    if (diagram instanceof DDiagram && target instanceof Capability) {
      AbstractCapabilityPkg abstractCapabilityPkg = BlockArchitectureExt.getAbstractCapabilityPkg(BlockArchitectureExt.getRootBlockArchitecture(target));
      result.addAll(CapabilityPkgExt.getAllAbstractCapabilities(abstractCapabilityPkg));
    }
    return result;
  }

  public List<EObject> getAvailableActorsToInsertInCC(DSemanticDecorator diagram) {
    List<EObject> result = new ArrayList<EObject>();
    EObject target = diagram.getTarget();
    if (diagram instanceof DDiagram && target instanceof Capability) {
      Structure structure = BlockArchitectureExt.getActorPkg(BlockArchitectureExt.getRootBlockArchitecture(target));
      if(structure instanceof ActorPkg){
        result.addAll(ActorPkgExt.getAllActors((ActorPkg)structure));
      }
    }
    return result;
  }

  public List<EObject> getCCMissions(DSemanticDecorator diagram) {
    List<EObject> result = new ArrayList<EObject>();
    EObject target = diagram.getTarget();
    if (diagram instanceof DDiagram && target instanceof Capability) {
      SystemEngineering sysEng = SystemEngineeringExt.getSystemEngineering((CapellaElement) target);
      SystemAnalysis sysAnalysis = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
      result.addAll(MissionPkgExt.getAllMissions(sysAnalysis.getOwnedMissionPkg()));
    }
    return result;
  }

  public List<EObject> getCMCapabilities(DSemanticDecorator diagram) {
    List<EObject> result = new ArrayList<EObject>();
    EObject target = diagram.getTarget();
    if (diagram instanceof DDiagram && target instanceof Mission) {
      AbstractCapabilityPkg abstractCapabilityPkg = BlockArchitectureExt.getAbstractCapabilityPkg(BlockArchitectureExt.getRootBlockArchitecture(target));
      result.addAll(CapabilityPkgExt.getAllAbstractCapabilities(abstractCapabilityPkg));
    }
    return result;
  }

  public List<EObject> getAvailableActorsToInsertInCM(DSemanticDecorator diagram) {
    List<EObject> result = new ArrayList<EObject>();
    EObject target = diagram.getTarget();
    if (diagram instanceof DDiagram && target instanceof Mission) {
      Structure structure = BlockArchitectureExt.getActorPkg(BlockArchitectureExt.getRootBlockArchitecture(target));
      if(structure instanceof ActorPkg){
        result.addAll(ActorPkgExt.getAllActors((ActorPkg)structure));
      }
    }
    return result;
  }
}
