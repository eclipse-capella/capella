/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.vp.ms.ui.properties;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticKindGroup;
import org.polarsys.capella.vp.ms.kind_Type;
import org.polarsys.capella.vp.ms.provider.MsEditPlugin;

public class KindGroup extends AbstractSemanticKindGroup {

  private Button inclusionBtn;
  private Button exclusionBtn;

  public KindGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, String groupLabel,
      int numColumns) {
    super(parent, widgetFactory, groupLabel, numColumns);
    inclusionBtn = createButton(kind_Type.ATOMIC);
    exclusionBtn = createButton(kind_Type.GLOBAL);
  }

  /**
   * Create a radio button.
   *
   * @param enumerated
   * @param enabled
   * @return a not <code>null</code> object.
   */
  @Override
  protected Button createButton(Enumerator enumerated, boolean enabled) {
    return createButton(_group,
        MsEditPlugin.INSTANCE.getString("_UI_kind_Type_" + enumerated.getLiteral() + "_literal"), enumerated, //$NON-NLS-1$//$NON-NLS-2$
        enabled, SWT.RADIO);
  }

  @Override
  public Button getDefaultSemanticField() {
    return inclusionBtn;
  }

  @Override
  public List<Button> getSemanticFields() {
    return Arrays.asList(inclusionBtn, exclusionBtn);
  }

}
