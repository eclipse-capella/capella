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
package org.polarsys.capella.core.sirius.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.AbstractDNode;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DEdge;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.EdgeTarget;
import org.eclipse.sirius.viewpoint.description.AbstractNodeMapping;
import org.eclipse.sirius.viewpoint.description.EdgeMapping;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorCapabilityInvolvement;
import org.polarsys.capella.core.data.ctx.ActorMissionInvolvement;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityExploitation;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityOperationalCapabilityInvolvement;
import org.polarsys.capella.core.data.oa.OperationalCapability;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.common.queries.debug.QueryDebugger;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;

/**
 */
public class InteractionServices {

  /**
   * Retrieve from the given scope, any element which are already displayed in the diagram. (initial scope variable in tools)
   * @param diagram
   * @param scope
   * @return
   */
  public Collection<EObject> getInitialSelection(DDiagram diagram, Collection<EObject> scope) {

    Map<EObject, DSemanticDecorator> elements = DiagramServices.getDiagramServices().getMapOfDiagramElements(diagram);
    Collection<EObject> result = new HashSet<EObject>();
    for (EObject object : scope) {
      if (elements.containsKey(object)) {
        result.add(object);
      }
    }
    return result;
  }

  /**
   * Retrieve scope for the Relationship tool in the capability diagram
   * @param source
   * @param diagram_p
   * @return
   */
  @SuppressWarnings("unchecked")
  public List<EObject> getCapabilityDiagramScopeInsertRelationship(EObject source, DDiagram diagram_p) {
    if (IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      return getScopeInsertRelationship(source, diagram_p, false, true);

    } else if (IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      return getScopeInsertRelationship(source, diagram_p, true, false);

    } else if (IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      return getScopeInsertRelationship(source, diagram_p, true, false);

    } else if (IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      return getScopeInsertRelationship(source, diagram_p, true, true);
    } else if (IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      return getScopeInsertRelationship(source, diagram_p, false, true);
    } else if (IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME.equals(diagram_p.getDescription().getName())) {
      return getScopeInsertRelationship(source, diagram_p, false, true);
    }

    return Collections.EMPTY_LIST;
  }

  // reroutage dans common.odesign :
  // remplacement dans scope de
  // "&lt;%getAvailableParts($containerView.eContainer(&quot;DDiagram&quot;).eAllContents(&quot;DSemanticDecorator&quot;).target.filter(&quot;InstanceRole&quot;).representedInstance.filter(&quot;Part&quot;))[abstractType.filter(&quot;AbstractActor&quot;).nSize() == 0]%>"
  // par = &lt;% $container.getISScopeInsertComponents() %>
  public List<Part> getISScopeInsertComponents(Scenario scenario) {
    // Collection<Part> partsFilter = new ArrayList<Part>();
    // for (InstanceRole role : scenario.getOwnedInstanceRoles()) {
    // if (role.getRepresentedInstance() instanceof Part) {
    // partsFilter.add((Part) role.getRepresentedInstance());
    // }
    // }
    // List<Part> parts = new ArrayList<Part>(new InteractionAspectService().getAvailableParts(scenario, partsFilter));
    // parts = QueryInterpretor.executeFilter(parts, new RemoveActorsFilter());
    List<Part> parts =
        QueryInterpretor.executeQuery(org.polarsys.capella.core.sirius.analysis.queries.QueryIdentifierConstants.GET_IS_SCOPE_INSERT_COMPONENTS, scenario,
            new QueryContext());
    parts =
        (List) QueryDebugger.executeQueryWithInclusionDebug(
        		org.polarsys.capella.core.libraries.extendedqueries.QueryIdentifierConstants.GET_IS_SCOPE_INSERT_COMPONENTS_FOR_LIB, scenario, parts);
    return parts;
  }

  // reroutage dans common.odesign :
  // remplacement dans scope de "getAllActors[~abstractType.filter(&quot;Part&quot;).nSize >= 1].typedElements"
  // par = &lt;% $container.getISScopeInsertActors() %>
  public List<Part> getISScopeInsertActors(Scenario scenario) {
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(scenario);
    List<Part> parts =
        QueryInterpretor.executeQuery(org.polarsys.capella.core.sirius.analysis.queries.QueryIdentifierConstants.GET_IS_SCOPE_INSERT_ACTORS, architecture,
            new QueryContext());
    parts =
        (List) QueryDebugger.executeQueryWithInclusionDebug(
        		org.polarsys.capella.core.libraries.extendedqueries.QueryIdentifierConstants.GET_IS_SCOPE_INSERT_ACTORS_FOR_LIB, architecture, parts);
    return parts;
  }
  
  /**
   * Retrieve scope for the Relationship tool in the capability diagram
   * @param source
   * @param diagram_p
   * @param addActorMissionInvolvement
   * @param addCapabilityManagement
   * @return
   */
  protected List<EObject> getScopeInsertRelationship(EObject source, EObject diagram_p, boolean addActorMissionInvolvement, boolean addCapabilityManagement) {
    List<EObject> result = new ArrayList<EObject>();
    DDiagram diagram = (DDiagram) diagram_p;

    if (source instanceof AbstractCapability) {
      AbstractCapability capa = (AbstractCapability) source;

      // In synchronized mode, edges will be automatically created, so we create only wanted nodes
      if (diagram.isSynchronized()) {

        if (addCapabilityManagement) {
          if (capa instanceof Capability) {
            for (ActorCapabilityInvolvement involvement : ((Capability) capa).getInvolvedActors()) {
              result.add(involvement.getActor());
            }
          } else if (capa instanceof OperationalCapability) {
            EList<EntityOperationalCapabilityInvolvement> ownedEntityOperationalCapabilityInvolvements =
                ((OperationalCapability) capa).getOwnedEntityOperationalCapabilityInvolvements();
            for (EntityOperationalCapabilityInvolvement involvement : ownedEntityOperationalCapabilityInvolvements) {
              result.add(involvement.getEntity());
            }
          }

          result.addAll(capa.getSub());
          result.addAll(capa.getSuper());
          result.addAll(capa.getExtendedAbstractCapabilities());

          for (AbstractCapabilityExtend extend : capa.getExtending()) {
            result.add(extend.getExtension());
          }

          result.addAll(capa.getIncludedAbstractCapabilities());

          for (AbstractCapabilityInclude extend : capa.getIncluding()) {
            result.add(extend.getInclusion());
          }
        }
        if (capa instanceof Capability) {
          result.addAll(((Capability) capa).getPurposeMissions());
        }

      } else {
        // We retrieve all wanted edges
        if (addCapabilityManagement) {
          if (capa instanceof Capability) {
            result.addAll(((Capability) capa).getInvolvedActors());
          } else if (capa instanceof OperationalCapability) {
            result.addAll(((OperationalCapability) capa).getOwnedEntityOperationalCapabilityInvolvements());
          }

          result.addAll(capa.getSubGeneralizations());
          result.addAll(capa.getSuperGeneralizations());
          result.addAll(capa.getExtending());
          result.addAll(capa.getIncluding());
          result.addAll(capa.getExtends());
          result.addAll(capa.getIncludes());
        }
        if (capa instanceof Capability) {
          result.addAll(((Capability) capa).getPurposes());
        }
      }

    } else if (source instanceof Actor) {
      Actor actor = (Actor) source;

      // In synchronized mode, edges will be automatically created, so we create only wanted nodes
      if (diagram.isSynchronized()) {

        result.addAll(actor.getSub());
        result.addAll(actor.getSuper());

        if (addCapabilityManagement) {
          result.addAll(actor.getContributedCapabilities());
        }
        if (addActorMissionInvolvement) {
          result.addAll(actor.getContributedMissions());
        }

      } else {
        result.addAll(actor.getSubGeneralizations()); // filter actors
        result.addAll(actor.getSuperGeneralizations());

        if (addCapabilityManagement) {
          result.addAll(actor.getParticipationsInCapabilities());
        }
        if (addActorMissionInvolvement) {
          result.addAll(actor.getParticipationsInMissions());
        }
      }

    } else if (source instanceof Mission) {
      Mission mission = (Mission) source;

      // In synchronized mode, edges will be automatically created, so we create only wanted nodes
      if (diagram.isSynchronized()) {
        result.addAll(mission.getExploitedCapabilities());

        if (addActorMissionInvolvement) {
          for (ActorMissionInvolvement involvement : mission.getInvolvedActors()) {
            result.add(involvement.getActor());
          }
        }

      } else {

        if (addActorMissionInvolvement) {
          result.addAll(mission.getInvolvedActors());
        }
        result.addAll(mission.getOwnedCapabilityExploitations());
      }

    } else if (source instanceof Entity) {
      Entity entity = (Entity) source;
      EList<Involvement> involvingInvolvements = entity.getInvolvingInvolvements();

      // In synchronized mode, edges will be automatically created, so we create only wanted nodes
      if (diagram.isSynchronized()) {
        for (Involvement involvement : involvingInvolvements) {
          InvolverElement involver = involvement.getInvolver();
          if ((null != involver) && (involver instanceof OperationalCapability)) {
            result.add(involver);
          }
        }
      } else {
        for (Involvement involvement : involvingInvolvements) {
          InvolverElement involver = involvement.getInvolver();
          if ((null != involver) && (involver instanceof OperationalCapability)) {
            result.add(involvement);
          }
        }
      }

    }

    return result;
  }

  /**
   * Retrieve the edge mapping used in the given diagram for the current object
   * @param diagram
   * @param object
   * @return
   */
  protected EdgeMapping getEdgeMapping(DDiagram diagram, EObject object) {

    String mappingName = null;
    if (IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (object instanceof ActorCapabilityInvolvement) {
        mappingName = IMappingNameConstants.CC_ACTOR_INVOLVEMENT;
      } else if (object instanceof CapabilityExploitation) {
        mappingName = IMappingNameConstants.CC_CAPABILITY_EXPLOITATION;
      } else if (object instanceof AbstractCapabilityExtend) {
        mappingName = IMappingNameConstants.CC_ABSTRACT_CAPABILITY_EXTEND;
      } else if (object instanceof AbstractCapabilityInclude) {
        mappingName = IMappingNameConstants.CC_ABSTRACT_CAPABILITY_INCLUDE;
      } else if (object instanceof AbstractCapabilityGeneralization) {
        mappingName = IMappingNameConstants.CC_ABSTRACT_CAPABILITY_GENERALIZATION;
      } else if (object instanceof Generalization) {
        mappingName = IMappingNameConstants.CC_ACTOR_GENERALIZATION;
      }
    } else if (IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (object instanceof CapabilityExploitation) {
        mappingName = IMappingNameConstants.CM_CAPABILITY_EXPLOITATION;
      } else if (object instanceof Generalization) {
        mappingName = IMappingNameConstants.CM_ACTOR_GENERALIZATION;
      } else if (object instanceof ActorMissionInvolvement) {
        mappingName = IMappingNameConstants.CM_ACTOR_MISSION_INVOLVEMENT;
      }
    } else if (IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (object instanceof CapabilityExploitation) {
        mappingName = IMappingNameConstants.MB_CAPABILITY_EXPLOITATION;
      } else if (object instanceof Generalization) {
        mappingName = IMappingNameConstants.MB_ACTOR_GENERALIZATION;
      } else if (object instanceof ActorMissionInvolvement) {
        mappingName = IMappingNameConstants.MB_ACTOR_MISSION_INVOLVEMENT;
      }
    } else if (IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (object instanceof ActorCapabilityInvolvement) {
        mappingName = IMappingNameConstants.MCB_ACTOR_INVOLVEMENT;
      } else if (object instanceof CapabilityExploitation) {
        mappingName = IMappingNameConstants.MCB_CAPABILITY_EXPLOITATION;
      } else if (object instanceof AbstractCapabilityExtend) {
        mappingName = IMappingNameConstants.MCB_ABSTRACT_CAPABILITY_EXTEND;
      } else if (object instanceof AbstractCapabilityInclude) {
        mappingName = IMappingNameConstants.MCB_ABSTRACT_CAPABILITY_INCLUDE;
      } else if (object instanceof AbstractCapabilityGeneralization) {
        mappingName = IMappingNameConstants.MCB_ABSTRACT_CAPABILITY_GENERALIZATION;
      } else if (object instanceof Generalization) {
        mappingName = IMappingNameConstants.MCB_ACTOR_GENERALIZATION;
      } else if (object instanceof ActorMissionInvolvement) {
        mappingName = IMappingNameConstants.MCB_ACTOR_MISSION_INVOLVEMENT;
      }
    } else if (IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (object instanceof CommunicationMean) {
        mappingName = IMappingNameConstants.OCB_COMMUNICATION_MEAN_MAPPING_NAME;
      } else if (object instanceof EntityOperationalCapabilityInvolvement) {
        mappingName = IMappingNameConstants.OCB_ENTITY_INVOLVEMENT_MAPPING_NAME;
      } else if (object instanceof AbstractCapabilityExtend) {
        mappingName = IMappingNameConstants.OCB_Extends_MAPPING_NAME;
      } else if (object instanceof AbstractCapabilityInclude) {
        mappingName = IMappingNameConstants.OCB_Include_MAPPING_NAME;
      } else if (object instanceof AbstractCapabilityGeneralization) {
        mappingName = IMappingNameConstants.OCB_OC_Generalization_MAPPING_NAME;
      }
    } else if (IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (object instanceof EntityOperationalCapabilityInvolvement) {
        mappingName = IMappingNameConstants.COC_EntityCapabilityInvolvement_MAPPING_NAME;
      } else if (object instanceof AbstractCapabilityExtend) {
        mappingName = IMappingNameConstants.COC_Extends_MAPPING_NAME;
      } else if (object instanceof AbstractCapabilityInclude) {
        mappingName = IMappingNameConstants.COC_Include_MAPPING_NAME;
      } else if (object instanceof AbstractCapabilityGeneralization) {
        mappingName = IMappingNameConstants.COC_Generalization_MAPPING_NAME;
      }
    }

    if (mappingName != null) {
      return DiagramServices.getDiagramServices().getEdgeMapping(diagram, mappingName);
    }
    return null;
  }

  /**
   * Retrieve the node mapping used in the given diagram for the current object
   * @param diagram
   * @param object
   * @return
   */
  protected AbstractNodeMapping getNodeMapping(DDiagram diagram, EObject object) {

    String mappingName = null;
    if (IDiagramNameConstants.CONTEXTUAL_CAPABILITY_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (object instanceof Component) {
        mappingName = IMappingNameConstants.CC_COMPONENT_MAPPING_NAME;
      } else if (object instanceof Mission) {
        mappingName = IMappingNameConstants.CC_MISSION_MAPPING_NAME;
      } else if (object instanceof Capability) {
        mappingName = IMappingNameConstants.CC_CAPABILITY_MAPPING_NAME;
      }

    } else if (IDiagramNameConstants.CONTEXTUAL_MISSION_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (object instanceof Component) {
        mappingName = IMappingNameConstants.CM_COMPONENT_MAPPING_NAME;
      } else if (object instanceof Mission) {
        mappingName = IMappingNameConstants.CM_MISSION_MAPPING_NAME;
      } else if (object instanceof Capability) {
        mappingName = IMappingNameConstants.CM_CAPABILITY_MAPPING_NAME;
      }

    } else if (IDiagramNameConstants.MISSIONS_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (object instanceof Component) {
        mappingName = IMappingNameConstants.MB_COMPONENT_MAPPING_NAME;
      } else if (object instanceof Mission) {
        mappingName = IMappingNameConstants.MB_MISSION_MAPPING_NAME;
      } else if (object instanceof Capability) {
        mappingName = IMappingNameConstants.MB_CAPABILITY_MAPPING_NAME;
      }

    } else if (IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (object instanceof Component) {
        mappingName = IMappingNameConstants.MCB_COMPONENT_MAPPING_NAME;
      } else if (object instanceof Mission) {
        mappingName = IMappingNameConstants.MCB_MISSION_MAPPING_NAME;
      } else if (object instanceof Capability) {
        mappingName = IMappingNameConstants.MCB_CAPABILITY_MAPPING_NAME;
      }
    } else if (IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (object instanceof Entity) {
        mappingName = IMappingNameConstants.OCB_OPERATIONAL_ENTITY_MAPPING_NAME;
      } else if (object instanceof OperationalCapability) {
        mappingName = IMappingNameConstants.OCB_OPERATIONAL_CAPABILITY_MAPPING_NAME;
      }
    } else if (IDiagramNameConstants.CONTEXTUAL_OPERATIONAL_CAPABILITIES__DIAGRAM_NAME.equals(diagram.getDescription().getName())) {
      if (object instanceof Entity) {
        mappingName = IMappingNameConstants.COC_ENTITY_MAPPING_NAME;
      } else if (object instanceof OperationalCapability) {
        mappingName = IMappingNameConstants.COC_OC_MAPPING_NAME;
      }
    }

    if (mappingName != null) {
      return DiagramServices.getDiagramServices().getAbstractNodeMapping(diagram, mappingName);
    }
    return null;
  }

  /**
   * Show selectedItems_p and hide unselected elements of the scope_p in the diagram of sourceView_p
   * @param context_p
   * @param sourceView_p
   * @param selectedItems_p
   * @param scope_p
   */
  public void showHideRelationshipInCapabilityDiagram(EObject context_p, EObject sourceView_p, Collection<EObject> selectedItems_p, Collection<EObject> scope_p) {
    DDiagram diagram = CapellaServices.getService().getDiagramContainer(sourceView_p);
    DiagramServices ds = DiagramServices.getDiagramServices();

    Map<EObject, DSemanticDecorator> elements = ds.getMapOfDiagramElements(diagram);

    for (EObject object : selectedItems_p) {
      AbstractNodeMapping nodeMapping = getNodeMapping(diagram, object);

      if (nodeMapping != null) {
        // Display the given node
        DDiagramElement container = ds.createAbstractDNode(nodeMapping, object, diagram, diagram);
        elements.put(object, container);

      } else {
        // Display the given edge
        EdgeMapping edgeMapping = getEdgeMapping(diagram, object);
        if (edgeMapping != null) {
          EObject source = null;
          EObject target = null;

          // Retrieve source and target according to mapping definition
          EList<EObject> sourceCandidates = ds.getEdgeSourceCandidates(edgeMapping, object, diagram);
          EList<EObject> targetCandidates = ds.getEdgeTargetCandidates(edgeMapping, object, diagram);
          if (sourceCandidates.size() > 0) {
            source = sourceCandidates.get(0);
          }
          if (targetCandidates.size() > 0) {
            target = targetCandidates.get(0);
          }

          DSemanticDecorator sourceView = elements.get(source);
          if (source != null) {
            sourceView = elements.get(source);
            if (sourceView == null) {
              nodeMapping = getNodeMapping(diagram, source);
              if (nodeMapping != null) {
                DDiagramElement container = ds.createAbstractDNode(nodeMapping, source, diagram, diagram);
                elements.put(source, container);
                sourceView = container;
              }
            }
          }

          DSemanticDecorator targetView = elements.get(target);
          if (target != null) {
            targetView = elements.get(target);
            if (targetView == null) {
              nodeMapping = getNodeMapping(diagram, target);
              if (nodeMapping != null) {
                DDiagramElement container = ds.createAbstractDNode(nodeMapping, target, diagram, diagram);
                elements.put(target, container);
                targetView = container;
              }
            }
          }

          if ((sourceView != null) && (targetView != null) && !ds.isOnDiagram(diagram, object)) {
            ds.createEdge(edgeMapping, (EdgeTarget) sourceView, (EdgeTarget) targetView, object);
          }
        }
      }
    }

    for (EObject elementScope : scope_p) {
      if (elements.containsKey(elementScope) && !selectedItems_p.contains(elementScope)) {
        DSemanticDecorator diagramElement = elements.get(elementScope);
        if (diagramElement instanceof DEdge) {
          ds.removeEdgeView((DEdge) diagramElement);

        } else if (diagramElement instanceof AbstractDNode) {
          ds.removeAbstractDNodeView((AbstractDNode) diagramElement);
        }
      }
    }
  }
}
