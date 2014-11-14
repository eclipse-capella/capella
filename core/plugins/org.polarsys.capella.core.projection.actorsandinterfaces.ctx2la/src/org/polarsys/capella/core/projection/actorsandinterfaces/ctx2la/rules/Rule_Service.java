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
package org.polarsys.capella.core.projection.actorsandinterfaces.ctx2la.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.tiger.impl.TransfoRule;

/**
 */
public class Rule_Service extends TransfoRule {

  /**
   * 
   */
  public Rule_Service() {
    super(InformationPackage.Literals.SERVICE, InformationPackage.Literals.SERVICE);

    setNeedsContext(true);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#getDescription()
   */
  @Override
  public String getDescription() {
    return super.getDescription() + __br
    		 + " - Required parameter: " + TransfoEngine.TRANSFO_SOURCE; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#retrieveRelatedElements(java.lang.Object)
   */
  @Override
  public List<EObject> retrieveRelatedElements_(EObject element_p, ITransfo transfo_p) {
    Service sourceElement = (Service) element_p;
    List<EObject> relatedElements = new ArrayList<EObject>();

    for (Parameter parameter : sourceElement.getOwnedParameters()) {
      relatedElements.add(parameter);
    }

    return relatedElements;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#transform(java.lang.Object)
   */
  @Override
  public EObject transform_(EObject element_p, ITransfo transfo_p) {
    Service sourceElement = (Service) element_p;

    Service targetElement = InformationFactory.eINSTANCE.createService();
    targetElement.setName(sourceElement.getName());

    return targetElement;    
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#update(java.lang.Object)
   */
  @Override
  public void update_(EObject element_p, ITransfo transfo_p) {
    super.update_(element_p, transfo_p);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach(java.lang.Object)
   */
  @Override
  public void attach_(EObject element_p, ITransfo transfo_p) throws TransfoException {
    Service sourceElement = (Service) element_p;
    TigerRelationshipHelper.attachTransformedContainedElements(sourceElement, transfo_p, CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES);
  }
}
