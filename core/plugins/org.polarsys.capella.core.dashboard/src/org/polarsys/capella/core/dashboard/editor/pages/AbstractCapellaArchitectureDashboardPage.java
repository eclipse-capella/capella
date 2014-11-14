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
package org.polarsys.capella.core.dashboard.editor.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener2;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.services.helper.FormHelper;
import org.polarsys.capella.common.ui.services.helper.FormHelper.LayoutType;
import org.polarsys.capella.common.ui.services.helper.ViewerHelper;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.FilteredTree;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.PatternFilter;
import org.polarsys.capella.common.ui.toolkit.widgets.filter.TreePatternFilter;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.common.mdsofa.common.helper.MiscHelper;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.core.dashboard.IImageKeys;
import org.polarsys.capella.core.dashboard.CapellaDashboardActivator;
import org.polarsys.capella.core.dashboard.actions.AbstractDescriptionAction;
import org.polarsys.capella.core.dashboard.actions.AbstractViewerFilteringAction;
import org.polarsys.capella.core.dashboard.actions.util.FormTextPageLinkAdapter;
import org.polarsys.capella.core.dashboard.actions.util.MDSashForm;
import org.polarsys.capella.core.dashboard.editor.CapellaDashboardEditor;
import org.polarsys.capella.core.dashboard.editor.pages.contributed.ICapellaDashboardPagesProvider;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.AbstractHyperlinkAdapter;
import org.polarsys.capella.core.dashboard.internal.DelegatedViewerFilter;
import org.polarsys.capella.core.dashboard.viewer.CapellaArchitectureContentProvider;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.CloneAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.SelectionHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.representation.MoveRepresentationAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.CapellaNavigatorLabelProvider;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.core.sirius.ui.actions.DeleteRepresentationAction;
import org.polarsys.capella.core.sirius.ui.actions.OpenRepresentationsAction;
import org.polarsys.capella.core.sirius.ui.actions.RenameRepresentationAction;
import org.polarsys.capella.core.sirius.ui.editor.CapellaDashboardEditorInput;

/**
 * Base class to implement Capella architecture dashboard page.
 */
public abstract class AbstractCapellaArchitectureDashboardPage extends AbstractCapellaDashboardPage implements IPropertyChangeListener, IViewerProvider {
  /**
   * Capella UI logger.
   */
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);
  /**
   * Capella Architecture Dashboard Pages providers.
   */
  private static List<ICapellaDashboardPagesProvider> __capellaPagesProviders;
  /**
   * Diagrams Viewer weight (used by the sash).
   */
  private static final int DIAGRAMS_VIEWER_WEIGHT = 40;
  private static final String GROUP_MOVE = "Move"; //$NON-NLS-1$
  /**
   * {@link AbstractCapellaArchitectureDashboardPage} pages provider extension-point short ID.
   */
  private static final String CAPELLA_ARCHITECTURE_PAGES_PROVIDER_EXTENSION_POINT_ID = "capellaArchitecturePagesProvider"; //$NON-NLS-1$
  /**
   * Section weight (used by the sash).
   */
  private static final int SECTION_WEIGHT = 60;
  private CloneAction _cloneAction;

  private DeleteRepresentationAction _deleteRepresentationAction;
  /**
   * Diagram viewer filter.
   */
  private DelegatedViewerFilter _diagramViewerFilter;
  /**
   * Used to flag if current page is dirty.
   */
  private volatile boolean _isDiagramViewerDirty;
  private MoveRepresentationAction _moveDiagramAction;
  private OpenRepresentationsAction _openRepresentation;
  private RenameRepresentationAction _renameRepresentationAction;
  /**
   * Sash that layouts activities in one column and diagrams in another one.
   */
  private MDSashForm _sashForm;
  /**
   * UI action that triggers a diagram viewer filtering operation.
   */
  private AbstractViewerFilteringAction _sectionActionDrivingFilter;
  /**
   * Container that hosts all sections.
   */
  private Composite _sectionContainer;
  private BaseSelectionListenerAction _showInCapellaExplorerAction;
  /**
   * Viewer that displays diagrams.
   */
  private TreeViewer _viewer;
  /**
   * Container that hosts the viewer.
   */
  private Composite _viewerContainer;

  /**
   * Our session listener
   */
  private SessionListener _sessionListener;

  /**
   * Constructor.
   * @param editor_p
   * @param id_p
   * @param title_p
   */
  public AbstractCapellaArchitectureDashboardPage(FormEditor editor_p, String id_p) {
    super(editor_p, id_p, ICommonConstants.EMPTY_STRING); /* Page title is computed afterward */
    setPartName(getPageTitle());
    // Load contributors.
    loadPagesContributors();

  }

  /**
   * Adjust image href.
   * @param richText_p
   * @param used to collect image.
   */
  protected abstract void adjustImageHRef(FormText richText_p, CapellaDashboardActivator activator_p);

  /**
   * Returns <code>true</code> if specified selection contains only {@link DRepresentation}.
   * @param selection_p
   * @return
   */
  @SuppressWarnings("unchecked")
  boolean containsOnlyRepresentations(IStructuredSelection selection_p) {
    boolean result = true;
    Iterator<Object> iterator = selection_p.toList().iterator();
    while (iterator.hasNext() && result) {
      Object representation = iterator.next();
      if (representation instanceof ItemWrapper) {
        representation = ((ItemWrapper) representation).getWrappedObject();
      }
      if (!(representation instanceof DRepresentation)) {
        result = false;
        break;
      }
    }
    return result;
  }

  /**
   * Create contributed sections
   * @param sectionContainer_p
   * @param managedForm_p
   */
  protected void createContributedSections(Composite sectionContainer_p, IManagedForm managedForm_p) {
    // Loop over pages contributors.
    for (ICapellaDashboardPagesProvider contributor : __capellaPagesProviders) {
      handleContributedSectionsFor(contributor, sectionContainer_p, managedForm_p);
    }
  }

  /**
   * @see org.polarsys.capella.core.dashboard.editor.pages.AbstractCapellaDashboardPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
   */
  @Override
  protected void createFormContent(IManagedForm managedForm_p) {
    super.createFormContent(managedForm_p);
    // Set the header title.
    ScrolledForm form = managedForm_p.getForm();
    form.setText(getHeaderTitle());
    // Install a default layout.
    GridLayout layout = new GridLayout();
    Composite body = form.getBody();
    body.setLayout(layout);
    _sashForm = new MDSashForm(body, SWT.NULL);
    _sashForm.setData("form", managedForm_p); //$NON-NLS-1$
    managedForm_p.getToolkit().adapt(_sashForm, false, false);
    _sashForm.setMenu(form.getBody().getMenu());
    // Install a default layout based on two columns.
    _sashForm.setLayout(new GridLayout(2, false));
    _sashForm.setLayoutData(new GridData(GridData.FILL_BOTH));
    // The left one contains sections.
    _sectionContainer = createSectionContainer(_sashForm, managedForm_p);
    // The right one, contains a tree viewer that displays diagrams for related page.
    _viewerContainer = createViewerContainer(_sashForm, managedForm_p);
    _viewerContainer.setLayoutData(new GridData(GridData.FILL_BOTH));
    // Create sections.
    createSections(_sectionContainer, managedForm_p);
    // Create contributes sections.
    createContributedSections(_sectionContainer, managedForm_p);
    // Create the viewer.
    Couple<TreeViewer, Section> createdViewer = createViewer(_viewerContainer, managedForm_p);
    _viewer = createdViewer.getKey();
    // Hook resize listener for the sash behavior.
    hookResizeListener();
    // 66% 34% weights for the sash.
    _sashForm.setWeights(new int[] { SECTION_WEIGHT, DIAGRAMS_VIEWER_WEIGHT });
    // Register as property listener, to live refresh check / unckeck sections.
    Activator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
    Activator.getDefault().addProjectsPropertyChangeListener(this);

    // Force a layout to make sure, page is well refreshed.
    managedForm_p.reflow(true);
  }

  /**
   * Create the overview section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @return
   */
  protected void createOverviewSection(Composite sectionContainer_p, IManagedForm managedForm_p) {
    CapellaDashboardActivator activator = CapellaDashboardActivator.getDefault();
    String overviewContent = FileHelper.readFile(activator.getPluginId() + "/xml/overview/" + getOverviewFileName()); //$NON-NLS-1$
    FormText richText = FormHelper.createRichText(managedForm_p.getToolkit(), sectionContainer_p, overviewContent, new FormTextPageLinkAdapter(getEditor()));
    adjustImageHRef(richText, activator);
    richText.marginHeight = 0;
    richText.marginWidth = 0;
  }

  /**
   * Create the container that hosts sections.<br>
   * This one layouts its content using a {@link TableWrapLayout} to allow sections to have wrapped hyper controls.
   * @param parent_p
   * @param managedForm_p
   */
  protected Composite createSectionContainer(Composite parent_p, IManagedForm managedForm_p) {
    Composite container = FormHelper.createCompositeWithLayoutType(managedForm_p.getToolkit(), parent_p, LayoutType.TABLEWRAP_LAYOUT, 1, true);
    container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
    return container;
  }

  /**
   * Create section displayed in this page.<br>
   * Default implementation return <code>null</code>.
   * @param sectionContainer_p
   * @return First created section. This one is used to enable UI alignment with Diagram viewer section.
   */
  protected void createSections(final Composite sectionContainer_p, IManagedForm managedForm_p) {
    AbstractDescriptionAction displayDescription = new AbstractDescriptionAction(sectionContainer_p.getShell()) {
      /**
       * @see org.polarsys.capella.core.dashboard.actions.AbstractDescriptionAction#getDescriptionFile()
       */
      @Override
      protected String getDescriptionFile() {
        return getPageDescriptionFileName();
      }

    };
    Form formWidget = managedForm_p.getForm().getForm();
    formWidget.getMenuManager().add(displayDescription);
    formWidget.getToolBarManager().add(displayDescription);
    formWidget.getToolBarManager().update(true);
  }

  /**
   * Create Transverse Modeling Section.
   * @param sectionContainer_p
   * @param managedForm_p
   * @param filteringAction_p
   * @param descriptionAction_p
   * @param newClassDiagramAdapter_p
   * @param newStateMachineDiagramAdapter_p
   * @param newStateModeFunctionsMatrixAdapter_p
   * @param newStateModeFunctionsMatrixLabel_p
   * @return
   */
  protected Section createTransverseModeling(Composite sectionContainer_p, IManagedForm managedForm_p, AbstractViewerFilteringAction filteringAction_p,
      AbstractDescriptionAction descriptionAction_p, AbstractHyperlinkAdapter newClassDiagramAdapter_p,
      AbstractHyperlinkAdapter newStateMachineDiagramAdapter_p, AbstractHyperlinkAdapter newStateModeFunctionsMatrixAdapter_p,
      String newStateModeFunctionsMatrixLabel_p) {
    IAction[] toolbarActions = new IAction[] { descriptionAction_p, filteringAction_p };
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createTwistieSectionWithToolbar(sectionContainer_p, managedForm_p,
            Messages.AbstractCapellaArchitectureDashboardPage_TransverseModelingSection_Title, null, false, MiscHelper.asList(toolbarActions));
    // Get the section composite.
    Composite sectionComposite = section.getValue();
    // Get the form toolkit.
    FormToolkit toolkit = managedForm_p.getToolkit();
    CapellaDashboardActivator capellaDashboardActivator = CapellaDashboardActivator.getDefault();
    // Create an hyper link for Describe the information exchanged between Functions and / or between Components with a New Class diagram.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_CLASS_DIAGRAM),
        Messages.AbstractCapellaArchitectureDashboardPage_NewClassDiagram_Title, null,
        Messages.AbstractCapellaArchitectureDashboardPage_NewClassDiagram_Description, newClassDiagramAdapter_p);
    // Create an hyper link for Describe the States and Modes of the system with a new State Machine.
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_NEW_MODE_STATE_DIAGRAM),
        Messages.AbstractCapellaArchitectureDashboardPage_NewStateMachinDiagram_Title, null,
        Messages.AbstractCapellaArchitectureDashboardPage_NewStateMachinDiagram_Description, newStateMachineDiagramAdapter_p);
    // Create an hyper link for Create a new State & Mode / Functions matrix.
    String newStateModeFunctionsMatrixLabel = newStateModeFunctionsMatrixLabel_p;
    if (null == newStateModeFunctionsMatrixLabel) {
      newStateModeFunctionsMatrixLabel = Messages.AbstractCapellaArchitectureDashboardPage_NewStateModeFunctionsMatrix_Title;
    }
    FormHelper.createLinkWithDescription(toolkit, sectionComposite, capellaDashboardActivator.getImage(IImageKeys.IMG_TRACEABILITY_MATRIX),
        newStateModeFunctionsMatrixLabel, null, null, newStateModeFunctionsMatrixAdapter_p);
    return section.getKey();
  }

  /**
   * Create the viewer that displays diagrams.
   * @param viewerContainer_p
   * @param managedForm_p
   */
  protected Couple<TreeViewer, Section> createViewer(Composite viewerContainer_p, IManagedForm managedForm_p) {
    // Create the section.
    Couple<Section, Composite> section =
        FormHelper.createSectionWithDescription(managedForm_p.getToolkit(), viewerContainer_p,
            Messages.AbstractCapellaArchitectureDashboardPage_DiagramsViewer_Title, null);
    FormHelper.createSectionToolbar(section.getKey(), Collections.singletonList(getResetSectionFilter()));
    section.getKey().setLayoutData(new GridData(GridData.FILL_BOTH));
    // Create a tree viewer with a regular expression filter.
    PatternFilter patternFilter = new TreePatternFilter();
    FilteredTree filteredTree = new FilteredTree(section.getValue(), SWT.NONE | SWT.SINGLE | SWT.BORDER, patternFilter);
    managedForm_p.getToolkit().adapt(filteredTree);
    // Get the tree viewer.
    final TreeViewer treeViewer = filteredTree.getViewer();
    // Add double click listener to open double-clicked diagrams.
    treeViewer.addDoubleClickListener(new IDoubleClickListener() {
      /**
       * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
       */
      public void doubleClick(DoubleClickEvent event_p) {
        IStructuredSelection selection = (IStructuredSelection) event_p.getSelection();
        // Open selected representations.
        OpenRepresentationsAction action = new OpenRepresentationsAction();
        action.selectionChanged(selection);
        action.run();
      }
    });
    // Add the filter driven by section.
    _diagramViewerFilter = new DelegatedViewerFilter();
    treeViewer.addFilter(_diagramViewerFilter);
    // Set content provider.
    treeViewer.setContentProvider(new CapellaArchitectureContentProvider(getHandledViewpoint(), getFilteringMetaClassForCommonViewpoint()));
    // Set label provider.
    treeViewer.setLabelProvider(new CapellaNavigatorLabelProvider());
    // Auto expand the root node.
    treeViewer.setAutoExpandLevel(2);

    // Set the initial input. Also make sure to refresh the viewer if underlying
    // resources change. (e.g. after an clearcase undo checkout)
    Session session = getEditorInput().getSession();
    _sessionListener = new SessionListener() {
      public void notify(int changeKind_p) {
        if (changeKind_p == SessionListener.REPLACED) {
          markDiagramViewerAsDirty();
        }
      }
    };
    getSession().addListener(_sessionListener);
    treeViewer.setInput(session);

    // Forward selection changes to the editor site selection provider to notify the platform (e.g property view).
    treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
      /**
       * {@inheritDoc}
       */
      public void selectionChanged(SelectionChangedEvent event_p) {
        getEditorSite().getSelectionProvider().setSelection(event_p.getSelection());
      }
    });
    // Make a context menu for the tree viewer.
    makeViewerActions(treeViewer);
    return new Couple<TreeViewer, Section>(treeViewer, section.getKey());
  }

  /**
   * Create viewer container that hosts the viewer.<br>
   * This one layouts its content using a {@link GridLayout}.
   * @param parent_p
   * @param managedForm_p
   * @return
   */
  protected Composite createViewerContainer(Composite parent_p, IManagedForm managedForm_p) {
    return FormHelper.createCompositeWithLayoutType(managedForm_p.getToolkit(), parent_p, LayoutType.GRID_LAYOUT, 1, true);
  }

  /**
   * Declare viewer actions.<br>
   * This methods is called eache time the menu is pop-up.
   * @param contextMenuManager_p
   * @param treeViewer_p
   */
  protected void declareViewerActions(MenuManager contextMenuManager_p, TreeViewer treeViewer_p) {
    // Menu manager is not extensible at the moment.
    ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
    ISelectionProvider selectionProvider = getEditorSite().getSelectionProvider();

    _showInCapellaExplorerAction = new BaseSelectionListenerAction(Messages.AbstractCapellaArchitectureDashboardPage_ShowInCapellaExplorerAction_Title) {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void run() {
        LocateInCapellaExplorerAction callback = new LocateInCapellaExplorerAction();
        callback.setSite(getEditorSite());
        callback.run(_showInCapellaExplorerAction);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      protected boolean updateSelection(IStructuredSelection selection_p) {
        return containsOnlyRepresentations(selection_p);
      }
    };
    _showInCapellaExplorerAction.setActionDefinitionId("org.polarsys.capella.core.platform.sirius.ui.navigator.locateInCapellaExplorerCommand"); //$NON-NLS-1$
    _showInCapellaExplorerAction.setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor(
        org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys.IMG_SHOW_IN_CAPELLA_EXPLORER));
    SelectionHelper.registerToSelectionChanges(_showInCapellaExplorerAction, selectionProvider);
    contextMenuManager_p.add(_showInCapellaExplorerAction);

    contextMenuManager_p.add(new Separator());
    _openRepresentation = new OpenRepresentationsAction() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected boolean updateSelection(IStructuredSelection selection_p) {
        return containsOnlyRepresentations(selection_p);
      }
    };
    SelectionHelper.registerToSelectionChanges(_openRepresentation, selectionProvider);
    contextMenuManager_p.add(_openRepresentation);
    contextMenuManager_p.add(new Separator());

    _cloneAction = new CloneAction(treeViewer_p);
    _cloneAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
    SelectionHelper.registerToSelectionChanges(_cloneAction, selectionProvider);
    contextMenuManager_p.add(_cloneAction);

    _deleteRepresentationAction = new DeleteRepresentationAction() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected boolean updateSelection(IStructuredSelection selection_p) {
        return containsOnlyRepresentations(selection_p);
      }
    };
    _deleteRepresentationAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
    _deleteRepresentationAction.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
    SelectionHelper.registerToSelectionChanges(_deleteRepresentationAction, selectionProvider);
    contextMenuManager_p.add(_deleteRepresentationAction);

    contextMenuManager_p.add(new Separator(GROUP_MOVE));
    _moveDiagramAction = new MoveRepresentationAction();
    SelectionHelper.registerToSelectionChanges(_moveDiagramAction, selectionProvider);

    _renameRepresentationAction = new RenameRepresentationAction() {
      /**
       * {@inheritDoc}
       */
      @Override
      protected boolean updateSelection(IStructuredSelection selection_p) {
        return containsOnlyRepresentations(selection_p);
      }
    };
    SelectionHelper.registerToSelectionChanges(_renameRepresentationAction, selectionProvider);
    contextMenuManager_p.add(_renameRepresentationAction);
  }

  /**
   * @see org.eclipse.ui.forms.editor.FormPage#dispose()
   */
  @Override
  public void dispose() {
    // Unregister as property listener.
    CapellaDashboardActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
    // Dispose viewer actions.
    ISelectionProvider selectionProvider = getEditorSite().getSelectionProvider();
    if (null != _renameRepresentationAction) {
      selectionProvider.removeSelectionChangedListener(_renameRepresentationAction);
      _renameRepresentationAction = null;
    }
    if (null != _deleteRepresentationAction) {
      selectionProvider.removeSelectionChangedListener(_deleteRepresentationAction);
      _deleteRepresentationAction = null;
    }
    if (null != _openRepresentation) {
      selectionProvider.removeSelectionChangedListener(_openRepresentation);
      _openRepresentation = null;
    }
    if (null != _moveDiagramAction) {
      selectionProvider.removeSelectionChangedListener(_moveDiagramAction);
      _moveDiagramAction.dispose();
      _moveDiagramAction = null;
    }
    if (null != _cloneAction) {
      selectionProvider.removeSelectionChangedListener(_cloneAction);
      _cloneAction = null;
    }
    if (null != _showInCapellaExplorerAction) {
      selectionProvider.removeSelectionChangedListener(_showInCapellaExplorerAction);
      _showInCapellaExplorerAction = null;
    }
    if (null != _sessionListener) {
      getSession().removeListener(_sessionListener);
    }
    _viewer = null;
    super.dispose();
  }

  /**
   * Handle property change.<br>
   * Default implementation does nothing.
   * @param event_p
   * @param value_p
   * @param property_p
   * @return
   */
  protected abstract boolean doPropertyChange(PropertyChangeEvent event_p, boolean value_p, String property_p);

  /**
   * {@inheritDoc}
   */
  @Override
  public CapellaDashboardEditor getEditor() {
    return (CapellaDashboardEditor) super.getEditor();
  }

  /**
   * @see org.eclipse.ui.part.EditorPart#getEditorInput()
   */
  @Override
  public CapellaDashboardEditorInput getEditorInput() {
    return (CapellaDashboardEditorInput) super.getEditorInput();
  }

  /**
   * Get the EClass this architecture page is handling.<br>
   * This EClass is used to filter out UI from other levels in Common viewpoint part.<br>
   * Example: in SA page we only display Common representations hold by CtxPackage.Literals.SYSTEM_ANALYSIS.
   * @return
   */
  public abstract EClass getFilteringMetaClassForCommonViewpoint();

  /**
   * Get the viewpoint handled by this page.
   * @return an empty string.
   */
  protected String getHandledViewpoint() {
    return ICommonConstants.EMPTY_STRING;
  }

  /**
   * Get the header page title.
   * @return
   */
  protected String getHeaderTitle() {
    return getPageTitle();
  }

  /**
   * Get the underlying Capella project, {@link CapellaSessionEditorInput#getCapellaProject()}.
   * @return
   */
  public Project getCapellaProject() {
    return getEditorInput().getCapellaProject();
  }

  /**
   * Get the XML overview file that defines the form text content for the overview section.<br>
   * {@link #createOverviewSection(Composite, IManagedForm, String)}.<br>
   * Returned path must be relative to <code>org.polarsys.capella.core.dashboard/xml/overview</code> folder.<br>
   * @return
   */
  protected abstract String getOverviewFileName();

  /**
   * Get the XML file that defines the form text content for the description of the page.<br>
   * Returned path must be relative to <code>org.polarsys.capella.core.dashboard/xml</code> folder.<br>
   * @return
   */
  protected abstract String getPageDescriptionFileName();

  /**
   * Get the page title (used to render tab text).
   * @return a not <code> null</code> string.
   */
  protected abstract String getPageTitle();

  /**
   * Create an action to remove the section filter.
   * @return
   */
  private Action getResetSectionFilter() {
    Action resetSectionFilter = new Action() {
      /**
       * @see org.eclipse.jface.action.Action#run()
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void run() {
        _diagramViewerFilter.setDelegatedFilter(null);
        uncheckSectionFilterAction();
        ViewerHelper.refresh(getViewer());
      }
    };
    resetSectionFilter.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
    resetSectionFilter.setToolTipText(Messages.AbstractCapellaArchitectureDashboardPage_ResetSectionFilterAction_Title);
    return resetSectionFilter;
  }

  /**
   * Get the section container.
   * @return the sectionContainer
   */
  protected Composite getSectionContainer() {
    return _sectionContainer;
  }

  /**
   * Get the underlying Sirius session, {@link CapellaSessionEditorInput#getSession()}.
   * @return
   */
  public Session getSession() {
    return getEditorInput().getSession();
  }

  /**
   * Get the tree viewer that displays diagrams.
   * @return a not <code>null</code> instance.
   */
  public TreeViewer getViewer() {
    return _viewer;
  }

  /**
   * Handle contributed sections for specified {@link ICapellaDashboardPagesProvider}.
   * @param contributor_p
   * @param sectionContainer_p
   * @param managedForm_p
   */
  protected abstract void handleContributedSectionsFor(ICapellaDashboardPagesProvider contributor_p, Composite sectionContainer_p, IManagedForm managedForm_p);

  /**
   * Hook resize listener for sash form.
   */
  private void hookResizeListener() {
    Listener listener = _sashForm.getListener();
    Control[] children = _sashForm.getChildren();
    for (Control element : children) {
      if (element instanceof Sash) {
        continue;
      }
      element.addListener(SWT.Resize, listener);
    }
  }

  /**
   * Load the pages contributors.<br>
   * See CAPELLA_ARCHITECTURE_PAGES_PROVIDER_EXTENSION_POINT_ID extension-point.
   */
  private void loadPagesContributors() {
    if (null == __capellaPagesProviders) {
      // Load providers i.e contributors.
      IConfigurationElement[] configurationElements =
          ExtensionPointHelper.getConfigurationElements(CapellaDashboardActivator.getDefault().getPluginId(),
              CAPELLA_ARCHITECTURE_PAGES_PROVIDER_EXTENSION_POINT_ID);
      // Create the list that stores the contributors.
      __capellaPagesProviders = new ArrayList<ICapellaDashboardPagesProvider>(configurationElements.length);
      // Loop over contributions to fill in the list.
      for (IConfigurationElement configurationElement : configurationElements) {
        try {
          // Get the contribution.
          ICapellaDashboardPagesProvider contributedProvider =
              (ICapellaDashboardPagesProvider) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
          // Add it into the list.
          __capellaPagesProviders.add(contributedProvider);
        } catch (Exception exception_p) {
          StringBuilder loggerMessage = new StringBuilder("AbstractCapellaArchitectureDashboardPage.createContributedSections(..) _ "); //$NON-NLS-1$
          loggerMessage.append(exception_p.getMessage());
          __logger.warn(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI), exception_p);
        }
      }
    }
  }

  /**
   * Make a contextual menu for specified viewer.
   * @param treeViewer_p
   */
  private void makeViewerActions(final TreeViewer treeViewer_p) {
    MenuManager contextMenuManager = new MenuManager("representationsPopup"); //$NON-NLS-1$
    // Used to make sure the contextMenu is filled up dynamically for Move Diagrams...
    contextMenuManager.addMenuListener(new IMenuListener2() {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      public void menuAboutToHide(IMenuManager manager_p) {
        manager_p.remove(MoveRepresentationAction.MOVE_DIAGRAMS_MENU_ID);
        // Make sure action contained list are freed at each selection time.
        _moveDiagramAction.dispose();
      }

      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      public void menuAboutToShow(IMenuManager manager_p) {
        manager_p.appendToGroup(GROUP_MOVE, _moveDiagramAction.fillContextMenu((IStructuredSelection) treeViewer_p.getSelection()));
      }
    });
    Control control = treeViewer_p.getControl();
    // Add here some actions.
    declareViewerActions(contextMenuManager, treeViewer_p);
    Menu contextMenu = contextMenuManager.createContextMenu(control);
    control.setMenu(contextMenu);
    // Hook the menu into the Workbench and its declarative action mechanisms.
    getEditorSite().registerContextMenu(contextMenuManager, treeViewer_p);
  }

  /**
   * Mark diagram viewer as dirty.
   */
  public void markDiagramViewerAsDirty() {
    // If active, refresh it directly.
    if (isActive()) {
      refreshDiagramViewer();
    } else {
      _isDiagramViewerDirty = true;
    }
  }

  /**
   * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
   */
  public void propertyChange(PropertyChangeEvent event_p) {

    if (Activator.getDefault().existPropertyStore(event_p)) {
      return;
    }
    String property = event_p.getProperty();
    boolean value = false;
    if (event_p.getNewValue() instanceof String) {
      String stringValue = (String) event_p.getNewValue();
      if (stringValue.isEmpty()) {
        return;
      }
      value = Boolean.parseBoolean(stringValue);
    } else {
      value = ((Boolean) event_p.getNewValue()).booleanValue();
    }
    if (doPropertyChange(event_p, value, property)) {
      getManagedForm().reflow(false);
      getManagedForm().refresh();
    }

  }

  /**
   * Refresh the diagram viewer.
   */
  public void refreshDiagramViewer() {
    if ((null != _viewer) && !_viewer.getControl().isDisposed()) {
      ViewerHelper.refresh(getViewer());
      _isDiagramViewerDirty = false;
    }
  }

  /**
   * @see org.eclipse.ui.forms.editor.FormPage#setActive(boolean)
   */
  @Override
  public void setActive(boolean active_p) {
    super.setActive(active_p);
    if (active_p) {
      // Set a new selection to the property sheet page on dashboard page activation.
      setCurrentPageSelectionToPropertySheetPage();
      // If active and dirty, refreshes the diagram viewer.
      if (_isDiagramViewerDirty) {
        refreshDiagramViewer();
      }
      updateActionBars();
    }
  }

  /**
   * Refresh the property sheet page according to the current viewer selection.<br>
   * {@link #getViewer()}.
   */
  protected void setCurrentPageSelectionToPropertySheetPage() {
    // Call the property sheet page without loading it.
    // Indeed, we won't load it if the view is not displayed.
    TabbedPropertySheetPage propertySheetPage = getEditor().getPropertySheetPage();
    if (null != propertySheetPage) {
      propertySheetPage.selectionChanged(getEditor(), getViewer().getSelection());
    }
  }

  /**
   * Set a viewer filter on the diagram viewer.
   * @param filter_p <code>null</code> means reset the filter.
   * @param action_p
   */
  public void setViewerFilter(ViewerFilter filter_p, AbstractViewerFilteringAction action_p) {
    // If given filter is null that means reset the filter.
    if (null == filter_p) {
      getResetSectionFilter().run();
      return;
    }
    // Get the tree viewer.
    TreeViewer treeViewer = getViewer();
    _diagramViewerFilter.setDelegatedFilter(filter_p);
    // Indeed, when the tree viewer is expanded, depending on the applied filter, SWTException are thrown.
    // It seems linked to Europa release. The only way to override that is to first collapse the tree viewer before refreshing it.
    Control control = treeViewer.getControl();
    try {
      // Switch off redraw
      control.setRedraw(false);
      treeViewer.collapseAll();
      treeViewer.refresh();
      treeViewer.expandAll();
      // Uncheck the previous action's section that drove the filter.
      uncheckSectionFilterAction();
      // Keep a reference on the action's section that drives the filter.
      _sectionActionDrivingFilter = action_p;
    } finally {
      // Finally, redraw again.
      control.setRedraw(true);
    }
  }

  /**
   * Uncheck the previous action's section that drove the filter.
   */
  private void uncheckSectionFilterAction() {
    if (null != _sectionActionDrivingFilter) {
      _sectionActionDrivingFilter.setChecked(false);
      _sectionActionDrivingFilter = null;
    }
  }

  /**
   * Update action bars (handlers).
   * @param editorActionBars
   */
  public void updateActionBars() {
    IActionBars editorActionBars = getEditorSite().getActionBars();
    editorActionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), _deleteRepresentationAction);
    editorActionBars.setGlobalActionHandler(ActionFactory.RENAME.getId(), _renameRepresentationAction);
    // Update action bars to make sure global ActionHandler are updated accordingly.
    editorActionBars.updateActionBars();
  }
}
