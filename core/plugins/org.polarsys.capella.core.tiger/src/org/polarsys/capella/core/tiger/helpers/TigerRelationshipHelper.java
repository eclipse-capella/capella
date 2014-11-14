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
package org.polarsys.capella.core.tiger.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.KeyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.Messages;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.impl.Transfo;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * EMF Helper to provide transformation and update services
 * TODO provide a better tooling than attach-* methods
 */
public class TigerRelationshipHelper {

  protected static Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);
  
  /**
   * The name of the property which holds the transformation UID
   */
  public static final String PROPERTY_TRANSFO_UID = "TransfoUID"; //$NON-NLS-1$

  /**
   * Adds an element linked through the relationship by using its name.
   * <p/>
   * It uses the meta-model to proceed a model action.
   * @param relatedElement_p The new element to be linked
   * @param relationshipString_p The relation name
   */
  @SuppressWarnings( { "unchecked" })
  public static boolean attachElementByRel(EObject element_p, EObject relatedElement_p, EReference relationship_p) {

    if (element_p == null) {
      throw new NullPointerException(Messages.TigerRelationshipHelper_CannotAttachNull);
    }

    if (relationship_p == null) {
      return false;
    }

    boolean done = false;
    boolean alreadyExist = false;

    if (isApplicable(element_p.eClass(), relationship_p)) {

      if (!relationship_p.isMany()) {
        if ((element_p.eGet(relationship_p) == null && relatedElement_p != null)
            || (element_p.eGet(relationship_p) != null && !element_p.eGet(relationship_p).equals(relatedElement_p))) {
          element_p.eSet(relationship_p, relatedElement_p);
          done = true;
        } else if ((element_p.eGet(relationship_p) == null && relatedElement_p == null)
                   || (element_p.eGet(relationship_p) != null && element_p.eGet(relationship_p).equals(relatedElement_p))) {
          alreadyExist = true;
        }
      } else {
        EList<EObject> list = (EList<EObject>) element_p.eGet(relationship_p);

        if (list.contains(relatedElement_p)) {
          done = false;
          alreadyExist = true;
        } else {
          try {
            list.add(relatedElement_p);
            done = true;

          } catch (ArrayStoreException exception) {
            done = false;
          } catch (IllegalArgumentException exception) {
            done = false;
          }
        }
      }
    }

    if (done && _logger.isInfoEnabled()) {
      String nlsKey = null;
      if (relationship_p.isContainment()) {
        nlsKey = Messages.TigerRelationshipHelper_ContainedBy;
      } else {
        nlsKey = Messages.TigerRelationshipHelper_ReferencedBy;
      }
      String text = NLS.bind(nlsKey, new Object[] { EObjectLabelProviderHelper.getText(relatedElement_p), EObjectLabelProviderHelper.getText(element_p), relationship_p.getName() });
      _logger.info(new EmbeddedMessage(text, _logger.getName(), new Object[] { relatedElement_p, element_p }));
    } else if (!alreadyExist) {
      String nlsKey = null;
      if (relationship_p.isContainment()) {
        nlsKey = Messages.TigerRelationshipHelper_ShouldBeContainedBy;
      } else {
        nlsKey = Messages.TigerRelationshipHelper_ShouldBeReferencedBy;
      }
      String text = NLS.bind(nlsKey, new Object[] { EObjectLabelProviderHelper.getText(relatedElement_p), EObjectLabelProviderHelper.getText(element_p), relationship_p.getName() });
      _logger.warn(new EmbeddedMessage(text, _logger.getName(), new Object[] { relatedElement_p, element_p }));
    }
    

    if (_logger.isDebugEnabled()){
      String message = "          => Attempt attach " + DebugHelper.elementToString(element_p) + "---" + relationship_p.getName() + "--->" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                       + DebugHelper.elementToString(relatedElement_p);
  
      if (done)
        message += " -> [OK] "; //$NON-NLS-1$
      else
        message += " -> [KO] "; //$NON-NLS-1$
  
      _logger.debug(message);
    }
    return done;

  }

  /**
   * Allows to link the n-th transitioned element of sourceElement_p with the given eClass to the n-th element of transitioned element of
   * source.get(relationshipMapping_p)
   * @param sourceElement_p
   * @param sourceEClass_p
   * @param targetEClass_p
   * @param relationshipMapping_p
   * @param transfo_p
   */
  public static void attachIemeWithIeme(EObject sourceElement_p, EClass sourceEClass_p, EClass targetEClass_p, EReference relationshipMapping_p, ITransfo transfo_p) {
    int i = 0;
    for (EObject obj : Query.retrieveTransformedElements(sourceElement_p, transfo_p, sourceEClass_p)) {
      int j = 0;
      for (EObject related : Query.retrieveTransformedElements((EObject) sourceElement_p.eGet(relationshipMapping_p), transfo_p, targetEClass_p)) {
        if (i == j) {
          TigerRelationshipHelper.attachElementByRel(obj, related, relationshipMapping_p);
        }
        j++;
      }
      i++;
    }

  }

  /**
   * use instead attachUnattachedIntoTransformedContainerLikeSource
   */
  @Deprecated
  public static void attachLikeSourceContainerRelation(EObject srcElt_p, ITransfo transfo_p) {
    // Retrieve the TargetContainer and TargetElement
    for (EObject targetElement : Query.retrieveTransformedElements(srcElt_p, transfo_p)) {
      for (EObject targetElementContainer : Query.retrieveTransformedElements(srcElt_p.eContainer(), transfo_p)) {
        EcoreUtil2.attachLikeSourceContainmentFeature(srcElt_p, targetElement, targetElementContainer);
      }
    }
  }

  public static void attachToBestElement(EObject element_p, EReference reference_p, ITransfo transfo_p) {
    if ((element_p == null) || !isApplicable(element_p.eClass(), reference_p)) {
      return;
    }

    for (EObject targetElement : Query.retrieveTransformedElements(element_p, transfo_p)) {
      if (isApplicable(targetElement.eClass(), reference_p)) {
        Object resultTarget = targetElement.eGet(reference_p);

        for (EObject sourceElement : retrieveReferenceAsList(element_p, reference_p)) {
          for (EObject bestElement : retrieveBestElements(targetElement, sourceElement, (EClass) reference_p.getEType(), transfo_p)) {

            if (reference_p.isMany() || resultTarget == null
                || (resultTarget != null && (resultTarget.equals(sourceElement) || resultTarget.equals(bestElement)))) {
              if (bestElement != sourceElement) {
                detachElementByRel(targetElement, sourceElement, reference_p);
              }
              attachElementByRel(targetElement, bestElement, reference_p);
            }
          }
        }
      }

    }

  }

  /**
   * Attaches the trace element to the nearest container which can hold traces.
   * @param object_p The object to start the search
   * @param trace_p The trace
   * @return <code>true</code> if the attachment has been done
   */
  public static boolean attachTraceToNearestContainer(EObject object_p, Trace trace_p) {
    Structure container = null;
    boolean found = false;

    // Finds the first container of trace on top
    EObject currentObject = object_p;
    while ((object_p != null) && !found) {
      // Structures can hold traces
      if (object_p instanceof Structure) {
        container = (Structure) object_p;
      }
      currentObject = currentObject.eContainer();
    }

    // If element found, attaches it
    if (container != null) {
      container.getOwnedTraces().add(trace_p);
      return true;
    }

    return false;
  }

  /**
   * Attaches the transformed contained elements by the specified relationship. (use instead attachUnattachedIntoTransformedContainer)
   * @param sourceElement_p The source element
   * @param transfo_p The transformation
   * @param relationship_p The relationship name
   * @return <code>true</code> if the attachment succeeded
   */
  @Deprecated
  public static boolean attachTransformedContainedElements(EObject sourceElement_p, ITransfo transfo_p, EReference relationship_p) {
    return attachTransformedContainedElementsByMapping(sourceElement_p, transfo_p, relationship_p, relationship_p);
  }

  /**
   * Attaches the transformed contained elements by the specified relationship mapping. (use instead attachUnattachedIntoTransformedContainer)
   * @param sourceElement_p The source element
   * @param transfo_p The transformation
   * @param sourceRelationship_p The source relationship name
   * @param targetRelationship_p The target relationship name
   * @return <code>true</code> if the attachment succeeded
   */
  @Deprecated
  public static boolean attachTransformedContainedElementsByMapping(EObject sourceElement_p, ITransfo transfo_p, EReference sourceRelationship_p, EReference targetRelationship_p) {
    return attachTransformedContainedElementsByMapping(sourceElement_p, transfo_p, sourceRelationship_p, targetRelationship_p, null, null);
  }

  /**
   * Attaches the transformed contained elements by the specified relationship mapping. (use instead attachUnattachedIntoTransformedContainer)
   * @param sourceElement_p The source element
   * @param transfo_p The transformation
   * @param sourceRelationship_p The source relationship name
   * @param targetRelationship_p The target relationship name
   * @return <code>true</code> if the attachment succeeded
   */
  @Deprecated
  @SuppressWarnings("unchecked")
  public static boolean attachTransformedContainedElementsByMapping(EObject sourceElement_p, ITransfo transfo_p, EReference sourceRelationship_p, EReference targetRelationship_p, EClass expectedTarget_p, EClass expectedTargetContainer_p) {

    // 1- The containment feature is not the one expected
    if (!sourceElement_p.eContainmentFeature().equals(sourceRelationship_p)) {
      return false;
    }

    Object te = Query.retrieveTransformedElement(sourceElement_p, transfo_p, expectedTarget_p);
    if (te == null) {
      return false;
    }

    if (te instanceof EObject) {
      EObject targetElement = (EObject) te;
      EObject currentTgtContainerElt = targetElement.eContainer();
      EObject srcContainerElt = sourceElement_p.eContainer();
      Object tgtContainerElt = Query.retrieveTransformedElement(srcContainerElt, transfo_p, expectedTargetContainer_p);

      if (tgtContainerElt instanceof EObject) {

        if (currentTgtContainerElt == tgtContainerElt) {
          return true;
        }

        if (!(attachElementByRel((EObject) tgtContainerElt, targetElement, targetRelationship_p))) {
          return false;
        }

      } else {
        boolean isAttached = false;

        for (EObject tgtContainerE : (List<EObject>) tgtContainerElt) {
          if (currentTgtContainerElt == tgtContainerE) {
            return true;
          }

          if (attachElementByRel(tgtContainerE, targetElement, targetRelationship_p)) {
            isAttached = true;
            if (targetRelationship_p.isContainment()) {
              return true;
            }
          }
        }
        return isAttached;
      }

    } else {
      List<EObject> tes = (List<EObject>) te;
      for (EObject targetElement : tes) {
        EObject currentTgtContainerElt = targetElement.eContainer();
        EObject srcContainerElt = sourceElement_p.eContainer();
        EObject tgtContainerElt = Query.retrieveFirstTransformedElement(srcContainerElt, transfo_p, expectedTargetContainer_p);

        if (currentTgtContainerElt == tgtContainerElt) {
          return true;
        }

        if (!(attachElementByRel(tgtContainerElt, targetElement, targetRelationship_p))) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * Attach transitioned elements into the first container of transitioned container of sourceElement_p which can contains the transitioned element
   * @param sourceElement_p
   * @param transfo_p
   * @param sourceRelationship_p
   * @param targetRelationship_p
   * @param expectedTarget_p
   * @param expectedTargetContainer_p
   * @return
   */
  public static boolean attachTransformedContainedElementsInBestContainer(EObject sourceElement_p, ITransfo transfo_p, EReference sourceRelationship_p, EReference targetRelationship_p, EClass expectedTarget_p, EClass expectedTargetContainer_p) {

    // 1- The containment feature is not the one expected
    if (!sourceElement_p.eContainmentFeature().equals(sourceRelationship_p)) {
      return false;
    }

    EObject targetElement = Query.retrieveFirstTransformedElement(sourceElement_p, transfo_p, expectedTarget_p);
    if (targetElement == null) {
      return false;
    }

    EObject currentTgtContainerElt = targetElement.eContainer();
    EObject srcContainerElt = sourceElement_p.eContainer();
    EObject tgtContainerElt = Query.retrieveFirstTransformedElement(srcContainerElt, transfo_p, expectedTargetContainer_p);

    if ((tgtContainerElt != null) && (currentTgtContainerElt == tgtContainerElt)) {
      return true;
    }

    while (srcContainerElt != null) {
      if ((tgtContainerElt != null) && EcoreUtil2.isEqualOrSuperClass(expectedTargetContainer_p, tgtContainerElt.eClass())) {
        if (attachElementByRel(tgtContainerElt, targetElement, targetRelationship_p)) {
          return true;
        }
      }
      srcContainerElt = srcContainerElt.eContainer();
      tgtContainerElt = Query.retrieveFirstTransformedElement(srcContainerElt, transfo_p, expectedTargetContainer_p);
    }

    return false;
  }

  /**
   * @param element_p
   * @param transfo_p
   * @param scenarioOwnedAbstractEnds_p
   */
  @Deprecated
  public static boolean attachTransformedContainedElementsPreserveOrder(EObject element_p, ITransfo transfo_p, EReference relationship_p) {
    return attachTransformedContainedElementsPreserveOrder(element_p, transfo_p, relationship_p, relationship_p);
  }

  /**
   * Attach the containment relationship by preserving orders of transformed elements relatively to their position in the source relationship
   * @param element_p
   * @param transfo_p
   * @param targetRelationShip_p .getName ()
   * @param scenarioOwnedAbstractEnds_p
   */
  @Deprecated
  @SuppressWarnings("unchecked")
  public static boolean attachTransformedContainedElementsPreserveOrder(EObject sourceElement_p, ITransfo transfo_p, EReference sourceRelationship_p, EReference targetRelationship_p) {
    // 1- The containment feature is not the one expected
    if (!sourceElement_p.eContainmentFeature().equals(sourceRelationship_p)) {
      return false;
    }

    EObject targetElement = (EObject) Query.retrieveTransformedElement(sourceElement_p, transfo_p);

    if (targetElement == null) {
      return false;
    }

    EObject currentTargetContainerElement = targetElement.eContainer();
    EObject sourceContainerElement = sourceElement_p.eContainer();
    EObject targetContainerElement = (EObject) Query.retrieveTransformedElement(sourceContainerElement, transfo_p);

    if (currentTargetContainerElement == targetContainerElement) {
      return true;
    }

    if (!sourceRelationship_p.isMany()) {
      return attachElementByRel(targetContainerElement, targetElement, targetRelationship_p);
    }
    EList<EObject> srcList = (EList<EObject>) sourceContainerElement.eGet(sourceRelationship_p);
    EList<EObject> tgtList = (EList<EObject>) targetContainerElement.eGet(targetRelationship_p);

    int indiceOfElementToAddInSource = srcList.indexOf(sourceElement_p);

    // simple case : tgtList is empty, same than non preserving order
    if (tgtList.size() == 0) {
      return attachElementByRel(targetContainerElement, targetElement, targetRelationship_p);
    }

    // to simplify algorithm, i'm creating a map of inverse transformation
    Map<EObject, EObject> inverseTransfo = new HashMap<EObject, EObject>();
    for (EObject srcObj : srcList) {
      EObject tgtObj = (EObject) Query.retrieveTransformedElement(srcObj, transfo_p);
      inverseTransfo.put(tgtObj, srcObj);
    }

    // At which position can i insert my new object ? Starting from the end,
    // i'm looking for the indice of an element BEFORE
    // my sourceObject
    int indice = tgtList.size() - 1;
    while (indice >= 0) {
      EObject tgtObject = tgtList.get(indice);
      EObject srcObject = inverseTransfo.get(tgtObject);
      if (srcList.indexOf(srcObject) < indiceOfElementToAddInSource) {
        break; // found the indice of an element before the element to
      }
      // add in the source list
      indice--;
    }
    // we can add it just after its first preceding element.
    tgtList.add(indice + 1, targetElement);

    return true;

  }

  /**
   * Attaches the transformed elements through the specified relationship
   * @param sourceElement_p The source element
   * @param relationship The relationship name
   * @param transfo_p The transformation
   */
  public static void attachTransformedRelatedElements(EObject sourceElement_p, EReference relationship, ITransfo transfo_p) {
    attachTransformedRelatedElementsByMapping(sourceElement_p, relationship, relationship, transfo_p);
  }

  /**
   * Attaches the transformed element through a list of relationships
   * @param sourceElement_p The source element
   * @param relationships The relationship names
   * @param transfo_p The transformation
   */
  public static void attachTransformedRelatedElements(EObject sourceElement_p, EReference[] relationships, ITransfo transfo_p) {
    for (EReference relationship : relationships) {
      attachTransformedRelatedElements(sourceElement_p, relationship, transfo_p);
    }
  }

  /**
   * Attaches the transformed elements through the specified relationship
   * @param sourceElement_p The source element
   * @param relationship The relationship name
   * @param transfo_p The transformation
   */
  public static void attachTransformedRelatedElements(EObject sourceElement_p, ITransfo transfo_p, EReference relationship_p) {
    attachTransformedRelatedElementsByMapping(sourceElement_p, relationship_p, relationship_p, transfo_p);
  }

  /**
   * Attaches transformed related elements by mapping of relationship names
   * @param sourceElement_p The source element
   * @param relationshipInSource_p The source relationship name
   * @param relationshipInTarget_p The target relationship name (mapped)
   * @param transfo_p The transformation
   */
  @SuppressWarnings("unchecked")
  public static void attachTransformedRelatedElementsByMapping(EObject sourceElement_p, EReference relationshipInSource_p, EReference relationshipInTarget_p, ITransfo transfo_p) {

    List<EObject> sourceRelatedElements = Query.retrieveRelatedElements(sourceElement_p, relationshipInSource_p);

    // /////////////////////////////

    List<EObject> targetRelatedElements = new ArrayList<EObject>();
    for (EObject sourceRelatedElement : sourceRelatedElements) {
      if (sourceRelatedElement != null) {
        Object tre = Query.retrieveTransformedElement(sourceRelatedElement, transfo_p);
        if (tre != null) {
          if (tre instanceof EObject) {
            EObject targetRelatedElement = (EObject) tre;
            if (targetRelatedElement != null) {
              targetRelatedElements.add(targetRelatedElement);
            }
          } else {
            List<EObject> tres = (List<EObject>) tre;
            for (EObject targetRelatedElement : tres) {
              targetRelatedElements.add(targetRelatedElement);
            }
          }
        }
      }

    }

    // "Just add" version
    List<? extends EObject> targetElements = Query.retrieveTransformedElements(sourceElement_p, transfo_p);
    for (EObject newlyCreatedElement : targetRelatedElements) {
      for (EObject targetElement : targetElements) {
        if (isApplicable(targetElement.eClass(), relationshipInTarget_p)) {
          attachElementByRel(targetElement, newlyCreatedElement, relationshipInTarget_p);
        }
      }
    }
  }

  public static void attachTransformedRelatedElementsByMapping(EObject sourceElement_p, ITransfo transfo_p, EReference relationshipInSource_p, EReference relationshipInTarget_p) {
    attachTransformedRelatedElementsByMapping(sourceElement_p, relationshipInSource_p, relationshipInTarget_p, transfo_p);
  }

  /**
   * Attaches the transformed elements through the specified relationship mapping
   * @param sourceElement_p The source element
   * @param relationshipMapping_p The relationship mapping
   * @param transfo_p The transformation
   */
  public static void attachTransformedRelatedElementsByMapping(EObject sourceElement_p, List<EReference[]> relationshipMapping_p, ITransfo transfo_p) {
    for (EReference[] mapping : relationshipMapping_p) {
      attachTransformedRelatedElementsByMapping(sourceElement_p, mapping[0], mapping[1], transfo_p);
    }
  }

  /**
   * Attaches unattached transformed contained elements by the specified relationship.
   * @param sourceElement_p The source element
   * @param transfo_p The transformation
   * @param relationship_p The relationship name
   * @return <code>true</code> if the attachment succeeded
   */
  public static void attachUnattachedIntoSameContainer(EObject source_p, EClass targetEClass, EReference relationship_p, ITransfo transfo_p) {
    EObject newContainer = source_p.eContainer();
    for (EObject targetElement : Query.retrieveUnattachedTransformedElements(source_p, transfo_p, targetEClass)) {
      attachElementByRel(newContainer, targetElement, relationship_p);
    }
  }

  /**
   * Attaches unattached transitioned elements from source_p and with the targetEClass type
   * @param source_p the source
   * @param targetEClass the eclass of transitioned item which will be moved
   * @param relationship_p the containing reference
   * @param transfo_p
   */
  public static void attachUnattachedIntoTransformedContainer(EObject source_p, EClass targetEClass, EReference relationship_p, ITransfo transfo_p) {
    // Attach in the first transitioned element which can contains transitioned elements
    for (EObject newContainer : Query.retrieveTransformedElements(source_p.eContainer(), transfo_p)) {
      if (isApplicable(newContainer.eClass(), relationship_p)) {
        for (EObject targetElement : Query.retrieveUnattachedTransformedElements(source_p, transfo_p, targetEClass)) {
          attachElementByRel(newContainer, targetElement, relationship_p);
        }
        break;
      }
    }
  }

  /**
   * Attaches unattached transitioned elements from source_p and with the targetEClass type
   * @param source_p the source
   * @param targetEClass the eclass of transitioned item which will be moved
   * @param relationship_p the containing reference
   * @param transfo_p
   */
  public static void attachUnattachedIntoTransformedContainer(EObject source_p, EClass targetEClass, EReference[] relationships_p, ITransfo transfo_p) {
    // Attach in the first transitioned element which can contains transitioned elements
    for (EObject newContainer : Query.retrieveTransformedElements(source_p.eContainer(), transfo_p)) {
      boolean isAttached = false;
      for (EReference relationship_p : relationships_p) {
        if (isApplicable(newContainer.eClass(), relationship_p)) {
          for (EObject targetElement : Query.retrieveUnattachedTransformedElements(source_p, transfo_p, targetEClass)) {
            attachElementByRel(newContainer, targetElement, relationship_p);
          }
          isAttached = true;
        }
        if (isAttached) {
          break;
        }
      }
    }
  }

  /**
   * Attaches the targetElement into the transitioned container of source_p
   * @param source_p the source
   * @param targetEClass the eclass of transitioned item which will be moved
   * @param relationship_p the containing reference
   * @param transfo_p
   */
  public static void attachUnattachedIntoTransformedContainer(EObject source_p, EObject targetElement, EReference relationship_p, ITransfo transfo_p) {
    // Attach in the first transitioned element which can contains transitioned elements
    for (EObject newContainer : Query.retrieveTransformedElements(source_p.eContainer(), transfo_p)) {
      if (isApplicable(newContainer.eClass(), relationship_p)) {
        attachElementByRel(newContainer, targetElement, relationship_p);
        break;
      }
    }
  }

  /**
   * Attaches unattached transitioned elements from source_p and with the targetEClass type USE IT WHEN SOURCE CAN BE CONTAINED INTO SOME CONTAINER
   * @param source_p the source
   * @param targetEClass the eclass of transitioned item which will be moved
   * @param relationship_p the containing reference
   * @param transfo_p
   */
  public static void attachUnattachedIntoTransformedContainerLikeSource(EObject source_p, EClass targetEClass, ITransfo transfo_p) {
    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(source_p, targetEClass, (EReference) source_p.eContainingFeature(), transfo_p);
  }

  /**
   * Attaches unattached transitioned elements from source_p and with the targetEClass type
   * @param source_p the source
   * @param targetEClass the eclass of transitioned item which will be moved
   * @param relationship_p the containing reference
   * @param transfo_p
   */
  public static void attachUnattachedIntoTransformedGivenContainer(EObject source_p, EObject givenContainer_p, EClass targetEClass, EReference relationship_p, ITransfo transfo_p) {
    // Attach in the first transitioned element which can contains transitioned elements
    for (EObject newContainer : Query.retrieveTransformedElements(givenContainer_p, transfo_p)) {
      boolean isAttached = false;
      if (isApplicable(newContainer.eClass(), relationship_p)) {
        for (EObject targetElement : Query.retrieveUnattachedTransformedElements(source_p, transfo_p, targetEClass)) {
          attachElementByRel(newContainer, targetElement, relationship_p);
        }
        isAttached = true;
      }
      if (isAttached) {
        break;
      }
    }
  }

  /**
   * Create a link with a specific kind in according to source and target element type
   */
  private static AbstractTrace createAbstractTrace(EObject sourceType_p, EObject targetType_p, ITransfo transfo_p) throws TransfoException {
    AbstractTrace abstractTrace = null;
    EClass eGenericTraceFound = null;

    if (transfo_p instanceof Transfo) {
      eGenericTraceFound = ((Transfo) transfo_p).getSpecificLinkKindFromMap(sourceType_p, targetType_p);
      if (eGenericTraceFound == null) {
        eGenericTraceFound = ((Transfo) transfo_p).get_eDefaultTrace();
      }
    }

    if (eGenericTraceFound != null) {
      EFactory factory = eGenericTraceFound.getEPackage().getEFactoryInstance();
      // we only create the link if it doesn't exists.
      abstractTrace = traceExists(targetType_p, sourceType_p, eGenericTraceFound);
      if (null == abstractTrace) {
        EObject eObject = factory.create(eGenericTraceFound);
        if (eObject == null) {
          throw new TransfoException(eGenericTraceFound);
        }

        if (eObject instanceof AbstractTrace) {
          abstractTrace = (AbstractTrace) eObject;
        }
      }
    }
    return abstractTrace;
  }

  /**
   * Creates a transformation link between 2 elements and returns it
   * @param sourceObject_p The source element of the link
   * @param targetObject_p The target element of the link
   * @param transfo_p The transformation
   * @return The new transformation link
   * @throws TransfoException
   */
  public static AbstractTrace createTransfoLink(EObject sourceObject_p, EObject targetObject_p, ITransfo transfo_p) throws TransfoException {
    AbstractTrace abstractTrace = null;

    if ((sourceObject_p instanceof CapellaElement) && (targetObject_p instanceof CapellaElement)) {
      // 1- Get the source and target as Capella elements
      CapellaElement sourceElement = (CapellaElement) sourceObject_p;
      CapellaElement targetElement = (CapellaElement) targetObject_p;

      if ((sourceElement != null) && (targetElement != null)) {
        // 2- Create the transformation link
        abstractTrace = createAbstractTrace(sourceElement, targetElement, transfo_p);
        if (abstractTrace != null) {
          if (abstractTrace instanceof GenericTrace) {
            GenericTrace genericTrace = (GenericTrace) abstractTrace;
            // 3- The marker to specify the UID of the
            // transformation of the link
            KeyValue keyValue = CapellacoreFactory.eINSTANCE.createKeyValue();
            keyValue.setKey(PROPERTY_TRANSFO_UID);
            keyValue.setValue(transfo_p.getUid());
            genericTrace.getKeyValuePairs().add(keyValue);
          }

          // 4- Update relationships and roles
          abstractTrace.setSourceElement(targetElement);
          abstractTrace.setTargetElement(sourceElement);
        }
      }

    }
    return abstractTrace;
  }

  /**
   * Detaches the elements which are attached by the specified relationship from the source element.
   * <p/>
   * It uses the meta-model to proceed a model action.
   * @param element_p The element
   * @param relatedElement_p The related element
   * @param relationship_p The relationship name
   * @return <code>true</code> if the element is detached
   */
  @SuppressWarnings("unchecked")
  public static boolean detachElementByRel(EObject element_p, EObject relatedElement_p, EReference relationship_p) {

    if ((element_p == null) || (relatedElement_p == null)) {
      throw new NullPointerException(Messages.TigerRelationshipHelper_CannotAttachNull);
    }

    boolean done = false;
    if (!relationship_p.isMany()) {
      element_p.eUnset(relationship_p);
      done = true;
    } else {
      EList<EObject> list = (EList<EObject>) element_p.eGet(relationship_p);
      if (!list.contains(relatedElement_p)) {
        done = false;
      } else {
        list.remove(relatedElement_p);
        done = true;
      }
    }

    if (done && _logger.isInfoEnabled()) {
      String text = NLS.bind("Element ''{0}'' has been removed from ''{1}'' ({2})",  //$NON-NLS-1$
          new Object[] { EObjectLabelProviderHelper.getText(relatedElement_p), 
                         EObjectLabelProviderHelper.getText(element_p), 
                         relationship_p.getName() });
      _logger.info(new EmbeddedMessage(text, _logger.getName(), new Object[] { relatedElement_p, element_p }));
    }
    return done;

  }

  /**
   * Return a list containing only the container of the element. Useful for retrieveRelatedElements methods.
   * @param element_p
   * @return
   */
  public static List<EObject> getJustContainer(EObject element_p) {
    List<EObject> relatedElements = new ArrayList<EObject>();
    relatedElements.add(element_p.eContainer());
    return relatedElements;
  }

  /**
   * Returns whether the feature is available in the clazz
   */
  public static boolean isApplicable(EClass clazz_p, EStructuralFeature feature_p) {
    return EcoreUtil2.isEqualOrSuperClass(feature_p.getEContainingClass(), clazz_p);
  }

  /**
   * Retrieve the transitioned elements of the given object_p with the given clazz or the object_p if it isn't yet transitioned
   */
  @SuppressWarnings("unchecked")
  protected static Collection<EObject> retrieveBestElements(EObject source_p, EObject object_p, EClass clazz, ITransfo transfo_p) {
    Collection<EObject> objects = (Collection<EObject>) Query.retrieveTransformedElements(object_p, transfo_p, clazz);
    Collection<EObject> result = new LinkedList<EObject>();

    // Retrieves only transitioned elements which are in same or previous architectures of the source_p element
    BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(source_p);
    for (EObject object : objects) {
      BlockArchitecture targetArchitecture = BlockArchitectureExt.getRootBlockArchitecture(object);
      if ((sourceArchitecture == null)
          || (targetArchitecture == null)
          || (targetArchitecture.equals(sourceArchitecture) || BlockArchitectureExt.getPreviousBlockArchitectures(sourceArchitecture).contains(targetArchitecture))) {
        result.add(object);
      }
    }

    if (result.size() == 0) {
      result = Collections.singleton(object_p);
    }
    return result;
  }

  /**
   * Returns the given reference value as a list of elements
   */
  @SuppressWarnings("unchecked")
  protected static Collection<EObject> retrieveReferenceAsList(EObject object_p, EReference reference_p) {
    Object sourceReference = object_p.eGet(reference_p);
    if (sourceReference instanceof Collection<?>) {
      return (Collection<EObject>) sourceReference;
    }
    return Collections.singleton((EObject) sourceReference);
  }

  /**
   * @param sourceTypeP the source of the trace to find
   * @param targetTypeP the target of the trace to find
   * @param eGenericTraceFound the eClass of the trace to find
   * @return the trace if it exists, null otherwise.
   */
  private static AbstractTrace traceExists(EObject sourceTypeP, EObject targetTypeP, EClass eGenericTraceFound) {

    TraceableElement source = (TraceableElement) sourceTypeP;
    TraceableElement target = (TraceableElement) targetTypeP;

    for (AbstractTrace trace : source.getOutgoingTraces()) {
      if ((trace.eClass() == eGenericTraceFound) && (trace.getTargetElement() == target)) {
        return trace;
      }
    }
    return null;
  }

  /*
   * Updates an element by copying a element's property to a the transformed element's property
   * @param sourceElement_p The source element
   * @param property_p The name of the property
   * @param transfo_p The transformation
   */
  public static void updateElementByAttribute(EObject sourceElement_p, EObject targetElement_p, EAttribute attribute_p, ITransfo transfo_p) {
    if (targetElement_p != null) {
      EClass clazzAttribute = (EClass) attribute_p.eContainer();
      EClass clazz = sourceElement_p.eClass();
      if (clazzAttribute.isSuperTypeOf(clazz)) {
        Object value = sourceElement_p.eGet(attribute_p);
        targetElement_p.eSet(attribute_p, value);
        if (_logger.isDebugEnabled()){
          _logger.debug("       => Update attribute " //$NON-NLS-1$
                          + attribute_p.getName() + " on element " + DebugHelper.elementToString(targetElement_p)); //$NON-NLS-1$
        }
      }
    }
  }

  /**
   * Updates an element by copying a element's property to a the transformed element's property
   * @param sourceElement_p The source element
   * @param property_p The name of the property
   * @param transfo_p The transformation
   */
  public static void updateElementByAttributeIfEmpty(EObject sourceElement_p, EObject targetElement_p, EAttribute attribute_p, ITransfo transfo_p) {
    if (targetElement_p != null) {
      EClass clazzAttribute = (EClass) attribute_p.eContainer();
      EClass clazzSource = sourceElement_p.eClass();
      EClass clazzTarget = targetElement_p.eClass();
      if (clazzAttribute.isSuperTypeOf(clazzSource) && clazzAttribute.isSuperTypeOf(clazzTarget)) {
        Object valueSource = sourceElement_p.eGet(attribute_p);
        Object valueTarget = targetElement_p.eGet(attribute_p);

        if ((valueSource != null) && (valueSource != ICommonConstants.EMPTY_STRING)
            && ((valueTarget == null) || ((valueTarget instanceof String) && (((String) valueTarget).length() == 0))) && !valueSource.equals(valueTarget)) {
          targetElement_p.eSet(attribute_p, valueSource);
          if (!((valueTarget == null) || ((valueTarget instanceof String) && (((String) valueTarget).length() == 0)))) {
            if (_logger.isInfoEnabled()){
              String text = NLS.bind(Messages.TigerRelationshipHelper_UpdateAttribute, new Object[] { attribute_p.getName(), 
                                                                                                      EObjectLabelProviderHelper.getText(targetElement_p), 
                                                                                                      DebugHelper.elementToString(valueTarget), 
                                                                                                      DebugHelper.elementToString(valueSource) });
              
              _logger.info(new EmbeddedMessage(text, _logger.getName(), targetElement_p));
            }
          }

        }
      }
    }
  }

  /**
   * Updates an element's properties to the transformed element's properties
   * @param sourceElement_p The source element
   * @param properties_p The list of property names
   * @param transfo_p The transformation
   */
  public static void updateElementByProperty(EObject sourceElement_p, List<String> properties_p, ITransfo transfo_p) {
    for (String property : properties_p) {
      updateElementByProperty(sourceElement_p, property, transfo_p);
    }

  }

  /**
   * Updates an element by copying a element's property to a the transformed element's property
   * @param sourceElement_p The source element
   * @param property_p The name of the property
   * @param transfo_p The transformation
   */
  public static void updateElementByProperty(EObject sourceElement_p, String property_p, ITransfo transfo_p) {
    List<? extends EObject> targetElements = Query.retrieveTransformedElements(sourceElement_p, transfo_p);
    if (targetElements.size() > 0) {
      EStructuralFeature featureSource = sourceElement_p.eClass().getEStructuralFeature(property_p);
      if (featureSource instanceof EAttribute) {
        EAttribute attribute = (EAttribute) featureSource;
        Object valueSource = sourceElement_p.eGet(attribute);
        for (EObject targetElement : targetElements) {
          EStructuralFeature featureTarget = targetElement.eClass().getEStructuralFeature(property_p);
          if (featureTarget != null) {
            Object valueTarget = targetElement.eGet(attribute);
            if (((valueSource == null) && (valueTarget != null)) || ((valueSource != null) && !valueSource.equals(valueTarget))) {
              if (!(valueTarget == null)) {
                if (_logger.isInfoEnabled()){
                  String text = NLS.bind(Messages.TigerRelationshipHelper_UpdateAttribute, 
                      new Object[] { attribute.getName(), EObjectLabelProviderHelper.getText(targetElement), DebugHelper.elementToString(valueTarget), DebugHelper.elementToString(valueSource) }); 
                  _logger.info(new EmbeddedMessage(text, _logger.getName(), targetElement)); 
                }
              }
              targetElement.eSet(attribute, valueSource);
            }
          }
        }
      }
    }
  }

}
