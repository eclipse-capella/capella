/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.menu.dynamic.utils;

import java.util.Comparator;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;

/**
 */
public class ContributionItemComparator implements Comparator<IContributionItem> {
  /**
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   * @param item1_p the first compared element
   * @param item2_p the second compared element
   * @return the result of the comparison
   */
  public int compare(IContributionItem item1_p, IContributionItem item2_p) {
    String name1 = getText(item1_p);
    String name2 = getText(item2_p);
    if ((name1 == null) || (name2 == null)) {
      return 0;
    }
    return name1.compareTo(name2);
  }

  /**
   * Get text from given contribution item.<br>
   * Default implementation handles {@link ActionContributionItem} and {@link MenuManager}
   * @param item_p
   * @return <code>null</code> if item is not handled.
   */
  protected String getText(IContributionItem item_p) {
    String result = null;
    if (item_p instanceof ActionContributionItem) {
      result = ((ActionContributionItem) item_p).getAction().getText();
    } else if (item_p instanceof MenuManager) {
      result = ((MenuManager) item_p).getMenuText();
    }
    return result;
  }
}
