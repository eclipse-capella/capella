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
package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.RefinementLink;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;

/**
 * RefinementLink helpers
 */
public class RefinementLinkExt {

  /**
   * 
   */
  private static final Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);

  /**
   * Creates a refinement traceability link owned by the source's first possible container.
   * @param sourceElt_p
   * @param targetElt_p
   * @return RefinementLink
   */
  static public RefinementLink createRefinementTraceabilityLink(NamedElement sourceElt_p, NamedElement targetElt_p) {
    return createRefinementTraceabilityLink(sourceElt_p, targetElt_p, sourceElt_p);
  }

  /**
   * Creates a refinement traceability link owned by given NamedElement first possible container.
   * @param sourceElt_p
   * @param targetElt_p
   * @param container_p
   * @return RefinementLink
   */
  static public RefinementLink createRefinementTraceabilityLink(NamedElement sourceElt_p, NamedElement targetElt_p, NamedElement container_p) {
    RefinementLink lnk = null;
    Namespace ownerElt =
        (Namespace) ((container_p instanceof Namespace) ? container_p : EcoreUtil2.getFirstContainer(container_p, CapellacorePackage.Literals.NAMESPACE));

    if (ownerElt != null) {
      lnk = createRefinementTraceabilityLink(sourceElt_p, targetElt_p, ownerElt);
    } else {
      _logger
          .debug(new EmbeddedMessage(
              "The traceability between '" + sourceElt_p.getName() + "' and '" + targetElt_p.getName() + "' have no container.", IReportManagerDefaultComponents.MODEL)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    return lnk;
  }

  /**
   * Creates a refinement traceability link.
   * @param sourceElt_p
   * @param targetElt_p
   * @param container_p
   * @return RefinementLink
   */
  static public RefinementLink createRefinementTraceabilityLink(NamedElement sourceElt_p, NamedElement targetElt_p, Namespace container_p) {
    RefinementLink lnk = null;

    if (container_p != null) {
      lnk = InteractionFactory.eINSTANCE.createRefinementLink();
      lnk.setSourceElement(sourceElt_p);
      lnk.setTargetElement(targetElt_p);
      container_p.getOwnedTraces().add(lnk);

      /** do some additional stuff */
      CreationHelper.performContributionCommands(sourceElt_p, container_p);
    }

    return lnk;
  }

  /**
   * Returns elements values for sourceElt_p.eGet(reference) where values isn't the best values used. (best value as in isAttachedToBestElement method)
   */
  public static List<EObject> getInvalidAttachedToBestElement(EObject sourceElt_p, EReference reference) {
    List<EObject> list = new ArrayList<EObject>();
    if (reference.isMany()) {
      for (Object obj : ((List<?>) sourceElt_p.eGet(reference))) {
        if ((obj != null) && (obj instanceof EObject)) {
          if (!isAttachedToBestElement(sourceElt_p, (EObject) obj)) {
            list.add((EObject) obj);
          }
        }
      }
    } else {
      Object obj = sourceElt_p.eGet(reference);
      if ((obj != null) && (obj instanceof EObject)) {
        if (!isAttachedToBestElement(sourceElt_p, (EObject) obj)) {
          list.add((EObject) obj);
        }
      }
    }
    return list;
  }

  /**
   * Returns list of attribute values which isn't equals from the source element and refined elements
   */
  public static List<Object> getMissingValuesFromRefined(EObject sourceElt_p, EAttribute attribute, Collection<?> excludeValuesFromSources) {
    List<Object> list = new ArrayList<Object>();

    for (AbstractTrace lnk : ((TraceableElement) sourceElt_p).getOutgoingTraces()) {
      TraceableElement target = lnk.getTargetElement();

      if ((target != null) && EcoreUtil2.isEqualOrSuperClass(target.eClass(), sourceElt_p.eClass())) {

        if (attribute.isMany()) {
          for (Object obj : ((List<?>) target.eGet(attribute))) {
            if ((obj != null) && !excludeValuesFromSources.contains(obj)) {
              if (!((List<?>) sourceElt_p.eGet(attribute)).contains(obj)) {
                list.add(obj);
              }
            }
          }
        } else {
          Object obj = target.eGet(attribute);
          if ((obj != null) && !excludeValuesFromSources.contains(obj)) {
            if (!obj.equals(sourceElt_p.eGet(attribute))) {
              list.add(obj);
            }
          }
        }
      }
    }

    return list;
  }

  /**
   * Retrieves all source elements linked, by a refinement traceability link, with a given target element.
   * @param currentElt_p
   * @param type_p
   * @return List<NamedElement>
   */
  static public List<CapellaElement> getRefinementRelatedSourceElements(CapellaElement currentElt_p, EClass type_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();

    if (currentElt_p != null) {
      for (AbstractTrace lnk : currentElt_p.getIncomingTraces()) {
        if (lnk instanceof RefinementLink) {
          TraceableElement elt = lnk.getSourceElement();
          if ((elt instanceof CapellaElement) && (type_p.isSuperTypeOf(elt.eClass())) && !result.contains(elt)) {
            result.add((CapellaElement) elt);
          }
        }
      }
    }

    return result;
  }

  /**
   * Retrieves all target elements linked, by a refinement traceability link, with a given source element.
   * @param currentElt_p
   * @param type_p
   * @return List<NamedElement>
   */
  static public List<CapellaElement> getRefinementRelatedTargetElements(CapellaElement currentElt_p, EClass type_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();

    if (currentElt_p != null) {
      for (AbstractTrace lnk : currentElt_p.getOutgoingTraces()) {
        if (lnk instanceof RefinementLink) {
          TraceableElement elt = lnk.getTargetElement();
          if ((elt instanceof CapellaElement) && (type_p.isSuperTypeOf(elt.eClass())) && !result.contains(elt)) {
            result.add((CapellaElement) elt);
          }
        }
      }
    }

    return result;
  }

  /**
   * Retrieves all source elements linked, by a refinement traceability link, with a given target element.
   * @param currentElt_p
   * @param type_p
   * @return List<NamedElement>
   */
  static public List<CapellaElement> getRelatedSourceElements(CapellaElement currentElt_p, EClass type_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();

    if (currentElt_p != null) {
      for (AbstractTrace lnk : currentElt_p.getIncomingTraces()) {
        TraceableElement elt = lnk.getSourceElement();
        if ((elt instanceof CapellaElement) && (type_p.isSuperTypeOf(elt.eClass())) && !result.contains(elt)) {
          result.add((CapellaElement) elt);
        }
      }
    }

    return result;
  }

  /**
   * Retrieves all target elements linked, by a traceability link, with a given source element.
   * @param currentElt_p
   * @param type_p
   * @return List<NamedElement>
   */
  static public List<CapellaElement> getRelatedTargetElements(CapellaElement currentElt_p, EClass type_p) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();

    if (currentElt_p != null) {
      for (AbstractTrace lnk : currentElt_p.getOutgoingTraces()) {
        TraceableElement elt = lnk.getTargetElement();
        if ((elt instanceof CapellaElement) && (type_p.isSuperTypeOf(elt.eClass())) && !result.contains(elt)) {
          result.add((CapellaElement) elt);
        }
      }
    }

    return result;
  }

  /**
   * Returns list of attribute values which isn't equals from the source element and refined elements
   */
  public static boolean hasMissingValuesFromRefined(EObject sourceElt_p, EAttribute attribute, Collection<?> excludeValuesFromSources) {
    return getMissingValuesFromRefined(sourceElt_p, attribute, excludeValuesFromSources).size() > 0;
  }

  /**
   * Returns whether the element objValue is the best element defined for the sourceElt_p. (It means returns whether objValue is in the same layer than
   * sourceElement or if it isn't refined in the sourceElt_p architecture)
   */
  public static boolean isAttachedToBestElement(EObject sourceElt_p, EObject objValue) {
    if (!CapellaLayerCheckingExt.areInSameLayer(sourceElt_p, objValue)) {
      if (RefinementLinkExt.isTransitionedInto(objValue, BlockArchitectureExt.getRootBlockArchitecture(sourceElt_p))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Verifies whether {@link sourceElt_p} has a refinement link towards {@link targetElt_p} or not.
   * @param sourceElt_p
   * @param targetElt_p
   */
  static public boolean isLinkedTo(TraceableElement sourceElt_p, TraceableElement targetElt_p) {
    if (sourceElt_p != null) {
      for (AbstractTrace lnk : sourceElt_p.getOutgoingTraces()) {
        TraceableElement target = lnk.getTargetElement();
        if ((target != null) && target.equals(targetElt_p)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns whether the sourceElt_p is refined or not into the given architecture
   */
  public static boolean isTransitionedInto(EObject sourceElt_p, BlockArchitecture rootBlockArchitecture_p) {
    if ((sourceElt_p != null) && (sourceElt_p instanceof TraceableElement)) {
      for (AbstractTrace lnk : ((TraceableElement) sourceElt_p).getIncomingTraces()) {
        TraceableElement target = lnk.getSourceElement();
        if ((target != null) && rootBlockArchitecture_p.equals(BlockArchitectureExt.getRootBlockArchitecture(target))) {
          return true;
        }
      }
    }
    return false;
  }

}
