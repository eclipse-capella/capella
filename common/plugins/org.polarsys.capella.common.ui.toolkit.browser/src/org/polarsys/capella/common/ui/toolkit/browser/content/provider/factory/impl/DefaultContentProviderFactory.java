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

package org.polarsys.capella.common.ui.toolkit.browser.content.provider.factory.impl;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.factory.AbstractContentProviderFactory;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.impl.CurrentElementCP;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.impl.ReferencedElementCP;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.impl.ReferencingElementCP;

/**
 * Default implementation of a ContentProvideFactory for SemanticBrowser.
 */
public abstract class DefaultContentProviderFactory extends AbstractContentProviderFactory {
  private AdapterFactory _adapterFactory;

  /**
   * Constructor.
   */
  public DefaultContentProviderFactory(AdapterFactory adapterFactory) {
    _adapterFactory = adapterFactory;
  }

  /**
   * Get the adapter factory.
   * @return the adapterFactory
   */
  protected AdapterFactory getAdapterFactory() {
    return _adapterFactory;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITreeContentProvider getCurrentContentProvider() {
    return new CurrentElementCP(getAdapterFactory(), model);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITreeContentProvider getReferencedContentProvider() {
    return new ReferencedElementCP(getAdapterFactory(), model);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITreeContentProvider getReferencingContentProvider() {
    return new ReferencingElementCP(getAdapterFactory(), model);
  }
}
