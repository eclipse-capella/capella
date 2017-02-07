/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.common.rules.fa;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.ComponentContext;
import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.AbstractFunctionalStructure;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.projection.preferences.ProjectionPreferencesPlugin;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

/**
 */
public class Rule_Connection extends Rule_CapellaElement {

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_Connection() {
    this(FaPackage.Literals.COMPONENT_EXCHANGE, FaPackage.Literals.COMPONENT_EXCHANGE);
  }

  public Rule_Connection(EClass source, EClass target) {
    super(source, target, FaPackage.Literals.COMPONENT_EXCHANGE_REALIZATION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject element_p, List<EObject> result_p, IContext context_p) {
    ComponentExchange sourceElement = (ComponentExchange) element_p;
    if (transformRequired(element_p, context_p).isOK()) {
      result_p.add(sourceElement.getSource());
      result_p.add(sourceElement.getTarget());
      result_p.addAll(sourceElement.getOwnedComponentExchangeEnds());
      result_p.addAll(sourceElement.getOwnedComponentExchangeFunctionalExchangeAllocations());

      if (sourceElement.getConvoyedInformations().size() > 0) {
        if (ProjectionPreferencesPlugin.getDefault().transitionExchangeItemWhileFunctionalTransition()) {
          result_p.addAll(sourceElement.getConvoyedInformations());
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    EObject object = super.transformDirectElement(element_p, context_p);
    ComponentExchange source = (ComponentExchange) element_p;
    ComponentExchange target = (ComponentExchange) object;
    target.setOriented(source.isOriented());
    target.setKind(source.getKind());
    return object;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    if (isFirstAttach(element_p, result_p, context_p)) {
      TigerRelationshipHelper.attachTransformedRelatedElements(element_p, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__SOURCE,
          context_p.getTransfo());
      TigerRelationshipHelper.attachTransformedRelatedElements(element_p, ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__TARGET,
          context_p.getTransfo());
    }

    AttachmentHelper.getInstance(context_p).attachToBestElement(element_p, result_p,
        ModellingcorePackage.Literals.ABSTRACT_INFORMATION_FLOW__CONVOYED_INFORMATIONS, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {

    //Returns always the system component.
    BlockArchitecture sourceArchitecture = BlockArchitectureExt.getRootBlockArchitecture(element_p);
    if (sourceArchitecture instanceof OperationalAnalysis) {
      return BlockArchitectureExt.getFirstComponent(sourceArchitecture);
    }

    //Returns the related context
    if (element_p.eContainer() instanceof ComponentContext) {
      BlockArchitecture targetArchitecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
      return BlockArchitectureExt.getContext(targetArchitecture);
    }

    //Returns the first transitioned container
    EObject container = element_p.eContainer();
    if ((container != null) && !(container instanceof ComponentContext)) {
      EObject parent = container;
      while (parent != null) {
        EObject targetContainer = Query.retrieveFirstTransformedElement(parent, context_p.getTransfo(), FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK);
        if (targetContainer != null) {
          return targetContainer;
        }
        targetContainer = Query.retrieveFirstTransformedElement(parent, context_p.getTransfo(), FaPackage.Literals.ABSTRACT_FUNCTIONAL_STRUCTURE);
        if (targetContainer != null) {
          return targetContainer;
        }
        parent = parent.eContainer();
      }
    }

    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    BlockArchitecture architecture = (BlockArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
    return BlockArchitectureExt.getFirstComponent(architecture);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    if ((element_p.eContainer() != null) && !(element_p.eContainer() instanceof ComponentContext)) {
      super.retrieveContainer(element_p, result_p, context_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    if (container_p instanceof AbstractFunctionalStructure) {
      return FaPackage.Literals.ABSTRACT_FUNCTIONAL_STRUCTURE__OWNED_COMPONENT_EXCHANGES;
    }
    if (container_p instanceof AbstractFunctionalBlock) {
      return FaPackage.Literals.ABSTRACT_FUNCTIONAL_BLOCK__OWNED_COMPONENT_EXCHANGES;
    }

    return super.getTargetContainementFeature(element_p, result_p, container_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    IStatus result = super.transformRequired(element_p, context_p);
    if (result.isOK()) {
      ComponentExchange ce = (ComponentExchange) element_p;

      EObject transfoSource = (EObject) context_p.get(TransfoEngine.TRANSFO_SOURCE);
      if (element_p == transfoSource) {
        return result;
      }
      if (ce.getSource() == null) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, ProjectionMessages.SourceNull);
      }
      if (ce.getTarget() == null) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, ProjectionMessages.TargetNull);
      }
      if (!TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(ce.getSource(), context_p).isOK()) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, NLS.bind(ProjectionMessages.SourceBoundNotTransitioned,
            EObjectLabelProviderHelper.getText(ce.getSource())));
      }
      if (!TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(ce.getTarget(), context_p).isOK()) {
        return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, NLS.bind(ProjectionMessages.TargetBoundNotTransitioned,
            EObjectLabelProviderHelper.getText(ce.getTarget())));
      }
    }
    return result;
  }

}
