/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.updateconnections.ui.tester;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.RePackage;

import com.google.common.base.Predicate;

public class RePropertyTester extends PropertyTester {

  /**
   * Is the tested element a CatalogElement of type RPL
   */
  public static final String PROPERTY_IS_RPL_CATALOG_ELEMENT = "isRplCatalogElement"; //$NON-NLS-1$

  /**
   * Is the tested element used (referenced) in a RPL?
   */
  public static final String PROPERTY_IS_RPL_ELEMENT = "isRplElement"; //$NON-NLS-1$

  /**
   * Is the tested element a CatalogElement of type REC?
   */
  public static final String PROPERTY_IS_REC_CATALOG_ELEMENT = "isRecCatalogElement"; //$NON-NLS-1$

  /**
   * Is the tested element used (referenced) in a REC?
   */
  public static final String PROPERTY_IS_REC_ELEMENT = "isRecElement"; //$NON-NLS-1$

  /**
   * Update connections can be executed on a selection of two elements which are (members of) two distinct rpls.
   */
  public static final String PROPERTY_IS_UPDATE_CONNECTIONS_ENABLED = "isUpdateConnectionsEnabled"; //$NON-NLS-1$

  @Override
  public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {

    switch (property) {
    case PROPERTY_IS_RPL_CATALOG_ELEMENT:
      return isRplCatalogElement(receiver);
    case PROPERTY_IS_RPL_ELEMENT:
      return isRplElement(receiver);
    case PROPERTY_IS_REC_CATALOG_ELEMENT:
      return isRecCatalogElement(receiver);
    case PROPERTY_IS_REC_ELEMENT:
      return isRecElement(receiver);
    case PROPERTY_IS_UPDATE_CONNECTIONS_ENABLED:
      return isUpdateConnectionsEnabled(receiver);
    default:
      throw new IllegalArgumentException("Unknown property: " + property); //$NON-NLS-1$
    }

  }

  private boolean isUpdateConnectionsEnabled(Object receiver) {
    Object[] selected = ((Collection<?>) receiver).toArray();
    if (selected.length == 2) {

      // TODO factor this out
      if (selected[0] instanceof IDiagramElementEditPart) {
        selected[0] = ((IDiagramElementEditPart) selected[0]).resolveTargetSemanticElement();
      }
      if (selected[1] instanceof IDiagramElementEditPart) {
        selected[1] = ((IDiagramElementEditPart) selected[1]).resolveTargetSemanticElement();
      }

      if (selected[0] instanceof EObject && selected[1] instanceof EObject) {
        Collection<CatalogElement> sel0CatalogElements = getCatalogElements((EObject) selected[0],
            Predicates.forKind(CatalogElementKind.RPL));
        Collection<CatalogElement> sel1CatalogElements = getCatalogElements((EObject) selected[1],
            Predicates.forKind(CatalogElementKind.RPL));
        if (selected[0] instanceof CatalogElement) {
          sel0CatalogElements.add((CatalogElement) selected[0]);
        }
        if (selected[1] instanceof CatalogElement) {
          sel1CatalogElements.add((CatalogElement) selected[1]);
        }
        sel1CatalogElements.removeAll(sel0CatalogElements);
        return !sel0CatalogElements.isEmpty() && !sel1CatalogElements.isEmpty();
      }
    }
    return false;
  }

  private boolean isRecElement(Object receiver) {
    if (receiver instanceof EObject) {
      for (CatalogElementLink link : EObjectExt.<CatalogElementLink> getReferencers((EObject) receiver,
          RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET)) {
        if (isRecCatalogElement(link.getSource())) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean isRecCatalogElement(Object receiver) {
    if (receiver instanceof CatalogElement) {
      CatalogElement ce = (CatalogElement) receiver;
      return ce.getKind() == CatalogElementKind.REC || ce.getKind() == CatalogElementKind.REC_RPL;
    }
    return false;
  }

  private boolean isRplElement(Object receiver) {
    if (receiver instanceof EObject) {
      for (CatalogElementLink link : EObjectExt.<CatalogElementLink> getReferencers((EObject) receiver,
          RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET)) {
        if (isRplCatalogElement(link.getSource())) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean isRplCatalogElement(Object receiver) {
    if (receiver instanceof CatalogElement) {
      CatalogElement ce = (CatalogElement) receiver;
      return ce.getKind() == CatalogElementKind.RPL || ce.getKind() == CatalogElementKind.REC_RPL;
    }
    return false;
  }

  /**
   * Find all catalog elements with a given member that match an optional filter.
   * 
   * @param member
   * @param filter
   * @return
   */
  private Collection<CatalogElement> getCatalogElements(EObject member, Predicate<CatalogElement> filter) {
    Collection<CatalogElementLink> links = EObjectExt.getReferencers(member,
        RePackage.Literals.CATALOG_ELEMENT_LINK__TARGET);
    Collection<CatalogElement> result = new ArrayList<CatalogElement>();
    for (CatalogElementLink link : links) {
      if (filter == null || filter.apply(link.getSource())) {
        result.add(link.getSource());
      }
    }
    return result;
  }

  // TODO have a re predicates class in capella core
  public static class Predicates {

    public static final Predicate<CatalogElement> forKind(final CatalogElementKind kind) {
      return new Predicate<CatalogElement>() {
        @Override
        public boolean apply(CatalogElement input) {
          return input.getKind() == kind;
        }
      };
    }

  }

}
