/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.libraries.model;

import org.eclipse.emf.common.util.URI;
import org.polarsys.capella.common.libraries.IModelIdentifier;

public class ModelIdentifier implements IModelIdentifier {

  String identifier = null;
  URI uri = null;
  boolean isProxy = false;

  public ModelIdentifier(String paramIdentifier, URI paramUri) {
    super();
    this.identifier = paramIdentifier;
    this.uri = paramUri;
  }

  public ModelIdentifier(String paramIdentifier, URI paramUri, boolean isProxy) {
    super();
    this.identifier = paramIdentifier;
    this.uri = paramUri;
    this.isProxy = isProxy;
  }
  
  @Override
  public int hashCode() {
    return uri.hashCode();
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof ModelIdentifier) {
      return uri.equals(((ModelIdentifier) object).uri);
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
    return uri.toString();
  }

  @Override
  public String getName() {
    return URI.decode(uri.lastSegment());
  }
  
  public boolean isProxy() {
    return isProxy;
  }
}
