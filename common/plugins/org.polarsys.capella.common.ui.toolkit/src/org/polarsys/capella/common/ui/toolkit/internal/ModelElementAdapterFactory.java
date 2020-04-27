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
    public ModelElementWrapper(ModelElement element) {
      _element = element;
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
  public Object getAdapter(Object adaptableObject, Class adapterType) {
    Object result = null;
    // Handle Link Selection adaptation.
    if ((adaptableObject instanceof ModelElement) && ITreeLabelAdapter.class.equals(adapterType)) {
      result = new ModelElementWrapper((ModelElement) adaptableObject);
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
