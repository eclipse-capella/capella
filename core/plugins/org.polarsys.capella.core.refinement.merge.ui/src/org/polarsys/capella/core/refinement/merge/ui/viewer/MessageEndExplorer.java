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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 *
 */
@SuppressWarnings({"nls", "unused"})
public class MessageEndExplorer extends Composite {

  private SashForm sashForm = null;
  private List list1 = null;
  private Composite composite = null;
  private Label label = null;
  private Text _name = null;
  private Label label1 = null;
  private Text _capellaId = null;
  private ListViewer listViewer = null;
  
  java.util.List<AbstractEnd> _messageEnds = new Vector<AbstractEnd>();
  private Text _instanceRole = null;
  private Label label2 = null;
  private Text _representedInstance = null;
  private Label label3 = null;
  private Text _representedComponent = null;
  private Label label4 = null;
  private Button _isMessageEnd = null;
  private Group _other = null;
  private Text _otherName = null;
  private Group _message = null;
  private Label label5 = null;
  private Text _messageName = null;
  private Label label7 = null;
  private Text _otherInstanceRole = null;
  private Label label6 = null;
  private Label label8 = null;
  private Label label9 = null;
  private Text _otherRepresentedInstance = null;
  private Text _otherRepresentedComponent = null;
  private Button _otherIsSending = null;
  private Button _isSendingEnd = null;
  public MessageEndExplorer(Composite parent, int style) {
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
    this.setLayout(new FillLayout());
    setSize(new Point(898, 538));
    createSashForm();
  }

  public void setCurrentMessageEnd(MessageEnd messageEnd_p) {
    _name.setText(messageEnd_p.getName()==null?"null":messageEnd_p.getName());
    _capellaId.setText(messageEnd_p.getId());
    _instanceRole.setText(messageEnd_p.getCovered().getName());
    _representedInstance.setText(messageEnd_p.getCovered().getRepresentedInstance().getName());
    _representedComponent.setText(((AbstractNamedElement) ((Part) messageEnd_p.getCovered().getRepresentedInstance()).eContainer()).getName());
    SequenceMessage message = messageEnd_p.getMessage();
    _messageName.setText(message.getName());
    boolean isSendingEnd = message.getSendingEnd() == messageEnd_p;
    _isSendingEnd.setSelection(isSendingEnd);
    MessageEnd other = isSendingEnd?message.getReceivingEnd():message.getSendingEnd();
    _otherName.setText(other.getName()==null?"null":other.getName());
    _otherInstanceRole.setText(other.getCovered().getName()==null?"null":other.getCovered().getName());
    _otherIsSending.setSelection(!isSendingEnd);
    _otherRepresentedInstance.setText(other.getCovered().getRepresentedInstance().getName());
    _otherRepresentedComponent.setText(((AbstractNamedElement) ((Part) other.getCovered().getRepresentedInstance()).eContainer()).getName());
    _message.setVisible(true);
    _other.setVisible(true);   
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
        setCurrentAbstractEnd(_messageEnds.get(selectedItem));
      }
    });    
    createComposite();
  }
  
  private void setCurrentAbstractEnd(AbstractEnd end_p) {
    if (end_p instanceof MessageEnd) {
      MessageEnd messageEnd = (MessageEnd) end_p;
      setCurrentMessageEnd(messageEnd);
      _isMessageEnd.setSelection(true);
    }
    else
    {
      ExecutionEnd executionEnd = (ExecutionEnd) end_p;
      setCurrentExecutionEnd(executionEnd);
      _isMessageEnd.setSelection(false);
    }
    
  }
  
  /**
   * @param executionEnd_p
   */
  private void setCurrentExecutionEnd(ExecutionEnd executionEnd_p) {
    _name.setText(executionEnd_p.getName()==null?"null":executionEnd_p.getName());
    _capellaId.setText(executionEnd_p.getId());
    _instanceRole.setText(executionEnd_p.getCovered().getName());
    _representedInstance.setText(executionEnd_p.getCovered().getRepresentedInstance().getName());
    _representedComponent.setText(((AbstractNamedElement) ((Part) executionEnd_p.getCovered().getRepresentedInstance()).eContainer()).getName());
    _message.setVisible(false);
    _other.setVisible(false);
    
  }

  /**
   * This method initializes composite  
   *
   */
  private void createComposite() {
    GridData gridData24 = new GridData();
    gridData24.widthHint = 160;
    GridData gridData23 = new GridData();
    gridData23.widthHint = 160;
    GridData gridData22 = new GridData();
    gridData22.grabExcessHorizontalSpace = false;
    gridData22.widthHint = 160;
    GridData gridData19 = new GridData();
    gridData19.widthHint = 160;
    GridData gridData7 = new GridData();
    gridData7.horizontalAlignment = GridData.BEGINNING;
    gridData7.widthHint = 160;
    gridData7.verticalAlignment = GridData.CENTER;
    GridData gridData13 = new GridData();
    gridData13.grabExcessHorizontalSpace = true;
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
    GridData gridData8 = new GridData();
    gridData8.horizontalAlignment = GridData.FILL;
    gridData8.grabExcessHorizontalSpace = true;
    gridData8.verticalAlignment = GridData.CENTER;
    GridLayout gridLayout7 = new GridLayout();
    gridLayout7.numColumns = 2;
    composite = new Composite(sashForm, SWT.NONE);
    composite.setEnabled(false);
    composite.setLayout(gridLayout7);
    label = new Label(composite, SWT.NONE);
    label.setText("Name");
    label.setLayoutData(gridData24);
    _name = new Text(composite, SWT.BORDER);
    _name.setLayoutData(gridData12);
    label1 = new Label(composite, SWT.NONE);
    label1.setText("CapellaId");
    label1.setLayoutData(gridData23);
    _capellaId = new Text(composite, SWT.BORDER);
    _capellaId.setLayoutData(gridData11);
    Label filler = new Label(composite, SWT.NONE);
    _isMessageEnd = new Button(composite, SWT.CHECK);
    _isMessageEnd.setText("MessageEnd=1/ExecutionEnd=0");
    _isMessageEnd.setLayoutData(gridData13);
    label2 = new Label(composite, SWT.NONE);
    label2.setText("Instance Role");
    label2.setLayoutData(gridData22);
    _instanceRole = new Text(composite, SWT.BORDER);
    _instanceRole.setLayoutData(gridData10);
    label3 = new Label(composite, SWT.NONE);
    label3.setText("Represented Instance");
    label3.setLayoutData(gridData19);
    _representedInstance = new Text(composite, SWT.BORDER);
    _representedInstance.setLayoutData(gridData9);
    label4 = new Label(composite, SWT.NONE);
    label4.setText("Represented Component");
    label4.setLayoutData(gridData7);
    _representedComponent = new Text(composite, SWT.BORDER);
    _representedComponent.setLayoutData(gridData8);
    Label filler53 = new Label(composite, SWT.NONE);
    _isSendingEnd = new Button(composite, SWT.CHECK);
    _isSendingEnd.setText("Sending=1/Receiving=0");
    create_other();
    create_message();
  }

  /**
   * @param value_p
   */
  public void addAbstractEnd(AbstractEnd value_p) {
    boolean isMessageEnd = value_p instanceof MessageEnd;
    list1.add((isMessageEnd?"[M]":"[E]") 
              + (value_p.getName()==null?"null":value_p.getName()));
    _messageEnds.add(value_p);
    
  }

  /**
   * This method initializes _other	
   *
   */
  private void create_other() {
    GridData gridData21 = new GridData();
    gridData21.widthHint = 160;
    gridData21.grabExcessHorizontalSpace = false;
    GridData gridData20 = new GridData();
    gridData20.widthHint = 160;
    GridData gridData18 = new GridData();
    gridData18.grabExcessHorizontalSpace = true;
    gridData18.verticalAlignment = GridData.CENTER;
    gridData18.horizontalAlignment = GridData.FILL;
    GridData gridData17 = new GridData();
    gridData17.grabExcessHorizontalSpace = true;
    gridData17.verticalAlignment = GridData.CENTER;
    gridData17.horizontalAlignment = GridData.FILL;
    GridData gridData16 = new GridData();
    gridData16.grabExcessHorizontalSpace = true;
    gridData16.verticalAlignment = GridData.CENTER;
    gridData16.horizontalAlignment = GridData.FILL;
    GridData gridData15 = new GridData();
    gridData15.grabExcessHorizontalSpace = false;
    gridData15.verticalAlignment = GridData.CENTER;
    gridData15.widthHint = 160;
    gridData15.horizontalAlignment = GridData.BEGINNING;
    GridData gridData14 = new GridData();
    gridData14.grabExcessHorizontalSpace = false;
    gridData14.verticalAlignment = GridData.CENTER;
    gridData14.widthHint = 160;
    gridData14.horizontalAlignment = GridData.BEGINNING;
    GridData gridData4 = new GridData();
    gridData4.grabExcessHorizontalSpace = true;
    gridData4.verticalAlignment = GridData.CENTER;
    gridData4.horizontalAlignment = GridData.FILL;
    GridData gridData3 = new GridData();
    gridData3.horizontalAlignment = GridData.FILL;
    gridData3.grabExcessHorizontalSpace = true;
    gridData3.verticalAlignment = GridData.CENTER;
    GridLayout gridLayout6 = new GridLayout();
    gridLayout6.numColumns = 2;
    GridData gridData2 = new GridData();
    gridData2.horizontalAlignment = GridData.FILL;
    gridData2.horizontalSpan = 2;
    gridData2.grabExcessHorizontalSpace = true;
    gridData2.verticalAlignment = GridData.CENTER;
    _other = new Group(composite, SWT.NONE);
    _other.setText("Other end");
    _other.setLayout(gridLayout6);
    _other.setLayoutData(gridData2);
    label7 = new Label(_other, SWT.NONE);
    label7.setText("Name");
    label7.setLayoutData(gridData15);
    _otherName = new Text(_other, SWT.BORDER);
    _otherName.setLayoutData(gridData3);
    label6 = new Label(_other, SWT.NONE);
    label6.setText("Instance Role");
    label6.setLayoutData(gridData14);
    _otherInstanceRole = new Text(_other, SWT.BORDER);
    _otherInstanceRole.setLayoutData(gridData4);
    label8 = new Label(_other, SWT.NONE);
    label8.setText("Represented Instance");
    label8.setLayoutData(gridData20);
    _otherRepresentedInstance = new Text(_other, SWT.BORDER);
    _otherRepresentedInstance.setLayoutData(gridData16);
    label9 = new Label(_other, SWT.NONE);
    label9.setText("Represented Component");
    label9.setLayoutData(gridData21);
    _otherRepresentedComponent = new Text(_other, SWT.BORDER);
    _otherRepresentedComponent.setLayoutData(gridData17);
    Label filler1 = new Label(_other, SWT.NONE);
    _otherIsSending = new Button(_other, SWT.CHECK);
    _otherIsSending.setText("Sending=1/Receiving=0");
    _otherIsSending.setLayoutData(gridData18);
  }

  /**
   * This method initializes _message	
   *
   */
  private void create_message() {
    GridData gridData6 = new GridData();
    gridData6.widthHint = 160;
    gridData6.horizontalAlignment = GridData.BEGINNING;
    gridData6.verticalAlignment = GridData.CENTER;
    gridData6.grabExcessHorizontalSpace = false;
    GridData gridData = new GridData();
    gridData.grabExcessHorizontalSpace = true;
    gridData.horizontalAlignment = GridData.FILL;
    gridData.verticalAlignment = GridData.CENTER;
    gridData.grabExcessVerticalSpace = false;
    gridData.widthHint = -1;
    GridLayout gridLayout1 = new GridLayout();
    gridLayout1.numColumns = 2;
    GridData gridData5 = new GridData();
    gridData5.horizontalAlignment = GridData.FILL;
    gridData5.heightHint = -1;
    gridData5.horizontalSpan = 2;
    gridData5.grabExcessHorizontalSpace = false;
    gridData5.verticalAlignment = GridData.CENTER;
    _message = new Group(composite, SWT.NONE);
    _message.setText("Message");
    _message.setLayout(gridLayout1);
    _message.setLayoutData(gridData5);
    label5 = new Label(_message, SWT.NONE);
    label5.setText("Name");
    label5.setLayoutData(gridData6);
    _messageName = new Text(_message, SWT.BORDER);
    _messageName.setLayoutData(gridData);
  }
}
