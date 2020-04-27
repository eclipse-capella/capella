/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.diagram.helpers.naming.DAnnotationSourceConstants;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

public class EOIController extends DAnnotationReferenceController {
  
  public EOIController() {
    super(DAnnotationSourceConstants.CAPELLA_ELEMENT_OF_INTEREST);
  }

  @Override
  public List<EObject> readOpenValues(EObject semanticElement, EStructuralFeature semanticFeature, boolean available) {
    
    List<EObject> result = new ArrayList<EObject>();
    if (available) {
      Session session = SessionManager.INSTANCE.getSession(((DRepresentationDescriptor)semanticElement).getTarget());
      for (Resource resource : session.getSemanticResources()) {
        if (CapellaResourceHelper.isCapellaResource(resource)) {
          for (Iterator<EObject> it = resource.getAllContents(); it.hasNext();) {
            result.add(it.next());
          }
        }
      }
    } else {
      result.addAll(loadValues(semanticElement, semanticFeature));
    }
    return result;
  }

}
