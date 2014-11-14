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
   * @param parent_p
   * @param widgetFactory_p
   * @param enabled_p
   */
  public MessageKindGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean enabled_p) {
    super(parent_p, widgetFactory_p, Messages.getString("MessageKind.Label"), 3); //$NON-NLS-1$

    _messageKindBtnUnset = createButton(MessageKind.UNSET, enabled_p);
    _messageKindBtnSynchronous = createButton(MessageKind.SYNCHRONOUS_CALL, enabled_p);
    _messageKindBtnAsynchronous = createButton(MessageKind.ASYNCHRONOUS_CALL, enabled_p);
    _messageKindBtnReply = createButton(MessageKind.REPLY, enabled_p);
    _messageKindBtnCreate = createButton(MessageKind.CREATE, enabled_p);
    _messageKindBtnDelete = createButton(MessageKind.DELETE, enabled_p);
    _messageKindBtnTimer = createButton(MessageKind.TIMER, enabled_p);
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
