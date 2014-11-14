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
package org.polarsys.capella.core.sirius.ui.copylayout;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataKey;
import org.polarsys.capella.shared.id.handler.IdManager;

/**
 * Specific key allowing to know the semantic {@link EObject}.
 * 
 */
public class AbstractCapellaLayoutDataKey implements LayoutDataKey {

  public static AbstractCapellaLayoutDataKey INVALID_KEY = null;

  protected EObject _semantic;

  public EObject getSemantic() {
    return _semantic;
  }

  /**
   * Default constructor.
   * 
   * @param key
   *            The key
   */
  public AbstractCapellaLayoutDataKey(EObject object_p) {
    _semantic = object_p;
  }

  @Override
  public String getId() {
    return getId(getSemantic());
  }

  public String getId(Object object_p) {
    if (object_p instanceof EObject) {
      if (object_p instanceof EClass) {
        return ((EClass) object_p).getName();
      }
      String id = IdManager.getInstance().getId((EObject) object_p);
      if (id != null) {
        return id;
      }

    }
    return String.valueOf(object_p.toString());
  }

  @Override
  public boolean equals(Object obj) {
    return (obj instanceof AbstractCapellaLayoutDataKey) && (hashCode() == obj.hashCode());
  }

  @Override
  /**
   * Must returns a unique hashCode. Equals method is based on it
   */
  public int hashCode() {
    int result = 17;
    EObject semantic = getSemantic();
    if (semantic != null) {
      result = (37 * result) + getId(semantic).hashCode();
    }
    return result;
  }
}
