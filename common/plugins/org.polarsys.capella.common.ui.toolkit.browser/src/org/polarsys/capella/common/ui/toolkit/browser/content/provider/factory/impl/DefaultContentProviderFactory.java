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
  public DefaultContentProviderFactory(AdapterFactory adapterFactory_p) {
    _adapterFactory = adapterFactory_p;
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
    return new CurrentElementCP(getAdapterFactory());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITreeContentProvider getReferencedContentProvider() {
    return new ReferencedElementCP(getAdapterFactory());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITreeContentProvider getReferencingContentProvider() {
    return new ReferencingElementCP(getAdapterFactory());
  }
}
