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
package org.polarsys.capella.core.projection.common.rules.cs;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.AbstractInformationFlow;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.ComponentContext;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.projection.common.CapellaEngine;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;

/**
 */
public class Rule_Part extends Rule_CapellaElement {

  @Override
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    Part sourcePart = (Part) element_p;

    boolean allowMultiplePart = TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(sourcePart));

    //Specific case for the part of the root component. Retrieve the existing part
    if ((sourcePart.getAbstractType() != null) && (sourcePart.getAbstractType() instanceof PartitionableElement)) {
      PartitionableElement type = (PartitionableElement) sourcePart.getAbstractType();
      if ((type != null) && (type.getRepresentingPartitions().size() == 1)) {
        Object targetObject = Query.retrieveFirstTransformedElement(type, context_p.getTransfo());
        if ((targetObject != null) && (targetObject instanceof PartitionableElement)) {
          PartitionableElement targetType = (PartitionableElement) targetObject;
          if ((targetType.getRepresentingPartitions().size() == 1) && (targetType.getRepresentingPartitions().get(0) instanceof Part)) {
            EObject t = targetType.getRepresentingPartitions().get(0);
            if ((sourcePart.getAbstractType().eContainer() instanceof BlockArchitecture) || !allowMultiplePart) {
              notifyMessage(
                  NLS.bind(ProjectionMessages.ElementTransitionedToExistingElement, EObjectLabelProviderHelper.getText(element_p),
                      EObjectLabelProviderHelper.getText(t)), new Object[] { element_p, t }, ReportManagerConstants.LOG_LEVEL_INFO, context_p.getTransfo());
              return targetType.getRepresentingPartitions().get(0);
            }
          }
        }
      }
    }

    return super.transformDirectElement(element_p, context_p);

  }

  public Rule_Part() {
    super(CsPackage.Literals.PART, CsPackage.Literals.PART);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {

    IStatus result = Status.OK_STATUS;
    Part partSrc = (Part) element_p;

    if (!(partSrc.eContainer() instanceof ComponentContext)) {
      //[TRANSITION] LCPC internal component doesn't perform transition of part if parent is not in the scope
      result = TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(partSrc.eContainer(), context_p);
      if (result.isOK()) {
        result = TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformedTo(partSrc.eContainer(), context_p, CsPackage.Literals.COMPONENT);
        if (!result.isOK()) {
          return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, NLS.bind(ProjectionMessages.ContainerTransitionedToPackage,
              EObjectLabelProviderHelper.getText(partSrc.eContainer())));
        }
      } else {
        if (TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven(partSrc))) {
          return result;
        }
      }
    }

    result = TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(partSrc.getAbstractType(), context_p);
    if (!result.isOK()) {
      return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, NLS.bind(ProjectionMessages.TypeNotTransitioned,
          EObjectLabelProviderHelper.getText(partSrc.getAbstractType())));
    }

    result = TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformedTo(partSrc.getAbstractType(), context_p, CsPackage.Literals.COMPONENT);
    if (!result.isOK()) {
      return new Status(IStatus.WARNING, ProjectionMessages.Activity_Transformation, NLS.bind(ProjectionMessages.TypeTransitionedToPackage,
          EObjectLabelProviderHelper.getText(partSrc.getAbstractType())));
    }

    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    super.attachRelated(element_p, result_p, context_p);

    Part sourcePart = (Part) element_p;
    if (isFirstAttach(element_p, result_p, context_p)) {
      // Retrieve and Type the Part
      Component lcSrcTyped = (Component) sourcePart.getType();
      Component lcTgtTyped = (Component) Query.retrieveFirstTransformedElement(lcSrcTyped, context_p.getTransfo(), CsPackage.Literals.COMPONENT);
      TigerRelationshipHelper.attachElementByRel(result_p, lcTgtTyped, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE);

      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MIN_CARD,
          context_p);
      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_MAX_CARD,
          context_p);
      AttachmentHelper.getInstance(context_p).attachTracedElements(element_p, result_p, InformationPackage.Literals.MULTIPLICITY_ELEMENT__OWNED_DEFAULT_VALUE,
          context_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject container = element_p.eContainer();
    if ((container != null) && !(container instanceof ComponentContext)) {
      EObject parent = container;
      while (parent != null) {
        EObject targetContainer = Query.retrieveFirstTransformedElement(parent, context_p.getTransfo(), CsPackage.Literals.COMPONENT);
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
    return BlockArchitectureExt.getContext((ComponentArchitecture) context_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    Part sourcePart = (Part) source_p;
    result_p.add(sourcePart.getAbstractType());
    result_p.addAll(sourcePart.getOwnedConstraints());

    if (sourcePart.getOwnedMinCard() != null) {
      result_p.add(sourcePart.getOwnedMinCard());
    }
    if (sourcePart.getOwnedMaxCard() != null) {
      result_p.add(sourcePart.getOwnedMaxCard());
    }
    if (sourcePart.getOwnedDefaultValue() != null) {
      result_p.add(sourcePart.getOwnedDefaultValue());
    }
    
    // exporting componentExchanges
    for (AbstractInformationFlow flow : sourcePart.getInformationFlows()) {
      if (flow instanceof ComponentExchange) {
        ComponentExchange ce = (ComponentExchange) flow;
        if (TransformationHandlerHelper.getInstance(context_p).isOrWillBeTransformed(ce, context_p).isOK()) {
          result_p.add(ce);
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    if (!(element_p.eContainer() instanceof ComponentContext)) {
      super.retrieveContainer(element_p, result_p, context_p);
    }
  }

}
