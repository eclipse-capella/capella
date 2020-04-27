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
  public void render(Composite parent, IRendererContext context) {
    IPropertyGroup group = context.getPropertyGroup(this);
    renderGroup(parent, group, context.getPropertyContext(), context, context.getLabelProvider());
  }

  /**
   * @param group
   * @return
   */
  protected String getGroupName(IPropertyGroup group) {
    if ((group.getName() == null) || (group.getName().length() == 0)) {
      return ICommonConstants.EMPTY_STRING;
    }
    return ICommonConstants.WHITE_SPACE_CHARACTER + group.getName() + ICommonConstants.WHITE_SPACE_CHARACTER;
  }

  /**
   * 
   */
  protected Composite createGroup(Composite parent, IPropertyGroup group, IPropertyContext context, IRendererContext rendererContext) {

    Composite parentComposite = null;

    parentComposite = new Composite(parent, SWT.NONE);

    if (parent.getLayout() instanceof GridLayout) {
      GridLayout gridLayout = new GridLayout();
      gridLayout.marginHeight = 0;
      gridLayout.marginWidth = 0;
      parentComposite.setLayout(gridLayout);
      parentComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    }

    Group newGroup = new Group(parentComposite, SWT.NONE);
    newGroup.setText(getGroupName(group));
    newGroup.setData(group);

    if (parent.getLayout() instanceof GridLayout) {
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

  public Label createPartLabel(Composite parent, IProperty item, boolean required) {
    Label label = new Label(parent, 0);

    if ((item.getDescription() != null) && !item.getDescription().isEmpty()) {
      label.setToolTipText(item.getDescription());
    }

    if ((item.getName() != null) && !item.getName().isEmpty()) {
      label.setText(item.getName() + " :   ");
    }
    if (required) {
      label.setFont(JFaceResources.getFontRegistry().getBold("org.eclipse.jface.defaultfont")); //$NON-NLS-1$
    }
    return label;
  }

  /**
   * @param parent
   * @param group
   */
  protected Composite renderGroup(Composite parent, IPropertyGroup group, IPropertyContext context, IRendererContext rendererContext,
      ILabelProvider labelProvider) {

    Composite parentComposite = null;

    Collection<IPropertyGroup> childGroups = rendererContext.getRenderers().getGroups(context.getProperties(), group);
    Collection<IProperty> childProperties = rendererContext.getRenderers().getItems(context.getProperties(), group);

    if ((childGroups.size() == 0) && (childProperties.size() == 0)) {
      return parent;
    }

    for (IPropertyGroup item : childGroups) {
      try {

        if (parentComposite == null) { // lazy group creation
          parentComposite = createParentComposite(parent, group, context, rendererContext);
        }

        // Render the default renderer for the group
        IGroupRenderer groupRenderer = rendererContext.getRenderer(item);
        if (groupRenderer != null) {
          groupRenderer.render(parentComposite, rendererContext);
        }

      } catch (Exception e) {
        e.printStackTrace();
      }

    }

    boolean displayLabels = isDisplayLabel(group);
    for (IProperty item : childProperties) {
      try {
        IRenderer renderer = rendererContext.getRenderer(item);
        if (renderer != null) {

          if (parentComposite == null) { // lazy group creation
            parentComposite = createParentComposite(parent, group, context, rendererContext);
          }

          if (displayLabels) {
            Label label = createPartLabel(parentComposite, item, false);
            GridData data = new GridData();
            label.setLayoutData(data);
          }

          rendererContext.putParameter("PARAMETER_RENDER_LABEL", Boolean.valueOf(!displayLabels));
          renderer.render(parentComposite, rendererContext);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return parentComposite;
  }

  /**
   * @param group
   * @return
   */
  private Composite createParentComposite(Composite parent, IPropertyGroup group, IPropertyContext context, IRendererContext rendererContext) {
    Composite parentComposite = createGroup(parent, group, context, rendererContext);
    parentComposite.setLayout(createGroupLayout(group, rendererContext)); // 2 ?
    parentComposite.setLayoutData(createGroupLayoutData(group, rendererContext)); // 2 ?

    if (parentComposite.getLayout() instanceof GridLayout) {
      ((GridLayout) (parentComposite.getLayout())).numColumns = (isDisplayLabel(group) ? 1 : 0) + 1;
    }
    return parentComposite;
  }

  /**
   * @return
   */
  protected boolean isDisplayLabel(IPropertyGroup group) {
    return false;
  }

  /**
   * @param group
   * @param rendererContext
   * @return
   */
  protected Object createGroupLayoutData(IPropertyGroup group, IRendererContext rendererContext) {
    return new GridData(SWT.FILL, SWT.FILL, true, true);
  }

  /**
   * @param group
   * @param rendererContext
   * @return
   */
  protected Layout createGroupLayout(IPropertyGroup group, IRendererContext rendererContext) {
    return new GridLayout(1, false);
  }

  /**
   * @param item
   * @param rendererContext
   * @return
   */
  protected int getGridColumn(IPropertyGroup item, IRendererContext rendererContext) {
    return 1;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose(IRendererContext context) {
    // Nothing here
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IPropertyGroup propertyGroup, IRendererContext context) {
    // Nothing here
  }

}
