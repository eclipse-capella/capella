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
package org.polarsys.capella.common.flexibility.wizards.group.renderer;

import java.util.Collection;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;
import org.polarsys.capella.common.flexibility.wizards.schema.IGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 */
public class DefaultGroupRenderer implements IGroupRenderer {

  public static final String PARAMETER_GROUP_INDEX = "PARAMETER_GROUP_INDEX";

  /**
   * {@inheritDoc}
   */
  @Override
  public void render(Composite parent_p, IRendererContext context_p) {
    IPropertyGroup group = context_p.getPropertyGroup(this);
    renderGroup(parent_p, group, context_p.getPropertyContext(), context_p, context_p.getLabelProvider());
  }

  /**
   * @param mainGroup_p
   * @return
   */
  protected String getGroupName(IPropertyGroup group_p) {
    if ((group_p.getName() == null) || (group_p.getName().length() == 0)) {
      return ICommonConstants.EMPTY_STRING;
    }
    return ICommonConstants.WHITE_SPACE_CHARACTER + group_p.getName() + ICommonConstants.WHITE_SPACE_CHARACTER;
  }

  /**
   * 
   */
  protected Composite createGroup(Composite parent_p, IPropertyGroup group_p, IPropertyContext context, IRendererContext rendererContext_p) {

    Composite parentComposite = null;

    parentComposite = new Composite(parent_p, SWT.NONE);

    if (parent_p.getLayout() instanceof GridLayout) {
      GridLayout gridLayout = new GridLayout();
      gridLayout.marginHeight = 0;
      gridLayout.marginWidth = 0;
      parentComposite.setLayout(gridLayout);
      parentComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    }

    Group newGroup = new Group(parentComposite, SWT.NONE);
    newGroup.setText(getGroupName(group_p));
    newGroup.setData(group_p);

    if (parent_p.getLayout() instanceof GridLayout) {
      GridLayout gridLayout2 = new GridLayout();
      gridLayout2.marginHeight = 0;
      gridLayout2.marginWidth = 0;
      gridLayout2.numColumns = 2;
      newGroup.setLayout(gridLayout2);
      newGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
    }

    parentComposite = newGroup;

    return parentComposite;
  }

  public Label createPartLabel(Composite parent, IProperty item_p, boolean required) {
    Label label = new Label(parent, 0);

    if ((item_p.getDescription() != null) && !item_p.getDescription().isEmpty()) {
      label.setToolTipText(item_p.getDescription());
    }

    if ((item_p.getName() != null) && !item_p.getName().isEmpty()) {
      label.setText(item_p.getName() + " :   ");
    }
    if (required) {
      label.setFont(JFaceResources.getFontRegistry().getBold("org.eclipse.jface.defaultfont")); //$NON-NLS-1$
    }
    return label;
  }

  /**
   * @param parent_p
   * @param group_p
   */
  protected Composite renderGroup(Composite parent_p, IPropertyGroup group_p, IPropertyContext context, IRendererContext rendererContext_p,
      ILabelProvider labelProvider) {

    Composite parentComposite = null;

    Collection<IPropertyGroup> childGroups = rendererContext_p.getRenderers().getGroups(context.getProperties(), group_p);
    Collection<IProperty> childProperties = rendererContext_p.getRenderers().getItems(context.getProperties(), group_p);

    if ((childGroups.size() == 0) && (childProperties.size() == 0)) {
      return parent_p;
    }

    for (IPropertyGroup item : childGroups) {
      try {

        if (parentComposite == null) { // lazy group creation
          parentComposite = createParentComposite(parent_p, group_p, context, rendererContext_p);
        }

        // Render the default renderer for the group
        IGroupRenderer groupRenderer = rendererContext_p.getRenderer(item);
        if (groupRenderer != null) {
          groupRenderer.render(parentComposite, rendererContext_p);
        }

      } catch (Exception e) {
        e.printStackTrace();
      }

    }

    boolean displayLabels = isDisplayLabel(group_p);
    for (IProperty item : childProperties) {
      try {
        IRenderer renderer = rendererContext_p.getRenderer(item);
        if (renderer != null) {

          if (parentComposite == null) { // lazy group creation
            parentComposite = createParentComposite(parent_p, group_p, context, rendererContext_p);
          }

          if (displayLabels) {
            Label label = createPartLabel(parentComposite, item, false);
            GridData data = new GridData();
            label.setLayoutData(data);
          }

          rendererContext_p.putParameter("PARAMETER_RENDER_LABEL", Boolean.valueOf(!displayLabels));
          renderer.render(parentComposite, rendererContext_p);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return parentComposite;
  }

  /**
   * @param group_p
   * @return
   */
  private Composite createParentComposite(Composite parent_p, IPropertyGroup group_p, IPropertyContext context, IRendererContext rendererContext_p) {
    Composite parentComposite = createGroup(parent_p, group_p, context, rendererContext_p);
    parentComposite.setLayout(createGroupLayout(group_p, rendererContext_p)); // 2 ?
    parentComposite.setLayoutData(createGroupLayoutData(group_p, rendererContext_p)); // 2 ?

    if (parentComposite.getLayout() instanceof GridLayout) {
      ((GridLayout) (parentComposite.getLayout())).numColumns = (isDisplayLabel(group_p) ? 1 : 0) + 1;
    }
    return parentComposite;
  }

  /**
   * @return
   */
  protected boolean isDisplayLabel(IPropertyGroup group_p) {
    return false;
  }

  /**
   * @param group_p
   * @param rendererContext_p
   * @return
   */
  protected Object createGroupLayoutData(IPropertyGroup group_p, IRendererContext rendererContext_p) {
    return new GridData(SWT.FILL, SWT.FILL, true, true);
  }

  /**
   * @param group_p
   * @param rendererContext_p
   * @return
   */
  protected Layout createGroupLayout(IPropertyGroup group_p, IRendererContext rendererContext_p) {
    return new GridLayout(1, false);
  }

  /**
   * @param item_p
   * @param rendererContext_p
   * @return
   */
  protected int getGridColumn(IPropertyGroup item_p, IRendererContext rendererContext_p) {
    return 1;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose(IRendererContext context_p) {
    // Nothing here
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IPropertyGroup propertyGroup_p, IRendererContext context_p) {
    // Nothing here
  }

}
