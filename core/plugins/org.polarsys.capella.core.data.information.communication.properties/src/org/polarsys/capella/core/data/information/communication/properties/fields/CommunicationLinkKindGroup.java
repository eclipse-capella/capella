/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
   * @param parent
   * @param widgetFactory
   */
  public CommunicationLinkKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(parent, widgetFactory, Messages.getString("CommunicationLinkKind.Label"), 5); //$NON-NLS-1$

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
   * @param exchangeMechanism
   */
  public void synchronizeKindsStatus(ExchangeMechanism exchangeMechanism) {
    if (exchangeMechanism != null) {
      switch (exchangeMechanism.getValue()) {
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
    loadData(semanticElement);
  }

  /**
   * @param enabled
   */
  protected void enableFlowKinds(boolean enabled) {
    enableButton(_communicationLinkBtnProduce, enabled);
    enableButton(_communicationLinkBtnConsume, enabled);
  }

  /**
   * @param enabled
   */
  protected void enableEventKinds(boolean enabled) {
    enableButton(_communicationLinkBtnSend, enabled);
    enableButton(_communicationLinkBtnReceive, enabled);
  }

  /**
   * @param enabled
   */
  protected void enableOperationKinds(boolean enabled) {
    enableButton(_communicationLinkBtnCall, enabled);
    enableButton(_communicationLinkBtnExecute, enabled);
  }

  /**
   * @param enabled
   */
  protected void enableSharedDataKinds(boolean enabled) {
    enableButton(_communicationLinkBtnWrite, enabled);
    enableButton(_communicationLinkBtnAccess, enabled);
  }

  /**
   * @param enabled
   */
  protected void enableUnsetKinds(boolean enabled) {
    enableButton(_communicationLinkBtnAcquire, enabled);
    enableButton(_communicationLinkBtnTransmit, enabled);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<>();

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
