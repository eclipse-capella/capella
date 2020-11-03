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
package org.polarsys.capella.common.ui.toolkit.internal;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.selection.ILinkSelection;
import org.polarsys.capella.common.helpers.selection.LinkSelectionProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.ITreeContentAdapter;

/**
 * Adapter factory to adapt {@link ILinkSelection} to {@link ITreeContentAdapter}.
 */
public class LinkSelectionAdapterFactory implements IAdapterFactory {
  /**
   * Wrapper class to adapt an {@link ILinkSelection} to {@link ITreeContentAdapter}
   */
  class LinkSelectionWrapper implements ITreeContentAdapter {
    private ILinkSelection _linkSelection;

    /**
     * Constructor.
     * @param linkSelection
     */
    public LinkSelectionWrapper(ILinkSelection linkSelection) {
      _linkSelection = linkSelection;
    }

    /**
     * {@inheritDoc}
     */
    public Object getParent(Object child, Object context) {
      Object result = null;
      // Preconditions:
      if (!(child instanceof EObject)) {
        return result;
      }
      if (context instanceof EObject) {
        result = _linkSelection.getDisplayedTarget((EObject) child, (EObject) context);
      }
      return result;
    }
  }

  /**
   * {@inheritDoc}
   */
  public Object getAdapter(Object adaptableObject, Class adapterType) {
    Object result = null;
    // Handle Link Selection adaptation.
    if ((adaptableObject instanceof EClass) && ITreeContentAdapter.class.equals(adapterType)) {
      ILinkSelection linkSelection = LinkSelectionProvider.getInstance().getContribution((EClass) adaptableObject);
      if (null != linkSelection) {
        result = new LinkSelectionWrapper(linkSelection);
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public Class[] getAdapterList() {
    return new Class[] { ITreeContentAdapter.class };
  }
}
