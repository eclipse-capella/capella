/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.scenario.common.rules;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;

public class Rule_Constraint extends Rule_CapellaElement {

  public Rule_Constraint() {
    super(CapellacorePackage.Literals.CONSTRAINT, CapellacorePackage.Literals.CONSTRAINT);
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    Constraint sourceElement = (Constraint) source;
    result.add(sourceElement.getOwnedSpecification());
  }
  
  @Override
  public void update_(EObject element, ITransfo transfo) {
    super.update_(element, transfo);
  }
  
  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    Constraint sourceElement = (Constraint)element;
    for(ModelElement srcConstrainedElement : sourceElement.getConstrainedElements()) {
      EObject transformedElement = Query.retrieveFirstTransformedElement(srcConstrainedElement, getTransfo());
      if(transformedElement instanceof ModelElement) {
        ((Constraint)result).getConstrainedElements().add((ModelElement)transformedElement);
      }
    }
  }
}
