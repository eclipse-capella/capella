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
package org.polarsys.capella.common.flexibility.wizards.ui;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import org.polarsys.capella.common.flexibility.wizards.constants.ICommonConstants;
import org.polarsys.capella.common.flexibility.wizards.group.renderer.CTabGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.group.renderer.DefaultGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.group.renderer.FlatGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.policy.AbstractRendererPolicy;
import org.polarsys.capella.common.flexibility.wizards.policy.IPolicifiedRendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderers;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;

/**
 *
 */
public class PropertyControl {

  protected IPropertyContext _context;

  protected IRendererContext _renderers;

  protected ILabelProvider _labelProvider;

  /**
   * @return the context
   */
  protected IPropertyContext getPropertyContext() {
    return _context;
  }

  /**
   * @return the renderers
   */
  protected IRendererContext getRendererContext() {
    return _renderers;
  }

  /**
   * @return the labelProvider
   */
  protected ILabelProvider getLabelProvider() {
    return _labelProvider;
  }

  /**
   * Returns whether the viewer should have tabs for all main groups or all groups will be sequential
   */
  protected boolean isFlat() {
    return false;
  }

  public PropertyControl(ILabelProvider labelProvider_p, IPropertyContext context_p, IRendererContext renderers_p) {
    this._context = context_p;
    this._renderers = renderers_p;
    this._labelProvider = labelProvider_p;
    initRendererContext();
  }

  /**
   * 
   */
  protected void initRendererContext() {
    if (_renderers instanceof IPolicifiedRendererContext) {
      ((IPolicifiedRendererContext) _renderers).addRendererPolicy(new AbstractRendererPolicy() {

        @Override
        public boolean match(IPropertyGroup property_p) {
          return true;
        }

        @Override
        public IGroupRenderer createRenderer(IPropertyGroup group_p) {
          if (group_p.getParentId() == null) {
            if (isFlat()) {
              return new FlatGroupRenderer();
            }
            return new CTabGroupRenderer();
          }
          return new DefaultGroupRenderer();
        }
      });
    }
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
   * {@inheritDoc}
   */
  public Control createControl(Composite parent_p) {
    IPropertyContext context = getPropertyContext();
    IRendererContext rendererContext = getRendererContext();

    IRenderers renderers = getRendererContext().getRenderers();
    if ((context == null) || (renderers == null)) {
      return parent_p;
    }

    IProperties properties = context.getProperties();
    Composite topLevel = new Composite(parent_p, SWT.NONE);
    GridLayout mainLayout = new GridLayout();
    mainLayout.marginHeight = 0;
    mainLayout.marginWidth = 0;
    topLevel.setLayout(mainLayout);
    topLevel.setLayoutData(new GridData(GridData.FILL_BOTH));

    boolean isFlat = isFlat();
    CTabFolder tabFolder = null;
    // Create the tabs
    if (!isFlat) {
      tabFolder = new CTabFolder(topLevel, SWT.TOP);
      tabFolder.setBorderVisible(true);
      tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
      tabFolder.setLayout(new GridLayout());
    }

    int i = 0;
    for (IPropertyGroup mainGroup : renderers.getGroups(properties, IPropertyGroup.EMPTY)) {

      rendererContext.putParameter(DefaultGroupRenderer.PARAMETER_GROUP_INDEX, Integer.valueOf(i));
      rendererContext.putParameter(CTabGroupRenderer.PARAMETER_TAB_FOLDER, tabFolder);
      IGroupRenderer groupRenderer = rendererContext.getRenderer(mainGroup);
      i++;
      if (groupRenderer != null) {
        groupRenderer.render(topLevel, rendererContext);
      }
    }

    return topLevel;
  }

}
