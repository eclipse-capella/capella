/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package ms.configuration.services.cs;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.polarsys.capella.core.diagram.helpers.naming.DiagramDescriptionConstants;

public class MsMappingConstants {

  public static final URI MS_Viewpoint = URI.createPlatformPluginURI("ms.design/description/ms.odesign", false);
 
  public static final URI ES_Edge_InstanceRole_Configuration = MS_Viewpoint.appendFragment("//@ownedViewpoints[name='ms']/@ownedRepresentationExtensions[name='ESConfigurations']/@layers[name='ES_Layer_Configuration']/@edgeMappings[name='ES_Layer_InstanceRole_Configuration_Edge']");
  public static final URI IS_Edge_InstanceRole_Configuration = MS_Viewpoint.appendFragment("//@ownedViewpoints[name='ms']/@ownedRepresentationExtensions[name='ISConfigurations']/@layers[name='IS_Layer_Configuration']/@edgeMappings[name='IS_Layer_InstanceRole_Configuration_Edge']");

  public static final URI ES_Node_Configuration = MS_Viewpoint.appendFragment("//@ownedViewpoints[name='ms']/@ownedRepresentationExtensions[name='ESConfigurations']/@layers[name='ES_Layer_Configuration']/@nodeMappings[name='ES_Layer_ConfigurationNode']");
  public static final URI IS_Node_Configuration = MS_Viewpoint.appendFragment("//@ownedViewpoints[name='ms']/@ownedRepresentationExtensions[name='ISConfigurations']/@layers[name='IS_Layer_Configuration']/@nodeMappings[name='IS_Layer_ConfigurationNode']"); 

  static NodeMapping getConfigurationNodeMapping(SequenceDDiagram diagram) {
    URI mappingURI = null;
    if (DiagramDescriptionConstants.INTERFACE_SCENARIO.equals(diagram.getDescription().getName())){
      mappingURI = MsMappingConstants.IS_Node_Configuration;
    } else if (DiagramConstants.EXCHANGE_SCENARIO.equals(diagram.getDescription().getName())) {
      mappingURI = MsMappingConstants.ES_Node_Configuration;
    }
    return (NodeMapping) getMapping(diagram, mappingURI);
  }

  static DiagramElementMapping getMapping(SequenceDDiagram diagram, URI mappingURI) {
    Session session = SessionManager.INSTANCE.getSession(diagram);
    return (DiagramElementMapping) session.getTransactionalEditingDomain().getResourceSet().getEObject(mappingURI, false);
  }

  static EdgeMapping getInstanceRoleConfigurationEdgeMapping(SequenceDDiagram diagram) {
    URI mappingURI = null;
    if (DiagramDescriptionConstants.INTERFACE_SCENARIO.equals(diagram.getDescription().getName())){
      mappingURI = MsMappingConstants.IS_Edge_InstanceRole_Configuration;
    } else if (DiagramConstants.EXCHANGE_SCENARIO.equals(diagram.getDescription().getName())) {
      mappingURI = MsMappingConstants.ES_Edge_InstanceRole_Configuration;
    }
    return (EdgeMapping) getMapping(diagram, mappingURI);
  }

}
