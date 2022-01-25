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
package org.polarsys.capella.common.mdsofa.common.activator;

import org.eclipse.core.runtime.IConfigurationElement;
import org.osgi.framework.BundleContext;

import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.mdsofa.common.helper.IUserEnforcedHelper;
import org.polarsys.capella.common.mdsofa.common.internal.helper.DefaultUserHelper;

/**
 * SolFa common plug-in activator.
 */
public class SolFaCommonActivator extends AbstractActivator {
  /**
   * Extension point "user_helper" short id.
   */
  protected static final String EXTENSION_POINT_SHORT_ID_USER_HELPER = "user_helper"; //$NON-NLS-1$
  /**
   * Shared instance.
   */
  private static SolFaCommonActivator __plugin;
  /**
   * User enforced helper.
   */
  private IUserEnforcedHelper _userHelper;

  /**
   * Get shared instance.
   * @return
   */
  public static SolFaCommonActivator getDefault() {
    return __plugin;
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context_p) throws Exception {
    super.start(context_p);
    __plugin = this;
  }

  /**
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context_p) throws Exception {
    __plugin = null;
    super.stop(context_p);
  }

  /**
   * Get user enforced helper unique implementation.
   * @return null if none could be found.
   */
  public IUserEnforcedHelper getUserEnforcedHelper() {
    // Lazy loading. Search for the implementation.
    if (null == _userHelper) {
      // Get extensions abiding to user helper extension point.
      IConfigurationElement[] configurationElements = ExtensionPointHelper.getConfigurationElements(getBundle().getSymbolicName(), EXTENSION_POINT_SHORT_ID_USER_HELPER);
      if ((null != configurationElements) && (configurationElements.length > 0)) {
        // There should be one implementation only !
        // So take the first one, as expected.
        Object instantiatedClass = ExtensionPointHelper.createInstance(configurationElements[0], ExtensionPointHelper.ATT_CLASS);
        // Make sure this is the correct resulting type.
        if (instantiatedClass instanceof IUserEnforcedHelper) {
          _userHelper = (IUserEnforcedHelper) instantiatedClass;
        }
      }
      // Could not find any user helper, use default one.
      if (null == _userHelper) {
        _userHelper = new DefaultUserHelper();
      }
    }
    return _userHelper;
  }
}
