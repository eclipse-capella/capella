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
     * @param linkSelection_p
     */
    public LinkSelectionWrapper(ILinkSelection linkSelection_p) {
      _linkSelection = linkSelection_p;
    }

    /**
     * {@inheritDoc}
     */
    public Object getParent(Object child_p, Object context_p) {
      Object result = null;
      // Preconditions:
      if (!(child_p instanceof EObject)) {
        return result;
      }
      EObject context = null;
      try {
        context = (null != context_p) ? (EObject) context_p : null;
      } catch (ClassCastException exception_p) {
        return result;
      }
      result = _linkSelection.getDisplayedTarget((EObject) child_p, context);
      return result;
    }
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public Object getAdapter(Object adaptableObject_p, Class adapterType_p) {
    Object result = null;
    // Handle Link Selection adaptation.
    if ((adaptableObject_p instanceof EClass) && ITreeContentAdapter.class.equals(adapterType_p)) {
      ILinkSelection linkSelection = LinkSelectionProvider.getInstance().getContribution((EClass) adaptableObject_p);
      if (null != linkSelection) {
        result = new LinkSelectionWrapper(linkSelection);
      }
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public Class[] getAdapterList() {
    return new Class[] { ITreeContentAdapter.class };
  }
}
