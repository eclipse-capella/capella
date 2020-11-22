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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;

import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 */
public class CapellaModelFilter extends ViewerFilter implements ModifyListener {
  private Pattern _expPattern;
  private Viewer _viewer;

  /**
   * Constructor.
   */
  public CapellaModelFilter() {
    super();
  }

  /**
   * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  @Override
  public boolean select(Viewer viewer_p, Object parentElement_p, Object element_p) {
    if (_viewer == null || _viewer != viewer_p) {
      _viewer = viewer_p;
    }
    if (element_p instanceof TraceableElement) {
      if (element_p instanceof AbstractNamedElement) {
        AbstractNamedElement element = (AbstractNamedElement) element_p;
        String name = element.getName();
        return checkPattern(element_p, name);
      }
      return true;
    }
    return false;
  }

  @SuppressWarnings("nls")
  private boolean checkPattern(Object element_p, String name_p) {
    if (name_p == null || name_p.trim().equals("")) {
      return false;
    }

    if (_expPattern == null) {
      return true;
    }
    if (_viewer instanceof TreeViewer) {
      TreeViewer viewer = (TreeViewer) _viewer;
      return checkPattern(viewer, element_p, name_p);
    }
    Matcher matcher = _expPattern.matcher(name_p);
    if (_expPattern.pattern().startsWith("*")) { //$NON-NLS-1$
      return matcher.find();
    }
    return matcher.matches();
  }

  /**
   * @param viewer_p
   * @param element_p
   * @param name_p
   * @return
   */
  private boolean checkPattern(TreeViewer viewer_p, Object element_p, String name_p) {
    boolean flag = false;

    Object[] children = ((ITreeContentProvider) viewer_p.getContentProvider()).getChildren(element_p);
    // Gets and checks the current object label.

    Matcher matcher = _expPattern.matcher(name_p);
    if (_expPattern.pattern().startsWith(".*")) { //$NON-NLS-1$
        flag = matcher.find();
    } else {
        flag = matcher.matches();
    }
    if (children != null) {
      for (Object obj : children) {
        if (element_p instanceof AbstractNamedElement) {
          AbstractNamedElement element = (AbstractNamedElement) element_p;
          String name = element.getName();
          if (name != null) {
            matcher = _expPattern.matcher(name);
            if (_expPattern.pattern().startsWith(".*") && !flag) { //$NON-NLS-1$
              flag = matcher.find();
            }
            if (!flag)
              flag = matcher.matches();
            if (flag)
              return true;
            flag = checkPattern(viewer_p, obj, name);
          }
        }
      }
    }
    return flag;
  }

  @SuppressWarnings("nls")
  public void modifyText(ModifyEvent modifyEvent) {
    Text txt = (Text) modifyEvent.widget;
    String value = txt.getText();
    if (0 == value.length()) {
      _expPattern = null;
      if (_viewer != null) {
        _viewer.refresh();
      }
      return;
    }

    if (value.contains(".")) {
      value = value.replace(".", "[.]");
    }

    if (value.contains("\\") && !(value.endsWith("\\"))) {
      int spos = 0, epos = 0;
      while ((spos = value.indexOf("\\", epos)) != -1) {
        value = value.substring(epos, spos) + "[" + value.charAt(spos + 1) + "]" + value.substring(spos + 2);
        epos = spos + 2;
      }
    }

    value = value.replaceAll("[*]+", "*"); //$NON-NLS-1$//$NON-NLS-2$

    // In regular expression domain, the '*' character is considered as
    // a suffix because it 's a greedy quantifier.
    // Because of this, if the '*' is encountered near the index 0, the
    // pattern mechanism cannot validate the regular expression.
    // To resolve this trouble, we replace all the '*' character with
    // the '.*' regular expression which means 'any string'.
    // make difference between wildcards and real '*'
    // character.
    value = value.replaceAll("^\\[*]", ".*"); //$NON-NLS-1$//$NON-NLS-2$

    if (value.contains("[")) {
      int spos = 0; 
      int epos = 0;
      while ((spos = value.indexOf("[", epos)) != -1) {
        value = value.substring(epos, value.indexOf("[", epos)).replace("*", ".*") + value.substring(spos);
        epos = value.indexOf("]", spos);
      }
      value = value.substring(0, value.lastIndexOf("]")) + value.substring(value.lastIndexOf("]")).replace("*", ".*");
    } else {
      value = value.replace("*", ".*");
    }


    // In regular expression domain, the '?' character is considered as
    // a suffix because it 's a greedy quantifier.
    // Because of this, if the '?' is encountered near the index 0, the
    // pattern mechanism cannot validate the regular expression.
    // To resolve this trouble, we replace the '?' character with the
    // '.' regular expression which means 'any character'.
    // make difference between wildcards and real '?'
    // character.
    value = value.replaceAll("^\\[\\?]", "."); //$NON-NLS-1$ //$NON-NLS-2$

    if (value.contains("[")) {
      int spos = 0, epos = 0;
      while ((spos = value.indexOf("[", epos)) != -1) {
        value = value.substring(epos, value.indexOf("[", epos)).replace("?", ".") + value.substring(spos);
        epos = value.indexOf("]", spos);
      }
      value = value.substring(0, value.lastIndexOf("]")) + value.substring(value.lastIndexOf("]")).replace("?", ".");
    } else {
      value = value.replace("?", ".");
    }

    // Adds a '.*' regular expression which means 'any string' in order
    // to match all elements which start with the text field user entry.
    value = value.concat(".*"); //$NON-NLS-1$

    try {
      // Compiles the regular expression with case insensitive mode.
      _expPattern = Pattern.compile(value, Pattern.CASE_INSENSITIVE);
    } catch (RuntimeException exception) {
      //
    }
    if (_viewer != null) {
      _viewer.setSelection(null);
      _viewer.refresh();
    }
  }
}
