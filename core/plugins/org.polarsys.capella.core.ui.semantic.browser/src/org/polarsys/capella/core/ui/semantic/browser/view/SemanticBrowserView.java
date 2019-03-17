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
package org.polarsys.capella.core.ui.semantic.browser.view;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyTitle;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.services.helper.ViewerHelper;
import org.polarsys.capella.common.ui.services.swt.events.AbstractKeyAdapter;
import org.polarsys.capella.common.ui.toolkit.browser.action.BrowserHistory;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.factory.AbstractContentProviderFactory;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.impl.AbstractContentProvider;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.BrowserElementWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.CategoryWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.label.provider.factory.AbstractLabelProviderFactory;
import org.polarsys.capella.common.ui.toolkit.browser.model.ISemanticBrowserModel;
import org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart;
import org.polarsys.capella.common.ui.toolkit.viewers.DelegateSelectionProviderWrapper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.model.handler.provider.IReadOnlyListener;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.CapellaNavigatorLabelProvider;
import org.polarsys.capella.core.ui.properties.CapellaTabbedPropertySheetPage;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.semantic.browser.CapellaBrowserActivator;
import org.polarsys.capella.core.ui.semantic.browser.CapellaBrowserPreferences;
import org.polarsys.capella.core.ui.semantic.browser.actions.RefreshAction;
import org.polarsys.capella.core.ui.semantic.browser.actions.SemanticBrowserActionFactory;
import org.polarsys.capella.core.ui.semantic.browser.model.SemanticBrowserModel;

/**
 * Browser Semantic View. Load by extension point.
 */
public abstract class SemanticBrowserView extends ViewPart implements ISemanticBrowserViewPart,
    ITabbedPropertySheetPageContributor, IEditingDomainProvider, IReadOnlyListener {

  /**
   * Listener that listens to closing and closed session events.
   */
  protected class CloseSessionListener extends SessionManagerListener.Stub {
    @Override
    public void notify(final Session updated, final int notification) {
      switch (notification) {
      case SessionListener.CLOSING:
        Object currentInput = getCurrentViewer().getInput();
        if (currentInput instanceof EObject) {
          // Get the session of current displayed object.
          Session session = SessionManager.INSTANCE.getSession((EObject) currentInput);
          if (updated.equals(session)) {
            clean();
          }
        }
        for (BrowserHistory.BrowserNavigationHistoryEntry entry : getHistory().getAllNavigationEntries()) {
          if (entry.getRealObject() instanceof EObject
              && updated == SessionManager.INSTANCE.getSession((EObject) entry.getRealObject())) {
            entry.invalidate();
          }
        }
        break;
      case SessionListener.CLOSED:
        // Update history to clean dead entries.
        getHistory().update(null);
        break;
      default:
        break;
      }
    }
  }

  private static final Logger logger = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.UI);
  /**
   * Category name displayed in Current Viewer.<br>
   * See definition in org.polarsys.capella.core.semantic.queries.sirius plugin.xml file in appropriate extension.
   */
  private static final String ALL_RELATED_DIAGRAMS = "All Related Diagrams"; //$NON-NLS-1$
  /**
   * Category name displayed in Current Viewer.<br>
   * See definition in org.polarsys.capella.core.semantic.queries.sirius plugin.xml file in appropriate extension.
   */
  private static final String ALL_RELATED_TABLES = "All Related Tables"; //$NON-NLS-1$
  /**
   * Constant used to persist if the view is listening workbench page selection events.
   */
  private static final String LISTENING_TO_WORKBENCH_PAGE_SELECTION_EVENTS = "listeningToWorkbenchPageSelectionEvents"; //$NON-NLS-1$
  /**
   * Text of the label for Referencing Elements browser.
   */
  private static final String REFERENCING_ELEMENTS_LABEL_TXT = Messages.SemanticBrowserView_Referencing_Elements_Title;

  /**
   * Semantic browser id.
   */
  public static final String SEMANTIC_BROWSER_ID = "org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserID"; //$NON-NLS-1$
  /**
   * Memento persistence tag.
   */
  private static final String TAG_MEMENTO = "memento"; //$NON-NLS-1$

  /**
   * Default viewers embedded into the view.
   */
  private TreeViewer currentViewer;
  private TreeViewer referencedViewer;
  private TreeViewer referencingViewer;

  private DelegateSelectionProviderWrapper delegateSelectionProvider;

  /**
   * Navigation history.
   */
  private BrowserHistory history;

  /**
   * Is CTRL key pressed when a double click is emitted ?
   */
  private boolean isCtrlKeyPressed;

  /**
   * Used to drive setFocus from setInput.
   */
  private boolean shouldSetFocus;

  private boolean isVisible;
  private boolean isLinkedToSelection;
  private IPartListener2 partListener;
  protected ISemanticBrowserModel model;
  private ISelectionListener selectionListener;
  private SessionManagerListener sessionListener;
  private TabbedPropertyTitle semanticBrowserTitle;
  private TabbedPropertySheetPage propertySheetPage;
  private IDoubleClickListener viewerDoubleClickListener;
  private ISelectionChangedListener viewerSelectionListener;

  /**
   * Constructor.
   */
  public SemanticBrowserView() {
    model = new SemanticBrowserModel();
  }

  @Override
  public ISemanticBrowserModel getModel() {
    return model;
  }

  /**
   * Activate the listening to page selection events.
   */
  public void activateListeningToPageSelectionEvents() {
    if (!isLinkedToSelection) {
      getSite().getPage().addSelectionListener(getSelectionListener());
      isLinkedToSelection = true;
      getModel().setListeningToPageSelectionEvents(isVisible);
    }
  }

  /**
   * Deactivate listening to page selection events.
   */
  public void deactivateListeningToPageSelectionEvents() {
    if (isLinkedToSelection) {
      getSite().getPage().removeSelectionListener(getSelectionListener());
      isLinkedToSelection = false;
      getModel().setListeningToPageSelectionEvents(false);
    }
  }

  /**
   * Adds drag support to given viewer. The drag transfer type is LocalSelectionTransfer.
   * 
   * @param viewer
   */
  protected void addDndDragSupport(final TreeViewer viewer) {
    int operations = DND.DROP_MOVE | DND.DROP_COPY;
    Transfer[] transferTypes = new Transfer[] { LocalSelectionTransfer.getTransfer() };
    // The DragSourceListener implementation is inspired by
    // org.eclipse.debug.internal.ui.views.variables.SelectionDragAdapter.
    viewer.addDragSupport(operations, transferTypes, new DragSourceListener() {
      /**
       * {@inheritDoc}
       */
      @Override
      public void dragSetData(DragSourceEvent event) {
        event.data = LocalSelectionTransfer.getTransfer().getSelection();
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public void dragStart(DragSourceEvent event) {
        // Check selection to drag is a CapellaElement.
        ISelection selection = viewer.getSelection();
        if (selection instanceof IStructuredSelection
            && CapellaResourceHelper.isSemanticElements(((IStructuredSelection) selection).toList())) {
          // Fill LocalSelectionTransfer.
          LocalSelectionTransfer.getTransfer().setSelection(selection);
          LocalSelectionTransfer.getTransfer().setSelectionSetTime(event.time & 0xFFFFFFFFL);
          // Allow drag operation.
          event.doit = true;
        } else {
          // Forbid drag operation.
          event.doit = false;
        }
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public void dragFinished(DragSourceEvent event) {
        // Clean LocalSelectionTranfer.
        LocalSelectionTransfer.getTransfer().setSelection(null);
        LocalSelectionTransfer.getTransfer().setSelectionSetTime(0);
      }
    });
  }

  /**
   * <p>
   * Adds a double click listeners to the given viewer.
   * </p>
   * 
   * @param viewer
   *          The viewer
   */
  protected void addListeners(final TreeViewer viewer) {
    // Lazy creation pattern.
    if (null == viewerSelectionListener) {
      viewerSelectionListener = new ISelectionChangedListener() {
        @Override
        public void selectionChanged(SelectionChangedEvent event) {
          ISelectionProvider provider = event.getSelectionProvider();
          updateSelectionProvider(provider);
          refreshPropertyPage(provider);
          updateStatusLine(provider.getSelection());
        }
      };
    }
    // Register the listener.
    viewer.addSelectionChangedListener(viewerSelectionListener);

    // Add a focus listener to update the selection provider.
    viewer.getControl().addFocusListener(new FocusAdapter() {
      @SuppressWarnings("synthetic-access")
      @Override
      public void focusGained(FocusEvent event) {
        Object source = event.getSource();
        ISelectionProvider newSelectionProvider = null;
        if (source.equals(SemanticBrowserView.this.currentViewer.getControl())) {
          newSelectionProvider = SemanticBrowserView.this.currentViewer;
        } else if (source.equals(referencedViewer.getControl())) {
          newSelectionProvider = referencedViewer;
        } else if (source.equals(referencingViewer.getControl())) {
          newSelectionProvider = referencingViewer;
        }
        if (null != newSelectionProvider) {
          updateSelectionProvider(newSelectionProvider);
          refreshPropertyPage(newSelectionProvider);
          try {
            getViewSite().getPage().showView(SEMANTIC_BROWSER_ID, null, org.eclipse.ui.IWorkbenchPage.VIEW_CREATE);
          } catch (PartInitException e) {
            logger.error(e.getMessage());
          }
        }
      }
    });

    // Lazy creation pattern.
    if (null == viewerDoubleClickListener) {
      viewerDoubleClickListener = new IDoubleClickListener() {
        @SuppressWarnings("synthetic-access")
        @Override
        public void doubleClick(DoubleClickEvent event) {
          try {
            handleDoubleClick(event);
          } catch (RuntimeException exception) {
            StringBuilder loggerMessage = new StringBuilder("SemanticBrowserView.addListeners(..) _ "); //$NON-NLS-1$
            loggerMessage.append(exception.getMessage());
            logger.error(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI), exception);
          }
        }
      };
    }
    // Register the listener.
    viewer.addDoubleClickListener(viewerDoubleClickListener);
    viewer.getControl().addKeyListener(new AbstractKeyAdapter() {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void keyPressed(KeyEvent keyEvent) {
        if (isCtrlPressed(keyEvent)) {
          isCtrlKeyPressed = true;
        }
      }

      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void keyReleased(KeyEvent keyEvent) {
        if (isCtrlPressed(keyEvent)) {
          isCtrlKeyPressed = false;
        }
      }
    });
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart#clean()
   */
  @Override
  public void clean() {
    // No need to set focus.
    boolean restoreState = shouldSetFocus ? true : false;
    shouldSetFocus = false;
    setInput(null);
    if (restoreState) {
      shouldSetFocus = true;
    }
  }

  /**
   * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public void createPartControl(Composite parent) {
    // Create and set a layout on the parent.
    GridLayout layout = new GridLayout();
    // No blank space.
    layout.marginHeight = 0;
    layout.marginWidth = 0;
    layout.verticalSpacing = 0;
    layout.horizontalSpacing = 0;
    parent.setLayout(layout);
    // Creates the title composite
    semanticBrowserTitle = new TabbedPropertyTitle(parent, new TabbedPropertySheetWidgetFactory());
    GridData titleLayoutData = new GridData(GridData.FILL_HORIZONTAL);
    // Do grab excess vertical space.
    titleLayoutData.grabExcessVerticalSpace = false;
    semanticBrowserTitle.setLayoutData(titleLayoutData);
    // Sets a default name with no image
    semanticBrowserTitle.setTitle(Messages.SemanticBrowserView_Default_Name, null);

    // Create the main sash form that host inner viewers.
    SashForm mainSashForm = new SashForm(parent, SWT.HORIZONTAL);
    Layout gridLayoutTop = new GridLayout(3, true);
    mainSashForm.setLayout(gridLayoutTop);
    mainSashForm.setLayoutData(new GridData(GridData.FILL_BOTH));

    // Set the model in the AbstractContentProviderFactory
    AbstractContentProviderFactory.getInstance().setModel(model);

    // Initialize referencing viewer as first element of the main sash form.
    ViewerComparator comparator = new ViewerComparator();
    AbstractContentProvider treeProvider = (AbstractContentProvider) AbstractContentProviderFactory.getInstance()
        .getReferencingContentProvider();
    referencingViewer = createViewer(mainSashForm, REFERENCING_ELEMENTS_LABEL_TXT, 3, treeProvider.getBrowserId());
    initializeViewer(referencingViewer, treeProvider,
        AbstractLabelProviderFactory.getInstance().getReferencingLabelProvider(), comparator);

    // Create a sash form as second element of the main sash form.
    // Initialize current viewer as first element of the center sash form.
    treeProvider = (AbstractContentProvider) AbstractContentProviderFactory.getInstance().getCurrentContentProvider();
    this.currentViewer = createViewer(mainSashForm, Messages.SemanticBrowserView_Current_Element_Title, 3,
        treeProvider.getBrowserId());
    initializeViewer(this.currentViewer, treeProvider,
        AbstractLabelProviderFactory.getInstance().getCurrentLabelProvider(), new ViewerComparator() {
          /**
           * Overridden to force All Related Diagrams and All Related Tables to be located at the end of the tree.
           * {@inheritDoc}
           */
          @Override
          public int compare(Viewer viewer, Object e1, Object e2) {
            if ((e1 instanceof CategoryWrapper) && isRepresentationCategory((CategoryWrapper) e1)) {
              return 1;
            }
            if ((e2 instanceof CategoryWrapper) && isRepresentationCategory((CategoryWrapper) e2)) {
              return -1;
            }
            return super.compare(viewer, e1, e2);
          }

          /**
           * Is given category used to displayed diagrams or tables ?
           * 
           * @param categoryWrapper
           * @return
           */
          private boolean isRepresentationCategory(CategoryWrapper categoryWrapper) {
            ICategory category = (ICategory) (categoryWrapper).getElement();
            String categoryName = category.getName();
            return categoryName.equals(ALL_RELATED_DIAGRAMS) || categoryName.equals(ALL_RELATED_TABLES);
          }
        });

    // Initialize the referenced viewer as third element of the main sash
    // form.
    treeProvider = (AbstractContentProvider) AbstractContentProviderFactory.getInstance()
        .getReferencedContentProvider();
    referencedViewer = createViewer(mainSashForm, Messages.SemanticBrowserView_Referenced_Elements_Title, 3,
        treeProvider.getBrowserId());
    initializeViewer(referencedViewer, treeProvider,
        AbstractLabelProviderFactory.getInstance().getReferencedLabelProvider(), comparator);

    initializeContextMenus();
    // Create and set a delegate selection provider, initialized on current
    // viewer.
    List<ISelectionProvider> lstProvider = new ArrayList<>();
    lstProvider.add(this.currentViewer);
    lstProvider.add(referencedViewer);
    lstProvider.add(referencingViewer);

    delegateSelectionProvider = new DelegateSelectionProviderWrapper(lstProvider);
    delegateSelectionProvider.setActiveDelegate(this.currentViewer);
    getViewSite().setSelectionProvider(delegateSelectionProvider);
    addSessionListener();
    addPartListener();
    makeActions();
  }

  /**
   * Create a composite widget which contains a treeviewer.
   * 
   * @param parent
   * @param label
   * @param autoExpandLevel
   * @return the referenced treeviewer
   */
  protected TreeViewer createViewer(Composite parent, String label, int autoExpandLevel, String browserID) {
    BrowserComposite composite = new BrowserComposite(parent, model, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL, label,
        browserID);
    TreeViewer treeViewer = composite.getTreeviewer();
    treeViewer.setUseHashlookup(true);
    ColumnViewerToolTipSupport.enableFor(treeViewer, ToolTip.RECREATE);
    addListeners(treeViewer);
    addDndDragSupport(treeViewer);
    return treeViewer;
  }

  /**
   * @see org.eclipse.ui.part.WorkbenchPart#dispose()
   */
  @Override
  public void dispose() {
    // Unregister...
    TreeViewer currentTreeViewer = getCurrentViewer();
    if (null != currentTreeViewer) {
      Object currentInput = currentTreeViewer.getInput();
      if (null != currentInput) {
        CapellaReadOnlyHelper.unregister((EObject) currentInput, this);
      }
    }

    saveViewSettings();

    deactivateListeningToPageSelectionEvents();

    disposePropertySheetPage();

    removeViewersListeners();

    disposeHistory();

    removeSessionListener();

    removePartListener();

    model = null;
    super.dispose();
  }

  /**
   * @see org.eclipse.ui.navigator.CommonNavigator#getAdapter(java.lang.Class)
   */
  @SuppressWarnings("rawtypes")
  @Override
  public Object getAdapter(Class adapter) {
    if (IPropertySheetPage.class.equals(adapter)) {
      return getPropertySheetPage();
    } else if (Control.class.equals(adapter)) {
      return getParentControl();
    }
    return super.getAdapter(adapter);
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor#getContributorId()
   */
  @Override
  public String getContributorId() {
    return CapellaUIPropertiesPlugin.PROPERTIES_CONTRIBUTOR;
  }

  /**
   * @return the currentViewer
   */
  @Override
  public TreeViewer getCurrentViewer() {
    return this.currentViewer;
  }

  /**
   * Get dialog settings for this view.
   * 
   * @return
   */
  private IDialogSettings getDialogSettingsSection() {
    IDialogSettings dialogSettings = CapellaBrowserActivator.getDefault().getDialogSettings();
    String sectionName = getClass().getName();
    // Get the dialog setting for this view.
    IDialogSettings section = dialogSettings.getSection(sectionName);
    if (null == section) {
      section = dialogSettings.addNewSection(sectionName);
    }
    return section;
  }

  /**
   * @see org.eclipse.emf.edit.domain.IEditingDomainProvider#getEditingDomain()
   */
  @Override
  public EditingDomain getEditingDomain() {
    ISelection selection = getCurrentViewer().getSelection();
    if (selection instanceof IStructuredSelection) {
      Object elt = ((IStructuredSelection) selection).getFirstElement();
      if (elt instanceof EObject) {
        return TransactionHelper.getEditingDomain((EObject) elt);
      }
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart#getHistory()
   */
  @Override
  public BrowserHistory getHistory() {
    if (null == history) {
      history = new BrowserHistory();
    }
    return history;
  }

  /**
   * Get parent control, if accessible.
   * 
   * @return <code>null</code> if control could not be resolved.
   */
  protected Control getParentControl() {
    if (null != semanticBrowserTitle) {
      return semanticBrowserTitle.getParent();
    }
    return null;
  }

  /**
   * Gets the property sheet page.
   */
  protected TabbedPropertySheetPage getPropertySheetPage() {
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
          pageSite.setSelectionProvider(SemanticBrowserView.this.getViewSite().getSelectionProvider());
        }
      };
    }
    return propertySheetPage;
  }

  /**
   * @return the referencedViewer
   */
  @Override
  public TreeViewer getReferencedViewer() {
    return referencedViewer;
  }

  /**
   * @return the referencingViewer
   */
  @Override
  public TreeViewer getReferencingViewer() {
    return referencingViewer;
  }

  /**
   * Get the current viewer root element.
   * 
   * @see org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart#getRootElement()
   */
  @Override
  public EObject getRootElement() {
    return ((AbstractContentProvider) this.currentViewer.getContentProvider()).getRootElement();
  }

  public ISelectionListener getSelectionListener() {
    if (selectionListener == null) {
      selectionListener = createSelectionListener();
    }
    return selectionListener;
  }

  /**
   * Creates the selection listener to handle {@link IWorkbenchPage} selection events.
   * 
   * @return
   */
  protected ISelectionListener createSelectionListener() {
    return new ISelectionListener() {
      /**
       * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart,
       *      org.eclipse.jface.viewers.ISelection)
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        Object newInput = handleWorkbenchPageSelectionEvent(part, selection);
        // Set the selected object as new input only if it is an EObject
        if (newInput instanceof EObject) {
          // Avoid the property view to be selection provider.
          if (CapellaUIPropertiesPlugin.PROPERTIES_SHEET_VIEW_ID.equals(part.getSite().getId())) {
            return;
          }
          // Check the input is different from current one.
          try {
            shouldSetFocus = false;
            setInput(newInput);
          } finally {
            shouldSetFocus = true;
          }
        } else if ((part != SemanticBrowserView.this)
            && !CapellaUIPropertiesPlugin.PROPERTIES_SHEET_VIEW_ID.equals(part.getSite().getId())) { // //
          // Avoid the property view to be selection provider.
          // Event sent by another part apart from the Property Sheet view
          // Prevent from displaying elements not related to the workbench current selection.
          clean();
        }
      }
    };
  }

  /**
   * Handle double click event in the viewpart.
   * 
   * @param event
   */
  protected void handleDoubleClick(DoubleClickEvent event) {
    ITreeSelection selection = (ITreeSelection) event.getSelection();
    if (!selection.isEmpty()) {
      Object doubleClickedElement = selection.getFirstElement();
      // do nothing if element of the wrapper is the root element.
      // change
      if (doubleClickedElement instanceof BrowserElementWrapper) {
        doubleClickedElement = ((BrowserElementWrapper) doubleClickedElement).getElement();
      }
      // Handle a ModelElement double click event.
      if (doubleClickedElement instanceof EObject) {
        if (isCtrlKeyPressed) {
          if (getRootElement() != doubleClickedElement) {
            // CTRL is pressed, let's navigate...
            setInput(doubleClickedElement);
            // Set and reveal the focused element.
            this.currentViewer.setSelection(new StructuredSelection(doubleClickedElement), true);
          }
        } else {
          CapellaUIPropertiesPlugin.getDefault().openWizard(event, (EObject) doubleClickedElement);
        }
      }
    }
  }

  /**
   * Handle workbench page selection events.<br>
   * Default implementation returns <code>null</code>.
   * 
   * @param part
   * @param selection
   * @return <code>null</code> means no capella element found from selection.
   */
  protected Object handleWorkbenchPageSelectionEvent(IWorkbenchPart part, ISelection selection) {
    return null;
  }

  /**
   * @see org.eclipse.ui.part.ViewPart#init(org.eclipse.ui.IViewSite, org.eclipse.ui.IMemento)
   */
  @Override
  public void init(IViewSite site, IMemento memento) throws PartInitException {
    // Specified memento could be null :
    // 1) if the view was not shown when the previous workbench session
    // exited.
    // 2) the view is open by the end-user whereas the workbench is already
    // loaded.
    memento = restoreViewSettings(memento);
    super.init(site, memento);
    Integer value = null;
    if (null != memento) {
      // Get state of listening to Page selection events.
      value = memento.getInteger(LISTENING_TO_WORKBENCH_PAGE_SELECTION_EVENTS);
    }

    boolean isListeningOnStartup = !CapellaBrowserActivator.getDefault().getPreferenceStore()
        .getBoolean(CapellaBrowserPreferences.PREFS_DISABLE_SEMANTIC_BROWSER_SYNC_ON_STARTUP);
    isLinkedToSelection = null != value ? value.intValue() == 1 : isListeningOnStartup;
    getModel().setListeningToPageSelectionEvents(isLinkedToSelection && isVisible);
  }

  /**
   * Initialize a context menu for given viewer.
   * 
   * @param menuManagerText
   * @param menuManagerId
   * @param viewer
   */
  private void initializeContextMenu(String menuManagerText, String menuManagerId, TreeViewer viewer) {
    MenuManager menuManager = new MenuManager(menuManagerText, menuManagerId);
    menuManager.setRemoveAllWhenShown(true);
    Tree tree = viewer.getTree();
    Menu currentMenu = menuManager.createContextMenu(tree);
    tree.setMenu(currentMenu);
    getSite().registerContextMenu(menuManager, viewer);
  }

  /**
   * Initialize contextual menus of internal viewers.
   */
  private void initializeContextMenus() {
    initializeContextMenu("current#PopupMenu", null, this.currentViewer); //$NON-NLS-1$
    initializeContextMenu("referenced#PopupMenu", null, referencedViewer); //$NON-NLS-1$
    initializeContextMenu("referencing#PopupMenu", null, referencingViewer); //$NON-NLS-1$
  }

  /**
   * Initialize given viewer with specified parameter.<br>
   * The viewer layout data is set to a {@link GridData#FILL_BOTH}.
   * 
   * @param viewer
   * @param contentProvider
   * @param labelProvider
   * @param sorter
   */
  private void initializeViewer(TreeViewer viewer, IContentProvider contentProvider, IBaseLabelProvider labelProvider,
      ViewerComparator comparator) {
    viewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
    viewer.setContentProvider(contentProvider);
    viewer.setLabelProvider(labelProvider);
    viewer.setComparator(comparator);
  }

  /**
   * @return the isCtrlKeyPressed
   */
  protected boolean isCtrlKeyPressed() {
    return isCtrlKeyPressed;
  }

  protected IAction showPatternsAction;
  protected IAction showDiagramsAction;
  protected IAction limitateTreeExpansionAction;

  /**
   * Make actions.
   */
  protected void makeActions() {
    SemanticBrowserActionFactory factory = new SemanticBrowserActionFactory();
    factory.createBackAction(this);
    factory.createForwardAction(this);
    factory.createShowDiagramsAction(this);
    factory.createLimitateTreeExpansionAction(this);
    factory.createRefreshAction(this);
    factory.createListenToSelectionEventsAction(this, isLinkedToSelection);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart#refresh()
   */
  @Override
  public void refresh() {
    ViewerHelper.refresh(referencingViewer);
    ViewerHelper.refresh(referencedViewer);
    ViewerHelper.refresh(this.currentViewer);
  }

  protected void refreshPropertyPage(ISelectionProvider selectionProvider) {
    // Notify the property page to refresh with the new selection.
    // Be careful, the properties view can be closed, don't send it
    // selection changes.
    IStructuredSelection selection = (IStructuredSelection) selectionProvider.getSelection();
    if ((null != propertySheetPage) && (!propertySheetPage.getControl().isDisposed())) {
      ISelectionProvider pageSelectionProvider = propertySheetPage.getSite().getSelectionProvider();
      if ((null == pageSelectionProvider) || (pageSelectionProvider != selectionProvider)) {
        propertySheetPage.getSite().setSelectionProvider(selectionProvider);
      }
      propertySheetPage.selectionChanged(SemanticBrowserView.this, selection);
    }
  }

  public void updateStatusLine(ISelection selection) {
    if (selection instanceof IStructuredSelection) {
      Object selectedElement = ((IStructuredSelection) selection).getFirstElement();
      IStatusLineManager statusLineManager = getViewSite().getActionBars().getStatusLineManager();

      if (selectedElement instanceof EObject) {
        CapellaNavigatorLabelProvider semanticBrowserLabelProvider = new CapellaNavigatorLabelProvider(
            CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
        Image image = semanticBrowserLabelProvider.getImage(selectedElement);
        String text = semanticBrowserLabelProvider.getDescription(selectedElement);
        statusLineManager.setMessage(image, text);
      } else {
        statusLineManager.setMessage(null, ICommonConstants.EMPTY_STRING);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    // do nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void refreshTitleBar() {
    Object input = getCurrentViewer().getInput();
    refreshTitleBar(input);
    setInputOnViewers(input);
  }

  /**
   * @param input
   */
  @SuppressWarnings("synthetic-access")
  @Override
  public void setInputOnViewers(final Object input) {
    TreeViewer currentTreeViewer = getCurrentViewer();

    if ((currentTreeViewer != null)
        && ((currentTreeViewer.getControl() != null) && !(currentTreeViewer.getControl().isDisposed()))) {
      Display display = currentTreeViewer.getControl().getDisplay();

      BusyIndicator.showWhile(display, new Runnable() {
        @Override
        public void run() {

          // Broadcast "set input" signal to all viewers.
          ViewerHelper.run(referencingViewer, new Runnable() {
            @Override
            public void run() {
              if ((referencingViewer.getControl() != null) && !referencingViewer.getControl().isDisposed()) {
                referencingViewer.setInput(input);
              }
            }
          });
          ViewerHelper.run(referencedViewer, new Runnable() {
            @Override
            public void run() {
              if ((referencedViewer.getControl() != null) && !referencedViewer.getControl().isDisposed()) {
                referencedViewer.setInput(input);
              }
            }
          });
          ViewerHelper.run(SemanticBrowserView.this.currentViewer, new Runnable() {
            @Override
            public void run() {
              if ((SemanticBrowserView.this.currentViewer.getControl() != null)
                  && !SemanticBrowserView.this.currentViewer.getControl().isDisposed()) {
                SemanticBrowserView.this.currentViewer.setInput(input);
              }
            }
          });
        }
      });
    }
  }

  /**
   * Updates the title bar.
   */
  private void refreshTitleBar(Object selectedElement) {
    String title = Messages.SemanticBrowserView_Default_Name;
    Image image = null;
    if (null != selectedElement) {
      // The text to display
      title = AbstractLabelProviderFactory.getInstance().getCurrentLabelProvider().getText(selectedElement);
      // Get the metaclass label.
      String metaclassLabel = EObjectLabelProviderHelper.getMetaclassLabel(((EObject) selectedElement), true);
      if ((null != metaclassLabel) && !title.startsWith(metaclassLabel)) {
        title = metaclassLabel + title;
      }

      // The image to display
      image = AbstractLabelProviderFactory.getInstance().getCurrentLabelProvider().getImage(selectedElement);
    }
    if (!semanticBrowserTitle.isDisposed()) {
      semanticBrowserTitle.setRedraw(false);
      semanticBrowserTitle.setTitle(title, image);
      semanticBrowserTitle.setRedraw(true);
    }
  }

  /**
   * Remove listeners registered on given viewer.
   */
  protected void removeListeners(TreeViewer viewer) {
    viewer.removeDoubleClickListener(viewerDoubleClickListener);
    viewer.removeSelectionChangedListener(viewerSelectionListener);
  }

  /**
   * Restore view settings.
   */
  private IMemento restoreViewSettings(IMemento memento) {
    IMemento mementol = memento;
    // Specified memento is null, let's get it from view settings
    // persistence.
    if (null == mementol) {
      // Indeed, if the view was not shown when the previous workbench
      // session exited, no memento is provided.
      // The only chance to restore current state is to get the memento
      // from its persisted representation in view settings (if any).
      String persistedMemento = getDialogSettingsSection().get(TAG_MEMENTO);
      if (null != persistedMemento) {
        try {
          mementol = XMLMemento.createReadRoot(new StringReader(persistedMemento));
        } catch (WorkbenchException exception) {
          // Don't do anything. Simply don't restore the settings
        }
      }
    }
    return mementol;
  }

  /**
   * @see org.eclipse.ui.part.ViewPart#saveState(org.eclipse.ui.IMemento)
   */
  @Override
  public void saveState(IMemento memento) {
    memento.putInteger(LISTENING_TO_WORKBENCH_PAGE_SELECTION_EVENTS, isLinkedToSelection ? 1 : 0);
  }

  /**
   * Save the dialog settings for this view.
   */
  private void saveViewSettings() {
    String rootName = getClass().getSimpleName();
    // Create a new memento.
    XMLMemento rootMemento = XMLMemento.createWriteRoot(rootName);
    // Save current state in it.
    // Notice, that the saveState() method is also called by the workbench
    // when exiting before the dispose() method.
    // Nevertheless, we keep this call here, to make sure current state is
    // stored within a running workbench session where the saveState()
    // method is not called.
    saveState(rootMemento);
    StringWriter writer = new StringWriter();
    try {
      rootMemento.save(writer);
      getDialogSettingsSection().put(TAG_MEMENTO, writer.getBuffer().toString());
    } catch (IOException exception) {
      // Don't do anything. Simply don't store the settings
    }
  }

  /**
   * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
   */
  @Override
  public void setFocus() {
    ISelectionProvider selectionProvider = delegateSelectionProvider.getActiveDelegate();
    // Make sure the selection provider is tree viewer.
    if (selectionProvider instanceof TreeViewer) {
      ((TreeViewer) selectionProvider).getControl().setFocus();
    } else {
      this.currentViewer.getControl().setFocus();
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart#setInput(java.lang.Object)
   */
  @Override
  public final void setInput(final Object input) {
    // Precondition: do not set the same input twice.
    TreeViewer currentTreeViewer = getCurrentViewer();
    Object lastInput = currentTreeViewer.getInput();
    if ((null != lastInput) && (lastInput.equals(input))) {
      return;
    }
    refreshTitleBar(input);
    // Set the selection provider with currentViewer as selection provider.
    delegateSelectionProvider.setActiveDelegate(this.currentViewer);

    // Broadcast "set input" signal to all viewers.
    setInputOnViewers(input);

    CapellaReadOnlyHelper.unregister((EObject) lastInput, this);
    CapellaReadOnlyHelper.register((EObject) input, this);

    // Update history mechanism.
    getHistory().update(input);
    // Force to reset the focus and the underlying selection provider.
    // From platform selection changed event, the setFocus is disabled.
    if (shouldSetFocus) {
      // Set focus in another thread UI processing.
      setFocus();
    }
  }

  /**
   * Update the view site selection provider with specified one.
   * 
   * @param newSelectionProvider
   */
  protected void updateSelectionProvider(ISelectionProvider newSelectionProvider) {
    // Set the selection provider if necessary.
    ISelectionProvider currentSelectionProvider = delegateSelectionProvider.getActiveDelegate();
    if ((null == currentSelectionProvider) || (currentSelectionProvider != newSelectionProvider)) {
      delegateSelectionProvider.setActiveDelegate(newSelectionProvider);
    }
  }

  private void addSessionListener() {
    sessionListener = new CloseSessionListener();
    SessionManager.INSTANCE.addSessionsListener(sessionListener);
  }

  private void removeSessionListener() {
    SessionManager.INSTANCE.removeSessionsListener(sessionListener);
    sessionListener = null;
  }

  private void disposeHistory() {
    if (null != history) {
      history.dispose();
      history = null;
    }
  }

  private void disposePropertySheetPage() {
    if (null != propertySheetPage) {
      propertySheetPage.dispose();
      propertySheetPage = null;
    }
  }

  private void removeViewersListeners() {
    if (null != referencingViewer) {
      removeListeners(referencingViewer);
      referencingViewer = null;
    }
    if (null != referencedViewer) {
      removeListeners(referencedViewer);
      referencedViewer = null;
    }
    if (null != this.currentViewer) {
      removeListeners(this.currentViewer);
    }
    viewerSelectionListener = null;
    viewerDoubleClickListener = null;
  }

  private void addPartListener() {
    getSite().getPage().addPartListener(getPartListener());
  }

  private void removePartListener() {
    if (partListener != null) {
      getSite().getPage().removePartListener(getPartListener());
    }
  }

  private IPartListener2 getPartListener() {
    if (partListener == null) {
      partListener = createPartListener();
    }
    return partListener;
  }

  private IPartListener2 createPartListener() {
    return new IPartListener2() {

      @Override
      public void partActivated(IWorkbenchPartReference partRef) {
        // Do nothing by default
      }

      @Override
      public void partBroughtToTop(IWorkbenchPartReference partRef) {
        partVisible(partRef);
      }

      @Override
      public void partClosed(IWorkbenchPartReference partRef) {
        // Do nothing by default
      }

      @Override
      public void partDeactivated(IWorkbenchPartReference partRef) {
        // Do nothing by default
      }

      @Override
      public void partOpened(IWorkbenchPartReference partRef) {
        // Do nothing by default
      }

      @Override
      public void partHidden(IWorkbenchPartReference partRef) {
        if (partRef.getId().equals(getSite().getId())) {
          isVisible = false;
          getSite().getPage().removeSelectionListener(getSelectionListener());
          getModel().setListeningToPageSelectionEvents(false);
        }
      }

      @Override
      public void partVisible(IWorkbenchPartReference partRef) {
        if (partRef.getId().equals(getSite().getId())) {
          isVisible = true;
          getModel().setListeningToPageSelectionEvents(isLinkedToSelection && isVisible);
          if (isLinkedToSelection) {
            getSite().getPage().addSelectionListener(getSelectionListener());
            new RefreshAction(SemanticBrowserView.this).run();
          }
        }
      }

      @Override
      public void partInputChanged(IWorkbenchPartReference partRef) {
        // Do nothing by default
      }
    };
  }
}
