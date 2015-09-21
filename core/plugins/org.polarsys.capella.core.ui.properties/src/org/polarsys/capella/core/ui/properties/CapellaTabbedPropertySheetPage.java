/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties;

import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 */
public class CapellaTabbedPropertySheetPage extends TabbedPropertySheetPage {

  /**
   * @param editor
   */
  public CapellaTabbedPropertySheetPage(ITabbedPropertySheetPageContributor editor) {
    super(editor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void init(IPageSite pageSite) {
    super.init(pageSite);
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage#dispose()
   */
  @Override
  public void dispose() {
    super.dispose();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void labelProviderChanged(LabelProviderChangedEvent event) {
    if (!getControl().isDisposed()) {
      super.labelProviderChanged(event);
    }
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage#refresh()
   */
  @Override
  public void refresh() {
    if (getCurrentTab() != null) {
      super.refresh();
    }
  }
}
