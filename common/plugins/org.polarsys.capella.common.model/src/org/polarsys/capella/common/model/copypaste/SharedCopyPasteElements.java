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
   * @param originalObject_p a potentially null object
   * @return a potentially null object
   */
  public Object getCopyObject(Object originalObject_p) {
    return _originalsToCopies.get(originalObject_p);
  }

  /**
   * Get the original object from its copy.
   * @param copiedObject_p
   * @return
   */
  public Object getOriginalObject(Object copiedObject_p) {
    return _copiesToOriginals.get(copiedObject_p);
  }

  /**
   * Get the the Paste copy of a previous copied object in the clipboard.<br>
   * The paste copy is removed from internal resource.
   * @param pasteCopyObject_p
   * @return
   */
  public Object getPasteCopyOfCopiedObject(Object pasteCopyObject_p) {
    Object result = _copiesToOriginals.remove(pasteCopyObject_p);
    if (result != null)
      _originalsToCopies.remove(result);
    return result;
  }

  /**
   * Put a copied element and its original representation.
   * @param copiedElement_p
   * @param originalObject_p
   */
  public void put(Object copiedElement_p, Object originalObject_p) {
    _copiesToOriginals.put(copiedElement_p, originalObject_p);
    _originalsToCopies.put(originalObject_p, copiedElement_p);
  }

  /**
   * Clear.
   */
  public void clear() {
    _copiesToOriginals.clear();
    _originalsToCopies.clear();
  }
}
