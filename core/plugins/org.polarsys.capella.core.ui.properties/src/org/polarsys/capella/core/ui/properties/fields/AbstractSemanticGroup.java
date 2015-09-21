/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

  protected Composite _parent;

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
      _parent = parent;
    }
  }

  protected void createGroup(Composite parent) {
    _parent = _widgetFactory.createGroup(parent, ICommonConstants.EMPTY_STRING);
    _parent.setLayout(new GridLayout(2, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    _parent.setLayoutData(gd);
  }
  
  public Composite getParent() {
    return _parent;
  }
}
