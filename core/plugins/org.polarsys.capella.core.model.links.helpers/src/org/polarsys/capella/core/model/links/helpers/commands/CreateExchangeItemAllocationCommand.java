/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.links.helpers.commands;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.sirius.analysis.CapellaServices;
import org.polarsys.capella.core.model.links.helpers.LinkInfo.LinkStyle;

/**
 */
public class CreateExchangeItemAllocationCommand extends AbstractCreateLinksCommand {
  protected ExchangeItem _target;
  protected Interface _source;
  protected ExchangeItemAllocation _createdExchangeItemAllocation;

  public CreateExchangeItemAllocationCommand() {
    super("Exchange item allocation", LinkStyle.LINE_SOLID);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean prepare() {
    // Preconditions.
    // 1 target.
    if ((null == getTargets()) || (1 != getTargets().size())) {
      return false;
    }
    // 1 source.
    if ((null == getSources()) || (1 != getSources().size())) {
      return false;
    }
    _target = (ExchangeItem) getTargets().iterator().next();
    _source = (Interface) getSources().iterator().next();
    // Exchange item must't be already linked to the interface.
    if (_source.getExchangeItems().contains(_target)) {
      return false;
    }
    if (!CapellaServices.getService().getAllExchangeItems(_source).contains(_target)) {
      return false;
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute() {
    _createdExchangeItemAllocation = CsFactory.eINSTANCE.createExchangeItemAllocation();
    _createdExchangeItemAllocation.setAllocatedItem(_target);
    _source.getOwnedExchangeItemAllocations().add(_createdExchangeItemAllocation);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getCreatedLinkObject() {
    return _createdExchangeItemAllocation;
  }

}
