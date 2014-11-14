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
package org.polarsys.capella.common.tig.ef;

import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * Editing domain provider.<br>
 * Provides the execution framework with the implementation of the transactional editing domain that should be used.<br>
 * Should be used with the execution manager extension point.
 */
public interface IEditingDomainProvider {
  /**
   * Get or create the transactional editing domain to use.
   * @return A not null instance of {@link TransactionalEditingDomain}.
   */
  TransactionalEditingDomain getEditingDomain();
}
