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
package org.polarsys.capella.core.projection.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.core.model.helpers.refmap.CapellaRefMap;
import org.polarsys.capella.core.model.helpers.refmap.KPair;
import org.polarsys.capella.core.model.helpers.refmap.VPair;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

/**
 */
public class CapellaEngine extends TransfoEngine {

  /**
   * TRANSFO_TARGET_CONTAINER tag's property
   */
  public static final String TRANSFO_TARGET_CONTAINER = "transfoTargetContainer"; //$NON-NLS-1$

  public CapellaEngine(Logger logger) {
    super(logger);
  }

  public CapellaEngine() {
    super();
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoEngine#doProcessDependingModels (java.util.List)
   */
  @Override
  public void doProcessDependingModels(List<EObject> dependingModels_p) throws TransfoException {
    // do nothing
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoEngine#initialize_()
   */
  @Override
  public void initialize(ITransfo transfo_p) throws TransfoException {

    super.initialize(transfo_p);

    List<TransfoLink> techLinks = new ArrayList<TransfoLink>();
    for (EObject src : _agenda) {
      AbstractTrace trace = retrieveTransfoLink(src);
      if ((trace != null) && (trace instanceof TransfoLink)) {
        techLinks.add((TransfoLink) trace);
      }
    }

    List<TransfoLink> eltsToDelete = new ArrayList<TransfoLink>();
    EObject container = (EObject) transfo_p.get(TRANSFO_TARGET_CONTAINER);
    Set<EObject> links = EObjectExt.getAll(container, CapellacommonPackage.Literals.TRANSFO_LINK);
    for (EObject obj : links) {
      TransfoLink link = (TransfoLink) obj;
      TraceableElement src = link.getSourceElement();
      TraceableElement tgt = link.getTargetElement();
      if ((src == null) || (tgt == null)) {
        eltsToDelete.add(link);
      }
    }
    for (TransfoLink link : eltsToDelete) {
      try {
        link.destroy();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }

    for (TransfoLink transfoLink : techLinks) {
      TraceableElement src = transfoLink.getSourceElement();
      TraceableElement tgt = transfoLink.getTargetElement();

      if (!isSemantiklyLinked(src, LaPackage.Literals.LOGICAL_COMPONENT, tgt, PaPackage.Literals.PHYSICAL_COMPONENT,
          CsPackage.Literals.COMPONENT_REALIZATION)
          || !isSemantiklyLinked(src, FaPackage.Literals.COMPONENT_EXCHANGE, tgt, FaPackage.Literals.COMPONENT_EXCHANGE,
              FaPackage.Literals.COMPONENT_EXCHANGE_REALIZATION)
          || !isSemantiklyLinked(src, FaPackage.Literals.FUNCTIONAL_EXCHANGE, tgt, FaPackage.Literals.FUNCTIONAL_EXCHANGE,
              FaPackage.Literals.FUNCTIONAL_EXCHANGE_REALIZATION)
          || !isSemantiklyLinked(src, FaPackage.Literals.FUNCTIONAL_EXCHANGE, tgt, FaPackage.Literals.COMPONENT_EXCHANGE,
              FaPackage.Literals.COMPONENT_EXCHANGE_FUNCTIONAL_EXCHANGE_ALLOCATION)
          || !isSemantiklyLinked(src, FaPackage.Literals.ABSTRACT_FUNCTION, tgt, FaPackage.Literals.ABSTRACT_FUNCTION, FaPackage.Literals.FUNCTION_REALIZATION)
          || !isSemantiklyLinked(src, CsPackage.Literals.INTERFACE, tgt, CsPackage.Literals.INTERFACE, CsPackage.Literals.INTERFACE_ALLOCATION)) {
        transfoLink.destroy();
      }
    }
  }

  /**
   * Retrieves the transformation links.
   * @param object_p The element to be queried
   * @param transfo_p The transformation
   * @return The transformed element
   */
  protected AbstractTrace retrieveTransfoLink(EObject object_p) {
    if (object_p instanceof CapellaElement) {
      CapellaElement element = (CapellaElement) object_p;
      List<AbstractTrace> traceList = element.getIncomingTraces();
      for (AbstractTrace trace : traceList) {
        if (Query.isLinkOfTransfo(trace, _transfo)) {
          return trace;
        }
      }
    }
    return null;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoEngine#initialize_()
   */
  @Override
  protected void initialize_() {
    // do nothing
  }

  @Override
  public void generateUid() {
    CapellaElement transfoTarget = (CapellaElement) _transfo.get(TRANSFO_TARGET_CONTAINER);
    _transfo.setUid("Type='Transition' TargetId='" + transfoTarget.getId() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoEngine#finalize_()
   */
  @Override
  protected void finalize_() {
    if (_logger.isDebugEnabled()) {
      _logger.debug("===================================="); //$NON-NLS-1$
      _logger.debug("Finalizing..."); //$NON-NLS-1$
      _logger.debug("===================================="); //$NON-NLS-1$
    }
    performContributionCommands();
    attachTransfoLinks();
    attachSemanticLinks();
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected void performContributionCommands() {
    Object transformedElements = _transfo.get(TRANSFORMED_ELEMENTS);
    if ((transformedElements != null) && (transformedElements instanceof Collection)) {
      Collection<EObject> transformedElementsList = (Collection) transformedElements;
      for (EObject element : transformedElementsList) {
        CompoundCommand command = CreationHelper.getContributorsCommand(element, element.eContainer(), element.eClass(), element.eContainmentFeature());
        if (command.canExecute()) {
          command.execute();
        }
      }
    }

    Object links = _transfo.get(NEW_LINKS);
    if ((links != null) && (links instanceof Collection)) {
      Collection<EObject> linksList = (Collection) links;
      for (EObject element : linksList) {
        CompoundCommand command = CreationHelper.getContributorsCommand(element, element.eContainer(), element.eClass(), element.eContainmentFeature());
        if (command.canExecute()) {
          command.execute();
        }
      }
    }

  }

  /**
   * Attach created technical links (inherited from 'GenericTrace' type) to the target model
   */
  @SuppressWarnings("unchecked")
  protected void attachTransfoLinks() {
    List<AbstractTrace> links = (List<AbstractTrace>) _transfo.get(NEW_LINKS);
    for (AbstractTrace abstractLink : links) {
      if (abstractLink instanceof GenericTrace) {
        GenericTrace transfoLink = (GenericTrace) abstractLink;
        TraceableElement elt = transfoLink.getSourceElement();
        Namespace ownerElt = (Namespace) ((elt instanceof Namespace) ? elt : EcoreUtil2.getFirstContainer(elt, CapellacorePackage.Literals.NAMESPACE));
        if (ownerElt != null) {
          HoldingResourceHelper.ensureMoveElement(transfoLink, ownerElt);
          ownerElt.getOwnedTraces().add(transfoLink);

        }
      }
    }
  }

  /**
   * Attach created semantic links (inherited from 'AbstractTrace' type) to the target model
   */
  @SuppressWarnings("unchecked")
  protected void attachSemanticLinks() {
    Map<KPair, VPair> mappings = CapellaRefMap.getInstance().getMappings();

    for (EObject src : _agenda) {
      Object tgt = Query.retrieveTransformedElement(src, _transfo);
      if (tgt != null) {
        if (tgt instanceof EObject) {
          EObject tgt2 = (EObject) tgt;
          attachSemanticLink(mappings, tgt2, src);
        } else {
          List<EObject> l = (List<EObject>) tgt;
          for (EObject tgt2 : l) {
            attachSemanticLink(mappings, tgt2, src);
          }
        }
      }
    }
  }

  @SuppressWarnings("unchecked")
  protected void attachSemanticLink(Map<KPair, VPair> mapping_p, EObject linkSrc_p, EObject linkTgt_p) {
    VPair semanticLink = mapping_p.get(new KPair(linkSrc_p.eClass(), linkTgt_p.eClass()));
    if (semanticLink != null) {
      for (AbstractTrace trace : ((TraceableElement) linkTgt_p).getIncomingTraces()) {
        EClass[] classes = semanticLink.getFirstValue();
        EReference[] references = semanticLink.getSecondValue();
        for (int i = 0; i < classes.length; i++) {
          if (classes[i].isInstance(trace) && (trace.getSourceElement() == linkSrc_p)) {
            HoldingResourceHelper.ensureMoveElement(trace, linkSrc_p);
            ((Collection<EObject>) linkSrc_p.eGet(references[i])).add(trace);

          }
        }
      }
    }
  }

  protected boolean isSemantiklyLinked(TraceableElement src, EClass srcType, TraceableElement tgt, EClass tgtType, EClass semanticLink) {
    if ((!srcType.equals(src.eClass()) && !srcType.isSuperTypeOf(src.eClass())) || (!tgtType.equals(tgt.eClass()) && !tgtType.isSuperTypeOf(tgt.eClass()))) {
      return true;
    }
    for (AbstractTrace abstractTrace : src.getOutgoingTraces()) {
      if (abstractTrace.eClass() == semanticLink) {
        TraceableElement traceableElt = abstractTrace.getTargetElement();
        if (traceableElt == tgt) {
          return true;
        }
      }
    }
    return false;
  }
}
