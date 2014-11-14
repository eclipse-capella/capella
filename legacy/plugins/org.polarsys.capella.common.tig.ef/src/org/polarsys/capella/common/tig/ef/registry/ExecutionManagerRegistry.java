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
package org.polarsys.capella.common.tig.ef.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;
import org.polarsys.capella.common.tig.ef.ExecutionManager;
import org.polarsys.capella.common.tig.ef.IEditingDomainProvider;
import org.polarsys.capella.common.tig.ef.IExecutionFrameworkConstants;

/**
 * Execution manager registry.<br>
 * Allows to get access to a specific {@link ExecutionManager} from its id.<br>
 * Note that this id refers to the id of the {@link IEditingDomainItemProvider} when contributed by an extension.
 */
public class ExecutionManagerRegistry {
  /**
   * Unique instance reference.
   */
  private static ExecutionManagerRegistry __uniqueInstance;
  /**
   * The main map of (String, {@link ExecutionManager}).
   */
  private Map<String, ExecutionManager> _managers;
  /**
   * The artificial map of {@link ExecutionManager} by editor ID.<br>
   * Note that an execution manager being accessible through this map must always by also registered in the main managers map.
   */
  private Map<String, ExecutionManager> _managersForEditors;

  /**
   * Private constructor.
   */
  private ExecutionManagerRegistry() {
    _managers = new HashMap<String, ExecutionManager>(0);
    _managersForEditors = new HashMap<String, ExecutionManager>(0);
  }

  /**
   * Dispose registry content.
   */
  public void dispose() {
    _managers.clear();
    _managersForEditors.clear();
  }

  /**
   * Get {@link ExecutionManagerRegistry} unique instance.
   * @return
   */
  public static ExecutionManagerRegistry getInstance() {
    if (null == __uniqueInstance) {
      __uniqueInstance = new ExecutionManagerRegistry();
    }
    return __uniqueInstance;
  }

  /**
   * Create and register a new {@link ExecutionManager} for given provider.
   * @param provider_p The editing domain provider. Should be not <code>null</code>.
   * @param id_p The new manager unique ID. Should not be <code>null</code>.
   * @param editorIds_p The transactional editor IDs that are to use this execution manager as their model environment. Can be <code>null</code> if no
   *          transactional editor is to be used. Editor IDs are whitespace separated.
   * @return
   */
  public ExecutionManager addNewManager(IEditingDomainProvider provider_p, String id_p, String editorIds_p) {
    // Preconditions.
    Assert.isNotNull(id_p);
    Assert.isNotNull(provider_p);
    // Create execution manager.
    ExecutionManager result = new ExecutionManager(provider_p);
    // Set internal ID.
    result.setId(id_p);
    // Register it by ID.
    _managers.put(id_p, result);
    // Deal with editor IDs.
    if (null != editorIds_p) {
      String[] separatedEditorIds = StringHelper.getTokens(editorIds_p, ICommonConstants.EMPTY_STRING + ICommonConstants.WHITE_SPACE_CHARACTER);
      // Register manager for each editor ID.
      for (String editorId : separatedEditorIds) {
        _managersForEditors.put(editorId, result);
      }
    }
    return result;
  }

  /**
   * Remove manager from all maps.
   * @param id_p The unique manager ID, also being the ID of its editing domain provider. Must be not <code>null</code>.
   * @return The removed manager, or <code>null</code> if none could be found.
   */
  public ExecutionManager removeManager(String id_p) {
    // Precondition.
    Assert.isNotNull(id_p);
    // Remove manager from main map.
    ExecutionManager result = _managers.remove(id_p);
    // By construction, a manager that does not appear in this map, can not be present in editor IDs one.
    // So stop the removal right here.
    if (null == result) {
      return result;
    }
    // Remove it from editor IDs one.
    Set<Entry<String, ExecutionManager>> managerForEditors = _managersForEditors.entrySet();
    List<String> editorIDsToRemove = new ArrayList<String>(0);
    // Cycle through map entries.
    for (Entry<String, ExecutionManager> entry : managerForEditors) {
      // Direct reference comparison.
      if (entry.getValue() == result) {
        editorIDsToRemove.add(entry.getKey());
      }
    }
    // Finally, do remove from map.
    for (String deadEditorId : editorIDsToRemove) {
      _managersForEditors.remove(deadEditorId);
    }
    return result;
  }

  /**
   * Unregister manager for identified editor, if any (manager).
   * @param editorId_p The editor ID, associated with a specific manager. Should not be <code>null</code>.
   * @param The manager ID that has been unregistered for identified editor.<code>null</code> if none registered for given ID.
   */
  public String removeManagerFor(String editorId_p) {
    String result = null;
    // Precondition.
    if (null == editorId_p) {
      return result;
    }
    // Remove manager from editor IDs map.
    ExecutionManager manager = _managersForEditors.remove(editorId_p);
    if (null != manager) {
      result = manager.getId();
    }
    return result;
  }

  /**
   * Get execution manager from id.<br>
   * If none could be found in registry, try to find it by id as a declared extension.
   * @param id_p The manager unique ID (or its editing domain ID).
   * @return The execution manager, or <code>null</code> if none could be found.
   */
  public ExecutionManager getExecutionManager(String id_p) {
    // Precondition.
    Assert.isNotNull(id_p);
    // Get execution manager by id.
    ExecutionManager result = _managers.get(id_p);
    // Not registered yet.
    if (null == result) {
      // Try and load it from an extension.
      IConfigurationElement configurationElement =
                                                   ExtensionPointHelper
                                                       .getConfigurationElement(IExecutionFrameworkConstants.PLUGIN_ID,
                                                                                IExecutionFrameworkConstants.EXTENSION_POINT_ID_EDITING_DOMAIN_PROVIDER, id_p);
      // Too bad, could not get element from id.
      if (null == configurationElement) {
        return result;
      }
      // Try and instantiate the editing domain provider.
      IEditingDomainProvider editingDomainProvider =
                                                     (IEditingDomainProvider) ExtensionPointHelper.createInstance(configurationElement,
                                                                                                                  ExtensionPointHelper.ATT_CLASS);
      // Got it.
      if (null != editingDomainProvider) {
        // Get editor IDs.
        String editorIds = configurationElement.getAttribute(IExecutionFrameworkConstants.EXTENSION_POINT_ATTRIBUTE_EDITOR_IDS);
        // Add manager.
        result = addNewManager(editingDomainProvider, id_p, editorIds);
      }
    }
    return result;
  }

  /**
   * Get execution manager for identified editor.
   * @param editorId_p The editor ID that is to use an execution manager. Should not be <code>null</code>.
   * @return The registered manager id, or <code>null</code> if none.
   */
  public ExecutionManager getExecutionManagerFor(String editorId_p) {
    ExecutionManager result = null;
    // Precondition.
    if (null == editorId_p) {
      return result;
    }
    // Get execution manager by editor id.
    result = _managersForEditors.get(editorId_p);
    // Found in registry.
    if (null != result) {
      return result;
    }
    // Load extensions.
    IConfigurationElement[] configurationElements =
                                                    ExtensionPointHelper
                                                        .getConfigurationElements(IExecutionFrameworkConstants.PLUGIN_ID,
                                                                                  IExecutionFrameworkConstants.EXTENSION_POINT_ID_EDITING_DOMAIN_PROVIDER);
    // Cycle through elements.
    for (IConfigurationElement configurationElement : configurationElements) {
      String editorIds = configurationElement.getAttribute(IExecutionFrameworkConstants.EXTENSION_POINT_ATTRIBUTE_EDITOR_IDS);
      // Found a candidate. Assuming there can be only one.
      if ((null != editorIds) && editorIds.contains(editorId_p)) {
        // Load editing domain.
        IEditingDomainProvider editingDomainProvider =
                                                       (IEditingDomainProvider) ExtensionPointHelper.createInstance(configurationElement,
                                                                                                                    ExtensionPointHelper.ATT_CLASS);
        // Editing domain ID.
        String id = configurationElement.getAttribute(ExtensionPointHelper.ATT_ID);
        // Add new manager, since it should at be discovered at get time otherwise.
        result = addNewManager(editingDomainProvider, id, editorIds);
        break;
      }
    }
    return result;
  }
}
