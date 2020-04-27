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

package org.polarsys.capella.common.helpers.selection;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.HelperPlugin;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

/**
 * Singleton that deals with {@link ILinkSelection} contributions.
 */
public class LinkSelectionProvider {
  /**
   * Singleton instance.
   */
  private static LinkSelectionProvider __instance;
  /**
   * Constant that defines the LINK_SELECT extension-point short id value <code>LinkSelection</code>.
   */
  private static final String LINK_SELECT_EXTENSION_POINT_SHORT_ID = "LinkSelection"; //$NON-NLS-1$
  /**
   * Cache of loaded {@link ILinkSelection}.
   */
  private List<ILinkSelection> _linkSelectionContributions;

  /**
   * Constructor.
   */
  private LinkSelectionProvider() {
    // Do nothing.
  }

  /**
   * Get All contributions.
   */
  public List<ILinkSelection> getAllContributions() {
    if (null == _linkSelectionContributions) {
      _linkSelectionContributions = new ArrayList<ILinkSelection>();
      IConfigurationElement[] LinkSelectionContributions =
          ExtensionPointHelper.getConfigurationElements(HelperPlugin.getDefault().getPluginId(), LINK_SELECT_EXTENSION_POINT_SHORT_ID);
      // Loop over found contributions.
      for (IConfigurationElement contribution : LinkSelectionContributions) {
        // Instantiate the contribution.
        ILinkSelection linkSelection =
            (ILinkSelection) ExtensionPointHelper.createInstance(contribution, org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper.ATT_CLASS);
        if (null != linkSelection) {
          // Add it to loaded ones.
          _linkSelectionContributions.add(linkSelection);
        }
      }
    }
    return _linkSelectionContributions;
  }

  /**
   * Get a contribution for given meta-class.
   * @return <code>null</code> if not found.
   */
  public ILinkSelection getContribution(EClass metaclass) {
    List<ILinkSelection> allContributions = getAllContributions();
    EClass matchedEClass = null;
    ILinkSelection returnedSelection = null;
    for (ILinkSelection contrib : allContributions) {
      if (EcoreUtil2.isEqualOrSuperClass(contrib.getEClass(), metaclass)) {
        {
          if ((null == matchedEClass) || (EcoreUtil2.isEqualOrSuperClass(matchedEClass, contrib.getEClass()))) {
            matchedEClass = contrib.getEClass();
            returnedSelection = contrib;
          }
        }
      }
    }
    return returnedSelection;
  }

  /**
   * Get the singleton instance.
   */
  public static LinkSelectionProvider getInstance() {
    if (__instance == null) {
      __instance = new LinkSelectionProvider();
    }
    return __instance;
  }
}
