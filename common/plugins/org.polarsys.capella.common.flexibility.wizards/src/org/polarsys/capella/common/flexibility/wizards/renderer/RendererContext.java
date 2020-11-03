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
package org.polarsys.capella.common.flexibility.wizards.renderer;

import java.util.HashMap;

import org.eclipse.jface.viewers.ILabelProvider;
import org.polarsys.capella.common.flexibility.properties.PropertyChangeListener;
import org.polarsys.capella.common.flexibility.properties.PropertyChangedEvent;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;
import org.polarsys.capella.common.flexibility.wizards.policy.IPolicifiedRendererContext;
import org.polarsys.capella.common.flexibility.wizards.policy.IRendererPolicy;
import org.polarsys.capella.common.flexibility.wizards.schema.IGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IPropertyRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderers;
import org.polarsys.capella.common.flexibility.wizards.ui.DefaultLabelProvider;

/**
 *
 */
public class RendererContext implements IRendererContext, PropertyChangeListener, IPolicifiedRendererContext {

  ILabelProvider labelProvider;
  IPropertyContext propertyContext;
  IRenderers renderers;

  HashMap<IProperty, IPropertyRenderer> properties2renderers;
  HashMap<IPropertyRenderer, IProperty> renderers2properties;
  HashMap<IPropertyGroup, IGroupRenderer> groups2renderers;
  HashMap<IGroupRenderer, IPropertyGroup> renderers2groups;
  HashMap<String, Object> parameters = new HashMap<String, Object>();

  IRendererPolicy policy;

  public void addRendererPolicy(IRendererPolicy policy) {
    this.policy = policy;
  }

  public RendererContext(IRenderers renderers, IPropertyContext context) {
    setPropertyContext(context);
    this.renderers = renderers;
    this.properties2renderers = new HashMap<IProperty, IPropertyRenderer>(0);
    this.renderers2properties = new HashMap<IPropertyRenderer, IProperty>(0);
    this.groups2renderers = new HashMap<IPropertyGroup, IGroupRenderer>(0);
    this.renderers2groups = new HashMap<IGroupRenderer, IPropertyGroup>(0);
    this.labelProvider = new DefaultLabelProvider();
  }

  public ILabelProvider getLabelProvider() {
    return labelProvider;
  }

  /**
   * @param ctx
   */
  public void setPropertyContext(IPropertyContext ctx) {

    if ((propertyContext != null) && (propertyContext != ctx)) {
      propertyContext.unregisterListener(this);
    }

    HashMap<IProperty, IProperty> map = new HashMap<IProperty, IProperty>();

    if (propertyContext != ctx) {
      if (propertyContext != null) {

        for (IProperty property : propertyContext.getProperties().getAllItems()) {
          for (IProperty property2 : ctx.getProperties().getAllItems()) {
            if (property.getId().equals(property2.getId()) && !(property.equals(property2))) {
              map.put(property, property2);
              properties2renderers.put(property2, properties2renderers.get(property));
              renderers2properties.put(properties2renderers.get(property2), property2);
              updatePropertyRenderer(property2.getId());
            }
          }
        }
      }

      propertyContext = ctx;
    }

    if ((propertyContext != null)) {
      propertyContext.registerListener(this);
    }
  }

  public void update(PropertyChangedEvent event) {
    String id = event.getProperty().getId();
    updatePropertyRenderer(id);
  }

  /**
   * @param id
   */
  protected void updatePropertyRenderer(String id) {
    IProperty property = getPropertyContext().getProperties().getProperty(id);

    if (property != null) {
      IPropertyRenderer renderer = getRenderer(property);
      if (renderer != null) {
        renderer.updatedValue(property, this, getPropertyContext().getCurrentValue(property));
      }

      if (property instanceof ICompoundProperty) {
        for (String idSub : ((ICompoundProperty) property).getRelatedProperties()) {
          updatePropertyRenderer(idSub);
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IProperty getProperty(IPropertyRenderer renderer) {
    return renderers2properties.get(renderer);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IPropertyRenderer getRenderer(IProperty property) {
    if (!properties2renderers.containsKey(property)) {
      IPropertyRenderer renderer = renderers.createRenderer(property);
      if (renderer == null) {
        renderer = createDefaultRenderer(property);
      }
      properties2renderers.put(property, renderer);
      renderers2properties.put(renderer, property);
    }
    return properties2renderers.get(property);
  }

  public IPropertyRenderer createDefaultRenderer(IProperty property) {
    if ((policy != null) && policy.match(property)) {
      return policy.createRenderer(property);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IGroupRenderer getRenderer(IPropertyGroup propertyGroup) {
    if (!groups2renderers.containsKey(propertyGroup)) {
      IGroupRenderer renderer = renderers.createRenderer(propertyGroup);
      if (renderer == null) {
        renderer = createDefaultRenderer(propertyGroup);
      }
      groups2renderers.put(propertyGroup, renderer);
      renderers2groups.put(renderer, propertyGroup);
    }
    return groups2renderers.get(propertyGroup);
  }

  public IGroupRenderer createDefaultRenderer(IPropertyGroup propertyGroup) {
    if ((policy != null) && policy.match(propertyGroup)) {
      return policy.createRenderer(propertyGroup);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IPropertyGroup getPropertyGroup(IGroupRenderer renderer) {
    return renderers2groups.get(renderer);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IRenderers getRenderers() {
    return renderers;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IPropertyContext getPropertyContext() {
    return propertyContext;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getParameter(String id) {
    return parameters.get(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void putParameter(String id, Object value) {
    parameters.put(id, value);
  }

}
