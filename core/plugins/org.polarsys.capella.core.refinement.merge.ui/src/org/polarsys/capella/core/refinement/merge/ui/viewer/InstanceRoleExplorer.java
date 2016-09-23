/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.merge.ui.viewer;

import java.util.Vector;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import org.polarsys.capella.core.data.interaction.InstanceRole;

/**
 *
 */
@SuppressWarnings({"nls", "unused"})
public class InstanceRoleExplorer extends Composite {

  private SashForm sashForm = null;
  private List list1 = null;
  private Composite composite = null;
  private Label label = null;
  private Text text = null;
  private Label label1 = null;
  private Text text1 = null;
  private ListViewer listViewer = null;
  
  java.util.List<InstanceRole> _instanceRoles = new Vector<InstanceRole>();
  public InstanceRoleExplorer(Composite parent, int style) {
    super(parent, style);
    initialize();
    
    // -- Weights
    int[] weights = new int[2];
    weights[0] = 30;
    weights[1] = 60;
    sashForm.setWeights(weights); 
    // --
    
  }

  private void initialize() {
    GridData gridData1 = new GridData();
    gridData1.verticalSpan = 5;
    gridData1.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
    gridData1.grabExcessVerticalSpace = true;
    gridData1.grabExcessHorizontalSpace = true;
    gridData1.horizontalIndent = 0;
    gridData1.horizontalAlignment = GridData.FILL;
    this.setLocation(new Point(10, 10));
    this.setLayout(new FillLayout());
    setSize(new Point(678, 238));
    createSashForm();
  }
  
  /**
   * This method initializes sashForm 
   *
   */
  private void createSashForm() {
    sashForm = new SashForm(this, SWT.BORDER);
    list1 = new List(sashForm, SWT.V_SCROLL);
    listViewer = new ListViewer(list1);
    list1.addSelectionListener(new SelectionAdapter() {
      @SuppressWarnings("synthetic-access")
      @Override
      public void widgetSelected(SelectionEvent event) {
        int selectedItem = list1.getSelectionIndex();
        setCurrentInstanceRole(_instanceRoles.get(selectedItem));
      }


    });
    
    createComposite();
  }
  
  /**
   * @param executionEnd
   */
  private void setCurrentInstanceRole(InstanceRole instanceRole) {
    text.setText(instanceRole.getName()==null?"null":instanceRole.getName());
    text1.setText(instanceRole.getId());
  }

  /**
   * This method initializes composite  
   *
   */
  private void createComposite() {
    GridData gridData12 = new GridData();
    gridData12.grabExcessHorizontalSpace = true;
    gridData12.verticalAlignment = GridData.CENTER;
    gridData12.horizontalAlignment = GridData.FILL;
    GridData gridData11 = new GridData();
    gridData11.grabExcessHorizontalSpace = true;
    gridData11.verticalAlignment = GridData.CENTER;
    gridData11.horizontalAlignment = GridData.FILL;
    GridLayout gridLayout7 = new GridLayout();
    gridLayout7.numColumns = 2;
    composite = new Composite(sashForm, SWT.NONE);
    composite.setEnabled(false);
    composite.setLayout(gridLayout7);
    label = new Label(composite, SWT.NONE);
    label.setText("Name");
    text = new Text(composite, SWT.BORDER);
    text.setLayoutData(gridData12);
    label1 = new Label(composite, SWT.NONE);
    label1.setText("CapellaId");
    text1 = new Text(composite, SWT.BORDER);
    text1.setLayoutData(gridData11);
  }

  /**
   * @param value
   */
  public void addInstanceRole(InstanceRole value) {
    list1.add((value.getName()==null?"null":value.getName()));
    _instanceRoles.add(value);
  }
}
