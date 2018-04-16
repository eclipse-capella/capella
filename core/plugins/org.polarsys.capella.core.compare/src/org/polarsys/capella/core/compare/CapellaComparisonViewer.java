/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.compare;

import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.ui.util.UIUtil;
import org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer;
import org.eclipse.emf.diffmerge.ui.viewers.EnhancedComparisonTreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IActionBars;


/**
 * A comparison viewer for Capella models.
 */
public class CapellaComparisonViewer extends ComparisonViewer {
  
  /** The (initially null) filter for differences inside Sirius diagrams */
  protected ViewerFilter _diagramContentFilter;
  
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   */
  public CapellaComparisonViewer(Composite parent_p) {
    this(parent_p, null);
  }
  
  /**
   * Constructor
   * @param parent_p a non-null composite
   * @param actionBars_p optional action bars
   */
  public CapellaComparisonViewer(Composite parent_p, IActionBars actionBars_p) {
    super(parent_p, actionBars_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer#createViewerSynthesis(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected EnhancedComparisonTreeViewer createViewerSynthesis(Composite parent_p) {
    EnhancedComparisonTreeViewer result = super.createViewerSynthesis(parent_p);
    result.getInnerViewer().addFilter(_diagramContentFilter);
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer#handleDispose()
   */
  @Override
  protected void handleDispose() {
    super.handleDispose();
    _diagramContentFilter = null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer#initialize()
   */
  @Override
  protected void initialize() {
    super.initialize();
    _diagramContentFilter = new ViewerFilter() {
      @Override
      public boolean select(Viewer viewer_p, Object parentElement_p, Object element_p) {
        if (element_p instanceof IMatch) {
          IMatch match = (IMatch)element_p;
          if (match.get(Role.TARGET) instanceof DRepresentation &&
              match.get(Role.REFERENCE) instanceof DRepresentation)
            return false;
        }
        return true;
      }
    };
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.ComparisonViewer#setupMenuSynthesis(org.eclipse.swt.widgets.ToolBar)
   */
  @Override
  protected Menu setupMenuSynthesis(ToolBar toolbar_p) {
    Menu synthesisMenu = UIUtil.createMenuTool(toolbar_p);
    createItemRestart(synthesisMenu);
    new MenuItem(synthesisMenu, SWT.SEPARATOR);
    // Show all elements in synthesis
    createItemShowUncounted(synthesisMenu);
    createItemShowDiagramDifferences(synthesisMenu);
    createItemFilter(synthesisMenu);
    new MenuItem(synthesisMenu, SWT.SEPARATOR);
    // Common presentation features
    createItemSync(synthesisMenu);
    createItemSort(synthesisMenu);
    // UI options
    new MenuItem(synthesisMenu, SWT.SEPARATOR);
    setupMenuSynthesisMisc(synthesisMenu);
    return synthesisMenu;
  }
  
  /**
   * If the item is selected, the filter will be removed, meaning that every difference of the diagram will be showed.
   * If the item is not selected, the filter will be added, meaning that every difference of the diagram will be hidden.
   * 
   * @param context_p
   *          The menu context
   * @return The menu item
   */
  protected Item createItemShowDiagramDifferences(Menu context_p) {
    final MenuItem result = new MenuItem(context_p, SWT.CHECK);
    result.setText(Messages.CapellaComparisonViewer_MenuItemShowDiagramContent_Title);
    result.setToolTipText(Messages.CapellaComparisonViewer_MenuItemShowDiagramContent_Tooltip);
    result.addSelectionListener(new SelectionAdapter() {
      /**
       * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void widgetSelected(SelectionEvent e_p) {
        if (result.getSelection())
          _viewerSynthesisMain.getInnerViewer().removeFilter(_diagramContentFilter);
        else
          _viewerSynthesisMain.getInnerViewer().addFilter(_diagramContentFilter);
      }
    });
    return result;
  }
}
