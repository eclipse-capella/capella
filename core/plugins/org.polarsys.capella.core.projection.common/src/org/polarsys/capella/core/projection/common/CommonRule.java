/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.projection.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.projection.common.rules.IRuleTransformation;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.tiger.impl.TransfoRule;

/**
 */
public abstract class CommonRule extends TransfoRule implements IRuleTransformation {

  protected ITransfo _transfo;

  protected ITransfo getTransfo() {
    return _transfo;
  }

  public CommonRule(EClass sourceType_p, EClass targetType_p) {
    super(sourceType_p, targetType_p);

    _updatedAttributes.remove(ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.getName());
    _updatedAttributes.remove(CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION.getName());
    _updatedAttributes.remove(CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY.getName());
  }

  /**
   * @param sourceType_p
   * @param targetType_p
   * @param _eSpecificLinkKind_p
   */
  public CommonRule(EClass sourceType_p, EClass targetType_p, EClass _eSpecificLinkKind_p) {
    super(sourceType_p, targetType_p, _eSpecificLinkKind_p);

    _updatedAttributes.remove(ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.getName());
    _updatedAttributes.remove(CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION.getName());
    _updatedAttributes.remove(CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY.getName());
  }

  @Override
  public void update_(EObject element_p, ITransfo transfo_p) {

    for (EObject target : Query.retrieveTransformedElements(element_p, transfo_p, getTargetType())) {
      TigerRelationshipHelper.updateElementByAttributeIfEmpty(element_p, target, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, transfo_p);
      TigerRelationshipHelper.updateElementByAttributeIfEmpty(element_p, target, CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION, transfo_p);
      TigerRelationshipHelper.updateElementByAttributeIfEmpty(element_p, target, CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY, transfo_p);
    }

    TigerRelationshipHelper.updateElementByProperty(element_p, _updatedAttributes, transfo_p);
  }

  @Override
  public String getDescription() {
    return super.getDescription() + __br + " - Required parameter: " + TransfoEngine.TRANSFO_SOURCE + __br //$NON-NLS-1$
           + " - The target " + getTargetType().getName() + " is named with the source name"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Override
  public List<EObject> retrieveRelatedElements_(EObject element_p, ITransfo transfo_p) {
    _transfo = transfo_p;
    List<EObject> result = new ArrayList<EObject>(0);

    //Retrieve container if required
    doAddContainer(element_p, result);

    //Retrieve deep elements
    doGoDeep(element_p, result);

    List<EObject> affectedMessages = new ArrayList<EObject>();
    affectedMessages.add(element_p);
    affectedMessages.addAll(result);
    notifyMessage(NLS.bind(ProjectionMessages.RetrieveElement, EObjectLabelProviderHelper.getText(element_p), EObjectLabelProviderHelper.getText(result)), affectedMessages,
        ReportManagerConstants.LOG_LEVEL_DEBUG, transfo_p);

    if (result.contains(null)) {
      notifyMessage(NLS.bind(ProjectionMessages.RetrieveElementNullElement, EObjectLabelProviderHelper.getText(element_p)), affectedMessages,
          ReportManagerConstants.LOG_LEVEL_DEBUG, transfo_p);
      while (result.contains(null)) {
        result.remove(null);
      }
    }
    return result;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach(java.lang.Object)
   */
  @Override
  public final void attach_(EObject element_p, ITransfo transfo_p) throws TransfoException {
    // 
    if (Query.isElementTransformed(element_p, transfo_p, getTargetType())) {
      if (isFirstAttach(element_p, transfo_p)) {
        runSubTransition(element_p, transfo_p);
        firstAttach(element_p, transfo_p);

        List<? extends EObject> newRoot = Query.retrieveTransformedElements(element_p, transfo_p);
        if (newRoot.size() == 0) {
          String tfm = getTransformFailedMessage(element_p, transfo_p);
          if (tfm != null){
            notifyMessage(tfm, element_p,
              ReportManagerConstants.LOG_LEVEL_WARN, transfo_p);
          }
        }

      } else if (reAttachIsNeeded(element_p, transfo_p)) {
        runSubTransition(element_p, transfo_p);
        reAttach(element_p, transfo_p);

        for (EObject newRootObject : Query.retrieveTransformedElements(element_p, transfo_p)) {
          Object previousContainer = newRootObject.eContainer();
          if (previousContainer != newRootObject.eContainer()) {
            String sourceName = EObjectLabelProviderHelper.getText(newRootObject);
            String targetName = EObjectLabelProviderHelper.getText(newRootObject.eContainer());

            notifyMessage(NLS.bind(ProjectionMessages.ElementMoved, sourceName, targetName), new Object[] { newRootObject, newRootObject.eContainer() },
                ReportManagerConstants.LOG_LEVEL_INFO, transfo_p);
          }
        }

      }
    }
  }


  /**
   * Should be overridden when a sub transition is required.
   */
  protected void runSubTransition(EObject element_p, ITransfo transfo_p) {
    //nothing to do in a common rule
  }

  /**
   * Should be overridden when a sub transition before transformation is required.
   * @param element_p
   * @param transfo_p
   */
  protected void runSubTransitionBeforeTransform(EObject element_p, ITransfo transfo_p) {
    //nothing to do
  }

  /**
   * Detects if the attachment of the element is its first attachment or not A first attach is perform when the item is transitioned to more than one time.
   */
  protected boolean isFirstAttach(EObject sourceElement_p, ITransfo transfo_p) {
    Object targetElement = Query.retrieveTransformedElement(sourceElement_p, transfo_p, getTargetType());
    if (targetElement instanceof EObject) {
      return ((EObject) targetElement).eContainer() == null;
    }
    return true;
  }

  /**
   * Detects if the attachment of the element is its first attachment or not
   */
  protected boolean reAttachIsNeeded(EObject sourceElement_p, ITransfo transfo_p) {
    return true;
  }

  /**
   * First attachment of transformed element
   */
  protected abstract void firstAttach(EObject sourceElement_p, ITransfo transfo_p) throws TransfoException;

  /**
   * Re-attachment of transformed element
   */
  protected void reAttach(EObject sourceElement_p, ITransfo transfo_p) {
    //Default implementation doesn't change containment of the targeted element
  }

  @Override
  public final Object transform_(EObject element_p, ITransfo transfo_p) {

    //Run sub transitions if necessary
    runSubTransitionBeforeTransform(element_p, transfo_p);

    //Transform the element if element needs to be transformed
    if (transformIsRequired(element_p, transfo_p)) {

      Object result = retrieveTransformedElements(element_p, transfo_p);

      //Display message
      if (result instanceof EObject) {
        EObject transformed = (EObject) result;
        String sourceText = EObjectLabelProviderHelper.getText(element_p);
        String targetText = EObjectLabelProviderHelper.getText(transformed);
        String targetName = CapellaElementExt.getName(transformed);

        String sourceEClassName = EObjectLabelProviderHelper.getMetaclassLabel(element_p, true);
        String targetEClassName = EObjectLabelProviderHelper.getMetaclassLabel(transformed, true);

        if ((targetName == null || targetName.length() == 0) && result instanceof AbstractNamedElement) {
          targetText = sourceText;
        }

        notifyMessage(NLS.bind(ProjectionMessages.ElementTransitioned, new Object[] { sourceText, sourceEClassName, targetText, targetEClassName }),
            new Object[] { element_p, transformed }, ReportManagerConstants.LOG_LEVEL_INFO, transfo_p);
      } else if (result instanceof List<?>) {

        for (Object res : (List<?>) result) {
          if (res instanceof EObject) {
            EObject transformed = (EObject) res;
            String sourceText = EObjectLabelProviderHelper.getText(element_p);
            String targetText = EObjectLabelProviderHelper.getText(transformed);
            String targetName = CapellaElementExt.getName(transformed);

            String sourceEClassName = EObjectLabelProviderHelper.getMetaclassLabel(element_p, true);
            String targetEClassName = EObjectLabelProviderHelper.getMetaclassLabel(transformed, true);
            if ((targetName == null || targetName.length() == 0) && result instanceof AbstractNamedElement) {
              targetText = sourceText;
            }
            notifyMessage(NLS.bind(ProjectionMessages.ElementTransitioned, new Object[] { sourceText, sourceEClassName, targetText, targetEClassName }),
                new Object[] { element_p, transformed }, ReportManagerConstants.LOG_LEVEL_INFO, transfo_p);
          }
        }

      }

      return result;
    }

    String tfm = getTransformFailedMessage(element_p, transfo_p);
    if (tfm != null) {
      notifyMessage(tfm, element_p, ReportManagerConstants.LOG_LEVEL_WARN,
          transfo_p);
    }
    return null;
  }

  /**
   * Returns whether an element should be transformed or not For instance, a port should not be transformed if its container cannot be transformed
   */
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    if (transformIsRequired(element_p, context_p.getTransfo())) {
      return Status.OK_STATUS;
    }
    return Status.CANCEL_STATUS;
  }

  /**
   * Returns whether an element should be transformed or not For instance, a port should not be transformed if its container cannot be transformed
   */
  protected boolean transformIsRequired(EObject element_p, ITransfo transfo_p) {
    return true;
  }

  /**
   * Explains why the element cannot be transformed
   */
  protected String reasonTransformFailed(EObject element_p, ITransfo transfo_p) {
    return ICommonConstants.EMPTY_STRING;
  }

  /**
   * Retrieve transformed elements for the given element_p
   */
  protected Object retrieveTransformedElements(EObject element_p, ITransfo transfo_p) {
    return transformElement(element_p, transfo_p);
  }

  /**
   * Transform the source element to a new instance of the target EClass of the rule
   */
  protected Object transformElement(EObject element_p, ITransfo transfo_p) {
    EPackage pkg = (EPackage) getTargetType().eContainer();
    return pkg.getEFactoryInstance().create(getTargetType());
  }

  /**
   * Go deep into the element tree from specified element.
   * @param element_p
   * @param result
   */
  protected void doGoDeep(EObject element_p, List<EObject> result) {
    //retrieve no sub elements
  }

  /**
   * Default implementation adds element container to resulting list.
   * @param element_p
   * @param result_p
   */
  protected void doAddContainer(EObject element_p, List<EObject> result_p) {
    if (!Query.isElementTransformed(element_p, _transfo)) {
      result_p.add(element_p.eContainer());
    }
  }

  /**
   * Returns whether the given element is or will be transformed after transition
   */
  protected boolean isOrWillBeTransformed(EObject element_p, ITransfo transfo_p) {
    IContext context = IContext.getContext(transfo_p);
    return TransformationHandlerHelper.getInstance(context).isOrWillBeTransformed(element_p, context).isOK();
  }

  /**
   * Returns whether the given element is or will be transformed after transition This can be useful to know if the rule loaded which perform the transition
   * will transform it to the specified target EClass
   */
  protected boolean isOrWillBeTranformedTo(EObject element_p, ITransfo transfo_p, EClass target) {
    IContext context = IContext.getContext(transfo_p);
    return TransformationHandlerHelper.getInstance(context).isOrWillBeTransformedTo(element_p, context, target).isOK();
  }

  /**
   * Report a message to the engine
   * @param message
   * @param affectedObject
   * @param priority, should be a ReportManagerConstants.LOG_LEVEL* constants
   */
  @Deprecated
  protected void notifyMessage(String message, Object affectedObject, String priority, ITransfo transfo_p) {
    if (_logger.isEnabledFor(Level.toLevel(priority))){
      EmbeddedMessage mess = new EmbeddedMessage(message, _logger.getName(), affectedObject);
      mess.setSource(getClass().getSimpleName());
      _logger.log(Level.toLevel(priority), mess);       
    }
  }

  /**
   * Specifies the message that is logged when this rule decides that an element is not transformed.
   */
  protected String getTransformFailedMessage(EObject element, ITransfo transfo){
    String reason = reasonTransformFailed(element, transfo);
    String sourceEClassName = element != null ? element.eClass().getName() : ICommonConstants.EMPTY_STRING;
    return NLS.bind(ProjectionMessages.ElementNotTransitioned, EObjectLabelProviderHelper.getText(element), sourceEClassName)
        + ((reason == null || reason.length() == 0) ? ICommonConstants.EMPTY_STRING : reason);
  }

}
