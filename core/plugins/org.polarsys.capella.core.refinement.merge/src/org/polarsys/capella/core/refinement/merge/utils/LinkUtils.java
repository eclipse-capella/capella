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
   * @param tre_p the Element to check 
   * @param linkType_p
   * @return <code>true</code> whether any Links was found, <code>false</code> otherwise.
   */
  static public boolean hasOutgoingLinks(TraceableElement tre_p, LinkEnum linkType_p) {
    return !getOutgoingLinkTargets(tre_p, linkType_p).isEmpty();
  }

  /**
   * Check whether any incoming link of a given type is pointing to a {@link TraceableElement}  
   * @param tre_p the Element to check 
   * @param linkType_p
   * @return <code>true</code> whether any Links was found, <code>false</code> otherwise.
   */
  static public boolean hasIncomingLinks(TraceableElement tre_p, LinkEnum linkType_p) {
    return !getIncomingLinkTargets(tre_p, linkType_p).isEmpty();
  }

  /**
   * Check whether any incoming link of a given type is pointing to a {@link TraceableElement}
   * from a given target {@link Scenario}
   * @param tre_p the Element to check 
   * @param linkType_p
   * @param target_p the originator {@link Scenario}
   * @return <code>true</code> whether any Links was found, <code>false</code> otherwise.
   */
  static public boolean hasIncomingLinksFrom(TraceableElement tre_p, LinkEnum linkType_p, Scenario target_p) {
    return !getIncomingLinkTargetsFrom(tre_p, linkType_p, target_p).isEmpty();
  }
  
  
  /**
   * Return a {@link List} of  targets on any outgoing {@link Trace} of type {@link LinkEnum} whether it exists.
   * @param tr_p the Element to check
   * @param linkType_p the type of Link.
   * @return the {@link List} of {@link TraceableElement}s whether it exits, an empty one otherwise
   */
  static public List<TraceableElement> getOutgoingLinkTargets(TraceableElement tr_p, LinkEnum linkType_p) {
    
    List<TraceableElement> list = ScenarioExt.hasLinkOftype(
        tr_p,
        linkType_p.getEClass(),
        ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES,
        ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT
    );
    
    return list;
  }
  
  /**
   * Return a {@link List} of  sources on any incoming {@link Trace} of type {@link LinkEnum} whether it exists.
   * @param tre_p the Element to check
   * @param linkType_p the type of Link .
   * @return the {@link List} of {@link TraceableElement}s whether it exits, an empty one otherwise
   */
  static public List<TraceableElement> getIncomingLinkTargets(TraceableElement tre_p, LinkEnum linkType_p) {
    
    List<TraceableElement> list = ScenarioExt.hasLinkOftype(
        tre_p,
        linkType_p.getEClass(),
        ModellingcorePackage.Literals.TRACEABLE_ELEMENT__INCOMING_TRACES,
        ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT
    );
    
    return list;
  }
 
  /**
   * Return a {@link List} of  sources on any incoming {@link Trace} of type {@link LinkEnum} whether it exists
   * from a given target {@link Scenario}.
   * @param tre_p the Element to check
   * @param linkType_p the type of Link .
   * @param target_p the originator {@link Scenario}
   * @return the {@link List} of {@link TraceableElement}s whether it exits, an empty one otherwise
   */
  static public List<TraceableElement> getIncomingLinkTargetsFrom(TraceableElement tre_p, LinkEnum linkType_p, Scenario target_p) {
    
    List<TraceableElement> result = new ArrayList<TraceableElement>();
    
    List<TraceableElement> list = ScenarioExt.hasLinkOftype(
        tre_p,
        linkType_p.getEClass(),
        ModellingcorePackage.Literals.TRACEABLE_ELEMENT__INCOMING_TRACES,
        ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT
    );
    
    EObject originator= null;
    for (TraceableElement tre: list) {
      originator = tre.eContainer();
      if ( originator.equals(target_p) ) {
        result.add(tre);
      }
    }
    
    return result;
  }  
  
  /**
   * Return a {@link List} of  any outgoing {@link Trace} of type {@link LinkEnum} whether it exists.
   * @param tre_p the {@link TraceableElement} to check
   * @param linkType_p the type of Link
   * @return a {@link List} of {@link AbstractTrace}s whether it exits, an empty one otherwise
   */
  static public List<AbstractTrace> getOutgoingLinks(TraceableElement tre_p, LinkEnum linkType_p) {
    
    List<AbstractTrace> result = new ArrayList<AbstractTrace>();
    
    for (AbstractTrace at: tre_p.getOutgoingTraces()) {
      if ( linkType_p.isSameType(at) ) {
        result.add(at);
      }
    }
    
    return result;
  }

  /**
   * Return a {@link List} of  any incoming {@link Trace} of type {@link LinkEnum} whether it exists.
   * @param tre_p the {@link TraceableElement} to check
   * @param linkType_p the type of Link
   * @return a {@link List} of {@link AbstractTrace}s whether it exits, an empty one otherwise
   */
  static public List<AbstractTrace> getIncomingLinks(TraceableElement tre_p, LinkEnum linkType_p) {
    
    List<AbstractTrace> result = new ArrayList<AbstractTrace>();
    
    for (AbstractTrace at: tre_p.getIncomingTraces()) {
      if ( linkType_p.isSameType(at) ) {
        result.add(at);
      }
    }
    
    return result;
  }
  
  /**
   * Return a {@link List} of  any incoming {@link Trace} of type {@link LinkEnum} whether it exists
   * from a given target {@link Scenario}  
   * @param tre_p the {@link TraceableElement} to check
   * @param linkType_p the type of Link
   * @param target_p the originator {@link Scenario}
   * @return a {@link List} of {@link AbstractTrace}s whether it exits, an empty one otherwise
   */
  static public List<AbstractTrace> getIncomingLinksFrom(TraceableElement tre_p, LinkEnum linkType_p, Scenario target_p) {
    
    List<AbstractTrace> result = new ArrayList<AbstractTrace>();
    
    EObject originator = null;
    for (AbstractTrace at: tre_p.getIncomingTraces()) {
      originator =  at.getSourceElement().eContainer();
      if ( 
          linkType_p.isSameType(at) && 
          originator.equals(target_p)
      ) {
        result.add(at);
      }
    }
    
    return result;
  }
  
  /**
   * Check whether a {@link AbstractTrace} of a given type exists between two {@link TraceableElement}.
   * @param eltSrc_p the source element
   * @param eltTgt_p the supposed target element
   * @param linkType_p the type of Link
   * @return <code>true</code> if yes, <code>false</code> otherwise
   */
  public static boolean doesMergeLinkAlreadyExist(TraceableElement eltSrc_p, TraceableElement eltTgt_p, LinkEnum linkType_p) {
    
    boolean result = false;
    
    for (TraceableElement te: getOutgoingLinkTargets(eltSrc_p, linkType_p)) {
      if (te.equals(eltTgt_p)) {
        result = true;
        break;
      }
    }
    
    return result;
  }
  
  /**
   * Remove all {@link AbstractTrace}s on a given {@link TraceableElement}.
   * @param elt_p the target element.
   */
  public static void removeAllLinksOn(TraceableElement elt_p) {
    
    List<AbstractTrace> list = new ArrayList<AbstractTrace>();
    
    list.addAll(elt_p.getIncomingTraces());
    list.addAll(elt_p.getOutgoingTraces());
    
    deleteLinks(list);
    
    return;
  }
  
  /**
   * delete {@link AbstractTrace}s
   * @param traces_p list of {@link AbstractTrace} to delete
   */
  public static void deleteLinks(List<AbstractTrace> traces_p) {
    for (AbstractTrace at: traces_p) {
     deleteLink(at);
    }
  
    return;
  }

  /**
   * delete an {@link AbstractTrace}
   * @param tr_p the {@link AbstractTrace} to delete
   */
  public static void deleteLink(AbstractTrace at_p) {
    
    EObject eobject = at_p.eContainer();
    
    if ( null != eobject ) {
      Namespace ns =  (Namespace) at_p.eContainer();
      ns.getOwnedTraces().remove(at_p);
    }
    
    return;
  }
  
}
