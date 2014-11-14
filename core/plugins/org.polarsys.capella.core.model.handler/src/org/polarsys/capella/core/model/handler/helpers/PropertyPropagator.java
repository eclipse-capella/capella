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
package org.polarsys.capella.core.model.handler.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.requirement.RequirementPackage;

/**
 *
 */
public abstract class PropertyPropagator {

  /**
   * @param root_p
   */
  public void applyPropertiesOn(List<? extends EObject> literals_p, EObject root_p) {
    //
    // First of all, let's obtain target eObjects.
    //
    TreeIterator<EObject> it = root_p.eAllContents();
    EObject current = null;
    Collection<EObject> tgts = new HashSet<EObject>();

    if (isTaggableElement(root_p)) {
      tgts.add(root_p);
    }

    while (it.hasNext()) {
      current = it.next();
      if (isTaggableElement(current)) {
        tgts.add(current);
      }
    }

    //
    // On a second hand, let's tag target eObjects.
    //
    for (EObject eobj : tgts) {
      tagElement(literals_p, eobj);
    }
  }

  /**
   * @param element_p
   * @return
   */
  public boolean isTaggableElement(EObject element_p) {
    return isDirectElement(element_p) || isWithSpecializedElement(element_p);
  }

  /**
   * @param literals_p
   * @param eObject_p
   * @return
   */
  private boolean tagElement(List<? extends EObject> literals_p, EObject eObject_p) {
    boolean result = true;
    for (EObject literal : literals_p) {
      if (literal instanceof EnumerationPropertyLiteral) {
        result &= tagElement((EnumerationPropertyLiteral) literal, eObject_p);
      } else {
        result &= false;
      }
    }
    return result;
  }

  /**
   * @return
   */
  protected Collection<EClass> getDirectTypes() {
    Collection<EClass> directTypes = new HashSet<EClass>();
    directTypes.add(FaPackage.Literals.COMPONENT_EXCHANGE);
    directTypes.add(FaPackage.Literals.FUNCTIONAL_EXCHANGE);
    directTypes.add(InteractionPackage.Literals.ABSTRACT_FRAGMENT);
    directTypes.add(CsPackage.Literals.PHYSICAL_LINK);
    return directTypes;
  }

  /**
   * @return
   */
  protected Collection<EClass> getWithSpecializationType() {
    Collection<EClass> withSpecializationTypes = new HashSet<EClass>();
    withSpecializationTypes.add(CapellacommonPackage.Literals.STATE_MACHINE);
    withSpecializationTypes.add(CapellacorePackage.Literals.STRUCTURE);
    withSpecializationTypes.add(FaPackage.Literals.ABSTRACT_FUNCTION);
    withSpecializationTypes.add(CsPackage.Literals.PART);
    withSpecializationTypes.add(CsPackage.Literals.COMPONENT);
    withSpecializationTypes.add(FaPackage.Literals.FUNCTIONAL_CHAIN);
    withSpecializationTypes.add(FaPackage.Literals.EXCHANGE_CATEGORY);
    withSpecializationTypes.add(InteractionPackage.Literals.ABSTRACT_CAPABILITY);
    withSpecializationTypes.add(InteractionPackage.Literals.SCENARIO);
    withSpecializationTypes.add(CtxPackage.Literals.MISSION);
    withSpecializationTypes.add(OaPackage.Literals.ENTITY);
    withSpecializationTypes.add(OaPackage.Literals.ROLE);
    withSpecializationTypes.add(CsPackage.Literals.PHYSICAL_PATH);
    withSpecializationTypes.add(CapellacorePackage.Literals.GENERAL_CLASS);
    withSpecializationTypes.add(InformationPackage.Literals.COLLECTION);
    withSpecializationTypes.add(InformationPackage.Literals.EXCHANGE_ITEM);
    withSpecializationTypes.add(DatatypePackage.Literals.DATA_TYPE);
    withSpecializationTypes.add(InformationPackage.Literals.UNIT);
    withSpecializationTypes.add(RequirementPackage.Literals.REQUIREMENT);
    withSpecializationTypes.add(CapellacommonPackage.Literals.REGION);
    return withSpecializationTypes;
  }

  /**
   * @param eObject_p
   * @return
   */
  protected abstract boolean isTagged(EObject eObject_p);

  /**
   * @param literal_p
   * @param eObject_p
   * @return
   */
  protected abstract boolean tagElement(EnumerationPropertyLiteral literal_p, EObject eObject_p);

  /**
   * @return
   */
  protected abstract String getKeyword();

  /**
   * @param eObject_p
   * @return
   */
  private boolean isDirectElement(EObject eObject_p) {
    if (null != eObject_p) {
      EClass eclass = eObject_p.eClass();
      for (EClass current : getDirectTypes()) {
        if (current == eclass) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * @param eObject_p
   * @return
   */
  private boolean isWithSpecializedElement(EObject eObject_p) {
    if (null != eObject_p) {
      EClass eclass = eObject_p.eClass();
      for (EClass current : getWithSpecializationType()) {
        if (current.isSuperTypeOf(eclass)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * @param eObject_p
   * @return
   */
  public boolean isEnumerationPropertyTypeDefinedForProject(EObject eObject_p) {
    return (null != CapellaProjectHelper.getEnumerationPropertyType(eObject_p, getKeyword()));
  }

  /**
   * @param root_p
   * @return
   */
  public List<EObject> getTaggedObjects(EObject root_p) {
    List<EObject> result = new ArrayList<EObject>();

    if (null != root_p) {
      if (isTagged(root_p)) {
        result.add(root_p);
      }
      TreeIterator<EObject> it = root_p.eAllContents();
      EObject current = null;
      while (it.hasNext()) {
        current = it.next();
        if (isTagged(current)) {
          result.add(current);
        }
      }
    }

    return result;
  }
}
