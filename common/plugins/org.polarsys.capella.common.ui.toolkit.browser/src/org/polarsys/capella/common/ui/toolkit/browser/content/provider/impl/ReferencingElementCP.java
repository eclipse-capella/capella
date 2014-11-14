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

/**
 */
public class ReferencingElementCP extends AbstractContentProvider {
  /**
   * Constructor.
   * @param adapterFactory_p
   */
  public ReferencingElementCP(AdapterFactory adapterFactory_p) {
    super(adapterFactory_p);
  }

  /**
   * {@inheritDoc}
   */
  public String getBrowserId() {
    return ID_REFERENCING_CP;
  }
}
