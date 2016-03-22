/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

  IPropertyContext _propertyContext;

  IRenderers _renderers;

  HashMap<IProperty, IPropertyRenderer> _properties2renderers;

  HashMap<IPropertyRenderer, IProperty> _renderers2properties;

  HashMap<IPropertyGroup, IGroupRenderer> _groups2renderers;

  HashMap<IGroupRenderer, IPropertyGroup> _renderers2groups;

  HashMap<String, Object> _parameters = new HashMap<String, Object>();

  IRendererPolicy policy;

  public void addRendererPolicy(IRendererPolicy policy) {
    this.policy = policy;
  }

  public RendererContext(IRenderers renderers, IPropertyContext context) {
    setPropertyContext(context);
    _renderers = renderers;

    _properties2renderers = new HashMap<IProperty, IPropertyRenderer>(0);
    _renderers2properties = new HashMap<IPropertyRenderer, IProperty>(0);

    _groups2renderers = new HashMap<IPropertyGroup, IGroupRenderer>(0);
    _renderers2groups = new HashMap<IGroupRenderer, IPropertyGroup>(0);

    labelProvider = new DefaultLabelProvider();
  }

  public ILabelProvider getLabelProvider() {
    return labelProvider;
  }

  /**
   * @param propertyContext
   */
  public void setPropertyContext(IPropertyContext propertyContext) {

    if ((_propertyContext != null) && (_propertyContext != propertyContext)) {
      _propertyContext.unregisterListener(this);
    }

    HashMap<IProperty, IProperty> map = new HashMap<IProperty, IProperty>();

    if (_propertyContext != propertyContext) {
      if (_propertyContext != null) {

        for (IProperty property : _propertyContext.getProperties().getAllItems()) {
          for (IProperty property2 : propertyContext.getProperties().getAllItems()) {
            if (property.getId().equals(property2.getId()) && !(property.equals(property2))) {
              map.put(property, property2);
              _properties2renderers.put(property2, _properties2renderers.get(property));
              _renderers2properties.put(_properties2renderers.get(property2), property2);
              updatePropertyRenderer(property2.getId());
            }
          }
        }
      }

      _propertyContext = propertyContext;
    }

    if ((_propertyContext != null)) {
      _propertyContext.registerListener(this);
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
    return _renderers2properties.get(renderer);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IPropertyRenderer getRenderer(IProperty property) {
    if (!_properties2renderers.containsKey(property)) {
      IPropertyRenderer renderer = _renderers.createRenderer(property);
      if (renderer == null) {
        renderer = createDefaultRenderer(property);
      }
      _properties2renderers.put(property, renderer);
      _renderers2properties.put(renderer, property);
    }
    return _properties2renderers.get(property);
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
    if (!_groups2renderers.containsKey(propertyGroup)) {
      IGroupRenderer renderer = _renderers.createRenderer(propertyGroup);
      if (renderer == null) {
        renderer = createDefaultRenderer(propertyGroup);
      }
      _groups2renderers.put(propertyGroup, renderer);
      _renderers2groups.put(renderer, propertyGroup);
    }
    return _groups2renderers.get(propertyGroup);
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
    return _renderers2groups.get(renderer);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IRenderers getRenderers() {
    return _renderers;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IPropertyContext getPropertyContext() {
    return _propertyContext;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getParameter(String id) {
    return _parameters.get(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void putParameter(String id, Object value) {
    _parameters.put(id, value);
  }

}
