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

package org.polarsys.capella.core.platform.sirius.clipboard.operations;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gmf.runtime.emf.clipboard.core.CopyObjects;
import org.eclipse.gmf.runtime.emf.clipboard.core.IClipboardSupport;
import org.eclipse.gmf.runtime.emf.clipboard.core.internal.SavingEMFResource;

@SuppressWarnings({ "restriction", "rawtypes" })
public class CapellaSavingEMFResource extends SavingEMFResource {

  public CapellaSavingEMFResource(URI uri, String encoding, Map defaultSaveOptions, Map copy2ObjectMap,
      CopyObjects copyObjects, IClipboardSupport clipboardOperationHelper) {
    super(uri, encoding, defaultSaveOptions, copy2ObjectMap, copyObjects, clipboardOperationHelper);
  }
  
  @Override
  public String getID(EObject eObject) {
    String id = super.getID(eObject);
    Resource eResource = eObject.eResource();
    if(id == null && eResource !=  null && !(eResource instanceof XMLResource)) {
      id =  eResource.getURIFragment(eObject);
    }
    return id;
  }
}