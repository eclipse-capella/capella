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
package org.polarsys.capella.core.libraries.model;

import org.eclipse.emf.common.util.URI;
import org.polarsys.capella.common.libraries.IModelIdentifier;

public class ModelIdentifier implements IModelIdentifier {

  String identifier = null;

  URI uri = null;

  public ModelIdentifier(String identifier_p, URI uri_p) {
    super();
    identifier = identifier_p;
    uri = uri_p;
  }

  @Override
  public int hashCode() {
    return uri.hashCode();
  }
  
  @Override
  public boolean equals(Object object_p) {
    if (object_p instanceof ModelIdentifier) {
      return uri.equals(((ModelIdentifier) object_p).uri);
    }
    return false;
  }

  @Override
  public String getId() {
    return identifier;
  }

  public URI getUri() {
    return uri;
  }

  @Override
  public String toString() {
    return uri.toPlatformString(false);
  }

  @Override
  public String getName() {
    return URI.decode(uri.lastSegment());
  }
}
