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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Text;

import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;

/**
 */
public class DecompositionVisitor {

  public DecompositionVisitor() {
  }

  public static void addNewDecomposition(DecompositionTargetViewer viewer) {
    String name = Util.ZERO_LENGTH_STRING;
    DecompositionModel model = viewer.getDecompositionModel();

    if (model.addDecomposition(name)) {
      List<Decomposition> list = model.getDecompositions();
      int size = list.size();
      if (size == 2) {
        viewer.setDecompositionName(false);
      }
      Decomposition decomp = list.get(size - 1);
      name = DecompositionUtil.getDecompositionName(list);
      Text text = viewer.getDecompNameText();
      if (text != null) {
        String nameText = text.getText();
        if (nameText.trim().length() > 0) {
          name = nameText;
          if (name.equals(Messages.getString("LCDecompGeneralViewer.decomposition.name") + "1")) { //$NON-NLS-1$//$NON-NLS-2$
            if (size == 2) {
              viewer.setDecompositionName(true);
            }
          }
          text.setText(Util.ZERO_LENGTH_STRING);
        }
      }

      decomp.setName(name);

      viewer.createDecompositionTab(decomp);
      viewer.createDND();

      viewer.setDecompositionFieldsEnabled(model.getDecompositions().size());
    }
  }

  /*
   * Adds a new target component
   */
  public static void addNewTargetComponent(DecompositionTargetViewer viewer) {
    CTabFolder folder = viewer.getTabFolder();
    DecompositionModel model = viewer.getDecompositionModel();
    CTabItem item = folder.getSelection();
    TreeViewer treeViewer = (TreeViewer) item.getData(IDecompositionDataConstants.TARGET_TREEVIEWER_DATA);

    DecompositionComponent comp = new DecompositionComponent();

    Decomposition decomposition = (Decomposition) item.getData();
    String name = DecompositionUtil.getTargetComponentName(decomposition.getTargetComponents());
    comp.setName(name);
    if (model.addNewTargetComponent(decomposition, comp)) {
      viewer.refreshItems(treeViewer);
    }
  }

  /*
   * Detaches an interface from the target component
   */
  @SuppressWarnings("rawtypes")
  public static void detachInterface(DecompositionTargetViewer viewer) {
    CTabFolder folder = viewer.getTabFolder();
    DecompositionModel model = viewer.getDecompositionModel();
    CTabItem item = folder.getSelection();
    TreeViewer treeViewer = (TreeViewer) item.getData(IDecompositionDataConstants.TARGET_TREEVIEWER_DATA);
    ITreeSelection selection = (ITreeSelection) treeViewer.getSelection();
    if (selection.isEmpty()) {
      return;
    }
    List list = selection.toList();
    for (Object element : list) {
      if (model.isDetachAllowed(element)) {
        DecompositionItem pair = (DecompositionItem) element;
        DecompositionComponent comp = pair.getParentComponent();
        if (comp.isReusedComponent()) {
          continue;
        }
        if (model.detachInterface(comp, pair)) {
          viewer.refreshItems(treeViewer);
        }
      }
    }
  }

  public static List<DecompositionComponent> getReusedComponents(Decomposition decomposition_p) {
    List<DecompositionComponent> list = new ArrayList<DecompositionComponent>(1);
    for (DecompositionComponent comp : decomposition_p.getTargetComponents()) {
      if (comp.isReusedComponent()) {
        list.add(comp);
      }
    }
    return list;
  }

  // ///////////////////////////////////////////////////////////////////////////
  // ////////////////////// ALL TARGET COMPONENT OPERATIONS/////////////////////
  // ///////////////////////////////////////////////////////////////////////////

  /**
   * Removes all decompositions
   */
  public static void removeAllDecomposition(DecompositionTargetViewer viewer) {
    DecompositionModel model = viewer.getDecompositionModel();
    CTabFolder tabFolder = viewer.getTabFolder();
    if ((tabFolder == null) || tabFolder.isDisposed()) {
      return;
    }
    if (model.removeAllDecomposition()) {
      for (CTabItem item : tabFolder.getItems()) {
        item.dispose();
      }
      tabFolder.dispose();
      tabFolder = null;
      viewer.refreshItems(null);
    }
    addNewDecomposition(viewer);
    viewer.setDecompositionFieldsEnabled(model.getDecompositions().size());
  }

  /*
   * Removes all target components
   */
  public static void removeAllTargetComponent(DecompositionTargetViewer viewer) {
    CTabFolder folder = viewer.getTabFolder();
    DecompositionModel model = viewer.getDecompositionModel();
    CTabItem item = folder.getSelection();
    TreeViewer treeViewer = (TreeViewer) item.getData(IDecompositionDataConstants.TARGET_TREEVIEWER_DATA);
    Decomposition decomposition = (Decomposition) item.getData();
    if (model.removeAllTargetComponents(decomposition)) {
      viewer.refreshItems(treeViewer);
    }

  }

  /**
   * Removes the selected decomposition
   */
  public static void removeSelectedDecomposition(DecompositionTargetViewer viewer) {
    DecompositionModel model = viewer.getDecompositionModel();
    CTabFolder tabFolder = viewer.getTabFolder();
    if ((tabFolder == null) || tabFolder.isDisposed()) {
      return;
    }
    Decomposition decomposition = (Decomposition) tabFolder.getSelection().getData();
    if (model.removeDecomposition(decomposition)) {
      tabFolder.getSelection().dispose();
      viewer.refreshItems(null);
      if (model.getDecompositions().size() == 1) {
        viewer.setDecompositionName(false);
      }
    }
    if (tabFolder.getItemCount() == 0) {
      tabFolder.dispose();
      tabFolder = null;
      viewer.refreshItems(null);
      addNewDecomposition(viewer);
    }
    viewer.setDecompositionFieldsEnabled(model.getDecompositions().size());
  }

  /*
   * Removes the selected target component
   */
  public static void removeSelectedTargetComponent(DecompositionTargetViewer viewer) {
    CTabFolder folder = viewer.getTabFolder();
    DecompositionModel model = viewer.getDecompositionModel();
    CTabItem item = folder.getSelection();
    TreeViewer treeViewer = (TreeViewer) item.getData(IDecompositionDataConstants.TARGET_TREEVIEWER_DATA);
    ITreeSelection selection = (ITreeSelection) treeViewer.getSelection();
    if (selection.isEmpty()) {
      return;
    }
    List<?> list = selection.toList();
    for (Object data : list) {
      Decomposition decomposition = (Decomposition) item.getData();
      if (model.isRemoveAllowed(data)) {
        if (data instanceof DecompositionComponent) {
          DecompositionComponent comp = (DecompositionComponent) data;
          if (model.removeTargetComponent(decomposition, comp)) {
            if (comp.isReusedComponent()) {
              viewer.addShortcutInput((CapellaElement) comp.getReusedTarget());
            }
            viewer.refreshItems(treeViewer);
          }
        } else if (data instanceof DecompositionItem) {
          // Add remove functionality for Internal Interface
          DecompositionItem itemItf = (DecompositionItem) data;
          model.addDecompositionItemRemoved(itemItf);
          model.detachInterface(itemItf.getParentComponent(), itemItf);
          viewer.refreshItems(treeViewer);
        }

      }
      viewer.setRemoveTCButtonEnabled(!decomposition.getTargetComponents().isEmpty());
    }
  }

  /**
   * Renames the selected decomposition
   */
  public static void renameSelectedDecomposition(DecompositionTargetViewer viewer) {
    DecompositionModel model = viewer.getDecompositionModel();
    Text text = viewer.getDecompNameText();
    CTabFolder tabFolder = viewer.getTabFolder();
    String txt = text.getText();
    if ((txt == null) || (txt.trim().length() == 0)) {
      return;
    }
    if ((tabFolder == null) || tabFolder.isDisposed()) {
      return;
    }
    Decomposition decomposition = (Decomposition) tabFolder.getSelection().getData();
    if (model.renameDecomposition(decomposition, txt)) {
      tabFolder.getSelection().setText(txt);
      text.setText(""); //$NON-NLS-1$
    }
  }

  /*
   * Renames the selected target component
   */
  public static void renameTargetComponent(DecompositionTargetViewer viewer) {
    CTabFolder folder = viewer.getTabFolder();
    DecompositionModel model = viewer.getDecompositionModel();
    CTabItem item = folder.getSelection();
    DecompositionComponent dc = null;
    LogicalComponent lc = null;
    TreeViewer treeViewer = (TreeViewer) item.getData(IDecompositionDataConstants.TARGET_TREEVIEWER_DATA);
    ITreeSelection selection = (ITreeSelection) treeViewer.getSelection();
    if (selection.isEmpty()) {
      return;
    }

    Object data = selection.getFirstElement();
    Object part = null;
    if (data instanceof DecompositionComponent) {
      dc = (DecompositionComponent) data;
      Object value = dc.getValue();
      if (null != value) {
        if (value instanceof LogicalComponent) {
          lc = (LogicalComponent) value;
          if ((lc.getAbstractTypedElements() != null) && !lc.getAbstractTypedElements().isEmpty()) {
            part = lc.getAbstractTypedElements().get(0);
            dc.setName(((AbstractTypedElement) part).getName());
            dc.setTrigger(true);
          } else {
            dc.setName(lc.getName());
            dc.setTrigger(true);
          }
        }
      }
    }

    if (model.isRenameAllowed(data)) {
      ((DecompositionTreeCellModifier) treeViewer.getCellModifier()).setEnabled(true);
      treeViewer.editElement(data, 0);
    }
  }

  /*
   * Reuses a TargetComponent
   */
  public static void reuseComponent(DecompositionTargetViewer viewer) {
    CTabFolder folder = viewer.getTabFolder();
    DecompositionModel model = viewer.getDecompositionModel();
    CTabItem item = folder.getSelection();
    TreeViewer treeViewer = (TreeViewer) item.getData(IDecompositionDataConstants.TARGET_TREEVIEWER_DATA);
    Object obj = item.getData(IDecompositionDataConstants.REUSE_COMP_DATA);

    if (obj != null) {
      Decomposition decomposition = (Decomposition) item.getData();
      if (model.reuseTargetComponent(decomposition, obj)) {
        viewer.removeShortcutInput(obj);
        viewer.refreshItems(treeViewer);
      }
    }
  }

}
