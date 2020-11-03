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

import java.util.WeakHashMap;


/**
 * A navigable bijection between original model elements and their copies
 * from the clipboard.
 */
public class SharedCopyPasteElements {
  /**
   * Singleton instance.
   */
  private static SharedCopyPasteElements __singleton;
  
  /** A map from copies to original elements */
  private WeakHashMap<Object, Object> _copiesToOriginals;

  /** A map from original elements to copies */
  private WeakHashMap<Object, Object> _originalsToCopies;
  
  
  /**
   * Constructor.
   */
  private SharedCopyPasteElements() {
    // Prevents instantiation.
    _copiesToOriginals = new WeakHashMap<Object, Object>(0);
    _originalsToCopies = new WeakHashMap<Object, Object>(0);
  }
  
  /**
   * Get singleton.
   * @return a not <code>null</code> instance.
   */
  public static SharedCopyPasteElements getInstance() {
    if (null == __singleton) {
      __singleton = new SharedCopyPasteElements();
    }
    return __singleton;
  }
  
  /**
   * Get the copy object from its original.
   * @param originalObject a potentially null object
   * @return a potentially null object
   */
  public Object getCopyObject(Object originalObject) {
    return _originalsToCopies.get(originalObject);
  }

  /**
   * Get the original object from its copy.
   * @param copiedObject
   * @return
   */
  public Object getOriginalObject(Object copiedObject) {
    return _copiesToOriginals.get(copiedObject);
  }

  /**
   * Get the the Paste copy of a previous copied object in the clipboard.<br>
   * The paste copy is removed from internal resource.
   * @param pasteCopyObject
   * @return
   */
  public Object getPasteCopyOfCopiedObject(Object pasteCopyObject) {
    Object result = _copiesToOriginals.remove(pasteCopyObject);
    if (result != null)
      _originalsToCopies.remove(result);
    return result;
  }

  /**
   * Put a copied element and its original representation.
   * @param copiedElement
   * @param originalObject
   */
  public void put(Object copiedElement, Object originalObject) {
    _copiesToOriginals.put(copiedElement, originalObject);
    _originalsToCopies.put(originalObject, copiedElement);
  }

  /**
   * Clear.
   */
  public void clear() {
    _copiesToOriginals.clear();
    _originalsToCopies.clear();
  }
}
