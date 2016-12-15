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

package org.polarsys.capella.core.platform.sirius.ui.navigator.view;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.internal.misc.StringMatcher;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.INavigatorContentExtension;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.FilteredTree;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.PatternFilter;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.StringMatcherFactory;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.TreePatternFilter;
import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SelectionHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.MoveDownAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.MoveUpAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.preferences.ICapellaNavigatorPreferences;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.ActiveSessionManager;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.CapellaNavigatorContentProvider;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.ICommandStackSelectionProvider;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.NavigatorEditingDomainDispatcher;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.NavigatorSessionManagerListener;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.core.ui.properties.CapellaTabbedPropertySheetPage;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;

/**
 * The Capella common navigator.
 */
public class CapellaCommonNavigator extends CommonNavigator implements IEditingDomainProvider, ITabbedPropertySheetPageContributor, ICommandStackSelectionProvider,
    IPropertyChangeListener {

  /**
   * Capella common viewer.
   */
  public class CapellaCommonViewer extends CommonViewer {
    /**
     * Pretty much self explanatory.
     */
    @Deprecated
    protected volatile boolean _isRefreshEnabled;

    /**
     * Constructor.
     * @param aViewerId
     * @param aParent
     * @param aStyle
     */
    public CapellaCommonViewer(String aViewerId, Composite aParent, int aStyle) {
      super(aViewerId, aParent, aStyle);
      _isRefreshEnabled = true;
      NavigatorEditingDomainDispatcher.registerCommandStackSelectionProvider(CapellaCommonNavigator.this);
    }

    /**
     * @see org.eclipse.jface.viewers.AbstractTreeViewer#add(java.lang.Object, java.lang.Object)
     */
    @Override
    public void add(Object parentElementOrTreePath, Object childElement) {
      getPatternFilter().clearCaches();
      super.add(parentElementOrTreePath, childElement);
    }

    /**
     * @see org.eclipse.ui.navigator.CommonViewer#add(java.lang.Object, java.lang.Object[])
     */
    @Override
    public void add(Object parentElementOrTreePath, Object[] childElements) {
      getPatternFilter().clearCaches();
      super.add(parentElementOrTreePath, childElements);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISelection getSelection() {
      try {
        return super.getSelection();
      } catch (Exception e) {
        return StructuredSelection.EMPTY;
      }
    }

    /**
     * @see org.eclipse.jface.viewers.AbstractTreeViewer#inputChanged(java.lang.Object, java.lang.Object)
     */
    @Override
    protected void inputChanged(Object input, Object oldInput) {
      getPatternFilter().clearCaches();
      super.inputChanged(input, oldInput);
    }

    /**
     * @see org.eclipse.jface.viewers.AbstractTreeViewer#insert(java.lang.Object, java.lang.Object, int)
     */
    @Override
    public void insert(Object parentElementOrTreePath, Object element, int position) {
      getPatternFilter().clearCaches();
      super.insert(parentElementOrTreePath, element, position);
    }

    /**
     * @see org.eclipse.ui.navigator.CommonViewer#refresh(java.lang.Object, boolean)
     */
    @Override
    public void refresh(Object element, boolean updateLabels) {
      getPatternFilter().clearCaches();
      super.refresh(element, updateLabels);
    }

    /**
     * @see org.eclipse.jface.viewers.AbstractTreeViewer#remove(java.lang.Object)
     */
    @Override
    public void remove(Object elementsOrTreePaths) {
      getPatternFilter().clearCaches();
      super.remove(elementsOrTreePaths);
    }

    /**
     * @see org.eclipse.jface.viewers.AbstractTreeViewer#remove(java.lang.Object, java.lang.Object[])
     */
    @Override
    public void remove(Object parent, Object[] elements) {
      getPatternFilter().clearCaches();
      super.remove(parent, elements);
    }

    /**
     * @see org.eclipse.ui.navigator.CommonViewer#remove(java.lang.Object[])
     */
    @Override
    public void remove(Object[] elementsOrTreePaths) {
      getPatternFilter().clearCaches();
      super.remove(elementsOrTreePaths);
    }

    /**
     * @see org.eclipse.jface.viewers.TreeViewer#replace(java.lang.Object, int, java.lang.Object)
     */
    @Override
    public void replace(Object parentElementOrTreePath, int index, Object element) {
      getPatternFilter().clearCaches();
      super.replace(parentElementOrTreePath, index, element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFilters(ViewerFilter... filters) {
      super.setFilters(filters);
      addFilter(getPatternFilter());
    }

    /**
     * @see org.eclipse.jface.viewers.StructuredViewer#resetFilters()
     */
    @Override
    public void resetFilters() {
      super.resetFilters();
      addFilter(getPatternFilter());
    }

    /**
     * @see org.eclipse.jface.viewers.TreeViewer#setChildCount(java.lang.Object, int)
     */
    @Override
    public void setChildCount(Object elementOrTreePath, int count) {
      getPatternFilter().clearCaches();
      super.setChildCount(elementOrTreePath, count);
    }

    /**
     * @see org.eclipse.jface.viewers.TreeViewer#setContentProvider(org.eclipse.jface.viewers.IContentProvider)
     */
    @Override
    public void setContentProvider(IContentProvider provider) {
      getPatternFilter().clearCaches();
      super.setContentProvider(provider);
    }

    /**
     * @see org.eclipse.jface.viewers.TreeViewer#setHasChildren(java.lang.Object, boolean)
     */
    @Override
    public void setHasChildren(Object elementOrTreePath, boolean hasChildren) {
      getPatternFilter().clearCaches();
      super.setHasChildren(elementOrTreePath, hasChildren);
    }

    /**
     * @see org.eclipse.ui.navigator.CommonViewer#update(java.lang.Object, java.lang.String[])
     */
    @Override
    public void update(Object element, String[] properties) {
      super.update(element, properties);
    }

  }

  /**
   * Capella Filtered Tree.<br>
   * Standard {@link FilteredTree} adapted to CNF.
   */
  protected class CapellaFilteredTree extends FilteredTree {
    /**
     * Constructor.
     * @param parent
     */
    protected CapellaFilteredTree(Composite parent) {
      super(parent);
      attachFilterControlFocusListener();
    }

    /**
     * Constructor.
     * @param parent
     * @param treeStyle
     * @param filter
     */
    public CapellaFilteredTree(Composite parent, int treeStyle, PatternFilter filter) {
      super(parent, treeStyle, filter);
      attachFilterControlFocusListener();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void clearText() {
      // Reset pattern filter to default algorithm.
      getPatternFilter().setSearchInDescription(false);
      super.clearText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createClearText(Composite parent) {
      filterToolBar = new ToolBarManager(SWT.FLAT | SWT.HORIZONTAL);
      filterToolBar.createControl(parent);
      createSearchDescriptionButton(parent);
      super.createClearText(parent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Composite createFilterControls(Composite parent) {
      // Change the layout of the parent to host 3 widgets.
      GridLayout layout = (GridLayout) parent.getLayout();
      layout.numColumns = 3;
      Composite filterControls = super.createFilterControls(parent);
      return filterControls;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Label createMessageArea(Composite parent) {
      Label messageArea = super.createMessageArea(parent);
      GridData layoutData = (GridData) messageArea.getLayoutData();
      layoutData.horizontalSpan = 3;
      return messageArea;
    }

    /**
     * Create the button that triggers search in description.
     * @param parent parent <code>Composite</code> of toolbar button
     */
    private void createSearchDescriptionButton(Composite parent) {
      IAction searchInDescriptionAction = new Action(ICommonConstants.EMPTY_STRING, IAction.AS_PUSH_BUTTON) {
        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("synthetic-access")
        @Override
        public void run() {
          // Set pattern filter to search in description algorithm.
          getPatternFilter().setSearchInDescription(true);
          textChanged();
        }
      };
      searchInDescriptionAction.setToolTipText(Messages.CapellaCommonNavigator_searchInDescriptionAction_Tooltip);
      searchInDescriptionAction.setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor(IImageKeys.IM_SEARCH_DESCRIPTION));
      filterToolBar.add(searchInDescriptionAction);
    }

    /**
     * @see org.eclipse.ui.dialogs.FilteredTree#doCreateTreeViewer(org.eclipse.swt.widgets.Composite, int)
     */
    @Override
    protected TreeViewer doCreateTreeViewer(Composite parent, int style) {
      return new CapellaCommonViewer(getViewSite().getId(), parent, style);
    }

    /**
     * @see org.polarsys.capella.common.ui.toolkit.widgets.filter.FilteredTree#getExpansionLevelWhenNoFilter()
     */
    @Override
    protected int getExpansionLevelWhenNoFilter() {
      return 4; // Enables to see all architecture packages.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CapellaNavigatorPatternFilter getPatternFilter() {
      return (CapellaNavigatorPatternFilter) super.getPatternFilter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleCRKeyStoke() {
      // Reset pattern filter to default algorithm.
      getPatternFilter().setSearchInDescription(false);
      super.handleCRKeyStoke();
    }

    /**
     * @see org.polarsys.capella.common.ui.toolkit.widgets.filter.FilteredTree#init(int, org.polarsys.capella.common.ui.toolkit.widgets.filter.PatternFilter)
     */
    @Override
    protected void init(int treeStyle, PatternFilter filter) {
      // Disable auto filtering for usability.
      setAutoFiltering(false);
      super.init(treeStyle, filter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateToolbar(boolean visible) {
      // Do nothing as we want to always see the toolbar to access search
      // in description.
      IContributionItem[] items = filterToolBar.getItems();
      items[hasNativeClearButton() ? 0 : 1].setVisible(visible);
      filterToolBar.update(true);
    }

    private void attachFilterControlFocusListener() {
      getFilterControl().addFocusListener(new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
          // whenever the filter text control receives the focus,
          // uninstall
          // the global cut/copy/paste handlers set in
          // EditCommonActionProvider
          IActionBars ab = getViewSite().getActionBars();
          ab.setGlobalActionHandler(ActionFactory.CUT.getId(), null);
          ab.setGlobalActionHandler(ActionFactory.COPY.getId(), null);
          ab.setGlobalActionHandler(ActionFactory.PASTE.getId(), null);
        }
      });
    }
  }

  /**
   * Specific pattern filter for the Capella Navigator to be able to search base on {@link LabelProvider} or on Description model element attribute.
   */
  protected class CapellaNavigatorPatternFilter extends TreePatternFilter {
    /**
     * Flag to tell if we are searching in description {@link CapellaElement#getDescription()} rather than label provider.
     */
    private boolean _searchInDescription;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getTextFromModelElement(EObject element) {
      String result = null;
      if (_searchInDescription) {
        // Search on description attribute.
        if (element instanceof CapellaElement) {
          result = ((CapellaElement) element).getDescription();
        } else if (element instanceof DSemanticDiagram) {
          result = ((DSemanticDiagram) element).getDocumentation();
        }
      } else {
        result = super.getTextFromModelElement(element);
      }
      return (null == result) ? ICommonConstants.EMPTY_STRING : result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isElementVisible(Viewer viewer, Object parentElement, Object element) {
      if (_searchInDescription) {
        // Apply strict match algorithm.
        return isLeafMatch(viewer, parentElement, element) || isParentMatch(viewer, parentElement, element);
      }
      boolean visible = super.isElementVisible(viewer, parentElement, element);
      return visible;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isLeafMatch(Viewer viewer, Object parentElement, Object element) {
      if (_searchInDescription) {
        // Apply strict match algorithm.
        return doIsLeafMatch(viewer, parentElement, element);
      }
      return super.isLeafMatch(viewer, parentElement, element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isParentMatch(Viewer viewer, Object parentElement, Object element) {
      // we provide our own content provider instead of getting it from
      // the viewer
      ITreeContentProvider iTreeContentProvider = getContentProvider();
      Object[] children = iTreeContentProvider.getChildren(element);
      if ((children != null) && (children.length > 0)) {
        return isAnyVisible(viewer, element, children);
      }
      return false;
    }

    /**
     * Is searching is description.
     * @return the searchInDescription
     */
    protected boolean isSearchingInDescription() {
      return _searchInDescription;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
      if (_searchInDescription) {
        // Apply strict match algorithm.
        return isElementVisible(viewer, parentElement, element);
      }
      return super.select(viewer, parentElement, element);
    }

    /**
     * Set Search in description flag.
     * @param searchInDescription the searchInDescription to set
     */
    protected void setSearchInDescription(boolean searchInDescription) {
      _searchInDescription = searchInDescription;
    }
  }

  /**
   * The Capella project explorer view identifier.
   */
  public static final String ID = "capella.project.explorer"; //$NON-NLS-1$

  /**
   * Memento persistence tag.
   */
  private static final String TAG_MEMENTO = "memento"; //$NON-NLS-1$

  /**
   * Filtered tree used by the common viewer.
   */
  private CapellaFilteredTree _filteredTree;

  /**
   * Actions to move elements.
   */
  private MoveDownAction _moveDown;

  private MoveUpAction _moveUp;

  /**
   * The Capella session manager listener.
   */
  private NavigatorSessionManagerListener _sessionManagerListener;
  /**
   * Pattern filter used to apply pattern entered by the end-user on the common viewer.
   */
  private CapellaNavigatorPatternFilter patternFilter;

  private TabbedPropertySheetPage propertySheetPage;

  /**
   * Dialog settings for this view.
   */
  private IDialogSettings _viewSettings;

  // caches the contentProvider to speed up search
  private CapellaNavigatorContentProvider _contentProvider;

  /**
   * Constructs the Capella common navigator.
   */
  public CapellaCommonNavigator() {
    // Get the dialog settings section for this view.
    _viewSettings = getDialogSettingsSection();
  }

  /**
   * Contribute view's toolbar actions.
   */
  protected void contributeToolbarActions() {
    IViewSite site = getViewSite();
    // Get the view toolbar manager.
    IToolBarManager toolBarManager = site.getActionBars().getToolBarManager();
    // Get already contributed actions.
    IContributionItem[] items = toolBarManager.getItems();
    // Remove all contributions to change the items order.
    toolBarManager.removeAll();

    // Add the move up & down actions here to get them before action
    // provider initialization to avoid tool bar recomputation.
    _moveUp = new MoveUpAction();
    toolBarManager.add(_moveUp);
    SelectionHelper.registerToSelectionChanges(_moveUp, site.getSelectionProvider());
    _moveDown = new MoveDownAction();
    toolBarManager.add(_moveDown);
    SelectionHelper.registerToSelectionChanges(_moveDown, site.getSelectionProvider());

    toolBarManager.add(new Separator());
    // Add initial contributions.
    for (IContributionItem contributionItem : items) {
      toolBarManager.add(contributionItem);
    }

    // Update enablement state.
    IStructuredSelection selection = (IStructuredSelection) site.getSelectionProvider().getSelection();
    _moveUp.selectionChanged(selection);
    _moveDown.selectionChanged(selection);
  }

  /**
   * @see org.eclipse.ui.navigator.CommonNavigator#createCommonViewer(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected CommonViewer createCommonViewer(Composite parent) {
    patternFilter = new CapellaNavigatorPatternFilter();
    patternFilter.setStringMatcherFactory(new StringMatcherFactory() {
      @Override
      public StringMatcher createStringMatcher(String pattern) {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        return new StringMatcher(pattern, store.getBoolean(ICapellaNavigatorPreferences.PREFERENCE_IGNORE_CASE), false);
      }
    });
    _filteredTree = new CapellaFilteredTree(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL, patternFilter);
    final CapellaCommonViewer commonViewer = (CapellaCommonViewer) _filteredTree.getViewer();

    initListeners(commonViewer);
    commonViewer.getNavigatorContentService().restoreState(memento);
    // Listen to changes on "group tree items" preferences to refresh the
    // viewer.
    SiriusTransPlugin.getPlugin().getPreferenceStore().addPropertyChangeListener(new IPropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent event) {
        String propertyName = event.getProperty();
        if (CommonPreferencesConstants.PREF_GROUP_ENABLE.equals(propertyName) || CommonPreferencesConstants.PREF_GROUP_TRIGGER.equals(propertyName)
            || CommonPreferencesConstants.PREF_GROUP_SIZE.equals(propertyName)) {
          commonViewer.refresh();
        }
      }
    });

    return commonViewer;
  }

  /**
   * @see org.eclipse.ui.navigator.CommonNavigator#createPartControl(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public void createPartControl(Composite parent) {

    // Create a composite that hosts the view content.
    Composite composite = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    composite.setLayout(layout);
    composite.setLayoutData(new GridData(GridData.FILL_BOTH));
    super.createPartControl(composite);
    // Set a layout data for the common tree viewer.
    CommonViewer commonViewer = getCommonViewer();
    commonViewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));

    // Install a session manager listener.
    if (null == _sessionManagerListener) {
      _sessionManagerListener = new NavigatorSessionManagerListener(this);
    }

    // Add Capella actions in view's toolbar.
    contributeToolbarActions();
    // Install as property change listener.
    Activator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
    Activator.getDefault().addProjectsPropertyChangeListener(this);

  }

  /**
   * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
   */
  @Override
  public void propertyChange(PropertyChangeEvent event) {
    String property = event.getProperty();
    if (ICapellaNavigatorPreferences.PREFERENCE_SHOW_CAPELLA_PROJECT_CONCEPT.equals(property)
        || ICapellaNavigatorPreferences.PREFERENCE_PART_EXPLICIT_VIEW.equals(property)) {
      // Get all active sessions.
      Iterator<Session> iterator = SessionManager.INSTANCE.getSessions().iterator();
      // Iterate over sessions to refresh their UI representations.
      while (iterator.hasNext()) {
        Session session = iterator.next();
        if (PreferencesHelper.isNonReferencesCapellaProject(event.getSource(), SessionHelper.getCapellaProject(session), session)) {
          if (null != _sessionManagerListener) {
            _sessionManagerListener.notifyUpdatedSession(session);
          }
        }
      }
    }
  }

  /**
   * Re-enable content notifications.<br>
   * This is the default behavior, thus this method should not be called at creation time.
   */
  public void enableContentNotifications() {
    // ...and notifications.
    CapellaNavigatorContentProvider contentProvider = getContentProvider();
    if (null == contentProvider) {
      return;
    }
    ActiveSessionManager.getInstance().enableContentNotifications();
  }

  public void disableContentNotifications() {
    // ...and notifications.
    CapellaNavigatorContentProvider contentProvider = getContentProvider();
    if (null == contentProvider) {
      return;
    }
    ActiveSessionManager.getInstance().disableContentNotifications();
  }

  /**
   * Re-enable content notifications.<br>
   * This is the default behavior, thus this method should not be called at creation time.
   */
  public void enableContentNotifications(SemanticEditingDomain editingDomain) {
    // ...and notifications.
    CapellaNavigatorContentProvider contentProvider = getContentProvider();
    if (null == contentProvider) {
      return;
    }
    ActiveSessionManager.getInstance().enableContentNotifications(editingDomain);
  }

  /**
   * Disable content notifications until {@link #enableContentNotifications()} is called.
   */
  public void disableContentNotifications(SemanticEditingDomain editingDomain) {
    // ...and notifications.
    CapellaNavigatorContentProvider contentProvider = getContentProvider();
    if (null == contentProvider) {
      return;
    }
    ActiveSessionManager.getInstance().disableContentNotifications(editingDomain);
  }

  /**
   * @see org.eclipse.ui.navigator.CommonNavigator#dispose()
   */
  @Override
  public void dispose() {
    ISelectionProvider selectionProvider = getViewSite().getSelectionProvider();
    if (null != _moveUp) {
      selectionProvider.removeSelectionChangedListener(_moveUp);
      _moveUp = null;
    }
    if (null != _moveDown) {
      selectionProvider.removeSelectionChangedListener(_moveDown);
      _moveDown = null;
    }
    if (null != _sessionManagerListener) {
      _sessionManagerListener.dispose();
      _sessionManagerListener = null;
    }

    NavigatorEditingDomainDispatcher.unregisterCommandStackSelectionProvider(this);

    // Remove as property change listener
    CapellaNavigatorPlugin.getDefault().getPreferenceStore().removePropertyChangeListener(this);
    // Save view settings.
    saveViewSettings();
    super.dispose();
  }

  /**
   * @see org.eclipse.ui.navigator.CommonNavigator#getAdapter(java.lang.Class)
   */
  @Override
  @SuppressWarnings("unchecked")
  public <T> T getAdapter(Class<T> adapter) {
    if (IPropertySheetPage.class.equals(adapter)) {
      return (T) getPropertySheetPage();
    }
    return super.getAdapter(adapter);
  }

  /**
   * @see org.eclipse.ui.navigator.CommonNavigator#getCommonViewer()
   */
  @Override
  public CapellaCommonViewer getCommonViewer() {
    return (CapellaCommonViewer) super.getCommonViewer();
  }

  /**
   * Return the content provider used by this view.
   * @return
   */
  public CapellaNavigatorContentProvider getContentProvider() {
    if ((null != _contentProvider) && (null != _contentProvider.getSessionContentProvider())) {
      return _contentProvider;
    }
    // Get the navigator content service.
    INavigatorContentService navigatorContentService = getCommonViewer().getNavigatorContentService();
    // Get the content extension by id
    INavigatorContentExtension contentExt = navigatorContentService.getContentExtensionById(CapellaNavigatorContentProvider.CONTENT_EXTENSION_ID);
    ITreeContentProvider contentProvider = contentExt.getContentProvider();
    _contentProvider = contentProvider instanceof CapellaNavigatorContentProvider ? (CapellaNavigatorContentProvider) contentProvider : null;
    return _contentProvider;
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor#getContributorId()
   */
  @Override
  public String getContributorId() {
    return CapellaUIPropertiesPlugin.PROPERTIES_CONTRIBUTOR;
  }

  /**
   * Get dialog settings for this view.
   * @return
   */
  private IDialogSettings getDialogSettingsSection() {
    IDialogSettings dialogSettings = CapellaNavigatorPlugin.getDefault().getDialogSettings();
    String sectionName = getClass().getName();
    // Get the dialog setting for this view.
    IDialogSettings section = dialogSettings.getSection(sectionName);
    if (null == section) {
      section = dialogSettings.addNewSection(sectionName);
    }
    return section;
  }

  /**
   * Return the pattern filter.
   * @return the patternFilter
   */
  protected CapellaNavigatorPatternFilter getPatternFilter() {
    return patternFilter;
  }

  /**
   * Gets the property sheet page.
   */
  private IPropertySheetPage getPropertySheetPage() {
    if (null == propertySheetPage) {
      propertySheetPage = new CapellaTabbedPropertySheetPage(this) {
        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("synthetic-access")
        @Override
        public void dispose() {
          super.dispose();
          propertySheetPage = null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void init(IPageSite pageSite) {
          super.init(pageSite);
          pageSite.setSelectionProvider(getCommonViewer());
        }
      };
    }
    return propertySheetPage;
  }

  /**
   * @see org.eclipse.ui.navigator.CommonNavigator#handleDoubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
   */
  @Override
  protected void handleDoubleClick(DoubleClickEvent event) {
    super.handleDoubleClick(event);
    // Add an additional behavior for ModelElement selection.
    IStructuredSelection selection = (IStructuredSelection) event.getSelection();
    Object element = selection.getFirstElement();

    if (CapellaResourceHelper.isSemanticElement(element)) {
      CapellaUIPropertiesPlugin.getDefault().openWizard(event, (EObject) element);
    }
  }

  /**
   * @see org.eclipse.ui.navigator.CommonNavigator#init(org.eclipse.ui.IViewSite, org.eclipse.ui.IMemento)
   */
  @Override
  public void init(IViewSite site, IMemento memento) throws PartInitException {
    // Specified memento could be null :
    // 1) if the view was not shown when the previous workbench session
    // exited.
    // 2) the view is open by the end-user whereas the workbench is already
    // loaded.
    // Parent class does not a provide an accessor on memento field (Eclipse
    // 3.3).
    // As of 3.5, getMemento is provided.
    memento = restoreViewSettings(memento);
    super.init(site, memento);
    // Add a command stack listener to select and reveal affected objects by
    // the most recent command.

  }

  /**
   * Restore view settings.
   */
  private IMemento restoreViewSettings(IMemento memento) {
    IMemento memento_l = memento;
    // Specified memento is null, let's get it from view settings
    // persistence.
    if (null == memento_l) {
      // Indeed, if the view was not shown when the previous workbench
      // session exited, no memento is provided.
      // The only chance to restore current state is to get the memento
      // from its persisted representation in view settings (if any).
      String persistedMemento = _viewSettings.get(TAG_MEMENTO);
      if (null != persistedMemento) {
        try {
          memento_l = XMLMemento.createReadRoot(new StringReader(persistedMemento));
        } catch (WorkbenchException exception) {
          // Don't do anything. Simply don't restore the settings
        }
      }
    }
    return memento_l;
  }

  /**
   * Save the dialog settings for this view.
   */
  private void saveViewSettings() {
    String rootName = getClass().getSimpleName();
    // Create a new memento.
    XMLMemento memento_l = XMLMemento.createWriteRoot(rootName);
    // Save current state in it.
    // Notice, that the saveState() method is also called by the workbench
    // when exiting before the dispose() method.
    // Nevertheless, we keep this call here, to make sure current state is
    // stored within a running workbench session where the saveState()
    // method is not called.
    saveState(memento_l);
    StringWriter writer = new StringWriter();
    try {
      memento_l.save(writer);
      _viewSettings.put(TAG_MEMENTO, writer.getBuffer().toString());
    } catch (IOException exception) {
      // Don't do anything. Simply don't store the settings
    }
  }

  /**
   * FIXME MA01 - ensure Sirius-2776 is now fixed {@inheritDoc}
   */
  @Override
  public Saveable[] getActiveSaveables() {
    // Used by "Save" action.
    return super.getActiveSaveables();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Saveable[] getSaveables() {
    // Used by "Save All" action.
    return super.getSaveables();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void commandStackSelectionChanged(ISelection selection) {
    boolean enabled = true;
    if (selection instanceof IStructuredSelection) {
      TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(((IStructuredSelection) selection).getFirstElement());
      enabled = ActiveSessionManager.getInstance().isEnabledContentNotifications(domain);
    }
    if (enabled) {
      selectReveal(selection);
    }
  }

  /**
   * The returned editing domain depends on the current selection.<br/>
   * Returns <code>null</code> if the selection contains elements from different sessions (each session have its own editing domain), otherwise the session's editing domain of the selection.</br>
   * 
   * {@inheritDoc}
   */
  @Override
  public EditingDomain getEditingDomain() {
	  ISelection selection = getCommonViewer().getSelection();
	  if (selection instanceof IStructuredSelection) {
	    Collection<EObject> selectedElements = new ArrayList<EObject>();

		  Iterator<?> it = ((IStructuredSelection) selection).iterator();
      while(it.hasNext()) {
         Object element = it.next();
         if (element instanceof EObject) {
           selectedElements.add((EObject) element);
         }
      }

		  return TransactionHelper.getEditingDomain(selectedElements);
	  }
	  return null;
  }
}
