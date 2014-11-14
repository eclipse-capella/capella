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
   * @param parent_p
   * @param widgetFactory_p
   * @param enabled_p
   */
  public InteractionOperatorKindGroup(Composite parent_p, TabbedPropertySheetWidgetFactory widgetFactory_p, boolean enabled_p) {
    super(parent_p, widgetFactory_p, Messages.getString("InteractionOperatorKind.Label"), 6); //$NON-NLS-1$

    _messageKindBtnAlt = createButton(InteractionOperatorKind.ALT, enabled_p);
    _messageKindBtnAssert = createButton(InteractionOperatorKind.ASSERT, enabled_p);
    _messageKindBtnConsider = createButton(InteractionOperatorKind.CONSIDER, enabled_p);
    _messageKindBtnCritical = createButton(InteractionOperatorKind.CRITICAL, enabled_p);
    _messageKindBtnIgnore = createButton(InteractionOperatorKind.IGNORE, enabled_p);
    _messageKindBtnLoop = createButton(InteractionOperatorKind.LOOP, enabled_p);
    _messageKindBtnNeg = createButton(InteractionOperatorKind.NEG, enabled_p);
    _messageKindBtnOpt = createButton(InteractionOperatorKind.OPT, enabled_p);
    _messageKindBtnPar = createButton(InteractionOperatorKind.PAR, enabled_p);
    _messageKindBtnSeq = createButton(InteractionOperatorKind.SEQ, enabled_p);
    _messageKindBtnStrict = createButton(InteractionOperatorKind.STRICT, enabled_p);
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
