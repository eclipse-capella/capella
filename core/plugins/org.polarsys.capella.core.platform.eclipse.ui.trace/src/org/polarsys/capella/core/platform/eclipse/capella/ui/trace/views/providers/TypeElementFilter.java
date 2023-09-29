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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.Messages;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.TraceNameHelper;

/**
 *
 */
public class TypeElementFilter extends ViewerFilter {

  private String _comboBoxValue;
  private ResourceSet context;

  /**
   * 
   */
  public TypeElementFilter(ResourceSet context) {
    this.context = context;
  }

  /**
   * @param comboBoxValue_p
   *          the comboBoxValue to set
   */
  public void setComboBoxValue(String comboBoxValue_p) {
    _comboBoxValue = comboBoxValue_p;
  }

  /**
   * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object,
   *      java.lang.Object)
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public boolean select(Viewer viewer_p, Object parentElement_p, Object element_p) {
    if (_comboBoxValue.equalsIgnoreCase(Messages.getString("TraceTreeViewer.all_traces"))) { //$NON-NLS-1$
    	return true;
    }
    if (element_p instanceof Class) {
      if (TraceNameHelper.getTraceNameFromClass((Class) element_p, context).equals(_comboBoxValue)) {
        return true;
      }
      return false;
    }
    return true;

  }

}
