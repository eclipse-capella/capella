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
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.Mission;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramNamingConstants;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class CapabilitiesHandler extends AbstractDiagramHandler {

  @Override
  public boolean handles(IContext context_p, RepresentationDescription representation_p) {

    if (DiagramHelper.getService().isA(representation_p, IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (DiagramHelper.getService().isA(representation_p, IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (DiagramHelper.getService().isA(representation_p, IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      return true;

    }
    return false;

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean covers(IContext context_p, RepresentationDescription description_p) {
    if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      return false; //Disabled

    } else if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      return false; //Disabled

    }
    return false;

  }

  @Override
  public boolean backCovers(IContext context_p, RepresentationDescription description_p) {
    if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      return true;

    } else if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      return true;

    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DiagramElementMapping getTargetMapping(IContext context_p, RepresentationDescription sourceDescription_p,
      RepresentationDescription targetDescription_p, DiagramElementMapping sourceMapping_p, EObject source_p, EObject target_p) {

    String mappingName = sourceMapping_p.getName();
    String targetMappingName = null;

    if (DiagramHelper.getService().isA(sourceDescription_p, IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      if (DiagramHelper.getService().isA(targetDescription_p, IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
        if (IMappingNameConstants.OCB_ENTITY_INVOLVEMENT_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.MCB_ACTOR_INVOLVEMENT;

        } else if (IMappingNameConstants.OCB_OPERATIONAL_CAPABILITY_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.MCB_CAPABILITY_MAPPING_NAME;
          if (target_p instanceof Mission) {
            targetMappingName = IMappingNameConstants.MCB_MISSION_MAPPING_NAME;
          }

        } else if (IMappingNameConstants.OCB_OPERATIONAL_ENTITY_MAPPING_NAME.equals(mappingName)) {
          if (target_p instanceof SystemComponent) {
            targetMappingName = IMappingNameConstants.MCB_COMPONENT_MAPPING_NAME;
          }

        } else if (IMappingNameConstants.OCB_COMMUNICATION_MEAN_MAPPING_NAME.equals(mappingName)) {
          //Nothing

        }
      }
    } else if (DiagramHelper.getService().isA(sourceDescription_p, IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      if (DiagramHelper.getService().isA(targetDescription_p, IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
        if (IMappingNameConstants.MCB_ABSTRACT_CAPABILITY_EXTEND.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.CRB_EXTENDS_MAPPING;

        } else if (IMappingNameConstants.MCB_ABSTRACT_CAPABILITY_GENERALIZATION.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.CRB_CAP_GENERALIZATION_MAPPING;

        } else if (IMappingNameConstants.MCB_ABSTRACT_CAPABILITY_INCLUDE.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.CRB_INCLIDE_MAPPING;

        } else if (IMappingNameConstants.MCB_ACTOR_GENERALIZATION.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.CRB_ACTOR_GENERALIZATION_MAPPING;

        } else if (IMappingNameConstants.MCB_ACTOR_INVOLVEMENT.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.CRB_INVOLVEMENT_MAPPING;

        } else if (IMappingNameConstants.MCB_CAPABILITY_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.CRB_CAPABILITY_REALIZATION_MAPPING;

        } else if (IMappingNameConstants.MCB_COMPONENT_MAPPING_NAME.equals(mappingName)) {
          targetMappingName = IMappingNameConstants.CRB_COMPONENT_MAPPING;

        } else if (IMappingNameConstants.MCB_ACTOR_MISSION_INVOLVEMENT.equals(mappingName)) {
          //Nothing

        } else if (IMappingNameConstants.MCB_CAPABILITY_EXPLOITATION.equals(mappingName)) {
          //Nothing

        } else if (IMappingNameConstants.MCB_MISSION_MAPPING_NAME.equals(mappingName)) {
          //Nothing

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

    //Capabilities 
    if (service.isA(description_p, IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      return service.getDescription(session_p, IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME);

    } else if (service.isA(description_p, IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      return service.getDescription(session_p, IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK);

    } else if (service.isA(description_p, IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      return service.getDescription(session_p, IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK);
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

    if (DiagramHelper.getService().isA(description, IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      if (DiagramHelper.getService().isA(targetDescription_p, IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
        name =
            name.replace(DiagramNamingConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_PREFIX,
                DiagramNamingConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_PREFIX);

      }
    } else if (DiagramHelper.getService().isA(description, IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      if (DiagramHelper.getService().isA(targetDescription_p, IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
        name =
            name.replace(DiagramNamingConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_PREFIX, DiagramNamingConstants.CAPABILITY_REALIZATION_BLANK_DIAGRAM_PREFIX);

      }
    }
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getTargetDefaultLocation(IContext context_p, BlockArchitecture root_p, RepresentationDescription description_p) {

    //Capabilities
    if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getFunctionPkg(root_p);

    } else if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.CAPABILITY_REALIZATION_BLANK)) {
      return BlockArchitectureExt.getAbstractCapabilityPkg(root_p);

    } else if (DiagramHelper.getService().isA(description_p, IDiagramNameConstants.MISSIONS_CAPABILITIES_BLANK_DIAGRAM_NAME)) {
      return BlockArchitectureExt.getAbstractCapabilityPkg(root_p);
    }

    return null;
  }

}
