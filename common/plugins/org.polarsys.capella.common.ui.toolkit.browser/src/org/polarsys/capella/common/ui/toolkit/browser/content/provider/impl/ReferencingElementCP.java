/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.ui.toolkit.browser.content.provider.impl;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.polarsys.capella.common.ui.toolkit.browser.model.ISemanticBrowserModel;

/**
 */
public class ReferencingElementCP extends AbstractContentProvider {
  /**
   * Constructor.
   * @param adapterFactory
   */
  public ReferencingElementCP(AdapterFactory adapterFactory, ISemanticBrowserModel model) {
    super(adapterFactory, model);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getBrowserId() {
    return ID_REFERENCING_CP;
  }
}
