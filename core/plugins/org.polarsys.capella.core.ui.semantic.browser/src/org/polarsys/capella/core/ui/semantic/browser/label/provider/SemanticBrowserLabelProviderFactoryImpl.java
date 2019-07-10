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
package org.polarsys.capella.core.ui.semantic.browser.label.provider;

import org.eclipse.jface.viewers.ILabelProvider;
import org.polarsys.capella.common.ui.toolkit.browser.label.provider.factory.AbstractLabelProviderFactory;

public class SemanticBrowserLabelProviderFactoryImpl extends AbstractLabelProviderFactory {
  /**
   * Singleton label provider
   */
  private ILabelProvider labelProvider;

  private ILabelProvider getLabelProvider() {
    if (null == labelProvider) {
      labelProvider = new SemanticBrowserLabelProvider();
    }
    return labelProvider;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.label.provider.factory.AbstractLabelProviderFactory#getCurrentLabelProvider()
   */
  @Override
  public ILabelProvider getCurrentLabelProvider() {
    return getLabelProvider();
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.label.provider.factory.AbstractLabelProviderFactory#getDiagramLabelProvider()
   */
  @Override
  public ILabelProvider getDiagramLabelProvider() {
    return getLabelProvider();
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.label.provider.factory.AbstractLabelProviderFactory#getReferencedLabelProvider()
   */
  @Override
  public ILabelProvider getReferencedLabelProvider() {
    return getLabelProvider();
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.browser.label.provider.factory.AbstractLabelProviderFactory#getReferencingLabelProvider()
   */
  @Override
  public ILabelProvider getReferencingLabelProvider() {
    return getLabelProvider();
  }
}
