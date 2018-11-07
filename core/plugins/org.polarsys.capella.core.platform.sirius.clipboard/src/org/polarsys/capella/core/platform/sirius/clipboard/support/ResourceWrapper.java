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
package org.polarsys.capella.core.platform.sirius.clipboard.support;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.polarsys.capella.shared.id.handler.IdManager;

public class ResourceWrapper extends XMLResourceImpl {

  private Resource resource;
  
  public ResourceWrapper(Resource resource) {
    this.resource = resource;
  }
  
  @Override
  public ResourceSet getResourceSet() {
    return resource.getResourceSet();
  }
  
  @Override
  public String getID(EObject eObject) {  
    if(eObject.eResource() == resource) {
      return IdManager.getInstance().getId(eObject);
    }
    return null;
  }
}
