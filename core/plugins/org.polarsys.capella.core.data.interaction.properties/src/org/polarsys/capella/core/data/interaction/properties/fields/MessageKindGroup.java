/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.interaction.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class MessageKindGroup extends AbstractSemanticKindGroup {
  private Button _messageKindBtnUnset;
  private Button _messageKindBtnSynchronous;
  private Button _messageKindBtnAsynchronous;
  private Button _messageKindBtnReply;
  private Button _messageKindBtnCreate;
  private Button _messageKindBtnDelete;
  private Button _messageKindBtnTimer;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param enabled
   */
  public MessageKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean enabled) {
    super(parent, widgetFactory, Messages.getString("MessageKind.Label"), 3); //$NON-NLS-1$

    _messageKindBtnUnset = createButton(MessageKind.UNSET, enabled);
    _messageKindBtnSynchronous = createButton(MessageKind.SYNCHRONOUS_CALL, enabled);
    _messageKindBtnAsynchronous = createButton(MessageKind.ASYNCHRONOUS_CALL, enabled);
    _messageKindBtnReply = createButton(MessageKind.REPLY, enabled);
    _messageKindBtnCreate = createButton(MessageKind.CREATE, enabled);
    _messageKindBtnDelete = createButton(MessageKind.DELETE, enabled);
    _messageKindBtnTimer = createButton(MessageKind.TIMER, enabled);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_messageKindBtnUnset);
    fields.add(_messageKindBtnSynchronous);
    fields.add(_messageKindBtnAsynchronous);
    fields.add(_messageKindBtnReply);
    fields.add(_messageKindBtnCreate);
    fields.add(_messageKindBtnDelete);
    fields.add(_messageKindBtnTimer);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _messageKindBtnUnset;
  }
}
