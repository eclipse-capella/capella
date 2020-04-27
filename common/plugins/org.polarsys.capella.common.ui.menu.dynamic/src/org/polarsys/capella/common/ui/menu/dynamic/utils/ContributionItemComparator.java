/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.ui.menu.dynamic.utils;

import java.util.Comparator;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;

/**
 */
public class ContributionItemComparator implements Comparator<IContributionItem> {
  /**
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   * @param item1 the first compared element
   * @param item2 the second compared element
   * @return the result of the comparison
   */
  public int compare(IContributionItem item1, IContributionItem item2) {
    String name1 = getText(item1);
    String name2 = getText(item2);
    if ((name1 == null) || (name2 == null)) {
      return 0;
    }
    return name1.compareTo(name2);
  }

  /**
   * Get text from given contribution item.<br>
   * Default implementation handles {@link ActionContributionItem} and {@link MenuManager}
   * @param item
   * @return <code>null</code> if item is not handled.
   */
  protected String getText(IContributionItem item) {
    String result = null;
    if (item instanceof ActionContributionItem) {
      result = ((ActionContributionItem) item).getAction().getText();
    } else if (item instanceof MenuManager) {
      result = ((MenuManager) item).getMenuText();
    }
    return result;
  }
}
