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

import org.polarsys.capella.common.ui.toolkit.viewers.ITreeLabelAdapter;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * Adapter factory to adapt {@link ModelElement} to {@link ITreeLabelAdapter}.
 */
public class ModelElementAdapterFactory implements IAdapterFactory {
  /**
   * ModelElement wrapper to {@link ITreeLabelAdapter}.
   */
  class ModelElementWrapper implements ITreeLabelAdapter {
    private ModelElement _element;

    /**
     * Constructor.
     */
    public ModelElementWrapper(ModelElement element_p) {
      _element = element_p;
    }

    /**
     * {@inheritDoc}
     */
    public String getFullLabel() {
      return _element.getFullLabel();
    }
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public Object getAdapter(Object adaptableObject_p, Class adapterType_p) {
    Object result = null;
    // Handle Link Selection adaptation.
    if ((adaptableObject_p instanceof ModelElement) && ITreeLabelAdapter.class.equals(adapterType_p)) {
      result = new ModelElementWrapper((ModelElement) adaptableObject_p);
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public Class[] getAdapterList() {
    return new Class[] { ITreeLabelAdapter.class };
  }
}
