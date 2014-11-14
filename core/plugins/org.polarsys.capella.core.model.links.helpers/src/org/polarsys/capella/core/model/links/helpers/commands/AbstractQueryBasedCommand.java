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
package org.polarsys.capella.core.model.links.helpers.commands;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.links.helpers.LinkInfo.LinkStyle;

/**
 */
public abstract class AbstractQueryBasedCommand extends AbstractCreateLinksCommand {
  protected final EClass _linkType;
  protected final EReference _linkRefInSource;

  protected CapellaElement _sourceElement;

  protected CapellaElement _targetElement;

  public AbstractQueryBasedCommand(String label_p, LinkStyle linkStyle_p, EClass linkType_p, EReference linkRefInSource_p) {
    super(label_p, linkStyle_p);
    _linkType = linkType_p;
    _linkRefInSource = linkRefInSource_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean prepare() {
    // Extract target and source.
    if ((null == getSources()) || (null == getTargets()) || (1 != getSources().size()) || (1 != getTargets().size())) {
      return false;
    }
    _targetElement = (CapellaElement) getTarget();
    _sourceElement = (CapellaElement) getSource();

    // Do we have a query ?
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(_sourceElement.eClass(), _linkRefInSource);
    if (null == query) {
      return false;
    }
    List<CapellaElement> availableElements = query.getAvailableElements(_sourceElement);
    List<CapellaElement> currentElements = query.getCurrentElements(_sourceElement, false);
    availableElements.removeAll(currentElements);
    // Is target in available elements ?
    if (!availableElements.contains(_targetElement)) {
      return false;
    }
    return true;
  }

}
