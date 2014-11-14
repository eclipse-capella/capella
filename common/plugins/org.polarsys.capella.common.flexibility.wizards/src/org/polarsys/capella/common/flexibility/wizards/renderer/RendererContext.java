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
package org.polarsys.capella.common.flexibility.wizards.renderer;

import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;

import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
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
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;

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

  public void addRendererPolicy(IRendererPolicy policy_p) {
    policy = policy_p;
  }

  public RendererContext(IRenderers renderers_p, IPropertyContext context_p) {
    setPropertyContext(context_p);
    _renderers = renderers_p;

    _properties2renderers = new HashMap<IProperty, IPropertyRenderer>(0);
    _renderers2properties = new HashMap<IPropertyRenderer, IProperty>(0);

    _groups2renderers = new HashMap<IPropertyGroup, IGroupRenderer>(0);
    _renderers2groups = new HashMap<IGroupRenderer, IPropertyGroup>(0);

    labelProvider = new DefaultLabelProvider(new MDEAdapterFactoryLabelProvider(MDEAdapterFactory.getEditingDomain(), MDEAdapterFactory.getAdapterFactory())) {

      /**
       * {@inheritDoc}
       */
      @Override
      public String getText(Object object_p) {
        if (object_p instanceof EObject) {
          return EObjectLabelProviderHelper.getText(object_p);
        }
        return super.getText(object_p);
      }

    };
  }

  public ILabelProvider getLabelProvider() {
    return labelProvider;
  }

  /**
   * @param context_p
   */
  public void setPropertyContext(IPropertyContext propertyContext_p) {

    if ((_propertyContext != null) && (_propertyContext != propertyContext_p)) {
      _propertyContext.unregisterListener(this);
    }

    HashMap<IProperty, IProperty> map = new HashMap<IProperty, IProperty>();

    if (_propertyContext != propertyContext_p) {
      if (_propertyContext != null) {

        for (IProperty property : _propertyContext.getProperties().getAllItems()) {
          for (IProperty property2 : propertyContext_p.getProperties().getAllItems()) {
            if (property.getId().equals(property2.getId()) && !(property.equals(property2))) {
              map.put(property, property2);
              _properties2renderers.put(property2, _properties2renderers.get(property));
              _renderers2properties.put(_properties2renderers.get(property2), property2);
              updatePropertyRenderer(property2.getId());
            }
          }
        }
      }

      _propertyContext = propertyContext_p;
    }

    if ((_propertyContext != null)) {
      _propertyContext.registerListener(this);
    }
  }

  public void update(PropertyChangedEvent event_p) {
    String id = event_p.getProperty().getId();
    updatePropertyRenderer(id);
  }

  /**
   * @param id_p
   */
  protected void updatePropertyRenderer(String id_p) {
    IProperty property = getPropertyContext().getProperties().getProperty(id_p);

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
  public IProperty getProperty(IPropertyRenderer renderer_p) {
    return _renderers2properties.get(renderer_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IPropertyRenderer getRenderer(IProperty property_p) {
    if (!_properties2renderers.containsKey(property_p)) {
      IPropertyRenderer renderer = _renderers.createRenderer(property_p);
      if (renderer == null) {
        renderer = createDefaultRenderer(property_p);
      }
      _properties2renderers.put(property_p, renderer);
      _renderers2properties.put(renderer, property_p);
    }
    return _properties2renderers.get(property_p);
  }

  public IPropertyRenderer createDefaultRenderer(IProperty property_p) {
    if ((policy != null) && policy.match(property_p)) {
      return policy.createRenderer(property_p);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IGroupRenderer getRenderer(IPropertyGroup propertyGroup_p) {
    if (!_groups2renderers.containsKey(propertyGroup_p)) {
      IGroupRenderer renderer = _renderers.createRenderer(propertyGroup_p);
      if (renderer == null) {
        renderer = createDefaultRenderer(propertyGroup_p);
      }
      _groups2renderers.put(propertyGroup_p, renderer);
      _renderers2groups.put(renderer, propertyGroup_p);
    }
    return _groups2renderers.get(propertyGroup_p);
  }

  public IGroupRenderer createDefaultRenderer(IPropertyGroup propertyGroup_p) {
    if ((policy != null) && policy.match(propertyGroup_p)) {
      return policy.createRenderer(propertyGroup_p);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IPropertyGroup getPropertyGroup(IGroupRenderer renderer_p) {
    return _renderers2groups.get(renderer_p);
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
  public Object getParameter(String id_p) {
    return _parameters.get(id_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void putParameter(String id_p, Object value_p) {
    _parameters.put(id_p, value_p);
  }

}
