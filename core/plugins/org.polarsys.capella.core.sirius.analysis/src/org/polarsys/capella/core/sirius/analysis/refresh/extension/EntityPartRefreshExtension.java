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
package org.polarsys.capella.core.sirius.analysis.refresh.extension;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.IMappingNameConstants;

public class EntityPartRefreshExtension extends AbstractRefreshExtension implements IRefreshExtension{

	/**
	 * <b>Before Refresh</b>
	 * <p>
	 *  This method is executed before the refreshing action of the diaram
	 *  @param diagram_p
	 */
	public void beforeRefresh(DDiagram diagram_p) 
	{
		CsServices.getService().refreshEntitiesArchitecture(getComponentMapping(diagram_p), diagram_p);  
	}

	/**
	 * <b>Post Refresh</b>
	 * <p>
	 *  This method is executed after the refreshing action of the diagram
	 *  @param diagram
	 */
	public void postRefresh(DDiagram diagram_p) {
		// do nothing
	}
	
	public ContainerMapping getComponentMapping (DDiagram diagram_p){
	    if (diagram_p.getDescription().getName().equals(IDiagramNameConstants.OPERATIONAL_CAPABILITIES_ENTITYIES_BLANK_DIAGRAM_NAME)){
	      return DiagramServices.getDiagramServices().getContainerMapping(diagram_p, IMappingNameConstants.COC_OPERATIONAL_ENTITY_MAPPING_NAME);
	    }
	    return null;
	  }
}
