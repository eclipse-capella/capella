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
package org.polarsys.capella.core.refinement.merge.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.helpers.interaction.services.ScenarioExt;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Utility class in order to manage links
 *
 */
public class LinkUtils {

  /**
   * Check whether any incoming link of a given type is pointing to a {@link TraceableElement}  
   * @param tre the Element to check 
   * @param linkType
   * @return <code>true</code> whether any Links was found, <code>false</code> otherwise.
   */
  static public boolean hasOutgoingLinks(TraceableElement tre, LinkEnum linkType) {
    return !getOutgoingLinkTargets(tre, linkType).isEmpty();
  }

  /**
   * Check whether any incoming link of a given type is pointing to a {@link TraceableElement}  
   * @param tre the Element to check 
   * @param linkType
   * @return <code>true</code> whether any Links was found, <code>false</code> otherwise.
   */
  static public boolean hasIncomingLinks(TraceableElement tre, LinkEnum linkType) {
    return !getIncomingLinkTargets(tre, linkType).isEmpty();
  }

  /**
   * Check whether any incoming link of a given type is pointing to a {@link TraceableElement}
   * from a given target {@link Scenario}
   * @param tre the Element to check 
   * @param linkType
   * @param target the originator {@link Scenario}
   * @return <code>true</code> whether any Links was found, <code>false</code> otherwise.
   */
  static public boolean hasIncomingLinksFrom(TraceableElement tre, LinkEnum linkType, Scenario target) {
    return !getIncomingLinkTargetsFrom(tre, linkType, target).isEmpty();
  }
  
  
  /**
   * Return a {@link List} of  targets on any outgoing {@link Trace} of type {@link LinkEnum} whether it exists.
   * @param tr the Element to check
   * @param linkType the type of Link.
   * @return the {@link List} of {@link TraceableElement}s whether it exits, an empty one otherwise
   */
  static public List<TraceableElement> getOutgoingLinkTargets(TraceableElement tr, LinkEnum linkType) {
    
    List<TraceableElement> list = ScenarioExt.hasLinkOftype(
        tr,
        linkType.getEClass(),
        ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES,
        ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT
    );
    
    return list;
  }
  
  /**
   * Return a {@link List} of  sources on any incoming {@link Trace} of type {@link LinkEnum} whether it exists.
   * @param tre the Element to check
   * @param linkType the type of Link .
   * @return the {@link List} of {@link TraceableElement}s whether it exits, an empty one otherwise
   */
  static public List<TraceableElement> getIncomingLinkTargets(TraceableElement tre, LinkEnum linkType) {
    
    List<TraceableElement> list = ScenarioExt.hasLinkOftype(
        tre,
        linkType.getEClass(),
        ModellingcorePackage.Literals.TRACEABLE_ELEMENT__INCOMING_TRACES,
        ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT
    );
    
    return list;
  }
 
  /**
   * Return a {@link List} of  sources on any incoming {@link Trace} of type {@link LinkEnum} whether it exists
   * from a given target {@link Scenario}.
   * @param tre the Element to check
   * @param linkType the type of Link .
   * @param target the originator {@link Scenario}
   * @return the {@link List} of {@link TraceableElement}s whether it exits, an empty one otherwise
   */
  static public List<TraceableElement> getIncomingLinkTargetsFrom(TraceableElement tre, LinkEnum linkType, Scenario target) {
    
    List<TraceableElement> result = new ArrayList<TraceableElement>();
    
    List<TraceableElement> list = ScenarioExt.hasLinkOftype(
        tre,
        linkType.getEClass(),
        ModellingcorePackage.Literals.TRACEABLE_ELEMENT__INCOMING_TRACES,
        ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT
    );
    
    EObject originator= null;
    for (TraceableElement elt : list) {
      originator = elt.eContainer();
      if ( originator.equals(target) ) {
        result.add(elt);
      }
    }
    
    return result;
  }  
  
  /**
   * Return a {@link List} of  any outgoing {@link Trace} of type {@link LinkEnum} whether it exists.
   * @param tre the {@link TraceableElement} to check
   * @param linkType the type of Link
   * @return a {@link List} of {@link AbstractTrace}s whether it exits, an empty one otherwise
   */
  static public List<AbstractTrace> getOutgoingLinks(TraceableElement tre, LinkEnum linkType) {
    
    List<AbstractTrace> result = new ArrayList<AbstractTrace>();
    
    for (AbstractTrace at: tre.getOutgoingTraces()) {
      if ( linkType.isSameType(at) ) {
        result.add(at);
      }
    }
    
    return result;
  }

  /**
   * Return a {@link List} of  any incoming {@link Trace} of type {@link LinkEnum} whether it exists.
   * @param tre the {@link TraceableElement} to check
   * @param linkType the type of Link
   * @return a {@link List} of {@link AbstractTrace}s whether it exits, an empty one otherwise
   */
  static public List<AbstractTrace> getIncomingLinks(TraceableElement tre, LinkEnum linkType) {
    
    List<AbstractTrace> result = new ArrayList<AbstractTrace>();
    
    for (AbstractTrace at: tre.getIncomingTraces()) {
      if ( linkType.isSameType(at) ) {
        result.add(at);
      }
    }
    
    return result;
  }
  
  /**
   * Return a {@link List} of  any incoming {@link Trace} of type {@link LinkEnum} whether it exists
   * from a given target {@link Scenario}  
   * @param tre the {@link TraceableElement} to check
   * @param linkType the type of Link
   * @param target the originator {@link Scenario}
   * @return a {@link List} of {@link AbstractTrace}s whether it exits, an empty one otherwise
   */
  static public List<AbstractTrace> getIncomingLinksFrom(TraceableElement tre, LinkEnum linkType, Scenario target) {
    
    List<AbstractTrace> result = new ArrayList<AbstractTrace>();
    
    EObject originator = null;
    for (AbstractTrace at: tre.getIncomingTraces()) {
      originator =  at.getSourceElement().eContainer();
      if ( 
          linkType.isSameType(at) && 
          originator.equals(target)
      ) {
        result.add(at);
      }
    }
    
    return result;
  }
  
  /**
   * Check whether a {@link AbstractTrace} of a given type exists between two {@link TraceableElement}.
   * @param eltSrc the source element
   * @param eltTgt the supposed target element
   * @param linkType the type of Link
   * @return <code>true</code> if yes, <code>false</code> otherwise
   */
  public static boolean doesMergeLinkAlreadyExist(TraceableElement eltSrc, TraceableElement eltTgt, LinkEnum linkType) {
    
    boolean result = false;
    
    for (TraceableElement te: getOutgoingLinkTargets(eltSrc, linkType)) {
      if (te.equals(eltTgt)) {
        result = true;
        break;
      }
    }
    
    return result;
  }
  
  /**
   * Remove all {@link AbstractTrace}s on a given {@link TraceableElement}.
   * @param elt the target element.
   */
  public static void removeAllLinksOn(TraceableElement elt) {
    
    List<AbstractTrace> list = new ArrayList<AbstractTrace>();
    
    list.addAll(elt.getIncomingTraces());
    list.addAll(elt.getOutgoingTraces());
    
    deleteLinks(list);
    
    return;
  }
  
  /**
   * delete {@link AbstractTrace}s
   * @param traces list of {@link AbstractTrace} to delete
   */
  public static void deleteLinks(List<AbstractTrace> traces) {
    for (AbstractTrace at: traces) {
     deleteLink(at);
    }
  
    return;
  }

  /**
   * delete an {@link AbstractTrace}
   * @param tr the {@link AbstractTrace} to delete
   */
  public static void deleteLink(AbstractTrace at) {
    
    EObject eobject = at.eContainer();
    
    if ( null != eobject ) {
      Namespace ns =  (Namespace) at.eContainer();
      ns.getOwnedTraces().remove(at);
    }
    
    return;
  }
  
}
