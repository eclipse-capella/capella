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

import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.tiger.impl.TransfoRule;

/**
 */
public class Rule_Generalization extends TransfoRule {

  /**
   * 
   */
  public Rule_Generalization() {
    super(CapellacorePackage.Literals.GENERALIZATION, CapellacorePackage.Literals.GENERALIZATION);

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
    Generalization sourceElement = (Generalization) element_p;
    List<EObject> relatedElements = new ArrayList<EObject>();

    relatedElements.add(sourceElement.getSuper());

    return relatedElements;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#transform(java.lang.Object)
   */
  @Override
  public EObject transform_(EObject element_p, ITransfo transfo_p) {
    return CapellacoreFactory.eINSTANCE.createGeneralization();    
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
    Generalization sourceElement = (Generalization) element_p;

    TigerRelationshipHelper.attachTransformedContainedElements(sourceElement, transfo_p, CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS);
    TigerRelationshipHelper.attachTransformedRelatedElements(sourceElement, CapellacorePackage.Literals.GENERALIZATION__SUPER, transfo_p);    
  }
}
