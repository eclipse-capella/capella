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
package org.polarsys.capella.core.explorer.activity.ui.viewer;

import org.eclipse.amalgam.explorer.activity.ui.api.editor.pages.BasicSessionActivityExplorerPage;
import org.eclipse.amalgam.explorer.activity.ui.api.editor.pages.helper.SelectionHelper;
import org.eclipse.amalgam.explorer.activity.ui.api.editor.pages.viewers.DiagramViewer;
import org.eclipse.jface.action.IMenuListener2;
import org.eclipse.jface.action.IMenuManager;
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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.core.explorer.activity.ui.pages.AbstractCapellaPage;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.CloneAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.LocateInCapellaExplorerAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.actions.move.representation.MoveRepresentationAction;
import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.CapellaNavigatorLabelProvider;
import org.polarsys.capella.core.sirius.ui.actions.DeleteRepresentationAction;
import org.polarsys.capella.core.sirius.ui.actions.OpenRepresentationsAction;
import org.polarsys.capella.core.sirius.ui.actions.RenameRepresentationAction;

public class CapellaDiagramViewer extends DiagramViewer {

  private static final String GROUP_MOVE = "Move"; //$NON-NLS-1$
  private MoveRepresentationAction _moveDiagramAction;
  private OpenRepresentationsAction _openRepresentation;
  private RenameRepresentationAction _renameRepresentationAction;
  private BaseSelectionListenerAction _showInCapellaExplorerAction;
  private CloneAction _cloneAction;
  private DeleteRepresentationAction _deleteRepresentationAction;

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

    contextMenuManager.addMenuListener(new IMenuListener2() {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      public void menuAboutToHide(IMenuManager manager) {
        manager.remove(MoveRepresentationAction.MOVE_DIAGRAMS_MENU_ID);
        // Make sure action contained list are freed at each selection
        // time.
        _moveDiagramAction.dispose();
      }

      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      public void menuAboutToShow(IMenuManager manager) {
        manager.appendToGroup(GROUP_MOVE, _moveDiagramAction.fillContextMenu((IStructuredSelection) treeViewer.getSelection()));
      }
    });

    return contextMenuManager;
  }

  @Override
  protected void declareViewerActions(MenuManager contextMenuManager, TreeViewer treeViewer) {
    // Menu manager is not extensible at the moment.
    ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
    ISelectionProvider selectionProvider = page.getEditorSite().getSelectionProvider();

    _showInCapellaExplorerAction = new BaseSelectionListenerAction(Messages.AbstractCapellaArchitectureActivityExplorerPage_ShowInCapellaExplorerAction_Title) {
      /**
       * {@inheritDoc}
       */
      @SuppressWarnings("synthetic-access")
      @Override
      public void run() {
        LocateInCapellaExplorerAction callback = new LocateInCapellaExplorerAction();
        callback.setSite(page.getEditorSite());
        callback.run(_showInCapellaExplorerAction);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      protected boolean updateSelection(IStructuredSelection selection) {
        return containsOnlyRepresentations(selection);
      }
    };

    _showInCapellaExplorerAction.setActionDefinitionId("org.polarsys.capella.core.platform.sirius.ui.navigator.locateInCapellaExplorerCommand"); //$NON-NLS-1$
    _showInCapellaExplorerAction.setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor(
        org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys.IMG_SHOW_IN_CAPELLA_EXPLORER));
    SelectionHelper.registerToSelectionChanges(_showInCapellaExplorerAction, selectionProvider);
    contextMenuManager.add(_showInCapellaExplorerAction);

    contextMenuManager.add(new Separator());
    _openRepresentation = new OpenRepresentationsAction() {

      @Override
      protected boolean updateSelection(IStructuredSelection selection) {
        return containsOnlyRepresentations(selection);
      }
    };
    SelectionHelper.registerToSelectionChanges(_openRepresentation, selectionProvider);
    contextMenuManager.add(_openRepresentation);
    contextMenuManager.add(new Separator());

    _cloneAction = new CloneAction(treeViewer);
    _cloneAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
    SelectionHelper.registerToSelectionChanges(_cloneAction, selectionProvider);
    contextMenuManager.add(_cloneAction);

    _deleteRepresentationAction = new DeleteRepresentationAction() {

      @Override
      protected boolean updateSelection(IStructuredSelection selection) {
        return containsOnlyRepresentations(selection);
      }
    };
    _deleteRepresentationAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
    _deleteRepresentationAction.setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
    SelectionHelper.registerToSelectionChanges(_deleteRepresentationAction, selectionProvider);
    contextMenuManager.add(_deleteRepresentationAction);

    contextMenuManager.add(new Separator(GROUP_MOVE));
    _moveDiagramAction = new MoveRepresentationAction();
    SelectionHelper.registerToSelectionChanges(_moveDiagramAction, selectionProvider);
    _renameRepresentationAction = new RenameRepresentationAction() {

      @Override
      protected boolean updateSelection(IStructuredSelection selection) {
        return containsOnlyRepresentations(selection);
      }
    };
    SelectionHelper.registerToSelectionChanges(_renameRepresentationAction, selectionProvider);
    contextMenuManager.add(_renameRepresentationAction);

    updateActionBars();
  }

  @Override
  protected ILabelProvider getLabelProvider() {
    return new CapellaNavigatorLabelProvider();
  }

  @Override
  protected IContentProvider getContentProvider() {
    return new CapellaArchitectureContentProvider(((AbstractCapellaPage) page).getHandledViewpoint(),
        ((AbstractCapellaPage) page).getFilteringMetaClassForCommonViewpoint());
  }

  @Override
  public void dispose() {

    ISelectionProvider selectionProvider = page.getEditorSite().getSelectionProvider();
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

  }

  /**
   * Viewer filter driven by provided algorithm. Copied from: org.eclipse.amalgam.explorer.activity.ui.internal.DelegatedViewerFilter
   */
  protected class DelegatedViewerFilter extends ViewerFilter {
    /**
     * Delegated filter
     */
    private ViewerFilter _delegatedFilter;

    /**
     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
      boolean selected = true;
      if (null != _delegatedFilter) {
        selected = _delegatedFilter.select(viewer, parentElement, element);
      }
      return selected;
    }

    /**
     * @param delegatedFilter
     */
    public void setDelegatedFilter(ViewerFilter delegatedFilter) {
      _delegatedFilter = delegatedFilter;
    }
  }

  private void updateActionBars() {
    IActionBars editorActionBars = page.getEditorSite().getActionBars();
    editorActionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), _deleteRepresentationAction);
    editorActionBars.setGlobalActionHandler(ActionFactory.RENAME.getId(), _renameRepresentationAction);
    // Update action bars to make sure global ActionHandler are updated accordingly.
    editorActionBars.updateActionBars();
  }

}
