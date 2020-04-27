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
package org.polarsys.capella.patterns.ui.util;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.diffmerge.patterns.templates.engine.specifications.AbstractTemplatePatternSelection;
import org.eclipse.emf.diffmerge.patterns.ui.sirius.util.SiriusUIExtender;
import org.eclipse.emf.diffmerge.patterns.ui.util.IUIExtender;
import org.eclipse.emf.diffmerge.patterns.ui.viewers.ModelSubsetViewer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;
import org.polarsys.capella.patterns.ui.Messages;


/**
 * An implementation of IUIExtender for Capella.
 * This implementation relies on the diagram specifications of Capella.
 */
public class CapellaUIExtender extends SiriusUIExtender {

  /**
   * Default constructor
   */
  public CapellaUIExtender() {
    // Nothing needed
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.ui.sirius.util.SiriusUIExtender#createNavigationItems(org.eclipse.swt.widgets.Menu, org.eclipse.emf.diffmerge.patterns.ui.viewers.ModelSubsetViewer)
   */
  @Override
  public boolean createNavigationItems(final Menu menu, final ModelSubsetViewer viewer) {
    createShowInSemanticBrowserItem(menu, viewer);
    createShowInExplorerItem(menu, viewer);
    return true;
  }

  /**
   * Create the "Show in Capella Explorer" menu item
   * @param menu a non-null menu
   * @param viewer a non-null viewer
   */
  protected void createShowInExplorerItem(final Menu menu, final ModelSubsetViewer viewer) {
    final MenuItem showInExp = new MenuItem(menu, SWT.NONE);
    showInExp.setText(Messages.CapellaSemanticMapping_ShowInExplorer);
    showInExp.setAccelerator(SWT.F8);
    CommonNavigator exp = getCapellaAdvanceExplorer(false);
    if (exp != null)
      showInExp.setImage(exp.getTitleImage());
    showInExp.setEnabled(false);
    // Enabled state
    viewer.addSelectionListener(new ISelectionChangedListener() {
      public void selectionChanged(SelectionChangedEvent event) {
        IStructuredSelection selection = (IStructuredSelection)event.getSelection();
        boolean enable = selection.size() == 1;
        if (enable) {
          Object first = selection.getFirstElement();
          enable = first instanceof EObject &&
              AbstractTemplatePatternSelection.INSTANCE_FILTER.accepts((EObject)first);
        }
        showInExp.setEnabled(enable);
      }
    });
    // Execution
    showInExp.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e) {
        IStructuredSelection selection = viewer.getSelection();
        CommonNavigator innerExp = getCapellaAdvanceExplorer(true);
        if (innerExp != null && selection.size() == 1) {
          if (showInExp.getImage() == null)
            showInExp.setImage(innerExp.getTitleImage());
          innerExp.selectReveal(selection);
        }
      }
    });
  }

  /**
   * Create the "Show in Semantic Browser" menu item
   * @param menu a non-null menu
   * @param viewer a non-null viewer
   */
  protected void createShowInSemanticBrowserItem(final Menu menu, final ModelSubsetViewer viewer) {
    final MenuItem showInSB = new MenuItem(menu, SWT.NONE);
    showInSB.setText(Messages.CapellaSemanticMapping_ShowInSemanticBrowser);
    showInSB.setAccelerator(SWT.F9);
    ISemanticBrowserViewPart sb = getSemanticBrowser(false);
    if (sb != null)
      showInSB.setImage(sb.getTitleImage());
    showInSB.setEnabled(false);
    // Enabled state
    viewer.addSelectionListener(new ISelectionChangedListener() {
      public void selectionChanged(SelectionChangedEvent event) {
        IStructuredSelection selection = (IStructuredSelection)event.getSelection();
        boolean enable = selection.size() == 1;
        if (enable) {
          Object first = selection.getFirstElement();
          enable = first instanceof EObject &&
              AbstractTemplatePatternSelection.INSTANCE_FILTER.accepts((EObject)first);
        }
        showInSB.setEnabled(enable);
      }
    });
    // Execution
    showInSB.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @Override
      public void widgetSelected(SelectionEvent e) {
        @SuppressWarnings("unchecked")
        List<EObject> selection = viewer.getSelection().toList();
        ISemanticBrowserViewPart innerSb = getSemanticBrowser(true);
        if (innerSb != null && selection.size() == 1) {
          EObject element = selection.get(0);
          if (showInSB.getImage() == null)
            showInSB.setImage(innerSb.getTitleImage());
          innerSb.setInput(element);
        }
      }
    });
  }

  /**
   * Return the Project Explorer view if possible
   * @param forceShow whether the view must be shown if it is hidden
   * @return a potentially null object
   */
  protected CommonNavigator getCapellaAdvanceExplorer(boolean forceShow) {
    CommonNavigator result = null;
    final String viewID = CapellaCommonNavigator.ID;
    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    IViewPart view = null;
    if (forceShow) {
      try {
        view = page.showView(viewID);
      } catch (PartInitException e) {
        // Proceed
      }
    } else {
      view = page.findView(viewID);
    }
    if (view instanceof CommonNavigator)
      result = (CommonNavigator)view;
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.patterns.ui.sirius.util.SiriusUIExtender#getOverridenClasses()
   */
  @Override
  public Collection<? extends Class<? extends IUIExtender>> getOverridenClasses() {
    return Collections.singleton(SiriusUIExtender.class);
  }

  /**
   * Return the Semantic Browser view if possible
   * @param forceShow whether the view must be shown if it is hidden
   * @return a potentially null object
   */
  protected ISemanticBrowserViewPart getSemanticBrowser(boolean forceShow) {
    ISemanticBrowserViewPart result = null;
    final String viewID = SemanticBrowserView.SEMANTIC_BROWSER_ID;
    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    IViewPart view = null;
    if (forceShow) {
      try {
        view = page.showView(viewID);
      } catch (PartInitException e) {
        // Proceed
      }
    } else {
      view = page.findView(viewID);
    }
    if (view instanceof ISemanticBrowserViewPart)
      result = (ISemanticBrowserViewPart)view;
    return result;
  }

}
