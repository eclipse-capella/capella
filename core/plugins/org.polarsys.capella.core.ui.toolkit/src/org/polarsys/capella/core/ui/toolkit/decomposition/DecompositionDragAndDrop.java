/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.toolkit.decomposition;

import java.util.List;

import org.eclipse.jface.util.DelegatingDropAdapter;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * Class <code>DecompositionDragAndDrop</code> enables drag and drop operations for the Decomposition Operations.
 */
public class DecompositionDragAndDrop {

  private DragSource _dragSource;
  private DropTarget _dropTarget;
  private DecompositionModel _decompModel;

  private TreeViewer _sourceViewer;
  private TreeViewer _targetViewer;

  private LocalSelectionTransfer _sourceTransfer = LocalSelectionTransfer.getTransfer();
  private LocalSelectionTransfer _targetTransfer = LocalSelectionTransfer.getTransfer();
  static Object currentElement;

  /**
   * Constructor
   * @param sourceTree_p
   *          the source tree
   * @param decompModel_p
   *          the decomposition model
   */
  public DecompositionDragAndDrop(Tree sourceTree_p, DecompositionModel decompModel_p) {
    _dragSource = new DragSource(sourceTree_p, DND.DROP_MOVE | DND.DROP_COPY);
    _dropTarget = new DropTarget(sourceTree_p, DND.DROP_MOVE | DND.DROP_COPY);

    _dragSource.setTransfer(new Transfer[] { _sourceTransfer });
    _dropTarget.setTransfer(new Transfer[] { _targetTransfer });

    LCDragSourceListener dragSourceListener = new LCDragSourceListener(sourceTree_p);
    _dragSource.addDragListener(dragSourceListener);

    LCDropTargetAdapter dropTargetAdapter = new LCDropTargetAdapter();
    dragSourceListener.setDropTargetAdapter(dropTargetAdapter);

    _dropTarget.addDropListener(dropTargetAdapter);

    _decompModel = decompModel_p;
  }

  /**
   * Validates the drop target. Checks whether an item is dropped on to the same tree, and drop is allowed on the target item.
   * @param event
   *          the DropTargetEvent
   * @param dragSourceTree_p
   *          the source tree
   * @return
   */
  public boolean dropTargetValidate(DropTargetEvent event, Tree dragSourceTree_p) {
    if (event.item != null) {
      TreeItem item = (TreeItem) event.item;

      Object treeID = item.getParent().getData(IDecompositionDataConstants.TREEID_DATA);
      boolean checkExistence = false;
      if (treeID.equals(IDecompositionDataConstants.TARGET_TREE_ID)) {
        checkExistence = true;
      }

      if (!item.getParent().equals(dragSourceTree_p) && _decompModel.isDropAllowed(item.getData()) && _decompModel.isDragAllowed(currentElement)) {

        if (checkExistence) {
          if (_decompModel.isDropPossible(item.getData(), currentElement)) {
            if (event.detail != DND.DROP_COPY && event.detail != DND.DROP_MOVE) {
              event.detail = DND.DROP_MOVE;
            }
          } else {
            event.detail = DND.DROP_NONE;
          }
        } else {
          if (event.detail != DND.DROP_COPY && event.detail != DND.DROP_MOVE) {
            event.detail = DND.DROP_MOVE;
          }
        }
      } else {
         event.detail = DND.DROP_NONE;
      }
    } else {
      event.detail = DND.DROP_NONE;
    }
    return event.detail != DND.DROP_NONE;
  }

  /**
   * @return the dragSource
   */
  public DragSource getDragSource() {
    return _dragSource;
  }

  /**
   * @param dragSource_p
   *          the dragSource to set
   */
  public void setDragSource(DragSource dragSource_p) {
    _dragSource = dragSource_p;
  }

  /**
   * @return the dropTarget
   */
  public DropTarget getDropTarget() {
    return _dropTarget;
  }

  /**
   * @param dropTarget_p
   *          the dropTarget to set
   */
  public void setDropTarget(DropTarget dropTarget_p) {
    _dropTarget = dropTarget_p;
  }

  // ///////DRAG SOURCE LISTENER////////////////
  class LCDragSourceListener implements DragSourceListener {
    private Tree _dragSourceTree;
    private LCDropTargetAdapter _dropTargetAdapter;

    public LCDragSourceListener(Tree sourceTree_p) {
      super();
      _dragSourceTree = sourceTree_p;
    }

    public void dragStart(DragSourceEvent event) {
      Object treeID = _dragSourceTree.getData(IDecompositionDataConstants.TREEID_DATA);
      if (treeID.equals(IDecompositionDataConstants.SOURCE_TREE_ID)) { 
        event.doit = !getSourceViewer().getSelection().isEmpty();
        if (event.doit) {
          Object data = ((ITreeSelection) getSourceViewer().getSelection()).getFirstElement();
          getSourceTransfer().setSelection(getSourceViewer().getSelection());
          event.data = data;
          currentElement = data;
          _dropTargetAdapter.setSourceTree(_dragSourceTree);
        }
      } else if (treeID.equals(IDecompositionDataConstants.TARGET_TREE_ID)) { 
        event.doit = !getTargetViewer().getSelection().isEmpty();
        if (event.doit) {
          Object data = ((ITreeSelection) getTargetViewer().getSelection()).getFirstElement();
          getTargetTransfer().setSelection(getTargetViewer().getSelection());
          event.data = data;
          currentElement = data;
          _dropTargetAdapter.setSourceTree(_dragSourceTree);
        }
      }
    }

    public void dragFinished(DragSourceEvent event) {
      _dropTargetAdapter.setSourceTree(null);
      event.data = null;
      currentElement = null;
      refreshItems();
    }

    public void dragSetData(DragSourceEvent event) {
      if (event.doit) {
        Object treeID = _dragSourceTree.getData(IDecompositionDataConstants.TREEID_DATA);
        if (treeID.equals(IDecompositionDataConstants.SOURCE_TREE_ID)) {

          Object data = ((ITreeSelection) getSourceViewer().getSelection()).getFirstElement();
          getSourceTransfer().setSelection(getSourceViewer().getSelection());
          event.data = data;
          currentElement = data;
          _dropTargetAdapter.setSourceTree(_dragSourceTree);
        } else if (treeID.equals(IDecompositionDataConstants.TARGET_TREE_ID)) {

          Object data = ((ITreeSelection) getTargetViewer().getSelection()).getFirstElement();
          getTargetTransfer().setSelection(getTargetViewer().getSelection());
          event.data = data;
          currentElement = data;
          _dropTargetAdapter.setSourceTree(_dragSourceTree);
        }
      }
    }

    /**
     * @return the dropTargetAdapter
     */
    public LCDropTargetAdapter getDropTargetAdapter() {
      return _dropTargetAdapter;
    }

    /**
     * @param dropTargetAdapter_p
     *          the dropTargetAdapter to set
     */
    public void setDropTargetAdapter(LCDropTargetAdapter dropTargetAdapter_p) {
      _dropTargetAdapter = dropTargetAdapter_p;
    }
  }

  // ////////DROP TARGET LISTENER///////////////
  class LCDropTargetAdapter extends DropTargetAdapter {
    private Tree _sourceTree;

    public LCDropTargetAdapter() {
      super();
    }

    @Override
    public void dragEnter(DropTargetEvent event) {
      dropTargetValidate(event, getSourceTree());
    }

    @Override
    public void dragOver(DropTargetEvent event) {
      dropTargetValidate(event, getSourceTree());
      event.feedback |= DND.FEEDBACK_EXPAND | DND.FEEDBACK_SCROLL;
    }

    /**
     * @see DelegatingDropAdapter#drop(DropTargetEvent)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void drop(DropTargetEvent event) {
      if (dropTargetValidate(event, getSourceTree())) {

        TreeItem item = (TreeItem) event.item;

        if (item != null) {
          ITreeSelection selection = (ITreeSelection) event.data;
          Object data = selection.getFirstElement();

          if (getDecompModel().isDragAllowed(data)) {

            // check whether operation is attach or detach
            Object treeID = item.getParent().getData(IDecompositionDataConstants.TREEID_DATA);
            if (treeID.equals(IDecompositionDataConstants.SOURCE_TREE_ID)) { 
              if (!getTargetViewer().getSelection().isEmpty()) {
                List list = selection.toList();
                for (Object element : list) {
                  if (!handleDetach(element))
                    continue;

                }
                refreshItems();
              }
            } else if (treeID.equals(IDecompositionDataConstants.TARGET_TREE_ID)) { 
              if (getDecompModel().isDropPossible(item.getData(), data)) {
                if (handleAttach(item.getData(), data)) {
                  refreshItems();
                }
              }
            }
          }
        }
      }
      setSourceTree(null);
    }

    /**
     * @return the sourceTree
     */
    public Tree getSourceTree() {
      return _sourceTree;
    }

    /**
     * @param sourceTree_p
     *          the sourceTree to set
     */
    public void setSourceTree(Tree sourceTree_p) {
      _sourceTree = sourceTree_p;
    }
  }

  /**
   * Handles dropping of items.
   * @param node_p
   *          the node on which the item is to be dropped
   * @param item_p
   *          the item
   */
  public boolean handleAttach(Object node_p, Object item_p) {

	  if (node_p instanceof DecompositionComponent && item_p instanceof DecompositionItem) {
		  //Case : Drop Interface on Component
		  DecompositionItem itf = (DecompositionItem) item_p;
		  DecompositionComponent component = (DecompositionComponent) node_p;
		  return _decompModel.attachInterface(component, itf.getCopy());
	  } 
	  else if (node_p instanceof DecompositionComponent && item_p instanceof DecompositionItemService) {
		  //Case : Drop Service on Component
		  DecompositionItemService sce = (DecompositionItemService) item_p;
		  DecompositionComponent component = (DecompositionComponent) node_p;
		  return _decompModel.attachService(component, sce.getCopy(), sce.getParentDecompositionItem());
	  }
	  else if (node_p instanceof DecompositionItem && item_p instanceof DecompositionItemService) {
		  //Case : Drop Service on Interface
		  DecompositionItemService sce = (DecompositionItemService) item_p;
		  DecompositionItem itf = (DecompositionItem) node_p;
		  return _decompModel.attachService(itf, sce.getCopy(), sce.getParentDecompositionItem());
	  }
	  return false;
  }

  /**
   * @param node_p
   * @param item_p
   */
  public boolean handleDetach(Object node_p, Object item_p) {
    DecompositionComponent component = (DecompositionComponent) node_p;
    DecompositionItem item = (DecompositionItem) item_p;
    return _decompModel.detachInterface(component, item);
  }

  public boolean handleDetach(Object item_p) {
  if (item_p instanceof DecompositionItem) {
		  DecompositionItem item = (DecompositionItem) item_p;
		  DecompositionComponent component = item.getParentComponent();
		  if (component.isReusedComponent())
			  return false;
		  return _decompModel.detachInterface(component, item);  
	  } else if (item_p instanceof DecompositionItemService) {
		  // Add case for detach 'DecompositionItemService' (operation)
		  DecompositionItemService itemSce = (DecompositionItemService) item_p;
		  DecompositionItem itemItf = itemSce.getParentDecompositionItem();
		  if (!itemItf.isInternal()) 
		    return false;
		  
		  return _decompModel.detachService(itemItf, itemSce);
	  }
	  
	  return false;
  }

  public void refreshItems() {
    getSourceViewer().refresh(true);
    getTargetViewer().refresh(true);
    getSourceViewer().setSelection(null, true);
    getTargetViewer().setSelection(null, true);
    getSourceViewer().getTree().notifyListeners(SWT.Selection, new Event());
  }

  /**
   * @return the viewer
   */
  public TreeViewer getSourceViewer() {
    return _sourceViewer;
  }

  /**
   * @param viewer_p
   *          the viewer to set
   */
  public void setSourceViewer(TreeViewer viewer_p) {
    _sourceViewer = viewer_p;
  }

  /**
   * @return the sourceTransfer
   */
  public LocalSelectionTransfer getSourceTransfer() {
    return _sourceTransfer;
  }

  /**
   * @param sourceTransfer_p
   *          the sourceTransfer to set
   */
  public void setSourceTransfer(LocalSelectionTransfer sourceTransfer_p) {
    _sourceTransfer = sourceTransfer_p;
  }

  /**
   * @return the decompModel
   */
  public DecompositionModel getDecompModel() {
    return _decompModel;
  }

  /**
   * @return the targetViewer
   */
  public TreeViewer getTargetViewer() {
    return _targetViewer;
  }

  /**
   * @param targetViewer_p
   *          the targetViewer to set
   */
  public void setTargetViewer(TreeViewer targetViewer_p) {
    _targetViewer = targetViewer_p;
  }

  /**
   * @return the targetTransfer
   */
  public LocalSelectionTransfer getTargetTransfer() {
    return _targetTransfer;
  }

  /**
   * @param targetTransfer_p
   *          the targetTransfer to set
   */
  public void setTargetTransfer(LocalSelectionTransfer targetTransfer_p) {
    _targetTransfer = targetTransfer_p;
  }
}
