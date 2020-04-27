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

import org.polarsys.capella.core.data.interaction.InteractionOperatorKind;
import org.polarsys.capella.core.data.interaction.properties.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class InteractionOperatorKindGroup extends AbstractSemanticKindGroup {
  private Button _messageKindBtnAlt;
  private Button _messageKindBtnAssert;
  private Button _messageKindBtnConsider;
  private Button _messageKindBtnCritical;
  private Button _messageKindBtnIgnore;
  private Button _messageKindBtnLoop;
  private Button _messageKindBtnNeg;
  private Button _messageKindBtnOpt;
  private Button _messageKindBtnPar;
  private Button _messageKindBtnSeq;
  private Button _messageKindBtnStrict;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param enabled
   */
  public InteractionOperatorKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean enabled) {
    super(parent, widgetFactory, Messages.getString("InteractionOperatorKind.Label"), 6); //$NON-NLS-1$

    _messageKindBtnAlt = createButton(InteractionOperatorKind.ALT, enabled);
    _messageKindBtnAssert = createButton(InteractionOperatorKind.ASSERT, enabled);
    _messageKindBtnConsider = createButton(InteractionOperatorKind.CONSIDER, enabled);
    _messageKindBtnCritical = createButton(InteractionOperatorKind.CRITICAL, enabled);
    _messageKindBtnIgnore = createButton(InteractionOperatorKind.IGNORE, enabled);
    _messageKindBtnLoop = createButton(InteractionOperatorKind.LOOP, enabled);
    _messageKindBtnNeg = createButton(InteractionOperatorKind.NEG, enabled);
    _messageKindBtnOpt = createButton(InteractionOperatorKind.OPT, enabled);
    _messageKindBtnPar = createButton(InteractionOperatorKind.PAR, enabled);
    _messageKindBtnSeq = createButton(InteractionOperatorKind.SEQ, enabled);
    _messageKindBtnStrict = createButton(InteractionOperatorKind.STRICT, enabled);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_messageKindBtnAlt);
    fields.add(_messageKindBtnAssert);
    fields.add(_messageKindBtnConsider);
    fields.add(_messageKindBtnCritical);
    fields.add(_messageKindBtnIgnore);
    fields.add(_messageKindBtnLoop);
    fields.add(_messageKindBtnNeg);
    fields.add(_messageKindBtnOpt);
    fields.add(_messageKindBtnPar);
    fields.add(_messageKindBtnSeq);
    fields.add(_messageKindBtnStrict);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _messageKindBtnAlt;
  }
}
