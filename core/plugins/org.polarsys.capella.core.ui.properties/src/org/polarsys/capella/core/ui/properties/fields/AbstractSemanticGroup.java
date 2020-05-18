/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.fields;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 */
public abstract class AbstractSemanticGroup extends AbstractSemanticField {

  protected Composite parent;

  /**
   * @param parent
   * @param widgetFactory
   * @param skipGroup
   */
  public AbstractSemanticGroup(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory, boolean skipGroup) {
    super(widgetFactory);

    if (!skipGroup) {
      createGroup(parent);
    } else {
      this.parent = parent;
    }
  }

  protected void createGroup(Composite parent) {
    this.parent = widgetFactory.createGroup(parent, ICommonConstants.EMPTY_STRING);
    this.parent.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    this.parent.setLayoutData(gd);
  }
  
  public Composite getParent() {
    return this.parent;
  }
}
