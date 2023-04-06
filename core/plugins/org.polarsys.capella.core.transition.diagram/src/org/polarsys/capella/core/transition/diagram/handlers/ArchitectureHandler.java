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
package org.polarsys.capella.core.transition.diagram.handlers;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramNamingConstants;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.sirius.analysis.ABServices;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class ArchitectureHandler extends AbstractDiagramHandler {

  @Override
  public boolean handles(IContext context_p, RepresentationDescription representation_p) {

    if (DiagramHelper.getService().isA(representation_p, IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (DiagramHelper.getService().isA(representation_p,
        IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (DiagramHelper.getService().isA(representation_p,
        IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (DiagramHelper.getService().isA(representation_p,
        IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return true;

    }
    return false;
  }

  /**
   * In PAB, we don't want to display Root Physical System when initialized from a LAB with displayed LogicalSystem
   */
  @Override
  public DSemanticDecorator showNode(IContext context_p, RepresentationDescription sourceDescription_p,
      DDiagramContents targetContents_p, AbstractNodeMapping mapping_p, DSemanticDecorator containerNode_p,
      EObject targetSemantic_p) {
    if (targetSemantic_p instanceof Part) {
      AbstractType targetSemanticComponent = ((Part) targetSemantic_p).getAbstractType();
      if (targetSemanticComponent instanceof PhysicalComponent) {
        BlockArchitecture blockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(targetSemantic_p);
        if (targetSemanticComponent.equals(blockArchitecture.getSystem())) {
          return null;
        }
      }
    }
    return super.showNode(context_p, sourceDescription_p, targetContents_p, mapping_p, containerNode_p,
        targetSemantic_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean covers(IContext context_p, RepresentationDescription description_p) {

    if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (DiagramHelper.getService().isA(description_p,
        IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (DiagramHelper.getService().isA(description_p,
        IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return true;

    }
    return false;

  }

  @Override
  public boolean backCovers(IContext context_p, RepresentationDescription description_p) {
    if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (DiagramHelper.getService().isA(description_p,
        IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (DiagramHelper.getService().isA(description_p,
        IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return true;

    }
    return false;
  }

  @Override
  public EObject _getTargetSemantic(IContext context_p, EObject sourceSemantic_p,
      RepresentationDescription sourceDescription_p, RepresentationDescription targetDescription_p) {

    EObject result = super._getTargetSemantic(context_p, sourceSemantic_p, sourceDescription_p, targetDescription_p);
    if (sourceSemantic_p instanceof Entity) {
      result = null;
    }
    return result;
  }

  @Override
  public Collection<EObject> getTargetSemantics(IContext context_p, EObject sourceSemantic_p,
      RepresentationDescription sourceDescription_p, RepresentationDescription targetDescription_p) {

    Collection<EObject> result = super.getTargetSemantics(context_p, sourceSemantic_p, sourceDescription_p,
        targetDescription_p);

    ArrayList<EObject> objects = new ArrayList<EObject>();
    for (EObject resut : result) {
      if (result instanceof Component) {
        for (Part part : getCache(ComponentExt::getRepresentingParts, (Component) result)) {
          objects.add(part);
          break;
        }
      } else {
        objects.add(resut);
      }
    }

    return objects;
  }

  @Override
  public DDiagramElement showEdge(IContext context_p, RepresentationDescription sourceDescription_p,
      DDiagramContents targetContents_p, EdgeMapping mapping_p, DSemanticDecorator sourceNode_p,
      DSemanticDecorator targetNode_p, EObject targetSemantic_p) {

    if (DiagramHelper.getService().isA(sourceDescription_p,
        IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      if (targetSemantic_p instanceof FunctionalExchange) {
        ABServices.getService().showABFunctionalExchange(Collections.singleton(targetSemantic_p), targetContents_p);

      } else if (targetSemantic_p instanceof ComponentExchange) {
        ABServices.getService().showABComponentExchange(Collections.singleton(targetSemantic_p), targetContents_p);
      }
    }

    return super.showEdge(context_p, sourceDescription_p, targetContents_p, mapping_p, sourceNode_p, targetNode_p,
        targetSemantic_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DiagramElementMapping getTargetMapping(IContext context_p, RepresentationDescription sourceDescription_p,
      RepresentationDescription targetDescription_p, DiagramElementMapping sourceMapping_p, EObject source_p,
      EObject target_p) {

    String mappingName = sourceMapping_p.getName();
    String targetMappingName = null;

    if (DiagramHelper.getService().isA(sourceDescription_p,
        IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      if (DiagramHelper.getService().isA(targetDescription_p,
          IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {

        if (IMappingNameConstants.OAB_OPERATIONAL_PROCESS_END_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.SAB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;

        } else if (IMappingNameConstants.OAB_COMMUNICATION_MEAN_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.SAB_CONNECTION_MAPPING_NAME;

        } else if (IMappingNameConstants.OAB_COMPONENT_CATEGORY_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.SAB_COMPONENT_CATEGORY_MAPPING_NAME;

        } else if (IMappingNameConstants.OAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.SAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME;

        } else if (IMappingNameConstants.OAB_ENTITY_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.SAB_SYSTEM_MAPPING_NAME;

        } else if (IMappingNameConstants.OAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.SAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;

        } else if (IMappingNameConstants.OAB_FUNCTION_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.SAB_SYSTEM_FUNCTION_MAPPING_NAME;
        }

      }

    } else if (DiagramHelper.getService().isA(sourceDescription_p,
        IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      if (DiagramHelper.getService().isA(targetDescription_p,
          IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {

        if (IMappingNameConstants.SAB_SYSTEM_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_LOGICAL_COMPONENT_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_ACTOR_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_LOGICAL_COMPONENT_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_COMPONENT_CATEGORY_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_COMPONENT_CATEGORY_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_COMPONENT_PORT_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_COMPONENT_PORT_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_CONNECTION_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_CONNECTION_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_CONTROL_NODE_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_CONTROL_NODE_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_EXCHANGE_CATEGORY_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_EXCHANGE_CATEGORY_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_FUNCTION_PORT_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_FUNCTION_PORT_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_PHYSICAL_PORT_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_PHYSICAL_PORT_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_PHYSICALLINK_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_PHYSICALLINK_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_FUNCTION_PORT_ALLOCATION_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_FUNCTION_PORT_ALLOCATION_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_FUNCTIONAL_CHAIN_END_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_FUNCTIONAL_CHAIN_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_FUNCTIONAL_CHAIN_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_PHYSICAL_PATH_INTERNAL_LINK_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_PHYSICAL_PATH_INTERNAL_LINK_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_PHYSICAL_PATH_END.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_PHYSICAL_PATH_END;

        } else if (IMappingNameConstants.SAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_INTERNAL_LINK_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_INTERNAL_LINK_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_SYSTEM_FUNCTION_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_LOGICAL_FUNCTION_MAPPING_NAME;

        } else if (IMappingNameConstants.SAB_SYSTEM_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LAB_LOGICAL_COMPONENT_MAPPING_NAME;

        }

      }

    } else if (DiagramHelper.getService().isA(sourceDescription_p,
        IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      if (DiagramHelper.getService().isA(targetDescription_p,
          IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {

        if (IMappingNameConstants.LAB_COMPONENT_CATEGORY_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_COMPONENT_CATEGORY_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_COMPONENT_CATEGORY_PIN_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_COMPONENT_PORT_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_COMPONENT_PORT_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_CONNECTION_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_CONNECTION_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_FUNCTION_PORT_ALLOCATION_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_FUNCTION_PORT_ALLOCATION_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_CONTROL_NODE_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_CONTROL_NODE_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_EXCHANGE_CATEGORY_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_EXCHANGE_CATEGORY_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_FUNCTION_PORT_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_FUNCTION_PORT_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_FUNCTIONAL_CHAIN_END_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_FUNCTIONAL_CHAIN_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_FUNCTIONAL_CHAIN_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_PHYSICAL_PATH_END.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_PHYSICAL_PATH_END;

        } else if (IMappingNameConstants.LAB_PHYSICALLINK_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_PHYSICALLINK_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_INTERNAL_LINK_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_INTERNAL_LINK_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_INTERNAL_LINK_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_INTERNAL_LINK_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_LOGICAL_COMPONENT_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_PHYSICAL_COMPONENT_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_LOGICAL_FUNCTION_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_PHYSICAL_FUNCTION_MAPPING_NAME;

        } else if (IMappingNameConstants.LAB_PHYSICAL_PORT_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PAB_PHYSICAL_PORT_MAPPING_NAME;

        }
      }
    }

    return DiagramServices.getDiagramServices().getMappingByName(targetDescription_p, targetMappingName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public RepresentationDescription getTargetDescription(IContext context_p, Session session_p,
      RepresentationDescription description_p) {
    DiagramHelper service = DiagramHelper.getService();

    // Architecture Blank
    if (service.isA(description_p, IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      return service.getDescription(session_p, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME);

    } else if (service.isA(description_p, IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return service.getDescription(session_p, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME);

    } else if (service.isA(description_p, IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return service.getDescription(session_p, IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME);
    }

    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTargetName(IContext context_p, DRepresentation diagram_p,
      RepresentationDescription targetDescription_p) {
    RepresentationDescription description = DiagramHelper.getService().getDescription(diagram_p);
    String name = RepresentationHelper.getRepresentationDescriptor(diagram_p).getName();
    name = name.replace(description.getName(), targetDescription_p.getName());

    if (DiagramHelper.getService().isA(description, IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      if (DiagramHelper.getService().isA(targetDescription_p,
          IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
        name = name.replace(description.getLabel(), targetDescription_p.getName());
        name = name.replace(DiagramNamingConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_PREFIX,
            DiagramNamingConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_PREFIX);
      }

    } else if (DiagramHelper.getService().isA(description,
        IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      if (DiagramHelper.getService().isA(targetDescription_p,
          IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
        name = name.replace(DiagramNamingConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_PREFIX,
            DiagramNamingConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_PREFIX);

      }

    } else if (DiagramHelper.getService().isA(description,
        IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      if (DiagramHelper.getService().isA(targetDescription_p,
          IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
        name = name.replace(DiagramNamingConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_PREFIX,
            DiagramNamingConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_PREFIX);
      }
    }
    return name;
  }

  /**
   * @param edgeTarget_p
   * @param sourceNode_p
   * @param targetNode_p
   * @return
   */
  @Override
  public boolean isReconciliable(IContext context_p, RepresentationDescription sourceDescription_p, DEdge edgeTarget_p,
      DSemanticDecorator sourceNode_p, DSemanticDecorator targetNode_p) {
    if (DiagramHelper.getService().isA(sourceDescription_p,
        IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      if (edgeTarget_p.getTarget() instanceof FunctionalExchange) {
        return EcoreUtil.isAncestor(sourceNode_p, edgeTarget_p.getSourceNode())
            && EcoreUtil.isAncestor(targetNode_p, edgeTarget_p.getTargetNode());

      } else if (edgeTarget_p.getTarget() instanceof ComponentExchange) {
        return EcoreUtil.isAncestor(sourceNode_p, edgeTarget_p.getSourceNode())
            && EcoreUtil.isAncestor(targetNode_p, edgeTarget_p.getTargetNode());
      }
    }
    return sourceNode_p.equals(edgeTarget_p.getSourceNode()) && targetNode_p.equals(edgeTarget_p.getTargetNode());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getTargetDefaultLocation(IContext context_p, BlockArchitecture root_p,
      RepresentationDescription description_p) {

    // Architecture Blank
    if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getContext(root_p);

    } else if (DiagramHelper.getService().isA(description_p,
        IDiagramNameConstants.SYSTEM_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getOrCreateSystem(root_p);

    } else if (DiagramHelper.getService().isA(description_p,
        IDiagramNameConstants.LOGICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getOrCreateSystem(root_p);

    } else if (DiagramHelper.getService().isA(description_p,
        IDiagramNameConstants.PHYSICAL_ARCHITECTURE_BLANK_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getOrCreateSystem(root_p);
    }

    return null;
  }

}
