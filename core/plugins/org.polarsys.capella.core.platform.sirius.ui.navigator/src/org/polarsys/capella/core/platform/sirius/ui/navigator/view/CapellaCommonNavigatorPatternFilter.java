/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.view;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.CapellaPatternFilter;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;

public class CapellaCommonNavigatorPatternFilter extends CapellaPatternFilter {
  boolean isSearchInDescriptionEnabled = false;

  public void setSearchInDescriptionEnabled(boolean isSearchInDescriptionEnabled) {
    this.isSearchInDescriptionEnabled = isSearchInDescriptionEnabled;
  }

  @Override
  protected String getText(Viewer viewer, Object element) {
    if (isSearchInDescriptionEnabled) {
      return super.getText(viewer, element) + " " + getDescription(element);
    }
    return super.getText(viewer, element);
  }
  
  private String getDescription(Object element) {
    if (element instanceof CapellaElement) {
      return ((CapellaElement) element).getDescription();
      
    } else if (element instanceof DRepresentationDescriptor) {
      return ((DRepresentationDescriptor) element).getDocumentation();
      
    } else if (element instanceof DRepresentation) {
      return getDescription(RepresentationHelper.getRepresentationDescriptor((DRepresentation)element));
      
    } else if (element instanceof ItemWrapper) {
      return getDescription(((ItemWrapper) element).getWrappedObject());
    }
    return "";
  }
}
