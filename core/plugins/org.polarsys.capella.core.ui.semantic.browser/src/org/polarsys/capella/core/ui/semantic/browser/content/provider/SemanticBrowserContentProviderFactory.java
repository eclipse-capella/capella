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
package org.polarsys.capella.core.ui.semantic.browser.content.provider;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.viewers.ITreeContentProvider;

import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.factory.impl.DefaultContentProviderFactory;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.impl.CurrentElementCP;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.impl.ReferencedElementCP;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.impl.ReferencingElementCP;

/**
 * Content provider factory used for the semantic browser.
 */
public class SemanticBrowserContentProviderFactory extends DefaultContentProviderFactory {
  /**
   * Constructor.
   */
  public SemanticBrowserContentProviderFactory() {
    super(MDEAdapterFactory.getAdapterFactory());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITreeContentProvider getCurrentContentProvider() {

    return new CurrentElementCP(getAdapterFactory()) {

      /**
       * {@inheritDoc}
       */
      @Override
      public void notifyChanged(Notification notification_p) {
        if (SemanticBrowserView.isListeningToPageSelectionEvents()) {
          super.notifyChanged(notification_p);
        }
      }
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITreeContentProvider getReferencedContentProvider() {
    return new ReferencedElementCP(getAdapterFactory()) {

      /**
       * {@inheritDoc}
       */
      @Override
      public void notifyChanged(Notification notification_p) {
        if (SemanticBrowserView.isListeningToPageSelectionEvents()) {
          super.notifyChanged(notification_p);
        }
      }
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITreeContentProvider getReferencingContentProvider() {
    return new ReferencingElementCP(getAdapterFactory()) {

      /**
       * {@inheritDoc}
       */
      @Override
      public void notifyChanged(Notification notification_p) {
        if (SemanticBrowserView.isListeningToPageSelectionEvents()) {
          super.notifyChanged(notification_p);
        }
      }
    };
  }
}
