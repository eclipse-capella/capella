/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.common.handlers;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.projection.common.context.IContext;

/**
 * for further use
 */
public interface ISelectionContext {

  public static final ISelectionContext EMPTY_CONTEXT = null;

  public boolean match(EObject source_p, EObject target_p, IContext context_p);

}
