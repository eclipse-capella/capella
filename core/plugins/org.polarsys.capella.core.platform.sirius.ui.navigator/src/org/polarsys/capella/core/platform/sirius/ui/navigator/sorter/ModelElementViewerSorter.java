/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.platform.sirius.ui.navigator.sorter;

import java.text.Collator;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

/**
 * The model element viewer sorter.<br>
 * Actually, no sort is performed by this sorter but if we don't give a sorter, elements are alphabetically sorted by default.
 */
public class ModelElementViewerSorter extends ViewerSorter {

  /**
   * Constructs the model element viewer sorter.
   */
  public ModelElementViewerSorter() {
    // Do nothing.
  }

  /**
   * Constructs the model element viewer sorter with the specified collator.
   * @param collator_p The collator.
   */
  public ModelElementViewerSorter(Collator collator_p) {
    super(collator_p);
  }

  /**
   * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(Viewer viewer_p, Object object1_p, Object object2_p) {
    // With 0, no order is given, no sort is performed.
    return 0;
  }
}
