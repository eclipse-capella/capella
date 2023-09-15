/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.oa.presentation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashMap;
import java.util.HashMap;
import java.util.HashMap;
import java.util.HashMap;
import java.util.HashMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map;
import java.util.Map;
import java.util.Map;
import java.util.Map;
import java.util.Map;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.ui.MarkerHelper;
import org.eclipse.emf.common.ui.ViewerPane;
import org.eclipse.emf.common.ui.editor.ProblemEditorPart;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTreeEditor;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider;
import org.eclipse.emf.edit.ui.util.EditUIMarkerHelper;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.polarsys.capella.core.data.oa.provider.OaItemProviderAdapterFactory;
import org.polarsys.capella.core.data.oa.provider.OaItemProviderAdapterFactory;
import org.polarsys.capella.core.data.oa.provider.OaItemProviderAdapterFactory;
import org.polarsys.capella.core.data.oa.provider.OaItemProviderAdapterFactory;
import org.polarsys.capella.core.data.oa.provider.OaItemProviderAdapterFactory;
import org.polarsys.capella.core.data.oa.provider.OaItemProviderAdapterFactory;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheet;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.data.activity.provider.ActivityItemProviderAdapterFactory;
import org.polarsys.capella.common.data.behavior.provider.BehaviorItemProviderAdapterFactory;
import org.polarsys.capella.common.data.modellingcore.provider.ModellingcoreItemProviderAdapterFactory;
import org.polarsys.capella.core.data.capellacommon.provider.CapellacommonItemProviderAdapterFactory;
import org.polarsys.capella.core.data.capellacore.provider.CapellacoreItemProviderAdapterFactory;
import org.polarsys.capella.core.data.capellamodeller.presentation.CapellaModellerEditorPlugin;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellamodellerItemProviderAdapterFactory;
import org.polarsys.capella.core.data.cs.provider.CsItemProviderAdapterFactory;
import org.polarsys.capella.core.data.ctx.provider.CtxItemProviderAdapterFactory;
import org.polarsys.capella.core.data.epbs.provider.EpbsItemProviderAdapterFactory;
import org.polarsys.capella.core.data.fa.provider.FaItemProviderAdapterFactory;
import org.polarsys.capella.core.data.information.communication.provider.CommunicationItemProviderAdapterFactory;
import org.polarsys.capella.core.data.information.datatype.provider.DatatypeItemProviderAdapterFactory;
import org.polarsys.capella.core.data.information.datavalue.provider.DatavalueItemProviderAdapterFactory;
import org.polarsys.capella.core.data.information.provider.InformationItemProviderAdapterFactory;
import org.polarsys.capella.core.data.interaction.provider.InteractionItemProviderAdapterFactory;
import org.polarsys.capella.core.data.la.provider.LaItemProviderAdapterFactory;
import org.polarsys.capella.core.data.oa.provider.OaItemProviderAdapterFactory;
import org.polarsys.capella.core.data.pa.deployment.provider.DeploymentItemProviderAdapterFactory;
import org.polarsys.capella.core.data.pa.provider.PaItemProviderAdapterFactory;
import org.polarsys.capella.core.data.sharedmodel.provider.SharedmodelItemProviderAdapterFactory;
import org.polarsys.kitalpha.ad.metadata.helpers.MetadataHelper;
import org.polarsys.kitalpha.emde.extension.ExtendedModel;
import org.polarsys.kitalpha.emde.extension.ExtensibleModel;
import org.polarsys.kitalpha.emde.extension.ModelExtensionDescriptor;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.extension.ModelExtensionListener;
import org.polarsys.kitalpha.emde.extension.ModelExtensionManager;
import org.polarsys.kitalpha.emde.model.edit.provider.EmdeItemProviderAdapterFactory;
import org.polarsys.kitalpha.emde.ui.actions.EmdeViewerFilterAction;


/**
 * This is an example of a Oa model editor.
 * <!-- begin-user-doc -->
 * @implements ModelExtensionListener 
 * <!-- end-user-doc -->
 *
 * <!-- begin-capella-code -->
 * @implements ITabbedPropertySheetPageContributor
 * <!-- end-capella-code -->
 *
 * @generated
 */
public class OaEditor
	extends MultiPageEditorPart
	implements ITabbedPropertySheetPageContributor, IEditingDomainProvider, ISelectionProvider, IMenuListener, IViewerProvider, IGotoMarker, ModelExtensionListener {
	/**
   * This keeps track of the editing domain that is used to track all changes to the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected AdapterFactoryEditingDomain editingDomain;

	/**
   * This is the one adapter factory used for providing views of the model.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ComposedAdapterFactory adapterFactory;

	/**
   * This is the content outline page.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IContentOutlinePage contentOutlinePage;

	/**
   * This is a kludge...
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IStatusLineManager contentOutlineStatusLineManager;

	/**
   * This is the content outline page's viewer.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected TreeViewer contentOutlineViewer;

	/**
   * This is the property sheet page.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	// begin-capella-code
	//protected List<PropertySheetPage> propertySheetPages = new ArrayList<PropertySheetPage>();
	protected List<PropertySheetPage> propertySheetPages = new ArrayList<PropertySheetPage>();

	// end-capella-code

	/**
   * This is the viewer that shadows the selection in the content outline.
   * The parent relation must be correctly defined for this to work.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected TreeViewer selectionViewer;

	/**
   * This inverts the roll of parent and child in the content provider and show parents as a tree.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected TreeViewer parentViewer;

	/**
   * This shows how a tree view works.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected TreeViewer treeViewer;

	/**
   * This shows how a list view works.
   * A list viewer doesn't support icons.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ListViewer listViewer;

	/**
   * This shows how a table view works.
   * A table can be used as a list with icons.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected TableViewer tableViewer;

	/**
   * This shows how a tree view with columns works.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected TreeViewer treeViewerWithColumns;

	/**
   * This keeps track of the active viewer pane, in the book.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ViewerPane currentViewerPane;

	/**
   * This keeps track of the active content viewer, which may be either one of the viewers in the pages or the content outline viewer.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected Viewer currentViewer;
	
	/**
   * Viewers collection of this editor
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected Collection<Viewer> viewers = new ArrayList<Viewer>();

	/**
   * This Map contain {@link org.polarsys.kitalpha.emde.ui.actions.EmdeViewerFilterAction} all the applicable
   * {@link org.polarsys.kitalpha.emde.ui.actions.EmdeViewerFilterAction} per loaded Resource.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected Map<Resource, Collection<EmdeViewerFilterAction>> viewerFilterActions = new HashMap<Resource, Collection<EmdeViewerFilterAction>>();	

	/**
   * This listens to which ever viewer is active.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ISelectionChangedListener selectionChangedListener;

	/**
   * This keeps track of all the {@link org.eclipse.jface.viewers.ISelectionChangedListener}s that are listening to this editor.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected Collection<ISelectionChangedListener> selectionChangedListeners = new ArrayList<ISelectionChangedListener>();

	/**
   * This keeps track of the selection of the editor as a whole.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ISelection editorSelection = StructuredSelection.EMPTY;

	/**
   * The MarkerHelper is responsible for creating workspace resource markers presented
   * in Eclipse's Problems View.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected MarkerHelper markerHelper = new EditUIMarkerHelper();

	/**
   * This listens for when the outline becomes active
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IPartListener partListener =
		new IPartListener() {
      @Override
      public void partActivated(IWorkbenchPart p) {
        if (p instanceof ContentOutline) {
          if (((ContentOutline)p).getCurrentPage() == contentOutlinePage) {
            getActionBarContributor().setActiveEditor(OaEditor.this);

            setCurrentViewer(contentOutlineViewer);
          }
        }
        else if (p instanceof PropertySheet) {
          if (propertySheetPages.contains(((PropertySheet)p).getCurrentPage())) {
            getActionBarContributor().setActiveEditor(OaEditor.this);
            handleActivate();
          }
        }
        else if (p == OaEditor.this) {
          handleActivate();
        }
      }
      @Override
      public void partBroughtToTop(IWorkbenchPart p) {
        // Ignore.
      }
      @Override
      public void partClosed(IWorkbenchPart p) {
        // Ignore.
      }
      @Override
      public void partDeactivated(IWorkbenchPart p) {
        // Ignore.
      }
      @Override
      public void partOpened(IWorkbenchPart p) {
        // Ignore.
      }
    };

	/**
   * Resources that have been removed since last activation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected Collection<Resource> removedResources = new ArrayList<Resource>();

	/**
   * Resources that have been changed since last activation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected Collection<Resource> changedResources = new ArrayList<Resource>();

	/**
   * Resources that have been saved.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected Collection<Resource> savedResources = new ArrayList<Resource>();

	/**
   * Map to store the diagnostic associated with a resource.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected Map<Resource, Diagnostic> resourceToDiagnosticMap = new LinkedHashMap<Resource, Diagnostic>();

	/**
   * Controls whether the problem indication should be updated.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected boolean updateProblemIndication = true;

	/**
   * Adapter used to update the problem indication when resources are demanded loaded.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected EContentAdapter problemIndicationAdapter = 
		new EContentAdapter() {
      protected boolean dispatching;

      @Override
      public void notifyChanged(final Notification notification) {
        if (notification.getNotifier() instanceof Resource) {
          switch (notification.getFeatureID(Resource.class)) {
            case Resource.RESOURCE__IS_LOADED:						
              getSite().getShell().getDisplay().asyncExec
                (new Runnable() {
                  public void run() {
                    getEmdeViewerFilterActions((Resource) notification.getNotifier());
                  }
                });														
            case Resource.RESOURCE__ERRORS:
            case Resource.RESOURCE__WARNINGS: {
              Resource resource = (Resource)notification.getNotifier();
              Diagnostic diagnostic = analyzeResourceProblems(resource, null);
              if (diagnostic.getSeverity() != Diagnostic.OK) {
                resourceToDiagnosticMap.put(resource, diagnostic);
              }
              else {
                resourceToDiagnosticMap.remove(resource);
              }
              dispatchUpdateProblemIndication();
              break;
            }
          }
        }
        else {
          super.notifyChanged(notification);
        }
      }

      protected void dispatchUpdateProblemIndication() {
        if (updateProblemIndication && !dispatching) {
          dispatching = true;
          getSite().getShell().getDisplay().asyncExec
            (new Runnable() {
               @Override
               public void run() {
                 dispatching = false;
                 updateProblemIndication();
               }
             });
        }
      }

      @Override
      protected void setTarget(Resource target) {
        basicSetTarget(target);
      }

      @Override
      protected void unsetTarget(Resource target) {
        basicUnsetTarget(target);
        resourceToDiagnosticMap.remove(target);
        dispatchUpdateProblemIndication();
      }
    };

	/**
   * This listens for workspace changes.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IResourceChangeListener resourceChangeListener =
		new IResourceChangeListener() {
      @Override
      public void resourceChanged(IResourceChangeEvent event) {
        IResourceDelta delta = event.getDelta();
        try {
          class ResourceDeltaVisitor implements IResourceDeltaVisitor {
            protected ResourceSet resourceSet = editingDomain.getResourceSet();
            protected Collection<Resource> changedResources = new ArrayList<Resource>();
            protected Collection<Resource> removedResources = new ArrayList<Resource>();

            @Override
            public boolean visit(IResourceDelta delta) {
              if (delta.getResource().getType() == IResource.FILE) {
                if (delta.getKind() == IResourceDelta.REMOVED ||
                    delta.getKind() == IResourceDelta.CHANGED && delta.getFlags() != IResourceDelta.MARKERS) {
                  Resource resource = resourceSet.getResource(URI.createPlatformResourceURI(delta.getFullPath().toString(), true), false);
                  if (resource != null) {
                    if (delta.getKind() == IResourceDelta.REMOVED) {
                      removedResources.add(resource);
                    }
                    else if (!savedResources.remove(resource)) {
                      changedResources.add(resource);
                    }
                  }
                }
                return false;
              }

              return true;
            }

            public Collection<Resource> getChangedResources() {
              return changedResources;
            }

            public Collection<Resource> getRemovedResources() {
              return removedResources;
            }
          }

          final ResourceDeltaVisitor visitor = new ResourceDeltaVisitor();
          delta.accept(visitor);

          if (!visitor.getRemovedResources().isEmpty()) {
            getSite().getShell().getDisplay().asyncExec
              (new Runnable() {
                 @Override
                 public void run() {
                   removedResources.addAll(visitor.getRemovedResources());
                   if (!isDirty()) {
                     getSite().getPage().closeEditor(OaEditor.this, false);
                   }
                 }
               });
          }

          if (!visitor.getChangedResources().isEmpty()) {
            getSite().getShell().getDisplay().asyncExec
              (new Runnable() {
                 @Override
                 public void run() {
                   changedResources.addAll(visitor.getChangedResources());
                   if (getSite().getPage().getActiveEditor() == OaEditor.this) {
                     handleActivate();
                   }
                 }
               });
          }
        }
        catch (CoreException exception) {
          CapellaModellerEditorPlugin.INSTANCE.log(exception);
        }
      }
    };

	/**
   * Handles activation of the editor or it's associated views.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void handleActivate() {
    // Recompute the read only state.
    //
    if (editingDomain.getResourceToReadOnlyMap() != null) {
      editingDomain.getResourceToReadOnlyMap().clear();

      // Refresh any actions that may become enabled or disabled.
      //
      setSelection(getSelection());
    }

    if (!removedResources.isEmpty()) {
      if (handleDirtyConflict()) {
        getSite().getPage().closeEditor(OaEditor.this, false);
      }
      else {
        removedResources.clear();
        changedResources.clear();
        savedResources.clear();
      }
    }
    else if (!changedResources.isEmpty()) {
      changedResources.removeAll(savedResources);
      handleChangedResources();
      changedResources.clear();
      savedResources.clear();
    }
  }

	/**
   * Handles what to do with changed resources on activation.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void handleChangedResources() {
    if (!changedResources.isEmpty() && (!isDirty() || handleDirtyConflict())) {
      ResourceSet resourceSet = editingDomain.getResourceSet();
      if (isDirty()) {
        changedResources.addAll(resourceSet.getResources());
      }
      editingDomain.getCommandStack().flush();

      updateProblemIndication = false;
      for (Resource resource : changedResources) {
        if (resource.isLoaded()) {
          resource.unload();
          try {
            resource.load(resourceSet.getLoadOptions());
          }
          catch (IOException exception) {
            if (!resourceToDiagnosticMap.containsKey(resource)) {
              resourceToDiagnosticMap.put(resource, analyzeResourceProblems(resource, exception));
            }
          }
        }
      }

      if (AdapterFactoryEditingDomain.isStale(editorSelection)) {
        setSelection(StructuredSelection.EMPTY);
      }

      updateProblemIndication = true;
      updateProblemIndication();
    }
  }
  
	/**
   * Updates the problems indication with the information described in the specified diagnostic.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void updateProblemIndication() {
    if (updateProblemIndication) {
      BasicDiagnostic diagnostic =
        new BasicDiagnostic
          (Diagnostic.OK,
           "org.polarsys.capella.core.data.gen.editor", //$NON-NLS-1$
           0,
           null,
           new Object [] { editingDomain.getResourceSet() });
      for (Diagnostic childDiagnostic : resourceToDiagnosticMap.values()) {
        if (childDiagnostic.getSeverity() != Diagnostic.OK) {
          diagnostic.add(childDiagnostic);
        }
      }

      int lastEditorPage = getPageCount() - 1;
      if (lastEditorPage >= 0 && getEditor(lastEditorPage) instanceof ProblemEditorPart) {
        ((ProblemEditorPart)getEditor(lastEditorPage)).setDiagnostic(diagnostic);
        if (diagnostic.getSeverity() != Diagnostic.OK) {
          setActivePage(lastEditorPage);
        }
      }
      else if (diagnostic.getSeverity() != Diagnostic.OK) {
        ProblemEditorPart problemEditorPart = new ProblemEditorPart();
        problemEditorPart.setDiagnostic(diagnostic);
        problemEditorPart.setMarkerHelper(markerHelper);
        try {
          addPage(++lastEditorPage, problemEditorPart, getEditorInput());
          setPageText(lastEditorPage, problemEditorPart.getPartName());
          setActivePage(lastEditorPage);
          showTabs();
        }
        catch (PartInitException exception) {
          CapellaModellerEditorPlugin.INSTANCE.log(exception);
        }
      }

      if (markerHelper.hasMarkers(editingDomain.getResourceSet())) {
        try {
          markerHelper.updateMarkers(diagnostic);
        }
        catch (CoreException exception) {
          CapellaModellerEditorPlugin.INSTANCE.log(exception);
        }
      }
    }
  }

	/**
   * Shows a dialog that asks if conflicting changes should be discarded.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected boolean handleDirtyConflict() {
    return
      MessageDialog.openQuestion
        (getSite().getShell(),
         getString("_UI_FileConflict_label"), //$NON-NLS-1$
         getString("_WARN_FileConflict")); //$NON-NLS-1$
  }

	/**
   * This creates a model editor.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OaEditor() {
    super();
    initializeEditingDomain();
  }

	/**
   * This sets up the editing domain for the model editor.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void initializeEditingDomain() {
    // Create an adapter factory that yields item providers.
    //
    adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

    adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());

    adapterFactory.addAdapterFactory(new CapellamodellerItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new CapellacoreItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new OaItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new CtxItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new LaItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new PaItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new DeploymentItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new EpbsItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new SharedmodelItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new CapellacommonItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new InformationItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new CommunicationItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new DatatypeItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new DatavalueItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new CsItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new FaItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new InteractionItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new ModellingcoreItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new EmdeItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new ActivityItemProviderAdapterFactory());
    adapterFactory.addAdapterFactory(new BehaviorItemProviderAdapterFactory());
    for (AdapterFactory extendedAdapterFactory : ModelExtensionDescriptor.INSTANCE.getExtendedModelAdapterFactories(OaItemProviderAdapterFactory.class.getName())) {
      adapterFactory.addAdapterFactory(extendedAdapterFactory);
    }
    adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

    adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

    // Create the command stack that will notify this editor as commands are executed.
    //
    BasicCommandStack commandStack = new BasicCommandStack();

    // Add a listener to set the most recent command's affected objects to be the selection of the viewer with focus.
    //
    commandStack.addCommandStackListener
      (new CommandStackListener() {
         @Override
         public void commandStackChanged(final EventObject event) {
           getContainer().getDisplay().asyncExec
             (new Runnable() {
                @Override
                public void run() {
                  firePropertyChange(IEditorPart.PROP_DIRTY);

                  // Try to select the affected objects.
                  //
                  Command mostRecentCommand = ((CommandStack)event.getSource()).getMostRecentCommand();
                  if (mostRecentCommand != null) {
                    setSelectionToViewer(mostRecentCommand.getAffectedObjects());
                  }
                  for (Iterator<PropertySheetPage> i = propertySheetPages.iterator(); i.hasNext(); ) {
                    PropertySheetPage propertySheetPage = i.next();
                    if (propertySheetPage.getControl() == null || propertySheetPage.getControl().isDisposed()) {
                      i.remove();
                    }
                    else {
                      propertySheetPage.refresh();
                    }
                  }
                }
              });
         }
       });

    // Create the editing domain with a special command stack.
    //
    editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, new HashMap<Resource, Boolean>());
    // Register this editor for ExtendedModel state
    //
    ModelExtensionHelper.getInstance(getEditingDomain().getResourceSet()).addListener(this);		
  }

	/**
   * This is here for the listener to be able to call it.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
			@Override
	protected void firePropertyChange(int action) {
    super.firePropertyChange(action);
  }

	/**
   * This sets the selection into whichever viewer is active.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setSelectionToViewer(Collection<?> collection) {
    final Collection<?> theSelection = collection;
    // Make sure it's okay.
    //
    if (theSelection != null && !theSelection.isEmpty()) {
      Runnable runnable =
        new Runnable() {
          @Override
          public void run() {
            // Try to select the items in the current content viewer of the editor.
            //
            if (currentViewer != null) {
              currentViewer.setSelection(new StructuredSelection(theSelection.toArray()), true);
            }
          }
        };
      getSite().getShell().getDisplay().asyncExec(runnable);
    }
  }

	/**
   * This returns the editing domain as required by the {@link IEditingDomainProvider} interface.
   * This is important for implementing the static methods of {@link AdapterFactoryEditingDomain}
   * and for supporting {@link org.eclipse.emf.edit.ui.action.CommandAction}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public EditingDomain getEditingDomain() {
    return editingDomain;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public class ReverseAdapterFactoryContentProvider extends AdapterFactoryContentProvider {
		/**
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		public ReverseAdapterFactoryContentProvider(AdapterFactory adapterFactory) {
      super(adapterFactory);
    }

		/**
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		@Override
		public Object [] getElements(Object object) {
      Object parent = super.getParent(object);
      return (parent == null ? Collections.EMPTY_SET : Collections.singleton(parent)).toArray();
    }

		/**
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		@Override
		public Object [] getChildren(Object object) {
      Object parent = super.getParent(object);
      return (parent == null ? Collections.EMPTY_SET : Collections.singleton(parent)).toArray();
    }

		/**
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		@Override
		public boolean hasChildren(Object object) {
      Object parent = super.getParent(object);
      return parent != null;
    }

		/**
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */
		@Override
		public Object getParent(Object object) {
      return null;
    }
	}

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setCurrentViewerPane(ViewerPane viewerPane) {
    if (currentViewerPane != viewerPane) {
      if (currentViewerPane != null) {
        currentViewerPane.showFocus(false);
      }
      currentViewerPane = viewerPane;
    }
    setCurrentViewer(currentViewerPane.getViewer());
  }

	/**
   * This makes sure that one content viewer, either for the current page or the outline view, if it has focus,
   * is the current one.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setCurrentViewer(Viewer viewer) {
    // If it is changing...
    //
    if (currentViewer != viewer) {
      if (selectionChangedListener == null) {
        // Create the listener on demand.
        //
        selectionChangedListener =
          new ISelectionChangedListener() {
            // This just notifies those things that are affected by the section.
            //
            @Override
            public void selectionChanged(SelectionChangedEvent selectionChangedEvent) {
              setSelection(selectionChangedEvent.getSelection());
            }
          };
      }

      // Stop listening to the old one.
      //
      if (currentViewer != null) {
        currentViewer.removeSelectionChangedListener(selectionChangedListener);
      }

      // Start listening to the new one.
      //
      if (viewer != null) {
        viewer.addSelectionChangedListener(selectionChangedListener);
      }

      // Remember it.
      //
      currentViewer = viewer;

      // Set the editors selection based on the current viewer's selection.
      //
      setSelection(currentViewer == null ? StructuredSelection.EMPTY : currentViewer.getSelection());
    }
  }

	/**
   * This returns the viewer as required by the {@link IViewerProvider} interface.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Viewer getViewer() {
    return currentViewer;
  }
	
	/**
   * This returns viewers supported in this editor
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Collection<Viewer> getViewers() {
    return viewers;
  }	

	/**
	 * @generated
	 */
	public void modelEnabled(String nsURI) {
    for (Collection<EmdeViewerFilterAction> actionList : viewerFilterActions.values()) {
      for (EmdeViewerFilterAction action : actionList) {
        if (action.getExtendedModel().getName().equals(nsURI))
          action.setChecked(true);
      }
    }

  }

	/**
	 * @generated
	 */
	public void modelDisabled(String nsURI) {
    for (Collection<EmdeViewerFilterAction> actionList : viewerFilterActions.values()) {
      for (EmdeViewerFilterAction action : actionList) {
        if (action.getExtendedModel().getName().equals(nsURI))
          action.setChecked(false);
      }
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected Collection<EmdeViewerFilterAction> getEmdeViewerFilterActions(Resource resource_p) {
    if (resource_p == null || resource_p.getContents().isEmpty()) {
      return null;
    }
    // Cached extension actions		
    if (viewerFilterActions.get(resource_p) != null) {
      return viewerFilterActions.get(resource_p);
    }	
    // Create new extension actions
    Collection<EmdeViewerFilterAction> extensionActions = new ArrayList<EmdeViewerFilterAction>();		
    String extensibleModelURI = resource_p.getContents().get(0).eClass().getEPackage().getNsURI();
    ModelExtensionManager helper = ModelExtensionHelper.getInstance(resource_p);
    ExtensibleModel extensibleModel = ModelExtensionDescriptor.INSTANCE.getExtensibleModel(extensibleModelURI);
    if (extensibleModel != null) {		
      for (ExtendedModel extendedModel : extensibleModel.getAllExtendedModels()) {
        EmdeViewerFilterAction filterAction = new EmdeViewerFilterAction(resource_p, extensibleModel, extendedModel) {
          @Override
          public void run() {
            ISelection selection = getSelection();
            if (selection instanceof StructuredSelection) {
              if (selection.isEmpty() == false) {
                setSelectionToViewer(((StructuredSelection) getSelection()).toList());
              } else {        
                if (getResource() != null) {
                  if (getResource().getContents().isEmpty()) {
                    setSelectionToViewer((new StructuredSelection(getResource())).toList());
                  } else {
                    setSelectionToViewer((new StructuredSelection(getResource().getContents().get(0))).toList());
                  }
                }
              }
            }
          }
        };			
        filterAction.setChecked(!helper.isExtensionModelDisabled(extendedModel));		
        filterAction.setViewers(getViewers());	
        filterAction.setEnabled(helper.canDisableExtensionModel(extendedModel));		
        filterAction.addPropertyChangeListener((IPropertyChangeListener) getActionBarContributor());				
        extensionActions.add(filterAction);
      }
    }
    viewerFilterActions.put(resource_p, extensionActions);		
    return extensionActions;
  }	  	

	/**
   * This creates a context menu for the viewer and adds a listener as well registering the menu for extension.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void createContextMenuFor(StructuredViewer viewer) {
    MenuManager contextMenu = new MenuManager("#PopUp"); //$NON-NLS-1$
    contextMenu.add(new Separator("additions")); //$NON-NLS-1$
    contextMenu.setRemoveAllWhenShown(true);
    contextMenu.addMenuListener(this);
    Menu menu= contextMenu.createContextMenu(viewer.getControl());
    viewer.getControl().setMenu(menu);
    getSite().registerContextMenu(contextMenu, new UnwrappingSelectionProvider(viewer));

    int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
    Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance(), LocalSelectionTransfer.getTransfer(), FileTransfer.getInstance() };
    viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
    viewer.addDropSupport(dndOperations, transfers, new EditingDomainViewerDropAdapter(editingDomain, viewer));
  }

	/**
   * This is the method called to load a resource into the editing domain's resource set based on the editor's input.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void createModel() {
    URI resourceURI = EditUIUtil.getURI(getEditorInput(), editingDomain.getResourceSet().getURIConverter());
    Exception exception = null;
    Resource resource = null;
    try {
      // Load the resource through the editing domain.
      //
      resource = editingDomain.getResourceSet().getResource(resourceURI, true);
    }
    catch (Exception e) {
      exception = e;
      resource = editingDomain.getResourceSet().getResource(resourceURI, false);
    }

    Diagnostic diagnostic = analyzeResourceProblems(resource, exception);
    if (diagnostic.getSeverity() != Diagnostic.OK) {
      resourceToDiagnosticMap.put(resource,  analyzeResourceProblems(resource, exception));
    }
    editingDomain.getResourceSet().eAdapters().add(problemIndicationAdapter);
    MetadataHelper.getViewpointMetadata(editingDomain.getResourceSet()).initMetadataStorage();
  }

	/**
   * Returns a diagnostic describing the errors and warnings listed in the resource
   * and the specified exception (if any).
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Diagnostic analyzeResourceProblems(Resource resource, Exception exception) {
    boolean hasErrors = !resource.getErrors().isEmpty();
    if (hasErrors || !resource.getWarnings().isEmpty()) {
      BasicDiagnostic basicDiagnostic =
        new BasicDiagnostic
          (hasErrors ? Diagnostic.ERROR : Diagnostic.WARNING,
           "org.polarsys.capella.core.data.gen.editor", //$NON-NLS-1$
           0,
           getString("_UI_CreateModelError_message", resource.getURI()), //$NON-NLS-1$
           new Object [] { exception == null ? (Object)resource : exception });
      basicDiagnostic.merge(EcoreUtil.computeDiagnostic(resource, true));
      return basicDiagnostic;
    }
    else if (exception != null) {
      return
        new BasicDiagnostic
          (Diagnostic.ERROR,
           "org.polarsys.capella.core.data.gen.editor", //$NON-NLS-1$
           0,
           getString("_UI_CreateModelError_message", resource.getURI()), //$NON-NLS-1$
           new Object[] { exception });
    }
    else {
      return Diagnostic.OK_INSTANCE;
    }
  }

	/**
   * This is the method used by the framework to install your own controls.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void createPages() {
    // Creates the model from the editor input
    //
    createModel();

    // Only creates the other pages if there is something that can be edited
    //
    if (!getEditingDomain().getResourceSet().getResources().isEmpty()) {
      // Create a page for the selection tree view.
      //
      {
        ViewerPane viewerPane =
          new ViewerPane(getSite().getPage(), OaEditor.this) {
            @Override
            public Viewer createViewer(Composite composite) {
              Tree tree = new Tree(composite, SWT.MULTI);
                            // begin-extension-code
                            TreeViewer newTreeViewer = new TreeViewer(tree) {
                                @Override
                                public void refresh() {
                                    super.refresh();
                  for (PropertySheetPage propertySheetPage : propertySheetPages) {
                    propertySheetPage.refresh();
                  }
                                }
                            };
                            return newTreeViewer;
                            // end-extension-code
            }
            @Override
            public void requestActivation() {
              super.requestActivation();
              setCurrentViewerPane(this);
            }
          };
        viewerPane.createControl(getContainer());

        selectionViewer = (TreeViewer)viewerPane.getViewer();
                // begin-extension-code
                viewers.add(selectionViewer);
                // end-extension-code
        selectionViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
        selectionViewer.setUseHashlookup(true);

        selectionViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
        selectionViewer.setInput(editingDomain.getResourceSet());
        selectionViewer.setSelection(new StructuredSelection(editingDomain.getResourceSet().getResources().get(0)), true);
        viewerPane.setTitle(editingDomain.getResourceSet());

        new AdapterFactoryTreeEditor(selectionViewer.getTree(), adapterFactory);

        createContextMenuFor(selectionViewer);
        int pageIndex = addPage(viewerPane.getControl());
        setPageText(pageIndex, getString("_UI_SelectionPage_label")); //$NON-NLS-1$
      }

      // Create a page for the parent tree view.
      //
      {
        ViewerPane viewerPane =
          new ViewerPane(getSite().getPage(), OaEditor.this) {
            @Override
            public Viewer createViewer(Composite composite) {
              Tree tree = new Tree(composite, SWT.MULTI);
                            // begin-extension-code
                            TreeViewer newTreeViewer = new TreeViewer(tree) {
                                @Override
                                public void refresh() {
                                    super.refresh();
                  for (PropertySheetPage propertySheetPage : propertySheetPages) {
                    if (!propertySheetPage.getControl().isDisposed())
                      propertySheetPage.refresh();
                  }
                                }
                            };
                            return newTreeViewer;
                            // end-extension-code
            }
            @Override
            public void requestActivation() {
              super.requestActivation();
              setCurrentViewerPane(this);
            }
          };
        viewerPane.createControl(getContainer());

        parentViewer = (TreeViewer)viewerPane.getViewer();
                // begin-extension-code
                viewers.add(parentViewer);
                // end-extension-code
        parentViewer.setAutoExpandLevel(30);
        parentViewer.setContentProvider(new ReverseAdapterFactoryContentProvider(adapterFactory));
        parentViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

        createContextMenuFor(parentViewer);
        int pageIndex = addPage(viewerPane.getControl());
        setPageText(pageIndex, getString("_UI_ParentPage_label")); //$NON-NLS-1$
      }

      // This is the page for the list viewer
      //
      {
        ViewerPane viewerPane =
          new ViewerPane(getSite().getPage(), OaEditor.this) {
            @Override
            public Viewer createViewer(Composite composite) {
                            // begin-extension-code
                            ListViewer newListViewer = new ListViewer(composite) {
                                @Override
                                public void refresh() {
                                    super.refresh();
                  for (PropertySheetPage propertySheetPage : propertySheetPages) {
                    if (!propertySheetPage.getControl().isDisposed())
                      propertySheetPage.refresh();
                  }
                                }
                            };
                            return newListViewer;
                            // end-extension-code							
            }
            @Override
            public void requestActivation() {
              super.requestActivation();
              setCurrentViewerPane(this);
            }
          };
        viewerPane.createControl(getContainer());
        listViewer = (ListViewer)viewerPane.getViewer();
                // begin-extension-code
                viewers.add(listViewer);
                // end-extension-code
        listViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
        listViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

        createContextMenuFor(listViewer);
        int pageIndex = addPage(viewerPane.getControl());
        setPageText(pageIndex, getString("_UI_ListPage_label")); //$NON-NLS-1$
      }

      // This is the page for the tree viewer
      //
      {
        ViewerPane viewerPane =
          new ViewerPane(getSite().getPage(), OaEditor.this) {
            @Override
            public Viewer createViewer(Composite composite) {
                            // begin-extension-code
                            TreeViewer newTreeViewer = new TreeViewer(composite) {
                                @Override
                                public void refresh() {
                                    super.refresh();
                  for (PropertySheetPage propertySheetPage : propertySheetPages) {
                    if (!propertySheetPage.getControl().isDisposed())
                      propertySheetPage.refresh();
                  }
                                }
                            };
                            return newTreeViewer;
                            // end-extension-code
            }
            @Override
            public void requestActivation() {
              super.requestActivation();
              setCurrentViewerPane(this);
            }
          };
        viewerPane.createControl(getContainer());
        treeViewer = (TreeViewer)viewerPane.getViewer();
                // begin-extension-code
                viewers.add(treeViewer);
                // end-extension-code
        treeViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
        treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

        new AdapterFactoryTreeEditor(treeViewer.getTree(), adapterFactory);

        createContextMenuFor(treeViewer);
        int pageIndex = addPage(viewerPane.getControl());
        setPageText(pageIndex, getString("_UI_TreePage_label")); //$NON-NLS-1$
      }

      // This is the page for the table viewer.
      //
      {
        ViewerPane viewerPane =
          new ViewerPane(getSite().getPage(), OaEditor.this) {
            @Override
            public Viewer createViewer(Composite composite) {
                            // begin-extension-code
                            TableViewer newTableViewer = new TableViewer(composite) {
                                @Override
                                public void refresh() {
                                    super.refresh();
                  for (PropertySheetPage propertySheetPage : propertySheetPages) {
                    if (!propertySheetPage.getControl().isDisposed())
                      propertySheetPage.refresh();
                  }
                                }
                            };
                            return newTableViewer;
                            // end-extension-code
            }
            @Override
            public void requestActivation() {
              super.requestActivation();
              setCurrentViewerPane(this);
            }
          };
        viewerPane.createControl(getContainer());
        tableViewer = (TableViewer)viewerPane.getViewer();
                // begin-extension-code
                viewers.add(tableViewer);
                // end-extension-code

        Table table = tableViewer.getTable();
        TableLayout layout = new TableLayout();
        table.setLayout(layout);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn objectColumn = new TableColumn(table, SWT.NONE);
        layout.addColumnData(new ColumnWeightData(3, 100, true));
        objectColumn.setText(getString("_UI_ObjectColumn_label")); //$NON-NLS-1$
        objectColumn.setResizable(true);

        TableColumn selfColumn = new TableColumn(table, SWT.NONE);
        layout.addColumnData(new ColumnWeightData(2, 100, true));
        selfColumn.setText(getString("_UI_SelfColumn_label")); //$NON-NLS-1$
        selfColumn.setResizable(true);

        tableViewer.setColumnProperties(new String [] {"a", "b"}); //$NON-NLS-1$ //$NON-NLS-2$
        tableViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
        tableViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

        createContextMenuFor(tableViewer);
        int pageIndex = addPage(viewerPane.getControl());
        setPageText(pageIndex, getString("_UI_TablePage_label")); //$NON-NLS-1$
      }

      // This is the page for the table tree viewer.
      //
      {
        ViewerPane viewerPane =
          new ViewerPane(getSite().getPage(), OaEditor.this) {
            @Override
            public Viewer createViewer(Composite composite) {
                            // begin-extension-code
                            TreeViewer newTreeViewer = new TreeViewer(composite) {
                                @Override
                                public void refresh() {
                                    super.refresh();
                  for (PropertySheetPage propertySheetPage : propertySheetPages) {
                    if (!propertySheetPage.getControl().isDisposed())
                      propertySheetPage.refresh();
                  }
                                }
                            };
                            return newTreeViewer;
                            // end-extension-code
            }
            @Override
            public void requestActivation() {
              super.requestActivation();
              setCurrentViewerPane(this);
            }
          };
        viewerPane.createControl(getContainer());

        treeViewerWithColumns = (TreeViewer)viewerPane.getViewer();
                // begin-extension-code
                viewers.add(treeViewerWithColumns);
                // end-extension-code

        Tree tree = treeViewerWithColumns.getTree();
        tree.setLayoutData(new FillLayout());
        tree.setHeaderVisible(true);
        tree.setLinesVisible(true);

        TreeColumn objectColumn = new TreeColumn(tree, SWT.NONE);
        objectColumn.setText(getString("_UI_ObjectColumn_label")); //$NON-NLS-1$
        objectColumn.setResizable(true);
        objectColumn.setWidth(250);

        TreeColumn selfColumn = new TreeColumn(tree, SWT.NONE);
        selfColumn.setText(getString("_UI_SelfColumn_label")); //$NON-NLS-1$
        selfColumn.setResizable(true);
        selfColumn.setWidth(200);

        treeViewerWithColumns.setColumnProperties(new String [] {"a", "b"}); //$NON-NLS-1$ //$NON-NLS-2$
        treeViewerWithColumns.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
        treeViewerWithColumns.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

        createContextMenuFor(treeViewerWithColumns);
        int pageIndex = addPage(viewerPane.getControl());
        setPageText(pageIndex, getString("_UI_TreeWithColumnsPage_label")); //$NON-NLS-1$
      }

      getSite().getShell().getDisplay().asyncExec
        (new Runnable() {
           @Override
           public void run() {
             if (!getContainer().isDisposed()) {
               setActivePage(0);
             }
           }
         });
    }

    // Ensures that this editor will only display the page's tab
    // area if there are more than one page
    //
    getContainer().addControlListener
      (new ControlAdapter() {
        boolean guard = false;
        @Override
        public void controlResized(ControlEvent event) {
          if (!guard) {
            guard = true;
            hideTabs();
            guard = false;
          }
        }
       });

    getSite().getShell().getDisplay().asyncExec
      (new Runnable() {
         @Override
         public void run() {
           updateProblemIndication();
         }
       });
  }

	/**
   * If there is just one page in the multi-page editor part,
   * this hides the single tab at the bottom.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void hideTabs() {
    if (getPageCount() <= 1) {
      setPageText(0, ""); //$NON-NLS-1$
      if (getContainer() instanceof CTabFolder) {
        Point point = getContainer().getSize();
        Rectangle clientArea = getContainer().getClientArea();
        getContainer().setSize(point.x,  2 * point.y - clientArea.height - clientArea.y);
      }
    }
  }

	/**
   * If there is more than one page in the multi-page editor part,
   * this shows the tabs at the bottom.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void showTabs() {
    if (getPageCount() > 1) {
      setPageText(0, getString("_UI_SelectionPage_label")); //$NON-NLS-1$
      if (getContainer() instanceof CTabFolder) {
        Point point = getContainer().getSize();
        Rectangle clientArea = getContainer().getClientArea();
        getContainer().setSize(point.x, clientArea.height + clientArea.y);
      }
    }
  }

	/**
   * This is used to track the active viewer.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected void pageChange(int pageIndex) {
    super.pageChange(pageIndex);

    if (contentOutlinePage != null) {
      handleContentOutlineSelection(contentOutlinePage.getSelection());
    }
  }

	/**
   * This is how the framework determines which interfaces we implement.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@SuppressWarnings("rawtypes")
	@Override
	public <T> T getAdapter(Class<T> key) {
    if (key.equals(IContentOutlinePage.class)) {
      return showOutlineView() ? key.cast(getContentOutlinePage()) : null;
    }
    else if (key.equals(IPropertySheetPage.class)) {
      return key.cast(getPropertySheetPage());
    }
    else if (key.equals(IGotoMarker.class)) {
      return key.cast(this);
    }
    else {
      return super.getAdapter(key);
    }
  }

	/**
   * This accesses a cached version of the content outliner.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public IContentOutlinePage getContentOutlinePage() {
    if (contentOutlinePage == null) {
      // The content outline is just a tree.
      //
      class MyContentOutlinePage extends ContentOutlinePage {
        @Override
        public void createControl(Composite parent) {
          super.createControl(parent);
          contentOutlineViewer = getTreeViewer();
          contentOutlineViewer.addSelectionChangedListener(this);					
                    // begin-extension-code
                    viewers.add(contentOutlineViewer);
                    // end-extension-code
          for (Resource resource : viewerFilterActions.keySet()) {
            for (EmdeViewerFilterAction filterAction : viewerFilterActions.get(resource)) {
              filterAction.addViewer(contentOutlineViewer);
            }
          }  										

          // Set up the tree viewer.
          //
          contentOutlineViewer.setUseHashlookup(true);
          contentOutlineViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
          contentOutlineViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
          contentOutlineViewer.setInput(editingDomain.getResourceSet());

          // Make sure our popups work.
          //
          createContextMenuFor(contentOutlineViewer);

          if (!editingDomain.getResourceSet().getResources().isEmpty()) {
            // Select the root object in the view.
            //
            contentOutlineViewer.setSelection(new StructuredSelection(editingDomain.getResourceSet().getResources().get(0)), true);
          }
        }

        @Override
        public void makeContributions(IMenuManager menuManager, IToolBarManager toolBarManager, IStatusLineManager statusLineManager) {
          super.makeContributions(menuManager, toolBarManager, statusLineManager);
          contentOutlineStatusLineManager = statusLineManager;
        }

        @Override
        public void setActionBars(IActionBars actionBars) {
          super.setActionBars(actionBars);
          getActionBarContributor().shareGlobalActions(this, actionBars);
        }
        
        @Override
        public void dispose() {
                    // begin-extension-code             
                    viewers.remove(contentOutlineViewer);                   
                    // end-extension-code					
          for (Resource resource : viewerFilterActions.keySet()) {
            for (EmdeViewerFilterAction filterAction : viewerFilterActions.get(resource)) {
              filterAction.removeViewer(contentOutlineViewer);
            }
          }					
          super.dispose();
        }				
      }

      contentOutlinePage = new MyContentOutlinePage();

      // Listen to selection so that we can handle it is a special way.
      //
      contentOutlinePage.addSelectionChangedListener
        (new ISelectionChangedListener() {
           // This ensures that we handle selections correctly.
           //
           @Override
           public void selectionChanged(SelectionChangedEvent event) {
             handleContentOutlineSelection(event.getSelection());
           }
         });
    }

    return contentOutlinePage;
  }

	// begin-capella-code
	/**
	 * @generated
	 */
	public static final String PROPERTIES_CONTRIBUTOR =
		"org.polarsys.capella.core.data.oa.properties";  //$NON-NLS-1$

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor#getContributor()
	 * @generated
	 */
	public String getContributorId() {
    return PROPERTIES_CONTRIBUTOR;
  }
	// end-capella-code

	/**
   * This accesses a cached version of the property sheet.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public IPropertySheetPage getPropertySheetPage() {
    PropertySheetPage propertySheetPage =
      new ExtendedPropertySheetPage(editingDomain, ExtendedPropertySheetPage.Decoration.NONE, null, 0, false) {
        @Override
          public void setSelectionToViewer(List<?> selection) {
            OaEditor.this.setSelectionToViewer(selection);
            OaEditor.this.setFocus();
          }

        @Override
        public void setActionBars(IActionBars actionBars) {
          super.setActionBars(actionBars);
          getActionBarContributor().shareGlobalActions(this, actionBars);
        }
      };
    propertySheetPage.setPropertySourceProvider(new AdapterFactoryContentProvider(adapterFactory));
    propertySheetPages.add(propertySheetPage);

    return propertySheetPage;
  }

	/**
   * This deals with how we want selection in the outliner to affect the other views.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void handleContentOutlineSelection(ISelection selection) {
    if (currentViewerPane != null && !selection.isEmpty() && selection instanceof IStructuredSelection) {
      Iterator<?> selectedElements = ((IStructuredSelection)selection).iterator();
      if (selectedElements.hasNext()) {
        // Get the first selected element.
        //
        Object selectedElement = selectedElements.next();

        // If it's the selection viewer, then we want it to select the same selection as this selection.
        //
        if (currentViewerPane.getViewer() == selectionViewer) {
          ArrayList<Object> selectionList = new ArrayList<Object>();
          selectionList.add(selectedElement);
          while (selectedElements.hasNext()) {
            selectionList.add(selectedElements.next());
          }

          // Set the selection to the widget.
          //
          selectionViewer.setSelection(new StructuredSelection(selectionList));
        }
        else {
          // Set the input to the widget.
          //
          if (currentViewerPane.getViewer().getInput() != selectedElement) {
            currentViewerPane.getViewer().setInput(selectedElement);
            currentViewerPane.setTitle(selectedElement);
          }
        }
      }
    }
  }

	/**
   * This is for implementing {@link IEditorPart} and simply tests the command stack.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public boolean isDirty() {
    return ((BasicCommandStack)editingDomain.getCommandStack()).isSaveNeeded();
  }

	/**
   * This is for implementing {@link IEditorPart} and simply saves the model file.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void doSave(IProgressMonitor progressMonitor) {
    // Save only resources that have actually changed.
    //
    final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
    saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
    saveOptions.put(Resource.OPTION_LINE_DELIMITER, Resource.OPTION_LINE_DELIMITER_UNSPECIFIED);

    // Do the work within an operation because this is a long running activity that modifies the workbench.
    //
    WorkspaceModifyOperation operation =
      new WorkspaceModifyOperation() {
        // This is the method that gets invoked when the operation runs.
        //
        @Override
        public void execute(IProgressMonitor monitor) {
          // Save the resources to the file system.
          //
          boolean first = true;
          List<Resource> resources = editingDomain.getResourceSet().getResources();
          for (int i = 0; i < resources.size(); ++i) {
            Resource resource = resources.get(i);
            if ((first || !resource.getContents().isEmpty() || isPersisted(resource)) && !editingDomain.isReadOnly(resource)) {
              try {
                long timeStamp = resource.getTimeStamp();
                resource.save(saveOptions);
                if (resource.getTimeStamp() != timeStamp) {
                  savedResources.add(resource);
                }
              }
              catch (Exception exception) {
                resourceToDiagnosticMap.put(resource, analyzeResourceProblems(resource, exception));
              }
              first = false;
            }
          }
        }
      };

    updateProblemIndication = false;
    try {
      // This runs the options, and shows progress.
      //
      new ProgressMonitorDialog(getSite().getShell()).run(true, false, operation);

      // Refresh the necessary state.
      //
      ((BasicCommandStack)editingDomain.getCommandStack()).saveIsDone();
      firePropertyChange(IEditorPart.PROP_DIRTY);
    }
    catch (Exception exception) {
      // Something went wrong that shouldn't.
      //
      CapellaModellerEditorPlugin.INSTANCE.log(exception);
    }
    updateProblemIndication = true;
    updateProblemIndication();
  }

	/**
   * This returns whether something has been persisted to the URI of the specified resource.
   * The implementation uses the URI converter from the editor's resource set to try to open an input stream.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected boolean isPersisted(Resource resource) {
    boolean result = false;
    try {
      InputStream stream = editingDomain.getResourceSet().getURIConverter().createInputStream(resource.getURI());
      if (stream != null) {
        result = true;
        stream.close();
      }
    }
    catch (IOException e) {
      // Ignore
    }
    return result;
  }

	/**
   * This always returns true because it is not currently supported.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public boolean isSaveAsAllowed() {
    return true;
  }

	/**
   * This also changes the editor's input.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void doSaveAs() {
    SaveAsDialog saveAsDialog = new SaveAsDialog(getSite().getShell());
    saveAsDialog.open();
    IPath path = saveAsDialog.getResult();
    if (path != null) {
      IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
      if (file != null) {
        doSaveAs(URI.createPlatformResourceURI(file.getFullPath().toString(), true), new FileEditorInput(file));
      }
    }
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void doSaveAs(URI uri, IEditorInput editorInput) {
    (editingDomain.getResourceSet().getResources().get(0)).setURI(uri);
    setInputWithNotify(editorInput);
    setPartName(editorInput.getName());
    IProgressMonitor progressMonitor =
      getActionBars().getStatusLineManager() != null ?
        getActionBars().getStatusLineManager().getProgressMonitor() :
        new NullProgressMonitor();
    doSave(progressMonitor);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void gotoMarker(IMarker marker) {
    List<?> targetObjects = markerHelper.getTargetObjects(editingDomain, marker);
    if (!targetObjects.isEmpty()) {
      setSelectionToViewer(targetObjects);
    }
  }

	/**
   * This is called during startup.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void init(IEditorSite site, IEditorInput editorInput) {
    setSite(site);
    setInputWithNotify(editorInput);
    setPartName(editorInput.getName());
    site.setSelectionProvider(this);
    site.getPage().addPartListener(partListener);
    ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListener, IResourceChangeEvent.POST_CHANGE);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void setFocus() {
    if (currentViewerPane != null) {
      currentViewerPane.setFocus();
    }
    else {
      getControl(getActivePage()).setFocus();
    }
  }

	/**
   * This implements {@link org.eclipse.jface.viewers.ISelectionProvider}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
    selectionChangedListeners.add(listener);
  }

	/**
   * This implements {@link org.eclipse.jface.viewers.ISelectionProvider}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
    selectionChangedListeners.remove(listener);
  }

	/**
   * This implements {@link org.eclipse.jface.viewers.ISelectionProvider} to return this editor's overall selection.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public ISelection getSelection() {
    return editorSelection;
  }

	/**
   * This implements {@link org.eclipse.jface.viewers.ISelectionProvider} to set this editor's overall selection.
   * Calling this result will notify the listeners.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void setSelection(ISelection selection) {
    editorSelection = selection;

    for (ISelectionChangedListener listener : selectionChangedListeners) {
      listener.selectionChanged(new SelectionChangedEvent(this, selection));
    }
    setStatusLineManager(selection);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setStatusLineManager(ISelection selection) {
    IStatusLineManager statusLineManager = currentViewer != null && currentViewer == contentOutlineViewer ?
      contentOutlineStatusLineManager : getActionBars().getStatusLineManager();

    if (statusLineManager != null) {
      if (selection instanceof IStructuredSelection) {
        Collection<?> collection = ((IStructuredSelection)selection).toList();
        switch (collection.size()) {
          case 0: {
            statusLineManager.setMessage(getString("_UI_NoObjectSelected")); //$NON-NLS-1$
            break;
          }
          case 1: {
            String text = new AdapterFactoryItemDelegator(adapterFactory).getText(collection.iterator().next());
            statusLineManager.setMessage(getString("_UI_SingleObjectSelected", text)); //$NON-NLS-1$
            break;
          }
          default: {
            statusLineManager.setMessage(getString("_UI_MultiObjectSelected", Integer.toString(collection.size()))); //$NON-NLS-1$
            break;
          }
        }
      }
      else {
        statusLineManager.setMessage(""); //$NON-NLS-1$
      }
    }
  }

	/**
   * This looks up a string in the plugin's plugin.properties file.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static String getString(String key) {
    return CapellaModellerEditorPlugin.INSTANCE.getString(key);
  }

	/**
   * This looks up a string in plugin.properties, making a substitution.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	private static String getString(String key, Object s1) {
    return CapellaModellerEditorPlugin.INSTANCE.getString(key, new Object [] { s1 });
  }

	/**
   * This implements {@link org.eclipse.jface.action.IMenuListener} to help fill the context menus with contributions from the Edit menu.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void menuAboutToShow(IMenuManager menuManager) {
    ((IMenuListener)getEditorSite().getActionBarContributor()).menuAboutToShow(menuManager);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EditingDomainActionBarContributor getActionBarContributor() {
    return (EditingDomainActionBarContributor)getEditorSite().getActionBarContributor();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public IActionBars getActionBars() {
    return getActionBarContributor().getActionBars();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public AdapterFactory getAdapterFactory() {
    return adapterFactory;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void dispose() {
    updateProblemIndication = false;

    ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceChangeListener);

    getSite().getPage().removePartListener(partListener);

    adapterFactory.dispose();		

    // Clean and dispose viewerFilterActions
    for (Resource resource : viewerFilterActions.keySet()) {
      for (EmdeViewerFilterAction filterAction : viewerFilterActions.get(resource)) {
        filterAction.removePropertyChangeListener((IPropertyChangeListener) getActionBarContributor());
        filterAction.dispose();
      }
    }
    // Unregister this editor for ExtendedModel state
    //
    ModelExtensionHelper.getInstance(getEditingDomain().getResourceSet()).removeListener(this);		

    if (getActionBarContributor().getActiveEditor() == this) {
      getActionBarContributor().setActiveEditor(null);
    }

    for (PropertySheetPage propertySheetPage : propertySheetPages) {
      propertySheetPage.dispose();
    }

    if (contentOutlinePage != null) {
      contentOutlinePage.dispose();
    }

    super.dispose();
  }

	/**
   * Returns whether the outline view should be presented to the user.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected boolean showOutlineView() {
    return true;
  }
}