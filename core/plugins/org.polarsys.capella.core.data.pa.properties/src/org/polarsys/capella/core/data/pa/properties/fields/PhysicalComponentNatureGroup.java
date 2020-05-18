/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.pa.properties.fields;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.properties.Messages;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;

/**
 */
public class PhysicalComponentNatureGroup extends AbstractSemanticKindGroup {
  private Button pcNatureBtnBehavior;
  private Button pcNatureBtnNode;

  /**
   * Constructor.
   * 
   * @param parent
   * @param widgetFactory
   * @param enabled
   */
  public PhysicalComponentNatureGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory,
      boolean enabled) {
    super(parent, widgetFactory, Messages.getString("PhysicalComponentNature.Label"), enabled, 3); //$NON-NLS-1$

    pcNatureBtnBehavior = createButton(PhysicalComponentNature.BEHAVIOR, enabled);
    pcNatureBtnNode = createButton(PhysicalComponentNature.NODE, enabled);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Button> getSemanticFields() {
    List<Button> fields = new ArrayList<Button>();

    fields.add(pcNatureBtnBehavior);
    fields.add(pcNatureBtnNode);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Button getDefaultSemanticField() {
    return null;
  }

  @Override
  public void setEnabled(boolean enabled) {
    boolean isEnabled = enabled;

    if (isEnabled && semanticElement instanceof PhysicalComponent) {
      PhysicalComponent component = (PhysicalComponent) semanticElement;
      isEnabled = ComponentExt.isActor(component) || !ComponentExt.isComponentRoot(component);
    }

    super.setEnabled(isEnabled);
    for (Button button : getSemanticFields()) {
      if (isEnabled) {
        button.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
      } else {
        button.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
      }
    }
  }
}
