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
package org.polarsys.capella.common.re.ui.group.renderers;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import org.polarsys.capella.common.flexibility.wizards.group.renderer.HorizontalGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;

/**
 *
 */
public class ReplicaGroupRenderer extends HorizontalGroupRenderer {

  @Override
  protected Composite createGroup(Composite parent, IPropertyGroup group, IPropertyContext context, IRendererContext rendererContext) {

    Composite parentComposite = null;

    parentComposite = new Composite(parent, SWT.NONE);
    GridLayout gridLayout = new GridLayout(1, true);
    gridLayout.marginHeight = 0;
    gridLayout.marginWidth = 0;
    parentComposite.setLayout(gridLayout);
    parentComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

    Group newGroup = new Group(parentComposite, SWT.NONE);
    newGroup.setText(getGroupName(group));
    newGroup.setData(group);
    GridLayout gridLayout2 = new GridLayout(1, true);
    gridLayout2.marginHeight = 0;
    gridLayout2.marginWidth = 0;
    newGroup.setLayout(gridLayout2);

    newGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
    parentComposite = newGroup;

    SashForm form = new SashForm(parentComposite, SWT.HORIZONTAL | SWT.SMOOTH);

    parentComposite = form;

    return form;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Composite renderGroup(Composite parent, IPropertyGroup group, IPropertyContext context, IRendererContext rendererContext,
      ILabelProvider labelProvider) {
    Composite prt = super.renderGroup(parent, group, context, rendererContext, labelProvider);

    try {
      ((SashForm) prt).setWeights(new int[] { 40, 60 });
    } catch (Exception e) {
      
    }

    GridLayout gridLayout3 = new GridLayout(1, true);
    gridLayout3.marginHeight = 0;
    gridLayout3.marginWidth = 0;
    prt.setLayout(gridLayout3);
    GridData layoutData = new GridData(GridData.FILL_BOTH);
    layoutData.minimumHeight = 300;
    layoutData.minimumWidth = 600;
    prt.setLayoutData(layoutData);

    return prt;
  }

}
