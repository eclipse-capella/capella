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
package org.polarsys.capella.core.data.information.datatype.presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.ui.action.ViewerFilterAction;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.presentation.EcoreEditorPlugin;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.ControlAction;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.edit.ui.action.CreateSiblingAction;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.polarsys.capella.core.data.capellamodeller.presentation.CapellaModellerEditorPlugin;
import org.polarsys.kitalpha.ad.metadata.helpers.LibraryHelper;
import org.polarsys.kitalpha.emde.ui.actions.EmdeViewerFilterAction;
import org.polarsys.kitalpha.emde.ui.i18n.Messages;
import org.polarsys.kitalpha.resourcereuse.emfscheme.helpers.ModelReuseHelper;
import org.polarsys.kitalpha.resourcereuse.emfscheme.utils.services.ResourceSetLoaderServices;
import org.polarsys.kitalpha.resourcereuse.model.SearchCriteria;
import org.polarsys.kitalpha.resourcereuse.ui.dialog.ResourceReuseSelectionDialog;

/**
 * This is the action bar contributor for the Datatype model editor.
 * <!-- begin-user-doc -->
 * @implements IPropertyChangeListener
 * <!-- end-user-doc -->
 * @generated
 */
public class DatatypeActionBarContributor
	extends EditingDomainActionBarContributor
	implements ISelectionChangedListener, IPropertyChangeListener {
	private final class SchemeLoadResourceAction extends Action {

		public SchemeLoadResourceAction() {
			super("Load Reusable Resource...");
			URI uri = URI.createURI("platform:/plugin/org.polarsys.kitalpha.resourcereuse.emfscheme.ui/icons/searchView.gif");
			setImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(uri));
		}
		
			@Override
		public void run() {
			if (activeEditorPart instanceof IViewerProvider) {
				Viewer viewer = ((IViewerProvider) activeEditorPart).getViewer();
				if (viewer == null) 
					return ;
				ResourceReuseSelectionDialog dialog = new ResourceReuseSelectionDialog(activeEditor.getSite().getShell());
				
				if (dialog.open() == Window.OK) {
					EObject selection = currentResource.getContents().get(0);
					SearchCriteria criteria = dialog.getCriteria();
					URI modelToLoad = ModelReuseHelper.createModelReuseURI(criteria);
					try {
						Resource res = ResourceSetLoaderServices.loadResourceForCurrentResourceSet(selection, modelToLoad);
						if (res != null)
							LibraryHelper.add(currentResource.getResourceSet(), currentResource.getURI(), modelToLoad);
					} catch (Exception e) {
						CapellaModellerEditorPlugin.INSTANCE.log(e);
					}
				}
			}
		}
	}

	private final class RefreshViewerAction extends Action {
		private RefreshViewerAction() {
			super(CapellaModellerEditorPlugin.INSTANCE.getString("_UI_RefreshViewer_menu_item")); //$NON-NLS-1$
		}

			@Override
		public boolean isEnabled() {
			return activeEditorPart instanceof IViewerProvider;
		}

			@Override
		public void run() {
			if (activeEditorPart instanceof IViewerProvider) {
				Viewer viewer = ((IViewerProvider) activeEditorPart)
						.getViewer();
				if (viewer != null) {
					viewer.refresh();
				}
			}
		}
	}

	private final class ShowPropertiesViewAction extends Action {
		private ShowPropertiesViewAction() {
			super(CapellaModellerEditorPlugin.INSTANCE.getString("_UI_ShowPropertiesView_menu_item")); //$NON-NLS-1$
		}

			@Override
		public void run() {
			try {
				getPage().showView("org.eclipse.ui.views.PropertySheet");  //$NON-NLS-1$
			} catch (PartInitException exception) {
				CapellaModellerEditorPlugin.INSTANCE.log(exception);
			}
		}
	}

	/**
   * ExtendedLoadResourceAction.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public static class ExtendedLoadResourceAction extends LoadResourceAction {
		/**
   	 * <!-- begin-user-doc -->
	 	 * <!-- end-user-doc -->
   	 * @generated
   	 */	
		@Override
		public void run() {
      ExtendedLoadResourceDialog loadResourceDialog = new ExtendedLoadResourceDialog(
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), domain
      );    
      loadResourceDialog.open();
    } 
		   
		/**
     * <!-- begin-user-doc -->
	 	 * <!-- end-user-doc -->
   	 * @generated
   	 */    
		public static class ExtendedLoadResourceDialog extends LoadResourceAction.LoadResourceDialog {
			/**
   		 * <!-- begin-user-doc -->
	 		 * <!-- end-user-doc -->
   		 * @generated
   		 */				
			public ExtendedLoadResourceDialog(Shell parent, EditingDomain domain) {
        super(parent, domain);
      }
			
			/**
   		 * <!-- begin-user-doc -->
	 		 * <!-- end-user-doc -->
   		 * @generated
   		 */
			@Override      		      		
			protected boolean processResource(Resource resource) {
        	ResourceSet resourceSet = domain.getResourceSet();
        LibraryHelper.add(resourceSet, resourceSet.getResources().get(0).getURI(), resource.getURI());
          return true;
        }
		}
    
		/**
     * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
     * @generated
     */    
		public static class RegisteredPackageDialog extends ElementListSelectionDialog {
			/**
   		 * <!-- begin-user-doc -->
	 		 * <!-- end-user-doc -->
   		 * @generated
   		 */								
			public RegisteredPackageDialog(Shell parent) {
        super(
          parent, 
          new LabelProvider() {
            @Override
            public Image getImage(Object element) {
              return ExtendedImageRegistry.getInstance().getImage(EcoreEditPlugin.INSTANCE.getImage("full/obj16/EPackage")); //$NON-NLS-1$
            }
          }
        );        
        setMultipleSelection(true);
        setMessage(EcoreEditorPlugin.INSTANCE.getString("_UI_SelectRegisteredPackageURI")); //$NON-NLS-1$
        setFilter("*");
        Map<String, URI> ePackageNsURItoGenModelLocationMap = EcorePlugin.getEPackageNsURIToGenModelLocationMap();
        Object [] result = ePackageNsURItoGenModelLocationMap.keySet().toArray(new Object[ePackageNsURItoGenModelLocationMap.size()]);
        Arrays.sort(result);
        setElements(result);
        setTitle(EcoreEditorPlugin.INSTANCE.getString("_UI_PackageSelection_label")); //$NON-NLS-1$
      }
		}
    
	}

	/**
   * This keeps track of the active editor.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IEditorPart activeEditorPart;

	/**
   * This keeps track of the current selection provider.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ISelectionProvider selectionProvider;

	/**
   * This action opens the Properties view.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IAction showPropertiesViewAction =
		new ShowPropertiesViewAction();

	/**
   * This action refreshes the viewer of the current editor if the editor
   * implements {@link org.eclipse.emf.common.ui.viewer.IViewerProvider}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IAction refreshViewerAction =
		new RefreshViewerAction();

	/**
   * This action load resource from resourcereuse/emfscheme
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IAction schemeLoadResourceAction = new SchemeLoadResourceAction();

	/**
   * This will contain one {@link org.eclipse.emf.edit.ui.action.CreateChildAction} corresponding to each descriptor
   * generated for the current selection by the item provider.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected Collection<IAction> createChildActions;

	/**
   * This is the menu manager into which menu contribution items should be added for CreateChild actions.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IMenuManager createChildMenuManager;

	/**
   * This will contain one {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} corresponding to each descriptor
   * generated for the current selection by the item provider.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected Collection<IAction> createSiblingActions;

	/**
   * This is the menu manager into which menu contribution items should be added for CreateSibling actions.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IMenuManager createSiblingMenuManager;
	
	/**
   * This will contain a collection {@link org.polarsys.kitalpha.emde.ui.actions.EmdeViewerFilterAction} applicable
   * to the current loaded Resource.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected Collection<EmdeViewerFilterAction> currentResourceEmdeViewerFilterActions;	
	
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */    
	protected IMenuManager extensionViewerFilterMenuManager;	
  
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */    
	protected Resource currentResource;
	
	/**
   * This creates an instance of the contributor.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public DatatypeActionBarContributor() {
    super(ADDITIONS_LAST_STYLE);
    loadResourceAction = new ExtendedLoadResourceAction();
    validateAction = new ValidateAction();
    controlAction = new ControlAction();
  }
	
	/**
   * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent) 
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */	
	public void propertyChange(PropertyChangeEvent event) {
    if (event.getSource() instanceof ViewerFilterAction) {
      // Fake a selection changed event to update the menus.
      if (selectionProvider.getSelection() != null) {
        selectionChanged(new SelectionChangedEvent(selectionProvider, selectionProvider.getSelection()));
      }	    
    }	  
  }	
 
	/**
   * This adds Separators for editor additions to the tool bar.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) {
    super.contributeToToolBar(toolBarManager);
    toolBarManager.add(new Separator("datatype-settings")); //$NON-NLS-1$
    toolBarManager.add(new Separator("datatype-additions")); //$NON-NLS-1$
  }

	/**
   * This adds to the menu bar a menu and some separators for editor additions,
   * as well as the sub-menus for object creation items.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void contributeToMenu(IMenuManager menuManager) {
    super.contributeToMenu(menuManager);

    IMenuManager submenuManager = new MenuManager(CapellaModellerEditorPlugin.INSTANCE.getString("_UI_DatatypeEditor_menu"), "org.polarsys.capella.core.data.information.datatypeMenuID"); //$NON-NLS-1$ //$NON-NLS-2$
    menuManager.insertAfter("additions", submenuManager); //$NON-NLS-1$
    submenuManager.add(new Separator("settings")); //$NON-NLS-1$
    submenuManager.add(new Separator("actions")); //$NON-NLS-1$
    submenuManager.add(new Separator("additions")); //$NON-NLS-1$
    submenuManager.add(new Separator("additions-end")); //$NON-NLS-1$
    
    // Prepare Model Extension Menu Manager
    extensionViewerFilterMenuManager = new MenuManager(Messages._UI_Model_Extensions);
    submenuManager.insertBefore("additions-end", extensionViewerFilterMenuManager); //$NON-NLS-1$
    submenuManager.insertBefore("additions-end", new Separator()); //$NON-NLS-1$
        		
    // Prepare for CreateChild item addition or removal.
    //
    createChildMenuManager = new MenuManager(CapellaModellerEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item")); //$NON-NLS-1$
    submenuManager.insertBefore("additions", createChildMenuManager); //$NON-NLS-1$

    // Prepare for CreateSibling item addition or removal.
    //
    createSiblingMenuManager = new MenuManager(CapellaModellerEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item")); //$NON-NLS-1$
    submenuManager.insertBefore("additions", createSiblingMenuManager); //$NON-NLS-1$

    // Force an update because Eclipse hides empty menus now.
    //
    submenuManager.addMenuListener
      (new IMenuListener() {
         @Override
         public void menuAboutToShow(IMenuManager menuManager) {
           menuManager.updateAll(true);
         }
       });

    addGlobalActions(submenuManager);
  }

	/**
   * When the active editor changes, this remembers the change and registers with it as a selection provider.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void setActiveEditor(IEditorPart part) {
    super.setActiveEditor(part);
    activeEditorPart = part;


    if (part instanceof DatatypeEditor == false) {
      if (currentResourceEmdeViewerFilterActions != null) {
        for (EmdeViewerFilterAction filterAction : currentResourceEmdeViewerFilterActions) {      
          filterAction.setEnabled(false);
        }
      }
    }

    // Switch to the new selection provider.
    //
    if (selectionProvider != null) {
      selectionProvider.removeSelectionChangedListener(this);
    }
    if (part == null) {
      selectionProvider = null;
    }
    else {
      selectionProvider = part.getSite().getSelectionProvider();
      selectionProvider.addSelectionChangedListener(this);

      // Fake a selection changed event to update the menus.
      //
      if (selectionProvider.getSelection() != null) {
        selectionChanged(new SelectionChangedEvent(selectionProvider, selectionProvider.getSelection()));
      }
    }
  }

	/**
	 * @see org.eclipse.ui.part.EditorActionBarContributor#dispose()
	 * @generated
	 */
	@Override
	public void dispose() {
    super.dispose();
    if (currentResourceEmdeViewerFilterActions != null) {
      currentResourceEmdeViewerFilterActions.clear();
      currentResourceEmdeViewerFilterActions = null;
    }
    if (extensionViewerFilterMenuManager != null) {
      extensionViewerFilterMenuManager.dispose();
      extensionViewerFilterMenuManager = null;
    }
  }	

	/**
   * This implements {@link org.eclipse.jface.viewers.ISelectionChangedListener},
   * handling {@link org.eclipse.jface.viewers.SelectionChangedEvent}s by querying for the children and siblings
   * that can be added to the selected object and updating the menus accordingly.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
    // Current Selection
    ISelection selection = event.getSelection();
    Object selectedObject = null;
    if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() == 1) {
      selectedObject = ((IStructuredSelection) selection).getFirstElement();
    }
      
    Resource resource = null;
    if (selectedObject instanceof EObject) {
      resource = ((EObject) selectedObject).eResource();
    } else if (selectedObject instanceof Resource) {
      resource = (Resource) selectedObject;
    }
    if (resource != null) {
      if (resource.equals(currentResource) == false) {
        if (currentResource != null) {
          if (extensionViewerFilterMenuManager != null) {
            depopulateManager(extensionViewerFilterMenuManager, currentResourceEmdeViewerFilterActions);
          }
          currentResourceEmdeViewerFilterActions = null;
        }
        currentResource = resource;
      }
    }
    		
    // Remove any menu items for old selection.
    //
    if (createChildMenuManager != null) {
      depopulateManager(createChildMenuManager, createChildActions);
    }
    if (createSiblingMenuManager != null) {
      depopulateManager(createSiblingMenuManager, createSiblingActions);
    }

    // Populate EmdeViewerFilterActions if necessary
    if (currentResourceEmdeViewerFilterActions == null) {
      currentResourceEmdeViewerFilterActions = ((DatatypeEditor) activeEditorPart).getEmdeViewerFilterActions(currentResource);
      if (extensionViewerFilterMenuManager != null) {
        populateManager(extensionViewerFilterMenuManager, currentResourceEmdeViewerFilterActions, null);
        extensionViewerFilterMenuManager.update(true);
      }		  
    }		

    // Query the new selection for appropriate new child/sibling descriptors
    //
    Collection<?> newChildDescriptors = null;
    Collection<?> newSiblingDescriptors = null;

    if (selectedObject != null) {
      EditingDomain domain = ((IEditingDomainProvider)activeEditorPart).getEditingDomain();

      newChildDescriptors = domain.getNewChildDescriptors(selectedObject, null);
      newSiblingDescriptors = domain.getNewChildDescriptors(null, selectedObject);
    }

    // Generate actions for selection; populate and redraw the menus.
    //
    createChildActions = generateCreateChildActions(newChildDescriptors, selection);
    createSiblingActions = generateCreateSiblingActions(newSiblingDescriptors, selection);

    if (createChildMenuManager != null) {
      populateManager(createChildMenuManager, createChildActions, null);
      createChildMenuManager.update(true);
    }
    if (createSiblingMenuManager != null) {
      populateManager(createSiblingMenuManager, createSiblingActions, null);
      createSiblingMenuManager.update(true);
    }
  }

	/**
   * This generates a {@link org.eclipse.emf.edit.ui.action.CreateChildAction} for each object in <code>descriptors</code>,
   * and returns the collection of these actions.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected Collection<IAction> generateCreateChildActions(Collection<?> descriptors, ISelection selection) {
    Collection<IAction> actions = new ArrayList<IAction>();
    if (descriptors != null) {
      for (Object descriptor : descriptors) {
        actions.add(new CreateChildAction(activeEditorPart, selection, descriptor));
      }
    }
    return actions;
  }

	/**
   * This generates a {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} for each object in <code>descriptors</code>,
   * and returns the collection of these actions.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected Collection<IAction> generateCreateSiblingActions(Collection<?> descriptors, ISelection selection) {
    Collection<IAction> actions = new ArrayList<IAction>();
    if (descriptors != null) {
      for (Object descriptor : descriptors) {
        actions.add(new CreateSiblingAction(activeEditorPart, selection, descriptor));
      }
    }
    return actions;
  }

	/**
   * This populates the specified <code>manager</code> with {@link org.eclipse.jface.action.ActionContributionItem}s
   * based on the {@link org.eclipse.jface.action.IAction}s contained in the <code>actions</code> collection,
   * by inserting them before the specified contribution item <code>contributionID</code>.
   * If <code>contributionID</code> is <code>null</code>, they are simply added.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void populateManager(IContributionManager manager, Collection<? extends IAction> actions, String contributionID) {
    if (actions != null) {
      for (IAction action : actions) {
        if (contributionID != null) {
          manager.insertBefore(contributionID, action);
        }
        else {
          manager.add(action);
        }
      }
    }
  }
		
	/**
   * This removes from the specified <code>manager</code> all {@link org.eclipse.jface.action.ActionContributionItem}s
   * based on the {@link org.eclipse.jface.action.IAction}s contained in the <code>actions</code> collection.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void depopulateManager(IContributionManager manager, Collection<? extends IAction> actions) {
    if (actions != null) {
      IContributionItem[] items = manager.getItems();
      for (int i = 0; i < items.length; i++) {
        // Look into SubContributionItems
        //
        IContributionItem contributionItem = items[i];
        while (contributionItem instanceof SubContributionItem) {
          contributionItem = ((SubContributionItem)contributionItem).getInnerItem();
        }

        // Delete the ActionContributionItems with matching action.
        //
        if (contributionItem instanceof ActionContributionItem) {
          IAction action = ((ActionContributionItem)contributionItem).getAction();
          if (actions.contains(action)) {
            manager.remove(contributionItem);
          }
        }
      }
    }
  }

	/**
   * This populates the pop-up menu before it appears.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void menuAboutToShow(IMenuManager menuManager) {
    super.menuAboutToShow(menuManager);
    MenuManager submenuManager = null;

    submenuManager = new MenuManager(CapellaModellerEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item")); //$NON-NLS-1$
    populateManager(submenuManager, createChildActions, null);
    menuManager.insertBefore("edit", submenuManager); //$NON-NLS-1$

    submenuManager = new MenuManager(CapellaModellerEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item")); //$NON-NLS-1$
    populateManager(submenuManager, createSiblingActions, null);
    menuManager.insertBefore("edit", submenuManager); //$NON-NLS-1$

    MenuManager extensionMenuManager = new MenuManager(Messages._UI_Model_Extensions);
    populateManager(extensionMenuManager, currentResourceEmdeViewerFilterActions, null);
    menuManager.insertAfter("additions", extensionMenuManager); //$NON-NLS-1$		
    menuManager.insertAfter("additions", new Separator()); //$NON-NLS-1$    
  }

	/**
   * This inserts global actions before the "additions-end" separator.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected void addGlobalActions(IMenuManager menuManager) {
    menuManager.insertAfter("additions-end", new Separator("ui-actions")); //$NON-NLS-1$ //$NON-NLS-2$
    menuManager.insertAfter("ui-actions", showPropertiesViewAction); //$NON-NLS-1$
    menuManager.insertBefore("additions-end", schemeLoadResourceAction); //$NON-NLS-1$
    menuManager.insertAfter("ui-actions", refreshViewerAction); //$NON-NLS-1$

    super.addGlobalActions(menuManager);
  }

	/**
   * This ensures that a delete action will clean up all references to deleted objects.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected boolean removeAllReferencesOnDelete() {
    return true;
  }

}