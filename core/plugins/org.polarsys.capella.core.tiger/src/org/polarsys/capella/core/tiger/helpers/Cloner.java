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
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacommon.CapellacommonFactory;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

/**
 */
public class Cloner {

  protected static Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.REFINEMENT);
  
  /**
   * 
   */
  private Cloner() {
   
  }
  
  /**
   * 
   * @param sourceElement_p
   * @param transfo_p
   * @param transfoLinkList_p
   * @return
 * @throws TransfoException 
   * @throws TransfoException 
   */
  public static Object clone(EObject sourceElement_p, 
      ITransfo transfo_p, 
      List<AbstractTrace> transfoLinkList_p) throws TransfoException {
    List<EObject> sourceElementList = findElements(sourceElement_p, transfo_p);
    transform(sourceElementList, transfo_p, transfoLinkList_p);
    update(sourceElementList, transfo_p);
    attach(sourceElementList, transfo_p);

    Object targetElement 
    = Query.retrieveTransformedElement(sourceElement_p, transfo_p);    

    return targetElement;
  }

  /**
   * 
   * @param sourceElement_p
   * @param transfo_p
   * @return
   */
  protected static List<EObject> findElements(EObject sourceElement_p, ITransfo transfo_p) {
    List<EObject> elementsToBeCloned = new ArrayList<EObject>();
    List<EObject> agenda = new ArrayList<EObject>();
    agenda.add(sourceElement_p);
    while(!agenda.isEmpty()) {
      EObject currentElement = agenda.get(0);

      if(!elementsToBeCloned.contains(currentElement)) {
        elementsToBeCloned.add(currentElement);
      }

      List<EObject> relatedElements = retrieveRelatedElementsForClone(currentElement);

      agenda.remove(currentElement);
      for (EObject relatedElement : relatedElements) {
        if(isContainedBy(relatedElement, sourceElement_p)) {
          agenda.add(relatedElement);            
        }
      }

      if(!Collections.disjoint(agenda, elementsToBeCloned)) {
        // An element is about to be re-injected => otherwise it is a loop
        agenda.removeAll(elementsToBeCloned);
      }        
    }
    return elementsToBeCloned;
  }

  protected static void transform(List<EObject> sourceElementList_p, ITransfo transfo_p, List<AbstractTrace> transfoLinkList_p) throws TransfoException {

    for (EObject sourceElement : sourceElementList_p) {
      if(!Query.isElementTransformed(sourceElement, transfo_p))
      {
        EClass clazz = sourceElement.eClass();
        EObject targetElement = CapellacommonFactory.eINSTANCE.create(clazz);
        AbstractTrace transfoLink = TigerRelationshipHelper.createTransfoLink(sourceElement, targetElement, transfo_p);
        if (transfoLink != null) transfoLinkList_p.add(transfoLink);
      }
    }
  }

  protected static void update(List<EObject> sourceElementList_p, ITransfo transfo_p) {
    for (EObject sourceElement : sourceElementList_p) {
      EClass clazz = sourceElement.eClass();
      Object targetElement 
      = Query.retrieveTransformedElement(sourceElement, transfo_p);
      if (targetElement instanceof EObject) {
        EObject targetElement2 = (EObject) targetElement;
        List<EAttribute> attributes = clazz.getEAllAttributes();
        for (EAttribute attribute : attributes) {
          if(isClonableFeature(attribute) && !attribute.isID()) {
            Object value = sourceElement.eGet(attribute);
            targetElement2.eSet(attribute, value);          
          }
        }
      }


    }
  }

  protected static void attach(List<EObject> sourceElementList_p, ITransfo transfo_p) {
    for (EObject sourceElement : sourceElementList_p) {
      EClass clazz = sourceElement.eClass();
      EList<EStructuralFeature> features = clazz.getEAllStructuralFeatures();
      for (EStructuralFeature feature : features) {
        if(isClonableFeature(feature))
        {
          TigerRelationshipHelper.attachTransformedRelatedElements(sourceElement, transfo_p, (EReference)feature);
        }
      }
    }
  }

  /**
   * Retrieve elements linked by a relationship by passing its name
   * @param relationshipString_p The relationship name
   * @return
   */
  @SuppressWarnings({"nls", "unchecked"})
  protected static List<EObject> retrieveRelatedElementsForClone(EObject element) {

    EClass clazz = element.eClass();
    List<EObject> relatedElements = new ArrayList<EObject>();

    EList<EStructuralFeature> features = clazz.getEAllStructuralFeatures();

    for (EStructuralFeature feature : features) {
      try {
        if(isClonableFeature(feature)) {
          Object obj = element.eGet(feature);
          if (obj instanceof EObject) {
            EObject eObject = (EObject) obj;
            relatedElements.add(eObject);
          }
          else
          {
            if (obj instanceof EList) {
              EList eObject = (EList) obj;
              relatedElements.addAll(eObject);
            }
          }
        }
      } catch (Exception e) {
        _logger.error("   + Cancel feature " 
                           + feature.getName() 
                           + " on " + element.eClass().getName()
                           + " while cloning.", e);
      }
    }

    return relatedElements;
  }

  protected static boolean isClonableFeature(EStructuralFeature feature_p) {
    return !feature_p.isUnsettable() 
    && !feature_p.isDerived()
    && !feature_p.isVolatile()
    && !feature_p.isTransient();
  }

  protected static boolean isContainedBy(EObject object_p, EObject container_p) {
    if(object_p == container_p)
      return true;

    boolean found = false;
    EObject currentElement = object_p.eContainer();
    while (currentElement!=null && !found ) {
      found = (currentElement == container_p);
      currentElement = currentElement.eContainer();
    }

    return found;
  }


}
