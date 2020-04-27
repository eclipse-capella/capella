/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.tiger.IResolver;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.impl.Transfo;

/**
 * Utility class to provide queries for transformation based on
 * <ul>
 * <li>EMF</li>
 * <li>Capella</li>
 * </ul>
 */
public class Query {

  /**
   * Default constructor
   */
  private Query() {
    // Nothing to be done here...
  }

  /**
   * Get incoming traces for specified element (whatever the source may be).
   * @param element
   * @param transfo
   * @return
   */
  private static List<AbstractTrace> getIncomingTraces(CapellaElement element, ITransfo transfo) {
    return element.getIncomingTraces();
  }

  /**
   * Get incoming traces for specified element (whatever the source may be).
   * @param element
   * @param transfo
   * @return
   */
  private static List<AbstractTrace> getOutgoingTraces(CapellaElement element, ITransfo transfo) {
    return element.getOutgoingTraces();
  }

  /**
   * Retrieve a resolver
   * @param transfo
   */
  public static IResolver getResolver(ITransfo transfo) {
    List<IResolver> resolvers = transfo.getResolvers();
    if (resolvers.size() > 0) {
      return resolvers.get(0);
    }
    return null;
  }

  /**
   * Returns whether the object is transformed by the transformation
   * @param object The object to be tested
   * @param transfo The transformation
   * @return <code>true</code> if the object is transformed
   */
  public static boolean isElementTransformed(EObject object, ITransfo transfo) {
    return retrieveTransformedElement(object, transfo) != null;
  }

  /**
   * Returns whether the object is transformed by the transformation
   * @param object The object to be tested
   * @param transfo The transformation
   * @return <code>true</code> if the object is transformed
   */
  public static boolean isElementTransformed(EObject object, ITransfo transfo, EClass eTargetType) {
    return retrieveTransformedElement(object, transfo, eTargetType) != null;
  }

  /**
   * Specifies whether the transformation link has been created by the transformation
   * @param link The link to be tested
   * @param transfo The transformation
   * @return <code>true</code> if it is the case
   */
  public static boolean isLinkOfTransfo(AbstractTrace link, ITransfo transfo) {
    if (transfo == null) {
      return false;
    }

    if (isValidUID(link, transfo)) {
      if ((transfo instanceof Transfo) && ((Transfo) transfo).isValidLinkKind(link)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns whether the object is one to one transformed
   * @param object The object to be tested
   * @param transfo The transformation
   * @return <code>true</code> if the object is transformed
   */
  public static boolean isOneToManyTransformed(EObject object, ITransfo transfo) {
    return retrieveTransformedElements(object, transfo).size() > 1;
  }

  /**
   * Returns whether the object is one to one transformed
   * @param object The object to be tested
   * @param transfo The transformation
   * @return <code>true</code> if the object is transformed
   */
  public static boolean isOneToOneTransformed(EObject object, ITransfo transfo) {
    return isOneToOneTransformed(object, transfo, null);
  }

  /**
   * Returns whether the object is one to one transformed
   * @param object The object to be tested
   * @param transfo The transformation
   * @return <code>true</code> if the object is transformed
   */
  public static boolean isOneToOneTransformed(EObject object, ITransfo transfo, EClass target) {
    Object result = retrieveTransformedElement(object, transfo, target);
    return (result != null) && !((result instanceof List<?>) && (((List<?>) result).size() > 1));
  }

  public static boolean isValidUID(AbstractTrace link_p, ITransfo transfo_p) {
    return !(link_p instanceof GenericTrace) || (link_p instanceof TransfoLink);
  }

  /**
   * Retrieves the list of relationship names linking this object to others. Retains only the containment relationships
   * @return The list of relationship name
   */
  public static List<String> retrieveContainmentRelationships(EObject element) {
    EClass clazz = element.eClass();
    EList<?> features = clazz.getEAllContainments();
    List<String> relationshipNameList = new ArrayList<String>();

    for (Object featureObject : features) {
      EReference feature = (EReference) featureObject;
      if (feature.isContainer()) {
        relationshipNameList.add(feature.getName());
      }
    }

    return relationshipNameList;
  }

  /**
   * Retrieves the transformed elements by a specified transformation.
   * @param object The element to be queried
   * @param transfo The transformation
   * @param expectedTarget
   * @return The transformed element
   */
  public static EObject retrieveFirstTransformedElement(EObject object, ITransfo transfo) {
    return retrieveFirstTransformedElement(object, transfo, null);
  }

  /**
   * Retrieves the transformed elements by a specified transformation.
   * @param object The element to be queried
   * @param transfo The transformation
   * @param expectedTarget
   * @return The transformed element
   */
  public static EObject retrieveFirstTransformedElement(EObject object, ITransfo transfo, EClass expectedTarget) {
    Object res = retrieveTransformedElement(object, transfo, expectedTarget);
    if (res instanceof List<?>) {
      List<?> p = (List<?>) res;
      return (EObject) p.get(0);
    }
    return (EObject) res;
  }

  /**
   * Retrieves the model root of an element
   * @param object The element to be queried
   * @return The model root
   */
  public static ModelRoot retrieveModelRoot(EObject object) {
    EObject currentElement = object;
    while (currentElement != null) {
      if (currentElement instanceof ModelRoot) {
        return (ModelRoot) currentElement;
      }
      currentElement = currentElement.eContainer();
    }

    return null;
  }

  /**
   * Retrieve elements linked by a relationship by passing its name
   * @param relationshipString The relationship name
   * @return
   */
  @SuppressWarnings("unchecked")
  public static List<EObject> retrieveRelatedElements(EObject element, EReference relationshipString) {

    List<EObject> relatedElements = new ArrayList<EObject>();

    // Skip derived features (computed) <=> UML slashed relationships
    if (!relationshipString.isDerived()) {
      try {
        Object obj = element.eGet(relationshipString);
        if (obj instanceof EObject) {
          EObject eObject = (EObject) obj;
          relatedElements.add(eObject);
        } else {
          if (obj instanceof EList) {
            EList eObject = (EList) obj;
            relatedElements.addAll(eObject);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return relatedElements;
  }

  /**
   * Retrieves the list of related elements linked with the specified relationships in parameter
   * @param element The element
   * @param relationships The list of relationship (roles)
   * @return The element to be queried
   */
  public static List<EObject> retrieveRelatedElements(EObject element, EReference[] relationships) {
    List<EObject> relatedElements = new ArrayList<EObject>();

    for (EReference relationship : relationships) {
      relatedElements.addAll(retrieveRelatedElements(element, relationship));
    }

    return relatedElements;
  }

  /**
   * Retrieves the list of relationship names linking this object to others
   * @return The list of relationship name
   */
  public static List<String> retrieveRelationships(EObject element) {
    EClass clazz = element.eClass();
    EList<?> features = clazz.getEAllStructuralFeatures();
    List<String> relationshipNameList = new ArrayList<String>();

    for (Object featureObject : features) {
      EStructuralFeature feature = (EStructuralFeature) featureObject;
      relationshipNameList.add(feature.getName());
    }

    return relationshipNameList;
  }

  /**
   * Retrieves the shared package of an element (a Capella Project) at the upper containment level.
   * @param object The element to be tested
   * @return The list of shared packages
   */
  public static List<SharedPkg> retrieveSharedPkgs(EObject object) {
    List<EObject> objects = object.eContents();
    List<SharedPkg> sharedPkgs = new ArrayList<SharedPkg>();

    for (EObject obj : objects) {
      if (obj instanceof SharedPkg) {
        SharedPkg sharedPkg = (SharedPkg) obj;
        sharedPkgs.add(sharedPkg);
      }
    }
    return sharedPkgs;
  }

  public static List<SharedPkg> retrieveSharedPkgsOfElement(EObject object) {
    SystemEngineering systemEngineering = retrieveSystemEngineering(object);
    return retrieveSharedPkgs(systemEngineering.eContainer());
  }

  /**
   * Retrieves the transformed elements by a specified transformation.
   * @param object The element to be queried
   * @param transfo The transformation
   * @param expectedTarget
   * @return The transformed element
   */
  public static List<? extends EObject> retrieveSourceElements(EObject targetElement, ITransfo transfo, EClass expectedTarget) {
    List<EObject> results = new ArrayList<EObject>();

    if (targetElement instanceof CapellaElement) {
      CapellaElement element = (CapellaElement) targetElement;

      try {
        // load rules for the given element if wasn't loaded to load all related transfo link (FunctionalRealization, PortRealization etc)
        transfo.findCachedMatchingRule(targetElement);
      } catch (TransfoException exception) {
        // Nothing to do
      }

      List<AbstractTrace> traceList = getOutgoingTraces(element, transfo);
      for (AbstractTrace trace : traceList) {
        if (isLinkOfTransfo(trace, transfo)) {
          TraceableElement srcElement = trace.getTargetElement();
          if ((expectedTarget == null) || EcoreUtil2.isEqualOrSuperClass(expectedTarget, srcElement.eClass())) {
            results.add(srcElement);
          }
        }
      }
    }
    return results;
  }

  /**
   * Retrieves the system engineering from a model element
   * @param object The model element
   * @return The system engineering
   */
  protected static SystemEngineering retrieveSystemEngineering(EObject object) {
    EObject currentElement = object.eContainer();
    while (currentElement != null) {
      if (currentElement instanceof SystemEngineering) {
        return (SystemEngineering) currentElement;
      }
      currentElement = currentElement.eContainer();
    }

    return null;
  }

  /**
   * Retrieves the transformed elements by a specified transformation. USE IT CAREFULLY! (we can be sure that the given object is transitioned one2one only
   * when we have created it into the transition, otherwise, the user can have made some bad changes with transfoLinks)
   * @param object The element to be queried
   * @param transfo The transformation
   * @return The transformed element
   */
  public static Object retrieveTransformedElement(EObject object, ITransfo transfo) {
    return retrieveTransformedElement(object, transfo, null);
  }

  /**
   * Retrieves the transformed elements by a specified transformation. USE IT CAREFULLY! (we can be sure that the given object is transitioned one2one only
   * when we have created it into the transition, otherwise, the user can have made some bad changes with transfoLinks)
   * @param object The element to be queried
   * @param transfo The transformation
   * @param expectedTarget
   * @return The transformed element
   */
  public static Object retrieveTransformedElement(EObject object, ITransfo transfo, EClass expectedTarget) {
    List<EObject> result = new ArrayList<EObject>();
    if (object instanceof CapellaElement) {
      CapellaElement element = (CapellaElement) object;

      try {
        // load rules for the given element if wasn't loaded to load all related transfo link (FunctionalRealization, PortRealization etc)
        transfo.findCachedMatchingRule(object);
      } catch (TransfoException exception) {
        // Nothing to do
      }

      List<AbstractTrace> traceList = getIncomingTraces(element, transfo);
      for (AbstractTrace trace : traceList) {
        if (isLinkOfTransfo(trace, transfo)) {
          TraceableElement srcElement = trace.getSourceElement();
          if ((expectedTarget == null) || EcoreUtil2.isEqualOrSuperClass(expectedTarget, srcElement.eClass())) {
            result.add(srcElement);
          }
        }
      }
    }
    if (result.size() == 0) {
      return null;
    }
    if (result.size() == 1) {
      return result.get(0);
    }
    return result;
  }

  /**
   * Retrieves the transformed elements by a specified transformation.
   * @param object The element to be queried
   * @param transfo The transformation
   * @param expectedTarget
   * @return The transformed element
   */
  public static List<? extends EObject> retrieveTransformedElements(EObject object, ITransfo transfo) {
    return retrieveTransformedElements(object, transfo, null);
  }

  /**
   * Retrieves the transformed elements by a specified transformation.
   * @param object The element to be queried
   * @param transfo The transformation
   * @param expectedTarget
   * @return The transformed element
   */
  public static List<? extends EObject> retrieveTransformedElements(EObject object, ITransfo transfo, EClass expectedTarget) {
    List<EObject> results = new ArrayList<EObject>();

    if ((object != null) && (object instanceof CapellaElement)) {
      CapellaElement element = (CapellaElement) object;

      try {
        // load rules for the given element if wasn't loaded to load all related transfo link (FunctionalRealization, PortRealization etc)
        transfo.findCachedMatchingRule(object);
      } catch (TransfoException exception) {
        // Nothing to do
      }

      List<AbstractTrace> traceList = getIncomingTraces(element, transfo);
      for (AbstractTrace trace : traceList) {
        if (isLinkOfTransfo(trace, transfo)) {
          TraceableElement srcElement = trace.getSourceElement();
          if ((expectedTarget == null) || EcoreUtil2.isEqualOrSuperClass(expectedTarget, srcElement.eClass())) {
            results.add(srcElement);
          }
        }
      }
    }
    return results;
  }

  /**
   * Retrieves the transformed elements by a specified transformation.
   * @param object The element to be queried
   * @param transfo The transformation
   * @param expectedTarget
   * @return The transformed element
   */
  public static List<? extends EObject> retrieveUnattachedTransformedElements(EObject object, ITransfo transfo, EClass expectedTarget) {
    List<EObject> result = new ArrayList<EObject>();
    if (object instanceof CapellaElement) {
      CapellaElement element = (CapellaElement) object;

      try {
        // load rules for the given element if wasn't loaded to load all related transfo link (FunctionalRealization, PortRealization etc)
        transfo.findCachedMatchingRule(object);
      } catch (TransfoException exception) {
        // Nothing to do
      }

      List<AbstractTrace> traceList = getIncomingTraces(element, transfo);
      for (AbstractTrace trace : traceList) {
        if (isLinkOfTransfo(trace, transfo)) {
          TraceableElement srcElement = trace.getSourceElement();
          if ((expectedTarget == null) || EcoreUtil2.isEqualOrSuperClass(expectedTarget, srcElement.eClass())) {
            if (srcElement.eContainer() == null) {
              result.add(srcElement);
            }
          }
        }
      }
    }
    return result;
  }
}
