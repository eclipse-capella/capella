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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;
import org.polarsys.capella.common.flexibility.wizards.renderer.NullRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 *
 */
public class FlatSectionGroupRenderer extends DefaultGroupRenderer {

  protected Section section;

  @Override
  public void updatedValue(IPropertyGroup property, IRendererContext context) {
    //Nothing here
  }

  /**
   * 
   */
  @Override
  protected Composite createGroup(Composite parent, IPropertyGroup group, IPropertyContext context, IRendererContext rendererContext) {

    int i = 0;
    for (IProperty property : context.getProperties().getItems(group)) {
      IRenderer rent = rendererContext.getRenderer(property);
      if ((rent != null) && !(rent instanceof NullRenderer)) {
        i++;
      }
    }
    if (i == 0) {
      return parent;
    }

    Composite parentComposite = null;
    section = new Section(parent, ExpandableComposite.TWISTIE);
    section.setText(getGroupName(group));
    parentComposite = section;
    parentComposite = new Composite(parentComposite, SWT.NONE);
    section.setClient(parentComposite);

    GridLayout gridLayout = new GridLayout();
    gridLayout.marginHeight = 0;
    gridLayout.marginWidth = 0;
    section.setLayout(gridLayout);
    section.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    gridLayout = new GridLayout();
    gridLayout.marginHeight = 0;
    gridLayout.marginWidth = 0;
    parentComposite.setLayout(gridLayout);
    parentComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    return parentComposite;
  }

}
