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
package org.polarsys.capella.core.data.core.validation.capellaelement;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.data.activity.ActivityNode;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.Port;

/**
 * This class is a naming conflict rule applicable to: <li><code>AbstractConnection</code></li> <li><code>PhysicalLinks</code></li> <li>
 * <code>FunctionalExchange</code></li>
 */
public class MDCHK_DE_CE_PL_Naming_1 extends Abstract_MDCHK_NamingConflictRule {
	/**
	 * @see org.polarsys.capella.core.data.core.validation.capellaelement.Abstract_MDCHK_NamingConflictRule#isImpactedByCurrentRule(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	protected boolean isImpactedByCurrentRule(EObject eObj) {
		return ((eObj instanceof FunctionalExchange) || (eObj instanceof ComponentExchange) || (eObj instanceof PhysicalLink));
	}

	/**
	 * @param conflictingElements
	 * @param candidate
	 * @param currentElement
	 * @return
	 */
	@Override
	protected void hasConflictPhysicalLink(Set<AbstractNamedElement> conflictingElements, AbstractNamedElement candidate, PhysicalLink currentElement) {
		PhysicalPort currentSourcePort = currentElement.getSourcePhysicalPort();
		PhysicalPort currentTargetPort = currentElement.getTargetPhysicalPort();

		if (candidate instanceof PhysicalLink) {
			PhysicalLink link = (PhysicalLink) candidate;
			PhysicalPort candidateSourcePort = link.getSourcePhysicalPort();
			PhysicalPort candidateTargetPort = link.getTargetPhysicalPort();

			if (link.getName().equals(currentElement.getName())) {
				if ((candidateTargetPort != null) && (candidateSourcePort != null)) {
					if (isRelative(candidateTargetPort.eContainer(), currentTargetPort.eContainer()) && isRelative(candidateSourcePort.eContainer(), currentSourcePort.eContainer())) {
						conflictingElements.add(candidate);
					}
				}
			}
		}
	}

	/**
	 * @param conflictingElements
	 * @param candidate
	 * @param currentElement
	 * @return boolean <b>true</b> if exist an conflict <b>false</b> false otherwise
	 */
	@Override
	protected void hasConflictFunctionalExchange(Set<AbstractNamedElement> conflictingElements, AbstractNamedElement candidate,
			FunctionalExchange currentElement) {
		ActivityNode currentSourcePort = currentElement.getSource();
		ActivityNode currentTargetPort = currentElement.getTarget();

		if (candidate instanceof FunctionalExchange) {
			FunctionalExchange link = (FunctionalExchange) candidate;
			ActivityNode candidateSourcePort = link.getSource();
			ActivityNode candidateTargetPort = link.getTarget();

			if (link.getName().equals(currentElement.getName())) {
				if ((candidateTargetPort != null) && (candidateSourcePort != null)){
					if(currentSourcePort instanceof AbstractFunction && currentTargetPort instanceof AbstractFunction){
						if (isRelative(candidateTargetPort, currentTargetPort) && isRelative(candidateSourcePort, currentSourcePort)) {
							conflictingElements.add(candidate);
						}
					}else {
						if (isRelative(candidateTargetPort.eContainer(), currentTargetPort.eContainer()) && isRelative(candidateSourcePort.eContainer(), currentSourcePort.eContainer())) {
							conflictingElements.add(candidate);
						}
					}
				}
			}
		}
	}

	/**
	 * @param candidate
	 * @param currentElement
	 * @return
	 */
	@Override
	protected void hasConflictComponentExchange(Set<AbstractNamedElement> conflictingElements, AbstractNamedElement candidate,
			ComponentExchange currentElement) {
		Port currentSourcePort = currentElement.getSourcePort();
		Port currentTargetPort = currentElement.getTargetPort();

		if (candidate instanceof ComponentExchange) {
			ComponentExchange link = (ComponentExchange) candidate;
			Port candidateSourcePort = link.getSourcePort();
			Port candidateTargetPort = link.getTargetPort();

			if (link.getName().equals(currentElement.getName())) {

				if ((candidateTargetPort != null) && (candidateSourcePort != null)) {
					if (isRelative(candidateTargetPort.eContainer(), currentTargetPort.eContainer()) && isRelative(candidateSourcePort.eContainer(), currentSourcePort.eContainer())) {
						conflictingElements.add(candidate);
					}
				} else if ((currentElement.getSource() != null) && (currentElement.getTarget() != null)) {
					if (isRelative(link.getTarget(), currentElement.getTarget()) && isRelative(link.getSource(), currentElement.getSource())) {
						conflictingElements.add(candidate);
					}
				}
			}
		}
	}
	
	public boolean isRelative(EObject first, EObject second){
		return (foundObject(first, second) || foundObject(second, first));
	}

	public boolean foundObject(EObject obj, EObject set) {

		if(EcoreUtil.equals(obj, set)){
			return true;
		} else {       
			if(set.eContents().size()>0){
				for(EObject child: set.eContents()){
					if(foundObject(obj, child)){
						return true;
					}
				}
			}
		}
		return false;           
	}

}
