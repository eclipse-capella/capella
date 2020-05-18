/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramNamingConstants;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.DDiagramContents;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * An handler for dataflow diagrams
 */
public class DataflowHandler extends AbstractDiagramHandler {

  @Override
  public boolean handles(IContext context_p, RepresentationDescription representation_p) {

    if (DiagramHelper.getService().isA(representation_p, IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (DiagramHelper.getService().isA(representation_p, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (DiagramHelper.getService().isA(representation_p, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (DiagramHelper.getService().isA(representation_p, IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return true;

    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean covers(IContext context_p, RepresentationDescription description_p) {
    if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return true;

    }
    return false;

  }

  @Override
  public boolean backCovers(IContext context_p, RepresentationDescription description_p) {
    if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return true;

    }
    return false;
  }

  @Override
  public DDiagramElement showEdge(IContext context_p, RepresentationDescription sourceDescription_p, DDiagramContents targetContents_p, EdgeMapping mapping_p,
      DSemanticDecorator sourceNode_p, DSemanticDecorator targetNode_p, EObject targetSemantic_p) {

    if (DiagramHelper.getService().isA(sourceDescription_p, IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME)) {
      if (targetSemantic_p instanceof FunctionalExchange) {
        FaServices.getFaServices().showDFFunctionalExchange((AbstractDNode) sourceNode_p, (FunctionalExchange) targetSemantic_p, targetContents_p, false);
      }
    }

    return super.showEdge(context_p, sourceDescription_p, targetContents_p, mapping_p, sourceNode_p, targetNode_p, targetSemantic_p);
  }

  /**
   * @param edgeTarget_p
   * @param sourceNode_p
   * @param targetNode_p
   * @return
   */
  @Override
  public boolean isReconciliable(IContext context_p, RepresentationDescription sourceDescription_p, DEdge edgeTarget_p, DSemanticDecorator sourceNode_p,
      DSemanticDecorator targetNode_p) {
    if (DiagramHelper.getService().isA(sourceDescription_p, IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME)) {
      if (edgeTarget_p.getTarget() instanceof FunctionalExchange) {
        return EcoreUtil.isAncestor(sourceNode_p, edgeTarget_p.getSourceNode()) && EcoreUtil.isAncestor(targetNode_p, edgeTarget_p.getTargetNode());
      }
    }
    return sourceNode_p.equals(edgeTarget_p.getSourceNode()) && targetNode_p.equals(edgeTarget_p.getTargetNode());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DiagramElementMapping getTargetMapping(IContext context_p, RepresentationDescription sourceDescription_p,
      RepresentationDescription targetDescription_p, DiagramElementMapping sourceMapping_p, EObject source_p, EObject target_p) {

    String mappingName = sourceMapping_p.getName();
    String targetMappingName = null;

    if (DiagramHelper.getService().isA(sourceDescription_p, IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME)) {
      if (DiagramHelper.getService().isA(targetDescription_p, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {

        if (IMappingNameConstants.OAIB_FUNCTION_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.SDFB_FUNCTION_MAPPING_NAME;

        } else if (IMappingNameConstants.OAIB_FUNCTIONAL_EXCHANGE_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.SDFB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;

        } else if (IMappingNameConstants.OAIB_OPERATIONAL_PROCESS_END_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.SDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;

        } else if (IMappingNameConstants.OAIB_OPERATIONAL_PROCESS_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.SDFB_FUNCTIONAL_CHAIN_MAPPING_NAME;

        }
      }

    } else if (DiagramHelper.getService().isA(sourceDescription_p, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      if (DiagramHelper.getService().isA(targetDescription_p, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {

        if (IMappingNameConstants.SDFB_FUNCTION_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LDFB_FUNCTION_MAPPING_NAME;

        } else if (IMappingNameConstants.SDFB_FUNCTIONAL_EXCHANGE_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LDFB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;

        } else if (IMappingNameConstants.SDFB_FUNCTIONAL_CHAIN_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LDFB_FUNCTIONAL_CHAIN_MAPPING_NAME;

        } else if (IMappingNameConstants.SDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;

        } else if (IMappingNameConstants.SDFB_CONTROL_NODE_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LDFB_CONTROL_NODE_MAPPING_NAME;

        } else if (IMappingNameConstants.SDFB_PIN_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LDFB_PIN_MAPPING_NAME;

        } else if (IMappingNameConstants.SDFB_INTERNAL_LINK_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LDFB_INTERNAL_LINK_MAPPING_NAME;

        } else if (IMappingNameConstants.SDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;

        } else if (IMappingNameConstants.SDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;

        } else if (IMappingNameConstants.SDFB_EXCHANGE_CATEGORY_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.LDFB_EXCHANGE_CATEGORY_MAPPING_NAME;
        }

      }

    } else if (DiagramHelper.getService().isA(sourceDescription_p, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      if (DiagramHelper.getService().isA(targetDescription_p, IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {

        if (IMappingNameConstants.LDFB_FUNCTION_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PDFB_FUNCTION_MAPPING_NAME;

        } else if (IMappingNameConstants.LDFB_FUNCTIONAL_EXCHANGE_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PDFB_FUNCTIONAL_EXCHANGE_MAPPING_NAME;

        } else if (IMappingNameConstants.LDFB_FUNCTIONAL_CHAIN_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PDFB_FUNCTIONAL_CHAIN_MAPPING_NAME;

        } else if (IMappingNameConstants.LDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PDFB_FUNCTIONAL_CHAIN_END_MAPPING_NAME;

        } else if (IMappingNameConstants.LDFB_CONTROL_NODE_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PDFB_CONTROL_NODE_MAPPING_NAME;

        } else if (IMappingNameConstants.LDFB_PIN_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PDFB_PIN_MAPPING_NAME;

        } else if (IMappingNameConstants.LDFB_INTERNAL_LINK_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PDFB_INTERNAL_LINK_MAPPING_NAME;

        } else if (IMappingNameConstants.LDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PDFB_EXCHANGE_CATEGORY_INPUTPORT_MAPPING_NAME;

        } else if (IMappingNameConstants.LDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PDFB_EXCHANGE_CATEGORY_OUTPUTPORT_MAPPING_NAME;

        } else if (IMappingNameConstants.LDFB_EXCHANGE_CATEGORY_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.PDFB_EXCHANGE_CATEGORY_MAPPING_NAME;
        }

      }
    }

    return DiagramServices.getDiagramServices().getMappingByName(targetDescription_p, targetMappingName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public RepresentationDescription getTargetDescription(IContext context_p, Session session_p, RepresentationDescription description_p) {
    DiagramHelper service = DiagramHelper.getService();

    //DataFlow Blank
    if (service.isA(description_p, IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME)) {
      return service.getDescription(session_p, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME);

    } else if (service.isA(description_p, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return service.getDescription(session_p, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME);

    } else if (service.isA(description_p, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return service.getDescription(session_p, IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME);
    }

    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTargetName(IContext context_p, DRepresentation diagram_p, RepresentationDescription targetDescription_p) {
    RepresentationDescription description = DiagramHelper.getService().getDescription(diagram_p);
    String name = RepresentationHelper.getRepresentationDescriptor(diagram_p).getName();
    name = name.replace(description.getName(), targetDescription_p.getName());

    if (DiagramHelper.getService().isA(description, IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME)) {
      if (DiagramHelper.getService().isA(targetDescription_p, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
        name =
            name.replace(DiagramNamingConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_PREFIX,
                DiagramNamingConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_PREFIX);
      }

    } else if (DiagramHelper.getService().isA(description, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      if (DiagramHelper.getService().isA(targetDescription_p, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
        name = name.replace(DiagramNamingConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_PREFIX, DiagramNamingConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_PREFIX);
      }

    } else if (DiagramHelper.getService().isA(description, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      if (DiagramHelper.getService().isA(targetDescription_p, IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
        name = name.replace(DiagramNamingConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_PREFIX, DiagramNamingConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_PREFIX);
      }
    }
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getTargetDefaultLocation(IContext context_p, BlockArchitecture root_p, RepresentationDescription description_p) {

    //DataFlow Blank
    if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.OPERATIONAL_ACTIVITY_INTERACTION_BLANK_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getFunctionPkg(root_p);

    } else if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.SYSTEM_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getFunctionPkg(root_p);

    } else if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.LOGICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getFunctionPkg(root_p);

    } else if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.PHYSICAL_DATA_FLOW_BLANK_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getFunctionPkg(root_p);
    }

    return null;
  }

}
