/*******************************************************************************
 *  Copyright (c) 2007, 2020 LCELB
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 * 
 *  Contributors:
 *      LCELB - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.mdsofa.common.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 */
public class ExtensionPointHelper {
  /**
   * Log4j reference logger.
   */
  private static final Logger _logger = Logger.getLogger(ExtensionPointHelper.class.getPackage().getName());
  /**
   * Define a constant for the attribute named <code>class</code> in ExtensionPoint tab of plug-in xml files.
   */
  public static final String ATT_CLASS = "class"; //$NON-NLS-1$
  /**
   * Define a constant for the attribute named <code>name</code> in ExtensionPoint tab of plug-in xml files.
   */
  public static final String ATT_NAME = "name"; //$NON-NLS-1$
  /**
   * Define a constant for the attribute named <code>id</code> in ExtensionPoint tab of plug-in xml files.
   */
  public static final String ATT_ID = "id"; //$NON-NLS-1$
  /**
   * Define a constant for the element named <code>description</code> in ExtensionPoint tab of plug-in xml files.
   */
  public static final String ELEMENT_DESCRIPTION = "description"; //$NON-NLS-1$

  /**
   * Get the configuration elements for a specified plugin id & extension point id.
   * @param pluginId_p the identifier of the plugin.
   * @param extensionPointId_p the simple identifier of the extension point.
   * @return an array of {@link IConfigurationElement} or an empty array if the extension point does not exist,has no extensions configured, or none of the
   *         extensions contain configuration elements.
   */
  public static IConfigurationElement[] getConfigurationElements(String pluginId_p, String extensionPointId_p) {
    IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
    IConfigurationElement[] configElements = extensionRegistry.getConfigurationElementsFor(pluginId_p, extensionPointId_p);
    return configElements;
  }

  /**
   * Get the extensions for identified plug-in extension point coming from an identified plug-in.
   * @param pluginId_p The identifier of the plug-in declaring the extension point.
   * @param extensionPointId_p The extension point id.
   * @param extensionDeclaringPluginId_p The identifier of the plug-in potentially declaring extensions for this extension point.
   * @return an array of {@link IConfigurationElement}. Empty if no extension could be found with given criteria.
   */
  public static IConfigurationElement[] getConfigurationElements(String pluginId_p, String extensionPointId_p, String extensionDeclaringPluginId_p) {
    List<IConfigurationElement> resultingElements = new ArrayList<IConfigurationElement>(0);
    IConfigurationElement[] allElements = getConfigurationElements(pluginId_p, extensionPointId_p);
    for (IConfigurationElement configurationElement : allElements) {
      if (isDeclaredBy(configurationElement, extensionDeclaringPluginId_p)) {
        resultingElements.add(configurationElement);
      }
    }
    return resultingElements.toArray(new IConfigurationElement[resultingElements.size()]);
  }

  /**
   * Is given configuration element declared by identified plug-in ?<br>
   * That is, is it read from the plugin.xml coming with identified plug-in ?
   * @param configurationElement_p
   * @param pluginId_p
   * @return
   */
  public static boolean isDeclaredBy(IConfigurationElement configurationElement_p, String pluginId_p) {
    return (null != configurationElement_p) && (null != pluginId_p) && pluginId_p.equals(configurationElement_p.getContributor().getName());
  }

  /**
   * Get the configuration element for specified parameters.
   * @param pluginId_p the identifier of the plugin.
   * @param extensionId_p the short identifier of the extension point.
   * @param idValue_p the value of the 'id' attribute declared into the extension point.
   * @return a {@link IConfigurationElement} instance or null if the specified extension is not found.
   */
  public static IConfigurationElement getConfigurationElement(String pluginId_p, String extensionId_p, String idValue_p) {
    return getConfigurationElement(pluginId_p, extensionId_p, ATT_ID, idValue_p);
  }

  /**
   * Get the configuration element for specified parameters.
   * @param pluginId_p the identifier of the plugin.
   * @param extensionId_p the short identifier of the extension point.
   * @param attName_p the attribute name.
   * @param attValue_p the attribute value.
   * @return a {@link IConfigurationElement} instance or null if the specified extension is not found.
   */
  public static IConfigurationElement getConfigurationElement(String pluginId_p, String extensionId_p, String attName_p, String attValue_p) {
    IConfigurationElement[] configElements = getConfigurationElements(pluginId_p, extensionId_p);
    IConfigurationElement result = null;
    for (int i = 0; i < configElements.length && (null == result); i++) {
      IConfigurationElement configElement = configElements[i];
      // Check if attribute value is the expected one.
      if (hasValue(configElement, attName_p, attValue_p)) {
        result = configElement;
      }
    }
    return result;
  }

  /**
   * Is given configuration element containing an attribute named <code>attName_p</code> with value set to given one ?
   * @param configurationElement_p
   * @param attName_p
   * @param attValue_p
   * @return
   */
  public static boolean hasValue(IConfigurationElement configurationElement_p, String attName_p, String attValue_p) {
    boolean result = false;
    // Pre-conditions.
    if ((null == configurationElement_p) || (null == attName_p) || (null == attValue_p)) {
      return result;
    }
    // Get attribute value for the configuration element.
    String attValue = configurationElement_p.getAttribute(attName_p);
    // Compare it with given one.
    result = attValue_p.equals(attValue);
    return result;
  }

  /**
   * Return the value of the "id" attribute for given configuration element.
   * @param configurationElement_p
   * @return null if given element is null, or has no attribute named "id".
   */
  public static String getId(IConfigurationElement configurationElement_p) {
    String result = null;
    if (null != configurationElement_p) {
      result = configurationElement_p.getAttribute(ATT_ID);
    }
    return result;
  }

  /**
   * Create an executable extension for a specified plugin's id & extension point id, matching an attribute's value.<br>
   * The extension point must define an attribute 'class'.
   * @param pluginId_p the identifier of the plugin.
   * @param extensionPointId_p the simple identifier of the extension point.
   * @param attributeId_p the identifier of the attribute used into the comparison. If null, matching is not performed.
   * @param attributeValue_p the value of the attribute used for comparison matching. If null, matching is not performed.
   * @return an instance of the interface regarding the attribute 'class'
   * @see ATT_CLASS
   */
  public static Object createExecutableExtension(String pluginId_p, String extensionPointId_p, String attributeId_p, String attributeValue_p) {
    Object object = null;
    IConfigurationElement[] configElements = getConfigurationElements(pluginId_p, extensionPointId_p);

    if (configElements.length == 0) {
      StringBuffer loggerMessage = new StringBuffer("ExtensionPointHelper.createExecutableExtension(..) _ "); //$NON-NLS-1$
      loggerMessage.append("extensionPointId:"); //$NON-NLS-1$
      loggerMessage.append(extensionPointId_p);
      loggerMessage.append(", must exist!"); //$NON-NLS-1$
      _logger.error(loggerMessage.toString());
    } else {
      boolean isMatchingImperative = false;
      // Test if matching is imperative
      if (null != attributeId_p && null != attributeValue_p) {
        isMatchingImperative = true;
      }
      // Loop over configuration until object is created.
      for (int i = 0; i < configElements.length && (null == object); i++) {
        IConfigurationElement configElement = configElements[i];
        boolean isExecutableExtensionCreatable = true;

        if (isMatchingImperative) {
          // Perform matching on the attribute defined by an identifier
          String attributeValue = configElement.getAttribute(attributeId_p);
          // If different, do not instantiate the object.
          if (!attributeValue_p.equals(attributeValue)) {
            isExecutableExtensionCreatable = false;
          }
        }
        if (isExecutableExtensionCreatable) {
          object = createInstance(configElement, ATT_CLASS);
        }
      }
    }
    return object;
  }

  /**
   * Create an executable extension for given parameters.<br>
   * The extension point must define an attribute 'class'.
   * @param pluginId_p the plug-in that exposes the extension point.
   * @param extensionPointId_p the extension point containing an attribute 'class' to instantiate.
   * @param idValue_p the extension id value (attribute 'id') that matches the extension point estensionPointId_p.<br>
   *          If null, comparison is not performed.
   * @return an Object instance if instantiation is successful; null otherwise.
   */
  public static Object createExecutableExtension(String pluginId_p, String extensionPointId_p, String idValue_p) {
    return createExecutableExtension(pluginId_p, extensionPointId_p, ATT_ID, idValue_p);
  }

  /**
   * Create an object instance for specified parameters.
   * @param configurationElement_p a configuration element loaded from an extension point.
   * @param attributeName_p the attribute that hosts the java type.
   * @return an Object instance if instantiation is successful; null otherwise.
   */
  public static Object createInstance(IConfigurationElement configurationElement_p, String attributeName_p) {
    Object typeInstance = null;
    try {
      typeInstance = configurationElement_p.createExecutableExtension(attributeName_p);
    } catch (CoreException exception_p) {
      StringBuffer loggerMessage = new StringBuffer("ExtensionPointHelper.createsInstance(..) _ "); //$NON-NLS-1$
      loggerMessage.append("Unable to instantiate class type:"); //$NON-NLS-1$
      loggerMessage.append(configurationElement_p.getAttribute(attributeName_p));
      _logger.error(loggerMessage.toString(), exception_p);
    }
    return typeInstance;
  }

  /**
   * Return the fully qualified extension-point id from given parameters.
   * @param hostingPluginId_p the id of the plug-in that defines the extension-point.
   * @param extensionPointId_p the short id of the extension-point.
   * @return the fully qualified extension-point id : <code>'plug-in id.'extension-point id'.
   */
  public static String getExtensionPointId(String hostingPluginId_p, String extensionPointId_p) {
    StringBuilder result = new StringBuilder(hostingPluginId_p);
    result.append(ICommonConstants.POINT_CHARACTER).append(extensionPointId_p);
    return result.toString();
  }
}
