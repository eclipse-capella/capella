/*******************************************************************************
 * Copyright (c) 2003, 2020 Corporation and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    IBM - Initial API and implementation 
 *    Thales - Contributor
 *******************************************************************************/
package org.polarsys.capella.core.commands.preferences.util;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.polarsys.capella.core.commands.preferences.internalization.l10n.CustomPreferencesMessages;
import org.polarsys.capella.core.commands.preferences.preferences.ConfigurabilityPreferences;
import org.polarsys.capella.core.commands.preferences.service.AbstractItemDescriptor;
import org.polarsys.capella.core.commands.preferences.service.PreferencesItemsRegistry;
import org.polarsys.capella.core.commands.preferences.xml.IExtensionPointItemDescriptor;
import org.polarsys.capella.core.preferences.commands.exceptions.ItemExistsException;

/**
 * for Item discovery purposes.
 * </p>
 */
public final class ExtensionPointItemDescriptor extends AbstractItemDescriptor
    implements IExtensionPointItemDescriptor {

  static final String ITEM_ID = CustomPreferencesMessages.rule_id;
  static final String ITEM_NAME = CustomPreferencesMessages.rule_name;

  private final IConfigurationElement config;

  private String name;
  private String id;

  private final String pluginId;

  private String description;

  private boolean isEnabledByDefault = true;

  /**
   * Initializes me from the specified Eclipse configuration element.
   * 
   * @param config
   *          the configuration element
   * @throws ItemExistsException
   *           if the <code>config</code>uration element specified a constraint ID that already exists in the system
   */
  public ExtensionPointItemDescriptor(IConfigurationElement config) throws ItemExistsException {
    this.config = config;
    name = config.getAttribute(XmlPreferencesConfig.ITEM_NAME);
    description = config.getAttribute(XmlPreferencesConfig.ITEM_DESCRIPTION);
    pluginId = config.getDeclaringExtension().getNamespaceIdentifier();

    // item ID is required to start with contributing plugin ID
    id = normalizedId(pluginId, config.getAttribute(XmlPreferencesConfig.ITEM_ID));

    id = config.getAttribute(XmlPreferencesConfig.ITEM_ID);

    try {
      assertNotNull(id, pluginId);

      PreferencesItemsRegistry.getInstance().register(this);
      parseIsEnabledByDefault(config);

      // ensure that I get my default enablement state
      setEnabled(ConfigurabilityPreferences.isInstanceScopePreferenceItemEnabled(id));

      if (this.name == null) {
        ICommandService service = PlatformUI.getWorkbench().getService(ICommandService.class);
        Command command = service.getCommand(this.id);
        try {
          this.name = command.getName();
          this.description = command.getDescription();

        } catch (NotDefinedException e1) {
          this.name = this.id;
        }
      }
      if (this.description == null) {
        ICommandService service = PlatformUI.getWorkbench().getService(ICommandService.class);
        Command command = service.getCommand(this.id);
        try {
          this.description = command.getDescription();
        } catch (NotDefinedException e1) {
          // Nothing here
        }
      }

    } catch (CoreException e) {
      // Trace.catching(XmlItemDescriptor.class, "<init>", e); //$NON-NLS-1$
      e.printStackTrace();
      setError(e);

      if (this.id == null) {
        this.id = "$error." + System.identityHashCode(this); //$NON-NLS-1$
      }
    }

    if (this.name == null) {
      this.name = this.id;
    }
  }

  /**
   * Returns the normalized constraint ID, which is prefixed by the contributing plug-in's ID.
   * 
   * @param pluginId
   *          the ID of the plug-in that contributes the constraint
   * @param id
   *          the constraint's ID, as declared in the XML
   * @return the normalized ID, which either the original ID if it already starts with the plug-in ID; the original ID
   *         prefixed by the plug-in ID, otherwise
   */
  public static String normalizedId(String pluginId, String id) {
    assert pluginId != null;
    assert id != null;

    String result = id;

    if (!result.startsWith(pluginId)) {
      result = pluginId + '.' + result;
    }

    return result;
  }

  // implements the interface method
  public IConfigurationElement getConfig() {
    return config;
  }

  // implements the interface method
  public String getName() {
    return name;
  }

  // implements the interface method
  public String getId() {
    return id;
  }

  // implements the interface method
  public String getPluginId() {
    return pluginId;
  }

  // implements the interface method
  public String getDescription() {
    return description;
  }

  private void setDescription(String description) {
    this.description = description;
  }

  public String getLanguage() {
    return getConfig().getAttribute(XmlPreferencesConfig.A_LANG);
  }

  public boolean isEnabledByDefault() {
    return isEnabledByDefault;
  }

  /**
   * Parses the <tt>&lt;constraint&gt;</tt> element's <tt>isEnabledByDefault</tt> attribute from the XML.
   * 
   * @param extConfig
   *          the Eclipse configuration element representing the XML extension data
   */
  private void parseIsEnabledByDefault(IConfigurationElement extConfig) {
    String attr = extConfig.getAttribute(XmlPreferencesConfig.ITEM_IS_ENABLED_BY_DEFAULT);

    boolean result = true;
    if (attr != null && !attr.isEmpty()) {
      result = Boolean.parseBoolean(attr);
    }

    this.isEnabledByDefault = result;
    ConfigurabilityPreferences.setItemDisabledDefault(id, isEnabledByDefault);

  }

}
