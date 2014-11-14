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
package org.polarsys.capella.core.platform.sirius.ui.navigator.sorter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;

/**
 * Used to put Project or SystemEngineering at the first place under the .aird file.
 */
public class AirdContentSorter extends ViewerSorter {
  /**
   * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(Viewer viewer_p, Object object1_p, Object object2_p) {
    if ((object1_p instanceof Project) || (object1_p instanceof SystemEngineering)) {
      // A Project or a SystemEngineering is lesser than any object.
      return -1;
    }
    if ((object2_p instanceof Project) || (object2_p instanceof SystemEngineering)) {
      // Any object is greater than a Project or a SystemEngineering.
      return 1;
    }
    return 0;
  }
}
