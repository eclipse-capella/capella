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
package org.polarsys.capella.core.model.handler.crossreferencer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
   * An {@link ECrossReferenceAdapter} that only takes capella resources into account.<br>
   * This one is also dedicated to specific Capella derived features computation.
   */
public class CapellaDerivedECrossReferenceAdapter extends CapellaECrossReferenceAdapter {

  public CapellaDerivedECrossReferenceAdapter(EditingDomain editingDomain) {
	super(editingDomain);
  }

  /**
   * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#getInverseReferences(org.eclipse.emf.ecore.EObject, boolean)
   */
  @Override
  @SuppressWarnings("unchecked")
  public Collection<Setting> getInverseReferences(EObject eObject_p, boolean resolve_p) {
    Collection<EStructuralFeature.Setting> result = new ArrayList<EStructuralFeature.Setting>();

    if (resolve_p && resolve()) {
      resolveAll(eObject_p);
    }

    EObject eContainer = eObject_p.eContainer();
    if (eContainer != null) {
      result.add(((InternalEObject) eContainer).eSetting(eObject_p.eContainmentFeature()));
    }

    Collection<EStructuralFeature.Setting> nonNavigableInverseReferences = inverseCrossReferencer.get(eObject_p);
    if (nonNavigableInverseReferences != null) {
      result.addAll(nonNavigableInverseReferences);
    }

    for (EReference eReference : eObject_p.eClass().getEAllReferences()) {
      EReference eOpposite = eReference.getEOpposite();
      // Additionally test if opposite reference is not filtered.
      if ((eOpposite != null) && isLookedAfter(eOpposite) && !eReference.isContainer() && eObject_p.eIsSet(eReference)) {
        if (eReference.isMany()) {
          Object collection = eObject_p.eGet(eReference);
          for (Iterator<EObject> j = resolve() ? ((Collection<EObject>) collection).iterator() : ((InternalEList<EObject>) collection).basicIterator(); j
              .hasNext();) {
            InternalEObject referencingEObject = (InternalEObject) j.next();
            result.add(referencingEObject.eSetting(eOpposite));
          }
        } else {
          result.add(((InternalEObject) eObject_p.eGet(eReference, resolve())).eSetting(eOpposite));
        }
      }
    }

    return result;
  }

  /**
   * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#isIncluded(org.eclipse.emf.ecore.EReference)
   */
  @Override
  protected boolean isIncluded(EReference eReference_p) {
    return isLookedAfter(eReference_p) && super.isIncluded(eReference_p);
  }

  /**
   * Do the cross referencer care about this pointing reference ?
   * @param eReference_p
   * @return
   */
  protected boolean isLookedAfter(EReference eReference_p) {
    return CsPackage.Literals.INTERFACE_USE__USED_INTERFACE.equals(eReference_p)
           || CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE.equals(eReference_p)
           || CsPackage.Literals.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT.equals(eReference_p)
           || InformationPackage.Literals.ASSOCIATION__NAVIGABLE_MEMBERS.equals(eReference_p)
           || CapellacorePackage.Literals.INVOLVEMENT__INVOLVED.equals(eReference_p) || CapellacorePackage.Literals.INVOLVEMENT__INVOLVER.equals(eReference_p)
           || CapellacorePackage.Literals.GENERALIZATION__SUB.equals(eReference_p) || CapellacorePackage.Literals.GENERALIZATION__SUPER.equals(eReference_p)
           || CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUES.equals(eReference_p)
           || CapellacorePackage.Literals.CAPELLA_ELEMENT__APPLIED_PROPERTY_VALUE_GROUPS.equals(eReference_p)
           || ActivityPackage.Literals.ACTIVITY_EDGE__SOURCE.equals(eReference_p) || ActivityPackage.Literals.ACTIVITY_EDGE__TARGET.equals(eReference_p)
           || CapellacommonPackage.Literals.REGION__INVOLVED_STATES.equals(eReference_p)
           || ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT.equals(eReference_p)
           || ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT.equals(eReference_p)
           || ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE.equals(eReference_p)
           || ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__SOURCE.equals(eReference_p)
           || ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET.equals(eReference_p)
           || ModellingcorePackage.Literals.ABSTRACT_CONSTRAINT__CONSTRAINED_ELEMENTS.equals(eReference_p);
  }
}
