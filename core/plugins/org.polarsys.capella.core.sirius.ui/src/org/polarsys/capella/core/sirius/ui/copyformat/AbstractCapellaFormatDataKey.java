/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.copyformat;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;
import org.polarsys.capella.shared.id.handler.IdManager;

/**
 * Specific key allowing to know the semantic {@link EObject}.
 * 
 */
public class AbstractCapellaFormatDataKey implements FormatDataKey {

  public static AbstractCapellaFormatDataKey INVALID_KEY = null;

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
  public AbstractCapellaFormatDataKey(EObject object) {
    _semantic = object;
  }

  @Override
  public String getId() {
    return getId(getSemantic());
  }

  public String getId(Object object_) {
    if (object_ instanceof EObject) {
      if (object_ instanceof EClass) {
        return ((EClass) object_).getName();
      }
      String id = IdManager.getInstance().getId((EObject) object_);
      if (id != null) {
        return id;
      }

    }
    return String.valueOf(object_.toString());
  }

  @Override
  public boolean equals(Object obj) {
    return (obj instanceof AbstractCapellaFormatDataKey) && (hashCode() == obj.hashCode());
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
