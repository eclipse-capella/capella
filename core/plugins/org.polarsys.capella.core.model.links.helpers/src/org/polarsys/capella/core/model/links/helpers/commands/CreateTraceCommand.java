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
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.core.model.links.helpers.LinkInfo.LinkStyle;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class CreateTraceCommand extends AbstractQueryBasedCommand {
  protected AbstractTrace _createdTrace;

  /**
   * @param linkType
   * @param linkRefInSource
   */
  public CreateTraceCommand(String label, LinkStyle linkGraphicalRepresentation, EClass linkType, EReference linkRefInSource) {
    super(label, linkGraphicalRepresentation, linkType, linkRefInSource);
    // Precondition.
    if (!ModellingcorePackage.Literals.ABSTRACT_TRACE.isSuperTypeOf(linkType)) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EObject getCreatedLinkObject() {
    return _createdTrace;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute() {
    _createdTrace = (AbstractTrace) EcoreUtil.create(_linkType);
    _createdTrace.setSourceElement(_sourceElement);
    _createdTrace.setTargetElement(_targetElement);
    ((List<EObject>) _sourceElement.eGet(_linkRefInSource)).add(_createdTrace);
  }
}
