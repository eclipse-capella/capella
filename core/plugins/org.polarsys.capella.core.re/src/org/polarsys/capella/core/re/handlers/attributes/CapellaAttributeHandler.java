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
package org.polarsys.capella.core.re.handlers.attributes;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.handlers.attributes.DefaultAttributeHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class CapellaAttributeHandler extends DefaultAttributeHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean isValidContainement(EObject targetContainer_p, EObject target_p) {
    List<EObject> elements = Collections.singletonList(target_p);
    boolean isSemanticallyCorrect = new CapellaMoveHelper().checkSemanticRules(elements, targetContainer_p).isOK();
    if (!isSemanticallyCorrect) {
      return false;
    }

    // Checks all target eReferences compatibility with all selected model elements eClass.
    if (!new CapellaMoveHelper().checkEMFRules(elements, targetContainer_p).isOK()) {
      return false;
    }

    return super.isValidContainement(targetContainer_p, target_p);
  }

  @Override
  protected CatalogElementLink getLinkRelatedElement(CatalogElementLink link_p, IContext context_p) {

    EObject target = link_p.getTarget();
    if (target instanceof Component) {
      Collection<Part> parts = ComponentExt.getRepresentingParts((Component) target);
      if (!parts.isEmpty()) {
        CatalogElementLink container = getLink(link_p.getSource(), parts.iterator().next(), context_p);
        if (container != null) {
          return container;
        }
      }
    }

    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isSuffixableElement(Object object_p, IContext context_p) {
    //avoid suffix on Exchanges
    if ((object_p instanceof FunctionalExchange) || (object_p instanceof ComponentExchange) || (object_p instanceof PhysicalLink)) {
      return false;
    }
    return super.isSuffixableElement(object_p, context_p);
  }

  @Override
  protected Collection<EObject> getRelatedElements(EObject element_p) {
    if (element_p instanceof Part) {
      return Arrays.asList(new EObject[] { element_p, ((Part) element_p).getAbstractType() });
    }
    return Collections.singleton(element_p);
  }

  @Override
  protected boolean isInitialSelectionValidContainer(EObject selection_p, CatalogElementLink link_p, IContext context_p) {

    if ((link_p.getTarget() instanceof Part) && (((Part) link_p.getTarget()).getAbstractType() instanceof AbstractActor)) {
      return false;
    }

    return super.isInitialSelectionValidContainer(selection_p, link_p, context_p);
  }
}
