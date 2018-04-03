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
package org.polarsys.capella.common.platform.sirius.customisation.id.handler.drepresentation;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.polarsys.capella.shared.id.handler.AbstractIdHandler;
import org.polarsys.capella.shared.id.handler.IScope;

public class DRepresentationIdHandler extends AbstractIdHandler {

  public DRepresentationIdHandler() {
    // Do nothing
  }

  @Override
  public String getId(EObject object) {
    if (object instanceof DRepresentation) {
      return ((DRepresentation) object).getUid(); 
    }
    return null;
  }
  
	@Override
	public EObject getEObject(String id, IScope scope) {
	  for (Resource resource : scope.getResources()) {
      if (resource instanceof AirdResource) {
        Optional<DRepresentation> foundDRepresentation = resource.getContents().stream()
            .filter(DRepresentation.class::isInstance).map(DRepresentation.class::cast)
            .filter(dRepresentation -> id.equals(dRepresentation.getUid())).findFirst();

        if (foundDRepresentation.isPresent())
          return foundDRepresentation.get();
      }
    }

	  return super.getEObject(id, scope);
	}
}
