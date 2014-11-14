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
package org.polarsys.capella.common.ui.toolkit.browser.content.provider.impl;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.polarsys.capella.common.ui.toolkit.browser.model.ISemanticBrowserModel;

/**
 */
public class ReferencedElementCP extends AbstractContentProvider {
  /**
   * Constructor.
   * @param adapterFactory_p
   */
  public ReferencedElementCP(AdapterFactory adapterFactory_p, ISemanticBrowserModel model_p) {
    super(adapterFactory_p, model_p);
  }
  /**
   * {@inheritDoc}
   */
  @Override
  public String getBrowserId() {
    return ID_REFERENCED_CP;
  }
}
