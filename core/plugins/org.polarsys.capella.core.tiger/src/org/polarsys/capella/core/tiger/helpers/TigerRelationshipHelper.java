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
package org.polarsys.capella.core.tiger.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.Messages;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.impl.Transfo;

/**
 * EMF Helper to provide transformation and update services
 * TODO provide a better tooling than attach-* methods
 */
public final class TigerRelationshipHelper {

	/**
	 * Constructor
	 */
  private TigerRelationshipHelper() {
	}

protected static final Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);
  
  /**
   * The name of the property which holds the transformation UID
   */
  public static final String PROPERTY_TRANSFO_UID = "TransfoUID"; //$NON-NLS-1$

  /**
   * Adds an element linked through the relationship by using its name.
   * <p/>
   * It uses the meta-model to proceed a model action.
   * @param relatedElement The new element to be linked
   * @param relationship The relation
   */
  @SuppressWarnings( { "unchecked" })
  public static boolean attachElementByRel(EObject element, EObject relatedElement, EReference relationship) {

    if (element == null) {
      throw new NullPointerException(Messages.TigerRelationshipHelper_CannotAttachNull);
    }

    if (relationship == null) {
      return false;
    }

    boolean done = false;
    boolean alreadyExist = false;

    if (isApplicable(element.eClass(), relationship)) {
      if (relationship.isContainment()) {
        HoldingResourceHelper.ensureMoveElement(relatedElement, element);
      }
      if (!relationship.isMany()) {
        if ((element.eGet(relationship) == null && relatedElement != null)
            || (element.eGet(relationship) != null && !element.eGet(relationship).equals(relatedElement))) {
          element.eSet(relationship, relatedElement);
          done = true;
        } else if ((element.eGet(relationship) == null && relatedElement == null)
                   || (element.eGet(relationship) != null && element.eGet(relationship).equals(relatedElement))) {
          alreadyExist = true;
        }
      } else {
        EList<EObject> list = (EList<EObject>) element.eGet(relationship);

        if (list.contains(relatedElement)) {
          done = false;
          alreadyExist = true;
        } else {
          try {
            list.add(relatedElement);
            done = true;

          } catch (ArrayStoreException | IllegalArgumentException ex) {
            done = false;
          }
        }
      }
    }

    if (done && logger.isInfoEnabled()) {
      String nlsKey = null;
      if (relationship.isContainment()) {
        nlsKey = Messages.TigerRelationshipHelper_ContainedBy;
      } else {
        nlsKey = Messages.TigerRelationshipHelper_ReferencedBy;
      }
      String text = NLS.bind(nlsKey, new Object[] { EObjectLabelProviderHelper.getText(relatedElement), EObjectLabelProviderHelper.getText(element), relationship.getName() });
      logger.info(new EmbeddedMessage(text, logger.getName(), new Object[] { relatedElement, element }));
    } else if (!alreadyExist) {
      String nlsKey = null;
      if (relationship.isContainment()) {
        nlsKey = Messages.TigerRelationshipHelper_ShouldBeContainedBy;
      } else {
        nlsKey = Messages.TigerRelationshipHelper_ShouldBeReferencedBy;
      }
      String text = NLS.bind(nlsKey, new Object[] { EObjectLabelProviderHelper.getText(relatedElement), EObjectLabelProviderHelper.getText(element), relationship.getName() });
      logger.warn(new EmbeddedMessage(text, logger.getName(), new Object[] { relatedElement, element }));
    }
    

    if (logger.isDebugEnabled()){
      String message = "          => Attempt attach " + EObjectExt.getText(element) + "---" + relationship.getName() + "--->" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                       + EObjectExt.getText(relatedElement);
  
      if (done)
        message += " -> [OK] "; //$NON-NLS-1$
      else
        message += " -> [KO] "; //$NON-NLS-1$
  
      logger.debug(message);
    }
    return done;

  }

  /**
   * Allows to link the n-th transitioned element of sourceElement with the given eClass to the n-th element of transitioned element of
   * source.get(relationshipMapping)
   * @param sourceElement
   * @param sourceEClass
   * @param targetEClass
   * @param relationshipMapping
   * @param transfo
   */
  public static void attachIemeWithIeme(EObject sourceElement, EClass sourceEClass, EClass targetEClass, EReference relationshipMapping, ITransfo transfo) {
    int i = 0;
    for (EObject obj : Query.retrieveTransformedElements(sourceElement, transfo, sourceEClass)) {
      int j = 0;
      for (EObject related : Query.retrieveTransformedElements((EObject) sourceElement.eGet(relationshipMapping), transfo, targetEClass)) {
        if (i == j) {
          TigerRelationshipHelper.attachElementByRel(obj, related, relationshipMapping);
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
  public static void attachLikeSourceContainerRelation(EObject srcElt, ITransfo transfo) {
    // Retrieve the TargetContainer and TargetElement
    for (EObject targetElement : Query.retrieveTransformedElements(srcElt, transfo)) {
      for (EObject targetElementContainer : Query.retrieveTransformedElements(srcElt.eContainer(), transfo)) {
        EcoreUtil2.attachLikeSourceContainmentFeature(srcElt, targetElement, targetElementContainer);
      }
    }
  }

  public static void attachToBestElement(EObject element, EReference reference, ITransfo transfo) {
    if ((element == null) || !isApplicable(element.eClass(), reference)) {
      return;
    }

    for (EObject targetElement : Query.retrieveTransformedElements(element, transfo)) {
      if (isApplicable(targetElement.eClass(), reference)) {
        Object resultTarget = targetElement.eGet(reference);

        for (EObject sourceElement : retrieveReferenceAsList(element, reference)) {
          for (EObject bestElement : retrieveBestElements(targetElement, sourceElement, (EClass) reference.getEType(), transfo)) {

            if (reference.isMany() || resultTarget == null
                || ((resultTarget.equals(sourceElement) || resultTarget.equals(bestElement)))) {
              if (bestElement != sourceElement) {
                detachElementByRel(targetElement, sourceElement, reference);
              }
              attachElementByRel(targetElement, bestElement, reference);
            }
          }
        }
      }

    }

  }

  /**
   * Attaches the trace element to the nearest container which can hold traces.
   * @param object The object to start the search
   * @param trace The trace
   * @return <code>true</code> if the attachment has been done
   */
  public static boolean attachTraceToNearestContainer(EObject object, Trace trace) {
    Structure container = null;

    // Finds the first container of trace on top
    EObject currentObject = object;
    while (currentObject != null) {
      // Structures can hold traces
      if (object instanceof Structure) {
        container = (Structure) object;
      }
      currentObject = currentObject.eContainer();
    }

    // If element found, attaches it
    if (container != null) {
      container.getOwnedTraces().add(trace);
      return true;
    }

    return false;
  }

  /**
   * Attaches the transformed contained elements by the specified relationship. (use instead attachUnattachedIntoTransformedContainer)
   * @param sourceElement The source element
   * @param transfo The transformation
   * @param relationship The relationship name
   * @return <code>true</code> if the attachment succeeded
   */
  @Deprecated
  public static boolean attachTransformedContainedElements(EObject sourceElement, ITransfo transfo, EReference relationship) {
    return attachTransformedContainedElementsByMapping(sourceElement, transfo, relationship, relationship);
  }

  /**
   * Attaches the transformed contained elements by the specified relationship mapping. (use instead attachUnattachedIntoTransformedContainer)
   * @param sourceElement The source element
   * @param transfo The transformation
   * @param sourceRelationship The source relationship name
   * @param targetRelationship The target relationship name
   * @return <code>true</code> if the attachment succeeded
   */
  @Deprecated
  public static boolean attachTransformedContainedElementsByMapping(EObject sourceElement, ITransfo transfo, EReference sourceRelationship, EReference targetRelationship) {
    return attachTransformedContainedElementsByMapping(sourceElement, transfo, sourceRelationship, targetRelationship, null, null);
  }

  /**
   * Attaches the transformed contained elements by the specified relationship mapping. (use instead attachUnattachedIntoTransformedContainer)
   * @param sourceElement The source element
   * @param transfo The transformation
   * @param sourceRelationship The source relationship name
   * @param targetRelationship The target relationship name
   * @return <code>true</code> if the attachment succeeded
   */
  @Deprecated
  @SuppressWarnings("unchecked")
  public static boolean attachTransformedContainedElementsByMapping(EObject sourceElement, ITransfo transfo, EReference sourceRelationship, EReference targetRelationship, EClass expectedTarget, EClass expectedTargetContainer) {

    // 1- The containment feature is not the one expected
    if (!sourceElement.eContainmentFeature().equals(sourceRelationship)) {
      return false;
    }

    Object te = Query.retrieveTransformedElement(sourceElement, transfo, expectedTarget);
    if (te == null) {
      return false;
    }

    if (te instanceof EObject) {
      EObject targetElement = (EObject) te;
      EObject currentTgtContainerElt = targetElement.eContainer();
      EObject srcContainerElt = sourceElement.eContainer();
      Object tgtContainerElt = Query.retrieveTransformedElement(srcContainerElt, transfo, expectedTargetContainer);

      if (tgtContainerElt instanceof EObject) {

        if (currentTgtContainerElt == tgtContainerElt) {
          return true;
        }

        if (!(attachElementByRel((EObject) tgtContainerElt, targetElement, targetRelationship))) {
          return false;
        }

      } else {
        boolean isAttached = false;

        for (EObject tgtContainerE : (List<EObject>) tgtContainerElt) {
          if (currentTgtContainerElt == tgtContainerE) {
            return true;
          }

          if (attachElementByRel(tgtContainerE, targetElement, targetRelationship)) {
            isAttached = true;
            if (targetRelationship.isContainment()) {
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
        EObject srcContainerElt = sourceElement.eContainer();
        EObject tgtContainerElt = Query.retrieveFirstTransformedElement(srcContainerElt, transfo, expectedTargetContainer);

        if (currentTgtContainerElt == tgtContainerElt) {
          return true;
        }

        if (!(attachElementByRel(tgtContainerElt, targetElement, targetRelationship))) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * Attach transitioned elements into the first container of transitioned container of sourceElement which can contains the transitioned element
   * @param sourceElement
   * @param transfo
   * @param sourceRelationship
   * @param targetRelationship
   * @param expectedTarget
   * @param expectedTargetContainer
   * @return
   */
  public static boolean attachTransformedContainedElementsInBestContainer(EObject sourceElement, ITransfo transfo, EReference sourceRelationship, EReference targetRelationship, EClass expectedTarget, EClass expectedTargetContainer) {

    // 1- The containment feature is not the one expected
    if (!sourceElement.eContainmentFeature().equals(sourceRelationship)) {
      return false;
    }

    EObject targetElement = Query.retrieveFirstTransformedElement(sourceElement, transfo, expectedTarget);
    if (targetElement == null) {
      return false;
    }

    EObject currentTgtContainerElt = targetElement.eContainer();
    EObject srcContainerElt = sourceElement.eContainer();
    EObject tgtContainerElt = Query.retrieveFirstTransformedElement(srcContainerElt, transfo, expectedTargetContainer);

    if ((tgtContainerElt != null) && (currentTgtContainerElt == tgtContainerElt)) {
      return true;
    }

    while (srcContainerElt != null) {
      if ((tgtContainerElt != null) && EcoreUtil2.isEqualOrSuperClass(expectedTargetContainer, tgtContainerElt.eClass())) {
        if (attachElementByRel(tgtContainerElt, targetElement, targetRelationship)) {
          return true;
        }
      }
      srcContainerElt = srcContainerElt.eContainer();
      tgtContainerElt = Query.retrieveFirstTransformedElement(srcContainerElt, transfo, expectedTargetContainer);
    }

    return false;
  }

  /**
   * @param element
   * @param transfo
   * @param scenarioOwnedAbstractEnds
   */
  @Deprecated
  public static boolean attachTransformedContainedElementsPreserveOrder(EObject element, ITransfo transfo, EReference relationship) {
    return attachTransformedContainedElementsPreserveOrder(element, transfo, relationship, relationship);
  }

  /**
   * Attach the containment relationship by preserving orders of transformed elements relatively to their position in the source relationship
   * @param element
   * @param transfo
   * @param targetRelationShip .getName ()
   * @param scenarioOwnedAbstractEnds
   */
  @Deprecated
  @SuppressWarnings("unchecked")
  public static boolean attachTransformedContainedElementsPreserveOrder(EObject sourceElement, ITransfo transfo, EReference sourceRelationship, EReference targetRelationship) {
    // 1- The containment feature is not the one expected
    if (!sourceElement.eContainmentFeature().equals(sourceRelationship)) {
      return false;
    }

    EObject targetElement = (EObject) Query.retrieveTransformedElement(sourceElement, transfo);

    if (targetElement == null) {
      return false;
    }

    EObject currentTargetContainerElement = targetElement.eContainer();
    EObject sourceContainerElement = sourceElement.eContainer();
    EObject targetContainerElement = (EObject) Query.retrieveTransformedElement(sourceContainerElement, transfo);

    if (currentTargetContainerElement == targetContainerElement) {
      return true;
    }

    if (!sourceRelationship.isMany()) {
      return attachElementByRel(targetContainerElement, targetElement, targetRelationship);
    }
    EList<EObject> srcList = (EList<EObject>) sourceContainerElement.eGet(sourceRelationship);
    EList<EObject> tgtList = (EList<EObject>) targetContainerElement.eGet(targetRelationship);

    int indiceOfElementToAddInSource = srcList.indexOf(sourceElement);

    // simple case : tgtList is empty, same than non preserving order
    if (tgtList.isEmpty()) {
      return attachElementByRel(targetContainerElement, targetElement, targetRelationship);
    }

    // to simplify algorithm, i'm creating a map of inverse transformation
    Map<EObject, EObject> inverseTransfo = new HashMap<>();
    for (EObject srcObj : srcList) {
      EObject tgtObj = (EObject) Query.retrieveTransformedElement(srcObj, transfo);
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
   * @param sourceElement The source element
   * @param relationship The relationship name
   * @param transfo The transformation
   */
  public static void attachTransformedRelatedElements(EObject sourceElement, EReference relationship, ITransfo transfo) {
    attachTransformedRelatedElementsByMapping(sourceElement, relationship, relationship, transfo);
  }

  /**
   * Attaches the transformed element through a list of relationships
   * @param sourceElement The source element
   * @param relationships The relationship names
   * @param transfo The transformation
   */
  public static void attachTransformedRelatedElements(EObject sourceElement, EReference[] relationships, ITransfo transfo) {
    for (EReference relationship : relationships) {
      attachTransformedRelatedElements(sourceElement, relationship, transfo);
    }
  }

  /**
   * Attaches the transformed elements through the specified relationship
   * @param sourceElement The source element
   * @param relationship The relationship name
   * @param transfo The transformation
   */
  public static void attachTransformedRelatedElements(EObject sourceElement, ITransfo transfo, EReference relationship) {
    attachTransformedRelatedElementsByMapping(sourceElement, relationship, relationship, transfo);
  }

  /**
   * Attaches transformed related elements by mapping of relationship names
   * @param sourceElement The source element
   * @param relationshipInSource The source relationship name
   * @param relationshipInTarget The target relationship name (mapped)
   * @param transfo The transformation
   */
  @SuppressWarnings("unchecked")
  public static void attachTransformedRelatedElementsByMapping(EObject sourceElement, EReference relationshipInSource, EReference relationshipInTarget, ITransfo transfo) {

    List<EObject> sourceRelatedElements = Query.retrieveRelatedElements(sourceElement, relationshipInSource);

    // /////////////////////////////

    List<EObject> targetRelatedElements = new ArrayList<EObject>();
    for (EObject sourceRelatedElement : sourceRelatedElements) {
      if (sourceRelatedElement != null) {
        Object tre = Query.retrieveTransformedElement(sourceRelatedElement, transfo);
        if (tre != null) {
          if (tre instanceof EObject) {
            EObject targetRelatedElement = (EObject) tre;
            targetRelatedElements.add(targetRelatedElement);
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
    List<? extends EObject> targetElements = Query.retrieveTransformedElements(sourceElement, transfo);
    for (EObject newlyCreatedElement : targetRelatedElements) {
      for (EObject targetElement : targetElements) {
        if (isApplicable(targetElement.eClass(), relationshipInTarget)) {
          attachElementByRel(targetElement, newlyCreatedElement, relationshipInTarget);
        }
      }
    }
  }
  
  @SuppressWarnings("unchecked")
  public static void invertedAttachTransformedRelatedElements(EObject sourceElement, EReference sourceReference, EReference targetReference, ITransfo transfo) {

    List<EObject> sourceRelatedElements = Query.retrieveRelatedElements(sourceElement, sourceReference);

    // /////////////////////////////

    List<EObject> targetRelatedElements = new ArrayList<EObject>();
    for (EObject sourceRelatedElement : sourceRelatedElements) {
      if (sourceRelatedElement != null) {
        Object tre = Query.retrieveTransformedElement(sourceRelatedElement, transfo);
        if (tre != null) {
          if (tre instanceof EObject) {
            EObject targetRelatedElement = (EObject) tre;
            targetRelatedElements.add(targetRelatedElement);
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
    List<? extends EObject> targetElements = Query.retrieveTransformedElements(sourceElement, transfo);
    for (EObject targetElement : targetElements) {
      for (EObject newlyCreatedElement : targetRelatedElements) {
        if (isApplicable(targetElement.eClass(), targetReference)) {
          attachElementByRel(newlyCreatedElement, targetElement, targetReference);
        }
      }
    }
  }

  public static void attachTransformedRelatedElementsByMapping(EObject sourceElement, ITransfo transfo, EReference relationshipInSource, EReference relationshipInTarget) {
    attachTransformedRelatedElementsByMapping(sourceElement, relationshipInSource, relationshipInTarget, transfo);
  }

  /**
   * Attaches the transformed elements through the specified relationship mapping
   * @param sourceElement The source element
   * @param relationshipMapping The relationship mapping
   * @param transfo The transformation
   */
  public static void attachTransformedRelatedElementsByMapping(EObject sourceElement, List<EReference[]> relationshipMapping, ITransfo transfo) {
    for (EReference[] mapping : relationshipMapping) {
      attachTransformedRelatedElementsByMapping(sourceElement, mapping[0], mapping[1], transfo);
    }
  }

  /**
   * Attaches unattached transformed contained elements by the specified relationship.
   * @param sourceElement The source element
   * @param transfo The transformation
   * @param relationship The relationship name
   * @return <code>true</code> if the attachment succeeded
   */
  public static void attachUnattachedIntoSameContainer(EObject source, EClass targetEClass, EReference relationship, ITransfo transfo) {
    EObject newContainer = source.eContainer();
    for (EObject targetElement : Query.retrieveUnattachedTransformedElements(source, transfo, targetEClass)) {
      attachElementByRel(newContainer, targetElement, relationship);
    }
  }

  /**
   * Attaches unattached transitioned elements from source and with the targetEClass type
   * @param source the source
   * @param targetEClass the eclass of transitioned item which will be moved
   * @param relationship the containing reference
   * @param transfo
   */
  public static void attachUnattachedIntoTransformedContainer(EObject source, EClass targetEClass, EReference relationship, ITransfo transfo) {
    // Attach in the first transitioned element which can contains transitioned elements
    for (EObject newContainer : Query.retrieveTransformedElements(source.eContainer(), transfo)) {
      if (isApplicable(newContainer.eClass(), relationship)) {
        for (EObject targetElement : Query.retrieveUnattachedTransformedElements(source, transfo, targetEClass)) {
          attachElementByRel(newContainer, targetElement, relationship);
        }
        break;
      }
    }
  }

  /**
   * Attaches unattached transitioned elements from source and with the targetEClass type
   * @param source the source
   * @param targetEClass the eclass of transitioned item which will be moved
   * @param relationship the containing reference
   * @param transfo
   */
  public static void attachUnattachedIntoTransformedContainer(EObject source, EClass targetEClass, EReference[] relationships, ITransfo transfo) {
    // Attach in the first transitioned element which can contains transitioned elements
    for (EObject newContainer : Query.retrieveTransformedElements(source.eContainer(), transfo)) {
      boolean isAttached = false;
      for (EReference relationship : relationships) {
        if (isApplicable(newContainer.eClass(), relationship)) {
          for (EObject targetElement : Query.retrieveUnattachedTransformedElements(source, transfo, targetEClass)) {
            attachElementByRel(newContainer, targetElement, relationship);
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
   * Attaches the targetElement into the transitioned container of source
   * @param source the source
   * @param targetEClass the eclass of transitioned item which will be moved
   * @param relationship the containing reference
   * @param transfo
   */
  public static void attachUnattachedIntoTransformedContainer(EObject source, EObject targetElement, EReference relationship, ITransfo transfo) {
    // Attach in the first transitioned element which can contains transitioned elements
    for (EObject newContainer : Query.retrieveTransformedElements(source.eContainer(), transfo)) {
      if (isApplicable(newContainer.eClass(), relationship)) {
        attachElementByRel(newContainer, targetElement, relationship);
        break;
      }
    }
  }

  /**
   * Attaches unattached transitioned elements from source and with the targetEClass type USE IT WHEN SOURCE CAN BE CONTAINED INTO SOME CONTAINER
   * @param source the source
   * @param targetEClass the eclass of transitioned item which will be moved
   * @param relationship the containing reference
   * @param transfo
   */
  public static void attachUnattachedIntoTransformedContainerLikeSource(EObject source, EClass targetEClass, ITransfo transfo) {
    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(source, targetEClass, (EReference) source.eContainingFeature(), transfo);
  }

  /**
   * Attaches unattached transitioned elements from source and with the targetEClass type
   * @param source the source
   * @param targetEClass the eclass of transitioned item which will be moved
   * @param relationship the containing reference
   * @param transfo
   */
  public static void attachUnattachedIntoTransformedGivenContainer(EObject source, EObject givenContainer, EClass targetEClass, EReference relationship, ITransfo transfo) {
    // Attach in the first transitioned element which can contains transitioned elements
    for (EObject newContainer : Query.retrieveTransformedElements(givenContainer, transfo)) {
      boolean isAttached = false;
      if (isApplicable(newContainer.eClass(), relationship)) {
        for (EObject targetElement : Query.retrieveUnattachedTransformedElements(source, transfo, targetEClass)) {
          attachElementByRel(newContainer, targetElement, relationship);
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
  private static AbstractTrace createAbstractTrace(EObject sourceType, EObject targetType, ITransfo transfo) throws TransfoException {
    AbstractTrace abstractTrace = null;
    EClass eGenericTraceFound = null;

    if (transfo instanceof Transfo) {
      eGenericTraceFound = ((Transfo) transfo).getSpecificLinkKindFromMap(sourceType, targetType);
      if (eGenericTraceFound == null) {
        eGenericTraceFound = ((Transfo) transfo).get_eDefaultTrace();
      }
    }

    if (eGenericTraceFound != null) {
      EFactory factory = eGenericTraceFound.getEPackage().getEFactoryInstance();
      // we only create the link if it doesn't exists.
      abstractTrace = traceExists(targetType, sourceType, eGenericTraceFound);
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
   * @param sourceObject The source element of the link
   * @param targetObject The target element of the link
   * @param transfo The transformation
   * @return The new transformation link
   * @throws TransfoException
   */
  public static AbstractTrace createTransfoLink(EObject sourceObject, EObject targetObject, ITransfo transfo) throws TransfoException {
    AbstractTrace abstractTrace = null;

    if ((sourceObject instanceof CapellaElement) && (targetObject instanceof CapellaElement)) {
      // 1- Get the source and target as Capella elements
      CapellaElement sourceElement = (CapellaElement) sourceObject;
      CapellaElement targetElement = (CapellaElement) targetObject;

      // 2- Create the transformation link
      abstractTrace = createAbstractTrace(sourceElement, targetElement, transfo);
      if (abstractTrace != null) {
        // 3- Update relationships and roles
        abstractTrace.setSourceElement(targetElement);
        abstractTrace.setTargetElement(sourceElement);
      }

    }
    return abstractTrace;
  }

  /**
   * Detaches the elements which are attached by the specified relationship from the source element.
   * <p/>
   * It uses the meta-model to proceed a model action.
   * @param element The element
   * @param relatedElement The related element
   * @param relationship The relationship name
   * @return <code>true</code> if the element is detached
   */
  @SuppressWarnings("unchecked")
  public static boolean detachElementByRel(EObject element, EObject relatedElement, EReference relationship) {

    if ((element == null) || (relatedElement == null)) {
      throw new NullPointerException(Messages.TigerRelationshipHelper_CannotAttachNull);
    }

    boolean done = false;
    if (!relationship.isMany()) {
      element.eUnset(relationship);
      done = true;
    } else {
      EList<EObject> list = (EList<EObject>) element.eGet(relationship);
      if (!list.contains(relatedElement)) {
        done = false;
      } else {
        list.remove(relatedElement);
        done = true;
      }
    }

    if (done && logger.isInfoEnabled()) {
      String text = NLS.bind("Element ''{0}'' has been removed from ''{1}'' ({2})",  //$NON-NLS-1$
          new Object[] { EObjectLabelProviderHelper.getText(relatedElement), 
                         EObjectLabelProviderHelper.getText(element), 
                         relationship.getName() });
      logger.info(new EmbeddedMessage(text, logger.getName(), new Object[] { relatedElement, element }));
    }
    return done;

  }

  /**
   * Return a list containing only the container of the element. Useful for retrieveRelatedElements methods.
   * @param element
   * @return
   */
  public static List<EObject> getJustContainer(EObject element) {
    List<EObject> relatedElements = new ArrayList<>();
    relatedElements.add(element.eContainer());
    return relatedElements;
  }

  /**
   * Returns whether the feature is available in the clazz
   */
  public static boolean isApplicable(EClass clazz, EStructuralFeature feature) {
    return EcoreUtil2.isEqualOrSuperClass(feature.getEContainingClass(), clazz);
  }

  /**
   * Retrieve the transitioned elements of the given object with the given clazz or the object if it isn't yet transitioned
   */
  @SuppressWarnings("unchecked")
  public static Collection<EObject> retrieveBestElements(EObject source, EObject object, EClass clazz, ITransfo transfo) {
    Collection<EObject> objects = (Collection<EObject>) Query.retrieveTransformedElements(object, transfo, clazz);
    Collection<EObject> result = new LinkedList<>();

    // Retrieves only transitioned elements which are in same or previous architectures of the source element
    BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(source);
    for (EObject obj : objects) {
      BlockArchitecture targetArchitecture = BlockArchitectureExt.getRootBlockArchitecture(obj);
      if ((sourceArchitecture == null)
          || (targetArchitecture == null)
          || (targetArchitecture.equals(sourceArchitecture) || BlockArchitectureExt.getPreviousBlockArchitectures(sourceArchitecture).contains(targetArchitecture))) {
        result.add(obj);
      }
    }

    if (result.isEmpty()) {
      result = Collections.singleton(object);
    }
    return result;
  }

  /**
   * Returns the given reference value as a list of elements
   */
  @SuppressWarnings("unchecked")
  public static Collection<EObject> retrieveReferenceAsList(EObject object, EReference reference) {
    Object sourceReference = object.eGet(reference);
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
   * @param sourceElement The source element
   * @param property The name of the property
   * @param transfo The transformation
   */
  public static void updateElementByAttribute(EObject sourceElement, EObject targetElement, EAttribute attribute, ITransfo transfo) {
    if (targetElement != null) {
      EClass clazzAttribute = (EClass) attribute.eContainer();
      EClass clazz = sourceElement.eClass();
      if (clazzAttribute.isSuperTypeOf(clazz)) {
        Object value = sourceElement.eGet(attribute);
        targetElement.eSet(attribute, value);
        if (logger.isDebugEnabled()){
          logger.debug("       => Update attribute " //$NON-NLS-1$
                          + attribute.getName() + " on element " + EObjectExt.getText(targetElement)); //$NON-NLS-1$
        }
      }
    }
  }

  /**
   * Updates an element by copying a element's property to a the transformed element's property
   * @param sourceElement The source element
   * @param property The name of the property
   * @param transfo The transformation
   */
  public static void updateElementByAttributeIfEmpty(EObject sourceElement, EObject targetElement, EAttribute attribute, ITransfo transfo) {
    if (targetElement != null) {
      EClass clazzAttribute = (EClass) attribute.eContainer();
      EClass clazzSource = sourceElement.eClass();
      EClass clazzTarget = targetElement.eClass();
      if (clazzAttribute.isSuperTypeOf(clazzSource) && clazzAttribute.isSuperTypeOf(clazzTarget)) {
        Object valueSource = sourceElement.eGet(attribute);
        Object valueTarget = targetElement.eGet(attribute);

        if ((valueSource != null) && (valueSource != ICommonConstants.EMPTY_STRING)
            && ((valueTarget == null) || ((valueTarget instanceof String) && (((String) valueTarget).length() == 0))) && !valueSource.equals(valueTarget)) {
          targetElement.eSet(attribute, valueSource);
          if (!((valueTarget == null) || (((String) valueTarget).length() == 0))) {
            if (logger.isInfoEnabled()){
              String text = NLS.bind(Messages.TigerRelationshipHelper_UpdateAttribute, new Object[] { attribute.getName(), 
                                                                                                      EObjectLabelProviderHelper.getText(targetElement), 
                                                                                                      Objects.toString(valueTarget), 
                                                                                                      Objects.toString(valueSource) });
              
              logger.info(new EmbeddedMessage(text, logger.getName(), targetElement));
            }
          }

        }
      }
    }
  }

  /**
   * Updates an element's properties to the transformed element's properties
   * @param sourceElement The source element
   * @param properties The list of property names
   * @param transfo The transformation
   */
  public static void updateElementByProperty(EObject sourceElement, List<String> properties, ITransfo transfo) {
    for (String property : properties) {
      updateElementByProperty(sourceElement, property, transfo);
    }

  }

  /**
   * Updates an element by copying a element's property to a the transformed element's property
   * @param sourceElement The source element
   * @param property The name of the property
   * @param transfo The transformation
   */
  public static void updateElementByProperty(EObject sourceElement, String property, ITransfo transfo) {
    List<? extends EObject> targetElements = Query.retrieveTransformedElements(sourceElement, transfo);
    if (!targetElements.isEmpty()) {
      EStructuralFeature featureSource = sourceElement.eClass().getEStructuralFeature(property);
      if (featureSource instanceof EAttribute) {
        EAttribute attribute = (EAttribute) featureSource;
        Object valueSource = sourceElement.eGet(attribute);
        for (EObject targetElement : targetElements) {
          EStructuralFeature featureTarget = targetElement.eClass().getEStructuralFeature(property);
          if (featureTarget != null) {
            Object valueTarget = targetElement.eGet(attribute);
            if (((valueSource == null) && (valueTarget != null)) || ((valueSource != null) && !valueSource.equals(valueTarget))) {
              if (valueTarget != null && logger.isInfoEnabled()) {
                  String text = NLS.bind(Messages.TigerRelationshipHelper_UpdateAttribute, 
                      new Object[] { attribute.getName(), EObjectLabelProviderHelper.getText(targetElement), Objects.toString(valueTarget), Objects.toString(valueSource) }); 
                  logger.info(new EmbeddedMessage(text, logger.getName(), targetElement)); 
              }
              targetElement.eSet(attribute, valueSource);
            }
          }
        }
      }
    }
  }

  /**
   * @param element
   * @param reference
   * @param transfo
   */
  public static void attachToBestAndValidElements(EObject element, EReference reference, Collection<EObject> objects, ITransfo transfo) {
  
    if ((element == null) || !isApplicable(element.eClass(), reference)) {
      return;
    }
  
    for (EObject targetElement : Query.retrieveTransformedElements(element, transfo)) {
      if (isApplicable(targetElement.eClass(), reference)) {
  
        Object resultTarget = targetElement.eGet(reference);
  
        for (EObject sourceElement : retrieveReferenceAsList(element, reference)) {
          for (EObject bestElement : retrieveBestElements(targetElement, sourceElement, (EClass) reference.getEType(), transfo)) {
            if (!objects.contains(bestElement)) {
              continue;
            }
            if (reference.isMany() || (resultTarget == null)
                || (resultTarget.equals(sourceElement) || resultTarget.equals(bestElement))) {
              if (bestElement != sourceElement) {
                detachElementByRel(targetElement, sourceElement, reference);
              }
              attachElementByRel(targetElement, bestElement, reference);
            }
          }
        }
      }
    }
  
  }
}
