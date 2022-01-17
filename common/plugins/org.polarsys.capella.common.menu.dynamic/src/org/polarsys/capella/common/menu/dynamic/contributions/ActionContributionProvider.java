/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.menu.dynamic.contributions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EClass;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

/**
 * 
 */
public class ActionContributionProvider {
  /**
   * Contribution menu item extension-point id.
   */
  static final String MENU_ITEM_CONTRIBUTION_EXTENSION_ID = "MDEMenuItemContribution"; //$NON-NLS-1$
  /**
   * Singleton instance.
   */
  private static ActionContributionProvider __instance;
  /**
   * Map of contributed menu items per metaclass.
   */
  private Map<EClass, List<IMDEMenuItemContribution>> _menuContributions;

  /**
   * Get the singleton instance.
   */
  public static ActionContributionProvider getInstance() {
    if (null == __instance) {
      __instance = new ActionContributionProvider();
    }
    return __instance;
  }

  /**
   * Constructor.
   */
  private ActionContributionProvider() {
    // Instantiate the map that holds all contributions.
    _menuContributions = new HashMap<EClass, List<IMDEMenuItemContribution>>(0);
    // Load contributions.
    IConfigurationElement[] contributors = ExtensionPointHelper.getConfigurationElements(FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), MENU_ITEM_CONTRIBUTION_EXTENSION_ID);
    for (IConfigurationElement contributorElement : contributors) {
      // Instantiate current contribution.
      IMDEMenuItemContribution contribution =
          (IMDEMenuItemContribution) ExtensionPointHelper.createInstance(contributorElement, ExtensionPointHelper.ATT_CLASS);
      EClass relatedMetaclass = contribution.getMetaclass();
      // Get existing contributions for related metaclass
      List<IMDEMenuItemContribution> menuContributionsForRelatedMetaclass = _menuContributions.get(relatedMetaclass);
      if (null == menuContributionsForRelatedMetaclass) {
        // No existing contributions, initialize a new collection.
        menuContributionsForRelatedMetaclass = new ArrayList<IMDEMenuItemContribution>(1);
        _menuContributions.put(relatedMetaclass, menuContributionsForRelatedMetaclass);
      }
      // Add current contribution for the related metaclass.
      menuContributionsForRelatedMetaclass.add(contribution);
    }
  }

  /**
   * Get all contributed actions for given metaclass.
   * @param cls
   * @return a not <code>null</code> list.
   */
  public List<IMDEMenuItemContribution> getAllActionContributions(EClass cls) {
    List<IMDEMenuItemContribution> result = new ArrayList<IMDEMenuItemContribution>();
    Iterator<Entry<EClass, List<IMDEMenuItemContribution>>> entries = _menuContributions.entrySet().iterator();
    // Iterate over all contributed menus.
    while (entries.hasNext()) {
      Map.Entry<EClass, java.util.List<IMDEMenuItemContribution>> entry = entries.next();
      // Get the metaclass.
      EClass currentMetaclass = entry.getKey();
      // Select this entry if criteria match.
      if ((currentMetaclass.isAbstract() && currentMetaclass.isSuperTypeOf(cls)) || currentMetaclass.equals(cls)) {
        result.addAll(entry.getValue());
      }
    }
    return result;
  }
}
