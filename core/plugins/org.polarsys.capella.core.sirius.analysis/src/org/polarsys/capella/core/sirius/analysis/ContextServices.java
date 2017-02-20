/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.helpers.ctx.services.CapabilityPkgExt;
import org.polarsys.capella.core.data.helpers.ctx.services.MissionPkgExt;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.OperationalActor;
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

  public List<EObject> getCCCapabilities(DSemanticDecorator current) {
    return getMBCapabilities(current);
  }

  public List<EObject> getCMCapabilities(DSemanticDecorator current) {
    return getMBCapabilities(current);
  }

  public List<EObject> getCRBCapabilities(DSemanticDecorator diagram) {
    return getMBCapabilities(diagram);
  }

  public List<EObject> getMBCapabilities(DSemanticDecorator current) {
    List<EObject> result = new ArrayList<EObject>();
    EObject target = current.getTarget();
    AbstractCapabilityPkg abstractCapabilityPkg = BlockArchitectureExt
        .getAbstractCapabilityPkg(BlockArchitectureExt.getRootBlockArchitecture(target));
    result.addAll(CapabilityPkgExt.getAllAbstractCapabilities(abstractCapabilityPkg));
    return result;
  }

  public List<EObject> getOCBCapabilities(DSemanticDecorator diagram) {
    return getMBCapabilities(diagram);
  }

  public List<EObject> getMCBCapabilities(DSemanticDecorator current) {
    return getMBCapabilities(current);
  }

  /**
   * @deprecated Use getMCBActors(current) instead
   */
  @Deprecated
  public List<EObject> getAvailableActorsToInsertInMCB2(DSemanticDecorator current) {
    return getMCBActors(current);
  }

  /**
   * @deprecated Use getMCBActors(current) instead
   */
  @Deprecated
  public List<Actor> getAvailableActorsToInsertInMCB(DSemanticDecorator current) {
    return (List) getMCBActors(current);
  }

  /**
   * @deprecated Will be removed
   */
  @Deprecated
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

  /**
   * @deprecated Will be removed
   */
  @Deprecated
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

  public List<EObject> getCCMissions(DSemanticDecorator current) {
    return getMCBMissions(current);
  }

  public List<EObject> getMCBMissions(DSemanticDecorator current) {
    List<EObject> result = new ArrayList<EObject>();
    EObject target = current.getTarget();
    SystemEngineering sysEng = SystemEngineeringExt.getSystemEngineering((CapellaElement) target);
    SystemAnalysis sysAnalysis = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
    result.addAll(MissionPkgExt.getAllMissions(sysAnalysis.getOwnedMissionPkg()));
    return result;
  }

  public List<EObject> getMBMissions(DSemanticDecorator diagram) {
    List<EObject> result = new ArrayList<EObject>();
    EObject target = diagram.getTarget();
    if ((diagram instanceof DDiagram) && (target instanceof MissionPkg)) {
      result.addAll(MissionPkgExt.getAllMissions((MissionPkg) target));
    }
    return result;
  }

  public List<EObject> getCMActors(DSemanticDecorator diagram) {
    return getMCBActors(diagram);
  }

  public List<EObject> getCCActors(DSemanticDecorator diagram) {
    return getMCBActors(diagram);
  }

  public List<EObject> getMCBActors(DSemanticDecorator current) {
    List<EObject> result = new ArrayList<EObject>();
    EObject target = current.getTarget();
    Structure structure = BlockArchitectureExt.getActorPkg(BlockArchitectureExt.getRootBlockArchitecture(target));
    result.addAll(ActorPkgExt.getAllActors(structure));
    return result;
  }

  public List<EObject> getCRBActors(DSemanticDecorator diagram) {
    return getMCBActors(diagram);
  }

  public List<EObject> getMBActors(DSemanticDecorator diagram) {
    return getMCBActors(diagram);
  }

  public List<EObject> getCOCActors(DSemanticDecorator view) {
    List<EObject> result = new ArrayList<EObject>();
    Collection<? extends Component> availableEntitiesToInsert = OAServices.getService().getOEBEntities(view);
    for (Component component : availableEntitiesToInsert) {
      if (component instanceof OperationalActor) {
        result.add(component);
      }
    }
    return result;
  }

  public List<EObject> getCOCEntities(DSemanticDecorator view) {
    List<EObject> result = new ArrayList<EObject>();
    Collection<? extends Component> allComponents = OAServices.getService().getAvailableEntitiesToInsert(view);
    for (Component component : allComponents) {
      if ((component instanceof Entity) && !(component instanceof OperationalActor)) {
        result.add(component);
      }
    }
    return result;
  }

  public List<EObject> getCOCCapabilities(DSemanticDecorator diagram) {
    return getOCBCapabilities(diagram);
  }

  public List<EObject> getOCBActors(DSemanticDecorator view) {
    return getCOCActors(view);
  }

  public List<EObject> getOCBEntities(DSemanticDecorator view) {
    return getCOCEntities(view);
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

  public Collection<EObject> getMBCapabilityInvolvementSemanticCandidates(DDiagram diagram) {
    Collection<EObject> result = new ArrayList<EObject>();

    for (DDiagramElement dNode : DiagramServices.getDiagramServices().getAllAbstractNodes(diagram, false)) {
      EObject target = dNode.getTarget();
      if (target instanceof AbstractCapability) {
        result.addAll(((AbstractCapability) target).getInvolvedInvolvements());
      }
    }

    return result;
  }

  public Collection<EObject> getMBMissionInvolvementSemanticCandidates(DDiagram diagram) {
    Collection<EObject> result = new ArrayList<EObject>();

    for (DDiagramElement dNode : DiagramServices.getDiagramServices().getAllAbstractNodes(diagram, false)) {
      EObject target = dNode.getTarget();
      if (target instanceof Mission) {
        result.addAll(((Mission) target).getInvolvedActors());
      }
    }

    return result;
  }

  public Collection<EObject> getMBCapabilityIncludeSemanticCandidates(DDiagram diagram) {
    Collection<EObject> result = new ArrayList<EObject>();

    for (DDiagramElement dNode : DiagramServices.getDiagramServices().getAllAbstractNodes(diagram, false)) {
      EObject target = dNode.getTarget();
      if (target instanceof AbstractCapability) {
        result.addAll(((AbstractCapability) target).getIncludes());
      }
    }

    return result;
  }

  public Collection<EObject> getMBCapabilityExtendSemanticCandidates(DDiagram diagram) {
    Collection<EObject> result = new ArrayList<EObject>();

    for (DDiagramElement dNode : DiagramServices.getDiagramServices().getAllAbstractNodes(diagram, false)) {
      EObject target = dNode.getTarget();
      if (target instanceof AbstractCapability) {
        result.addAll(((AbstractCapability) target).getExtends());
      }
    }

    return result;
  }

  public Collection<EObject> getMBActorGeneralizationSemanticCandidates(DDiagram diagram) {
    return InformationServices.getService().getCDBGeneralizationSemanticCandidates(diagram);
  }

  public Collection<EObject> getMBCapabilityGeneralizationSemanticCandidates(DDiagram diagram) {
    Collection<EObject> result = new ArrayList<EObject>();
    for (DDiagramElement dNode : DiagramServices.getDiagramServices().getAllAbstractNodes(diagram, false)) {
      EObject target = dNode.getTarget();
      if (target instanceof AbstractCapability) {
        result.addAll(((AbstractCapability) target).getSuperGeneralizations());
      }
    }
    return result;
  }

  public Collection<EObject> getMBCapabilityExploitationSemanticCandidates(DDiagram diagram) {
    Collection<EObject> result = new ArrayList<EObject>();
    for (DDiagramElement dNode : DiagramServices.getDiagramServices().getAllAbstractNodes(diagram, false)) {
      EObject target = dNode.getTarget();
      if (target instanceof Mission) {
        result.addAll(((Mission) target).getOwnedCapabilityExploitations());
      }
    }
    return result;
  }

}
