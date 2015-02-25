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
package org.polarsys.capella.core.ui.metric.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeColumn;
import org.polarsys.capella.common.ui.toolkit.dialogs.AbstractExportDialog;
import org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.IViewerStyle;
import org.polarsys.capella.common.ui.toolkit.viewers.TreeAndListViewer;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataContentProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.ui.metric.MetricMessages;
import org.polarsys.capella.core.ui.properties.annotations.IRepresentationAnnotationConstants;

/**
 */
public class ProgressMonitoringOverviewDialog extends AbstractExportDialog {

  private CCombo _combo;
  private EObject _root = null;

  /**
   * @param parentShell
   * @param dialogTitle_p
   * @param dialogMessage_p
   * @param root_p
   * @param elements_p
   */
  public ProgressMonitoringOverviewDialog(Shell parentShell, String dialogTitle_p, String dialogMessage_p, EObject root_p, List<? extends EObject> elements_p) {
    super(parentShell, dialogTitle_p, dialogMessage_p, dialogTitle_p);

    _root = root_p;
    setData(new TreeData(elements_p, null));
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractViewerDialog#doCreateDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void doCreateDialogArea(Composite parent_p) {
    // Create a composing composite.
    Composite containingComposite = new Composite(parent_p, SWT.NONE);
    containingComposite.setLayout(new GridLayout(1, true));
    containingComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
    
    createStatusArea(containingComposite);
    
    // Create the viewer area.
    super.doCreateDialogArea(containingComposite);
  }

  /**
   * @param parent_p
   */
  protected void createStatusArea(Composite parent_p) {
    final int initialSelection = 0;

    Composite comp = new Composite(parent_p, SWT.NONE);
    comp.setLayout(new GridLayout(2, false));
    comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    
    Label lbl = new Label(comp, SWT.NONE);
    lbl.setText(MetricMessages.progressMonitoring_dialog_combo_lbl);
    
    _combo = new CCombo(comp, SWT.NONE | SWT.READ_ONLY | SWT.BORDER);

    GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
    _combo.setLayoutData(gd);
    _combo.add(MetricMessages.progressMonitoring_dialog_combo_allStatus);
    _combo.setData(String.valueOf(initialSelection), getData());

    EnumerationPropertyType ept = CapellaProjectHelper.getEnumerationPropertyType(_root, CapellaProjectHelper.PROGRESS_STATUS_KEYWORD);

    int i = 0;
    int i0 = 0;
    for (EnumerationPropertyLiteral enumz: ept.getOwnedLiterals()) {
      _combo.add(enumz.getLabel());
      _combo.setData(String.valueOf(++i), enumz);
    }
    
    _combo.select(i0);
    _combo.addSelectionListener(new SelectionAdapter() {
      @SuppressWarnings({ "synthetic-access", "unchecked" })
      @Override
      public void widgetSelected(SelectionEvent e) {
        int idx = _combo.getSelectionIndex();
        List<? extends EObject> allTaggedElements = (List<? extends EObject>) ((TreeData) getData()).getValidElements();
        List<EObject> list = null;
        String eAnnot= IRepresentationAnnotationConstants.ProgressStatus;
        
        if ( 0 == idx ) {
          list = (List<EObject>) allTaggedElements;
        } else {
          EnumerationPropertyLiteral lit = (EnumerationPropertyLiteral) _combo.getData(String.valueOf(idx));
          list = new ArrayList<EObject>();
          for (EObject eobj: allTaggedElements) {
            if ( (eobj instanceof CapellaElement) && ((CapellaElement)eobj).getStatus().equals(lit) ) {
              list.add(eobj);
            } else if (eobj instanceof DDiagram) {
            	DAnnotation dAnnotation= RepresentationHelper.getAnnotation(eAnnot, (DRepresentation) eobj);
            	if (dAnnotation.getDetails().get("value").equals(lit.getName())) {
            	list.add(eobj);
            	}
            }
          }
        }
        getViewer().setInput( new TreeData(list, null) );
      }
    });
  }
  
  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractMessageDialogWithViewer#createViewer(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected AbstractRegExpViewer createViewer(Composite parent_p) {
    // Create tree viewer.
    // Don't use the status bar of the viewer b
    TreeAndListViewer treeViewer = new TreeAndListViewer(parent_p, false, IViewerStyle.SHOW_STATUS_BAR);
    TreeViewer viewer = treeViewer.getClientViewer();
    TreeViewerColumn columnViewer = new TreeViewerColumn(viewer, SWT.LEFT | SWT.FILL);
    TreeColumn column = columnViewer.getColumn();
    column.setText(MetricMessages.progressMonitoring_dialog_header_col0);
    column.setWidth(300);
    
    columnViewer = new TreeViewerColumn(viewer, SWT.LEFT | SWT.FILL);
    column = columnViewer.getColumn();
    column.setText(MetricMessages.progressMonitoring_dialog_header_col1);
    column.setWidth(100);
    
    viewer.getTree().setLinesVisible(true);
    viewer.getTree().setHeaderVisible(true);
    viewer.setContentProvider(new DataContentProvider());
    viewer.setLabelProvider(new ProgressMonitoringLabelProvider(viewer, SWT.COLOR_BLUE));
    // Set layout data.
    viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
    viewer.setSorter(new ProgressMonitoringSorter());

    return treeViewer;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected List<String[]> getExportableData() {  	
    List<String[]> result = super.getExportableData(ProjectExt.getProject(_root).getName());

    @SuppressWarnings("unchecked")
    List<? extends EObject> allTaggedElements = (List<? extends EObject>) ((TreeData) getData()).getValidElements();

    for (EObject current: allTaggedElements) {
      if (current instanceof CapellaElement) {
    	CapellaElement me = (CapellaElement) current;
    	result.add(
    		new String[] {
    			current.eClass().getName(),
    			me.getFullLabel(),
    			me.getLabel(),
    			me.getStatus().getLabel()
    			}
    		);
      } else if (current instanceof DDiagram) {
    	  DDiagram dDiagram= (DDiagram) current;

    	String eAnnotStatus= IRepresentationAnnotationConstants.ProgressStatus;
    	DAnnotation dAnnotationStatus= RepresentationHelper.getAnnotation(eAnnotStatus, (DRepresentation) current);
    	result.add(
    		new String[] {
    			dDiagram.getName(),
    			//
    			//
    			dAnnotationStatus.getDetails().get("value")
    			  }
    	  );
      }
    }
    return result;
  }
}
