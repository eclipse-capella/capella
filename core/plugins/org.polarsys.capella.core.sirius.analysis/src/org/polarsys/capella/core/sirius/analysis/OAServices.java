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

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.common.data.activity.ActivityEdge;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.oa.ActivityAllocation;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.data.oa.OperationalCapabilityPkg;
import org.polarsys.capella.core.data.oa.Role;
import org.polarsys.capella.core.data.oa.RoleAllocation;
import org.polarsys.capella.core.data.oa.RolePkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.FunctionalExchangeExt;
import org.polarsys.capella.core.model.helpers.OperationalAnalysisExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.sequencediag.ScenarioService;

/**
 */
public class OAServices {

  /** A shared instance. */
  private static OAServices _service;

  /**
   * returns a shared instance of this services.
   * 
   * @return a shared instance of this services.
   */
  public static OAServices getService() {
    if (_service == null) {
      _service = new OAServices();
    }
    return _service;
  }

  public List<EObject> getOEBScopeBreakdown(EObject eObject) {
    List<Entity> roots = new ArrayList<Entity>();
    List<EObject> result = new ArrayList<EObject>();

    if (eObject instanceof Entity) {
      roots.add((Entity) eObject);
    } else if (eObject instanceof EntityPkg) {
      roots.addAll(((EntityPkg) eObject).getOwnedEntities());
    }

    for (Entity entity : roots) {
      if ((entity.getRepresentingParts().size() > 0) && !result.contains(entity)) {
        result.add(entity);
      }
      for (Component pe : CapellaServices.getService().getAllDescendants(entity)) {
        if (!result.contains(pe)) {
          result.add(pe);
        }
      }
    }

    return result;
  }

  /**
   * Gets the iB target.
   */
  public EObject getOEBTarget(DSemanticDecorator decorator) {
    if (decorator instanceof DDiagram) {
      for (DDiagramElement element : ((DDiagram) decorator).getOwnedDiagramElements()) {
        if (element.getTarget() == decorator.getTarget()) {
          EObject target = CsServices.getService().getParentContainer(decorator.getTarget());
          if (target instanceof BlockArchitecture) {
            return BlockArchitectureExt.getContext((BlockArchitecture) target);
          }
          return target;
        }
      }
      return decorator.getTarget();
    }
    return decorator.getTarget();
  }

  /**
   * Retrieve the root role pkg, create one if required
   */
  public RolePkg getRootRolePkg(EObject object) {
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(object);
    if (architecture instanceof OperationalAnalysis) {
      OperationalAnalysis analysis = (OperationalAnalysis) architecture;
      if (analysis.getOwnedRolePkg() == null) {
        RolePkg pkg = OaFactory.eINSTANCE.createRolePkg("Roles"); //$NON-NLS-1$
        analysis.setOwnedRolePkg(pkg);
      }
      return analysis.getOwnedRolePkg();
    }
    return null;
  }

  /**
   * Retrieve the root entity pkg, create one if required
   */
  public EntityPkg getRootEntityPkg(EObject object) {
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(object);
    if (architecture instanceof OperationalAnalysis) {
      OperationalAnalysis analysis = (OperationalAnalysis) architecture;
      if (analysis.getOwnedEntityPkg() == null) {
        EntityPkg pkg = OaFactory.eINSTANCE.createEntityPkg("Operational Entities"); //$NON-NLS-1$
        analysis.setOwnedEntityPkg(pkg);
      }
      return analysis.getOwnedEntityPkg();
    }
    return null;
  }

  /**
   * get all the outgoing and incoming exchanges of Operational Function used in oa.odesign
   * 
   * @param eObject
   * @return
   */
  public List<FunctionalExchange> getAllAvailableExchanges(EObject eObject) {
    List<FunctionalExchange> list = new ArrayList<FunctionalExchange>();

    // null value check
    if (eObject == null) {
      return list;
    }

    if (eObject instanceof OperationalActivity) {
      OperationalActivity oa = (OperationalActivity) eObject;

      // exchanges of current operational Activity
      list.addAll(getOutgoingAndIncomingExchanges(oa));

      // get all children
      List<AbstractFunction> allAbstractFunctions = getCache(FunctionExt::getAllAbstractFunctions, oa);
      for (AbstractFunction abstractFunction : allAbstractFunctions) {
        list.addAll(getOutgoingAndIncomingExchanges(abstractFunction));
      }
    }

    return list;
  }

  /**
   * get all the outGoing and inComing exchanges
   * 
   * @param function
   * @return list of FunctionalExchanges
   */
  private List<FunctionalExchange> getOutgoingAndIncomingExchanges(AbstractFunction function) {
    List<FunctionalExchange> list = new ArrayList<FunctionalExchange>();

    // incoming
    EList<ActivityEdge> incoming = function.getIncoming();
    for (ActivityEdge activityEdge : incoming) {
      // filter functional exchanges
      if (activityEdge instanceof FunctionalExchange) {
        list.add((FunctionalExchange) activityEdge);
      }
    }
    // outgoing
    EList<ActivityEdge> outgoing = function.getOutgoing();
    for (ActivityEdge activityEdge2 : outgoing) {
      // filter functional exchanges
      if (activityEdge2 instanceof FunctionalExchange) {
        list.add((FunctionalExchange) activityEdge2);
      }
    }

    return list;
  }

  /**
   * check if role is allocated in entity used in oa.odesign
   * 
   * @param context
   * @param current
   *          - Role
   * @param container
   *          - Entity
   * @return return true if given role is allocated in given entity
   */
  public boolean isAllocatedRole(EObject context, EObject role, Entity container) {

    if ((role == null) || (container == null) || !(role instanceof Role)) {
      return false;
    }
    // get owned role allocations
    EList<RoleAllocation> ownedRoleAllocations = container.getOwnedRoleAllocations();
    for (RoleAllocation roleAllocation : ownedRoleAllocations) {
      if (roleAllocation.getRole().equals(role)) {
        return true;
      }
    }
    return false;
  }

  public boolean isInOperationalAnalysis(EObject context) {
    return (context instanceof CapellaElement)
        && CapellaLayerCheckingExt.isInOperationalAnalysisLayer((CapellaElement) context);
  }

  public Collection<EObject> getOESScopeInsertEntitiesRoles(Scenario scenario) {
    Collection<EObject> roots = new ArrayList<EObject>();
    roots.addAll(new ScenarioService().getAllMultiInstanceRoleParts(scenario));
    BlockArchitecture analysis = BlockArchitectureExt.getRootBlockArchitecture(scenario);
    if (analysis instanceof OperationalAnalysis) {
      roots.addAll(OperationalAnalysisExt.getAllRoles((OperationalAnalysis) analysis));
    }
    return roots;
  }

  /**
   * check if function is allocated in role used in oa.odesign
   * 
   * @param context
   * @param current
   *          - OperationialActivity
   * @param container
   *          - Role
   * @return return true if given function is allocated in given role
   */
  public boolean isAllocatedFunctionInRole(EObject context, OperationalActivity current, Role container) {
    // null value check
    if ((current == null) || (container == null)) {
      return false;
    }
    // return false if current function is not a leaf
    if (!FunctionExt.isLeaf(current)) {
      return false;
    }
    // get Owned Activity Allocations
    EList<ActivityAllocation> ownedActivityAllocations = container.getOwnedActivityAllocations();
    for (ActivityAllocation activityAllocation : ownedActivityAllocations) {
      if (activityAllocation.getActivity().equals(current)) {
        return true;
      }
    }
    return false;
  }

  /**
   * used in oa.odesign
   * 
   * @param view
   * @return available entities to insert in OEB
   */
  @Deprecated
  public Collection<? extends Component> getAvailableEntitiesToInsert(DSemanticDecorator view) {
    return getOEBEntities(view);
  }

  public Collection<? extends Component> getOEBEntities(DSemanticDecorator view) {
    EObject target = view.getTarget();

    Collection<Component> entities = Collections.emptyList();

    if ((target instanceof Entity) || (target instanceof EntityPkg)
        || (target instanceof Entity && ((Component) target).isActor())) {
      target = getOEBTarget(view);
      if (target instanceof EntityPkg) {
        target = getRootEntityPkg(BlockArchitectureExt.getRootBlockArchitecture(target));
      } else if (target instanceof Entity && ((Component) target).isActor()) {
        // eContainer of operational Actor is either Entity or EntityPkg
        target = target.eContainer();
      }

      if (target instanceof Entity) {
        entities = ComponentExt.getAllSubUsedComponents((Entity) target);

      } else if (target instanceof EntityPkg) {
        entities = new ArrayList<>(OperationalAnalysisExt.getAllEntity((EntityPkg) target));
      }

    } else if ((target instanceof OperationalCapabilityPkg) || (target instanceof OperationalCapability)) {
      BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(target);
      if (arch instanceof OperationalAnalysis) {
        OperationalAnalysis opAnalysis = (OperationalAnalysis) arch;
        EntityPkg pkg = opAnalysis.getOwnedEntityPkg();
        entities = new ArrayList<>(OperationalAnalysisExt.getAllEntity(pkg));
      }
    }

    return entities.stream().filter(c -> !c.getRepresentingParts().isEmpty()).collect(Collectors.toList());
  }

  public List<CommunicationMean> getAllCommunicationMeans(OperationalAnalysis arch) {
    return OperationalAnalysisExt.getAllCommunicationMeans(arch);
  }

  /**
   * used in OA (Operational Entity Blank diagram)
   * 
   * @param entityView
   * @param selectedComMeans
   * @return
   */
  public EObject insertRemoveCommunicationMeans(DNodeContainer entityView, List<CommunicationMean> selectedComMeans) {
    Set<CommunicationMean> displayedComMeans = new HashSet<CommunicationMean>();
    Set<DEdge> incomingOutgoingEdges = new HashSet<DEdge>();
    Map<Entity, DNodeContainer> displayedEntities = new HashMap<Entity, DNodeContainer>();

    incomingOutgoingEdges.addAll(entityView.getIncomingEdges());
    incomingOutgoingEdges.addAll(entityView.getOutgoingEdges());
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(entityView);

    ContainerMapping entityMapping = DiagramServices.getDiagramServices().getContainerMapping(diagram,
        IMappingNameConstants.OAB_ENTITY_MAPPING_NAME);
    EdgeMapping comMeanMapping = DiagramServices.getDiagramServices().getEdgeMapping(diagram,
        IMappingNameConstants.OAB_COMMUNICATION_MEAN_MAPPING_NAME);

    for (AbstractDNode aContainer : diagram.getContainers()) {
      if (aContainer.getTarget() instanceof Entity) {
        displayedEntities.put((Entity) aContainer.getTarget(), (DNodeContainer) aContainer);
      }
    }

    for (DEdge anEdge : incomingOutgoingEdges) {
      if (anEdge.getTarget() instanceof CommunicationMean) {
        if (!selectedComMeans.contains(anEdge.getTarget())) {
          DiagramServices.getDiagramServices().removeEdgeView(anEdge);
        } else {
          displayedComMeans.add((CommunicationMean) anEdge.getTarget());
        }
      }
    }
    for (CommunicationMean aCommunicationMean : selectedComMeans) {
      if (!displayedComMeans.contains(aCommunicationMean)) {
        DNodeContainer sourceView = displayedEntities.get(aCommunicationMean.getSource());
        DNodeContainer targetView = displayedEntities.get(aCommunicationMean.getTarget());
        if (sourceView == null) {
          sourceView = DiagramServices.getDiagramServices().createContainer(entityMapping,
              aCommunicationMean.getSource(), diagram, diagram);
          displayedEntities.put((Entity) aCommunicationMean.getSource(), sourceView);
        }
        if (targetView == null) {
          targetView = DiagramServices.getDiagramServices().createContainer(entityMapping,
              aCommunicationMean.getTarget(), diagram, diagram);
          displayedEntities.put((Entity) aCommunicationMean.getTarget(), targetView);
        }
        DiagramServices.getDiagramServices().createEdge(comMeanMapping, sourceView, targetView, aCommunicationMean);
      }
    }
    return entityView;
  }

  public EObject insertRemoveEntities(EObject view, List<Entity> selectedEntities) {

    DDiagram diagram = CapellaServices.getService().getDiagramContainer(view);
    // Get all container of the diagram
    for (AbstractDNode aContainer : diagram.getContainers()) {
      // Test if the model element is an Entity and the view is a DNodeContainer
      if ((aContainer.getTarget() instanceof Entity) && (aContainer instanceof DNodeContainer)) {
        Entity currentEntity = (Entity) aContainer.getTarget();
        aContainer.setVisible(selectedEntities.contains(currentEntity));

      }
    }

    return view;
  }

  public OperationalActivity getInteractionSourceInDiagram(FunctionalExchange interaction, DDiagram diagram) {
    // Precondition: check given FE has an OA as source.
    AbstractFunction sourceFunction = FunctionalExchangeExt.getSourceFunction(interaction);
    if (!(sourceFunction instanceof OperationalActivity)) {
      return null;
    }
    // Get OAs displayed in diagram.
    Set<OperationalActivity> displayedActivities = new HashSet<OperationalActivity>();
    for (DDiagramElement anElement : diagram.getDiagramElements()) {
      if (anElement.getTarget() instanceof OperationalActivity) {
        displayedActivities.add((OperationalActivity) anElement.getTarget());
      }
    }

    if (displayedActivities.contains(sourceFunction)) {
      return (OperationalActivity) sourceFunction;
    }

    EObject nearestDisplayedParentOA = EcoreUtil2.getFirstContainer(sourceFunction,
        eObj -> displayedActivities.contains(eObj));

    if (nearestDisplayedParentOA instanceof OperationalActivity) {
      AbstractFunction targetFunction = FunctionalExchangeExt.getTargetFunction(interaction);
      if (!EcoreUtil.isAncestor(nearestDisplayedParentOA, targetFunction)) {
        return (OperationalActivity) nearestDisplayedParentOA;
      }
    }

    return null;
  }

  public OperationalActivity getInteractionTargetInDiagram(FunctionalExchange interaction, DDiagram diagram) {
    // Precondition: check given FE has an OA as target.
    AbstractFunction targetFunction = FunctionalExchangeExt.getTargetFunction(interaction);
    if (!(targetFunction instanceof OperationalActivity)) {
      return null;
    }
    // Get OAs displayed in diagram.
    Set<OperationalActivity> displayedActivities = new HashSet<OperationalActivity>();
    for (DDiagramElement anElement : diagram.getDiagramElements()) {
      if (anElement.getTarget() instanceof OperationalActivity) {
        displayedActivities.add((OperationalActivity) anElement.getTarget());
      }
    }
    
    if (displayedActivities.contains(targetFunction)) {
      return (OperationalActivity) targetFunction;
    }
    
    EObject nearestDisplayedParentOA = EcoreUtil2.getFirstContainer(targetFunction,
        eObj -> displayedActivities.contains(eObj));
    
    if (nearestDisplayedParentOA instanceof OperationalActivity) {
      AbstractFunction sourceFunction = FunctionalExchangeExt.getSourceFunction(interaction);
      if (!EcoreUtil.isAncestor(nearestDisplayedParentOA, sourceFunction)) {
        return (OperationalActivity) nearestDisplayedParentOA;
      }
    }

    return null;
  }

  public List<EObject> getAvailableOperationalActivityAllocations(Role role) {
    List<EObject> returnedList = new ArrayList<EObject>();
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(role.eClass(),
        OaPackage.Literals.ROLE__OWNED_ACTIVITY_ALLOCATIONS);
    if (query != null) {
      returnedList.addAll(query.getAvailableElements(role));
    }
    return returnedList;
  }

  /**
   * A boring helper for COAI since sirius has changed something on import mapping management but we don't know what To
   * remove when contextual diagrams will be removed
   * 
   * @param containerView
   * @param target
   * @return
   */
  @Deprecated
  public boolean isOAInternalValid(DSemanticDecorator containerView) {

    // Avoid creation of mapping COAI_OPERATIONAL_ACTIVITY_IMPORT_SUB_MAPPING_NAME into diagram (only into a node)
    if (!(containerView instanceof DDiagram)) {
      return true;
    }
    return false;
  }

  /**
   * A boring helper for COAI since sirius has changed something on import mapping management but we don't know what To
   * remove when contextual diagrams will be removed
   * 
   * @param containerView
   * @param target
   * @return
   */
  @Deprecated
  public boolean isOARootValid(DSemanticDecorator containerView, EObject target) {
    if ((target instanceof AbstractNamedElement) && "OA2".equals(((AbstractNamedElement) target).getName())) { //$NON-NLS-1$
      System.out.println(0);
    }
    // Avoid creation of mapping COAI_OPERATIONAL_ACTIVITY_IMPORT_MAPPING_NAME for internal data flow
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView);
    if (diagram instanceof DSemanticDecorator) {
      if (EcoreUtil.isAncestor(((DSemanticDecorator) diagram).getTarget(), target)) {
        return target == ((DSemanticDecorator) diagram).getTarget();
      }
    }
    return false;
  }

  /**
   * A boring helper for COAI since sirius has changed something on import mapping management but we don't know what To
   * remove when contextual diagrams will be removed
   * 
   * @param containerView
   * @param target
   * @return
   */
  @Deprecated
  public boolean isOARootValidA(DSemanticDecorator containerView, EObject target) {
    if ((target instanceof AbstractNamedElement) && "OA2".equals(((AbstractNamedElement) target).getName())) { //$NON-NLS-1$
      System.out.println(0);
    }
    // Avoid creation of mapping COAI_OPERATIONAL_ACTIVITY_IMPORT_MAPPING_NAME for internal data flow
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(containerView);
    if (diagram instanceof DSemanticDecorator) {
      if (EcoreUtil.isAncestor(((DSemanticDecorator) diagram).getTarget(), target)) {
        return target == ((DSemanticDecorator) diagram).getTarget();
      }
    }
    return true;
  }

  /**
   * @deprecated Will be removed, use CapabilityPkgExt.getAllAbstractCapabilities
   */
  @Deprecated
  public List<Object> getAllOperationalCapabilities(CapellaElement element) {
    BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(element);
    if (!(arch instanceof OperationalAnalysis)) {
      return Collections.emptyList();
    }
    List<Object> result = new ArrayList<Object>(
        OperationalAnalysisExt.getAllOperationalCapabilities((OperationalAnalysis) arch));
    return result;
  }

  public boolean hideAllocatedInteractions(EObject obj) {
    return (obj instanceof FunctionalExchange)
        && ((FunctionalExchange) obj).getAllocatingComponentExchanges().isEmpty();
  }

  public Collection<Part> getAllEntities(EObject any) {
    Collection<Part> parts = new ArrayList<>();

    OperationalAnalysis architecture = (OperationalAnalysis) EcoreUtil2.getFirstContainer(any,
        OaPackage.Literals.OPERATIONAL_ANALYSIS);
    for (Entity entity : OperationalAnalysisExt.getAllEntity(architecture)) {
      parts.addAll(entity.getRepresentingParts());
    }

    return parts;
  }
}
