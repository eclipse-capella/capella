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
  protected boolean isImpactedByCurrentRule(EObject eObj_p) {
    return !((eObj_p instanceof FunctionalExchange) || (eObj_p instanceof ComponentExchange) || (eObj_p instanceof Association) || (eObj_p instanceof PhysicalLink));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void hasConflictComponentExchange(Set<AbstractNamedElement> conflictingElements_p, AbstractNamedElement currentElementInner_p,
      ComponentExchange elementComponentExchange_p) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void hasConflictFunctionalExchange(Set<AbstractNamedElement> conflictingElements_p, AbstractNamedElement currentElementInner_p,
      FunctionalExchange componentExchange_p) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void hasConflictPhysicalLink(Set<AbstractNamedElement> conflictingElements_p, AbstractNamedElement currentElementInner_p,
      PhysicalLink componentExchange_p) {

  }
}
