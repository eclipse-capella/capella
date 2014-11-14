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

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentContext;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.projection.common.AbstractRule;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class Rule_Part extends AbstractRule {

  public Rule_Part() {
    super(CsPackage.Literals.PART, CsPackage.Literals.PART);

    setNeedsContext(true);
    _updatedAttributes.remove(ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.getName());
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#getDescription()
   */
  @Override
  public String getDescription() {
    return super.getDescription() + __br + " - Required parameter: " + TransfoEngine.TRANSFO_SOURCE + __br //$NON-NLS-1$
           + " - The target part is named with the source name."; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#when(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.impl.Transfo)
   */
  @Override
  public boolean when(EObject element_p, ITransfo transfo_p) {
    if (!super.when(element_p, transfo_p))
      return false;

    return true;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#transform(java.lang.Object)
   */
  @Override
  public EObject transform_(EObject element_p, ITransfo transfo_p) {
    Part sourceElement = (Part) element_p;

    Part part = CsFactory.eINSTANCE.createPart(sourceElement.getName());

    return part;
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
    Part partSrc = (Part) element_p;
    Part partTgt = (Part) Query.retrieveTransformedElement(element_p, transfo_p);

    ComponentContext lcTgtContainer = ((LogicalArchitecture) transfo_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER)).getOwnedLogicalContext();
    lcTgtContainer.getOwnedFeatures().add(partTgt);

    // Retrieve and Type the Part
    Component lcSrcTyped = (Component) partSrc.getType();
    Component lcTgtTyped = (Component) Query.retrieveTransformedElement(lcSrcTyped, transfo_p);
    partTgt.setAbstractType(lcTgtTyped);
  }

  @Override
  protected void doGoDeep(EObject element_p, List<EObject> result) {
    Part sourcePart = (Part) element_p;
    result.add(sourcePart.getAbstractType());
  }

  /**
   * @see org.polarsys.capella.core.projection.common.AbstractRule#doAddContainer(org.eclipse.emf.ecore.EObject, java.util.List)
   */
  @Override
  protected void doAddContainer(EObject element_p, List<EObject> result_p) {
    // Do nothing
  }
}
