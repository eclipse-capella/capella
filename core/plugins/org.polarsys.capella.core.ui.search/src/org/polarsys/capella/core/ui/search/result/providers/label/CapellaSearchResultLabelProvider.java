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
package org.polarsys.capella.core.ui.search.result.providers.label;

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
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.ui.search.CapellaSearchConstants;
import org.polarsys.capella.core.ui.search.match.SearchMatch;
import org.polarsys.capella.core.ui.search.match.SearchMatchChild;

/*
 * class used to format the result displayed in the search result
 */
@SuppressWarnings("restriction")
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
    if (element instanceof SearchMatchChild) {
      return ((SearchMatchChild) element).getDisplayText();
    } else if (element instanceof SearchMatch) {
      SearchMatch capellaSearchMatchEntry = (SearchMatch) element;
      EAttribute attribute = (EAttribute) capellaSearchMatchEntry.getAttribute();
      if (capellaSearchMatchEntry.getChildren().isEmpty()) {
        return attribute.getName() + ": " + capellaSearchMatchEntry.getDisplayText();
      }
      return attribute.getName();
    } else if (element instanceof Shape && ViewType.NOTE.equals(((Shape) element).getType())) {
      return CapellaSearchConstants.Note_Label;
    }
    return capellaNavigatorLabelProvider.getText(element);
  }

  @Override
  public Image getImage(Object element) {
    if (element instanceof SearchMatchChild) {
      return null;
    } else if (element instanceof SearchMatch) {
      return AbstractUIPlugin.imageDescriptorFromPlugin(FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), "icons/line_match.png").createImage();
    } else if (element instanceof Shape && ViewType.NOTE.equals(((Shape) element).getType())) {
      return ExtendedImageRegistry.INSTANCE
          .getImage(DiagramUIPlugin.getInstance().getBundle().getEntry("icons/note.gif"));
    }
    return capellaNavigatorLabelProvider.getImage(element);
  }
}
