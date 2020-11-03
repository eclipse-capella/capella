/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
   * @param selection
   */
  public void setClipboard(Collection<?> selection) {
    clear();
    if (null == selection) {
      return;
    }
    _clipboard = new ArrayList<Object>(selection);
  }

  /**
   * Is given object a cut one ?
   * @param cutElement
   * @return <code>true</code> means given object is cut.
   */
  public boolean isObjectCut(Object cutElement) {
    boolean result = false;
    if ((_clipboard == null) || !(cutElement instanceof EObject)) {
      return result;
    }
    if (_clipboard.contains(cutElement)) {
      result = true;
    } else {
      EObject cutElt = (EObject) cutElement;
      EObject cutElementContainer = cutElt.eContainer();
      if (null != cutElementContainer) {
        result = isObjectCut(cutElementContainer);
      }
    }
    return result;
  }
}
