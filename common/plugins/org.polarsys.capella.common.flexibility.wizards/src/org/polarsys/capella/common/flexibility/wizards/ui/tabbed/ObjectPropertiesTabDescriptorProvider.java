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
package org.polarsys.capella.common.flexibility.wizards.ui.tabbed;

import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.flexibility.properties.PropertyChangeListener;
import org.polarsys.capella.common.flexibility.properties.PropertyChangedEvent;
import org.polarsys.capella.common.flexibility.properties.loader.ObjectPropertiesLoader;
import org.polarsys.capella.common.flexibility.properties.property.EStructuralFeatureProperty;
import org.polarsys.capella.common.flexibility.properties.property.Properties;
import org.polarsys.capella.common.flexibility.properties.property.PropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.loader.RenderersLoader;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderers;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 * 
 */
public class ObjectPropertiesTabDescriptorProvider extends PropertiesTabDescriptorProvider {

  RenderersLoader _rendererLoader = new RenderersLoader();

  IProperties _properties = null;

  Collection<Object> source = null;

  String _identifier = null;

  ObjectPropertiesLoader _propertiesLoader = new ObjectPropertiesLoader() {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String adapt(String canonicalName_p) {
      return ObjectPropertiesTabDescriptorProvider.this.adapt(canonicalName_p);
    }

  };

  /**
   * {@inheritDoc}
   */
  @Override
  public IRendererContext getRendererContext() {
    IRendererContext context = super.getRendererContext();
    IPropertyContext pContext = getPropertyContext();
    if (pContext != context.getPropertyContext()) {
      context.setPropertyContext(pContext);
    }
    return context;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IPropertyContext getPropertyContext() {
    if (_protertyContext == null) {
      _protertyContext = createContext(getProperties(source));
    }
    return _protertyContext;
  }

  @Override
  public IProperties getProperties(Collection<Object> selection_p) {
    Collection<Object> selection = selection_p;

    if (source != selection) {
      source = selection;

      //Compute a propertiesId for the selection
      String identifier = _propertiesLoader.getIdentifier(source);

      if (_identifier != identifier) {
        //If different from previously stored identifier, we recompute an properties (already stored in cache of PropertiesLoader) and renderers (recomputed)
        _properties = _propertiesLoader.getProperties(identifier);
        _renderers = null;
      }

      _identifier = identifier;
      _protertyContext = null;
      _rendererContext = null;
    }

    if (_properties == null) {
      _properties = new Properties("");
    }
    return _properties;
  }

  private class ContextAdapter extends AdapterImpl implements PropertyChangeListener {

    IPropertyContext context;
    boolean isEnabled = true;

    ContextAdapter(IPropertyContext context_p) {
      context = context_p;
      context.registerListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyChanged(Notification msg_p) {
      super.notifyChanged(msg_p);
      try {
        isEnabled = false;
        if ((context != null) && (context.getProperties() != null)) {
          for (IProperty property : context.getProperties().getAllItems()) {
            EStructuralFeature feature = (EStructuralFeature) msg_p.getFeature();
            if ((feature != null) && (feature.getName() != null)) {
              if ((property != null) && (property instanceof EStructuralFeatureProperty)) {
                EStructuralFeatureProperty featureProperty = (EStructuralFeatureProperty) property;
                //we update property modifying the related feature
                //we may check the eClass too.
                if (feature.getName().equals(featureProperty.getRelatedEReference())) {
                  ((PropertyContext) context).notifyListeners(property);
                }
              }
            }
          }
        }

      } finally {
        isEnabled = true;
      }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final PropertyChangedEvent event_p) {
      if (!isEnabled) {
        return;
      }
      if ((context != null) && (event_p.getProperty() != null) && context.isModified(event_p.getProperty())) {
    	AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
          /**
           * {@inheritDoc}
           */
          @Override
          public String getName() {
            return "Modify " + event_p.getProperty().getName();
          }

          public void run() {
            context.write(event_p.getProperty());
          }
        };
        ExecutionManager em = ExecutionManagerRegistry.getInstance().addNewManager();
        em.execute(cmd);
        ExecutionManagerRegistry.getInstance().removeManager(em);
      }
    }
  }

  /**
   * @param properties_p
   * @return
   */
  protected IPropertyContext createContext(IProperties properties_p) {
    final IPropertyContext context = new PropertyContext(properties_p) {

      Adapter adapter = new ContextAdapter(this);

      /**
       * {@inheritDoc}
       */
      @Override
      public void setSource(Object source_p) {
        Object root = source_p;
        if ((getSource() == source_p)) {
          return;
        }
        root = adapt(root);

        if (root instanceof EObject) {
          ((SemanticEditingDomain) TransactionHelper.getEditingDomain((EObject) root)).getDataNotifier().addAdapter((EObject) root, adapter);
        }
        super.setSource(root);
      }

    };

    return context;
  }

  /**
   * @param properties_p
   * @return
   */
  @Override
  protected IRenderers createRenderers(IProperties properties_p) {
    return _rendererLoader.getRenderers(properties_p);
  }

  protected String adapt(String canonicalName_p) {
    return canonicalName_p;
  }

  @Override
  protected Object adapt(Object source_p) {
    return source;
  }

}
