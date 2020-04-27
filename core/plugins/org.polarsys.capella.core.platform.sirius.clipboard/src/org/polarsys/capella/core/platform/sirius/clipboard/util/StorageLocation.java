/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.clipboard.util;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * A location for storing model elements.
 */
public class StorageLocation {
  
  /** The non-null container */
	private EObject _container;
	
	/** The non-null containment */
	private EReference _containment;
	
	
	/**
	 * Constructor
	 * @param container_p a non-null element
	 * @param containment_p a non-null containment reference
	 */
	public StorageLocation(EObject container_p, EReference containment_p) {
    _container = container_p;
    _containment = containment_p;
  }
	
	/**
	 * Return the container
	 * @return a non-null element
	 */
	public EObject getContainer() {
    return _container;
  }
	
	/**
	 * Return the containment
	 * @return a non-null containment reference
	 */
	public EReference getContainment() {
    return _containment;
  }
	
  /**
   * Apply this location as storage for the given element
   * @param newChild a non-null element
   */
	public void applyOn(EObject newChild) {
		applyOnAll(Collections.singletonList(newChild));
	}
	
	/**
	 * Apply this location as storage for the given elements
	 * @param newChildren a non-null, potentially empty list
	 */
	@SuppressWarnings("unchecked")
  public void applyOnAll(List<? extends EObject> newChildren) {
		try {
			if (_containment.isMany()) {
				((EList<EObject>)_container.eGet(_containment)).addAll(newChildren);
			} else if (!newChildren.isEmpty()) {
				// Warning: replace already existing element
				EObject predecessor = (EObject)_container.eGet(_containment);
				EcoreUtil.remove(predecessor);
				_container.eSet(_containment, newChildren.get(0));
			}
		} catch(IllegalArgumentException e) {e.printStackTrace();} // Not changeable
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o_p) {
	  boolean result = false;
	  if (o_p instanceof StorageLocation) {
	    StorageLocation peer = (StorageLocation)o_p;
	    result =
	      peer.getContainer() == getContainer() &&
	      peer.getContainment() == getContainment();
	  }
	  return result;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
  @Override
  public int hashCode() {
    return getContainer().hashCode() + getContainment().hashCode();
  }
	
  /**
   * Return whether this storage location supports addition
   */
  public boolean supportsAddition() {
    return MiscUtil.supportsAddition(getContainment());
  }
  
}
