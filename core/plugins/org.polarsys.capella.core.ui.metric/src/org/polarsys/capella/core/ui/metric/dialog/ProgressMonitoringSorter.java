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
package org.polarsys.capella.core.ui.metric.dialog;

import java.text.Collator;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * Progress Monitoring viewer sorter.
 */
public class ProgressMonitoringSorter extends ViewerSorter {
  /**
   * Constructor.
   */
  public ProgressMonitoringSorter() {
    super();
  }

  /**
   * Constructor.
   * @param collator_p
   */
  public ProgressMonitoringSorter(Collator collator_p) {
    super(collator_p);
  }

  /**
   * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(Viewer viewer_p, Object object1_p, Object object2_p) {
    // Sorter Capella resource before capella fragment.
    if ((object1_p instanceof Resource) && (object2_p instanceof Resource)) {
      Resource resource1 = (Resource) object1_p;
      Resource resource2 = (Resource) object2_p;

      // Preconditions : must be capella resources.
      if (CapellaResourceHelper.isCapellaResource(resource1) && CapellaResourceHelper.isCapellaResource(resource2)) {
        int result = 0;
        boolean fragment1 = CapellaResourceHelper.isCapellaFragment(resource1.getURI());
        boolean fragment2 = CapellaResourceHelper.isCapellaFragment(resource2.getURI());

        if (fragment1 && !fragment2) {
          result = 1;
        } else if (!fragment1 && fragment2) {
          result = -1;
        }
        return result;
      }
    }
    return super.compare(viewer_p, object1_p, object2_p);
  }
}
