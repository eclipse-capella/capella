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
package org.polarsys.capella.core.ui.semantic.browser.label.provider;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ui.toolkit.browser.label.provider.factory.AbstractLabelProviderFactory;

public class SemanticBrowserLabelProviderFactoryImpl extends AbstractLabelProviderFactory {
  /**
   * Singleton label provider
   */
  private ILabelProvider currentLabelProvider;
  private ILabelProvider diagramLabelProvider;
  private ILabelProvider referencingLabelProvider;
  private ILabelProvider referencedLabelProvider;

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.label.provider.factory.AbstractLabelProviderFactory#getCurrentLabelProvider()
   */
  @Override
  public ILabelProvider getCurrentLabelProvider() {
    if (null == currentLabelProvider) {
      currentLabelProvider = new SemanticBrowserDecoratingLabelProvider(new SemanticBrowserLabelProvider());
    }
    return currentLabelProvider;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.label.provider.factory.AbstractLabelProviderFactory#getDiagramLabelProvider()
   */
  @Override
  public ILabelProvider getDiagramLabelProvider() {
    if (null == diagramLabelProvider) {
      diagramLabelProvider = new SemanticBrowserDecoratingLabelProvider(new SemanticBrowserLabelProvider());
    }
    return diagramLabelProvider;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.label.provider.factory.AbstractLabelProviderFactory#getReferencedLabelProvider()
   */
  @Override
  public ILabelProvider getReferencedLabelProvider() {
    if (null == referencingLabelProvider) {
      referencingLabelProvider = new SemanticBrowserDecoratingLabelProvider(new SemanticBrowserLabelProvider());
    }
    return referencingLabelProvider;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.label.provider.factory.AbstractLabelProviderFactory#getReferencingLabelProvider()
   */
  @Override
  public ILabelProvider getReferencingLabelProvider() {
    if (null == referencedLabelProvider) {
      referencedLabelProvider = new SemanticBrowserDecoratingLabelProvider(new SemanticBrowserLabelProvider());
    }
    return referencedLabelProvider;
  }
}
