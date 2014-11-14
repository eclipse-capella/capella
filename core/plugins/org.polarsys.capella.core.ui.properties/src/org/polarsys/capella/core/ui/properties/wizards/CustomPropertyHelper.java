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
   * @param metaclass_p
   * @param contributorId_p
   * @return
   */
  public static Map<String, IAbstractSection> getCustomPropertySection(EObject object_p, String contributorId_p) {
    return getCustomPropertySection(object_p, contributorId_p, true);
  }

  /**
   * Get the custom property sections for given parameters.
   * @param metaclass_p
   * @param contributorId_p
   * @param strict_p
   * @return
   */
  public static Map<String, IAbstractSection> getCustomPropertySection(EObject object_p, String contributorId_p, boolean strict_p) {
    Map<String, IAbstractSection> result = new HashMap<String, IAbstractSection>(0);
    // Preconditions
    if ((null == object_p) || (null == contributorId_p)) {
      return result;
    }

    Map<String, List<Object>> map = getAllPropertySections(object_p, contributorId_p, strict_p);
    Iterator<String> it = map.keySet().iterator();
    while (it.hasNext()) {
      String key = it.next();
      List<Object> values = map.get(key);
      if (values.size() > 0) {
        Object value = values.get(0);
        if (value instanceof IAbstractSection) {
          result.put(key, (IAbstractSection) value);
        }
      }
    }

    // we use a TreeMap because the keys are sorted
    // if we don't, the 'Description' tabitem is put before the 'Base' tabitem
    return new TreeMap<String, IAbstractSection>(result);
  }

  /**
   * @param metaclass_p
   * @param contributorId_p
   * @param key_p
   */
  public static AbstractPropertySection getDescriptionSection(EClass metaclass_p, String contributorId_p, String key_p) {
    if (null != contributorId_p) {
      Map<String, List<Object>> map = getAllPropertySections(metaclass_p, contributorId_p, false);
      List<Object> values = map.get(key_p);
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
   * @param metaclass_p
   * @param contributorId_p
   * @param strict_p
   * @return
   */
  private static Map<String, List<Object>> getAllPropertySections(EObject object_p, String contributorId_p, boolean strict_p) {
    Map<String, List<Object>> result = new HashMap<String, List<Object>>();

    String metaClassName = object_p.eClass().getInstanceClassName();
    List<String> allMetaClassNames = getAllMetaClassNames(object_p.eClass());

    IConfigurationElement[] propertySectionsElements =
        ExtensionPointHelper.getConfigurationElements(TabbedPropertyViewPlugin.getPlugin().getBundle().getSymbolicName(), EXTPT_SECTIONS);
    for (IConfigurationElement propertySectionsElement : propertySectionsElements) {
      // Get the contributor id.
      String contributorId = propertySectionsElement.getAttribute(ATT_CONTRIBUTOR_ID);
      // Search for a 'propertySections' matching the capella navigator property contributor id.
      if (contributorId_p.equals(contributorId)) {
        // Get the 'propertySection' Node that deals with current metaclass.
        IConfigurationElement[] sections = propertySectionsElement.getChildren(ELEMENT_SECTION);
        // Loop over contained propertySection.
        for (IConfigurationElement propertySectionElement : sections) {
          String key = propertySectionElement.getAttribute(ELEMENT_TAB);
          IConfigurationElement[] elements = propertySectionElement.getChildren(ELEMENT_INPUT);
          for (IConfigurationElement element : elements) {
            String readMetaClass = element.getAttribute(ATT_INPUT_TYPE);
            // Search one that matches the current metaclass.
            if ((strict_p && metaClassName.equals(readMetaClass)) || (!strict_p && allMetaClassNames.contains(readMetaClass))) {
              // Get the name of tab for this section.
              Object section = ExtensionPointHelper.createInstance(propertySectionElement, ExtensionPointHelper.ATT_CLASS);
              List<Object> lst = result.get(key);
              if (null == lst) {
                lst = new ArrayList<Object>();
              }
              // this list can potentially contains several sections
              // we insert at first position the one that strictly matches 'metaclass_p'
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

    SubPropertiesTabDescriptorProvider _provider = new SubPropertiesTabDescriptorProvider() {

      @Override
      protected ITabDescriptor createTabDescriptor(IPropertyContext context_p, IRendererContext rendererContext_p, IPropertyGroup mainGroup_p) {
        return new PropertiesTabDescriptor(context_p, rendererContext_p, mainGroup_p) {

          @Override
          protected ISectionDescriptor createSectionDescriptor(IPropertyContext context_p, IRendererContext rendererContext_p, IPropertyGroup group_p) {
            return new PropertiesSectionDescriptor(context_p, rendererContext_p, group_p) {

              @Override
              protected ISection createSection(IPropertyContext context_p, IRendererContext renderers_p, IPropertyGroup group_p) {
                return new CapellaPropertySection(context_p, renderers_p, group_p);
              }

            };
          }
        };
      }

    };
    ISelection selection = new StructuredSelection(object_p);

    ITabDescriptor[] descriptors = _provider.getTabDescriptors(null, selection);
    for (ITabDescriptor descriptor : descriptors) {
      for (Object object : descriptor.getSectionDescriptors()) {
        if ((object != null) && (object instanceof ISectionDescriptor)) {
          ISectionDescriptor sectionDescriptor = (ISectionDescriptor) object;
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
   * @param eclass_p
   * @return
   */
  protected static List<String> getAllMetaClassNames(EClass eclass_p) {
    List<String> allNames = new ArrayList<String>();
    for (EClass eclass : eclass_p.getEAllSuperTypes()) {
      allNames.add(eclass.getInstanceClassName());
    }
    allNames.add(eclass_p.getInstanceClassName());
    return allNames;
  }
}
