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
   * @param parent_p
   * @param widgetFactory_p
   */
  public CommunicationLinkProtocolGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p) {
    this(parent_p, Messages.getString("CommunicationLinkProtocol.Label"), widgetFactory_p, true, true, true); //$NON-NLS-1$
  }

  /**
   * Constructor.
   * @param parent_p
   * @param text_p
   * @param widgetFactory_p
   * @param showCallProtocols_p
   * @param showSendProtocols_p
   * @param showAccessProtocols_p
   */
  public CommunicationLinkProtocolGroup(Composite parent_p, String text_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean showCallProtocols_p, boolean showSendProtocols_p, boolean showAccessProtocols_p) {
    super(parent_p, widgetFactory_p, text_p, getNumColumns(showCallProtocols_p, showSendProtocols_p, showAccessProtocols_p));

    _communicationLinkBtnUnset = createButton(CommunicationLinkProtocol.UNSET);
    if (showCallProtocols_p) {
      _communicationLinkBtnSynchronous = createButton(CommunicationLinkProtocol.SYNCHRONOUS);
      _communicationLinkBtnAsynchronous = createButton(CommunicationLinkProtocol.ASYNCHRONOUS);
    }
    if (showSendProtocols_p) {
      _communicationLinkBtnUnicast = createButton(CommunicationLinkProtocol.UNICAST);
      _communicationLinkBtnMulticast = createButton(CommunicationLinkProtocol.MULTICAST);
      _communicationLinkBtnBroadcast = createButton(CommunicationLinkProtocol.BROADCAST);
    }
    if (showAccessProtocols_p) {
      _communicationLinkBtnRead = createButton(CommunicationLinkProtocol.READ);
      _communicationLinkBtnAccept = createButton(CommunicationLinkProtocol.ACCEPT);
    }
  }
  
  /**
   * 
   */
  private static int getNumColumns(boolean showCallProtocols_p, boolean showSendProtocols_p, boolean showAccessProtocols_p) {
    return 1 + (showCallProtocols_p ? 2 : 0) + (showSendProtocols_p ? 3 : 0) + (showAccessProtocols_p ? 2 : 0);
  }

  /**
   * @param exchangeMechanism_p
   */
  public void synchronizeProtocolsStatus(ExchangeMechanism exchangeMechanism_p) {
    if (exchangeMechanism_p != null) {
      switch (exchangeMechanism_p.getValue()) {
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
    loadData(_semanticElement);
  }

  /**
   * @param communicationLinkKind_p
   */
  public void synchronizeProtocolsStatus(CommunicationLinkKind communicationLinkKind_p) {
    if (communicationLinkKind_p != null) {
      switch (communicationLinkKind_p.getValue()) {
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
    loadData(_semanticElement);
  }

  /**
   * @param enabled_p
   */
  protected void enableAccessProtocols(boolean enabled_p) {
    enableButton(_communicationLinkBtnRead, enabled_p);
    enableButton(_communicationLinkBtnAccept, enabled_p);
  }
  
  /**
   * @param enabled_p
   */
  protected void enableCallProtocols(boolean enabled_p) {
    enableButton(_communicationLinkBtnSynchronous, enabled_p);
    enableButton(_communicationLinkBtnAsynchronous, enabled_p);
  }

  /**
   * @param enabled_p
   */
  protected void enableSendProtocols(boolean enabled_p) {
    enableButton(_communicationLinkBtnUnicast, enabled_p);
    enableButton(_communicationLinkBtnMulticast, enabled_p);
    enableButton(_communicationLinkBtnBroadcast, enabled_p);
  }

  /**
   * @param enabled_p
   */
  protected void enableUnsetProtocols(boolean enabled_p) {
    enableButton(_communicationLinkBtnUnset, enabled_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

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
