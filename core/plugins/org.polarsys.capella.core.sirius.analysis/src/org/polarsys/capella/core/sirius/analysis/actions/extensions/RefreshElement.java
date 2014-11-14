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
package org.polarsys.capella.core.sirius.analysis.actions.extensions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;

/**
 */
public class RefreshElement extends AbstractExternalJavaAction {
  /**
   * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#execute(java.util.Collection, java.util.Map)
   */
  public void execute(Collection<? extends EObject> selections_p, Map<String, Object> parameters_p) {
    EObject context = (EObject) parameters_p.get(CONTEXT);
    
    if (context instanceof DRepresentationElement) {
      CapellaServices.getService().refreshElement((DRepresentationElement)context);
    } else if (context instanceof DDiagram) {
      CapellaServices.getService().forceRefresh((DDiagram)context);
    }
  }

}
