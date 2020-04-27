/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.filters;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.extension.ModelExtensionManager;
import org.polarsys.capella.core.data.cs.Part;

/**
 */
public class PartFilter extends ViewerFilter {

    public Object[] filter(Viewer viewer, Object parent, Object[] elements) {
        int size = elements.length;
        List<Object> out = new ArrayList<Object>(size);
        
        for (int i = 0; i < size; ++i) {
            Object element = elements[i];
            if (doSelect(viewer, parent, element)) {
				out.add(element);
			}
        }
        return out.toArray();
    }

    private boolean doSelect(Viewer viewer, Object parent, Object element) {
        if (element instanceof Part) {
          Part part = (Part) element;
          return part.getAbstractType() == null || !ModelExtensionHelper.getInstance(part).isExtensionModelDisabled(part.getAbstractType());
        }
        return true;
      }

	/**
   * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  @Override
  public boolean select(Viewer viewer_p, Object parentElement_p, Object element_p) {
    return true;
  }
}
