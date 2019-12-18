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
package org.polarsys.capella.core.ui.search;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
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
      "org.eclipse.search.ui.match.highlight"); //$NON-NLS-1$

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
    if (element instanceof CapellaSearchMatch) {
      return Activator.getDefault().getImage("line_match.png"); //$NON-NLS-1$
    }
    return capellaNavigatorLabelProvider.getImage(element);
  }

  @Override
  public StyledString getStyledText(Object element) {
    StyledString str = new StyledString();

    str.append(getLabelText(element));
    str.append(" "); //$NON-NLS-1$
    str.append(getCountText(element));
    str.append(" "); //$NON-NLS-1$

    if (element instanceof EObject) {
      // boolean isControllable = CapellaReadOnlyHelper.getReadOnlySectionHandler().isControllable((EObject) element);
      boolean isLockedByOther = CapellaReadOnlyHelper.getReadOnlySectionHandler().isLockedByOthers((EObject) element);
      if (isLockedByOther) {
        str.append("(Read-Only)", StyledString.DECORATIONS_STYLER); //$NON-NLS-1$
      }
    }

    return str;
  }

  public StyledString getLabelText(Object element) {
    StyledString labelText = new StyledString(""); //$NON-NLS-1$
    if (element instanceof CapellaSearchMatch) {
      CapellaSearchMatch capellaSearchMatch = (CapellaSearchMatch) element;

      // matched by search field
      labelText.append(capellaSearchMatch.getSearchField().getLabel(), StyledString.DECORATIONS_STYLER);
      labelText.append(" "); //$NON-NLS-1$

      // matched at line number
      int lineNumber = capellaSearchMatch.getLineNumber();
      labelText.append(String.valueOf(lineNumber), StyledString.QUALIFIER_STYLER);
      labelText.append(": "); //$NON-NLS-1$

      // matched content
      StyledString lineContent = new StyledString(capellaSearchMatch.getLineContent());
      for (CapellaSearchMatchOccurrence matchOccurence : capellaSearchMatch.getMatchOccurrences()) {
        // styling only matched occurrences
        int offset = matchOccurence.getOffset();
        int len = matchOccurence.getLength();
        lineContent.setStyle(offset, len, HIGHLIGHT_MATCHED_TEXT_STYLE);
      }

      labelText.append(lineContent);
    } else {
      labelText.append(capellaNavigatorLabelProvider.getText(element));
    }
    return labelText;
  }

  public StyledString getCountText(Object element) {
    int displayedCount = 0;
    int totalCount = 0;
    if (element instanceof CapellaSearchMatch) {
      displayedCount = ((CapellaSearchMatch) element).getMatchOccurrences().size();
      totalCount = displayedCount;
    } else {
      CapellaSearchResult capellaSearchResult = capellaSearchResultPage.getInput();
      if (element instanceof IProject) {
        displayedCount = capellaSearchResult.getDisplayedOccurrenceCount((IProject) element);
        totalCount = capellaSearchResult.getOccurrenceCount((IProject) element);
      } else {
        displayedCount = capellaSearchResult.getDisplayedOccurrenceCount(element);
        totalCount = capellaSearchResult.getOccurrenceCount(element);
      }
    }
    StyledString styledString = new StyledString();
    if (totalCount > 0) {
      int filteredCount = totalCount - displayedCount;
      Object pluralSuffix = (totalCount > 1) ? "s" : "";
      String countText = (filteredCount > 0)
          ? String.format(Messages.CapellaSearchResult_Occurrences_Count_Label_With_Active_Filters, totalCount,
              pluralSuffix, filteredCount)
          : String.format(Messages.CapellaSearchResult_Occurrences_Count_Label, totalCount, pluralSuffix);

      styledString.append(countText, StyledString.COUNTER_STYLER);
    }
    return styledString;
  }
}
