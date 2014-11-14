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
package org.polarsys.capella.core.ui.properties.sections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.graphics.Color;

import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;
import org.polarsys.capella.common.flexibility.wizards.group.renderer.DefaultGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.group.renderer.FlatGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.policy.AbstractRendererPolicy;
import org.polarsys.capella.common.flexibility.wizards.policy.IPolicifiedRendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.ui.tabbed.PropertiesSection;

/**
 *
 */
public class CapellaPropertySection extends PropertiesSection implements IAbstractSection {

  /**
   * @param context_p
   * @param renderers_p
   * @param group_p
   */
  public CapellaPropertySection(IPropertyContext context_p, IRendererContext renderers_p, IPropertyGroup group_p) {
    super(context_p, renderers_p, group_p);
  }

  @Override
  protected void initRendererContext(final IRendererContext rendererContext_p) {

    if (rendererContext_p instanceof IPolicifiedRendererContext) {
      ((IPolicifiedRendererContext) rendererContext_p).addRendererPolicy(new AbstractRendererPolicy() {

        @Override
        public boolean match(IPropertyGroup group_p) {
          return true;
        }

        @Override
        public IGroupRenderer createRenderer(IPropertyGroup group_p) {
          //Sub groups should not use this renderer !
          for (IPropertyGroup group : rendererContext_p.getPropertyContext().getProperties().getGroups(IPropertyGroup.EMPTY)) {
            if (group.getId().equals(group_p.getParentId())) {
              return new FlatGroupRenderer();
            }
          }
          return new DefaultGroupRenderer() {
            @Override
            protected boolean isDisplayLabel(IPropertyGroup group_p) {
              return true;
            }

            @Override
            protected String getGroupName(IPropertyGroup group_p) {
              return "";
            }
          };
        }

      });
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean select(Object toTest_p) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject object_p) {
    setInput(null, new StructuredSelection(object_p));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setParentBackgroundColor(Color color_p) {
    //Nothing here
  }

}
