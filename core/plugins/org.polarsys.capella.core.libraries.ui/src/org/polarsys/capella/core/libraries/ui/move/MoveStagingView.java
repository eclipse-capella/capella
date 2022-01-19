/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.libraries.ui.move;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

import org.apache.log4j.Priority;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.internal.ui.DebugPluginImages;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.ImageURIRegistry;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.ui.viewer.ColumnViewerInformationControlToolTipSupport;
import org.eclipse.emf.common.ui.viewer.IStyledLabelDecorator;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.DecoratingColumLabelProvider.StyledLabelProvider;
import org.eclipse.emf.edit.ui.provider.DiagnosticDecorator;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.JFacePreferences;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.commands.ICommandImageService;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.flexibility.wizards.ui.FlexibilityColors;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManager;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.tools.report.util.LogExt;
import org.polarsys.capella.common.tools.report.util.ReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.toolkit.ToolkitPlugin;
import org.polarsys.capella.core.data.core.validation.constraint.ReferentialConstraintsResourceSetListener;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.model.helpers.move.CapellaMoveHelper;
import org.polarsys.capella.core.model.helpers.move.Stage;
import org.polarsys.capella.core.model.helpers.move.StageListener;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.drop.ExplorerDropAdapterAssistant;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

import com.google.common.collect.Lists;

public class MoveStagingView extends ViewPart implements ISelectionProvider, ITabbedPropertySheetPageContributor, SessionListener {

  /**
   * The context menu id for the stage viewer
   */
  public final static String STAGEVIEWER_CONTEXT_MENU = "org.polarsys.capella.core.libraries.ui.moveview.stageViewer"; //$NON-NLS-1$
  
  /**
   * The context menu id for the destination viewer
   */
  public final static String DESTINATIONVIEWER_CONTEXT_MENU = "org.polarsys.capella.core.libraries.ui.moveview.destinationViewer"; //$NON-NLS-1$

  /**
   * The transfer view id.
   */
  public static final String VIEW_ID = "org.polarsys.capella.core.libraries.ui.moveview"; //$NON-NLS-1$

  TreeViewer stageViewer;
  TreeViewer destinationViewer;
  ColumnViewerInformationControlToolTipSupport tooltipSupport;

  FormToolkit toolkit;

  private final AdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
  private final AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(adapterFactory);

  Stage stage;
  StageListener listener;

  Form form;

  Section stageSection;
  Section destinationSection;

  /**
   * This executes the transfer
   */
  IAction executeAction;

  /**
   * Actions on the left viewer
   */
  IAction addRequiredAction;
  IAction addAllRequiredAction;
  IAction unstageAction;

  IAction stageExpandAllAction;
  IAction stageCollapseAllAction;

  IAction nextEleementAction;
  IAction previousElementAction;
  /**
   * Actions on the right viewer
   */
  IAction clearParentAction;
  
  IAction destExpandAllAction;
  IAction destCollapseAllAction;

  /**
   * The session associated to the current transfer
   */
  Session session;

  /**
   * Get the underlying stage model of the viewer, or null if the view has not been {@link #init(Collection) initialized}
   * @return the stage 
   */
  public Stage getStage(){
    return stage;
  }

  /**
   * Initialize the view with the collection of elements. The elements must all belong to the same editing domain.
   * Calling this method multiple times has no effect unless {@link #reset()} was called in between.
   * 
   * @param dropped the elements to initialize the viewer
   */
  public void init(Collection<EObject> dropped) {
    if (stage == null) {
      stage = new Stage(EcoreUtil2.getEditingDomain(dropped));

      listener = new StageListener() {

        @Override
        public void stageChanged(Stage s) {
          PlatformUI.getWorkbench().getDisplay().asyncExec(() -> handleStageChanged(s));
        }

        @Override
        public void elementsAdded(Collection<EObject> elements) {
          PlatformUI.getWorkbench().getDisplay().asyncExec(() -> handleStageElementsAdded(elements));
        }

        @Override
        public void parentChanged(EObject staged, EObject oldParent, EObject newParent) {
          PlatformUI.getWorkbench().getDisplay().asyncExec(() -> handleStageParentChanged(staged, oldParent, newParent));
        }

        @Override
        public void elementsRemoved(Collection<? extends EObject> elements) {
          PlatformUI.getWorkbench().getDisplay().asyncExec(() -> handleStageElementsRemoved(elements));
        }
      };
      stage.addStageListener(listener);
      stageViewer.setInput(stage.getEditingDomain().getResourceSet());
      stageViewer.setLabelProvider(createLabelProvider(true));
      stageViewer.setFilters(new CollectionTreeFilter(stage.getElements(), false));
      tooltipSupport = new ColumnViewerInformationControlToolTipSupport(stageViewer, new DiagnosticDecorator.EditingDomainLocationListener(stage.getEditingDomain(), stageViewer) {
        @Override
        protected void setSelection(final Object object) {
          MyLocateInCapellaExplorerAction action = new MyLocateInCapellaExplorerAction();
          action.selectElementInCapellaExplorer(new StructuredSelection(object));
        }
      });

      destinationViewer.setInput(stage.getEditingDomain().getResourceSet());

      stageExpandAllAction.setEnabled(true);
      stageCollapseAllAction.setEnabled(true);
      destExpandAllAction.setEnabled(true);
      destCollapseAllAction.setEnabled(true);
      previousElementAction.setEnabled(true);
      nextEleementAction.setEnabled(true);

      initSessionListener(stage.getEditingDomain());
      stage.addAll(dropped);
    }

  }

  @Override
  public void createPartControl(Composite parent) {

    toolkit = new FormToolkit(parent.getDisplay());
    form = toolkit.createForm(parent);

    GridLayout layout = new GridLayout(2, true);
    form.getBody().setLayout(layout);

    stageSection = toolkit.createSection(form.getBody(),
        Section.TITLE_BAR|Section.DESCRIPTION|
        Section.EXPANDED);
    stageSection.setText(Messages.MoveStagingView_stageSectionTitle);
    stageSection.setDescription(Messages.MoveStagingView_stageSectionDescription);

    GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
    stageSection.setLayoutData(gd);

    Composite stageSectionClient = toolkit.createComposite(stageSection);
    stageSectionClient.setLayout(new GridLayout(2, false));
    stageSection.setClient(stageSectionClient);

    createStageViewer(stageSectionClient);

    destinationSection = toolkit.createSection(form.getBody(),
        Section.TITLE_BAR|Section.DESCRIPTION|
        Section.EXPANDED);
    destinationSection.setText(Messages.MoveStagingView_destinationSectionTitle);
    destinationSection.setDescription(Messages.MoveStagingView_destinationSectionDescription);

    gd = new GridData(SWT.FILL, SWT.FILL, true, true);
    destinationSection.setLayoutData(gd);

    Composite destinationSectionClient = toolkit.createComposite(destinationSection);
    destinationSectionClient.setLayout(new GridLayout(2, false));
    destinationSection.setClient(destinationSectionClient);
    createDestinationViewer(destinationSectionClient);

    getViewSite().setSelectionProvider(this);

    initTreePacker();
    initSelectionSynchronizer();

    initToolbar();

  }

  private void initToolbar() {
    ImageDescriptor descriptor = DebugPluginImages.getImageRegistry().getDescriptor(IDebugUIConstants.IMG_ACT_RUN);
    executeAction = new Action("", descriptor) { //$NON-NLS-1$

    @Override
    public void run() {
      AtomicBoolean forcedMove = new AtomicBoolean();
      AtomicReference<Diagnostic> referenceErrors = new AtomicReference<>();
      destinationViewer.getTree().setRedraw(false);
      ResourceSetListener validateListener = new ReferentialConstraintsResourceSetListener(status -> {
        referenceErrors.set(status);
        MyDiagnosticDialog dialog = new MyDiagnosticDialog(getViewSite().getShell(), status); 
        if (dialog.open() == Window.CANCEL) {
          throw new RollbackException(new Status(IStatus.CANCEL, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), Messages.MoveStagingView_CancelStatusMessage));
        } else {
          forcedMove.set(true);
        }
      });
      TransactionalEditingDomain domain = (TransactionalEditingDomain) stage.getEditingDomain();
      domain.addResourceSetListener(validateListener);
      Diagnostic result = null;
      try {
        result = stage.executeWithDiagnostics();
        report(result);
        if (result.getSeverity() == Diagnostic.OK) {
          boolean resetView = false;
          Diagnostic referenceErrorDiagnostic = referenceErrors.get();
          if (referenceErrorDiagnostic != null) {
            report(referenceErrorDiagnostic);
            String userDecision = null;
            if (forcedMove.get()) {
              userDecision = Messages.MoveStagingView_forced_move_message;
              resetView = true;
            } else {
              userDecision = Messages.MoveStagingView_CancelStatusMessage;
            }
            report(new BasicDiagnostic(Stage.SOURCE, 0, userDecision, new Object[0]));
          } else {
            resetView = true;
          }
          if (resetView) {
            reset();
          } 
        } else {
          DiagnosticDialog.open(getViewSite().getShell(), Messages.MoveStagingView_fail_dialog_title, Messages.MoveStagingView_fail_dialog_message, result);
        }
      } finally {
        domain.removeResourceSetListener(validateListener);
        destinationViewer.getTree().setRedraw(true);
      }
    }
    };

    executeAction.setEnabled(false);
    getViewSite().getActionBars().getToolBarManager().add(executeAction);
  }


  private void report(Diagnostic d) {
    Priority prio = LogExt.convertSeverityToPriority(d);
    if (d.getChildren().isEmpty()) {
      ReportManagerDefaultComponents.getReportManagerForModel().log(prio, d);
    } else {
      for (Diagnostic child : d.getChildren()) {
        report(child);
      }
    }
  }

  // Mirrors selection of staged elements in the left view to the same elements in the right view and vice versa
  private void initSelectionSynchronizer() {

    ISelectionChangedListener listener = new ISelectionChangedListener() {
      @Override
      public void selectionChanged(SelectionChangedEvent event) {
        TreeViewer target = null;
        if (((TreeViewer)event.getSelectionProvider()).getTree().isFocusControl()) {
          if (event.getSelectionProvider() == stageViewer) {
            target = destinationViewer;
          } else {
            target = stageViewer;
          }
          if (((IStructuredSelection) event.getSelection()).size() == 1) {
            Object elem = ((IStructuredSelection) event.getSelection()).getFirstElement();
            if (stage.getElements().contains(elem)) {
              target.setSelection(event.getSelection());
              target.reveal(elem);
            }
          }
        }
      }
    };
    stageViewer.addSelectionChangedListener(listener);
    destinationViewer.addSelectionChangedListener(listener);

  }

  private void initTreePacker() {
    TreeListener packer = new TreeListener() {
      private void pack(final TreeEvent e) {
        Display.getCurrent().asyncExec(new Runnable() {
          @Override
          public void run() {
            if (!e.widget.isDisposed()) {
              ((Tree)e.widget).getColumn(0).pack();
            }
          }
        });
      }

      @Override
      public void treeExpanded(final TreeEvent e) {
        pack(e);
      }

      @Override
      public void treeCollapsed(final TreeEvent e) {
        pack(e);
      }
    };
    stageViewer.getTree().addTreeListener(packer);
    destinationViewer.getTree().addTreeListener(packer);
  }

  private void createStageViewer(Composite stageSectionClient) {
    stageViewer = new TreeViewer(toolkit.createTree(stageSectionClient, SWT.MULTI | SWT.BORDER));

    GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
    stageViewer.getTree().setLayoutData(gd);

    TreeColumn column = new TreeColumn(stageViewer.getTree(), SWT.LEFT);
    column.setWidth(200);

    stageViewer.setAutoExpandLevel(3);

    stageViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory){

      @Override
      public Object[] getElements(Object object) {
        Set<Object> elems = new LinkedHashSet<Object>();
        for (EObject e : stage.getElements()){
          elems.add(EcoreUtil.getRootContainer(e));
        }
        return elems.toArray();
      }

      @Override
      public Object getParent(Object object) {
        return ((EObject) object).eContainer();
      }

    });

    stageViewer.addDropSupport(DND.DROP_MOVE, new Transfer[] { LocalSelectionTransfer.getTransfer() }, new ViewerDropAdapter(stageViewer) {

      @Override
      public boolean validateDrop(Object target, int operation, TransferData transferType) {

        ISelection sel =  LocalSelectionTransfer.getTransfer().getSelection();

        if (sel instanceof IStructuredSelection){

          Collection<EObject> eObjects = new ArrayList<EObject>();

          for (Iterator<?> it = ((IStructuredSelection) sel).iterator(); it.hasNext();) {
            Object next = it.next();
            if (next instanceof EObject) {
              eObjects.add((EObject) next);
            } else {
              return false;
            }
          }

          EditingDomain editingDomain = EcoreUtil2.getEditingDomain(eObjects);
          if (editingDomain == null) {
            return false;
          }

          if (stage == null || stage.getEditingDomain() == editingDomain && Collections.disjoint(stage.getElements(), eObjects)){
            return true;
          }
        }

        return false;
      }

      @Override
      public boolean performDrop(Object data) {
        // validate() ensures that this is safe
        @SuppressWarnings("unchecked") Collection<EObject> dropped = ((IStructuredSelection) data).toList();
        if (stage == null){
          init(dropped);
        } else {
          stage.addAll(dropped);
        }
        return true;
      }

    });

    stageViewer.addDragSupport(0, new Transfer[] { LocalSelectionTransfer.getTransfer() } , new ViewerDragAdapter(stageViewer){

      @Override
      public void dragStart(DragSourceEvent event) {
        if (stage.getElements().containsAll(stageViewer.getStructuredSelection().toList())){
          super.dragStart(event);
        } else {
          event.doit = false;
        }
      }

      @Override
      public void dragFinished(DragSourceEvent event) {
        // when the element is dropped on the right viewer, paint it in green on the left
        super.dragFinished(event);
        stageViewer.setSelection(StructuredSelection.EMPTY);
        stageViewer.refresh();
      }

    });

    ToolBarManager stageViewerToolBarManager = new ToolBarManager(SWT.FLAT | SWT.VERTICAL);
    ToolBar stageViewerToolbar = stageViewerToolBarManager.createControl(stageSectionClient);
    gd = new GridData(SWT.CENTER, SWT.TOP, false, false);
    stageViewerToolbar.setLayoutData(gd);

    unstageAction = new UnstageAction();
    addRequiredAction = new AddRequiredAction();
    addAllRequiredAction = new AddAllRequiredAction();

    stageExpandAllAction = new ExpandAllAction(stageViewer);
    stageExpandAllAction.setEnabled(false);

    stageCollapseAllAction = new CollapseAllAction(stageViewer);
    stageCollapseAllAction.setEnabled(false);

    previousElementAction = new PreviousElementAction(stageViewer);
    previousElementAction.setEnabled(false);

    nextEleementAction = new NextElementAction(stageViewer);
    nextEleementAction.setEnabled(false);

    stageViewer.addSelectionChangedListener((ISelectionChangedListener) addRequiredAction);
    stageViewer.addSelectionChangedListener((ISelectionChangedListener) unstageAction);
    stageViewer.addSelectionChangedListener((ISelectionChangedListener) addAllRequiredAction);
    
    stageViewerToolBarManager.add(stageExpandAllAction);
    stageViewerToolBarManager.add(stageCollapseAllAction);
    stageViewerToolBarManager.add(previousElementAction);
    stageViewerToolBarManager.add(nextEleementAction);
    stageViewerToolBarManager.add(unstageAction);
    stageViewerToolBarManager.add(addRequiredAction);
    stageViewerToolBarManager.add(addAllRequiredAction);

    stageViewer.setSelection(StructuredSelection.EMPTY);

    stageViewerToolBarManager.update(true);

    MenuManager stageContextMenu = new MenuManager();
    stageContextMenu.setRemoveAllWhenShown(true);
    stageContextMenu.addMenuListener(new IMenuListener() {
      @Override
      public void menuAboutToShow(IMenuManager manager) {
        if (unstageAction.isEnabled()) {
          manager.add(unstageAction);
        }
        if (addRequiredAction.isEnabled()) {
          manager.add(addRequiredAction);
        }
        if (addAllRequiredAction.isEnabled()) {
          manager.add(addAllRequiredAction);
        }
        manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
      }
    });

    stageViewer.getControl().setMenu(stageContextMenu.createContextMenu(stageViewer.getControl()));
    getViewSite().registerContextMenu(STAGEVIEWER_CONTEXT_MENU, stageContextMenu, stageViewer);

  }

  private void initSessionListener(EditingDomain editingDomain) {
    for (Session session : SessionManager.INSTANCE.getSessions()) {
      if (session.getTransactionalEditingDomain() == stage.getEditingDomain()) {
        MoveStagingView.this.session = session;
        session.addListener(MoveStagingView.this);
      }
    }
  }

  private CellLabelProvider createLabelProvider(boolean showErrorCount) {
    return new DelegatingStyledCellLabelProvider(
        new StyledLabelProvider(MoveStagingView.this.labelProvider,
        new StageLabelDecorator(showErrorCount)));
  }

  private void createDestinationViewer(Composite destinationSectionClient) {
    destinationViewer = new TreeViewer(toolkit.createTree(destinationSectionClient, SWT.BORDER | SWT.MULTI));
    TreeColumn column2 = new TreeColumn(destinationViewer.getTree(), SWT.LEFT);
    column2.setWidth(200);

    GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
    destinationViewer.getControl().setLayoutData(gd);

    destinationViewer.setAutoExpandLevel(3);

    destinationViewer.setLabelProvider(createLabelProvider(false));

    destinationViewer.setContentProvider(new AdapterFactoryContentProvider(MoveStagingView.this.adapterFactory) {
      @Override
      public Object[] getElements(Object object) {
        List<Object> result = new ArrayList<Object>();
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(object);
        IModel model = LibraryManager.INSTANCE.getModel(domain);
        for (IModel lib : LibraryManagerExt.getAllReferences(model)) {
          if (lib instanceof ICapellaModel) {
            result.add(((ICapellaModel)lib).getProject(domain));
          }
        }
        return result.toArray();
      }

      @Override
      public boolean hasChildren(Object object) {
        for (Object o : stage.getElements()) {
          if (stage.getNewParent((EObject)o) == object) {
            return true;
          }
        }
        return super.hasChildren(object);
      }

      @Override
      public Object[] getChildren(Object element) {

        List<Object> newChildren = null;

        for (EObject e : stage.getElements()) {
          if (element == stage.getNewParent(e)) {
            if (newChildren == null) {
              newChildren = new ArrayList<Object>();
            }
            newChildren.add(e);
          }
        }

        List<Object> result = Lists.newArrayList(super.getChildren(element));

        // during execution the stage model isn't updated so take care to not
        // return a moved element twice. also, staged elements are always the
        // 'last' children.
        if (newChildren != null) {
          for (Iterator<Object> it = result.iterator(); it.hasNext();) {
            Object next = it.next();
            if (newChildren.contains(next)) {
              it.remove();
            }
          }
          result.addAll(newChildren);
        }

        return result.toArray();
      }

    });

    destinationViewer.addDropSupport(0, new Transfer[] { LocalSelectionTransfer.getTransfer() }, new ViewerDropAdapter(destinationViewer) {

      final ExplorerDropAdapterAssistant a = new ExplorerDropAdapterAssistant(new CapellaMoveHelper());

      @Override
      public boolean validateDrop(Object target, int operation, TransferData transferType) {

        if (LocalSelectionTransfer.getTransfer().isSupportedType(transferType)) {

          // only accept drops from this same view
          IWorkbenchPart active = MoveStagingView.this.getSite().getPage().getActivePart();
          if (active == MoveStagingView.this) {
            ISelection sel = LocalSelectionTransfer.getTransfer().getSelection();

            if (sel instanceof IStructuredSelection){
              Collection<?> selected = ((IStructuredSelection)sel).toList();
              if (stage != null && stage.getElements().containsAll(selected)){
                return a.validateDrop(target, operation, transferType).isOK();
              }
            }
          }
        }
        return false;
      }

      @Override
      public boolean performDrop(Object data) {

        @SuppressWarnings("unchecked") Collection<EObject> staged = (((IStructuredSelection) data).toList());
        for (EObject e : staged) {
          stage.setParent(e, (EObject) getCurrentTarget());
        }

        return true;
      }
    });

    destinationViewer.addDragSupport(0, new Transfer[] { LocalSelectionTransfer.getTransfer() }, new ViewerDragAdapter(destinationViewer){

      @Override
      public void dragStart(DragSourceEvent event) {
        if (!stage.getElements().containsAll(((IStructuredSelection) viewer.getSelection()).toList())) {
          event.doit = false;
        }
        super.dragStart(event);
      }

    });

    ToolBarManager destinationViewerToolBarManager = new ToolBarManager(SWT.FLAT | SWT.VERTICAL);
    ToolBar destinationViewerToolbar = destinationViewerToolBarManager.createControl(destinationSectionClient);
    gd = new GridData(SWT.CENTER, SWT.TOP, false, false);
    destinationViewerToolbar.setLayoutData(gd);

    clearParentAction = new ClearParentAction();

    destinationViewer.addSelectionChangedListener((ISelectionChangedListener) clearParentAction);

    destExpandAllAction = new ExpandAllAction(destinationViewer);
    destExpandAllAction.setEnabled(false);

    destCollapseAllAction = new CollapseAllAction(destinationViewer);
    destCollapseAllAction.setEnabled(false);

    destinationViewerToolBarManager.add(destExpandAllAction);
    destinationViewerToolBarManager.add(destCollapseAllAction);
    destinationViewerToolBarManager.add(clearParentAction);

    destinationViewerToolBarManager.update(true);

    MenuManager destinationContextMenu = new MenuManager();
    destinationContextMenu.setRemoveAllWhenShown(true);
    destinationContextMenu.addMenuListener(new IMenuListener() {

      @Override
      public void menuAboutToShow(IMenuManager manager) {
        if (clearParentAction.isEnabled()) {
          manager.add(clearParentAction);
        }
        destinationContextMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
      }
    });

    destinationViewer.setSelection(StructuredSelection.EMPTY);
    
    destinationViewer.getControl().setMenu(destinationContextMenu.createContextMenu(destinationViewer.getControl()));
    getViewSite().registerContextMenu(DESTINATIONVIEWER_CONTEXT_MENU, destinationContextMenu, destinationViewer);
  }


  @Override
  public void setFocus() {
    stageViewer.getControl().setFocus();
  }

  private void handleStageChanged(Stage s) {
    stageViewer.refresh(true);
    destinationViewer.refresh(true);
    stageViewer.getTree().getColumn(0).pack();
    updateActions();
  }

  private void handleStageElementsAdded(Collection<EObject> elements) {

    handleStageChanged(stage);

    for (EObject e: elements) {
      stageViewer.reveal(e);
    }

    for (Iterator<EObject> it = EcoreUtil.getAllContents(elements); it.hasNext();) {
      EObject next = it.next();
      if (stage.hasBackreferences(next)) {
        stageViewer.reveal(next);
      }
    }

    stageViewer.getTree().getColumn(0).pack();

  }

  private void handleStageParentChanged(EObject staged, EObject oldParent, EObject newParent) {
    if (oldParent != null) {
      destinationViewer.refresh(oldParent, true);
    }
    destinationViewer.refresh(newParent, true);
    if (newParent != null) {
      destinationViewer.setExpandedState(newParent, true);
    } else {
      stageViewer.update(staged, null);
    }
    destinationViewer.getTree().getColumn(0).pack();
    updateActions();
  }

  private void handleStageElementsRemoved(Collection<? extends EObject> elements) {
    if (stage.getElements().isEmpty()) {
      reset();
    } else {
      stageViewer.refresh(true);
      destinationViewer.refresh(true);
      stageViewer.getTree().getColumn(0).pack();
      destinationViewer.getTree().getColumn(0).pack();

      updateActions();
    }
  }

  private void updateActions() {
    executeAction.setEnabled(stage != null && stage.canExecute());

    ((BaseSelectionListenerAction)unstageAction).selectionChanged(stageViewer.getStructuredSelection());
    ((BaseSelectionListenerAction)addRequiredAction).selectionChanged(stageViewer.getStructuredSelection());
    ((BaseSelectionListenerAction)addAllRequiredAction).selectionChanged(stageViewer.getStructuredSelection());

    ((BaseSelectionListenerAction)clearParentAction).selectionChanged(destinationViewer.getStructuredSelection());
  }

  private class AbstractDeleteAction extends BaseSelectionListenerAction {

    @Deprecated // use CommandImageConstants from 1.4 on 
    private static final String DELETE_IMAGE = "org.polarsys.capella.common.re.subcommands.delete"; //$NON-NLS-1$

    protected AbstractDeleteAction(String text) {
      super(text);
      ICommandImageService service = getViewSite().getService(ICommandImageService.class);
      setImageDescriptor(service.getImageDescriptor(DELETE_IMAGE));
    }

    @Override
    protected boolean updateSelection(IStructuredSelection selection) {
      return stage != null && !selection.isEmpty();
    }
    
  }
  
  private final class ClearParentAction extends AbstractDeleteAction {

    private ClearParentAction() {
      super(Messages.MoveStagingView_clearParentLabel);
      setToolTipText(Messages.MoveStagingView_clearParentLabel);
    }

    @Override
    public void run() {
      Collection<?> emfObjects = destinationViewer.getStructuredSelection().toList();
      for (EObject e : stage.getElements()) {
        if (EcoreUtil.isAncestor(emfObjects, stage.getNewParent(e))) {
          stage.setParent(e, null);
        }
      }
    }
  }

  private class UnstageAction extends AbstractDeleteAction {
    private UnstageAction() {
      super(Messages.MoveStagingView_unstageLabel);
      setToolTipText(Messages.MoveStagingView_unstageLabel);
    }

    @Override
    public void run() {
      Collection<EObject> toRemove = new ArrayList<EObject>();
      for (Iterator<EObject> it = EcoreUtil.getAllContents(getStructuredSelection().toList()); it.hasNext();) {
        EObject next = it.next();
        if (stage.getElements().contains(next)) {
          toRemove.add(next);
        }
      }
      stage.removeAll(toRemove);
    }

  }

  private abstract class AbstractAddRequiredAction extends BaseSelectionListenerAction {

    protected AbstractAddRequiredAction(String text) {
      super(text);
    }

    @Override
    protected boolean updateSelection(IStructuredSelection selection) {
      boolean enabled = false;
      for (Iterator<?> it = selection.iterator(); it.hasNext();) {
        EObject next = (EObject) it.next();
        if (stage != null && stage.getBackreferences(next).size() > 0) {
          enabled = true;
          break;
        }
      }
      return enabled;
    }

  }

  private class ExpandAllAction extends TreeViewerAction {
    public ExpandAllAction(TreeViewer viewer) {
      super(viewer);
      setText(Messages.MoveStagingView_expandAllAction_text);
      setToolTipText(Messages.MoveStagingView_expandAllAction_tooltip);
      setImageDescriptor(((ICommandImageService)getViewSite().getService(ICommandImageService.class)).getImageDescriptor("org.eclipse.ui.navigate.expandAll")); //$NON-NLS-1$
    }

    @Override
    public void run() {
      try {
        getViewer().getControl().setRedraw(false);
        getViewer().expandAll();
        getViewer().getTree().getColumn(0).pack();
      } finally {
        getViewer().getControl().setRedraw(true);
      }
    }
  }

  private abstract class TreeViewerAction extends Action {
    
    private final TreeViewer viewer;
    private TreeViewerAction(TreeViewer viewer) {
      this.viewer = viewer;
    }
    protected TreeViewer getViewer() {
      return viewer;
    }
  }
  
  private class CollapseAllAction extends TreeViewerAction {
    
    public CollapseAllAction(TreeViewer viewer) {
      super(viewer);
      setText(Messages.MoveStagingView_collapseAllAction_text);
      setToolTipText(Messages.MoveStagingView_collapseAllAction_tooltip);
      setImageDescriptor(((ICommandImageService)getViewSite().getService(ICommandImageService.class)).getImageDescriptor("org.eclipse.ui.navigate.collapseAll")); //$NON-NLS-1$
    }

    @Override
    public void run() {
      try {
        getViewer().getControl().setRedraw(false);
        getViewer().collapseAll();
        getViewer().getTree().getColumn(0).pack();
      } finally {
        getViewer().getControl().setRedraw(true);
      }
    }
  }
  
  private class AddRequiredAction extends AbstractAddRequiredAction {

    private AddRequiredAction() {
      super(Messages.MoveStagingView_addRequiredElementsLabel);
      final String ICONS_PATH = "icons/";
      setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), ICONS_PATH + "full/etool16/add_dependencies.gif")); //$NON-NLS-1$
      setToolTipText(Messages.MoveStagingView_addRequiredElementsLabel);
    }

    @Override
    public void run() {
        Collection<EObject> toAdd = new LinkedHashSet<EObject>();
        for (Iterator<?> it = getStructuredSelection().iterator(); it.hasNext();) {
          EObject next = (EObject) it.next();
          toAdd.addAll(stage.getBackreferences(next).values());
        }
        stage.addAll(toAdd);
    }

  }

  private class AddAllRequiredAction extends AbstractAddRequiredAction {

    protected AddAllRequiredAction() {
      super(Messages.MoveStagingView_addAllRequiredElementsLabel);
      final String ICONS_PATH = "icons/"; //$NON-NLS-1$
      setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), ICONS_PATH + "full/etool16/add_alldependencies.gif")); //$NON-NLS-1$
      setToolTipText(Messages.MoveStagingView_addAllRequiredElementsLabel);
    }

    public void run() {

      @SuppressWarnings("unchecked") 
      Collection<EObject> input = getStructuredSelection().toList();

      while (!input.isEmpty()) {
        Collection<EObject> backrefs = new LinkedHashSet<EObject>();
        for (EObject e : input) {
          backrefs.addAll(stage.getBackreferences(e).values());
        }
        stage.addAll(backrefs);
        input = backrefs;
      }
    }

  }

  private class NavigateStageAction extends TreeViewerAction {

    final boolean forward;

    NavigateStageAction(TreeViewer viewer, boolean forward){
      super(viewer);
      this.forward = forward;
    }


    @Override
    public void runWithEvent(Event event) {

      Predicate<EObject> matcher = (event.stateMask & SWT.CONTROL) == SWT.CONTROL ? 
          this::ctrlMatcher: this::defaultMatcher;

      ITreeContentProvider cp = (ITreeContentProvider) getViewer().getContentProvider(); 
      EObject selection = (EObject) ((IStructuredSelection)getViewer().getSelection()).getFirstElement();

      // If no element is selected, start at the bottom when navigating backwards, and at the top
      // when navigating forwards
      if (selection == null) {
        Object[] roots = cp.getElements(getViewer().getInput());
        if (forward) {
          selection = (EObject) roots[0];
        } else {
          selection = (EObject) roots[roots.length - 1];
          while (cp.hasChildren(selection)) {
            Object[] children = cp.getChildren(selection);
            selection = (EObject) children[children.length - 1];
          }
        }
      }

      EObject root = selection;

      EObject currentElement = matcher.test(root) ? root : null;
      EObject nextElement = null;

      while (nextElement == null && root != null) {

        // skip the subtree of the selection root when searching backwards
        if (forward) {
            nextElement = searchDown(root, currentElement, matcher);
        } else if (root != selection) {
            nextElement = searchUp(root, matcher);
        }

        if (nextElement == null) {
          EObject nextSubtree = forward ? getRightSibling(root) : getLeftSibling(root);
          if (nextSubtree == null) { 

            EObject parent = root.eContainer();

            // check the parent when searching backwards since it's not covered by any of the subtrees
            if (parent != null && !forward && matcher.test(parent)) {
              nextElement = parent;
            } else {
              while (parent != null && nextSubtree == null) {
                nextSubtree = forward ? getRightSibling(parent) : getLeftSibling(parent);
                parent = parent.eContainer();
              }
            }
          }
          root = nextSubtree;
        }
      }

      if (nextElement != null) {
        getViewer().setSelection(new StructuredSelection(nextElement));
        getViewer().reveal(nextElement);
      }
    }

    private EObject getRightSibling(EObject e) {
      Object[] siblings = getSiblings(e);
      for (int i = 0; i < siblings.length - 1; i++) {
        if (siblings[i] == e) {
          return (EObject) siblings[i+1];
        }
      }
      return null;
    }

    private EObject getLeftSibling(EObject e) {
      Object[] siblings = getSiblings(e);
      for (int i = 1; i < siblings.length; i++) {
        if (siblings[i] == e) {
            return (EObject) siblings[i-1];
        }
      }
      return null;
    }

    private Object[] getSiblings(Object e) {
      ITreeContentProvider tcp = (ITreeContentProvider) getViewer().getContentProvider();
      Object parent = tcp.getParent(e);
      Object[] siblings = null;
      if (parent == null) {
        siblings = tcp.getElements(getViewer().getInput());
      } else {
        siblings = tcp.getChildren(parent);
      }
      return siblings;
    }

    private EObject searchDown(EObject root, EObject current, Predicate<EObject> matcher) {
      Deque<EObject> toWalk = new ArrayDeque<>();
      toWalk.add(root);
      while (toWalk.size() > 0) {
        EObject next = toWalk.pop();
        if (next != current && matcher.test(next)) {
          return next;
        }
        Object[] children = ((ITreeContentProvider) getViewer().getContentProvider()).getChildren(next);
        for (int i = children.length - 1; i >= 0; i--) {
          toWalk.push((EObject) children[i]);
        }
      }
      return null;
    }

    private EObject searchUp(EObject root, Predicate<EObject> matcher) {
      Deque<EObject> postOrderRL = new ArrayDeque<>();
      Deque<EObject> toWalk = new ArrayDeque<>();
      toWalk.push(root);
      while (!toWalk.isEmpty()) {
        EObject next = toWalk.pop();
        postOrderRL.push(next);
        Object[] children = ((ITreeContentProvider) getViewer().getContentProvider()).getChildren(next);
        for (int i = children.length - 1; i >= 0; i--) {
          toWalk.push((EObject) children[i]);
        }
      }
      while (!postOrderRL.isEmpty()) {
        EObject next = postOrderRL.pop();
        if (matcher.test(next)) {
          return next;
        }
      }
      return null;
    }

    private boolean hasBackreferenceMatcher(EObject e) {
      return !stage.getBackreferences(e).isEmpty();
    }

    private boolean isStageElementMatcher(EObject e) {
      return stage.getElements().contains(e);
    }

    private boolean hasNoNewParentMatcher(EObject e) {
      return stage.getNewParent(e) == null;
    }

    private boolean ctrlMatcher(EObject e) {
      return hasBackreferenceMatcher(e) || (isStageElementMatcher(e) && hasNoNewParentMatcher(e));
    }

    private boolean defaultMatcher(EObject e) {
      return hasBackreferenceMatcher(e) || isStageElementMatcher(e);
    }

  }

  private class PreviousElementAction extends NavigateStageAction {
    public PreviousElementAction(TreeViewer viewer) {
      super(viewer, false);
      setText(Messages.MoveStagingView_previousElementAction_text);
      setToolTipText(Messages.MoveStagingView_previousElementAction_tooltip);
      setImageDescriptor(ToolkitPlugin.getDefault().getImageRegistry().getDescriptor(ToolkitPlugin.MOVE_UP_ITEM_IMAGE_ID));
    }
  }

  private class NextElementAction extends NavigateStageAction {
    public NextElementAction(TreeViewer viewer) {
      super(viewer, true);
      setText(Messages.MoveStagingView_nextElementAction_text);
      setToolTipText(Messages.MoveStagingView_nextElementAction_tooltip);
      setImageDescriptor(ToolkitPlugin.getDefault().getImageRegistry().getDescriptor(ToolkitPlugin.MOVE_DOWN_ITEM_IMAGE_ID));
    }
  }

  /*  This decorates image labels with error markers,
   *  adds the error count label decoration and applies font/color styles to the labels
   */
  class StageLabelDecorator extends CellLabelProvider implements IStyledLabelDecorator {

    private boolean showErrorCount;

    private Font boldFont;
    private Styler boldFontStyler;

    private Font italicFont;
    private Styler italicFontStyler;

    final Styler errorColorStyler = StyledString.createColorRegistryStyler(JFacePreferences.ERROR_COLOR, null);
    final Styler greenColorStyler = new Styler() {
      @Override
      public void applyStyles(TextStyle textStyle) {
        textStyle.foreground = FlexibilityColors.getColorRegistry().get(FlexibilityColors.FG_GREEN); 
      }
    };

    StageLabelDecorator(){
      showErrorCount = false;
    }
    
    StageLabelDecorator(boolean showErrorCount){
      this.showErrorCount = showErrorCount;
    }
    
    @Override
    public Image decorateImage(Image image, Object element) {

      Collection<Diagnostic> diagnostic = stage.getDiagnostics((EObject) element);

      for (Diagnostic diag : diagnostic) {
        if (diagnostic != null && diag.getSeverity() >= Diagnostic.WARNING)
        {
          return decorate(image, diag);
        }
      }
      return image;
    }

    public Image decorate(Image image, Diagnostic diagnostic) {
      List<Object> images = new ArrayList<Object>(2);
      images.add(image);
      images.add(EMFEditUIPlugin.INSTANCE.getImage(diagnostic.getSeverity() == Diagnostic.WARNING ? "full/ovr16/warning_ovr.gif" : "full/ovr16/error_ovr.gif")); //$NON-NLS-1$ //$NON-NLS-2$
      ComposedImage composedImage = new ComposedImage(images);
      return ExtendedImageRegistry.INSTANCE.getImage(composedImage);
    }

    @Override
    public String decorateText(String text, Object element) {
      return text;
    }

    protected void buildToolTipMessage(StringBuilder result, ILabelProvider labelProvider, Object object, Diagnostic diagnostic) {

      EObject target = (EObject) diagnostic.getData().get(1); // the referenced object
      EReference ref = (EReference) diagnostic.getData().get(2); // the reference

      result.append("<div>"); //$NON-NLS-1$
      String sourceText = DiagnosticDecorator.escapeContent(labelProvider.getText(object));
      URI sourceImage = ImageURIRegistry.INSTANCE.getImageURI(labelProvider.getImage(object));
      String targetText = DiagnosticDecorator.escapeContent(labelProvider.getText(target));
      URI targetImage = ImageURIRegistry.INSTANCE.getImageURI(labelProvider.getImage(target));

      result.append(sourceText);
      result.append(String.format(Messages.MoveStagingView_backrefTooltip, sourceImage, EcoreUtil.getURI(target), targetText, targetImage, DiagnosticDecorator.escapeContent(labelProvider.getText(ref))));
      result.append("</div>"); //$NON-NLS-1$
    }

    @Override
    public String getToolTipText(Object element) {
      Collection<Diagnostic> diags = stage.getDiagnostics((EObject) element);
      if (diags != null && diags.size() > 0) {
        Diagnostic d = diags.iterator().next();
        StringBuilder builder = new StringBuilder();
        buildToolTipMessage(builder, labelProvider, element, d);
        return builder.toString();
      }
      return null;
    }

    private int countChildrenBackreferences(EObject element) {
      int result = 0;
      for (Iterator<EObject> it = element.eAllContents(); it.hasNext();) {
        if (stage.getBackreferences(it.next()).size() > 0) {
          result++;
        }
      }
      return result;
    }

    @Override
    public StyledString decorateStyledText(StyledString styledString, Object element) {

      final Styler fontStyler = getFontStyler(element);
      Styler mainStyler = new Styler() {
        @Override
        public void applyStyles(TextStyle textStyle) {
          Styler fontStyler = getFontStyler(element);
          if (fontStyler != null) {
            fontStyler.applyStyles(textStyle);
          }
          Styler colorStyler = getColorStyler(element);
          if (colorStyler != null) {
            colorStyler.applyStyles(textStyle);
          }
        }
      };

      styledString.setStyle(0, styledString.length(), mainStyler);

      if (showErrorCount) {
        int backrefs = countChildrenBackreferences((EObject) element);
        if (backrefs > 0) {
          Styler errorCountStyler = new Styler() {
            @Override
            public void applyStyles(TextStyle textStyle) {
              if (fontStyler != null) {
                fontStyler.applyStyles(textStyle);
              }
              errorColorStyler.applyStyles(textStyle);
            }
          };
          styledString.append(" (" + String.valueOf(backrefs) + ")", errorCountStyler); //$NON-NLS-1$ //$NON-NLS-2$
        }
      }
      return styledString;
    }

    private Styler getColorStyler(Object object) {
      if (stage.hasBackreferences((EObject) object)) {
        return errorColorStyler;
      } else if (stage.getNewParent((EObject) object) != null) {
        return greenColorStyler;
      } 
      return null;
    }

    private StyledString.Styler getFontStyler(Object object) {
      if (stage.getElements().contains(object)) {
        if (boldFontStyler == null) {
          FontDescriptor descriptor = FontDescriptor.createFrom(JFaceResources.getDefaultFont()).setStyle(SWT.BOLD);
          boldFont = descriptor.createFont(JFaceResources.getDefaultFont().getDevice());
          boldFontStyler = new Styler() {
            @Override
            public void applyStyles(TextStyle textStyle) {
              textStyle.font = boldFont;
            }
          };
        }
        return boldFontStyler;
      } else if (object instanceof EObject && EcoreUtil.isAncestor(stage.getElements(), (EObject) object)) {
        return null;
      } else {
        if (italicFontStyler == null) {
          FontDescriptor descriptor = FontDescriptor.createFrom(JFaceResources.getDefaultFont()).setStyle(SWT.ITALIC);
          italicFont = descriptor.createFont(JFaceResources.getDefaultFont().getDevice());
          italicFontStyler = new Styler() {
            @Override
            public void applyStyles(TextStyle textStyle) {
              // if it is italic, it must be gray, there are no combinations so take a shortcut here..
              textStyle.font = italicFont;
              textStyle.foreground = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY);
            }
          };
        }
        return italicFontStyler;
      }

    }
    
    @Override
    public void update(ViewerCell cell) {
      /* not used */
    }
    
    public void dispose() {
      if (boldFont != null) {
        boldFont.dispose();
      }
      if (italicFont != null) {
        italicFont.dispose();
      }
    }

  }

  public void reset() {
    stageViewer.setInput(null);
    destinationViewer.setInput(null);
    if (stage != null) {
      if (listener != null) {
        stage.removeStageListener(listener);
      }
      stage.dispose();
      stage = null;
    }
    stageExpandAllAction.setEnabled(false);
    stageCollapseAllAction.setEnabled(false);
    destExpandAllAction.setEnabled(false);
    destCollapseAllAction.setEnabled(false);
    nextEleementAction.setEnabled(false);
    previousElementAction.setEnabled(false);
    if (session != null) {
      session.removeListener(this);
      session = null;
    }
  }

  @Override
  public void addSelectionChangedListener(ISelectionChangedListener listener) {
     stageViewer.addSelectionChangedListener(listener);
    destinationViewer.addSelectionChangedListener(listener);
  }

  @Override
  public ISelection getSelection() {
    return stageViewer.getControl().isFocusControl() ? stageViewer.getSelection() : destinationViewer.getSelection();
  }

  @Override
  public void removeSelectionChangedListener(ISelectionChangedListener listener) {
    stageViewer.removeSelectionChangedListener(listener);
    destinationViewer.removeSelectionChangedListener(listener);
  }

  @Override
  public void setSelection(ISelection selection) {
    stageViewer.setSelection(selection);
  }

  @Override
  public String getContributorId() {
    return "org.polarsys.capella.core.data.capellamodeller.properties"; //$NON-NLS-1$
  }

  @Override
  public Object getAdapter(Class adapter) {
    if (adapter == IPropertySheetPage.class) {
      return new TabbedPropertySheetPage(this);
    }
    return super.getAdapter(adapter);
  }

  @Override
  public void dispose() {
    labelProvider.dispose();
    if (toolkit != null) {
      toolkit.dispose();
    }
    if (stage != null) {
      stage.dispose();
    }
    if (tooltipSupport != null) {
      tooltipSupport.dispose();
    }
    if (session != null) {
      session.removeListener(this);
    }
  }

  private static class MyLocateInCapellaExplorerAction extends LocateInCapellaExplorerAction {
    @Override
    public void selectElementInCapellaExplorer(ISelection selection) {
      IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
      try {
        window.getActivePage().showView(CapellaCommonNavigator.ID, null, IWorkbenchPage.VIEW_ACTIVATE);
      } catch (PartInitException e) {
          Bundle bundle = FrameworkUtil.getBundle(this.getClass());
          Platform.getLog(bundle).error(e.getLocalizedMessage(), e);
      }
      super.selectElementInCapellaExplorer(selection);
    }
  }
  
  private static class MyDiagnosticDialog extends DiagnosticDialog {

    public MyDiagnosticDialog(Shell parent, Diagnostic diagnostic) {
      super(parent, Messages.ValidateExecuteListener_dialogTitle, Messages.ValidateExecuteListener_dialogMessage, diagnostic, Diagnostic.ERROR | Diagnostic.WARNING); 
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
      createDetailsButton(parent);
      createButton(parent, IDialogConstants.OK_ID, Messages.ValidateExecuteListener_dialogProceedButton, false);
      createButton(parent, IDialogConstants.CANCEL_ID, Messages.ValidateExecuteListener_dialogCancelButton, true);
    }

  }

  @Override
  public void notify(int changeKind) {
    if (changeKind == SessionListener.CLOSING) {
      reset();
    }
  }

}
