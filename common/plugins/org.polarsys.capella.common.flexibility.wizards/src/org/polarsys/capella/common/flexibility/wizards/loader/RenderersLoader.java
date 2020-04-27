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

package org.polarsys.capella.common.flexibility.wizards.loader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;
import org.polarsys.capella.common.flexibility.wizards.renderer.Renderers;
import org.polarsys.capella.common.flexibility.wizards.schema.IGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IPropertyRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderers;
import org.polarsys.capella.common.flexibility.wizards.schema.WizardsSchemaConstants;

/**
 */
public class RenderersLoader {

  HashMap<String, Renderers> renderersByProperties = new HashMap<String, Renderers>();

  protected Renderers getStoredProperties(String idProperties) {
    return renderersByProperties.get(idProperties);
  }

  protected void setStoredProperties(String idProperties, Renderers properties) {
    renderersByProperties.put(idProperties, properties);
  }

  protected Renderers getOrCreateStoredProperties(String idProperties) {

    if (getStoredProperties(idProperties) == null) {
      setStoredProperties(idProperties, new Renderers());
    }
    return getStoredProperties(idProperties);
  }

  public IRenderers getRenderers(IProperties properties) {

    HashMap<String, IConfigurationElement> renderersMap = new HashMap<String, IConfigurationElement>();
    HashMap<String, Collection<IConfigurationElement>> renderersBindingMapByProperties = new HashMap<String, Collection<IConfigurationElement>>();
    HashMap<String, IConfigurationElement> renderersBindingMapByProperty = new HashMap<String, IConfigurationElement>();

    IExtensionRegistry registry = Platform.getExtensionRegistry();
    Collection<IConfigurationElement> extensions = new ArrayList<IConfigurationElement>();
    extensions.addAll(Arrays.asList(registry.getConfigurationElementsFor(WizardsSchemaConstants.PropertiesSchema_ID)));
    extensions.addAll(Arrays.asList(registry.getConfigurationElementsFor(WizardsSchemaConstants.PropertiesSchema_SCHEMA_ID)));

    // Retrieve renderers
    for (IConfigurationElement extension : extensions) {
      if (extension.getName().equals(WizardsSchemaConstants.PropertiesSchema_RENDERER)) {
        String idRenderer = extension.getAttribute(WizardsSchemaConstants.PropertiesSchema_RENDERER_ID);
        renderersMap.put(idRenderer, extension);
        System.out.println("flexibility.wizards: migration is required for " + idRenderer);
      }
      if (extension.getName().equals(WizardsSchemaConstants.PropertiesSchema_PROPERTY_RENDERER)) {
        String idRenderer = extension.getAttribute(WizardsSchemaConstants.PropertiesSchema_PROPERTY_RENDERER__ID);
        renderersMap.put(idRenderer, extension);
      }
      if (extension.getName().equals(WizardsSchemaConstants.PropertiesSchema_GROUP_RENDERER)) {
        String idRenderer = extension.getAttribute(WizardsSchemaConstants.PropertiesSchema_GROUP_RENDERER__ID);
        renderersMap.put(idRenderer, extension);
      }
    }

    // Retrieve renderersBindings by IProperties.id
    for (IConfigurationElement extension : extensions) {
      if (extension.getName().equals(WizardsSchemaConstants.PropertiesSchema_RENDERERBINDING)) {
        String propertiesId = extension.getAttribute(WizardsSchemaConstants.PropertiesSchema_RENDERERBINDING_PROPERTIESID);
        Collection<IConfigurationElement> elements = renderersBindingMapByProperties.get(propertiesId);
        if (elements == null) {
          elements = new ArrayList<IConfigurationElement>();
          renderersBindingMapByProperties.put(propertiesId, elements);
        }
        elements.add(extension);
        System.out.println("flexibility.wizards: migration is required for binding to " + propertiesId);
      }

      if (extension.getName().equals(WizardsSchemaConstants.PropertiesSchema_BINDINGS)) {
        String propertiesId = extension.getAttribute(WizardsSchemaConstants.PropertiesSchema_BINDINGS__PROPERTIES);
        Collection<IConfigurationElement> elements = renderersBindingMapByProperties.get(propertiesId);
        if (elements == null) {
          elements = new ArrayList<IConfigurationElement>();
          renderersBindingMapByProperties.put(propertiesId, elements);
        }
        elements.addAll(Arrays.asList(extension.getChildren(WizardsSchemaConstants.PropertiesSchema_PROPERTY_BINDING)));
        elements.addAll(Arrays.asList(extension.getChildren(WizardsSchemaConstants.PropertiesSchema_GROUP_BINDING)));
      }

    }

    // Retrieve most lower renderersBindings by IProperty.id
    LinkedList<IProperties> toVisit = new LinkedList<IProperties>();
    toVisit.add(properties);
    while (toVisit.size() > 0) {
      IProperties parent = toVisit.removeFirst();
      if (parent == null) {
        continue;
      }

      Renderers childRenderers = getOrCreateStoredProperties(parent.getPropertiesId());
      for (IProperties properti : parent.getParents()) {
        Renderers parentRenderers = getOrCreateStoredProperties(properti.getPropertiesId());
        childRenderers.addParent(parentRenderers);
      }

      toVisit.addAll(parent.getParents());

      Collection<IConfigurationElement> elements = renderersBindingMapByProperties.get(parent.getPropertiesId());
      if (elements == null) {
        continue;
      }

      // Create a renderer for each IProperty
      for (IProperty property : properties.getAllItems()) {
        for (IConfigurationElement extension : elements) {
          String propertyId = null;
          String rendererId = null;

          if (extension.getName().equals(WizardsSchemaConstants.PropertiesSchema_RENDERERBINDING)) {
            propertyId = extension.getAttribute(WizardsSchemaConstants.PropertiesSchema_RENDERERBINDING_PROPERTYID);
            rendererId = extension.getAttribute(WizardsSchemaConstants.PropertiesSchema_RENDERERBINDING_RENDERERID);
            System.out.println("flexibility.wizards: migration is required for renderer binding " + rendererId);

          } else if (extension.getName().equals(WizardsSchemaConstants.PropertiesSchema_PROPERTY_BINDING)) {
            propertyId = extension.getAttribute(WizardsSchemaConstants.PropertiesSchema_PROPERTY_BINDING__PROPERTY);
            rendererId = extension.getAttribute(WizardsSchemaConstants.PropertiesSchema_PROPERTY_BINDING__RENDERER);

          }

          if (!property.getId().equals(propertyId)) {
            continue;
          }
          try {
            IConfigurationElement rendererExtention = renderersMap.get(rendererId);
            if (rendererExtention != null) {
              String nameClass = "";
              if (rendererExtention.getName().equals(WizardsSchemaConstants.PropertiesSchema_RENDERER)) {
                nameClass = WizardsSchemaConstants.PropertiesSchema_RENDERER_CLASS;
                System.out.println("flexibility.wizards: migration is required for renderer definition " + extension.getAttribute(nameClass));

              } else if (rendererExtention.getName().equals(WizardsSchemaConstants.PropertiesSchema_PROPERTY_RENDERER)) {
                nameClass = WizardsSchemaConstants.PropertiesSchema_PROPERTY_RENDERER__CLASS;

              }

              IRenderer iRenderer = (IRenderer) rendererExtention.createExecutableExtension(nameClass);
              if ((iRenderer != null) && (iRenderer instanceof IPropertyRenderer)) {
                childRenderers.addPropertyRenderer(property.getId(), (IPropertyRenderer) iRenderer);
              }
            }

          } catch (CoreException exception) {
            exception.printStackTrace();
          }
        }
      }

      // Create a renderer for each IPropertyGroup
      for (IPropertyGroup group : properties.getAllGroups()) {
        for (IConfigurationElement extension : elements) {
          String propertyId = null;
          String rendererId = null;

          if (extension.getName().equals(WizardsSchemaConstants.PropertiesSchema_GROUP_BINDING)) {
            propertyId = extension.getAttribute(WizardsSchemaConstants.PropertiesSchema_GROUP_BINDING__GROUP);
            rendererId = extension.getAttribute(WizardsSchemaConstants.PropertiesSchema_GROUP_BINDING__RENDERER);

          }

          if (!group.getId().equals(propertyId)) {
            continue;
          }
          try {
            IConfigurationElement rendererExtention = renderersMap.get(rendererId);
            if (rendererExtention != null) {
              String nameClass = "";
              if (rendererExtention.getName().equals(WizardsSchemaConstants.PropertiesSchema_GROUP_RENDERER)) {
                nameClass = WizardsSchemaConstants.PropertiesSchema_GROUP_RENDERER__CLASS;
              }

              IRenderer iRenderer = (IRenderer) rendererExtention.createExecutableExtension(nameClass);
              if ((iRenderer != null) && (iRenderer instanceof IGroupRenderer)) {
                childRenderers.addGroupRenderer(group.getId(), (IGroupRenderer) iRenderer);
              }
            }

          } catch (CoreException exception) {
            exception.printStackTrace();
          }
        }
      }

    }

    return getOrCreateStoredProperties(properties.getPropertiesId());
  }

}
