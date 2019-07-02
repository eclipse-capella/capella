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
package org.polarsys.capella.common.ui.toolkit.widgets.filter;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.internal.misc.StringMatcher;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;

public class CapellaPatternFilter extends PatternFilter {
  private String pattern;

  StringMatcher matcher;

  private boolean caseSensitiveEnabled = false;

  protected String getText(Viewer viewer, Object element) {
    // By comparing with the previous implementation, using EObjectLabelProviderHelper for model element is much more
    // faster than label provider.
    // TODO: If for a given element, EObjectLabelProviderHelper and default labelProvider return 2 different texts, what
    // will happen? LabelProvider returns a text that is displayed to user but EObjectLabelProviderHelper returns the
    // text that matches the search, it is incoherent, isn't it?
    if (element instanceof EObject) {
      return EObjectLabelProviderHelper.getText(element);
    }
    return ((ILabelProvider) ((StructuredViewer) viewer).getLabelProvider()).getText(element);
  }

  @Override
  protected boolean isLeafMatch(Viewer viewer, Object element) {
    String labelText = getText(viewer, element);
    if (labelText == null) {
      return false;
    }
    return wordMatches(labelText);
  }

  @Override
  public void setPattern(String patternString) {
    super.setPattern(patternString);
    if (patternString != null) {
      this.pattern = patternString;
      
      // As the matcher of PatternFilter use by default "true" for "ignoreCase" and "false" for "ignoreWildCard"
      // We create a another matcher here so that we can provide search options for user.
      if (!patternString.endsWith(" ")) {
        // So that if search for "Air", the results will include texts containing "Aircraft" or "Airplane"
        patternString += "*";
      }
      matcher = new StringMatcher(patternString, !caseSensitiveEnabled, false);
    }
  }

  public String getPattern() {
    return pattern;
  }

  @Override
  protected boolean wordMatches(String text) {
    if (pattern == null || pattern.isEmpty()) {
      return true;
    }

    if (text == null) {
      return false;
    }

    return matcher.match(text);
  }

  public void setCaseSensitiveEnabled(boolean caseSensitiveEnabled) {
    this.caseSensitiveEnabled = caseSensitiveEnabled;
  }

}
