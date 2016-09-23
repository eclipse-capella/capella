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

import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

/**
 *
 */
@SuppressWarnings({"nls", "unused"})
public class MessageExplorer extends Composite {

  private SashForm sashForm = null;
  private List list1 = null;
  private Composite composite = null;
  private Label label = null;
  private Text text = null;
  private Label label1 = null;
  private Text text1 = null;
  private ListViewer listViewer = null;
  
  java.util.List<SequenceMessage> _messages = new Vector<SequenceMessage>();
  private Text text2 = null;
  private Label label2 = null;
  private Text text3 = null;
  private Label label3 = null;
  private Text text4 = null;
  private Label label4 = null;
  public MessageExplorer(Composite parent, int style) {
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
    this.setLayout(new FillLayout());
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

  public void addMessage(SequenceMessage message) {
    list1.add(message.getName());
    _messages.add(message);
  }

  public void setCurrentMessage(SequenceMessage message) {
    text.setText(message.getName());
    text1.setText(message.getId());
    text2.setText(message.getKind().toString());
    MessageEnd sendingEnd = message.getSendingEnd();
    MessageEnd receivingEnd = message.getReceivingEnd();
    text3.setText(((sendingEnd.getName()==null?sendingEnd.getCovered().getName():sendingEnd.getName())));
    text4.setText(((receivingEnd.getName()==null?receivingEnd.getCovered().getName():receivingEnd.getName())));
  }
  
  /**
   * This method initializes sashForm	
   *
   */
  private void createSashForm() {
    sashForm = new SashForm(this, SWT.BORDER);
    list1 = new List(sashForm, SWT.V_SCROLL);
    list1.setEnabled(true);
    listViewer = new ListViewer(list1);
    list1.addSelectionListener(new SelectionAdapter() {
      @SuppressWarnings("synthetic-access")
      @Override
      public void widgetSelected(SelectionEvent event) {
        int selectedItem = list1.getSelectionIndex();
        setCurrentMessage(_messages.get(selectedItem));
      }
    });
    
    createComposite();
  }

  /**
   * This method initializes composite	
   *
   */
  private void createComposite() {
    GridData gridData6 = new GridData();
    gridData6.horizontalAlignment = GridData.FILL;
    gridData6.verticalAlignment = GridData.CENTER;
    GridData gridData5 = new GridData();
    gridData5.horizontalAlignment = GridData.FILL;
    gridData5.verticalAlignment = GridData.CENTER;
    GridData gridData4 = new GridData();
    gridData4.horizontalAlignment = GridData.FILL;
    gridData4.verticalAlignment = GridData.CENTER;
    GridData gridData31 = new GridData();
    gridData31.horizontalAlignment = GridData.FILL;
    gridData31.verticalAlignment = GridData.CENTER;
    GridData gridData21 = new GridData();
    gridData21.horizontalAlignment = GridData.FILL;
    gridData21.verticalAlignment = GridData.CENTER;
    GridData gridData12 = new GridData();
    gridData12.grabExcessHorizontalSpace = false;
    gridData12.verticalAlignment = GridData.CENTER;
    gridData12.horizontalAlignment = GridData.FILL;
    GridData gridData3 = new GridData();
    gridData3.widthHint = 200;
    GridData gridData2 = new GridData();
    gridData2.horizontalAlignment = GridData.FILL;
    gridData2.verticalAlignment = GridData.CENTER;
    GridData gridData11 = new GridData();
    gridData11.grabExcessHorizontalSpace = true;
    gridData11.verticalAlignment = GridData.CENTER;
    gridData11.horizontalAlignment = GridData.FILL;
    GridData gridData = new GridData();
    gridData.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
    gridData.widthHint = 50;
    gridData.heightHint = 18;
    GridLayout gridLayout = new GridLayout();
    gridLayout.numColumns = 2;
    composite = new Composite(sashForm, SWT.NONE);
    composite.setLayout(gridLayout);
    label = new Label(composite, SWT.NONE);
    label.setLayoutData(gridData);
    label.setText("Name");
    text = new Text(composite, SWT.BORDER);
    text.setLayoutData(gridData2);
    label1 = new Label(composite, SWT.NONE);
    label1.setText("CapellaId");
    label1.setLayoutData(gridData3);
    text1 = new Text(composite, SWT.BORDER);
    text1.setLayoutData(gridData11);
    label2 = new Label(composite, SWT.NONE);
    label2.setText("Kind");
    label2.setLayoutData(gridData21);
    text2 = new Text(composite, SWT.BORDER);
    text2.setLayoutData(gridData12);
    label3 = new Label(composite, SWT.NONE);
    label3.setText("Sending Message End RI");
    label3.setLayoutData(gridData5);
    text3 = new Text(composite, SWT.BORDER);
    text3.setLayoutData(gridData31);
    label4 = new Label(composite, SWT.NONE);
    label4.setText("Receiving Message End RI");
    label4.setLayoutData(gridData6);
    text4 = new Text(composite, SWT.BORDER);
    text4.setLayoutData(gridData4);
  }
}
