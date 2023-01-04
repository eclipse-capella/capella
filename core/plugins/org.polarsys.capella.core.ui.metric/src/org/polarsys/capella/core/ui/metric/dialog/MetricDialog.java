/*******************************************************************************
 * Copyright (c) 2006, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.metric.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeColumn;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.toolkit.dialogs.AbstractExportDialog;
import org.polarsys.capella.common.ui.toolkit.dialogs.AbstractViewerDialog;
import org.polarsys.capella.common.ui.toolkit.dialogs.IExportConstants;
import org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.RegExpTreeViewer;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.ui.metric.MetricMessages;
import org.polarsys.capella.core.ui.metric.core.MetricTree;

/**
 * Dialog in order to display "metrics" result on model
 */
public class MetricDialog extends AbstractExportDialog {

  protected String resourceName;
  private MetricLabelProvider labelProvider = new MetricLabelProvider();

  /**
   * Constructor
   * 
   * @param parentShell
   * @param title
   * @param message
   * @param shellTitle
   * @see AbstractViewerDialog#AbstractViewerDialog(Shell, String, String, String)
   */
  public MetricDialog(Shell parentShell, String title, String message, String shellTitle) {
    super(parentShell, title, message, shellTitle);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected List<String[]> getExportableData() {
    List<String[]> result = super.getExportableData(resourceName);
    List<MetricTree<EObject>> ses = new ArrayList<>();

    @SuppressWarnings("unchecked")
    MetricTree<EObject> data = (MetricTree<EObject>) getData();

    EClass eRoot = data.getElement().eClass();
    if (CapellacorePackage.Literals.MODELLING_ARCHITECTURE.isSuperTypeOf(eRoot)) {
      result.addAll(serializeLayerNodes(Collections.singletonList(data)));
    } else {
      if (eRoot == CapellamodellerPackage.Literals.PROJECT) {
        ses.addAll(data.getChildren());
      } else if (eRoot == CapellamodellerPackage.Literals.SYSTEM_ENGINEERING) {
        ses.add(data);
      }
      result.addAll(serializeSysEngNodes(ses));
    }

    return result;
  }

  private List<String[]> serializeSysEngNodes(List<MetricTree<EObject>> nodes) {
    List<String[]> result = new ArrayList<>();

    for (MetricTree<EObject> node : nodes) {
      result.add(IExportConstants.EXPORT_EMPTY_LINE);
      result.add(new String[] { node.getElement().eClass().getName() + ICommonConstants.COLON_CHARACTER
          + labelProvider.getColumnText(node, MetricLabelProvider.OBJ_COLUMN_INDEX) });
      result.add(IExportConstants.EXPORT_EMPTY_LINE);
      result.addAll(serializeLayerNodes(node.getChildren()));
    }

    return result;
  }

  private List<String[]> serializeLayerNodes(List<MetricTree<EObject>> nodes) {
    List<String[]> result = new ArrayList<>();

    for (MetricTree<EObject> node : nodes) {
      result.add(IExportConstants.EXPORT_EMPTY_LINE);
      result.add(new String[] { MetricMessages.layer + ICommonConstants.COLON_CHARACTER
          + labelProvider.getColumnText(node, MetricLabelProvider.OBJ_COLUMN_INDEX) });
      result.add(IExportConstants.EXPORT_EMPTY_LINE);
      for (MetricTree<EObject> node2 : node.getChildren()) {
        result.add(new String[] { labelProvider.getColumnText(node2, MetricLabelProvider.OBJ_COLUMN_INDEX),
            Integer.toString(node2.getCount()) });
      }
    }

    return result;
  }

  /**
   * Create a 2 level tree.
   * 
   * @param parent
   *          the parent composite
   */
  @Override
  protected AbstractRegExpViewer createViewer(Composite parent) {
    RegExpTreeViewer treeViewer = new RegExpTreeViewer(parent);
    TreeViewer viewer = treeViewer.getClientViewer();

    TreeViewerColumn columnViewer = new TreeViewerColumn(viewer, SWT.LEFT);
    TreeColumn column = columnViewer.getColumn();
    column.setText(MetricMessages.treeObjectColumnLabel);
    column.setWidth(300);

    ViewerComparator vc = new ViewerComparator() {
      /**
       * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object,
       *      java.lang.Object)
       */
      @SuppressWarnings("unchecked")
      @Override
      public int compare(Viewer viewer, Object e1, Object e2) {
        int result = 0;
        MetricTree<EObject> node1 = (MetricTree<EObject>) e1;
        MetricTree<EObject> node2 = (MetricTree<EObject>) e2;

        if (!node1.hasChildren()) {
          String lbl1 = labelProvider.getColumnText(node1, MetricLabelProvider.OBJ_COLUMN_INDEX);
          String lbl2 = labelProvider.getColumnText(node2, MetricLabelProvider.OBJ_COLUMN_INDEX);
          result = lbl1.compareTo(lbl2);
        }

        return result;
      }
    };

    viewer.setComparator(vc);

    columnViewer = new TreeViewerColumn(viewer, SWT.LEFT | SWT.FILL);
    column = columnViewer.getColumn();
    column.setText(MetricMessages.treeResultColumnLabel);
    column.setWidth(70);

    viewer.getTree().setLinesVisible(true);
    viewer.getTree().setHeaderVisible(true);
    viewer.setContentProvider(new MetricContentProvider());
    viewer.setLabelProvider(labelProvider);

    return treeViewer;
  }

  public void setResourceName(String name) {
    resourceName = name;
  }
  
  @Override
  protected String getDefaultFileName() {
    return MetricMessages.metricDialog_default_filename;
  }
}
