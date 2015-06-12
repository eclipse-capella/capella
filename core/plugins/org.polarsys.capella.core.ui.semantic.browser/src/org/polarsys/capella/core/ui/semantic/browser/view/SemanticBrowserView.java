/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.ImageDescriptor;
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
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
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
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyTitle;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.common.ui.services.helper.ViewerHelper;
import org.polarsys.capella.common.ui.services.swt.events.AbstractKeyAdapter;
import org.polarsys.capella.common.ui.toolkit.browser.action.BrowserActionFactory;
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
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;
import org.polarsys.capella.core.ui.properties.CapellaTabbedPropertySheetPage;
import org.polarsys.capella.core.ui.properties.CapellaUIPropertiesPlugin;
import org.polarsys.capella.core.ui.semantic.browser.CapellaBrowserActivator;
import org.polarsys.capella.core.ui.semantic.browser.CapellaBrowserPreferences;
import org.polarsys.capella.core.ui.semantic.browser.IImageKeys;
import org.polarsys.capella.core.ui.semantic.browser.model.SemanticBrowserModel;

/**
 * Browser Semantic View. Load by extension point.
 */
public abstract class SemanticBrowserView extends ViewPart implements ISemanticBrowserViewPart, ITabbedPropertySheetPageContributor, IEditingDomainProvider, IReadOnlyListener {

  /**
   * Listener that listens to closing and closed session events.
   */
  protected class SemCloseSessionListener extends SessionManagerListener.Stub {
    @Override
    public void notify(final Session updated_p, final int notification_p) {
      switch (notification_p) {
      case SessionListener.CLOSING:
        Object currentInput = getCurrentViewer().getInput();
        if (currentInput instanceof EObject) {
          // Get the session of current displayed object.
          Session session = SessionManager.INSTANCE.getSession((EObject) currentInput);
          if (updated_p.equals(session)) {
            clean();
          }
        }
        break;
      case SessionListener.CLOSED:
        // Update history to clean dead entries.
        getHistory().update(null);
        break;
      }
    }
  }

  /**
   * Whether or not the view is listening page selection events.<br>
   * Hopefully there is only one instance of a semantic browser at runtime.
   */
  private static volatile boolean __isListeningToPageSelectionEvents;

  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);
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
   * Back navigation action.
   */
  private IWorkbenchAction _backAction;

  private TreeViewer _currentViewer;
  private DelegateSelectionProviderWrapper _delegateSelectionProvider;
  /**
   * Forward navigation action.
   */
  private IWorkbenchAction _forwardAction;

  /**
   * Navigation history.
   */
  private BrowserHistory _history;

  /**
   * Is CTRL key pressed when a double click is emitted ?
   */
  private boolean _isCtrlKeyPressed;
  /**
   * Memento used to persist view states between sessions.
   */
  private IMemento _memento;
  private SessionManagerListener _semCloseSessionListener;
  private TabbedPropertySheetPage _propertySheetPage;
  private TreeViewer _referencedViewer;
  /**
   * Default viewers embedded into the view.
   */
  private TreeViewer _referencingViewer;
  private ISelectionListener _selectionListener;
  private TabbedPropertyTitle _semanticBrowserTitle;

  /**
   * Used to drive setFocus from setInput.
   */
  private boolean _shouldSetFocus;
  private IDoubleClickListener _viewerDoubleClickListener;

  private ISelectionChangedListener _viewerSelectionListener;

  /**
   * Dialog settings for this view.
   */
  private IDialogSettings _viewSettings;

  protected ISemanticBrowserModel model;

  /**
   * Constructor.
   */
  public SemanticBrowserView() {
    // Get the dialog settings section for this view.
    _viewSettings = getDialogSettingsSection();
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
    _selectionListener = getSelectionListener();
    if (null != _selectionListener) {
      getSite().getPage().addSelectionListener(_selectionListener);
    }
    __isListeningToPageSelectionEvents = true;
  }

  /**
   * Adds drag support to given viewer. The drag transfer type is LocalSelectionTransfer.
   * 
   * @param viewer_p
   */
  protected void addDndDragSupport(final TreeViewer viewer_p) {
    int operations = DND.DROP_MOVE | DND.DROP_COPY;
    Transfer[] transferTypes = new Transfer[] { LocalSelectionTransfer.getTransfer() };
    // The DragSourceListener implementation is inspired by
    // org.eclipse.debug.internal.ui.views.variables.SelectionDragAdapter.
    viewer_p.addDragSupport(operations, transferTypes, new DragSourceListener() {
      /**
       * {@inheritDoc}
       */
      @Override
      public void dragSetData(DragSourceEvent event_p) {
        event_p.data = LocalSelectionTransfer.getTransfer().getSelection();
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public void dragStart(DragSourceEvent event_p) {
        // Check selection to drag is a CapellaElement.
        ISelection selection = viewer_p.getSelection();
        if ((selection != null) && (selection instanceof IStructuredSelection) && (CapellaResourceHelper.isSemanticElements(((IStructuredSelection) selection).toList()))) {
          // Fill LocalSelectionTransfer.
          LocalSelectionTransfer.getTransfer().setSelection(selection);
          LocalSelectionTransfer.getTransfer().setSelectionSetTime(event_p.time & 0xFFFFFFFFL);
          // Allow drag operation.
          event_p.doit = true;
        } else {
          // Forbid drag operation.
          event_p.doit = false;
        }
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public void dragFinished(DragSourceEvent event_p) {
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
   * @param viewer_p
   *          The viewer
   */
  protected void addListeners(final TreeViewer viewer_p) {
    // Lazy creation pattern.
    if (null == _viewerSelectionListener) {
      _viewerSelectionListener = new ISelectionChangedListener() {
        @Override
        public void selectionChanged(SelectionChangedEvent event) {
          ISelectionProvider provider = event.getSelectionProvider();
          refreshPropertyPage(provider);
        }
      };
    }
    // Register the listener.
    viewer_p.addSelectionChangedListener(_viewerSelectionListener);

    // Add a focus listener to update the selection provider.
    viewer_p.getControl().addFocusListener(new FocusAdapter() {
      @SuppressWarnings("synthetic-access")
      @Override
      public void focusGained(FocusEvent event_p) {
        Object source = event_p.getSource();
        ISelectionProvider newSelectionProvider = null;
        if (source.equals(_currentViewer.getControl())) {
          newSelectionProvider = _currentViewer;
        } else if (source.equals(_referencedViewer.getControl())) {
          newSelectionProvider = _referencedViewer;
        } else if (source.equals(_referencingViewer.getControl())) {
          newSelectionProvider = _referencingViewer;
        }
        if (null != newSelectionProvider) {
          updateSelectionProvider(newSelectionProvider);
          refreshPropertyPage(newSelectionProvider);
          // viewer_p.addSelectionChangedListener(getSite().getWorkbenchWindow());
          // viewer_p.addSelectionChangedListener(WindowSelectionService);
          try {
            getViewSite().getPage().showView(SEMANTIC_BROWSER_ID, null, org.eclipse.ui.IWorkbenchPage.VIEW_CREATE);
          } catch (PartInitException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      }
    });

    // Lazy creation pattern.
    if (null == _viewerDoubleClickListener) {
      _viewerDoubleClickListener = new IDoubleClickListener() {
        @SuppressWarnings("synthetic-access")
        @Override
        public void doubleClick(DoubleClickEvent event_p) {
          try {
            handleDoubleClick(event_p);
          } catch (RuntimeException exception_p) {
            StringBuilder loggerMessage = new StringBuilder("SemanticBrowserView.addListeners(..) _ "); //$NON-NLS-1$
            loggerMessage.append(exception_p.getMessage());
            __logger.error(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI), exception_p);
          }
        }
      };
    }
    // Register the listener.
    viewer_p.addDoubleClickListener(_viewerDoubleClickListener);
    viewer_p.getControl().addKeyListener(new AbstractKeyAdapter() {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void keyPressed(KeyEvent keyEvent_p) {
        if (isCtrlPressed(keyEvent_p)) {
          _isCtrlKeyPressed = true;
        }
      }

      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void keyReleased(KeyEvent keyEvent_p) {
        if (isCtrlPressed(keyEvent_p)) {
          _isCtrlKeyPressed = false;
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
    boolean restoreState = _shouldSetFocus ? true : false;
    _shouldSetFocus = false;
    setInput(null);
    if (restoreState) {
      _shouldSetFocus = true;
    }
  }

  /**
   * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
   */
  @Override
  public void createPartControl(Composite parent_p) {
    // Create and set a layout on the parent.
    GridLayout layout = new GridLayout();
    // No blank space.
    layout.marginHeight = 0;
    layout.marginWidth = 0;
    layout.verticalSpacing = 0;
    layout.horizontalSpacing = 0;
    parent_p.setLayout(layout);
    // Creates the title composite
    _semanticBrowserTitle = new TabbedPropertyTitle(parent_p, new TabbedPropertySheetWidgetFactory());
    GridData titleLayoutData = new GridData(GridData.FILL_HORIZONTAL);
    // Do grab excess vertical space.
    titleLayoutData.grabExcessVerticalSpace = false;
    _semanticBrowserTitle.setLayoutData(titleLayoutData);
    // Sets a default name with no image
    _semanticBrowserTitle.setTitle(Messages.SemanticBrowserView_Default_Name, null);

    // Create the main sash form that host inner viewers.
    SashForm mainSashForm = new SashForm(parent_p, SWT.HORIZONTAL);
    Layout gridLayoutTop = new GridLayout(3, true);
    mainSashForm.setLayout(gridLayoutTop);
    mainSashForm.setLayoutData(new GridData(GridData.FILL_BOTH));

    // set the model in the AbstractContentProviderFactory
    AbstractContentProviderFactory.getInstance().setModel(model);

    // Initialize referencing viewer as first element of the main sash form.
    ViewerSorter sorter = new ViewerSorter();
    AbstractContentProvider treeProvider = (AbstractContentProvider) AbstractContentProviderFactory.getInstance().getReferencingContentProvider();
    _referencingViewer = createViewer(mainSashForm, REFERENCING_ELEMENTS_LABEL_TXT, 3, treeProvider.getBrowserId());
    initializeViewer(_referencingViewer, treeProvider, AbstractLabelProviderFactory.getInstance().getReferencingLabelProvider(), sorter);

    // Create a sash form as second element of the main sash form.
    // Initialize current viewer as first element of the center sash form.
    treeProvider = (AbstractContentProvider) AbstractContentProviderFactory.getInstance().getCurrentContentProvider();
    _currentViewer = createViewer(mainSashForm, Messages.SemanticBrowserView_Current_Element_Title, 3, treeProvider.getBrowserId());
    initializeViewer(_currentViewer, treeProvider, AbstractLabelProviderFactory.getInstance().getCurrentLabelProvider(), new ViewerSorter() {
      /**
       * Overridden to force All Related Diagrams and All Related Tables to be located at the end of the tree.
       * {@inheritDoc}
       */
      @Override
      public int compare(Viewer viewer_p, Object e1_p, Object e2_p) {
        if ((e1_p instanceof CategoryWrapper) && isRepresentationCategory((CategoryWrapper) e1_p)) {
          return 1;
        }
        if ((e2_p instanceof CategoryWrapper) && isRepresentationCategory((CategoryWrapper) e2_p)) {
          return -1;
        }
        return super.compare(viewer_p, e1_p, e2_p);
      }

      /**
       * Is given category used to displayed diagrams or tables ?
       * 
       * @param categoryWrapper_p
       * @return
       */
      private boolean isRepresentationCategory(CategoryWrapper categoryWrapper_p) {
        ICategory category = (ICategory) (categoryWrapper_p).getElement();
        String categoryName = category.getName();
        return categoryName.equals(ALL_RELATED_DIAGRAMS) || categoryName.equals(ALL_RELATED_TABLES);
      }
    });

    // Initialize the referenced viewer as third element of the main sash
    // form.
    treeProvider = (AbstractContentProvider) AbstractContentProviderFactory.getInstance().getReferencedContentProvider();
    _referencedViewer = createViewer(mainSashForm, Messages.SemanticBrowserView_Referenced_Elements_Title, 3, treeProvider.getBrowserId());
    initializeViewer(_referencedViewer, treeProvider, AbstractLabelProviderFactory.getInstance().getReferencedLabelProvider(), sorter);

    initializeContextMenus();
    // Create and set a delegate selection provider, initialized on current
    // viewer.
    List<ISelectionProvider> lstProvider = new ArrayList<ISelectionProvider>();
    lstProvider.add(_currentViewer);
    lstProvider.add(_referencedViewer);
    lstProvider.add(_referencingViewer);

    _delegateSelectionProvider = new DelegateSelectionProviderWrapper(lstProvider);
    _delegateSelectionProvider.setActiveDelegate(_currentViewer);
    getViewSite().setSelectionProvider(_delegateSelectionProvider);
    makeActions();
    // Listen to Closing/Close session events.
    _semCloseSessionListener = new SemCloseSessionListener();
    SessionManager.INSTANCE.addSessionsListener(_semCloseSessionListener);
  }

  /**
   * Create a composite widget which contains a treeviewer.
   * 
   * @param parent_p
   * @param label_p
   * @param autoExpandLevel_p
   * @return the referenced treeviewer
   */
  protected TreeViewer createViewer(Composite parent_p, String label_p, int autoExpandLevel_p, String browserID) {
    BrowserComposite composite = new BrowserComposite(parent_p, model, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL, label_p, browserID);
    TreeViewer treeViewer = composite.getTreeviewer();
    // treeViewer.setAutoExpandLevel(autoExpandLevel_p);
    treeViewer.setUseHashlookup(true);
    ColumnViewerToolTipSupport.enableFor(treeViewer, ToolTip.RECREATE);
    addListeners(treeViewer);
    addDndDragSupport(treeViewer);
    return treeViewer;
  }

  /**
   * Deactivate listening to page selection events.
   */
  public void deactivateListeningToPageSelectionEvents() {
    if (null != _selectionListener) {
      getSite().getPage().removeSelectionListener(_selectionListener);
      _selectionListener = null;
    }
    __isListeningToPageSelectionEvents = false;
  }

  /**
   * @see org.eclipse.ui.part.WorkbenchPart#dispose()
   */
  @Override
  public void dispose() {
    // Unregister...
    TreeViewer currentViewer = getCurrentViewer();
    if (null != currentViewer) {
      Object currentInput = currentViewer.getInput();
      if (null != currentInput) {
        CapellaReadOnlyHelper.unregister((EObject) currentInput, this);
      }
    }

    // Save view settings.
    saveViewSettings();
    deactivateListeningToPageSelectionEvents();
    if (null != _propertySheetPage) {
      _propertySheetPage.dispose();
      _propertySheetPage = null;
    }
    if (null != _referencingViewer) {
      removeListeners(_referencingViewer);
      _referencingViewer = null;
    }
    if (null != _referencedViewer) {
      removeListeners(_referencedViewer);
      _referencedViewer = null;
    }
    if (null != _currentViewer) {
      removeListeners(_currentViewer);
    }
    _viewerSelectionListener = null;
    _viewerDoubleClickListener = null;
    if (null != _history) {
      _history.dispose();
      _history = null;
    }
    // Remove Closing/Close session listener.
    SessionManager.INSTANCE.removeSessionsListener(_semCloseSessionListener);
    _semCloseSessionListener = null;

    model = null;
    super.dispose();
  }

  /**
   * @see org.eclipse.ui.navigator.CommonNavigator#getAdapter(java.lang.Class)
   */
  @SuppressWarnings("rawtypes")
  @Override
  public Object getAdapter(Class adapter_p) {
    if (IPropertySheetPage.class.equals(adapter_p)) {
      return getPropertySheetPage();
    } else if (Control.class.equals(adapter_p)) {
      return getParentControl();
    }
    return super.getAdapter(adapter_p);
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
    return _currentViewer;
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
    if (null == _history) {
      _history = new BrowserHistory();
    }
    return _history;
  }

  /**
   * Get parent control, if accessible.
   * 
   * @return <code>null</code> if control could not be resolved.
   */
  protected Control getParentControl() {
    if (null != _semanticBrowserTitle) {
      return _semanticBrowserTitle.getParent();
    }
    return null;
  }

  /**
   * Gets the property sheet page.
   */
  protected TabbedPropertySheetPage getPropertySheetPage() {
    if (null == _propertySheetPage) {
      _propertySheetPage = new CapellaTabbedPropertySheetPage(this) {
        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("synthetic-access")
        @Override
        public void dispose() {
          super.dispose();
          _propertySheetPage = null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void init(IPageSite pageSite_p) {
          super.init(pageSite_p);
          pageSite_p.setSelectionProvider(SemanticBrowserView.this.getViewSite().getSelectionProvider());
        }
      };
    }
    return _propertySheetPage;
  }

  /**
   * @return the referencedViewer
   */
  @Override
  public TreeViewer getReferencedViewer() {
    return _referencedViewer;
  }

  /**
   * @return the referencingViewer
   */
  @Override
  public TreeViewer getReferencingViewer() {
    return _referencingViewer;
  }

  /**
   * Get the current viewer root element.
   * 
   * @see org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart#getRootElement()
   */
  @Override
  public EObject getRootElement() {
    return ((AbstractContentProvider) _currentViewer.getContentProvider()).getRootElement();
  }

  /**
   * Get the selection listener to handle {@link IWorkbenchPage} selection events.
   * 
   * @return
   */
  protected ISelectionListener getSelectionListener() {
    return new ISelectionListener() {
      /**
       * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart,
       *      org.eclipse.jface.viewers.ISelection)
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void selectionChanged(IWorkbenchPart part_p, ISelection selection_p) {
        Object newInput = handleWorkbenchPageSelectionEvent(part_p, selection_p);
        // Set the selected object as new input only if it is an EObject
        if ((null != newInput) && (newInput instanceof EObject)) {
          // Avoid the property view to be selection provider.
          if (CapellaUIPropertiesPlugin.PROPERTIES_SHEET_VIEW_ID.equals(part_p.getSite().getId())) {
            return;
          }
          // Check the input is different from current one.
          try {
            _shouldSetFocus = false;
            setInput(newInput);
          } finally {
            _shouldSetFocus = true;
          }
        } else if ((part_p != SemanticBrowserView.this) && !CapellaUIPropertiesPlugin.PROPERTIES_SHEET_VIEW_ID.equals(part_p.getSite().getId())) { // //
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
   * @param event_p
   */
  protected void handleDoubleClick(DoubleClickEvent event_p) {
    ITreeSelection selection = (ITreeSelection) event_p.getSelection();
    if (!selection.isEmpty()) {
      Object doubleClickedElement = selection.getFirstElement();
      // do nothing if element of the wrapper is the root element.
      // change
      if (doubleClickedElement instanceof BrowserElementWrapper) {
        doubleClickedElement = ((BrowserElementWrapper) doubleClickedElement).getElement();
      }
      // Handle a ModelElement double click event.
      if (doubleClickedElement instanceof EObject) {
        if (_isCtrlKeyPressed) {
          if (getRootElement() != doubleClickedElement) {
            // CTRL is pressed, let's navigate...
            setInput(doubleClickedElement);
            // Set and reveal the focused element.
            _currentViewer.setSelection(new StructuredSelection(doubleClickedElement), true);
          }
        } else {
          CapellaUIPropertiesPlugin.getDefault().openWizard(event_p, (EObject) doubleClickedElement);
        }
      }
    }
  }

  /**
   * Handle workbench page selection events.<br>
   * Default implementation returns <code>null</code>.
   * 
   * @param part_p
   * @param selection_p
   * @return <code>null</code> means no capella element found from selection.
   */
  protected Object handleWorkbenchPageSelectionEvent(IWorkbenchPart part_p, ISelection selection_p) {
    return null;
  }

  /**
   * @see org.eclipse.ui.part.ViewPart#init(org.eclipse.ui.IViewSite, org.eclipse.ui.IMemento)
   */
  @Override
  public void init(IViewSite site_p, IMemento memento_p) throws PartInitException {
    // Specified memento could be null :
    // 1) if the view was not shown when the previous workbench session
    // exited.
    // 2) the view is open by the end-user whereas the workbench is already
    // loaded.
    _memento = restoreViewSettings(memento_p);
    super.init(site_p, _memento);
    Integer value = null;
    if (null != _memento) {
      // Get state of listening to Page selection events.
      value = _memento.getInteger(LISTENING_TO_WORKBENCH_PAGE_SELECTION_EVENTS);
    }

    boolean isListeningOnStartup = !CapellaBrowserActivator.getDefault().getPreferenceStore().getBoolean(CapellaBrowserPreferences.PREFS_DISABLE_SEMANTIC_BROWSER_SYNC_ON_STARTUP);
    __isListeningToPageSelectionEvents = (null != value) ? value.intValue() == 1 : isListeningOnStartup;
  }

  /**
   * Initialize a context menu for given viewer.
   * 
   * @param menuManagerText_p
   * @param menuManagerId_p
   * @param viewer_p
   */
  private void initializeContextMenu(String menuManagerText_p, String menuManagerId_p, TreeViewer viewer_p) {
    MenuManager menuManager = new MenuManager(menuManagerText_p, menuManagerId_p);
    menuManager.setRemoveAllWhenShown(true);
    Tree tree = viewer_p.getTree();
    Menu currentMenu = menuManager.createContextMenu(tree);
    tree.setMenu(currentMenu);
    getSite().registerContextMenu(menuManager, viewer_p);
  }

  /**
   * Initialize contextual menus of internal viewers.
   */
  private void initializeContextMenus() {
    initializeContextMenu("current#PopupMenu", null, _currentViewer); //$NON-NLS-1$
    initializeContextMenu("referenced#PopupMenu", null, _referencedViewer); //$NON-NLS-1$
    initializeContextMenu("referencing#PopupMenu", null, _referencingViewer); //$NON-NLS-1$
  }

  /**
   * Initialize given viewer with specified parameter.<br>
   * The viewer layout data is set to a {@link GridData#FILL_BOTH}.
   * 
   * @param viewer_p
   * @param contentProvider_p
   * @param labelProvider_p
   * @param sorter_p
   */
  private void initializeViewer(TreeViewer viewer_p, IContentProvider contentProvider_p, IBaseLabelProvider labelProvider_p, ViewerSorter sorter_p) {
    viewer_p.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
    viewer_p.setContentProvider(contentProvider_p);
    viewer_p.setLabelProvider(labelProvider_p);
    viewer_p.setSorter(sorter_p);
  }

  /**
   * @return the isCtrlKeyPressed
   */
  protected boolean isCtrlKeyPressed() {
    return _isCtrlKeyPressed;
  }

  protected IAction showPatternsAction;
  protected IAction showDiagramsAction;
  protected IAction limitateTreeExpansionAction;

  private ImageDescriptor getImage(EObject object) {
    IItemLabelProvider l = ((IItemLabelProvider) CapellaAdapterFactoryProvider.getInstance().getAdapterFactory().adapt(object, IItemLabelProvider.class));
    URL imageUrl = (URL) l.getImage(object);
    return ImageDescriptor.createFromURL(imageUrl);
  }

  /**
   * Make actions.
   */
  protected void makeActions() {
    IActionBars actionBars = getViewSite().getActionBars();
    IToolBarManager toolBarManager = actionBars.getToolBarManager();
    // Add history actions.
    _backAction = BrowserActionFactory.BACKWARD_HISTORY.create(getViewSite().getWorkbenchWindow(), this);
    _backAction.setActionDefinitionId("org.polarsys.capella.core.ui.semantic.browser.backwardNavigation"); //$NON-NLS-1$
    toolBarManager.add(_backAction);

    _forwardAction = BrowserActionFactory.FORWARD_HISTORY.create(getViewSite().getWorkbenchWindow(), this);
    _forwardAction.setActionDefinitionId("org.polarsys.capella.core.ui.semantic.browser.forwardNavigation"); //$NON-NLS-1$
    toolBarManager.add(_forwardAction);

    // Add hide diagrams action.
    showDiagramsAction = new Action(null, IAction.AS_CHECK_BOX) {
      @Override
      public void run() {
        model.setShowDiagrams(isChecked());
        Object input = getCurrentViewer().getInput();
        setInputOnViewers(input);
      }
    };
    showDiagramsAction.setChecked(model.doesShowDiagrams());
    showDiagramsAction.setToolTipText(Messages.SemanticBrowserView_ShowDiagramsAction_Tooltip);
    showDiagramsAction.setImageDescriptor(getImage(ViewpointFactory.eINSTANCE.createDAnalysis()));
    toolBarManager.add(showDiagramsAction);

    // Add limitate tree expansion action.
    limitateTreeExpansionAction = new Action(null, IAction.AS_CHECK_BOX) {
      @Override
      public void run() {
        model.setLimitateTreeExpansion(isChecked());
        Object input = getCurrentViewer().getInput();
        setInputOnViewers(input);
      }
    };
    limitateTreeExpansionAction.setChecked(model.doesLimitateTreeExpansion());
    limitateTreeExpansionAction.setToolTipText(Messages.SemanticBrowserView_LimitateTreeExpansionAction_Tooltip);
    limitateTreeExpansionAction.setImageDescriptor(CapellaBrowserActivator.getDefault().getImageDescriptor(IImageKeys.IMG_COLLAPSE_CATEGORIES));
    toolBarManager.add(limitateTreeExpansionAction);

    // Add refresh action.
    IAction refreshAction = new Action(null, CapellaBrowserActivator.getDefault().getImageDescriptor(IImageKeys.IMG_REFRESH)) {
      @Override
      public void run() {
        refreshTitleBar();
      }
    };
    toolBarManager.add(refreshAction);

    // Add the listening action (i.e button synch checked button).
    IAction listeningToPageSelectionEventsAction = new Action(null, IAction.AS_CHECK_BOX) {
      private ISelection getSelection(IWorkbenchPart part_p) {
        return part_p.getSite().getSelectionProvider().getSelection();
      }

      private boolean isSomethingSelectable(ISelection selection) {
        return (null != selection) && !selection.isEmpty();
      }

      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void run() {
        if (isChecked()) {
          activateListeningToPageSelectionEvents();
          // Get the current selection.
          IWorkbenchPage activePage = getSite().getPage();
          // Get the active part to get something selectable.
          IWorkbenchPart activePart = activePage.getActivePart();
          IWorkbenchPart part = null;
          ISelection selection = null;
          if ((null != activePart) && (SemanticBrowserView.this != activePart)) {
            // Handle selection at view creation time.
            ISelectionProvider selectionProvider = activePart.getSite().getSelectionProvider();
            if (null != selectionProvider) {
              selection = selectionProvider.getSelection();
              if (isSomethingSelectable(selection)) {
                part = activePart;
              }
            }
          } else {
            IViewPart capellaExplorer = activePage.findView(CapellaCommonNavigator.ID);
            if (null != capellaExplorer) {
              // Capella explorer is displayed.
              selection = getSelection(capellaExplorer);
              if (isSomethingSelectable(selection)) {
                part = capellaExplorer;
              }
            }
            if (null == part) {
              // Try to get a selection from active editor.
              IEditorPart activeEditor = activePage.getActiveEditor();
              if (null != activeEditor) {
                selection = getSelection(activeEditor);
                if (isSomethingSelectable(selection)) {
                  part = activeEditor;
                }
              }
            }
          }
          if (null != part) {
            // Something to select.
            _selectionListener.selectionChanged(part, selection);
          }
        } else {
          deactivateListeningToPageSelectionEvents();
        }
      }
    };
    listeningToPageSelectionEventsAction.setText(Messages.SemanticBrowserView_ListeningToPageSelectionEventsAction_Title);
    listeningToPageSelectionEventsAction.setToolTipText(Messages.SemanticBrowserView_ListeningToPageSelectionEventsAction_Tooltip);
    listeningToPageSelectionEventsAction.setImageDescriptor(CapellaBrowserActivator.getDefault().getImageDescriptor(IImageKeys.IMG_LISTENING_TO_PAGE_SELECTION_EVENTS));
    toolBarManager.add(listeningToPageSelectionEventsAction);
    // Restore state from boolean that keeps the state.
    listeningToPageSelectionEventsAction.setChecked(__isListeningToPageSelectionEvents);
    // If enable, listen to selection events.
    if (__isListeningToPageSelectionEvents) {
      // Run the action enables the listening and get the current
      // selection.
      listeningToPageSelectionEventsAction.run();
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart#refresh()
   */
  @Override
  public void refresh() {
    ViewerHelper.refresh(_referencingViewer);
    ViewerHelper.refresh(_referencedViewer);
    ViewerHelper.refresh(_currentViewer);
  }

  protected void refreshPropertyPage(ISelectionProvider selectionProvider_p) {
    // Notify the property page to refresh with the new selection.
    // Be careful, the properties view can be closed, don't send it
    // selection changes.
    IStructuredSelection selection = (IStructuredSelection) selectionProvider_p.getSelection();
    if ((null != _propertySheetPage) && (!_propertySheetPage.getControl().isDisposed())) {
      ISelectionProvider pageSelectionProvider = _propertySheetPage.getSite().getSelectionProvider();
      if ((null == pageSelectionProvider) || (pageSelectionProvider != selectionProvider_p)) {
        _propertySheetPage.getSite().setSelectionProvider(selectionProvider_p);
      }
      _propertySheetPage.selectionChanged(SemanticBrowserView.this, selection);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled_p) {
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
   * @param input_p
   */
  @SuppressWarnings("synthetic-access")
  @Override
  public void setInputOnViewers(final Object input_p) {
    TreeViewer currentViewer = getCurrentViewer();

    if ((currentViewer != null) && ((currentViewer.getControl() != null) && !(currentViewer.getControl().isDisposed()))) {
      Display display = currentViewer.getControl().getDisplay();

      BusyIndicator.showWhile(display, new Runnable() {
        @Override
        public void run() {

          // Broadcast "set input" signal to all viewers.
          ViewerHelper.run(_referencingViewer, new Runnable() {
            @Override
            public void run() {
              if ((_referencingViewer.getControl() != null) && !_referencingViewer.getControl().isDisposed()) {
                _referencingViewer.setInput(input_p);
              }
            }
          });
          ViewerHelper.run(_referencedViewer, new Runnable() {
            @Override
            public void run() {
              if ((_referencedViewer.getControl() != null) && !_referencedViewer.getControl().isDisposed()) {
                _referencedViewer.setInput(input_p);
              }
            }
          });
          ViewerHelper.run(_currentViewer, new Runnable() {
            @Override
            public void run() {
              if ((_currentViewer.getControl() != null) && !_currentViewer.getControl().isDisposed()) {
                _currentViewer.setInput(input_p);
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
  private void refreshTitleBar(Object selectedElement_p) {
    String title = Messages.SemanticBrowserView_Default_Name;
    Image image = null;
    if (null != selectedElement_p) {
      // The text to display
      title = AbstractLabelProviderFactory.getInstance().getCurrentLabelProvider().getText(selectedElement_p);
      // Get the metaclass label.
      String metaclassLabel = EObjectLabelProviderHelper.getMetaclassLabel(((EObject) selectedElement_p), true);
      if ((null != metaclassLabel) && !title.startsWith(metaclassLabel)) {
        title = metaclassLabel + title;
      }

      // The image to display
      image = AbstractLabelProviderFactory.getInstance().getCurrentLabelProvider().getImage(selectedElement_p);
    }
    if (!_semanticBrowserTitle.isDisposed()) {
      _semanticBrowserTitle.setRedraw(false);
      _semanticBrowserTitle.setTitle(title, image);
      _semanticBrowserTitle.setRedraw(true);
    }
  }

  /**
   * Remove listeners registered on given viewer.
   */
  protected void removeListeners(TreeViewer viewer_p) {
    viewer_p.removeDoubleClickListener(_viewerDoubleClickListener);
    viewer_p.removeSelectionChangedListener(_viewerSelectionListener);
  }

  /**
   * Restore view settings.
   */
  private IMemento restoreViewSettings(IMemento memento_p) {
    IMemento memento_l = memento_p;
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
        } catch (WorkbenchException exception_p) {
          // Don't do anything. Simply don't restore the settings
        }
      }
    }
    return memento_l;
  }

  /**
   * @see org.eclipse.ui.part.ViewPart#saveState(org.eclipse.ui.IMemento)
   */
  @Override
  public void saveState(IMemento memento_p) {
    memento_p.putInteger(LISTENING_TO_WORKBENCH_PAGE_SELECTION_EVENTS, __isListeningToPageSelectionEvents ? 1 : 0);
  }

  /**
   * Save the dialog settings for this view.
   */
  private void saveViewSettings() {
    String rootName = getClass().getSimpleName();
    // Create a new memento.
    XMLMemento memento = XMLMemento.createWriteRoot(rootName);
    // Save current state in it.
    // Notice, that the saveState() method is also called by the workbench
    // when exiting before the dispose() method.
    // Nevertheless, we keep this call here, to make sure current state is
    // stored within a running workbench session where the saveState()
    // method is not called.
    saveState(memento);
    StringWriter writer = new StringWriter();
    try {
      memento.save(writer);
      _viewSettings.put(TAG_MEMENTO, writer.getBuffer().toString());
    } catch (IOException exception_p) {
      // Don't do anything. Simply don't store the settings
    }
  }

  /**
   * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
   */
  @Override
  public void setFocus() {
    ISelectionProvider selectionProvider = _delegateSelectionProvider.getActiveDelegate();
    // Make sure the selection provider is tree viewer.
    if ((null != selectionProvider) && (selectionProvider instanceof TreeViewer)) {
      ((TreeViewer) selectionProvider).getControl().setFocus();
    } else {
      _currentViewer.getControl().setFocus();
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.view.ISemanticBrowserViewPart#setInput(java.lang.Object)
   */
  @Override
  public final void setInput(final Object input_p) {
    // Precondition: do not set the same input twice.
    TreeViewer currentViewer = getCurrentViewer();
    Object lastInput = currentViewer.getInput();
    if ((null != lastInput) && (lastInput.equals(input_p))) {
      return;
    }
    refreshTitleBar(input_p);
    // Set the selection provider with currentViewer as selection provider.
    _delegateSelectionProvider.setActiveDelegate(_currentViewer);

    // Broadcast "set input" signal to all viewers.
    setInputOnViewers(input_p);

    CapellaReadOnlyHelper.unregister((EObject) lastInput, this);
    CapellaReadOnlyHelper.register((EObject) input_p, this);

    // Update history mechanism.
    getHistory().update(input_p);
    // Force to reset the focus and the underlying selection provider.
    // From platform selection changed event, the setFocus is disabled.
    if (_shouldSetFocus) {
      // Set focus in another thread UI processing.
      setFocus();
    }
  }

  /**
   * Update the view site selection provider with specified one.
   * 
   * @param newSelectionProvider_p
   */
  protected void updateSelectionProvider(ISelectionProvider newSelectionProvider_p) {
    // Set the selection provider if necessary.
    ISelectionProvider currentSelectionProvider = _delegateSelectionProvider.getActiveDelegate();
    if ((null == currentSelectionProvider) || (currentSelectionProvider != newSelectionProvider_p)) {
      _delegateSelectionProvider.setActiveDelegate(newSelectionProvider_p);
    }
  }

  /**
   * Is Semantic Browser listening to {@link IWorkbenchPage} selection events.
   * 
   * @return the isListeningToPageSelectionEvents
   */
  public static boolean isListeningToPageSelectionEvents() {
    return __isListeningToPageSelectionEvents;
  }
}
