/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.flexibility.wizards.ui.tabbed;

import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
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

  RenderersLoader rendererLoader = new RenderersLoader();

  IProperties properties = null;

  Collection<Object> source = null;

  String identifier = null;

  ObjectPropertiesLoader propertiesLoader = new ObjectPropertiesLoader() {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String adapt(String canonicalName) {
      return ObjectPropertiesTabDescriptorProvider.this.adapt(canonicalName);
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
    if (propertyContext == null) {
      propertyContext = createContext(getProperties(source));
    }
    return propertyContext;
  }

  @Override
  public IProperties getProperties(Collection<Object> selection) {
    Collection<Object> sel = selection;

    if (source != sel) {
      source = sel;

      // Compute a propertiesId for the selection
      String id = propertiesLoader.getIdentifier(source);

      if (identifier != id) {
        // If different from previously stored identifier, we recompute an properties (already stored in cache of PropertiesLoader) and renderers (recomputed)
        properties = propertiesLoader.getProperties(id);
        renderers = null;
      }

      identifier = id;
      propertyContext = null;
      rendererContext = null;
    }

    if (properties == null) {
      properties = new Properties("");
    }
    return properties;
  }

  private class ContextAdapter extends AdapterImpl implements PropertyChangeListener {

    IPropertyContext context;
    boolean isEnabled = true;

    ContextAdapter(IPropertyContext context) {
      this.context = context;
      context.registerListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyChanged(Notification msg) {
      super.notifyChanged(msg);
      try {
        isEnabled = false;
        if ((context != null) && (context.getProperties() != null)) {
          for (IProperty property : context.getProperties().getAllItems()) {
            EStructuralFeature feature = (EStructuralFeature) msg.getFeature();
            if ((feature != null) && (feature.getName() != null)) {
              if ((property != null) && (property instanceof EStructuralFeatureProperty)) {
                EStructuralFeatureProperty featureProperty = (EStructuralFeatureProperty) property;
                // we update property modifying the related feature
                // we may check the eClass too.
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
    public void update(final PropertyChangedEvent event) {
      if (!isEnabled) {
        return;
      }
      if ((context != null) && (event.getProperty() != null) && context.isModified(event.getProperty())) {
        AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
          /**
           * {@inheritDoc}
           */
          @Override
          public String getName() {
            return "Modify " + event.getProperty().getName();
          }

          public void run() {
            context.write(event.getProperty());
          }
        };
        TransactionHelper.getExecutionManager((Collection) context.getSourceAsList()).execute(cmd);
      }
    }
  }

  /**
   * @param properties
   * @return
   */
  protected IPropertyContext createContext(IProperties properties) {
    final IPropertyContext context = new PropertyContext(properties) {

      Adapter adapter = new ContextAdapter(this);

      /**
       * {@inheritDoc}
       */
      @Override
      public void setSource(Object source) {
        Object root = source;
        if ((getSource() == source)) {
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
   * @param properties
   * @return
   */
  @Override
  protected IRenderers createRenderers(IProperties properties) {
    return rendererLoader.getRenderers(properties);
  }

  protected String adapt(String canonicalName) {
    return canonicalName;
  }

  @Override
  protected Object adapt(Object source) {
    return this.source;
  }

}
