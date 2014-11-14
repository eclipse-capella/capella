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
package org.polarsys.capella.common.model.copypaste;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

/**
 */
public class SharedCutPasteClipboard {
  /**
   * Singleton instance.
   */
  private static SharedCutPasteClipboard __singleton;

  private Collection<Object> _clipboard;

  /**
   * Constructor.
   */
  private SharedCutPasteClipboard() {
    // Avoid to instantiate.
  }

  /**
   * retrieve the instance of the cutClipboard
   * @return the cutClipboard
   */
  public static SharedCutPasteClipboard getCutClipboard() {
    if (null == __singleton) {
      __singleton = new SharedCutPasteClipboard();
    }
    return __singleton;
  }

  /**
   * Clear the clipboard.
   */
  public void clear() {
    _clipboard = null;
  }

  /**
   * Has cut objects i.e clipboard is not <code>null</code>
   * @return
   */
  public boolean hasCut() {
    return _clipboard != null;
  }

  /**
   * Get the clipboard.
   * @return
   */
  public Collection<?> getClipboard() {
    return _clipboard;
  }

  /**
   * Set clipboard content.
   * @param selection_p
   */
  public void setClipboard(Collection<?> selection_p) {
    clear();
    if (null == selection_p) {
      return;
    }
    _clipboard = new ArrayList<Object>(selection_p);
  }

  /**
   * Is given object a cut one ?
   * @param cutElement_p
   * @return <code>true</code> means given object is cut.
   */
  public boolean isObjectCut(Object cutElement_p) {
    boolean result = false;
    if ((_clipboard == null) || !(cutElement_p instanceof EObject)) {
      return result;
    }
    if (_clipboard.contains(cutElement_p)) {
      result = true;
    } else {
      EObject cutElement = (EObject) cutElement_p;
      EObject cutElementContainer = cutElement.eContainer();
      if (null != cutElementContainer) {
        result = isObjectCut(cutElementContainer);
      }
    }
    return result;
  }
}
