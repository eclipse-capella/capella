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
package org.polarsys.capella.common.ui.toolkit.browser.label.provider.factory;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.ILabelProvider;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.ui.toolkit.browser.BrowserActivator;

/**
 */
public abstract class AbstractLabelProviderFactory {

  /**
   * Singleton.
   */
  protected static AbstractLabelProviderFactory _instance = null;
  private static final String LABEL_PROVIDER_FACTORY = "labelProviderFactory"; //$NON-NLS-1$

  public abstract ILabelProvider getCurrentLabelProvider();

  public abstract ILabelProvider getDiagramLabelProvider();

  public abstract ILabelProvider getReferencedLabelProvider();

  public abstract ILabelProvider getReferencingLabelProvider();

  /**
   * Singleton accessor.
   * @return
   */
  public static AbstractLabelProviderFactory getInstance() {
    if (null == _instance) {
      IConfigurationElement[] configurationElements = ExtensionPointHelper.getConfigurationElements(BrowserActivator.getDefault().getBundle().getSymbolicName(), LABEL_PROVIDER_FACTORY);
      for (IConfigurationElement configurationElement : configurationElements) {
        AbstractLabelProviderFactory labelProvider =
            (AbstractLabelProviderFactory) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
        if (null != labelProvider) {
          _instance = labelProvider;
        }
      }
    }
    return _instance;
  }

}
