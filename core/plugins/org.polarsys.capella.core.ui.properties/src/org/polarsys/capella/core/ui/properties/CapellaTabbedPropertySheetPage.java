/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
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
    if (!getControl().isDisposed() && isValidSelection()) {
      super.labelProviderChanged(event);
    }
  }

  protected boolean isValidSelection() {
    if(getCurrentSelection() instanceof IStructuredSelection) {
      Object firstElement = ((IStructuredSelection)getCurrentSelection()).getFirstElement();
      return firstElement instanceof EObject && ((EObject)firstElement).eResource() != null;
    }
    return false;
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
