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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.MissionPkg;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.helpers.ctx.services.CapabilityPkgExt;
import org.polarsys.capella.core.data.helpers.ctx.services.MissionPkgExt;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ComponentPkgExt;
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

  public List<EObject> getCRIActors(DSemanticDecorator diagram) {
    return getMCBActors(diagram);
  }

  public List<EObject> getCRICapabilityRealizations(DSemanticDecorator diagram) {
    return getMBCapabilities(diagram);
  }

  public List<EObject> getCRIComponents(DSemanticDecorator diagram) {

    EObject target = diagram.getTarget();
    Component rootComponent = BlockArchitectureExt.getRootBlockArchitecture(target).getSystem();
    List<EObject> result = new ArrayList<>();

    if (rootComponent != null) {
      Collection<Component> subComponents = ComponentExt.getAllSubUsedComponents(rootComponent);
      result.addAll(subComponents);
    }

    return result;
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
    EObject target = current.getTarget();
    ComponentPkg componentPkg = BlockArchitectureExt
        .getComponentPkg(BlockArchitectureExt.getRootBlockArchitecture(target), false);

    return ComponentPkgExt.getAllActors(componentPkg).stream().filter(c -> !c.getRepresentingParts().isEmpty())
        .collect(Collectors.toList());
  }

  public List<EObject> getMCBComponents(DSemanticDecorator current) {
    List<EObject> result = new ArrayList<EObject>();
    EObject target = current.getTarget();
    ComponentPkg componentPkg = BlockArchitectureExt
        .getComponentPkg(BlockArchitectureExt.getRootBlockArchitecture(target), false);
    result.addAll(ComponentPkgExt.getSubDefinedComponents(componentPkg).stream().filter(c -> !ComponentExt.isActor(c))
        .collect(Collectors.toList()));
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
      if (ComponentExt.isActor(component)) {
        result.add(component);
      }
    }
    return result;
  }

  public List<EObject> getCOCEntities(DSemanticDecorator view) {
    List<EObject> result = new ArrayList<EObject>();
    Collection<? extends Component> allComponents = OAServices.getService().getAvailableEntitiesToInsert(view);
    for (Component component : allComponents) {
      if ((component instanceof Entity) && !(ComponentExt.isActor(component))) {
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

  public Collection<Component> getCRBComponents(DSemanticDecorator decorator) {
    Collection<Component> components = Collections.emptyList();

    if (decorator.getTarget() instanceof Component) {
      components = CsServices.getService().getCCIIShowHideComponent(decorator);

    } else {
      EObject parentContainer = CsServices.getService().getParentContainer(decorator.getTarget());

      if (parentContainer instanceof Component) {
        components = CsServices.getService().getSubComponents(parentContainer);

      } else if (parentContainer instanceof BlockArchitecture) {
        Component firstComponent = ((BlockArchitecture) parentContainer).getSystem();

        if (null != firstComponent) {
          components = CsServices.getService().getSubComponents(firstComponent);
        }
      }
    }

    return components.stream().filter(c -> !c.getRepresentingParts().isEmpty()).collect(Collectors.toList());
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
        result.addAll(((Mission) target).getInvolvedInvolvements());
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

  public Collection<EObject> getMBGeneralizationSemanticCandidates(DDiagram diagram) {
    Collection<EObject> compGeneralizations = getMBActorGeneralizationSemanticCandidates(diagram);
    compGeneralizations.addAll(getMBCapabilityGeneralizationSemanticCandidates(diagram));
    return compGeneralizations;
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

  public List<AbstractDNode> getDisplayedNodeViews(DSemanticDecorator view,
      Function<AbstractDNode, Boolean> filterFunc) {
    List<AbstractDNode> returnedList = new ArrayList<AbstractDNode>();
    if (view instanceof DDiagram) {
      DDiagram diagram = (DDiagram) view;
      for (AbstractDNode node : diagram.getNodes()) {
        if (filterFunc.apply(node)) {
          returnedList.add(node);
        }
      }
    }
    if (view instanceof DNodeContainer) {
      DNodeContainer currentContainer = (DNodeContainer) view;
      for (AbstractDNode node : currentContainer.getNodes()) {
        if (filterFunc.apply(node)) {
          returnedList.add(node);
        }
      }
    }
    return returnedList;
  }

  public List<AbstractDNode> getDisplayedContainerViews(DSemanticDecorator view,
      Function<AbstractDNode, Boolean> filterFunc) {
    List<AbstractDNode> returnedList = new ArrayList<AbstractDNode>();
    if (view instanceof DDiagram) {
      DDiagram diagram = (DDiagram) view;
      for (AbstractDNode node : diagram.getContainers()) {
        if (filterFunc.apply(node)) {
          returnedList.add(node);
        }
      }
    }
    if (view instanceof DNodeContainer) {
      DNodeContainer currentContainer = (DNodeContainer) view;
      for (AbstractDNode node : currentContainer.getContainers()) {
        if (filterFunc.apply(node)) {
          returnedList.add(node);
        }
      }
    }
    return returnedList;
  }

  public List<AbstractDNode> getDisplayedActorNodeViews(DSemanticDecorator view) {
    return getDisplayedNodeViews(view, new Function<AbstractDNode, Boolean>() {
      @Override
      public Boolean apply(AbstractDNode node) {
        return (node.getTarget() != null) && (node.getTarget() instanceof Component)
            && ComponentExt.isActor(node.getTarget());
      }
    });
  }

  public List<AbstractDNode> getDisplayedActorContainerViews(DSemanticDecorator view) {
    return getDisplayedContainerViews(view, new Function<AbstractDNode, Boolean>() {
      @Override
      public Boolean apply(AbstractDNode node) {
        return node.getTarget() instanceof Component && ComponentExt.isActor(node.getTarget());
      }
    });
  }

  public List<EObject> getDisplayedNodeActors(DSemanticDecorator view) {
    return getDisplayedActorNodeViews(view).stream().map(v -> v.getTarget()).collect(Collectors.toList());
  }

  public List<EObject> getDisplayedContainerActors(DSemanticDecorator view) {
    return getDisplayedActorContainerViews(view).stream().map(v -> v.getTarget()).collect(Collectors.toList());
  }

  public List<AbstractDNode> getDisplayedComponentNodeViews(DSemanticDecorator view) {
    return getDisplayedNodeViews(view, new Function<AbstractDNode, Boolean>() {
      @Override
      public Boolean apply(AbstractDNode node) {
        return (node.getTarget() != null) && (node.getTarget() instanceof Component)
            && !ComponentExt.isActor(node.getTarget());
      }
    });
  }

  public List<AbstractDNode> getDisplayedComponentContainerViews(DSemanticDecorator view) {
    return getDisplayedContainerViews(view, new Function<AbstractDNode, Boolean>() {
      @Override
      public Boolean apply(AbstractDNode node) {
        return (node.getTarget() != null) && (node.getTarget() instanceof Component)
            && !ComponentExt.isActor(node.getTarget());
      }
    });
  }

  public List<EObject> getDisplayedNodeComponents(DSemanticDecorator view) {
    return getDisplayedComponentNodeViews(view).stream().map(v -> v.getTarget()).collect(Collectors.toList());
  }

  public List<EObject> getDisplayedContainerComponents(DSemanticDecorator view) {
    return getDisplayedComponentContainerViews(view).stream().map(v -> v.getTarget()).collect(Collectors.toList());
  }
}
