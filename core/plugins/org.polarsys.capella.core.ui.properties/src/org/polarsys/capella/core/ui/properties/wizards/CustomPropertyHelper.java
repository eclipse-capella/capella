/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.wizards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.internal.views.properties.tabbed.TabbedPropertyViewPlugin;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;

import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.core.ui.properties.sections.IAbstractSection;
import org.polarsys.capella.core.ui.properties.sections.CapellaPropertySection;
import org.polarsys.capella.core.ui.properties.tabbed.SubPropertiesTabDescriptorProvider;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.ui.tabbed.PropertiesSectionDescriptor;
import org.polarsys.capella.common.flexibility.wizards.ui.tabbed.PropertiesTabDescriptor;

/**
 * Helper to load all sections declared in the platform for a metaclass and a property section contributor id.
 */
public class CustomPropertyHelper {
  private static final String EXTPT_SECTIONS = "propertySections"; //$NON-NLS-1$
  private static final String ATT_CONTRIBUTOR_ID = "contributorId"; //$NON-NLS-1$
  private static final String ELEMENT_SECTION = "propertySection"; //$NON-NLS-1$
  private static final String ELEMENT_INPUT = "input"; //$NON-NLS-1$
  private static final String ELEMENT_TAB = "tab"; //$NON-NLS-1$
  private static final String ATT_INPUT_TYPE = "type"; //$NON-NLS-1$

  /**
   * Get the custom property sections for given parameters.
   * @param metaclass
   * @param contributorId
   * @return
   */
  public static Map<String, IAbstractSection> getCustomPropertySection(EObject object, String contributorId) {
    return getCustomPropertySection(object, contributorId, true);
  }

  /**
   * Get the custom property sections for given parameters.
   * @param metaclass
   * @param contributorId
   * @param strict
   * @return
   */
  public static Map<String, IAbstractSection> getCustomPropertySection(EObject object, String contributorId, boolean strict) {
    Map<String, IAbstractSection> result = new HashMap<String, IAbstractSection>(0);
    // Preconditions
    if ((null == object) || (null == contributorId)) {
      return result;
    }

    Map<String, List<Object>> map = getAllPropertySections(object, contributorId, strict);
    Iterator<String> it = map.keySet().iterator();
    while (it.hasNext()) {
      String key = it.next();
      List<Object> values = map.get(key);
      if (values.size() > 0) {
        for (Object value : values) {
          if (value instanceof IAbstractSection && ((IAbstractSection) value).select(object)) {
            result.put(key, (IAbstractSection) value);
          }
        }
      }
    }

    // we use a TreeMap because the keys are sorted
    // if we don't, the 'Description' tabitem is put before the 'Base' tabitem
    return new TreeMap<String, IAbstractSection>(result);
  }

  /**
   * @param metaclass
   * @param contributorId
   * @param key
   */
  public static AbstractPropertySection getDescriptionSection(EClass metaclass, String contributorId, String key) {
    if (null != contributorId) {
      Map<String, List<Object>> map = getAllPropertySections(metaclass, contributorId, false);
      List<Object> values = map.get(key);
      if (values.size() > 0) {
        Object value = values.get(0);
        if (value instanceof AbstractPropertySection) {
          return (AbstractPropertySection) value;
        }
      }
    }
    return null;
  }

  /**
   * Get all the property sections for given parameters.
   * @param metaclass
   * @param contributorId
   * @param strict
   * @return
   */
  private static Map<String, List<Object>> getAllPropertySections(EObject object, String contributorId, boolean strict) {
    Map<String, List<Object>> result = new HashMap<String, List<Object>>();

    String metaClassName = object.eClass().getInstanceClassName();
    List<String> allMetaClassNames = getAllMetaClassNames(object.eClass());

    IConfigurationElement[] propertySectionsElements =
        ExtensionPointHelper.getConfigurationElements(TabbedPropertyViewPlugin.getPlugin().getBundle().getSymbolicName(), EXTPT_SECTIONS);
    for (IConfigurationElement propertySectionsElement : propertySectionsElements) {
      // Get the contributor id.
      String id = propertySectionsElement.getAttribute(ATT_CONTRIBUTOR_ID);
      // Search for a 'propertySections' matching the capella navigator property contributor id.
      if (contributorId.equals(id)) {
        // Get the 'propertySection' Node that deals with current metaclass.
        IConfigurationElement[] sections = propertySectionsElement.getChildren(ELEMENT_SECTION);
        // Loop over contained propertySection.
        for (IConfigurationElement propertySectionElement : sections) {
          String key = propertySectionElement.getAttribute(ELEMENT_TAB);
          IConfigurationElement[] elements = propertySectionElement.getChildren(ELEMENT_INPUT);
          for (IConfigurationElement element : elements) {
            String readMetaClass = element.getAttribute(ATT_INPUT_TYPE);
            // Search one that matches the current metaclass.
            if ((strict && metaClassName.equals(readMetaClass)) || (!strict && allMetaClassNames.contains(readMetaClass))) {
              // Get the name of tab for this section.
              Object section = ExtensionPointHelper.createInstance(propertySectionElement, ExtensionPointHelper.ATT_CLASS);
              List<Object> lst = result.get(key);
              if (null == lst) {
                lst = new ArrayList<Object>();
              }
              // this list can potentially contains several sections
              // we insert at first position the one that strictly matches 'metaclass'
              if (metaClassName.equals(readMetaClass)) {
                lst.add(0, section);
              } else {
                lst.add(section);
              }
              result.put(key, lst);
            }
          }
        }
      }
    }

    SubPropertiesTabDescriptorProvider provider = new SubPropertiesTabDescriptorProvider() {

      @Override
      protected ITabDescriptor createTabDescriptor(IPropertyContext context, IRendererContext rendererContext, IPropertyGroup mainGroup) {
        return new PropertiesTabDescriptor(context, rendererContext, mainGroup) {

          @Override
          protected ISectionDescriptor createSectionDescriptor(IPropertyContext context, IRendererContext rendererContext, IPropertyGroup group) {
            return new PropertiesSectionDescriptor(context, rendererContext, group) {

              @Override
              protected ISection createSection(IPropertyContext context, IRendererContext renderers, IPropertyGroup group) {
                return new CapellaPropertySection(context, renderers, group);
              }

            };
          }
        };
      }

    };
    ISelection selection = new StructuredSelection(object);

    ITabDescriptor[] descriptors = provider.getTabDescriptors(null, selection);
    for (ITabDescriptor descriptor : descriptors) {
      for (Object obj : descriptor.getSectionDescriptors()) {
        if (obj instanceof ISectionDescriptor) {
          ISectionDescriptor sectionDescriptor = (ISectionDescriptor) obj;
          ISection section = sectionDescriptor.getSectionClass();
          List<Object> lst = result.get(descriptor.getText());
          if (null == lst) {
            lst = new ArrayList<Object>();
          }
          lst.add(section);
          result.put(descriptor.getText(), lst);
        }
      }
    }

    return result;
  }

  /**
   * @param eclass
   * @return
   */
  protected static List<String> getAllMetaClassNames(EClass eclass) {
    List<String> allNames = new ArrayList<String>();
    for (EClass ecls : eclass.getEAllSuperTypes()) {
      allNames.add(ecls.getInstanceClassName());
    }
    allNames.add(eclass.getInstanceClassName());
    return allNames;
  }
}
