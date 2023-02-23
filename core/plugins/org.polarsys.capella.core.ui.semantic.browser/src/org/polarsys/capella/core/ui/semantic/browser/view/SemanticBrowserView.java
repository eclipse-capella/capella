/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
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
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.sirius.business.api.query.SiriusReferenceFinderCache;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
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
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyTitle;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.TransactionHelper;
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
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.model.handler.provider.IReadOnlyListener;
import org.polarsys.capella.core.ui.properties.CapellaTabbedPropertySheetPage;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.semantic.browser.CapellaBrowserActivator;
import org.polarsys.capella.core.ui.semantic.browser.CapellaBrowserPreferences;
import org.polarsys.capella.core.ui.semantic.browser.actions.SemanticBrowserActionFactory;
import org.polarsys.capella.core.ui.semantic.browser.handler.AbstractSemanticBrowserDoubleClickHandler;
import org.polarsys.capella.core.ui.semantic.browser.handler.DefaultSemanticBrowserDoubleClickHandler;
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

    Runnable cleaner = new Runnable() {
      @Override
      public void run() {
        clean();
      }
    };

    @Override
    public void notify(final Session updated, final int notification) {
      switch (notification) {
      case SessionListener.CLOSING:
        Object currentInput = getCurrentViewer().getInput();
        if (currentInput instanceof EObject) {
          // Get the session of current displayed object.
          Session session = SessionManager.INSTANCE.getSession((EObject) currentInput);
          if (updated.equals(session)) {
            if (Display.getCurrent() == null) {
              EclipseUIUtil.displayAsyncExec(cleaner);
            } else {
              cleaner.run();
            }
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
   * The id of the Semantic Browser View context, that allows bindings to be active only when this context is active.
   */
  private static final String BINDING_CONTEXT_ID = "org.polarsys.capella.core.ui.semantic.browser.context"; //$NON-NLS-1$

  /**
   * Default viewers embedded into the view.
   */
  private TreeViewer currentViewer;
  private TreeViewer referencedViewer;
  private TreeViewer referencingViewer;

  private Object input;

  /**
   * The current original selection, ie without adaptation, to be used for the refresh of the view.
   */
  private ISelection currentOriginalSelection;

  /**
   * The original selection used during the last refresh. {@link #input} field is not enough. Indeed, input can be the
   * same but if it has been moved from one container to another, the view needs to be refresh even if the "input" is
   * the same.
   */
  private ISelection previousOriginalSelection;

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

  private boolean isLinkedToSelection;

  protected ISemanticBrowserModel model;
  protected AbstractSemanticBrowserDoubleClickHandler doubleClickHandler;
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
      isLinkedToSelection = true;
      getModel().setListeningToPageSelectionEvents(isLinkedToSelection);
    }
  }

  /**
   * Deactivate listening to page selection events.
   */
  public void deactivateListeningToPageSelectionEvents() {
    if (isLinkedToSelection) {
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
    ViewerComparator comparator = new SemanticBrowserViewerComparator(model);
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
        AbstractLabelProviderFactory.getInstance().getCurrentLabelProvider(), new SemanticBrowserViewerComparator(model) {
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
            ICategory category = (categoryWrapper).getElement();
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

    // Add the selection listener
    getSite().getPage().addSelectionListener(getSelectionListener());

    makeActions();

    IContextService contextService = getSite().getService(IContextService.class);
    contextService.activateContext(BINDING_CONTEXT_ID);
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

    getSite().getPage().removeSelectionListener(getSelectionListener());

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
    return (part, selection) -> {
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
          saveInput(newInput, selection);
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
    };
  }

  /**
   * Handle double click event in the viewpart.
   * 
   * @param event
   */
  protected void handleDoubleClick(DoubleClickEvent event) {     
    AbstractSemanticBrowserDoubleClickHandler handler = getSemanticBrowserDoubleClickHandlerFor(event);
    handler.setControlKeyPressed(isCtrlKeyPressed);
    Object selectedElement = resolveSelectedElement(event); 
    handler.handle(this, event, selectedElement );    
  }
  
  /**
   * Returns the handle for double click behavior on elements
   * This can be subclassed to customize how double click works
   * @param event
   * @return
   */
  public AbstractSemanticBrowserDoubleClickHandler getSemanticBrowserDoubleClickHandlerFor(DoubleClickEvent event) {
    if (doubleClickHandler == null) 
      doubleClickHandler = new DefaultSemanticBrowserDoubleClickHandler();
    
    return doubleClickHandler;
  }
  
  /**
   * Returns the first element from the event selection, unwrapped if needed
   * @param event
   * @return
   */
  protected Object resolveSelectedElement(DoubleClickEvent event) {   
    ITreeSelection selection = (ITreeSelection)  event.getSelection();
    if (!selection.isEmpty()) {
      Object doubleClickedElement = selection.getFirstElement();
      if (doubleClickedElement instanceof BrowserElementWrapper) {
        doubleClickedElement = ((BrowserElementWrapper) doubleClickedElement).getElement();
      }
      return doubleClickedElement;
    }
    return null;
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
    // 1) If the view was not shown when the previous workbench session
    // exited.
    // 2) The view is open by the end-user whereas the workbench is already
    // loaded.
    memento = restoreViewSettings(memento);
    super.init(site, memento);

    boolean isListeningOnStartup = !CapellaBrowserActivator.getDefault().getPreferenceStore()
        .getBoolean(CapellaBrowserPreferences.PREFS_DISABLE_SEMANTIC_BROWSER_SYNC_ON_STARTUP);

    if (isListeningOnStartup && null != memento) {
      isListeningOnStartup = memento.getInteger(LISTENING_TO_WORKBENCH_PAGE_SELECTION_EVENTS) == 1;
    }
    if (isListeningOnStartup) {
      activateListeningToPageSelectionEvents();
    }
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
    factory.createLexicographicSortTreeAction(this);
    factory.createRefreshAction(this);
    factory.createListenToSelectionEventsAction(this, isLinkedToSelection);
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

      Image image = null;
      String text = null;

      // Handle firstly for representation
      EObject eObject = CapellaAdapterHelper.resolveEObject(selectedElement);
      if (selectedElement instanceof DRepresentation) {
        DRepresentationDescriptor descriptor = RepresentationHelper
            .getRepresentationDescriptor((DRepresentation) selectedElement);
        text = RepresentationHelper.getRepresentationFullPathText(descriptor);
        image = ExtendedImageRegistry.getInstance().getImage(EObjectLabelProviderHelper.getImage(eObject));
      } else if (selectedElement instanceof DRepresentationDescriptor) {
        text = RepresentationHelper.getRepresentationFullPathText((DRepresentationDescriptor) selectedElement);
        image = ExtendedImageRegistry.getInstance().getImage(EObjectLabelProviderHelper.getImage(eObject));
      } else {
        // Handle then semantic element
        EObject semanticElement = CapellaAdapterHelper.resolveBusinessObject(selectedElement);
        if (semanticElement != null) {
          text = EObjectLabelProviderHelper.getFullPathText(semanticElement);
          image = ExtendedImageRegistry.getInstance().getImage(EObjectLabelProviderHelper.getImage(semanticElement));
        }
      }

      statusLineManager.setMessage(image, text);
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
    refreshTitleBar(getCurrentViewer().getInput());
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

      BusyIndicator.showWhile(display, () -> {

        // Broadcast "set input" signal to all viewers.
        ViewerHelper.run(referencingViewer, () -> {
          if ((referencingViewer.getControl() != null) && !referencingViewer.getControl().isDisposed()) {
            referencingViewer.setInput(input);
          }
        });

        ViewerHelper.run(referencedViewer, () -> {
          if ((referencedViewer.getControl() != null) && !referencedViewer.getControl().isDisposed()) {
            referencedViewer.setInput(input);
          }
        });

        ViewerHelper.run(SemanticBrowserView.this.currentViewer, () -> {
          if ((SemanticBrowserView.this.currentViewer.getControl() != null)
              && !SemanticBrowserView.this.currentViewer.getControl().isDisposed()) {
            SemanticBrowserView.this.currentViewer.setInput(input);
          }
        });
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
      String label = AbstractLabelProviderFactory.getInstance().getCurrentLabelProvider().getText(selectedElement);
      if (label != null) {
        title = label;
        // Get the metaclass label.
        String metaclassLabel = EObjectLabelProviderHelper.getMetaclassLabel(((EObject) selectedElement), true);
        if ((null != metaclassLabel) && !title.startsWith(metaclassLabel)) {
          title = metaclassLabel + title;
        }
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
    setFocusOnViewer();
    if (isLinkedToSelection) {
      refresh();
    }
  }

  private void setFocusOnViewer() {
    ISelectionProvider selectionProvider = delegateSelectionProvider.getActiveDelegate();
    // Make sure the selection provider is tree viewer.
    if (selectionProvider instanceof TreeViewer) {
      ((TreeViewer) selectionProvider).getControl().setFocus();
    } else {
      this.currentViewer.getControl().setFocus();
    }
  }

  @Override
  public final void setInput(final Object input) {
    this.input = input;
    refresh();
  }

  @Override
  public final void saveInput(final Object input, final ISelection originalSelection) {
    this.input = input;
    this.previousOriginalSelection = this.currentOriginalSelection;
    this.currentOriginalSelection = originalSelection;

    if (isLinkedToSelection) {
      refresh();
    }

  }

  @Override
  public void refresh(boolean forceRefresh) {
    if (!PlatformUI.getWorkbench().isClosing() && getSite().getPage().isPartVisible(this)) {
      // Precondition: do not set the same input twice, except during refreshing.
      TreeViewer currentTreeViewer = getCurrentViewer();
      Object lastInput = currentTreeViewer.getInput();
      if (!forceRefresh && null != lastInput && lastInput.equals(input) && !hasOriginalSelectionChanged()) {
        return;
      }

      SiriusReferenceFinderCache.INSTANCE.enable();
      refreshTitleBar(input);
      // Set the selection provider with currentViewer as selection provider.
      delegateSelectionProvider.setActiveDelegate(this.currentViewer);

      // Store the original selection to detect a further change
      this.previousOriginalSelection = this.currentOriginalSelection;
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
        setFocusOnViewer();
      }
      SiriusReferenceFinderCache.INSTANCE.disable();
    }
  }

  /**
   * Return true if the selection has changed since the last refresh, false otherwise.
   * 
   * @return true if the selection has changed since the last refresh, false otherwise.
   */
  private boolean hasOriginalSelectionChanged() {
    boolean result = false;
    if (this.previousOriginalSelection == null) {
      if (this.currentOriginalSelection != null) {
        result = true;
      }
    } else {
      result = !previousOriginalSelection.equals(currentOriginalSelection);
    }
    return result;
  }

  /**
   * Propagates the current input to the sub viewers if the view is visible
   */
  @Override
  public void refresh() {
    refresh(false);
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

}
