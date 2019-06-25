/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.view;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.CapellaPatternFilter;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

public class CapellaCommonNavigatorPatternFilter extends CapellaPatternFilter {
  boolean isSearchInDescriptionEnabled = false;

  public void setSearchInDescriptionEnabled(boolean isSearchInDescriptionEnabled) {
    this.isSearchInDescriptionEnabled = isSearchInDescriptionEnabled;
  }

  @Override
  protected String getText(Viewer viewer, Object element) {
    String text = super.getText(viewer, element);
    if (isSearchInDescriptionEnabled) {
      if (element instanceof CapellaElement) {
        text += " ";
        text += ((CapellaElement) element).getDescription();
      }
      // TODO: if in the next release of sirius, documentation attribute is moved to DRepresentationDescriptor,
      // we can simplify here
      if (element instanceof DRepresentationDescriptor) {
        DRepresentation representation = ((DRepresentationDescriptor) element).getRepresentation();
        if (representation != null) {
          text += " ";
          text += representation.getDocumentation();
        }
      }
      if (element instanceof DRepresentation) {
        text += " ";
        text += ((DRepresentation) element).getDocumentation();
      }
    }
    return text;
  }
}
