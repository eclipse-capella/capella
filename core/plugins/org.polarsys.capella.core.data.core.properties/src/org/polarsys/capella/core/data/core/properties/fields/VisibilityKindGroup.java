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
package org.polarsys.capella.core.data.core.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.data.capellacore.VisibilityKind;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class VisibilityKindGroup extends AbstractSemanticKindGroup {
  private Button _visibilityBtnUnset;
  private Button _visibilityBtnPublic;
  private Button _visibilityBtnProtected;
  private Button _visibilityBtnPrivate;
  private Button _visibilityBtnPackage;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   */
  public VisibilityKindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(parent, widgetFactory, Messages.getString("VisibilityKind.Label"), 5); //$NON-NLS-1$

    _visibilityBtnUnset = createButton(VisibilityKind.UNSET);
    _visibilityBtnPublic = createButton(VisibilityKind.PUBLIC);
    _visibilityBtnProtected = createButton(VisibilityKind.PROTECTED);
    _visibilityBtnPrivate = createButton(VisibilityKind.PRIVATE);
    _visibilityBtnPackage = createButton(VisibilityKind.PACKAGE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(_visibilityBtnUnset);
    fields.add(_visibilityBtnPublic);
    fields.add(_visibilityBtnProtected);
    fields.add(_visibilityBtnPrivate);
    fields.add(_visibilityBtnPackage);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return _visibilityBtnUnset;
  }
}
