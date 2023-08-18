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
package org.polarsys.capella.core.data.oa.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.polarsys.capella.core.data.cs.impl.ComponentImpl;
import org.polarsys.capella.core.data.oa.AbstractConceptItem;
import org.polarsys.capella.core.data.oa.ItemInConcept;
import org.polarsys.capella.core.data.oa.OaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Concept Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.polarsys.capella.core.data.oa.impl.AbstractConceptItemImpl#getComposingLinks <em>Composing Links</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractConceptItemImpl extends ComponentImpl implements AbstractConceptItem {

	/**
   * The cached value of the '{@link #getComposingLinks() <em>Composing Links</em>}' reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getComposingLinks()
   * @generated
   * @ordered
   */
	protected EList<ItemInConcept> composingLinks;




	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected AbstractConceptItemImpl() {

    super();

  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OaPackage.Literals.ABSTRACT_CONCEPT_ITEM;
  }





	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */

	public EList<ItemInConcept> getComposingLinks() {

    if (composingLinks == null) {
      composingLinks = new EObjectResolvingEList<ItemInConcept>(ItemInConcept.class, this, OaPackage.ABSTRACT_CONCEPT_ITEM__COMPOSING_LINKS);
    }
    return composingLinks;
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case OaPackage.ABSTRACT_CONCEPT_ITEM__COMPOSING_LINKS:
        return getComposingLinks();
    }
    return super.eGet(featureID, resolve, coreType);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case OaPackage.ABSTRACT_CONCEPT_ITEM__COMPOSING_LINKS:
        getComposingLinks().clear();
        getComposingLinks().addAll((Collection<? extends ItemInConcept>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }


	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void eUnset(int featureID) {
    switch (featureID) {
      case OaPackage.ABSTRACT_CONCEPT_ITEM__COMPOSING_LINKS:
        getComposingLinks().clear();
        return;
    }
    super.eUnset(featureID);
  }



	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public boolean eIsSet(int featureID) {
    switch (featureID) {
      case OaPackage.ABSTRACT_CONCEPT_ITEM__COMPOSING_LINKS:
        return composingLinks != null && !composingLinks.isEmpty();
    }
    return super.eIsSet(featureID);
  }



} //AbstractConceptItemImpl