/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.scenario.is2is.rules;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.projection.common.CommonRule;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_AbstractEnd extends CommonRule {

  /**
   *
   *
   */
  public Rule_AbstractEnd() {
    super(InteractionPackage.Literals.ABSTRACT_END, InteractionPackage.Literals.ABSTRACT_END);
  }

  @Override
  protected String reasonTransformFailed(EObject element, ITransfo transfo) {
    return ProjectionMessages.EventNotTransitioned;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element, ITransfo transfo) {

    TigerRelationshipHelper.attachTransformedRelatedElements(element, InteractionPackage.Literals.ABSTRACT_END__EVENT, transfo);

    if (element instanceof ExecutionEnd) {
      TigerRelationshipHelper.attachTransformedRelatedElements(element, InteractionPackage.Literals.EXECUTION_END__EXECUTION, transfo);
    }
    TigerRelationshipHelper.attachTransformedRelatedElements(element, InteractionPackage.Literals.INTERACTION_FRAGMENT__COVERED_INSTANCE_ROLES, transfo);
    
    TigerRelationshipHelper.attachUnattachedIntoTransformedContainer(element, getTargetType(),
        InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS, transfo);
  }

  @Override
  protected Object transformElement(EObject element, ITransfo transfo) {
    EPackage pkg = (EPackage) element.eClass().eContainer();
    return pkg.getEFactoryInstance().create(element.eClass());
  }
}
