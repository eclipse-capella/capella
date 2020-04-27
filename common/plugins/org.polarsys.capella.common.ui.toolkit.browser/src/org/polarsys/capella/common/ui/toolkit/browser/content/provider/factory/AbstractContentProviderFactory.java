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

package org.polarsys.capella.common.ui.toolkit.browser.content.provider.factory;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.ui.toolkit.browser.BrowserActivator;
import org.polarsys.capella.common.ui.toolkit.browser.model.ISemanticBrowserModel;

/**
 * Provide an interface for a content provider factory.
 */
public abstract class AbstractContentProviderFactory {
  /**
   * Singleton.
   */
  protected static AbstractContentProviderFactory _instance = null;
  protected ISemanticBrowserModel model;
  private static final String CONTENT_PROVIDER_FACTORY = "contentProviderFactory"; //$NON-NLS-1$

  public abstract ITreeContentProvider getCurrentContentProvider();

  public abstract ITreeContentProvider getReferencedContentProvider();

  public abstract ITreeContentProvider getReferencingContentProvider();

  /**
   * Singleton accessor.
   * @return
   */
  public static AbstractContentProviderFactory getInstance() {
    if (null == _instance) {
      IConfigurationElement[] configurationElements = ExtensionPointHelper.getConfigurationElements(BrowserActivator.PLUGIN_ID, CONTENT_PROVIDER_FACTORY);
      for (IConfigurationElement configurationElement : configurationElements) {
        AbstractContentProviderFactory contentProvider =
            (AbstractContentProviderFactory) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
        if (null != contentProvider) {
          _instance = contentProvider;
        }
      }
    }
    return _instance;
  }
  
  public void setModel(ISemanticBrowserModel model) {
    this.model = model;
  }

}
