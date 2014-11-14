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
package org.polarsys.capella.common.ui.toolkit.viewers;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;

import org.polarsys.capella.common.ui.services.helper.ViewerHelper;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.PatternFilter;

/**
 * The regular expression viewer. Decorates another viewer with a regular expression search field. If the client area contains one (or more) structured viewer,
 * the developer can attach the regular expression viewer filter to them. The regular expression viewer is used to filter a displayed data list content or tree
 * content according to the regular expression search field user input.
 */
public abstract class AbstractRegExpViewer extends FieldsViewer {
  // The client viewer.
  private Viewer _clientViewer;
  private Menu _contextMenu;
  private MenuManager _contextMenuManager;
  private AbstractContextMenuFiller _contextMenuManagerFiller;
  // The regular expression viewer filter.
  private PatternFilter _filter;
  // The regular expression text field.
  private Text _regExpText;

  /**
   * Constructs the regular expression viewer.
   * @param parent_p The parent composite.
   */
  public AbstractRegExpViewer(Composite parent_p) {
    super(parent_p, SWT.NONE /* No style */);
  }

  /**
   * Constructs the regular expression viewer.
   * @param parent_p : The parent composite.
   * @param isMultipleSelection_p : multiple selection in tree option : default value : false
   */
  public AbstractRegExpViewer(Composite parent_p, boolean isMultipleSelection_p) {
    super(parent_p, isMultipleSelection_p);
  }

  /**
   * Constructs the regular expression viewer.
   * @param parent_p : The parent composite.
   * @param isMultipleSelection_p : multiple selection in tree option : default value : false
   * @param style_p
   */
  public AbstractRegExpViewer(Composite parent_p, boolean isMultipleSelection_p, int style_p, int viewerExpandLevel_p) {
    super(parent_p, isMultipleSelection_p, style_p, viewerExpandLevel_p);
  }

  /**
   * Adds the given filter to this viewer, and triggers refiltering and resorting of the elements.<br>
   * If you want to add more than one filter consider using {@link #setFilters(ViewerFilter[])}.<br>
   * Only works if client viewer ({@link #getClientViewer()} is {@link StructuredViewer}.
   * @param filter_p a viewer filter
   * @see #setFilters(ViewerFilter[])
   */
  public void addFilter(ViewerFilter filter_p) {
    Viewer clientViewer = getClientViewer();
    // Don't add twice internal filter.
    if ((clientViewer instanceof StructuredViewer) && !_filter.equals(filter_p)) {
      ((StructuredViewer) clientViewer).addFilter(filter_p);
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#createControl(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void createControl(Composite parent_p) {
    super.createControl(parent_p);
    // Set a layout.
    GridLayout layout = new GridLayout(1, true);
    parent_p.setLayout(layout);
    // Create the pattern filter.
    _filter = createPatternFilter();
    // Create the filter widgets.
    createFilterText(parent_p);
    // Creates the client viewer.
    _clientViewer = doClientViewer(parent_p);
    // Handle viewer filter attachment.
    handleViewerFilterAttachment(_filter);
    // Layouts the client area.
    if (null != _clientViewer) {
      GridData gdData = new GridData();
      gdData.verticalAlignment = SWT.FILL;
      gdData.horizontalAlignment = SWT.FILL;
      gdData.grabExcessHorizontalSpace = true;
      gdData.grabExcessVerticalSpace = true;
      _clientViewer.getControl().setLayoutData(gdData);
      // Install a context menu.
      installContextMenu();
    }
  }

  /**
   * Create the filter text widgets.
   * @param parent_p
   */
  protected void createFilterText(Composite parent_p) {
    // The group.
    Group group = new Group(parent_p, SWT.SHADOW_ETCHED_IN);
    group.setText(Messages.getString("AbstractRegExpViewer.group.label")); //$NON-NLS-1$
    GridData gdData = new GridData(GridData.FILL_HORIZONTAL);
    group.setLayoutData(gdData);
    GridLayout layout = new GridLayout(1, true);
    group.setLayout(layout);

    // The label.
    Label regExpLabel = new Label(group, SWT.NONE);
    gdData = new GridData(GridData.FILL_HORIZONTAL);
    regExpLabel.setText(Messages.getString("AbstractRegExpViewer.help.label")); //$NON-NLS-1$
    regExpLabel.setLayoutData(gdData);

    // The regular expression text field.
    _regExpText = new Text(group, SWT.BORDER);
    gdData = new GridData(GridData.FILL_HORIZONTAL);
    _regExpText.setLayoutData(gdData);
    _regExpText.addModifyListener(new ModifyListener() {
      @SuppressWarnings("synthetic-access")
      public void modifyText(ModifyEvent event_p) {
        // Set the new pattern entered by the end-user.
        _filter.setPattern(((Text) event_p.widget).getText());
        handlePatternApplied(getClientViewer());
      }
    });
  }

  /**
   * Create pattern filter used to filter related viewer.<br>
   * Default implementation uses a {@link PatternFilter}.
   */
  protected PatternFilter createPatternFilter() {
    return new PatternFilter() {
      /**
       * @see org.polarsys.capella.common.ui.toolkit.widgets.filter.PatternFilter#isParentMatch(org.eclipse.jface.viewers.Viewer, java.lang.Object,
       *      java.lang.Object)
       */
      @Override
      protected boolean isParentMatch(Viewer viewer_p, Object parentElement_p, Object element_p) {
        if (viewer_p instanceof AbstractTreeViewer) {
          return super.isParentMatch(viewer_p, parentElement_p, element_p);
        }
        // Returning false for non Tree-based viewers.
        return false;
      }
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    super.dispose();
    _filter = null;
    _contextMenuManagerFiller = null;
    if ((null != _regExpText) && !_regExpText.isDisposed()) {
      _regExpText.dispose();
      _regExpText = null;
    }
    if ((null != _contextMenu) && !_contextMenu.isDisposed()) {
      _contextMenu.dispose();
      _contextMenu = null;
    }
    if (null != _contextMenuManager) {
      _contextMenuManager.dispose();
    }
  }

  /**
   * Creates the client area viewer.
   * @param parent_p This regular expression composite.
   */
  protected abstract Viewer doClientViewer(Composite parent_p);

  /**
   * Gets the client viewer created by {@link #doClientViewer(Composite)}.
   * @return the client viewer.
   */
  public Viewer getClientViewer() {
    return _clientViewer;
  }

  /**
   * Get the context menu manager used by the menu manager filler.<br>
   * DON't USE this method to fill the context menu manager in. Please provide an {@link AbstractContextMenuFiller} instead.<br>
   * Can be used to register this context menu manager in a workbench part site.
   * @return the contextMenuManager
   */
  public MenuManager getContextMenuManager() {
    return _contextMenuManager;
  }

  /**
   * Gets the regular expression filter.
   * @return The regular expression filter.
   */
  public ViewerFilter getFilter() {
    return _filter;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#getInput()
   */
  @Override
  public Object getInput() {
    return null;
  }

  /**
   * Handle pattern applied.<br>
   * Default implementation refreshes the client viewer if this one is an instance of {@link StructuredViewer}.
   * @param clientViewer_p the client viewer created by {@link #doClientViewer(Composite)}.
   */
  protected void handlePatternApplied(Viewer clientViewer_p) {
    // Refresh the viewer to take into account the new pattern filter.
    if (clientViewer_p instanceof StructuredViewer) {
      ViewerHelper.refresh((StructuredViewer) clientViewer_p);
    }
  }

  /**
   * Handle {@link ViewerFilter} attachment.<br>
   * Default implementation attaches the filter to the client viewer if this one is an instance of {@link StructuredViewer}.
   * @param filter_p
   */
  protected void handleViewerFilterAttachment(ViewerFilter filter_p) {
    if ((null != _clientViewer) && (_clientViewer instanceof StructuredViewer)) {
      ((StructuredViewer) _clientViewer).addFilter(filter_p);
    }
  }

  /**
   * Install context menu on the control of the created client viewer.
   */
  protected void installContextMenu() {
    Control control = _clientViewer.getControl();
    _contextMenuManager = new MenuManager("#Popup"); //$NON-NLS-1$
    _contextMenuManager.setRemoveAllWhenShown(true);
    _contextMenuManager.addMenuListener(new IMenuListener() {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      public void menuAboutToShow(IMenuManager manager_p) {
        if (null != _contextMenuManagerFiller) {
          _contextMenuManagerFiller.fillMenuManager(_contextMenuManager, _clientViewer.getSelection());
        }
      }
    });
    _contextMenu = _contextMenuManager.createContextMenu(control);
    control.setMenu(_contextMenu);
  }

  /**
   * Removes the given filter from this viewer, and triggers refiltering and resorting of the elements if required.<br>
   * Has no effect if the identical filter is not registered.<br>
   * If you want to remove more than one filter consider using {@link #setFilters(ViewerFilter[])}.<br>
   * Only works if client viewer ({@link #getClientViewer()} is {@link StructuredViewer}.
   * @param filter_p a viewer filter
   * @see setFilters(ViewerFilter[])
   */
  public void removeFilter(ViewerFilter filter_p) {
    Viewer clientViewer = getClientViewer();
    if ((clientViewer instanceof StructuredViewer) && !_filter.equals(filter_p)) {
      ((StructuredViewer) clientViewer).removeFilter(filter_p);
    }
  }

  /**
   * Set context menu manager filler.<br>
   * The underlying context menu manager removes all items when the menu is about to show. Hence, this filler is called to fill in again the menu.
   * @param contextMenuManagerFiller_p
   */
  public void setContextMenuManagerFiller(AbstractContextMenuFiller contextMenuManagerFiller_p) {
    _contextMenuManagerFiller = contextMenuManagerFiller_p;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#setEnabled(boolean)
   */
  @Override
  public void setEnabled(boolean enabled_p) {
    if ((null != _regExpText) && !_regExpText.isDisposed()) {
      _regExpText.setEnabled(enabled_p);
    }
    if ((null != _clientViewer) && !_clientViewer.getControl().isDisposed()) {
      _clientViewer.getControl().setEnabled(enabled_p);
    }
  }

  /**
   * Sets the filters, replacing any previous filters, and triggers refiltering and resorting of the elements.<br>
   * Only works if client viewer ({@link #getClientViewer()} is {@link StructuredViewer}.
   * @param filters_p an array of viewer filters
   */
  public void setFilters(ViewerFilter[] filters_p) {
    Viewer clientViewer = getClientViewer();
    if (clientViewer instanceof StructuredViewer) {
      // Don't forget to keep the reg exp filter.
      ViewerFilter[] filters = new ViewerFilter[filters_p.length + 1];
      System.arraycopy(filters_p, 0, filters, 0, filters_p.length);
      filters[filters_p.length] = _filter;
      // Set filters.
      ((StructuredViewer) clientViewer).setFilters(filters);
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#setInput(java.lang.Object)
   */
  @Override
  public void setInput(Object input_p) {
    Viewer clientViewer = getClientViewer();
    if (null != clientViewer) {
      clientViewer.setInput(input_p);
    }
  }
}
