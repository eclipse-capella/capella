/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
   * @param sourceElement
   * @param transfo
   * @param transfoLinkList
   * @return
   * @throws TransfoException 
   */
  public static Object clone(EObject sourceElement, 
      ITransfo transfo, 
      List<AbstractTrace> transfoLinkList) throws TransfoException {
    List<EObject> sourceElementList = findElements(sourceElement, transfo);
    transform(sourceElementList, transfo, transfoLinkList);
    update(sourceElementList, transfo);
    attach(sourceElementList, transfo);

    Object targetElement 
    = Query.retrieveTransformedElement(sourceElement, transfo);    

    return targetElement;
  }

  /**
   * 
   * @param sourceElement
   * @param transfo
   * @return
   */
  protected static List<EObject> findElements(EObject sourceElement, ITransfo transfo) {
    List<EObject> elementsToBeCloned = new ArrayList<EObject>();
    List<EObject> agenda = new ArrayList<EObject>();
    agenda.add(sourceElement);
    while(!agenda.isEmpty()) {
      EObject currentElement = agenda.get(0);

      if(!elementsToBeCloned.contains(currentElement)) {
        elementsToBeCloned.add(currentElement);
      }

      List<EObject> relatedElements = retrieveRelatedElementsForClone(currentElement);

      agenda.remove(currentElement);
      for (EObject relatedElement : relatedElements) {
        if(isContainedBy(relatedElement, sourceElement)) {
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

  protected static void transform(List<EObject> sourceElementList, ITransfo transfo, List<AbstractTrace> transfoLinkList) throws TransfoException {

    for (EObject sourceElement : sourceElementList) {
      if(!Query.isElementTransformed(sourceElement, transfo))
      {
        EClass clazz = sourceElement.eClass();
        EObject targetElement = CapellacommonFactory.eINSTANCE.create(clazz);
        AbstractTrace transfoLink = TigerRelationshipHelper.createTransfoLink(sourceElement, targetElement, transfo);
        if (transfoLink != null) transfoLinkList.add(transfoLink);
      }
    }
  }

  protected static void update(List<EObject> sourceElementList, ITransfo transfo) {
    for (EObject sourceElement : sourceElementList) {
      EClass clazz = sourceElement.eClass();
      Object targetElement 
      = Query.retrieveTransformedElement(sourceElement, transfo);
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

  protected static void attach(List<EObject> sourceElementList, ITransfo transfo) {
    for (EObject sourceElement : sourceElementList) {
      EClass clazz = sourceElement.eClass();
      EList<EStructuralFeature> features = clazz.getEAllStructuralFeatures();
      for (EStructuralFeature feature : features) {
        if(isClonableFeature(feature))
        {
          TigerRelationshipHelper.attachTransformedRelatedElements(sourceElement, transfo, (EReference)feature);
        }
      }
    }
  }

  /**
   * Retrieve elements linked by a relationship by passing its name
   * @param relationshipString The relationship name
   * @return
   */
  @SuppressWarnings("unchecked")
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
          else {
            if (obj instanceof EList) {
              relatedElements.addAll((EList) obj);
            }
          }
        }
      } catch (Exception e) {
        _logger.error("   + Cancel feature " //$NON-NLS-1$
                           + feature.getName() 
                           + " on " + element.eClass().getName() //$NON-NLS-1$
                           + " while cloning.", e); //$NON-NLS-1$
      }
    }

    return relatedElements;
  }

  protected static boolean isClonableFeature(EStructuralFeature feature) {
    return !feature.isUnsettable() 
    && !feature.isDerived()
    && !feature.isVolatile()
    && !feature.isTransient();
  }

  protected static boolean isContainedBy(EObject object, EObject container) {
    if(object == container)
      return true;

    boolean found = false;
    EObject currentElement = object.eContainer();
    while (currentElement!=null && !found ) {
      found = (currentElement == container);
      currentElement = currentElement.eContainer();
    }

    return found;
  }
}
