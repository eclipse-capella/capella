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

public class MsMappingConstants {

  public static final URI MS_Viewpoint = URI.createPlatformPluginURI("ms.design/description/ms.odesign", false);
  public static final URI ES_Edge_InstanceRole_Configuration = MS_Viewpoint.appendFragment("//@ownedViewpoints[name='ms']/@ownedRepresentationExtensions[name='ESConfigurations']/@layers[name='ES_Layer_Configuration']/@edgeMappings[name='ES_Layer_Configuration_InstanceRoleEdge']");
  public static final URI ES_Node_Configuration = MS_Viewpoint.appendFragment("//@ownedViewpoints[name='ms']/@ownedRepresentationExtensions[name='ESConfigurations']/@layers[name='ES_Layer_Configuration']/@nodeMappings[name='ES_Layer_ConfigurationNode']");
  
}
