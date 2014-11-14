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
package org.polarsys.capella.core.platform.sirius.clipboard.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.clipboard.core.ClipboardUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * A clipboard for copy/paste in Capella Diagrams.
 * This clipboard extends the capabilities of the default GMF clipboard
 * - it stores the String data provided by the GMF clipboard;
 * - it remembers the original Sirius and GMF elements in order
 *    + to build a new layout based on the original layout;
 *    + to include the semantic layer in the paste operation.
 * It is an independent, separate clipboard so that graphical elements
 * actually correspond to the selected semantic elements.
 */
public class CapellaDiagramClipboard {
  
  // STATIC PART (Singleton pattern)
  
  private static final CapellaDiagramClipboard INSTANCE = new CapellaDiagramClipboard();
  
  public static CapellaDiagramClipboard getInstance() {
    return INSTANCE;
  }
  
  
  // INSTANCE PART
  
  // The GMF elements which are selected for copy
  private final List<View> _gmfElements;
  
  // The Sirius elements which are selected for copy
  private final List<DSemanticDecorator> _siriusElements;
  
  // A string representation of the Sirius elements in the GMF copy/paste mechanism
  private String _data;

  
  /**
   * Basic constructor
   */
  protected CapellaDiagramClipboard() {
    _data = null;
    _gmfElements = new ArrayList<View>();
    _siriusElements = new ArrayList<DSemanticDecorator>();
  }

  
  /**
   * The copy operation
   * @param toCopy_p the GMF views to copy
   */
  public void copy(List<? extends View> toCopy_p) {
    assert toCopy_p != null;
    clear();
    // Remember GMF original elements
    _gmfElements.addAll(toCopy_p);
    // Remember Sirius original elements
    _siriusElements.addAll(LayerUtil.toSirius(_gmfElements));
    // Use the GMF mechanism to copy the Sirius elements if available
    List<EObject> toSerialize = new ArrayList<EObject>();
    if (!_siriusElements.isEmpty())
      toSerialize.addAll(_siriusElements);
    else
      toSerialize.addAll(_gmfElements); // Case of GMF Note
    _data = ClipboardUtil.copyElementsToString(toSerialize, null, null);
  }
  
  /**
   * Clear the clipboard, which results in isEmpty() being true
   */
  public void clear() {
    _data = null;
    _siriusElements.clear();
    _gmfElements.clear();
  }
  
  /**
   * Return whether the clipboard has data to paste
   */
  public boolean isEmpty() {
    // For the clipboard to be considered non-empty, data must be present
    // at all layers
    return _data == null || _siriusElements.isEmpty() || _gmfElements.isEmpty();
  }

  /**
   * Return the (serialized) Sirius part of the clipboard's contents
   */
  public String getSiriusData() {
    return _data;
  }
  
  /**
   * Return the Sirius part of the clipboard's contents
   */
  public List<DSemanticDecorator> getSiriusElements() {
    return Collections.unmodifiableList(_siriusElements);
  }

  /**
   * Return the GMF part of the clipboard's contents
   */
  public List<View> getGmfElements() {
    return Collections.unmodifiableList(_gmfElements);
  }
  
}
