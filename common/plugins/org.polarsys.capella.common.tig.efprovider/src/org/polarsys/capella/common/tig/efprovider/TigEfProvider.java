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
package org.polarsys.capella.common.tig.efprovider;

import org.polarsys.capella.common.tig.ef.ExecutionManager;

/**
 * Provide the name i.e the identifier of the execution manager to use in this capella platform.
 */
public class TigEfProvider {
  /**
   * Editing domain provider id.<br>
   * @see org.polarsys.capella.common.platform.sirius.tig plug-in at org.polarsys.capella.common.tig.ef.editingDomainProvider extension.
   * @see ExecutionManagerRegistry, {@link ExecutionManager}
   */
  private static final String EDITING_DOMAIN_PROVIDER_ID = "org.polarsys.capella.common.platform.sirius.tig.EditingDomainProvider"; //$NON-NLS-1$

  /**
   * Get the execution manager id.
   * @return a not <code>null</code> string.
   */
  public static String getExecutionManagerName() {
    return EDITING_DOMAIN_PROVIDER_ID;
  }
}
