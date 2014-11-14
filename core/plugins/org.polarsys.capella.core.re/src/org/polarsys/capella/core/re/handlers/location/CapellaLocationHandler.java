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
package org.polarsys.capella.core.re.handlers.location;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.handlers.location.DefaultLocationHandler;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.helpers.move.MoveHelper;
import org.polarsys.capella.core.re.handlers.attributes.CapellaMoveHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class CapellaLocationHandler extends DefaultLocationHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean isValidContainement(EObject targetContainer_p, EObject target_p) {
    List<EObject> elements = Collections.singletonList(target_p);

    MoveHelper helper = new CapellaMoveHelper();
    boolean isSemanticallyCorrect = helper.checkSemanticRules(elements, targetContainer_p).isOK();
    if (!isSemanticallyCorrect) {
      return false;
    }

    // Checks all target eReferences compatibility with all selected model elements eClass.
    if (!helper.checkEMFRules(elements, targetContainer_p).isOK()) {
      return false;
    }

    return super.isValidContainement(targetContainer_p, target_p);
  }

  @Override
  protected CatalogElementLink getLinkRelatedElement(CatalogElementLink link_p, IContext context_p) {
    return null;
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
    if ((link_p.getTarget() instanceof Component) && (selection_p instanceof Part)) {
      return false;
    }
    return super.isInitialSelectionValidContainer(selection_p, link_p, context_p);
  }
}
