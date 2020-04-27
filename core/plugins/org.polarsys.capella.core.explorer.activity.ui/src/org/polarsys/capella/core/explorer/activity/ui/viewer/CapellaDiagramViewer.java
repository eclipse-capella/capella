/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.explorer.activity.ui.viewer;

import java.util.Collection;

import org.eclipse.amalgam.explorer.activity.ui.api.editor.pages.BasicSessionActivityExplorerPage;
import org.eclipse.amalgam.explorer.activity.ui.api.editor.pages.helper.SelectionHelper;
import org.eclipse.amalgam.explorer.activity.ui.api.editor.pages.viewers.DiagramViewer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.core.explorer.activity.ui.pages.AbstractCapellaPage;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.CloneAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.representation.MoveRepresentationMenuManager;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.CapellaNavigatorLabelProvider;
import org.polarsys.capella.core.sirius.ui.actions.DeleteRepresentationAction;
import org.polarsys.capella.core.sirius.ui.actions.OpenRepresentationsAction;
import org.polarsys.capella.core.sirius.ui.actions.RenameRepresentationAction;

public class CapellaDiagramViewer extends DiagramViewer {

  private static final String GROUP_MOVE = "Move"; //$NON-NLS-1$
  private MoveRepresentationMenuManager moveRepresentationMenu;
  private OpenRepresentationsAction openRepresentation;
  private RenameRepresentationAction renameRepresentationAction;
  private BaseSelectionListenerAction showInCapellaExplorerAction;
  private CloneAction cloneAction;
  private DeleteRepresentationAction deleteRepresentationAction;

  public CapellaDiagramViewer(BasicSessionActivityExplorerPage page) {
    super(page);
  }

  @Override
  protected void addListenersOnViewer(TreeViewer viewer) {
    viewer.addDoubleClickListener(new IDoubleClickListener() {
      /**
       * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
       */
      public void doubleClick(DoubleClickEvent event) {
        IStructuredSelection selection = (IStructuredSelection) event.getSelection();
        // Open selected representations.
        OpenRepresentationsAction action = new OpenRepresentationsAction();
        action.selectionChanged(selection);
        action.run();
      }
    });
  }

  @Override
  protected MenuManager initMenuToViewer(final TreeViewer treeViewer) {
    MenuManager contextMenuManager = new MenuManager("viewerPopup"); //$NON-NLS-1$

    Control control = treeViewer.getControl();
    // Add here some actions.
    declareViewerActions(contextMenuManager, treeViewer);
    Menu contextMenu = contextMenuManager.createContextMenu(control);
    control.setMenu(contextMenu);

    return contextMenuManager;
  }

  @Override
  protected void declareViewerActions(MenuManager contextMenuManager, TreeViewer treeViewer) {
    // Menu manager is not extensible at the moment.
    ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
    ISelectionProvider selectionProvider = page.getEditorSite().getSelectionProvider();

    showInCapellaExplorerAction = new BaseSelectionListenerAction(Messages.AbstractCapellaArchitectureActivityExplorerPage_ShowInCapellaExplorerAction_Title) {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void run() {
        IStructuredSelection selection = showInCapellaExplorerAction.getStructuredSelection();
        Collection<DRepresentationDescriptor> selectedDescriptors = RepresentationHelper.getSelectedDescriptors(selection.toList());
        IAction locatingAction = LocateInCapellaExplorerAction.createLocateTowards((EObject) selectedDescriptors.toArray()[0], "", false);
        locatingAction.run();
      }

      /**
       * {@inheritDoc}
       */
      @Override
      protected boolean updateSelection(IStructuredSelection selection) {
        return containsOnlyRepresentations(selection);
      }
    };

    showInCapellaExplorerAction.setActionDefinitionId("org.polarsys.capella.core.platform.sirius.ui.navigator.locateInCapellaExplorerCommand"); //$NON-NLS-1$
    showInCapellaExplorerAction.setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor(
        org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys.IMG_SHOW_IN_CAPELLA_EXPLORER));
    SelectionHelper.registerToSelectionChanges(showInCapellaExplorerAction, selectionProvider);
    contextMenuManager.add(showInCapellaExplorerAction);

    contextMenuManager.add(new Separator());
    openRepresentation = new OpenRepresentationsAction() {

      @Override
      protected boolean updateSelection(IStructuredSelection selection) {
        return containsOnlyRepresentations(selection);
      }
    };
    SelectionHelper.registerToSelectionChanges(openRepresentation, selectionProvider);
    contextMenuManager.add(openRepresentation);
    contextMenuManager.add(new Separator());

    cloneAction = new CloneAction(treeViewer);
    cloneAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
    SelectionHelper.registerToSelectionChanges(cloneAction, selectionProvider);
    contextMenuManager.add(cloneAction);

    deleteRepresentationAction = new DeleteRepresentationAction() {

      @Override
      protected boolean updateSelection(IStructuredSelection selection) {
        return containsOnlyRepresentations(selection);
      }
    };
    deleteRepresentationAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
    deleteRepresentationAction.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
    SelectionHelper.registerToSelectionChanges(deleteRepresentationAction, selectionProvider);
    contextMenuManager.add(deleteRepresentationAction);

    contextMenuManager.add(new Separator(GROUP_MOVE));
    moveRepresentationMenu = new MoveRepresentationMenuManager();
    SelectionHelper.registerToSelectionChanges(moveRepresentationMenu, selectionProvider);
    contextMenuManager.appendToGroup(GROUP_MOVE, moveRepresentationMenu);
    
    renameRepresentationAction = new RenameRepresentationAction() {

      @Override
      protected boolean updateSelection(IStructuredSelection selection) {
        return containsOnlyRepresentations(selection);
      }
    };
    SelectionHelper.registerToSelectionChanges(renameRepresentationAction, selectionProvider);
    contextMenuManager.add(renameRepresentationAction);

    updateActionBars();
  }

  @Override
  protected ILabelProvider getLabelProvider() {
    return new CapellaNavigatorLabelProvider();
  }

  @Override
  protected IContentProvider getContentProvider() {
    return new CapellaArchitectureContentProvider(((AbstractCapellaPage) page).getFilteringMetaClassForCommonViewpoint());
  }

  @Override
  public void dispose() {

    ISelectionProvider selectionProvider = page.getEditorSite().getSelectionProvider();
    if (null != renameRepresentationAction) {
      selectionProvider.removeSelectionChangedListener(renameRepresentationAction);
      renameRepresentationAction = null;
    }
    if (null != deleteRepresentationAction) {
      selectionProvider.removeSelectionChangedListener(deleteRepresentationAction);
      deleteRepresentationAction = null;
    }
    if (null != openRepresentation) {
      selectionProvider.removeSelectionChangedListener(openRepresentation);
      openRepresentation = null;
    }
    if (null != moveRepresentationMenu) {
      selectionProvider.removeSelectionChangedListener(moveRepresentationMenu);
      moveRepresentationMenu.dispose();
      moveRepresentationMenu = null;
    }
    if (null != cloneAction) {
      selectionProvider.removeSelectionChangedListener(cloneAction);
      cloneAction = null;
    }
    if (null != showInCapellaExplorerAction) {
      selectionProvider.removeSelectionChangedListener(showInCapellaExplorerAction);
      showInCapellaExplorerAction = null;
    }

  }

  /**
   * Viewer filter driven by provided algorithm. Copied from: org.eclipse.amalgam.explorer.activity.ui.internal.DelegatedViewerFilter
   */
  protected class DelegatedViewerFilter extends ViewerFilter {
    /**
     * Delegated filter
     */
    private ViewerFilter delegatedFilter;

    /**
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
      boolean selected = true;
      if (null != delegatedFilter) {
        selected = delegatedFilter.select(viewer, parentElement, element);
      }
      return selected;
    }

    /**
     * @param delegatedFilter
     */
    public void setDelegatedFilter(ViewerFilter delegatedFilter) {
      this.delegatedFilter = delegatedFilter;
    }
  }

  private void updateActionBars() {
    IActionBars editorActionBars = page.getEditorSite().getActionBars();
    editorActionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), deleteRepresentationAction);
    editorActionBars.setGlobalActionHandler(ActionFactory.RENAME.getId(), renameRepresentationAction);
    // Update action bars to make sure global ActionHandler are updated accordingly.
    editorActionBars.updateActionBars();
  }

}
