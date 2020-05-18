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
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.INavigatorContentExtension;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.eclipse.ui.navigator.INavigatorSaveablesService;
import org.eclipse.ui.navigator.SaveablesProvider;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.ShowInContext;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.commands.preferences.util.PreferencesHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateFilteredElementsInCommonNavigatorAction;
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

public class CapellaCommonNavigator extends CommonNavigator implements IEditingDomainProvider,
    ITabbedPropertySheetPageContributor, ICommandStackSelectionProvider, IPropertyChangeListener {

  /**
   * The project explorer view identifier.
   */
  public static final String ID = "capella.project.explorer"; //$NON-NLS-1$

  public static final int IS_SEARCH_IN_DESCRIPTION_ENABLED_PROPERTY = 0x10001;
  private boolean isSearchInDescriptionEnabled = false;

  public static final int IS_SEARCH_CASE_SENSITIVE_ENABLED_PROPERTY = 0x10002;
  private boolean isSearchCaseSensitiveEnabled = false;

  private static final String TAG_MEMENTO = "memento"; //$NON-NLS-1$

  private CapellaCommonNavigatorFilteredTree filteredTree;

  private NavigatorSessionManagerListener sessionManagerListener;

  private TabbedPropertySheetPage propertySheetPage;

  private IDialogSettings viewSettings;

  // caches the contentProvider to speed up search
  private CapellaNavigatorContentProvider _contentProvider;

  public CapellaCommonNavigator() {
    // Get the dialog settings section for this view.
    viewSettings = getDialogSettingsSection();
    NavigatorEditingDomainDispatcher.registerCommandStackSelectionProvider(this);
  }

  @Override
  protected CommonViewer createCommonViewerObject(Composite aParent) {
    filteredTree = new CapellaCommonNavigatorFilteredTree(aParent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL,
        new CapellaCommonNavigatorPatternFilter());
    filteredTree.setCaseSensitiveEnabled(isSearchCaseSensitiveEnabled);
    return (CommonViewer) filteredTree.getViewer();
  }

  @Override
  protected void initListeners(TreeViewer viewer) {
    super.initListeners(viewer);
    CapellaNavigatorPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(event -> {
      String propertyName = event.getProperty();
      if (CommonPreferencesConstants.PREF_GROUP_ENABLE.equals(propertyName)
          || CommonPreferencesConstants.PREF_GROUP_TRIGGER.equals(propertyName)
          || CommonPreferencesConstants.PREF_GROUP_SIZE.equals(propertyName)) {
        viewer.refresh();
      }
    });
  }

  @Override
  public void createPartControl(Composite parent) {

    // Create a composite that hosts the view content.
    Composite composite = new Composite(parent, SWT.NONE);
    FillLayout layout = new FillLayout(SWT.VERTICAL);
    composite.setLayout(layout);
    super.createPartControl(composite);

    // Install a session manager listener.
    if (null == sessionManagerListener) {
      sessionManagerListener = new NavigatorSessionManagerListener(this);
    }

    // Install as property change listener.
    Activator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
    Activator.getDefault().addProjectsPropertyChangeListener(this);
  }

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
        if (PreferencesHelper.isNonReferencesCapellaProject(event.getSource(), SessionHelper.getCapellaProject(session),
            session)) {
          if (null != sessionManagerListener) {
            sessionManagerListener.notifyUpdatedSession(session);
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

  @Override
  public void dispose() {
    if (null != sessionManagerListener) {
      sessionManagerListener.dispose();
      sessionManagerListener = null;
    }

    NavigatorEditingDomainDispatcher.unregisterCommandStackSelectionProvider(this);

    // Remove as property change listener
    CapellaNavigatorPlugin.getDefault().getPreferenceStore().removePropertyChangeListener(this);
    // Save view settings.
    saveViewSettings();
    super.dispose();
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T getAdapter(Class<T> adapter) {
    if (IPropertySheetPage.class.equals(adapter)) {
      return (T) getPropertySheetPage();
    }
    return super.getAdapter(adapter);
  }

  @Override
  public boolean show(ShowInContext context) {
    ISelection selection = context.getSelection();
    if (selection instanceof IStructuredSelection && !selection.isEmpty()) {
      IStructuredSelection structuredSelection = (IStructuredSelection) selection;

      // Before selecting, check if any active filter hides the selected element
      // If so, propose dialog to deactivate
      LocateFilteredElementsInCommonNavigatorAction locateFilteredElementsInCommonNavigatorAction = new LocateFilteredElementsInCommonNavigatorAction(
          getSite().getId());
      locateFilteredElementsInCommonNavigatorAction.run(structuredSelection);

      // Then select the elements
      selectReveal(structuredSelection);

      ISelection actualSelection = getCommonViewer().getSelection();

      // After doing selection, if no element was selected, then no element found.
      if (actualSelection == null || actualSelection.isEmpty()) {
        MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
            NLS.bind(Messages.LocateInCommonNavigator_SelectedElementNotVisible_Title, getPartName()),
            NLS.bind(Messages.LocateInCommonNavigator_SelectedElementNotVisible_2, getPartName()));
      }

      return true;
    }

    return false;
  }

  public CapellaNavigatorContentProvider getContentProvider() {
    if ((null != _contentProvider) && (null != _contentProvider.getSessionContentProvider())) {
      return _contentProvider;
    }
    // Get the navigator content service.
    INavigatorContentService navigatorContentService = getCommonViewer().getNavigatorContentService();
    // Get the content extension by id
    INavigatorContentExtension contentExt = navigatorContentService
        .getContentExtensionById(CapellaNavigatorContentProvider.CONTENT_EXTENSION_ID);
    ITreeContentProvider contentProvider = contentExt.getContentProvider();
    _contentProvider = contentProvider instanceof CapellaNavigatorContentProvider
        ? (CapellaNavigatorContentProvider) contentProvider
        : null;
    return _contentProvider;
  }

  @Override
  public String getContributorId() {
    return CapellaUIPropertiesPlugin.PROPERTIES_CONTRIBUTOR;
  }

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

  private IPropertySheetPage getPropertySheetPage() {
    if (null == propertySheetPage) {
      propertySheetPage = new CapellaTabbedPropertySheetPage(this) {
        /**
         * {@inheritDoc}
         */
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

  private IMemento restoreViewSettings(IMemento memento) {
    IMemento memento1 = memento;
    // Specified memento is null, let's get it from view settings
    // persistence.
    if (null == memento1) {
      // Indeed, if the view was not shown when the previous workbench
      // session exited, no memento is provided.
      // The only chance to restore current state is to get the memento
      // from its persisted representation in view settings (if any).
      String persistedMemento = viewSettings.get(TAG_MEMENTO);
      if (null != persistedMemento) {
        try {
          memento1 = XMLMemento.createReadRoot(new StringReader(persistedMemento));
        } catch (WorkbenchException exception) {
          // Don't do anything. Simply don't restore the settings
        }
      }
    }
    return memento1;
  }

  private void saveViewSettings() {
    String rootName = getClass().getSimpleName();
    // Create a new memento.
    XMLMemento memento1 = XMLMemento.createWriteRoot(rootName);
    // Save current state in it.
    // Notice, that the saveState() method is also called by the workbench
    // when exiting before the dispose() method.
    // Nevertheless, we keep this call here, to make sure current state is
    // stored within a running workbench session where the saveState()
    // method is not called.
    saveState(memento1);
    StringWriter writer = new StringWriter();
    try {
      memento1.save(writer);
      viewSettings.put(TAG_MEMENTO, writer.getBuffer().toString());
    } catch (IOException exception) {
      // Don't do anything. Simply don't store the settings
    }
  }

  @Override
  public Saveable[] getActiveSaveables() {
    // INavigatorSaveablesService has the logic to retrieve, from the selection, the Saveables concerned by the save
    // action.
    INavigatorSaveablesService navigatorSaveablesService = this.getCommonViewer().getNavigatorContentService()
        .getSaveablesService();
    return navigatorSaveablesService.getActiveSaveables();
  }

  @Override
  public Saveable[] getSaveables() {
    SaveablesProvider saveablesProvider = (SaveablesProvider) this.getContentProvider()
        .getAdapter(SaveablesProvider.class);
    return saveablesProvider.getSaveables();
  }

  @Override
  public void commandStackSelectionChanged(ISelection selection) {
    boolean enabled = true;
    if (selection instanceof IStructuredSelection) {
      TransactionalEditingDomain domain = TransactionUtil
          .getEditingDomain(((IStructuredSelection) selection).getFirstElement());
      enabled = ActiveSessionManager.getInstance().isEnabledContentNotifications(domain);
    }
    if (enabled) {
      selectReveal(selection);
    }
  }

  /**
   * The returned editing domain depends on the current selection.<br/>
   * Returns <code>null</code> if the selection contains elements from different sessions (each session have its own
   * editing domain), otherwise the session's editing domain of the selection.</br>
   * 
   * {@inheritDoc}
   */
  @Override
  public EditingDomain getEditingDomain() {
    ISelection selection = getCommonViewer().getSelection();
    if (selection instanceof IStructuredSelection) {
      Collection<EObject> selectedElements = new ArrayList<>();

      Iterator<?> it = ((IStructuredSelection) selection).iterator();
      while (it.hasNext()) {
        Object element = it.next();
        if (element instanceof EObject) {
          selectedElements.add((EObject) element);
        }
      }

      return TransactionHelper.getEditingDomain(selectedElements);
    }
    return null;
  }

  @Override
  protected ActionGroup createCommonActionGroup() {
    return new CapellaCommonNavigatorActionGroup(this, getCommonViewer(), getLinkHelperService());
  }

  public boolean isSearchInDescriptionEnabled() {
    return isSearchInDescriptionEnabled;
  }

  public boolean isSearchCaseSensitiveEnabled() {
    return isSearchCaseSensitiveEnabled;
  }

  public void setSearchInDescriptionEnabled(boolean isSearchInDescriptionEnabled) {
    this.isSearchInDescriptionEnabled = isSearchInDescriptionEnabled;
    filteredTree.setSearchInDescriptionEnabled(isSearchInDescriptionEnabled);
    firePropertyChange(IS_SEARCH_IN_DESCRIPTION_ENABLED_PROPERTY);
  }

  public void setSearchCaseSensitiveEnabled(boolean isSearchCaseSensitiveEnabled) {
    this.isSearchCaseSensitiveEnabled = isSearchCaseSensitiveEnabled;
    filteredTree.setCaseSensitiveEnabled(isSearchCaseSensitiveEnabled);
    firePropertyChange(IS_SEARCH_CASE_SENSITIVE_ENABLED_PROPERTY);
  }

  public void refreshViewer() {
    filteredTree.refresh();
  }
}
