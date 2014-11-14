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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 *
 */
public class CapellaDecoratorLayoutDataKey extends AbstractCapellaLayoutDataKey {

  AbstractCapellaLayoutDataKey _parent;

  Collection<EObject> _decorations;

  @Override
  public String getId() {
    String result = getId(getSemantic());

    if (getDecorations() != null) {
      for (EObject decoration : getDecorations()) {
        if (decoration != null) {
          result += ICommonConstants.COMMA_CHARACTER + getId(decoration);
        }
      }
    }

    return result;
  }

  /**
   * @param object_p
   */
  public CapellaDecoratorLayoutDataKey(AbstractCapellaLayoutDataKey key_p) {
    super(key_p.getSemantic());
    _parent = key_p;
  }

  /**
   * @return the decorations
   */
  public Collection<EObject> getDecorations() {
    return _decorations;
  }

  /**
   * @return the parent
   */
  public AbstractCapellaLayoutDataKey getParent() {
    return _parent;
  }

  protected void addDecoration(EObject object_p) {
    if (getDecorations() == null) {
      _decorations = new ArrayList<EObject>();
    }
    getDecorations().add(object_p);
  }

  @Override
  public int hashCode() {
    int result = 17;
    EObject semantic = getSemantic();
    if (semantic != null) {
      result = 37 * result + semantic.hashCode();
    }
    if (getDecorations() != null) {
      for (Object decoration : getDecorations()) {
        if (decoration != null) {
          result = 37 * result + decoration.hashCode();
        }
      }
    }
    return result;
  }

}
