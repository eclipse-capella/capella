/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.common.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.AbortExecutionException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.model.handler.provider.IReadOnlySectionHandler;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.projection.common.handlers.log.LogHelper;
import org.polarsys.capella.core.projection.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.tiger.impl.TransfoRule;

/**
 * Transformation rule between Tiger and Transposer
 */
public abstract class AbstractCommonRule extends TransfoRule implements IRuleTransformation {

  protected ITransfo _transfo;

  protected ITransfo getTransfo() {
    return _transfo;
  }

  public AbstractCommonRule(EClass sourceType_p, EClass targetType_p) {
    super(sourceType_p, targetType_p);
  }

  /**
   * @param sourceType_p
   * @param targetType_p
   * @param _eSpecificLinkKind_p
   */
  public AbstractCommonRule(EClass sourceType_p, EClass targetType_p, EClass _eSpecificLinkKind_p) {
    super(sourceType_p, targetType_p, _eSpecificLinkKind_p);

  }

  @Override
  public void update_(EObject element_p, ITransfo transfo_p) {

    IContext context_p = IContext.getContext(transfo_p);

    if (Query.isElementTransformed(element_p, transfo_p, getTargetType())) {
      for (EObject result : retrieveTracedElements(element_p, context_p)) {
        if (isValidTargetElement(element_p, result, context_p)) {
          updateElement(element_p, result, context_p);
        }
      }
    }

    TigerRelationshipHelper.updateElementByProperty(element_p, _updatedAttributes, transfo_p);
  }

  @Override
  public String getDescription() {
    return super.getDescription() + __br + " - Required parameter: " + TransfoEngine.TRANSFO_SOURCE + __br //$NON-NLS-1$
           + " - The target " + getTargetType().getName() + " is named with the source name"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Override
  public List<EObject> retrieveRelatedElements_(EObject source_p, ITransfo transfo_p) {
    _transfo = transfo_p;
    List<EObject> result = new ArrayList<EObject>(0);

    IContext context_p = IContext.getContext(transfo_p);

    retrieveCurrent(source_p, result, context_p);
    retrieveContainer(source_p, result, context_p);
    retrieveGoDeep(source_p, result, context_p);

    List<EObject> affectedMessages = new ArrayList<EObject>();
    affectedMessages.add(source_p);
    affectedMessages.addAll(result);
    notifyMessage(NLS.bind(ProjectionMessages.RetrieveElement, EObjectLabelProviderHelper.getText(source_p), EObjectLabelProviderHelper.getText(result)),
        affectedMessages, ReportManagerConstants.LOG_LEVEL_DEBUG, transfo_p);

    if (result.contains(null)) {
      notifyMessage(NLS.bind(ProjectionMessages.RetrieveElementNullElement, EObjectLabelProviderHelper.getText(source_p)), affectedMessages,
          ReportManagerConstants.LOG_LEVEL_DEBUG, transfo_p);
      while (result.contains(null)) {
        result.remove(null);
      }
    }
    return result;
  }

  protected void retrieveCurrent(EObject source_p, List<EObject> result_p, IContext context_p) {
    result_p.add(source_p);
  }

  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    // Nothing here
  }

  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    EObject container = element_p.eContainer();
    if (container != null) {
      if (!Query.isElementTransformed(element_p, _transfo)) {
        result_p.add(container);
      }
    }
  }

  protected List<EObject> retrieveTracedElements(EObject source_p, IContext context_p) {
    return TraceabilityHandlerHelper.getInstance(context_p).retrieveTracedElements(source_p, context_p);
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  protected boolean isValidTargetElement(EObject element_p, EObject result_p, IContext context_p) {
    return result_p != null;
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   */
  protected void updateElement(EObject element_p, EObject result_p, IContext context_p) {
    // Nothing here
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  protected boolean isContainementAttached(EObject element_p, EObject result_p, IContext context_p) {
    return result_p.eContainer() != null;
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    EObject bestContainer = null;
    EObject container = getSourceContainer(element_p, result_p, context_p);
    if (container != null) {
      bestContainer =
          TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(container, context_p,
              TransformationHandlerHelper.getInstance(context_p).getTargetType(container, context_p));
    }
    return bestContainer;
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  protected EObject getSourceContainer(EObject element_p, EObject result_p, IContext context_p) {
    return element_p.eContainer();
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  protected EObject getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    return null;
  }

  /**
   * Default implementation can return null if element_p is not attached
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  protected EStructuralFeature getSourceContainementFeature(EObject element_p, EObject result_p, IContext context_p) {
    return element_p.eContainingFeature();
  }

  /**
   * Default implementation can return null if element_p is not attached
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container, IContext context_p) {
    return element_p.eContainingFeature();
  }

  /**
   * Default implementation attach the target into the transitioned container
   * @param element_p
   * @param result_p
   * @param context_p
   */
  protected void attachContainement(EObject element_p, EObject result_p, IContext context_p) {
    EObject container = getBestContainer(element_p, result_p, context_p);

    if (container == null) {
      container = getDefaultContainer(element_p, result_p, context_p);
    }

    EStructuralFeature feature = getTargetContainementFeature(element_p, result_p, container, context_p);

    // Check that the container is not read only before attaching the element to the model.
    IReadOnlySectionHandler roHandler = CapellaReadOnlyHelper.getReadOnlySectionHandler();
    if ((roHandler != null) && roHandler.isLockedByOthers(container)) {
      throw new AbortExecutionException("the container may be read only. Please check that you are allowed to edit " //$NON-NLS-1$
                                        + EObjectExt.getText(container) + "."); //$NON-NLS-1$
    }

    if (container != null) {
      if (AttachmentHelper.getInstance(context_p).isApplicable(container.eClass(), feature)) {
        AttachmentHelper.getInstance(context_p).attachElementByRel(container, result_p, (EReference) feature);
      }
    }

  }

  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    // Check that the related element is not read only before attaching the element to the model.
    IReadOnlySectionHandler roHandler = CapellaReadOnlyHelper.getReadOnlySectionHandler();
    if ((roHandler != null) && roHandler.isLockedByOthers(result_p)) {
      throw new AbortExecutionException("the related element may be read only. Please check that you are allowed to edit " //$NON-NLS-1$
                                        + EObjectExt.getText(result_p) + "."); //$NON-NLS-1$
    }
  }

  protected boolean isFirstAttach(EObject element_p, EObject result_p, IContext context_p) {
    return ((Collection<?>) context_p.get(TransfoEngine.TRANSFORMED_ELEMENTS)).contains(result_p);
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  protected boolean isRelatedAttached(EObject element_p, EObject result_p, IContext context_p) {
    return false;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach(java.lang.Object)
   */
  @Override
  public void attach_(EObject element_p, ITransfo transfo_p) throws TransfoException {
    IContext context_p = IContext.getContext(transfo_p);

    if (Query.isElementTransformed(element_p, transfo_p, getTargetType())) {

      runSubTransition(element_p, transfo_p);

      for (EObject result : retrieveTracedElements(element_p, context_p)) {
        if (isValidTargetElement(element_p, result, context_p)) {
          updateElement(element_p, result, context_p);

          if (!isContainementAttached(element_p, result, context_p)) {
            attachContainement(element_p, result, context_p);
          }
          if (!isRelatedAttached(element_p, result, context_p)) {
            attachRelated(element_p, result, context_p);
          }
        }
      }
    }

  }

  /**
   * Should be overridden when a sub transition is required.
   */
  protected void runSubTransition(EObject element_p, ITransfo transfo_p) {
    // nothing to do in a common rule
  }

  /**
   * Should be overridden when a sub transition before transformation is required.
   * @param element_p
   * @param transfo_p
   */
  protected void runSubTransitionBeforeTransform(EObject element_p, ITransfo transfo_p) {
    // nothing to do
  }

  /**
   * @param element_p
   * @param context_p
   */
  protected Collection<EObject> transformElement(EObject element_p, IContext context_p) {
    EObject transitioned = transformDirectElement(element_p, context_p);
    return Collections.singleton(transitioned);
  }

  /**
   * Transform the source element to a new instance of the target EClass of the rule
   */
  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    EClass targetType = getTargetType();
    if ((targetType != null) && !targetType.isAbstract()) {
      EPackage pkg = (EPackage) targetType.eContainer();
      return pkg.getEFactoryInstance().create(targetType);
    }
    return null;
  }

  protected Resource getHoldingResource(ITransfo transfo_p) {
    if (!transfo_p.containsKey(TransfoEngine.HOLDING_RESOURCE)) {
      EObject transfoSource = (EObject) _transfo.get(TransfoEngine.TRANSFO_SOURCE);
      transfo_p.put(TransfoEngine.HOLDING_RESOURCE, HoldingResourceHelper.getHoldingResource(TransactionHelper.getEditingDomain(transfoSource)));
    }
    return (Resource) transfo_p.get(TransfoEngine.HOLDING_RESOURCE);
  }

  @Override
  public final Object transform_(EObject element_p, ITransfo transfo_p) {

    IContext context_p = IContext.getContext(transfo_p);

    // Run sub transitions if necessary
    runSubTransitionBeforeTransform(element_p, transfo_p);

    IStatus transformRequired = transformRequired(element_p, context_p);
    // Transform the element if element needs to be transformed
    if (transformRequired.isOK()) {

      Collection<EObject> result = transformElement(element_p, context_p);

      for (EObject res : result) {
        EObject transformed = res;
        if (res.eResource() == null) {
          HoldingResourceHelper.attachToHoldingResource(res, getHoldingResource(_transfo));
        }

        String sourceText = EObjectLabelProviderHelper.getText(element_p);
        String targetText = EObjectLabelProviderHelper.getText(transformed);
        String targetName = CapellaElementExt.getName(transformed);

        String sourceEClassName = EObjectLabelProviderHelper.getMetaclassLabel(element_p, true);
        String targetEClassName = EObjectLabelProviderHelper.getMetaclassLabel(transformed, true);
        if (((targetName == null) || (targetName.length() == 0)) && (res instanceof AbstractNamedElement)) {
          targetText = sourceText;
        }
        notifyMessage(NLS.bind(ProjectionMessages.ElementTransitioned, new Object[] { sourceText, sourceEClassName, targetText, targetEClassName }),
            new Object[] { element_p, transformed }, ReportManagerConstants.LOG_LEVEL_INFO, transfo_p);
      }

      return result;
    }

    String reason = transformRequired.getMessage();
    String sourceEClassName = EObjectLabelProviderHelper.getMetaclassLabel(element_p, true);
    notifyMessage(NLS.bind(ProjectionMessages.ElementNotTransitioned, EObjectLabelProviderHelper.getText(element_p), sourceEClassName)
                  + (((reason == null) || (reason.length() == 0)) ? ICommonConstants.EMPTY_STRING : reason), element_p, ReportManagerConstants.LOG_LEVEL_WARN,
        transfo_p);
    return null;
  }

  /**
   * Returns whether an element should be transformed or not For instance, a port should not be transformed if its container cannot be transformed
   */
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * Report a message to the engine
   * @param message
   * @param affectedObject
   * @param priority, should be a ReportManagerConstants.LOG_LEVEL* constants
   */
  protected void notifyMessage(String message, Object affectedObject, String priority, ITransfo transfo_p) {
    LogHelper.getInstance().log(message, priority, affectedObject, getClass().getSimpleName());
  }

  /**
   * Returns whether the given element is or will be transformed after transition
   */
  protected boolean isOrWillBeTransformed(EObject element_p, ITransfo transfo_p) {
    IContext context = IContext.getContext(transfo_p);
    return TransformationHandlerHelper.getInstance(context).isOrWillBeTransformed(element_p, context).isOK();
  }

}
