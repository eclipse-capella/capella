/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.search.searchfor.item;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.core.ui.search.CapellaSearchConstants;

public class SearchForDiagramItem extends SearchForClassItem {
  public SearchForDiagramItem(Object eClass) {
    super(eClass);
  }

  @Override
  public String getText() {
    return CapellaSearchConstants.Diagram_Label;
  }

  @Override
  public Image getImage() {
    return ExtendedImageRegistry.INSTANCE.getImage(DiagramUIPlugin.INSTANCE.getImage("full/obj16/DDiagram"));
  }
}
