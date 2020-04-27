/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
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

  public AbstractQueryBasedCommand(String label, LinkStyle linkStyle, EClass linkType, EReference linkRefInSource) {
    super(label, linkStyle);
    _linkType = linkType;
    _linkRefInSource = linkRefInSource;
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
    List<EObject> availableElements = query.getAvailableElements(_sourceElement);
    List<EObject> currentElements = query.getCurrentElements(_sourceElement, false);
    availableElements.removeAll(currentElements);
    // Is target in available elements ?
    if (!availableElements.contains(_targetElement)) {
      return false;
    }
    return true;
  }

}
