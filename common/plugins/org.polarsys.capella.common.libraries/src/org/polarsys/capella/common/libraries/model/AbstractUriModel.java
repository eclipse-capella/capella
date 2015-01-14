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
package org.polarsys.capella.common.libraries.model;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.IModel;

public abstract class AbstractUriModel implements IModel {

  protected URI uriSemanticFile;

  public AbstractUriModel(URI uriSemanticFile_p) {
    uriSemanticFile = uriSemanticFile_p;
  }

  @Override
  public AccessPolicy getDefaultAccess(IModel referencedLibrary_p) {
    return AccessPolicy.READ_AND_WRITE;
  }

  @Override
  public boolean getDefaultActiveState(IModel library) {
    return true;
  }

  @Override
  public String toString() {
    return uriSemanticFile.toString();
  }

  @Override
  public int hashCode() {
    return uriSemanticFile.hashCode();
  }

  @Override
  public boolean equals(Object object_p) {
    if (object_p instanceof AbstractUriModel) {
      return uriSemanticFile.equals(((AbstractUriModel) object_p).uriSemanticFile);
    }
    if (object_p instanceof Resource) {
      return uriSemanticFile.equals(((Resource) object_p).getURI());
    }
    return false;
  }

}
