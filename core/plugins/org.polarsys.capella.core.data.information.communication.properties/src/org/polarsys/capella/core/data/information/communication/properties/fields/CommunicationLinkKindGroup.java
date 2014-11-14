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
package org.polarsys.capella.core.data.information.communication.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.information.ExchangeMechanism;
import org.polarsys.capella.core.data.information.communication.CommunicationLinkKind;
import org.polarsys.capella.core.data.information.communication.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class CommunicationLinkKindGroup extends AbstractSemanticKindGroup {
  private Button _communicationLinkBtnSend;
  private Button _communicationLinkBtnReceive;
  private Button _communicationLinkBtnProduce;
  private Button _communicationLinkBtnConsume;
  private Button _communicationLinkBtnCall;
  private Button _communicationLinkBtnExecute;
  private Button _communicationLinkBtnAccess;
  private Button _communicationLinkBtnWrite;
  private Button _communicationLinkBtnAcquire;
  private Button _communicationLinkBtnTransmit;

  /**
   * Constructor.
   * @param parent_p
   * @param widgetFactory_p
   */
  public CommunicationLinkKindGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    super(parent_p, widgetFactory_p, Messages.getString("CommunicationLinkKind.Label"), 5); //$NON-NLS-1$

    _communicationLinkBtnSend = createButton(CommunicationLinkKind.SEND);
    _communicationLinkBtnProduce = createButton(CommunicationLinkKind.PRODUCE);
    _communicationLinkBtnCall = createButton(CommunicationLinkKind.CALL);
    _communicationLinkBtnWrite = createButton(CommunicationLinkKind.WRITE);
    _communicationLinkBtnTransmit = createButton(CommunicationLinkKind.TRANSMIT);
    _communicationLinkBtnReceive = createButton(CommunicationLinkKind.RECEIVE);
    _communicationLinkBtnConsume = createButton(CommunicationLinkKind.CONSUME);
    _communicationLinkBtnExecute = createButton(CommunicationLinkKind.EXECUTE);
    _communicationLinkBtnAccess = createButton(CommunicationLinkKind.ACCESS);
    _communicationLinkBtnAcquire = createButton(CommunicationLinkKind.ACQUIRE);
  }

  /**
   * @param exchangeMechanism_p
   */
  public void synchronizeKindsStatus(ExchangeMechanism exchangeMechanism_p) {
    if (exchangeMechanism_p != null) {
      switch (exchangeMechanism_p.getValue()) {
        case ExchangeMechanism.FLOW_VALUE:
          enableFlowKinds(true);
          enableEventKinds(false);
          enableOperationKinds(false);
          enableSharedDataKinds(false);
          enableUnsetKinds(false);
          break;
        case ExchangeMechanism.EVENT_VALUE:
          enableFlowKinds(false);
          enableEventKinds(true);
          enableOperationKinds(false);
          enableSharedDataKinds(false);
          enableUnsetKinds(false);
          break;
        case ExchangeMechanism.OPERATION_VALUE:
          enableFlowKinds(false);
          enableEventKinds(false);
          enableOperationKinds(true);
          enableSharedDataKinds(false);
          enableUnsetKinds(false);
          break;
        case ExchangeMechanism.SHARED_DATA_VALUE:
          enableFlowKinds(false);
          enableEventKinds(false);
          enableOperationKinds(false);
          enableSharedDataKinds(true);
          enableUnsetKinds(false);
          break;
        case ExchangeMechanism.UNSET_VALUE:
          enableFlowKinds(false);
          enableEventKinds(false);
          enableOperationKinds(false);
          enableSharedDataKinds(false);
          enableUnsetKinds(true);
          break;
      }
    } else {
      enableFlowKinds(false);
      enableEventKinds(false);
      enableOperationKinds(false);
      enableSharedDataKinds(false);
      enableUnsetKinds(false);
    }
    loadData(_semanticElement);
  }

  /**
   * @param enabled_p
   */
  protected void enableFlowKinds(boolean enabled_p) {
    enableButton(_communicationLinkBtnProduce, enabled_p);
    enableButton(_communicationLinkBtnConsume, enabled_p);
  }

  /**
   * @param enabled_p
   */
  protected void enableEventKinds(boolean enabled_p) {
    enableButton(_communicationLinkBtnSend, enabled_p);
    enableButton(_communicationLinkBtnReceive, enabled_p);
  }

  /**
   * @param enabled_p
   */
  protected void enableOperationKinds(boolean enabled_p) {
    enableButton(_communicationLinkBtnCall, enabled_p);
    enableButton(_communicationLinkBtnExecute, enabled_p);
  }

  /**
   * @param enabled_p
   */
  protected void enableSharedDataKinds(boolean enabled_p) {
    enableButton(_communicationLinkBtnWrite, enabled_p);
    enableButton(_communicationLinkBtnAccess, enabled_p);
  }

  /**
   * @param enabled_p
   */
  protected void enableUnsetKinds(boolean enabled_p) {
    enableButton(_communicationLinkBtnAcquire, enabled_p);
    enableButton(_communicationLinkBtnTransmit, enabled_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_communicationLinkBtnProduce);
    fields.add(_communicationLinkBtnConsume);
    fields.add(_communicationLinkBtnSend);
    fields.add(_communicationLinkBtnReceive);
    fields.add(_communicationLinkBtnCall);
    fields.add(_communicationLinkBtnExecute);
    fields.add(_communicationLinkBtnWrite);
    fields.add(_communicationLinkBtnAccess);
    fields.add(_communicationLinkBtnAcquire);
    fields.add(_communicationLinkBtnTransmit);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return null;
  }
}
