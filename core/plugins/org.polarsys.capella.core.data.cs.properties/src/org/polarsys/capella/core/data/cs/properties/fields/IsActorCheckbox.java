/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.properties.sections.Messages;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticCheckboxGroup;
/**
 *
 */
public class IsActorCheckbox extends AbstractSemanticCheckboxGroup {
  private Button isActorBtn;

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   */
  public IsActorCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
    this(parent, widgetFactory, true);
  }

  /**
   * Constructor.
   * @param parent
   * @param widgetFactory
   * @param showIsActor
   */
  public IsActorCheckbox(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean showIsActor) {
    super(parent, widgetFactory);

    if (showIsActor) {
      isActorBtn = createButton(CsPackage.Literals.COMPONENT__ACTOR, Messages.Component_IsActor_Label, parent); //$NON-NLS-1$ 
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<>();

    if (null != isActorBtn) {
      fields.add(isActorBtn);
    }

    return fields;
  }

  public boolean isEnabled() {
    return isActorBtn.isEnabled();
  }
}
