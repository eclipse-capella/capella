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
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 * This class is a naming conflict rule applicable to: <li><code>AbstractConnection</code></li> <li><code>PhysicalLinks</code></li> <li>
 * <code>FunctionalExchange</code></li>
 */
public class MDCHK_DE_CE_PL_Naming_1 extends Abstract_MDCHK_NamingConflictRule {
  /**
   * @see org.polarsys.capella.core.data.core.validation.capellaelement.Abstract_MDCHK_NamingConflictRule#isImpactedByCurrentRule(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected boolean isImpactedByCurrentRule(EObject eObj_p) {
    return ((eObj_p instanceof FunctionalExchange) || (eObj_p instanceof ComponentExchange) || (eObj_p instanceof PhysicalLink));
  }

  /**
   * 
   * @param conflictingElements_p
   * @param candidate_p
   * @param currentElement_p
   * @return
   */
  @Override
  protected void hasConflictPhysicalLink(Set<AbstractNamedElement> conflictingElements_p, AbstractNamedElement candidate_p, PhysicalLink currentElement_p) {
    PhysicalPort currentSourcePort = currentElement_p.getSourcePhysicalPort();
    PhysicalPort currentTargetPort = currentElement_p.getTargetPhysicalPort();

    if (candidate_p instanceof PhysicalLink) {
      PhysicalLink link = (PhysicalLink) candidate_p;
      PhysicalPort candidateSourcePort = link.getSourcePhysicalPort();
      PhysicalPort candidateTargetPort = link.getTargetPhysicalPort();

      if (link.getName().equals(candidate_p.getName())) {
        if ((candidateTargetPort != null) && (candidateSourcePort != null)) {
          if (candidateTargetPort.equals(currentTargetPort) && candidateSourcePort.equals(currentSourcePort)) {
            conflictingElements_p.add(candidate_p);
          }
        }
      }
    }

  }

  /**
   * 
   * @param conflictingElements_p 
  * @param candidate_p
   * @param currentElement_p
   * @return boolean <b>true</b> if exist an conflict <b>false</b> false autherwise
   */
  @Override
  protected void hasConflictFunctionalExchange(Set<AbstractNamedElement> conflictingElements_p, AbstractNamedElement candidate_p,
      FunctionalExchange currentElement_p) {
    ActivityNode currentSourcePort = currentElement_p.getSource();
    ActivityNode currentTargetPort = currentElement_p.getTarget();

    if (candidate_p instanceof FunctionalExchange) {
      FunctionalExchange link = (FunctionalExchange) candidate_p;
      ActivityNode candidateSourcePort = link.getSource();
      ActivityNode candidateTargetPort = link.getTarget();

      if (link.getName().equals(candidate_p.getName())) {
        if ((candidateTargetPort != null) && (candidateSourcePort != null)) {
          if (candidateTargetPort.equals(currentTargetPort) && candidateSourcePort.equals(currentSourcePort)) {
            conflictingElements_p.add(candidate_p);
          }
        }
      }
    }
  }

  /**
   * 
   * @param candidate_p
   * @param currentElement_p
   * @return
   */
  @Override
  protected void hasConflictComponentExchange(Set<AbstractNamedElement> conflictingElements_p, AbstractNamedElement candidate_p,
      ComponentExchange currentElement_p) {
    Port currentSourcePort = currentElement_p.getSourcePort();
    Port currentTargetPort = currentElement_p.getTargetPort();

    if (candidate_p instanceof ComponentExchange) {
      ComponentExchange link = (ComponentExchange) candidate_p;
      Port candidateSourcePort = link.getSourcePort();
      Port candidateTargetPort = link.getTargetPort();

      if (link.getName().equals(candidate_p.getName())) {

        if ((candidateTargetPort != null) && (candidateSourcePort != null)) {
          if (candidateTargetPort.equals(currentTargetPort) && candidateSourcePort.equals(currentSourcePort)) {
            conflictingElements_p.add(candidate_p);
          }
        } else if ((currentElement_p.getSource() != null) && (currentElement_p.getTarget() != null)) {
          if (currentElement_p.getSource().equals(link.getSource()) && currentElement_p.getTarget().equals(link.getTarget())) {
            conflictingElements_p.add(candidate_p);
          }
        }
      }
    }
  }

}
