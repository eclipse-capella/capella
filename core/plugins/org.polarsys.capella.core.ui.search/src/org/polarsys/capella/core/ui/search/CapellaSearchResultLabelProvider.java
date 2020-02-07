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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.eclipse.ui.navigator.NavigatorContentServiceFactory;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

public class CapellaSearchResultLabelProvider extends LabelProvider implements IStyledLabelProvider {
  public static final Styler HIGHLIGHT_MATCHED_TEXT_STYLE = StyledString.createColorRegistryStyler(null,
      "org.eclipse.search.ui.match.highlight"); 

  private final ILabelProvider capellaNavigatorLabelProvider;

  private final CapellaSearchResultPage capellaSearchResultPage;

  public CapellaSearchResultLabelProvider(CapellaSearchResultPage capellaSearchResultPage) {
    this.capellaSearchResultPage = capellaSearchResultPage;

    INavigatorContentService capellaNavigatorContentService = NavigatorContentServiceFactory.INSTANCE
        .createContentService(CapellaCommonNavigator.ID);
    capellaNavigatorLabelProvider = capellaNavigatorContentService.createCommonLabelProvider();
  }

  @Override
  public String getText(Object element) {
    return getStyledText(element).getString();
  }

  @Override
  public Image getImage(Object element) {
    if (element instanceof CapellaSearchMatchEntry) {
      return Activator.getDefault().getImage("line_match.png");
    }
    return capellaNavigatorLabelProvider.getImage(element);
  }

  @Override
  public StyledString getStyledText(Object element) {
    StyledString str = new StyledString();

    str.append(getLabelText(element));
    str.append(" "); 

    if (element instanceof EObject) {
      boolean isLockedByOther = CapellaReadOnlyHelper.getReadOnlySectionHandler().isLockedByOthers((EObject) element);
      if (isLockedByOther) {
        str.append("(Read-Only)", StyledString.DECORATIONS_STYLER); 
      }
    }
    return str;
  }

  public StyledString getLabelText(Object element) {
    StyledString labelText = new StyledString(""); 
    if (element instanceof CapellaSearchMatchEntry) {
      CapellaSearchMatchEntry capellaSearchMatchEntry = (CapellaSearchMatchEntry) element;
      EAttribute attribute = (EAttribute) capellaSearchMatchEntry.getAttribute();
      labelText.append(attribute.getName());
      labelText.append(": ");
      labelText.append(capellaSearchMatchEntry.getText());
    }
    else {
      labelText.append(capellaNavigatorLabelProvider.getText(element));
    }
    return labelText;
  }
}
