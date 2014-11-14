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
import org.polarsys.capella.common.ui.toolkit.viewers.AbstractTooltipLabelProvider;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;

/**
 */
public class SemanticBrowserLabelProviderFactoryImpl extends AbstractLabelProviderFactory {
  /**
   * Label provider implementation.
   */
  private ILabelProvider _labelProvider;

  private ILabelProvider getLabelProvider() {
    if (null == _labelProvider) {
      _labelProvider =
          new AbstractTooltipLabelProvider(new SemanticBrowserLabelProvider(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory())) {
            @Override
            public String getToolTipText(Object element_p) {
              ILabelProvider labelProvider = getLabelProvider();
              if (labelProvider instanceof SemanticBrowserLabelProvider) {
                return ((SemanticBrowserLabelProvider) labelProvider).getToolTipText(element_p);
              }
              return null;
            }
          };
    }
    return _labelProvider;
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
