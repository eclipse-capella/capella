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
   * @param context
   * @param renderers
   * @param group
   */
  public CapellaPropertySection(IPropertyContext context, IRendererContext renderers, IPropertyGroup group) {
    super(context, renderers, group);
  }

  @Override
  protected void initRendererContext(final IRendererContext rendererContext) {

    if (rendererContext instanceof IPolicifiedRendererContext) {
      ((IPolicifiedRendererContext) rendererContext).addRendererPolicy(new AbstractRendererPolicy() {

        @Override
        public boolean match(IPropertyGroup group) {
          return true;
        }

        @Override
        public IGroupRenderer createRenderer(IPropertyGroup group) {
          //Sub groups should not use this renderer !
          for (IPropertyGroup grp : rendererContext.getPropertyContext().getProperties().getGroups(IPropertyGroup.EMPTY)) {
            if (grp.getId().equals(group.getParentId())) {
              return new FlatGroupRenderer();
            }
          }
          return new DefaultGroupRenderer() {
            @Override
            protected boolean isDisplayLabel(IPropertyGroup group) {
              return true;
            }

            @Override
            protected String getGroupName(IPropertyGroup group) {
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
  public boolean select(Object toTest) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject object) {
    setInput(null, new StructuredSelection(object));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setParentBackgroundColor(Color color) {
    //Nothing here
  }

  @Override
  public void performFinish() {
    // Do nothing
  }

}
