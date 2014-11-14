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
package org.polarsys.capella.core.projection.data.rules.classpkg;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.transfo.misc.DataHelpers;

public class Rule_Generalization extends Rule_CapellaElement {

  public Rule_Generalization() {
    super(CapellacorePackage.Literals.GENERALIZATION, CapellacorePackage.Literals.GENERALIZATION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    Generalization source = (Generalization) source_p;

    /**
     * Add into the projection list the Parent Classifier for current Generalization link only if belong to the same layer and Data Package than Child
     * classifier
     */
    if (DataHelpers.isBelongToSameDataPkgLayer(source.getSub(), source.getSuper()))
      result_p.add(source.getSuper());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__OWNED_GENERALIZATIONS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    Generalization source = (Generalization) element_p;
    Generalization target = (Generalization) result_p;
    GeneralizableElement subClassifier = source.getSub();
    GeneralizableElement superClassifier = source.getSuper();
    Classifier subClassifierTransformed = (Classifier) Query.retrieveFirstTransformedElement(subClassifier, context_p.getTransfo());

    // Add the Parent Classifier transitioned for current Generalization link only if belong to the same layer and Data Package than Child classifier
    if (DataHelpers.isBelongToSameDataPkgLayer(subClassifier, superClassifier)) {
      Classifier superClassifierTransformed = (Classifier) Query.retrieveFirstTransformedElement(superClassifier, context_p.getTransfo());
      if (null != superClassifierTransformed) {
        superClassifier = superClassifierTransformed;
      }
    }

    AttachmentHelper.getInstance(context_p).attachElementByRel(target, superClassifier, CapellacorePackage.Literals.GENERALIZATION__SUPER);
    AttachmentHelper.getInstance(context_p).attachElementByRel(target, subClassifierTransformed, CapellacorePackage.Literals.GENERALIZATION__SUB);

  }

}
