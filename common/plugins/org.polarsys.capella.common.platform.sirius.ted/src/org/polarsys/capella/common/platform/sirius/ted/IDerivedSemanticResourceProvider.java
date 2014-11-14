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
package org.polarsys.capella.common.platform.sirius.ted;

import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * Allows to provide custom resources to the Semantic Resources attached to a given Sirius Session.
 */
public interface IDerivedSemanticResourceProvider {

  /**
   * Provides the derived semantic resources.
   * @param editingDomain the editing domain managing the resources.
   * @return the derived semantic resources.
   */
  public Collection<Resource> getDerivedSemanticResources(TransactionalEditingDomain editingDomain);

}
