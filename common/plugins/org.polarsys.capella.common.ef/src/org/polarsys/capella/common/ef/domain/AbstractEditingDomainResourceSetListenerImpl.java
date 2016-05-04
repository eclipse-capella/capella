/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Soyatec - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.ef.domain;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * A base implementation for a ResourceSetListener which registers/deregisters itself on created/disposed capella editing domains.
 */
public abstract class AbstractEditingDomainResourceSetListenerImpl extends ResourceSetListenerImpl implements IEditingDomainListener {

  protected AbstractEditingDomainResourceSetListenerImpl(){
    super();
  }

  protected AbstractEditingDomainResourceSetListenerImpl(NotificationFilter filter) {
    super(filter);
  }

  @Override
  /**
   * This implementation registers itself as a ResourceSetListener on the created editing domain.
   */
  public void createdEditingDomain(EditingDomain editingDomain) {
    ((TransactionalEditingDomain)editingDomain).addResourceSetListener(this);
  }

  @Override
  /**
   * This implementation deregisters itself from the disposed editing domain.
   */
  public void disposedEditingDomain(EditingDomain editingDomain) {
    ((TransactionalEditingDomain)editingDomain).removeResourceSetListener(this);
  }

}
