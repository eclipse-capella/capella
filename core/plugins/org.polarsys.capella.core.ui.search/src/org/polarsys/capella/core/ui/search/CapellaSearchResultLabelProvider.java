/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.eclipse.ui.navigator.NavigatorContentServiceFactory;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

/*
 * class used to format the result displayed in the search result
 */
public class CapellaSearchResultLabelProvider extends LabelProvider {
  public static final Styler HIGHLIGHT_MATCHED_TEXT_STYLE = StyledString.createColorRegistryStyler(null,
      "org.eclipse.search.ui.match.highlight");

  private final ILabelProvider capellaNavigatorLabelProvider;

  public CapellaSearchResultLabelProvider() {
    INavigatorContentService capellaNavigatorContentService = NavigatorContentServiceFactory.INSTANCE
        .createContentService(CapellaCommonNavigator.ID);
    capellaNavigatorLabelProvider = capellaNavigatorContentService.createCommonLabelProvider();
  }

  @Override
  public String getText(Object element) {
    if (element instanceof CapellaSearchMatchEntry) {
      CapellaSearchMatchEntry capellaSearchMatchEntry = (CapellaSearchMatchEntry) element;
      EAttribute attribute = (EAttribute) capellaSearchMatchEntry.getAttribute();
      return attribute.getName() + ": " + capellaSearchMatchEntry.getText();
    } else if (element instanceof Shape && ViewType.NOTE.equals(((Shape) element).getType())) {
      return CapellaSearchConstants.Note_Label;
    }
    return capellaNavigatorLabelProvider.getText(element);
  }

  @SuppressWarnings("restriction")
  @Override
  public Image getImage(Object element) {
    if (element instanceof CapellaSearchMatchEntry) {
      return Activator.getDefault().getImage("line_match.png");
    } else if (element instanceof Shape && ViewType.NOTE.equals(((Shape) element).getType())) {
      return ExtendedImageRegistry.INSTANCE
          .getImage(DiagramUIPlugin.getInstance().getBundle().getEntry("icons/note.gif"));
    }
    return capellaNavigatorLabelProvider.getImage(element);
  }
}
