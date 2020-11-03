/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.information.communication.CommunicationLinkProtocol;
import org.polarsys.capella.core.data.information.communication.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class CommunicationLinkProtocolGroup extends AbstractSemanticKindGroup {
  private Button _communicationLinkBtnUnset;
  private Button _communicationLinkBtnSynchronous;
  private Button _communicationLinkBtnAsynchronous;
  private Button _communicationLinkBtnUnicast;
  private Button _communicationLinkBtnMulticast;
  private Button _communicationLinkBtnBroadcast;
  private Button _communicationLinkBtnRead;
  private Button _communicationLinkBtnAccept;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   */
  public CommunicationLinkProtocolGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    this(parent, Messages.getString("CommunicationLinkProtocol.Label"), widgetFactory, true, true, true); //$NON-NLS-1$
  }

  /**
   * Constructor.
   * @param parent
   * @param text
   * @param widgetFactory
   * @param showCallProtocols
   * @param showSendProtocols
   * @param showAccessProtocols
   */
  public CommunicationLinkProtocolGroup(Composite parent, String text, TabbedPropertySheetWidgetFactory widgetFactory, boolean showCallProtocols, boolean showSendProtocols, boolean showAccessProtocols) {
    super(parent, widgetFactory, text, getNumColumns(showCallProtocols, showSendProtocols, showAccessProtocols));

    _communicationLinkBtnUnset = createButton(CommunicationLinkProtocol.UNSET);
    if (showCallProtocols) {
      _communicationLinkBtnSynchronous = createButton(CommunicationLinkProtocol.SYNCHRONOUS);
      _communicationLinkBtnAsynchronous = createButton(CommunicationLinkProtocol.ASYNCHRONOUS);
    }
    if (showSendProtocols) {
      _communicationLinkBtnUnicast = createButton(CommunicationLinkProtocol.UNICAST);
      _communicationLinkBtnMulticast = createButton(CommunicationLinkProtocol.MULTICAST);
      _communicationLinkBtnBroadcast = createButton(CommunicationLinkProtocol.BROADCAST);
    }
    if (showAccessProtocols) {
      _communicationLinkBtnRead = createButton(CommunicationLinkProtocol.READ);
      _communicationLinkBtnAccept = createButton(CommunicationLinkProtocol.ACCEPT);
    }
  }
  
  /**
   * 
   */
  private static int getNumColumns(boolean showCallProtocols, boolean showSendProtocols, boolean showAccessProtocols) {
    return 1 + (showCallProtocols ? 2 : 0) + (showSendProtocols ? 3 : 0) + (showAccessProtocols ? 2 : 0);
  }

  /**
   * @param exchangeMechanism
   */
  public void synchronizeProtocolsStatus(ExchangeMechanism exchangeMechanism) {
    if (exchangeMechanism != null) {
      switch (exchangeMechanism.getValue()) {
        case ExchangeMechanism.OPERATION_VALUE:
          enableCallProtocols(true);
          enableSendProtocols(false);
          enableAccessProtocols(false);
          enableUnsetProtocols(true);
          break;
        case ExchangeMechanism.EVENT_VALUE:
          enableCallProtocols(false);
          enableSendProtocols(true);
          enableAccessProtocols(false);
          enableUnsetProtocols(true);
          break;
        case ExchangeMechanism.SHARED_DATA_VALUE:
          enableCallProtocols(false);
          enableSendProtocols(false);
          enableAccessProtocols(true);
          enableUnsetProtocols(true);
          break;
        default:
          enableCallProtocols(false);
          enableSendProtocols(false);
          enableAccessProtocols(false);
          enableUnsetProtocols(true);
      }
    } else {
      enableCallProtocols(false);
      enableSendProtocols(false);
      enableAccessProtocols(false);
      enableUnsetProtocols(true);
    }
    loadData(semanticElement);
  }

  /**
   * @param communicationLinkKind
   */
  public void synchronizeProtocolsStatus(CommunicationLinkKind communicationLinkKind) {
    if (communicationLinkKind != null) {
      switch (communicationLinkKind.getValue()) {
        case CommunicationLinkKind.CALL_VALUE:
          enableCallProtocols(true);
          enableSendProtocols(false);
          enableAccessProtocols(false);
          enableUnsetProtocols(true);
          break;
        case CommunicationLinkKind.SEND_VALUE:
          enableCallProtocols(false);
          enableSendProtocols(true);
          enableAccessProtocols(false);
          enableUnsetProtocols(true);
          break;
        case CommunicationLinkKind.ACCESS_VALUE:
          enableCallProtocols(false);
          enableSendProtocols(false);
          enableAccessProtocols(true);
          enableUnsetProtocols(true);
          break;
        default:
          enableCallProtocols(false);
          enableSendProtocols(false);
          enableAccessProtocols(false);
          enableUnsetProtocols(true);
      }
    } else {
      enableCallProtocols(false);
      enableSendProtocols(false);
      enableAccessProtocols(false);
      enableUnsetProtocols(true);
    }
    loadData(semanticElement);
  }

  /**
   * @param enabled
   */
  protected void enableAccessProtocols(boolean enabled) {
    enableButton(_communicationLinkBtnRead, enabled);
    enableButton(_communicationLinkBtnAccept, enabled);
  }
  
  /**
   * @param enabled
   */
  protected void enableCallProtocols(boolean enabled) {
    enableButton(_communicationLinkBtnSynchronous, enabled);
    enableButton(_communicationLinkBtnAsynchronous, enabled);
  }

  /**
   * @param enabled
   */
  protected void enableSendProtocols(boolean enabled) {
    enableButton(_communicationLinkBtnUnicast, enabled);
    enableButton(_communicationLinkBtnMulticast, enabled);
    enableButton(_communicationLinkBtnBroadcast, enabled);
  }

  /**
   * @param enabled
   */
  protected void enableUnsetProtocols(boolean enabled) {
    enableButton(_communicationLinkBtnUnset, enabled);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<>();

    fields.add(_communicationLinkBtnRead);
    fields.add(_communicationLinkBtnAccept);
    fields.add(_communicationLinkBtnSynchronous);
    fields.add(_communicationLinkBtnAsynchronous);
    fields.add(_communicationLinkBtnUnicast);
    fields.add(_communicationLinkBtnMulticast);
    fields.add(_communicationLinkBtnBroadcast);
    fields.add(_communicationLinkBtnUnset);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _communicationLinkBtnUnset;
  }
}
