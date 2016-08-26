/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.validation.ui;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.IWorkbenchContribution;
import org.eclipse.ui.services.IServiceLocator;

import org.polarsys.capella.common.ui.actions.ModelAdaptation;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * A wrapper to the ContributionItem used to create elements on Capella Specific case: we use the serviceLocator to retrieve selection made on the TreeViewer
 * instead of global selection
 */
public class DynamicCreateContributionItem extends CompoundContributionItem implements IWorkbenchContribution {

  IServiceLocator locator = null;

  class DynamicProvider extends DynamicActionProvider {

    IContributionItem getItem() {
      initActions(null, null, new ISelectionProvider() {

        @Override
        public void setSelection(ISelection selection) {
          // Nothing here
        }

        @Override
        public void removeSelectionChangedListener(ISelectionChangedListener listener) {
          // Nothing here
        }

        @Override
        public ISelection getSelection() {
          ISelectionService selectionService = (ISelectionService) locator.getService(ISelectionService.class);
          final ISelection selection = selectionService.getSelection();
          ModelElement element = getSelectedElement(selection);

          if (element == null) {
            return selectionService.getSelection();
          }

          return new StructuredSelection(element);
        }

        @Override
        public void addSelectionChangedListener(ISelectionChangedListener listener) {
          // Nothing here
        }
      });

      IContributionItem item = createContributionItem();
      return item;
    }
  }

  /**
   * Get model element form current selection.
   * @param _selection
   * @return could be <code>null</code>
   */
  protected ModelElement getSelectedElement(Object _selection) {
    ModelElement element = ModelAdaptation.adaptToCapella(_selection);
    // if ((element == null) && (_selection instanceof IAdaptable)) {
    // Object dnodeElement = ((IAdaptable) _selection).getAdapter(EObject.class);
    // element = (ModelElement) Platform.getAdapterManager().getAdapter(dnodeElement, ModelElement.class);
    // }

    return element;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initialize(IServiceLocator serviceLocator) {
    locator = serviceLocator;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IContributionItem[] getContributionItems() {
    IContributionItem item = new DynamicProvider().getItem();
    return new IContributionItem[] { item };
  }

}
