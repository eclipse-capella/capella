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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Execution;


/**
 *
 */
@SuppressWarnings("unused")
public class ExecutionExplorer extends Composite {

  private SashForm sashForm = null;
  private List list1 = null;
  private Composite composite = null;
  private Label label = null;
  private Text text = null;
  private Label label1 = null;
  private Text text1 = null;
  private ListViewer listViewer = null;
  
  java.util.List<Execution> _executions = new Vector<Execution>();
  private Text text2 = null;
  private Label label2 = null;
  private Text text3 = null;
  private Label label3 = null;
  private Button checkBox = null;
  private Label label4 = null;
  private Text text4 = null;
private Label label5 = null;
private Text text5 = null;
public ExecutionExplorer(Composite parent, int style) {
    super(parent, style);
    initialize();
    
    // -- Weights
    int[] weights = new int[2];
    weights[0] = 30;
    weights[1] = 60;
    sashForm.setWeights(weights); 
    // --
    
    list1.addSelectionListener(new SelectionAdapter() {
      @SuppressWarnings("synthetic-access")
      @Override
      public void widgetSelected(SelectionEvent event) {
        int selectedItem = list1.getSelectionIndex();
        setCurrentExecution(_executions.get(selectedItem));
      }
    });
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
    createComposite();
  }
  
  /**
   * @param executionEnd
   */
  private void setCurrentExecution(Execution execution) {
    text.setText(execution.getName()==null?"null":execution.getName()); //$NON-NLS-1$
    text1.setText(execution.getId());
    AbstractEnd start = (AbstractEnd) execution.getStart();
    AbstractEnd finish = (AbstractEnd) execution.getFinish();
    text2.setText(start.getName()==null?"null":start.getName()); //$NON-NLS-1$
    text3.setText(finish.getName()==null?"null":finish.getName()); //$NON-NLS-1$
    checkBox.setSelection(start.getCovered() == finish.getCovered());
    text4.setText(start.getCovered().getName()==null?"null":start.getCovered().getName()); //$NON-NLS-1$
    text5.setText(start.getCovered().getId());
  }

  /**
   * This method initializes composite  
   *
   */
  private void createComposite() {
    GridData gridData14 = new GridData();
    gridData14.grabExcessHorizontalSpace = false;
    gridData14.verticalAlignment = GridData.CENTER;
    gridData14.horizontalAlignment = GridData.FILL;
    GridData gridData5 = new GridData();
    gridData5.horizontalAlignment = GridData.FILL;
    gridData5.verticalAlignment = GridData.CENTER;
    GridData gridData4 = new GridData();
    gridData4.horizontalAlignment = GridData.FILL;
    gridData4.grabExcessHorizontalSpace = true;
    gridData4.grabExcessVerticalSpace = false;
    gridData4.verticalAlignment = GridData.CENTER;
    GridData gridData3 = new GridData();
    gridData3.horizontalAlignment = GridData.FILL;
    gridData3.verticalAlignment = GridData.CENTER;
    GridData gridData2 = new GridData();
    gridData2.horizontalAlignment = GridData.FILL;
    gridData2.verticalAlignment = GridData.CENTER;
    GridData gridData13 = new GridData();
    gridData13.horizontalAlignment = GridData.FILL;
    gridData13.verticalAlignment = GridData.CENTER;
    GridData gridData = new GridData();
    gridData.widthHint = 100;
    GridData gridData12 = new GridData();
    gridData12.grabExcessHorizontalSpace = true;
    gridData12.verticalAlignment = GridData.CENTER;
    gridData12.horizontalAlignment = GridData.FILL;
    GridData gridData11 = new GridData();
    gridData11.grabExcessHorizontalSpace = true;
    gridData11.verticalAlignment = GridData.CENTER;
    gridData11.horizontalAlignment = GridData.FILL;
    GridData gridData10 = new GridData();
    gridData10.grabExcessHorizontalSpace = true;
    gridData10.verticalAlignment = GridData.CENTER;
    gridData10.horizontalAlignment = GridData.FILL;
    GridData gridData9 = new GridData();
    gridData9.grabExcessHorizontalSpace = true;
    gridData9.verticalAlignment = GridData.CENTER;
    gridData9.horizontalAlignment = GridData.FILL;
    GridLayout gridLayout7 = new GridLayout();
    gridLayout7.numColumns = 2;
    composite = new Composite(sashForm, SWT.NONE);
    composite.setEnabled(false);
    composite.setLayout(gridLayout7);
    label = new Label(composite, SWT.NONE);
    label.setText("Name"); //$NON-NLS-1$
    text = new Text(composite, SWT.BORDER);
    text.setLayoutData(gridData4);
    text.setLayoutData(gridData12);
    label1 = new Label(composite, SWT.NONE);
    label1.setText("CapellaId"); //$NON-NLS-1$
    text1 = new Text(composite, SWT.BORDER);
    text1.setLayoutData(gridData3);
    text1.setLayoutData(gridData11);
    label2 = new Label(composite, SWT.NONE);
    label2.setText("Start"); //$NON-NLS-1$
    label2.setLayoutData(gridData);
    text2 = new Text(composite, SWT.BORDER);
    text2.setLayoutData(gridData2);
    text2.setLayoutData(gridData10);
    label3 = new Label(composite, SWT.NONE);
    label3.setText("Finish"); //$NON-NLS-1$
    text3 = new Text(composite, SWT.BORDER);
    text3.setLayoutData(gridData13);
    text3.setLayoutData(gridData9);
    Label filler = new Label(composite, SWT.NONE);
    checkBox = new Button(composite, SWT.CHECK);
    checkBox.setText("On the same instance role"); //$NON-NLS-1$
    label4 = new Label(composite, SWT.NONE);
    label4.setText("Instance Role"); //$NON-NLS-1$
    text4 = new Text(composite, SWT.BORDER);
    text4.setLayoutData(gridData5);
    label5 = new Label(composite, SWT.NONE);
    label5.setText("CapellaId"); //$NON-NLS-1$
    text5 = new Text(composite, SWT.BORDER);
    text5.setLayoutData(gridData14);
  }

  /**
   * @param value
   */
  public void addExecution(Execution value) {
    list1.add((value.getName()==null?"null":value.getName())); //$NON-NLS-1$
    _executions.add(value);
  }
}
