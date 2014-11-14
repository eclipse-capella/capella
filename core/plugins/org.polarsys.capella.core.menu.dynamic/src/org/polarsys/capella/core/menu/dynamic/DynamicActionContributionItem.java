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
package org.polarsys.capella.core.menu.dynamic;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;

/**
 * Dynamic contribution item for {@link IAction}.
 */
public class DynamicActionContributionItem extends ActionContributionItem {
  /**
   * Constructor.
   * @param action_p
   */
  public DynamicActionContributionItem(IAction action_p) {
    super(action_p);
  }

  /**
   * @see org.eclipse.jface.action.ContributionItem#isDirty()
   */
  @Override
  public boolean isDirty() {
    return true;
  }

  /**
   * @see org.eclipse.jface.action.ActionContributionItem#isDynamic()
   */
  @Override
  public boolean isDynamic() {
    return true;
  }

  /**
   * Return the text label of the underlying action.
   * @return
   */
  public String getText() {
    return getAction().getText();
  }
}
