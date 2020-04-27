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
package org.polarsys.capella.core.data.core.validation.capellaelement;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 * This check insures that an element doesn't contain a naming conflict.
 * 
 *  This naming conflict rule is not applicable to: <li><code>AbstractConnection</code></li> <li><code>PhysicalLinks</code></li> <li>
 * <code>FunctionalExchange</code></li>
 * @deprecated This constraint is now covered by the NameConflict class.
 */
@Deprecated
public class MDCHK_CapellaElement_Naming_1 extends Abstract_MDCHK_NamingConflictRule {

  /**
   * @see org.polarsys.capella.core.data.core.validation.capellaelement.Abstract_MDCHK_NamingConflictRule#isImpactedByCurrentRule(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected boolean isImpactedByCurrentRule(EObject eObj) {
    return !((eObj instanceof FunctionalExchange) || (eObj instanceof ComponentExchange) || (eObj instanceof Association) || (eObj instanceof PhysicalLink));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void hasConflictComponentExchange(Set<AbstractNamedElement> conflictingElements, AbstractNamedElement currentElementInner,
      ComponentExchange elementComponentExchange) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void hasConflictFunctionalExchange(Set<AbstractNamedElement> conflictingElements, AbstractNamedElement currentElementInner,
      FunctionalExchange componentExchange) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void hasConflictPhysicalLink(Set<AbstractNamedElement> conflictingElements, AbstractNamedElement currentElementInner,
      PhysicalLink componentExchange) {

  }
}
