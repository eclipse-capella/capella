/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.RefinementLink;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;

/**
 * RefinementLink helpers
 */
public class RefinementLinkExt {

  /**
   * 
   */
  private static final Logger _logger = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.MODEL);

  /**
   * Creates a refinement traceability link owned by the source's first possible container.
   * 
   * @param sourceElt
   * @param targetElt
   * @return RefinementLink
   */
  static public RefinementLink createRefinementTraceabilityLink(NamedElement sourceElt, NamedElement targetElt) {
    return createRefinementTraceabilityLink(sourceElt, targetElt, sourceElt);
  }

  /**
   * Creates a refinement traceability link owned by given NamedElement first possible container.
   * 
   * @param sourceElt
   * @param targetElt
   * @param container
   * @return RefinementLink
   */
  static public RefinementLink createRefinementTraceabilityLink(NamedElement sourceElt, NamedElement targetElt,
      NamedElement container) {
    RefinementLink lnk = null;
    Namespace ownerElt = (Namespace) ((container instanceof Namespace) ? container
        : EcoreUtil2.getFirstContainer(container, CapellacorePackage.Literals.NAMESPACE));

    if (ownerElt != null) {
      lnk = createRefinementTraceabilityLink(sourceElt, targetElt, ownerElt);
    } else {
      _logger.debug(new EmbeddedMessage(
          "The traceability between '" + sourceElt.getName() + "' and '" + targetElt.getName() + "' have no container.", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          IReportManagerDefaultComponents.MODEL));
    }

    return lnk;
  }

  /**
   * Creates a refinement traceability link.
   * 
   * @param sourceElt
   * @param targetElt
   * @param container
   * @return RefinementLink
   */
  static public RefinementLink createRefinementTraceabilityLink(NamedElement sourceElt, NamedElement targetElt,
      Namespace container) {
    RefinementLink lnk = null;

    if (container != null) {
      lnk = InteractionFactory.eINSTANCE.createRefinementLink();
      lnk.setSourceElement(sourceElt);
      lnk.setTargetElement(targetElt);
      container.getOwnedTraces().add(lnk);

      /** do some additional stuff */
      CreationHelper.performContributionCommands(sourceElt, container);
    }

    return lnk;
  }

  /**
   * Returns elements values for sourceElt.eGet(reference) where values isn't the best values used. (best value as in
   * isAttachedToBestElement method)
   */
  public static List<EObject> getInvalidAttachedToBestElement(EObject sourceElt, EReference reference) {
    List<EObject> list = new ArrayList<EObject>();
    if (reference.isMany()) {
      for (Object obj : ((List<?>) sourceElt.eGet(reference))) {
        if ((obj != null) && (obj instanceof EObject)) {
          if (!isAttachedToBestElement(sourceElt, (EObject) obj)) {
            list.add((EObject) obj);
          }
        }
      }
    } else {
      Object obj = sourceElt.eGet(reference);
      if ((obj != null) && (obj instanceof EObject)) {
        if (!isAttachedToBestElement(sourceElt, (EObject) obj)) {
          list.add((EObject) obj);
        }
      }
    }
    return list;
  }

  /**
   * Returns list of attribute values which isn't equals from the source element and refined elements
   */
  public static List<Object> getMissingValuesFromRefined(EObject sourceElt, EAttribute attribute,
      Collection<?> excludeValuesFromSources) {
    List<Object> list = new ArrayList<Object>();

    for (AbstractTrace lnk : ((TraceableElement) sourceElt).getOutgoingTraces()) {
      TraceableElement target = lnk.getTargetElement();

      if ((target != null) && EcoreUtil2.isEqualOrSuperClass(target.eClass(), sourceElt.eClass())) {

        if (attribute.isMany()) {
          for (Object obj : ((List<?>) target.eGet(attribute))) {
            if ((obj != null) && !excludeValuesFromSources.contains(obj)) {
              if (!((List<?>) sourceElt.eGet(attribute)).contains(obj)) {
                list.add(obj);
              }
            }
          }
        } else {
          Object obj = target.eGet(attribute);
          if ((obj != null) && !excludeValuesFromSources.contains(obj)) {
            if (!obj.equals(sourceElt.eGet(attribute))) {
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
   * 
   * @param currentElt
   * @param type
   * @return List<NamedElement>
   */
  static public List<CapellaElement> getRefinementRelatedSourceElements(CapellaElement currentElt, EClass type) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();

    if (currentElt != null) {
      for (AbstractTrace lnk : currentElt.getIncomingTraces()) {
        if (lnk instanceof RefinementLink) {
          TraceableElement elt = lnk.getSourceElement();
          if ((elt instanceof CapellaElement) && (type.isSuperTypeOf(elt.eClass())) && !result.contains(elt)) {
            result.add((CapellaElement) elt);
          }
        }
      }
    }

    return result;
  }

  /**
   * Retrieves all target elements linked, by a refinement traceability link, with a given source element.
   * 
   * @param currentElt
   * @param type
   * @return List<NamedElement>
   */
  static public List<CapellaElement> getRefinementRelatedTargetElements(CapellaElement currentElt, EClass type) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();

    if (currentElt != null) {
      for (AbstractTrace lnk : currentElt.getOutgoingTraces()) {
        if (lnk instanceof RefinementLink) {
          TraceableElement elt = lnk.getTargetElement();
          if ((elt instanceof CapellaElement) && (type.isSuperTypeOf(elt.eClass())) && !result.contains(elt)) {
            result.add((CapellaElement) elt);
          }
        }
      }
    }

    return result;
  }

  /**
   * Retrieves all source elements linked, by a refinement traceability link, with a given target element.
   * 
   * @param currentElt
   * @param type
   * @return List<NamedElement>
   */
  static public List<CapellaElement> getRelatedSourceElements(CapellaElement currentElt, EClass type) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();

    if (currentElt != null) {
      for (AbstractTrace lnk : currentElt.getIncomingTraces()) {
        TraceableElement elt = lnk.getSourceElement();
        if ((elt instanceof CapellaElement) && (type.isSuperTypeOf(elt.eClass())) && !result.contains(elt)) {
          result.add((CapellaElement) elt);
        }
      }
    }

    return result;
  }

  /**
   * Retrieves all target elements linked, by a traceability link, with a given source element.
   * 
   * @param currentElt
   * @param type
   * @return List<NamedElement>
   */
  static public List<CapellaElement> getRelatedTargetElements(CapellaElement currentElt, EClass type) {
    List<CapellaElement> result = new ArrayList<CapellaElement>();

    if (currentElt != null) {
      for (AbstractTrace lnk : currentElt.getOutgoingTraces()) {
        TraceableElement elt = lnk.getTargetElement();
        if ((elt instanceof CapellaElement) && (type.isSuperTypeOf(elt.eClass())) && !result.contains(elt)) {
          result.add((CapellaElement) elt);
        }
      }
    }

    return result;
  }

  /**
   * Returns list of attribute values which isn't equals from the source element and refined elements
   */
  public static boolean hasMissingValuesFromRefined(EObject sourceElt, EAttribute attribute,
      Collection<?> excludeValuesFromSources) {
    return getMissingValuesFromRefined(sourceElt, attribute, excludeValuesFromSources).size() > 0;
  }

  /**
   * Returns whether the element objValue is the best element defined for the sourceElt. (It means returns whether
   * objValue is in the same layer than sourceElement or if it isn't refined in the sourceElt architecture)
   */
  public static boolean isAttachedToBestElement(EObject sourceElt, EObject objValue) {
    if (!CapellaLayerCheckingExt.areInSameLayer(sourceElt, objValue)) {
      if (RefinementLinkExt.isTransitionedInto(objValue, BlockArchitectureExt.getRootBlockArchitecture(sourceElt))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Verifies whether {@link sourceElt} has a refinement link towards {@link targetElt} or not.
   * 
   * @param sourceElt
   * @param targetElt
   */
  static public boolean isLinkedTo(TraceableElement sourceElt, TraceableElement targetElt) {
    if (sourceElt != null) {
      for (AbstractTrace lnk : sourceElt.getOutgoingTraces()) {
        TraceableElement target = lnk.getTargetElement();
        if ((target != null) && target.equals(targetElt)) {
          return true;
        }
      }
    }
    if (sourceElt instanceof Part && targetElt instanceof Part) {
      AbstractType sourceType = ((Part) sourceElt).getAbstractType();
      AbstractType targetType = ((Part) targetElt).getAbstractType();
      if (sourceType instanceof TraceableElement && targetType instanceof TraceableElement) {
        return isLinkedTo((TraceableElement) sourceType, (TraceableElement) targetType);
      }
    }

    return false;
  }

  /**
   * Returns whether the sourceElt is refined or not into the given architecture
   */
  public static boolean isTransitionedInto(EObject sourceElt, BlockArchitecture rootBlockArchitecture) {
    if ((sourceElt != null) && (sourceElt instanceof TraceableElement)) {
      for (AbstractTrace lnk : ((TraceableElement) sourceElt).getIncomingTraces()) {
        TraceableElement target = lnk.getSourceElement();
        if ((target != null) && rootBlockArchitecture.equals(BlockArchitectureExt.getRootBlockArchitecture(target))) {
          return true;
        }
      }
    }
    return false;
  }

}
