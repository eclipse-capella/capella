/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.requirement.RequirementPackage;

/**
 *
 */
public abstract class PropertyPropagator {

  /**
   * @param literals
   * @param selectedObjects
   * @param semanticElementPropagation
   * @param technicalElementPropagation
   *          Indicates whether to propagate the status without filtering.
   * @param propagateToRepresentations
   *          Indicates whether to propagate the status to the referenced {@link DRepresentation}
   * @param mustCleanReview
   * 
   * @return the collection of modified objects
   */
  public List<Collection<EObject>> applyPropertiesOn(List<? extends EObject> literals, Collection<EObject> selectedObjects,
      boolean semanticElementPropagation, boolean technicalElementPropagation, boolean propagateToRepresentations, boolean useFilterStatus,
      String filterStatus, boolean mustCleanReview, boolean mustPropagateStatus) {
    //
    // First of all, let's obtain target eObjects.
    //
    Collection<EObject> tgts = new HashSet<EObject>();
    for (EObject obj : selectedObjects) {
      if (!tgts.contains(obj)) {
        handleFilterStatus(semanticElementPropagation, technicalElementPropagation, propagateToRepresentations, useFilterStatus, filterStatus, tgts, obj);

        if (!(obj instanceof DRepresentation)) {
          // Go through model elements to get inner elements
          TreeIterator<EObject> it = obj.eAllContents();

          while (it.hasNext()) {
            EObject current = it.next();
            handleFilterStatus(semanticElementPropagation, technicalElementPropagation, propagateToRepresentations, useFilterStatus, filterStatus, tgts, current);
          }
        }
      }
    }

    // Handle DRepresentation
    if (propagateToRepresentations) {
      Collection<DRepresentation> representationsTargeted = RepresentationHelper
          .getAllRepresentationsTargetedBy(selectedObjects);

      for (DRepresentation representation : representationsTargeted) {
        if (useFilterStatus) {
          if (mustBeFiltered(filterStatus, representation)) {
            tgts.add(representation);
          }
        } else {
          tgts.add(representation);
        }
      }

    }

    //
    // On a second hand, let's tag target eObjects.
    //
    Collection<EObject> tagChangedElements = new HashSet<EObject>();
    Collection<EObject> reviewChangedElements = new HashSet<EObject>();
    for (EObject eobj : tgts) {
    	boolean result = false;
    	if (mustPropagateStatus) {
    		result = tagElement(literals, eobj);
    		if (result) {
    			tagChangedElements.add(eobj);
    		}
    	}
    	if (mustCleanReview) {
    		result = cleanReview(eobj);
    		if (result) {
    			reviewChangedElements.add(eobj);
    		}
    	}
    }
    List<Collection<EObject>> colOut = new ArrayList<Collection<EObject>>();
    colOut.add(tagChangedElements);
    colOut.add(reviewChangedElements);
    return colOut;
  }

  protected void handleFilterStatus(boolean semanticElementPropagation, boolean technicalElementPropagation, boolean propagateToRepresentations, boolean useFilterStatus, String filterStatus,
      Collection<EObject> tgts, EObject current) {
    if (useFilterStatus) {
      if (mustBeFiltered(filterStatus, current)) {
        handlePropagation(semanticElementPropagation, technicalElementPropagation, propagateToRepresentations, tgts, current);
      }
    } else {
      handlePropagation(semanticElementPropagation, technicalElementPropagation, propagateToRepresentations, tgts, current);
    }
  }

  protected void handlePropagation(boolean semanticElementPropagation, boolean technicalElementPropagation, boolean propagateToRepresentations, Collection<EObject> tgts, EObject obj) {
    if (isTaggableElement(obj)) {
      if (obj instanceof DRepresentation) {
       if (propagateToRepresentations) {
        tgts.add(obj);
       }
      }
      else if (semanticElementPropagation) {
        tgts.add(obj);
      }
    } else {
      if (technicalElementPropagation) {
        tgts.add(obj);
      }
    }
  }

  protected boolean mustBeFiltered(String filterStatus, EObject obj) {
    return (filterStatus == null && getElementTag(obj) == null) || filterStatus != null
        && filterStatus.equals(getElementTag(obj));
  }

  /**
   * @param element
   * @return
   */
  public boolean isTaggableElement(EObject element) {
    return isDirectElement(element) || isWithSpecializedElement(element);
  }

  /**
   * @param literals
   * @param eObject
   * @return
   */
  protected boolean tagElement(List<? extends EObject> literals, EObject eObject) {
    boolean result = true;
    for (EObject literal : literals) {
      if (literal == null || literal instanceof EnumerationPropertyLiteral) {
        result &= tagElement((EnumerationPropertyLiteral) literal, eObject);
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
    withSpecializationTypes.add(CapellacorePackage.Literals.CONSTRAINT);
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
   * @param eObject
   * @return
   */
  protected abstract boolean isTagged(EObject eObject);

  /**
   * @param eObject
   * @return
   */
  protected abstract boolean isTaggedRepresentation(EObject eObject);

  /**
   * @param literal
   * @param eObject
   * @return
   */
  protected abstract boolean tagElement(EnumerationPropertyLiteral literal, EObject eObject);

  /**
   * @return
   */
  protected abstract String getKeyword();

  /**
   * Clean review
   * @param eobj
   */
  protected abstract boolean cleanReview(EObject eobj);

  /**
   * 
   * @param eObject
   * @return
   */
  protected abstract String getElementTag(EObject eObject);

  /**
   * @param eObject
   * @return
   */
  protected boolean isDirectElement(EObject eObject) {
    if (null != eObject) {
      EClass eclass = eObject.eClass();
      for (EClass current : getDirectTypes()) {
        if (current == eclass) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * @param eObject
   * @return
   */
  protected boolean isWithSpecializedElement(EObject eObject) {
    if (null != eObject) {
      EClass eclass = eObject.eClass();
      for (EClass current : getWithSpecializationType()) {
        if (current.isSuperTypeOf(eclass)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * @param eObject
   * @return
   */
  public boolean isEnumerationPropertyTypeDefinedForProject(EObject eObject) {
    return (null != CapellaProjectHelper.getEnumerationPropertyType(eObject, getKeyword()));
  }

  /**
   * @param root
   * @return
   */
  public List<EObject> getTaggedObjects(EObject root) {
    List<EObject> result = new ArrayList<EObject>();
    Session session = SessionManager.INSTANCE.getSession(root);

    if (null != root) {
      if (isTagged(root)) {
        result.add(root);
      }

      TreeIterator<EObject> it = root.eAllContents();
      EObject current = null;
      EObject currentDiagram = null;
      while (it.hasNext()) {
        current = it.next();
        if (isTagged(current)) {
          result.add(current);
        }
        for (DRepresentationDescriptor representationDesc : DialectManager.INSTANCE.getRepresentationDescriptors(current, session)) {
          currentDiagram = representationDesc.getRepresentation();
          if (isTaggedRepresentation(currentDiagram)) {
            result.add(representationDesc);
          }
        }
        for (DRepresentationDescriptor representationDesc : DialectManager.INSTANCE.getRepresentationDescriptors(root, session)) {
          currentDiagram = representationDesc.getRepresentation();
          if (isTaggedRepresentation(currentDiagram)) {
            result.add(representationDesc);
          }
        }
      }
    }
    return result;
  }
}
