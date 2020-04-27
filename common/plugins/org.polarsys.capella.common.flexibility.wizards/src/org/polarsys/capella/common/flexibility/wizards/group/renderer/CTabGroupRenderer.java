/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.flexibility.wizards.group.renderer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 */
public class CTabGroupRenderer extends DefaultGroupRenderer {

  public static final String PARAMETER_TAB_FOLDER = "PARAMETER_TAB_FOLDER";
  public static final String PARAMETER_SCROLL_MINIMAL_WIDTH = "PARAMETER_SCROLL_MINIMAL_WIDTH";
  public static final String PARAMETER_SCROLL_MINIMAL_HEIGHT = "PARAMETER_SCROLL_MINIMAL_HEIGHT";

  /**
   * 
   */
  @Override
  protected Composite createGroup(Composite parent, IPropertyGroup group, IPropertyContext context, IRendererContext rendererContext) {

    GridData contentLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
    contentLayoutData.horizontalIndent = 0;
    contentLayoutData.horizontalSpan = 2;
    contentLayoutData.verticalIndent = 0;

    CTabFolder tabFolder = (CTabFolder) rendererContext.getParameter(PARAMETER_TAB_FOLDER);

    CTabItem tab1 = null;
    tab1 = new CTabItem(tabFolder, SWT.NONE);
    tab1.setText(getGroupName(group));
    ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.H_SCROLL | SWT.V_SCROLL);

    Composite content = new Composite(scrolledComposite, SWT.FILL | SWT.RESIZE);

    content.setLayout(new GridLayout(1, false));
    content.setLayoutData(contentLayoutData);
    tab1.setControl(scrolledComposite);

    scrolledComposite.setContent(content);
    scrolledComposite.setAlwaysShowScrollBars(false);
    scrolledComposite.setExpandHorizontal(true);
    scrolledComposite.setExpandVertical(true);
    customizeComposite(scrolledComposite, rendererContext);
    scrolledComposite.setVisible(true);

    if (Integer.valueOf(0).equals(rendererContext.getParameter(PARAMETER_GROUP_INDEX))) {
      tabFolder.setSelection(tab1);
    }

    return content;
  }

  protected void customizeComposite(ScrolledComposite composite, IRendererContext rendererContext) {

    Integer width = (Integer) rendererContext.getParameter(PARAMETER_SCROLL_MINIMAL_WIDTH);
    Integer height = (Integer) rendererContext.getParameter(PARAMETER_SCROLL_MINIMAL_HEIGHT);

    if ((width != null) && (height != null)) {
      composite.setMinSize(width, height);
    }
  }
}
