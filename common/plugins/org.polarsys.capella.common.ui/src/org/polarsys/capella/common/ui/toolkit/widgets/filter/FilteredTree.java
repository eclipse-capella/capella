/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.toolkit.widgets.filter;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.bindings.keys.IKeyLookup;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.progress.WorkbenchJob;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.IImageKeys;
import org.polarsys.capella.common.ui.MdeCommonUiActivator;
import org.polarsys.capella.common.ui.services.swt.events.AbstractKeyAdapter;

/**
 * Copied from org.eclipse.ui.dialogs due to {@link PatternFilter} methods visibility.<br>
 * A simple control that provides a text widget and a tree viewer. The contents of the text widget are used to drive a PatternFilter that is on the viewer.
 * @see PatternFilter
 * @since 3.2
 */
public class FilteredTree extends Composite {
  /**
   * Custom tree viewer subclass that clears the caches in patternFilter on any change to the tree. See bug 187200.
   * @since 3.3
   */
  class NotifyingTreeViewer extends TreeViewer {
    /**
     * Constructor.
     * @param parent
     * @param style
     */
    public NotifyingTreeViewer(Composite parent, int style) {
      super(parent, style);
      setUseHashlookup(true);
    }

    @Override
    public void add(Object parentElementOrTreePath, Object childElement) {
      getPatternFilter().clearCaches();
      super.add(parentElementOrTreePath, childElement);
    }

    @Override
    public void add(Object parentElementOrTreePath, Object[] childElements) {
      getPatternFilter().clearCaches();
      super.add(parentElementOrTreePath, childElements);
    }

    @Override
    protected void inputChanged(Object input, Object oldInput) {
      getPatternFilter().clearCaches();
      super.inputChanged(input, oldInput);
    }

    @Override
    public void insert(Object parentElementOrTreePath, Object element, int position) {
      getPatternFilter().clearCaches();
      super.insert(parentElementOrTreePath, element, position);
    }

    @Override
    public void refresh() {
      getPatternFilter().clearCaches();
      super.refresh();
    }

    @Override
    public void refresh(boolean updateLabels) {
      getPatternFilter().clearCaches();
      super.refresh(updateLabels);
    }

    @Override
    public void refresh(Object element) {
      getPatternFilter().clearCaches();
      super.refresh(element);
    }

    @Override
    public void refresh(Object element, boolean updateLabels) {
      getPatternFilter().clearCaches();
      super.refresh(element, updateLabels);
    }

    @Override
    public void remove(Object elementsOrTreePaths) {
      getPatternFilter().clearCaches();
      super.remove(elementsOrTreePaths);
    }

    @Override
    public void remove(Object parent, Object[] elements) {
      getPatternFilter().clearCaches();
      super.remove(parent, elements);
    }

    @Override
    public void remove(Object[] elementsOrTreePaths) {
      getPatternFilter().clearCaches();
      super.remove(elementsOrTreePaths);
    }

    @Override
    public void replace(Object parentElementOrTreePath, int index, Object element) {
      getPatternFilter().clearCaches();
      super.replace(parentElementOrTreePath, index, element);
    }

    @Override
    public void setChildCount(Object elementOrTreePath, int count) {
      getPatternFilter().clearCaches();
      super.setChildCount(elementOrTreePath, count);
    }

    @Override
    public void setContentProvider(IContentProvider provider) {
      getPatternFilter().clearCaches();
      super.setContentProvider(provider);
    }

    @Override
    public void setHasChildren(Object elementOrTreePath, boolean hasChildren) {
      getPatternFilter().clearCaches();
      super.setHasChildren(elementOrTreePath, hasChildren);
    }
  }

  /**
   * Maximum time spent expanding the tree after the filter text has been updated (this is only used if we were able to at least expand the visible nodes)
   */
  private static final long SOFT_MAX_EXPAND_TIME = 200;

  private boolean isAutoFiltering;

  /**
   * The Composite on which the filter controls are created. This is used to set the background color of the filter controls to match the surrounding controls.
   */
  protected Composite filterComposite;

  /**
   * The filter text widget to be used by this tree. This value may be <code>null</code> if there is no filter widget, or if the controls have not yet been
   * created.
   */
  protected Text filterText;

  /**
   * The control representing the clear button for the filter text entry. This value may be <code>null</code> if no such button exists, or if the controls have
   * not yet been created.
   */
  protected ToolBarManager filterToolBar;

  /**
   * The text to initially show in the filter text control.
   */
  protected String initialText;

  private boolean narrowingDown;

  /**
   * The parent composite of the filtered tree.
   * @since 3.3
   */
  protected Composite parent;

  /**
   * The pattern filter for the tree. This value must not be <code>null</code>.
   */
  private PatternFilter patternFilter;

  private String previousFilterText;

  /**
   * The job used to refresh the tree.
   */
  private Job refreshJob;

  /**
   * Whether or not to show the filter controls (text and clear button). The default is to show these controls. This can be overridden by providing a setting in
   * the product configuration file. The setting to add to not show these controls is: org.eclipse.ui/SHOW_FILTERED_TEXTS=false
   */
  protected boolean showFilterControls;

  /**
   * @since 3.3
   */
  protected Composite treeComposite;

  /**
   * The viewer for the filtered tree. This value should never be <code>null</code> after the widget creation methods are complete.
   */
  protected TreeViewer treeViewer;
  
  protected String lastAppliedPattern = "";

  /**
   * Create a new instance of the receiver. Subclasses that wish to override the default creation behavior may use this constructor, but must ensure that the
   * <code>init(composite, int, PatternFilter)</code> method is called in the overriding constructor.
   * @param parent the parent <code>Composite</code>
   * @see #init(int, PatternFilter)
   * @since 3.3
   */
  protected FilteredTree(Composite parent) {
    super(parent, SWT.NONE);
    this.parent = parent;
    isAutoFiltering = false;
  }

  /**
   * Create a new instance of the receiver.
   * @param parent the parent <code>Composite</code>
   * @param treeStyle the style bits for the <code>Tree</code>
   * @param filter the filter to be used
   */
  public FilteredTree(Composite parent, int treeStyle, PatternFilter filter) {
    super(parent, SWT.NONE);
    this.parent = parent;
    isAutoFiltering = false;
    init(treeStyle, filter);
  }

  /**
   * Clears the text in the filter text widget. Also removes the optional additional filter that is provided via addFilter(ViewerFilter).
   */
  protected void clearText() {
    setFilterText(ICommonConstants.EMPTY_STRING);
    textChanged();
  }

  /**
   * @return {@code true} if the widget natively supports a clear button, {@code false} otherwise
   */
  protected boolean hasNativeClearButton() {
	return (filterText.getStyle() & SWT.CANCEL) != 0;
  }

  /**
   * Create the button that clears the text.
   * @param parent parent <code>Composite</code> of toolbar button
   */
  protected void createClearText(Composite parent) {
    // only create the button if the text widget doesn't support one natively
    if (!hasNativeClearButton()) {
      if (null == filterToolBar) {
        filterToolBar = new ToolBarManager(SWT.FLAT | SWT.HORIZONTAL);
        filterToolBar.createControl(parent);
      }

      IAction clearTextAction = new Action(ICommonConstants.EMPTY_STRING, IAction.AS_PUSH_BUTTON) {
        /**
         * @see org.eclipse.jface.action.Action#run()
         */
        @Override
        public void run() {
          clearText();
        }
      };
      clearTextAction.setToolTipText(WorkbenchMessages.FilteredTree_ClearToolTip);
      clearTextAction.setImageDescriptor(MdeCommonUiActivator.getDefault().getImageDescriptor(IImageKeys.IMG_CLEAR_ENABLED));
      clearTextAction.setDisabledImageDescriptor(MdeCommonUiActivator.getDefault().getImageDescriptor(IImageKeys.IMG_CLEAR_DISABLED));

      filterToolBar.add(clearTextAction);
    }
  }

  /**
   * Create the filtered tree's controls. Subclasses should override.
   * @param parent
   * @param treeStyle
   */
  protected void createControl(Composite parent, int treeStyle) {
    GridLayout layout = new GridLayout();
    layout.marginHeight = 0;
    layout.marginWidth = 0;
    setLayout(layout);
    setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    if (showFilterControls) {
      filterComposite = createFilterGroup(this);
      GridLayout filterLayout = new GridLayout(2, false);
      filterLayout.marginHeight = 0;
      filterLayout.marginWidth = 0;
      filterComposite.setLayout(filterLayout);
      filterComposite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
      filterComposite.setFont(parent.getFont());

      createFilterControls(filterComposite);
    }

    treeComposite = new Composite(this, SWT.NONE);
    GridLayout treeCompositeLayout = new GridLayout();
    treeCompositeLayout.marginHeight = 0;
    treeCompositeLayout.marginWidth = 0;
    treeComposite.setLayout(treeCompositeLayout);
    GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
    treeComposite.setLayoutData(data);

    createTreeControl(treeComposite, treeStyle);
  }

  /**
   * @param parent
   */
  protected Composite createFilterGroup(Composite parent) {
    filterComposite = new Group(parent, SWT.NONE);
    ((Group) filterComposite).setText(Messages.FilteredTree_Group_Title);
    return filterComposite;
  }

  /**
   * Create the filter controls. By default, a text and corresponding tool bar button that clears the contents of the text is created. Subclasses may override.
   * @param parent parent <code>Composite</code> of the filter controls
   * @return the <code>Composite</code> that contains the filter controls
   */
  protected Composite createFilterControls(Composite parent) {
    createMessageArea(parent);
    createFilterText(parent);
    createClearText(parent);
    if (filterToolBar != null) {
      filterToolBar.update(false);
      if (filterToolBar.getSize() == 1) {
        // initially there is no text to clear
        filterToolBar.getControl().setVisible(false);
      }
    }
    return parent;
  }

  /**
   * Creates the filter text and adds listeners. This method calls {@link #doCreateFilterText(Composite)} to create the text control. Subclasses should override
   * {@link #doCreateFilterText(Composite)} instead of overriding this method.
   * @param parent <code>Composite</code> of the filter text
   */
  protected void createFilterText(Composite parent) {
    filterText = doCreateFilterText(parent);
    filterText.getAccessible().addAccessibleListener(new AccessibleAdapter() {
      /**
       * @see org.eclipse.swt.accessibility.AccessibleListener#getName(org.eclipse.swt.accessibility.AccessibleEvent)
       */
      @Override
      public void getName(AccessibleEvent e) {
        String filterTextString = filterText.getText();
        if (filterTextString.length() == 0) {
          e.result = initialText;
        } else {
          e.result = filterTextString;
        }
      }
    });

    filterText.addFocusListener(new FocusAdapter() {
      /**
       * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
       */
      @Override
      public void focusGained(FocusEvent e) {
        /**
         * Running in an asyncExec because the selectAll() does not appear to work when using mouse to give focus to text.
         */
        Display display = filterText.getDisplay();
        display.asyncExec(new Runnable() {
          public void run() {
            if (!filterText.isDisposed() && getInitialText().equals(filterText.getText().trim())) {
              filterText.selectAll();
            }
          }
        });
      }
      
      @Override
      public void focusLost(FocusEvent e) {
        if (!getFilterString().equals(previousFilterText)) {
          previousFilterText = getFilterString();
          refreshJob.cancel();
          long delay = 0;
          if (isAutoFiltering()) {
            delay = getAutoFilteringDelay();
          }
          refreshJob.schedule(delay);
        }
      }
    });
    filterText.addKeyListener(new AbstractKeyAdapter() {
      /**
       * @see org.eclipse.swt.events.KeyAdapter#keyPressed(org.eclipse.swt.events.KeyEvent)
       */
      @Override
      public void keyPressed(KeyEvent event) {
        boolean hasItems = getViewer().getTree().getItemCount() > 0;
        if (hasItems && (event.keyCode == SWT.ARROW_DOWN)) {
          treeViewer.getTree().setFocus();
        } else if ((event.character == SWT.CR) && (handle(event, IKeyLookup.CR_NAME) || handle(event, IKeyLookup.NUMPAD_ENTER_NAME)) && !isAutoFiltering()) {
          handleCRKeyStoke();
        }
      }
    });

    if (isAutoFiltering()) {
      filterText.addModifyListener(new ModifyListener() {
        /*
         * (non-Javadoc)
         * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
         */
        public void modifyText(ModifyEvent e) {
          textChanged();
        }
      });
    } else {
      filterText.setToolTipText(Messages.FilteredTree_Tooltip);
    }
    GridData gridData = new GridData(SWT.FILL, SWT.BEGINNING, true, false);

    // if the text widget supported cancel then it will have it's own
    // integrated button. We can take all of the space.
    if ((filterText.getStyle() & SWT.CANCEL) != 0) {
      gridData.horizontalSpan = 2;
    }
    filterText.setLayoutData(gridData);
  }

  /**
   * Creates the message text widget and sets layout data.
   * @param parent the parent composite of the message area.
   */
  protected Label createMessageArea(Composite parent) {
    Label label = new Label(parent, SWT.NONE);
    label.setText(Messages.FilteredTree_Title);
    label.setFont(parent.getFont());
    GridData data = new GridData();
    data.grabExcessVerticalSpace = false;
    data.grabExcessHorizontalSpace = true;
    data.horizontalAlignment = GridData.FILL;
    data.verticalAlignment = GridData.BEGINNING;
    data.horizontalSpan = 2;
    label.setLayoutData(data);
    return label;
  }

  /**
   * Create the refresh job for the receiver.
   */
  private void createRefreshJob() {
    refreshJob = new WorkbenchJob("Refresh Filter") {//$NON-NLS-1$
          /**
           * Returns true if the job should be canceled (because of timeout or actual cancellation).
           * @param items
           * @param provider
           * @param monitor
           * @param cancelTime
           * @param numItemsLeft
           * @return true if canceled
           */
          private boolean recursiveExpand(TreeItem[] items, IProgressMonitor monitor, long cancelTime, int[] numItemsLeft) {
            boolean canceled = false;
            for (int i = 0; !canceled && (i < items.length); i++) {
              TreeItem item = items[i];
              boolean visible = numItemsLeft[0]-- >= 0;
              if (monitor.isCanceled() || (!visible && (System.currentTimeMillis() > cancelTime))) {
                canceled = true;
              } else {
                Object itemData = item.getData();
                if ((itemData != null)) {
                  if (!item.getExpanded()) {
                    // do the expansion through the viewer so that it can refresh children appropriately.
                    treeViewer.setExpandedState(itemData, true);
                  }
                  TreeItem[] children = item.getItems();
                  if (items.length > 0) {
                    canceled = recursiveExpand(children, monitor, cancelTime, numItemsLeft);
                  }
                }
              }
            }
            return canceled;
          }

          /**
           * @see org.eclipse.ui.progress.UIJob#runInUIThread(org.eclipse.core.runtime.IProgressMonitor)
           */
          @SuppressWarnings("synthetic-access")
          @Override
          public IStatus runInUIThread(IProgressMonitor monitor) {
            if (treeViewer.isBusy() || treeViewer.getControl().isDisposed()) {
              return Status.CANCEL_STATUS;
            }

            String text = getFilterString();
            if (text == null || lastAppliedPattern.equals(text)) {
              return Status.OK_STATUS;
            }

            boolean initial = (initialText != null) && initialText.equals(text);
            String newPatternToApply = "";
            if (!initial && (text.length() > 0)) {
              newPatternToApply = text;
            }
            lastAppliedPattern = newPatternToApply;
            patternFilter.setPattern(newPatternToApply);

            Control redrawFalseControl = treeComposite != null ? treeComposite : treeViewer.getControl();
            try {
              // don't want the user to see updates that will be made to the tree
              // we are setting redraw(false) on the composite to avoid dancing scroll bar
              redrawFalseControl.setRedraw(false);
              // Get current expanded elements just before the tree viewer refresh.
              ISelection currentSelection = treeViewer.getSelection();
              Object[] expandedElements = treeViewer.getExpandedElements();

              if (!narrowingDown) {
                // collapse all
                TreeItem[] is = treeViewer.getTree().getItems();
                for (TreeItem item : is) {
                  if (item.getExpanded()) {
                    treeViewer.setExpandedState(item.getData(), false);
                  }
                }
              }
              // Refresh the tree viewer to take into account the new pattern filter value.
              treeViewer.refresh(false);

              if (!initial) {
                if (text.length() > 0) {
                  // enabled toolbar - there is text to clear
                  // and the list is currently being filtered
                  updateToolbar(true);
                  /*
                   * Expand elements one at a time. After each is expanded, check to see if the filter text has been modified. If it has, then cancel the
                   * refresh job so the user doesn't have to endure expansion of all the nodes.
                   */
                  TreeItem[] items = getViewer().getTree().getItems();
                  int treeHeight = getViewer().getTree().getBounds().height;
                  int numVisibleItems = treeHeight / getViewer().getTree().getItemHeight();
                  long stopTime = SOFT_MAX_EXPAND_TIME + System.currentTimeMillis();

                  if ((items.length > 0) && recursiveExpand(items, monitor, stopTime, new int[] { numVisibleItems })) {
                    return Status.CANCEL_STATUS;
                  }
                } else {
                  // Disabled toolbar - there is no text to clear and the list is no longer filtered
                  updateToolbar(false);
                  handleTreeViewerExpansionWhenNoFilter(currentSelection, expandedElements);
                }
              } else {
                // No filter has been used since this tree viewer was displayed.
                // Disabled toolbar - there is no text to clear and the list is currently not filtered
                updateToolbar(false);
              }
            } finally {
              // done updating the tree - set redraw back to true
              TreeItem[] items = getViewer().getTree().getItems();
              if ((items.length > 0) && (getViewer().getTree().getSelectionCount() == 0)) {
                treeViewer.getTree().setTopItem(items[0]);
              }
              redrawFalseControl.setRedraw(true);
            }
            return Status.OK_STATUS;
          }

        };
    refreshJob.setSystem(true);
  }

  /**
   * Creates and set up the tree and tree viewer. This method calls {@link #doCreateTreeViewer(Composite, int)} to create the tree viewer. Subclasses should
   * override {@link #doCreateTreeViewer(Composite, int)} instead of overriding this method.
   * @param parent parent <code>Composite</code>
   * @param style SWT style bits used to create the tree
   * @return the tree
   */
  protected Control createTreeControl(Composite parent, int style) {
    treeViewer = doCreateTreeViewer(parent, style);
    GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
    treeViewer.getControl().setLayoutData(data);
    treeViewer.getControl().addDisposeListener(new DisposeListener() {
      /**
       * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
       */
      @SuppressWarnings("synthetic-access")
      public void widgetDisposed(DisposeEvent e) {
        if (refreshJob != null) {
          refreshJob.cancel();
        }
      }
    });
    if (treeViewer instanceof NotifyingTreeViewer) {
      patternFilter.setUseCache(true);
    }
    treeViewer.addFilter(patternFilter);
    return treeViewer.getControl();
  }

  /**
   * Creates the text control for entering the filter text. Subclasses may override.
   * @param parent the parent composite
   * @return the text widget
   * @since 3.3
   */
  protected Text doCreateFilterText(Composite parent) {
    return new Text(parent, SWT.SINGLE | SWT.BORDER | SWT.SEARCH);
  }

  /**
   * Creates the tree viewer. Subclasses may override.
   * @param parent the parent composite
   * @param style SWT style bits used to create the tree viewer
   * @return the tree viewer
   * @since 3.3
   */
  protected TreeViewer doCreateTreeViewer(Composite parent, int style) {
    return new NotifyingTreeViewer(parent, style);
  }

  /**
   * Get the delay used to trigger the refresh job.<br>
   * Default implementation returns <code>200</code> ms.
   * @return a long.
   */
  protected long getAutoFilteringDelay() {
    return 200L;
  }

  /**
   * Expansion level used to expand the tree viewer when the pattern filter is reset to null.<br>
   * Default returns 0.
   * @return
   */
  protected int getExpansionLevelWhenNoFilter() {
    return 0;
  }

  /**
   * Get the filter text for the receiver, if it was created. Otherwise return <code>null</code>.
   * @return the filter Text, or null if it was not created
   */
  public Text getFilterControl() {
    return filterText;
  }

  /**
   * Convenience method to return the text of the filter control. If the text widget is not created, then null is returned.
   * @return String in the text, or null if the text does not exist
   */
  protected String getFilterString() {
    return filterText != null ? filterText.getText() : null;
  }

  /**
   * Get the initial text for the receiver.
   * @return String
   */
  protected String getInitialText() {
    return initialText;
  }

  /**
   * Returns the pattern filter used by this tree.
   * @return The pattern filter; never <code>null</code>.
   */
  public PatternFilter getPatternFilter() {
    return patternFilter;
  }

  /**
   * Get the tree viewer of the receiver.
   * @return the tree viewer
   */
  public TreeViewer getViewer() {
    return treeViewer;
  }

  /**
   * Handle 'CR' key stroke.
   */
  protected void handleCRKeyStoke() {
    textChanged();
  }

  /**
   * Handle the tree viewer expansion when the pattern filter is reset to null.<br>
   * Default implementation tries to expand specified elements if any. Otherwise, expandToLevel operation is performed.
   * @param expandedElements
   * @see #getExpansionLevelWhenNoFilter().
   */
  protected void handleTreeViewerExpansionWhenNoFilter(Object[] expandedElements) {
    handleTreeViewerExpansionWhenNoFilter(null, expandedElements);
  }

  /**
   * Handle the tree viewer expansion when the pattern filter is reset to null.<br>
   * Default implementation tries to expand specified elements if any. Otherwise, expandToLevel operation is performed.
   * @param currentSelection
   * @param expandedElements
   * @see #getExpansionLevelWhenNoFilter().
   */
  protected void handleTreeViewerExpansionWhenNoFilter(ISelection currentSelection, Object[] expandedElements) {
    if (expandedElements.length == 0) {
      treeViewer.expandToLevel(getExpansionLevelWhenNoFilter());
    } else {
      treeViewer.setExpandedElements(expandedElements);
    }
    if (null != currentSelection) {
      treeViewer.setSelection(currentSelection, true);
    }
  }

  /**
   * Create the filtered tree.
   * @param treeStyle the style bits for the <code>Tree</code>
   * @param filter the filter to be used
   * @since 3.3
   */
  protected void init(int treeStyle, PatternFilter filter) {
    patternFilter = filter;
    showFilterControls = PlatformUI.getPreferenceStore().getBoolean(IWorkbenchPreferenceConstants.SHOW_FILTERED_TEXTS);
    createControl(parent, treeStyle);
    createRefreshJob();
    setInitialText(WorkbenchMessages.FilteredTree_FilterMessage);
    setFont(parent.getFont());
  }

  /**
   * @return the isAutoFiltering
   */
  protected boolean isAutoFiltering() {
    return isAutoFiltering;
  }

  /**
   * Select all text in the filter text field.
   */
  protected void selectAll() {
    if (filterText != null) {
      filterText.selectAll();
    }
  }

  /**
   * @param isAutoFiltering the isAutoFiltering to set
   */
  protected void setAutoFiltering(boolean isAutoFiltering) {
    this.isAutoFiltering = isAutoFiltering;
  }

  /**
   * Set the background for the widgets that support the filter text area.
   * @param background background <code>Color</code> to set
   */
  @Override
  public void setBackground(Color background) {
    super.setBackground(background);
    if (filterComposite != null) {
      filterComposite.setBackground(background);
    }
    if ((filterToolBar != null) && (filterToolBar.getControl() != null)) {
      filterToolBar.getControl().setBackground(background);
    }
  }

  /**
   * Set the text in the filter control.
   * @param string
   */
  protected void setFilterText(String string) {
    if (filterText != null) {
      filterText.setText(string);
      selectAll();
    }
  }

  /**
   * Set the text that will be shown until the first focus. A default value is provided, so this method only need be called if overriding the default initial
   * text is desired.
   * @param text initial text to appear in text field
   */
  public void setInitialText(String text) {
    initialText = text;
    setFilterText(initialText);
    textChanged();
  }

  /**
   * Update the receiver after the text has changed.
   */
  protected void textChanged() {
    narrowingDown = (previousFilterText == null) || getFilterString().startsWith(previousFilterText);
    previousFilterText = getFilterString();
    // cancel currently running job first, to prevent unnecessary redraw

    refreshJob.cancel();

    long delay = 0;
    if (isAutoFiltering()) {
      delay = getAutoFilteringDelay();
    }
    // Schedule it immediately.
    refreshJob.schedule(delay);
  }

  protected void updateToolbar(boolean visible) {
    if (filterToolBar != null) {
      filterToolBar.getControl().setVisible(visible);
    }
  }

  /**
   * Return a bold font if the given element matches the given pattern. Clients can opt to call this method from a Viewer's label provider to get a bold font
   * for which to highlight the given element in the tree.
   * @param element element for which a match should be determined
   * @param tree FilteredTree in which the element resides
   * @param filter PatternFilter which determines a match
   * @return bold font
   */
  public static Font getBoldFont(Object element, FilteredTree tree, PatternFilter filter) {
    String filterText = tree.getFilterString();

    if (filterText == null) {
      return null;
    }

    // Do nothing if it's empty string
    String initialText = tree.getInitialText();
    if (!("".equals(filterText) || initialText.equals(filterText))) {//$NON-NLS-1$
      boolean initial = (initialText != null) && initialText.equals(filterText);
      if (initial) {
        filter.setPattern(null);
      } else {
        filter.setPattern(filterText);
      }

      TreeViewer viewer = tree.getViewer();
      Object parent = ((ITreeContentProvider) viewer.getContentProvider()).getParent(element);
      if (filter.isElementVisible(viewer, parent, element) && filter.isLeafMatch(viewer, parent, element)) {
        return JFaceResources.getFontRegistry().getBold(JFaceResources.DIALOG_FONT);
      }
    }
    return null;
  }
}
