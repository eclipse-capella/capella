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
package org.polarsys.capella.core.transition.common.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.log.LogHelper;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeHandler;
import org.polarsys.capella.core.transition.common.handlers.scope.ScopeHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.selection.ISelectionContext;
import org.polarsys.capella.core.transition.common.handlers.selection.SelectionContextHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.traceability.TraceabilityHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.business.premises.ContainmentPremise;
import org.polarsys.kitalpha.transposer.rules.handler.business.premises.PrecedencePremise;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;

public abstract class AbstractRule implements IRule<EObject>, IRuleScope, IRuleTransformation, IRuleAttachment {

  /**
   * ------------ IRuleScope implementation - Retrieve related elements ------------
   */
  /**
   * @see org.polarsys.capella.core.transition.common.rules.IRuleScope#retrieveRootElement(java.lang.Object, java.lang.Object)
   */
  public List<EObject> retrieveRootElements(EObject source, IContext context) {
    setCurrentContext(context);
    List<EObject> result = new ArrayList<EObject>();
    retrieveRootElement(source, result, context);
    return result;
  }

  /**
   * @see org.polarsys.capella.core.transition.common.rules.IRuleScope#retrieveContainer(java.lang.Object, java.lang.Object)
   */
  public List<EObject> retrieveContainers(EObject source, IContext context) {
    setCurrentContext(context);
    List<EObject> result = new ArrayList<EObject>();
    retrieveContainer(source, result, context);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public List<EObject> retrieveRequiredElements(EObject element, IContext context) {
    setCurrentContext(context);
    List<EObject> result = new ArrayList<EObject>();
    retrieveRequired(element, result, context);
    return result;
  }

  /**
   * @see org.polarsys.capella.core.transition.common.rules.IRuleScope#retrieveRelatedElements(java.lang.Object, java.lang.Object)
   */
  public List<EObject> retrieveRelatedElements(EObject element, IContext context) {
    setCurrentContext(context);
    List<EObject> result = new ArrayList<EObject>();
    retrieveCurrent(element, result, context);
    retrieveGoDeep(element, result, context);

    return result;
  }

  protected void retrieveCurrent(EObject source, List<EObject> result, IContext context) {
    setCurrentContext(context);
    result.add(source);
  }

  protected void retrieveRequired(EObject element, List<EObject> result, IContext context) {
    // Nothing here
  }

  protected void retrieveRootElement(EObject source, List<EObject> result, IContext context) {
    EObject container = TransformationHandlerHelper.getInstance(context).getLevelElement(source, context);
    if (container != null) {
      result.add(container);
    }
  }

  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    // Nothing here
  }

  protected void retrieveContainer(EObject element, List<EObject> result, IContext context) {
    EObject container = element.eContainer();
    if (container != null) {
      result.add(container);
    }
  }

  /**
   * ------------ Premises computing ------------
   */

  public List<IPremise> getPremises(EObject element) {
    ArrayList<IPremise> needed = new ArrayList<IPremise>();
    ArrayList<EObject> previous = new ArrayList<EObject>();
    premicesContainement(element, needed);
    premicesRelated(element, needed);
    previous.add(element);

    // Log invalid premises
    IContext context = getCurrentContext();
    IScopeHandler scope = ScopeHandlerHelper.getInstance(context);
    if (scope != null) {
      for (IPremise premise : new ArrayList<IPremise>(needed)) {
        Object result = null;
        if (premise instanceof PrecedencePremise) {
          result = ((PrecedencePremise) premise).getFirstElement();
          if ((result != null) && (result instanceof EObject)) {
            previous.add((EObject) result);

          } else if ((result != null) && (result instanceof EObject) && !scope.isInScope((EObject) result, context)) {
            LogHelper.getInstance().warn(
                NLS.bind("Element ''{0}'' is premise of ''{1}'' but not in scope.", LogHelper.getInstance().getText(result),
                    LogHelper.getInstance().getText(element)), Messages.Activity_Transformation);
          }
        }
      }
    }

    if (LogHelper.getInstance().hasDebug()) {
      LogHelper.getInstance().debug(NLS.bind("Premises of ''{0}'' .", LogHelper.getInstance().getText(element)), previous, Messages.Activity_Transformation);
    }

    return needed;
  }

  /**
   * @param element
   * @param needed
   */
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    // Nothing here
  }

  /**
   * @param element
   * @param needed
   */
  protected void premicesContainement(EObject element, ArrayList<IPremise> needed) {
    IPremise premise = createDefaultContainementPremice(element);
    if (premise != null) {
      needed.add(premise);
    }
  }

  /**
   * Return a premise for the container (by default, if container isn't in the scope of transformation, we find recursively a container in the scope)
   */
  protected ContainmentPremise<EObject> createDefaultContainementPremice(EObject eObject) {
    EObject parent = eObject.eContainer();
    IContext context = getCurrentContext();
    if (context != null) {
      IScopeHandler scope = ScopeHandlerHelper.getInstance(context);
      if (scope != null) {
        parent = eObject.eContainer();
        while (parent != null) {
          if (scope.isInScope(parent, context)) {
            break;
          }
          parent = parent.eContainer();
        }
      }
    }

    if (parent != null) {
      return createContainmentPremice(parent);
    }
    return null;
  }

  /**
   * Return a premise for any elements by eObject.eGet(reference) available in the scope
   */
  protected List<PrecedencePremise<EObject>> createDefaultPrecedencePremices(Collection<EObject> referencedElements, String name) {
    ArrayList<PrecedencePremise<EObject>> premices = new ArrayList<PrecedencePremise<EObject>>();

    IContext context = getCurrentContext();
    if (context != null) {
      IScopeHandler scope = ScopeHandlerHelper.getInstance(context);
      if (scope != null) {
        for (EObject obj : referencedElements) {
          if (scope.isInScope(obj, context)) {
            premices.add(createPrecedencePremice(obj, name));
          }
        }
      }
    }
    return premices;
  }

  /**
   * Return a premise for any elements by eObject.eGet(reference) available in the scope
   */
  protected List<PrecedencePremise<EObject>> createDefaultCriticalPremices(Collection<EObject> referencedElements, String name) {
    ArrayList<PrecedencePremise<EObject>> premices = new ArrayList<PrecedencePremise<EObject>>();

    IContext context = getCurrentContext();
    if (context != null) {
      IScopeHandler scope = ScopeHandlerHelper.getInstance(context);
      if (scope != null) {
        for (EObject obj : referencedElements) {
          if (scope.isInScope(obj, context)) {
            premices.add(createCriticalPremice(obj, name));
          }
        }
      }
    }
    return premices;
  }

  /**
   * Return a premise for any elements by eObject.eGet(reference) available in the scope
   */
  public List<PrecedencePremise<EObject>> createDefaultPrecedencePremices(EObject eObject, EReference reference) {
    ArrayList<PrecedencePremise<EObject>> premices = new ArrayList<PrecedencePremise<EObject>>();

    IContext context = getCurrentContext();
    if (context != null) {
      IScopeHandler scope = ScopeHandlerHelper.getInstance(context);
      if (scope != null) {

        if (reference.isMany()) {
          for (Object obj : ((EList) eObject.eGet(reference))) {
            if ((obj != null) && (obj instanceof EObject)) {
              if (scope.isInScope((EObject) obj, context)) {
                premices.add(createPrecedencePremice((EObject) obj, reference.getName()));
              }
            }
          }
        } else {
          Object obj = eObject.eGet(reference);
          if ((obj != null) && (obj instanceof EObject)) {
            if (scope.isInScope((EObject) obj, context)) {
              premices.add(createPrecedencePremice((EObject) obj, reference.getName()));
            }
          }
        }
      }
    }
    return premices;
  }

  /**
   * Return critical premises for all targeted elements from the given reference
   * @param eObject
   * @param reference
   * @return
   */
  public List<PrecedencePremise<EObject>> createDefaultCriticalPremices(EObject eObject, EReference reference) {
    List<PrecedencePremise<EObject>> premices = createDefaultPrecedencePremices(eObject, reference);
    for (PrecedencePremise<EObject> premice : premices) {
      premice.setCritical(true);
    }
    return premices;
  }

  protected PrecedencePremise<EObject> createPrecedencePremice(EObject eObject, String string) {
    PrecedencePremise<EObject> premice = null;
    premice = new PrecedencePremise<EObject>(eObject, string);
    return premice;
  }

  protected ContainmentPremise<EObject> createContainmentPremice(EObject eObject) {
    ContainmentPremise<EObject> premice = null;
    premice = new ContainmentPremise<EObject>(eObject);
    return premice;
  }

  protected ContainmentPremise<EObject> createCriticalPremice(EObject eObject, String string) {
    ContainmentPremise<EObject> premice = null;
    premice = new ContainmentPremise<EObject>(eObject);
    return premice;
  }

  /**
   * ------------ Apply ------------
   * (handle both, complete and incomplete rules)
   * in an incomplete rule, we perform only transformation, and we attach them when the complete rule will be triggered
   */

  public void apply(EObject element, IContext context) throws Exception {

    Boolean isComplete = ((Boolean) context.get(ITransitionConstants.TRANSPOSER_APPLY_IS_COMPLETE));
    boolean isCompleteRule = isComplete == null ? true : isComplete.booleanValue();
    if (!isCompleteRule) {
      registerAsIncomplete(element, context);
    }

    try {
      if (applyRequired(element, context).isOK()) {

        if (!isCompleteRule || (isCompleteRule && !isIncomplete(element, context))) {

          IStatus transformRequired = transformRequired(element, context);

          if (transformRequired.isOK()) {
            for (EObject result : transformElement(element, context)) {
              postTransformElement(element, result, context);

              if (isValidTargetElement(element, result, context)) {
                if (!isRegisteredTargetElement(element, result, context)) {
                  registerTargetElement(element, result, context);
                }
              }
            }
          } else {
            LogHelper.getInstance().log("Element is not transitioned: " + transformRequired.getMessage(), transformRequired, element,
                transformRequired.getPlugin());
          }
        }

        if (isCompleteRule) {
          for (EObject result : retrieveTracedElements(element, context)) {
            if (isValidTargetElement(element, result, context)) {
              updateElement(element, result, context);

              if (!isContainementAttached(element, result, context)) {
                attachContainement(element, result, context);
              }
              if (!isRelatedAttached(element, result, context)) {
                attachRelated(element, result, context);
              }
            }
          }

        }
      }

    } catch (Exception e) {
      LogHelper.getInstance().error(e.getClass().getName() + " [" + e.getMessage() + "]", e.getCause(), Messages.Activity_Transformation);
      e.printStackTrace();
      throw e;
    }

  }

  /**
   * @param element
   * @param result
   * @param context
   */
  protected void postTransformElement(EObject element, EObject result, IContext context) {
    TransformationHandlerHelper.getInstance(context).postTransformElement(element, result, context);
  }

  /**
   * @param element
   * @param result
   * @param context
   */
  protected void updateElement(EObject element, EObject result, IContext context) {
    // Nothing here
  }

  /**
   * @param element
   * @param context
   * @return
   */
  public IStatus applyRequired(EObject element, IContext context) {
    Object applyRequired = context.get(ITransitionConstants.TRANSPOSER_APPLY_REQUIRED);
    if ((applyRequired != null) && ((Boolean) applyRequired).booleanValue()) {
      return Status.OK_STATUS;
    }
    return new Status(IStatus.WARNING, Messages.Activity_Transformation, "Apply is not required for this transition");
  }

  protected Collection<EObject> retrieveTracedElements(EObject source, IContext context) {
    return TraceabilityHandlerHelper.getInstance(context).retrieveTracedElements(source, context);
  }

  /**
   * @param element
   * @param result
   * @param context
   * @return
   */
  protected boolean isValidTargetElement(EObject element, EObject result, IContext context) {
    return result != null;
  }

  /**
   * @param element
   * @param result
   * @param context
   * @return
   */
  protected boolean isRegisteredTargetElement(EObject element, EObject result, IContext context) {
    return false;
  }

  /**
   * @param element
   * @param result
   * @param context
   */
  protected void registerTargetElement(EObject sourceElement, EObject targetElement, IContext context) {
    if (targetElement != null) {
      ((Collection) context.get(ITransitionConstants.TRANSFORMED_ELEMENTS)).add(targetElement);
    }
    TraceabilityHandlerHelper.getInstance(context).attachTraceability(sourceElement, targetElement, context);
  }

  /**
   * @param element
   * @param result
   * @param context
   * @return
   */
  protected boolean isRelatedAttached(EObject element, EObject result, IContext context) {
    return false;
  }

  /**
   * @param element
   * @param result
   * @param context
   * @return
   */
  protected boolean isContainementAttached(EObject element, EObject result, IContext context) {
    return result.eContainer() != null;
  }

  /**
   * Default implementation attach the target into the transitioned container
   * @param element
   * @param result
   * @param context
   */
  protected void attachContainement(EObject element, EObject result, IContext context) {

    EObject container = getBestContainer(element, result, context);

    if (container == null) {
      container = getDefaultContainer(element, result, context);
    }

    EStructuralFeature sourceFeature = getSourceContainementFeature(element, result, context);
    EStructuralFeature targetFeature = getTargetContainementFeature(element, result, container, context);

    if (container != null) {
      if (AttachmentHelper.getInstance(context).isApplicable(container.eClass(), targetFeature)) {
        AttachmentHelper.getInstance(context).attachElementByReference(element.eContainer(), container, element, result, (EReference) sourceFeature,
            (EReference) targetFeature);
      }
    }
  }

  /**
   * @param element
   * @param result
   * @param context
   * @return
   */
  protected EObject getBestContainer(EObject element, EObject result, IContext context) {
    EObject bestContainer = null;
    EObject container = getSourceContainer(element, result, context);

    if (container != null) {
      ISelectionContext sContext =
          SelectionContextHandlerHelper.getHandler(context).getSelectionContext(context, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION, element,
              result);

      bestContainer = TransformationHandlerHelper.getInstance(context).getBestTracedElement(container, context, sContext);
    }
    return bestContainer;
  }

  protected EObject getSourceContainer(EObject element, EObject result, IContext context) {
    return element.eContainer();
  }

  public EObject retrieveDefaultContainer(EObject element, EObject result, IContext context) {
    return getDefaultContainer(element, result, context);
  }

  /**
   * @param element
   * @param result
   * @param context
   * @return
   */
  protected EObject getDefaultContainer(EObject element, EObject result, IContext context) {
    return null;
  }

  /**
   * Default implementation can return null if element is not attached
   * @param element
   * @param result
   * @param context
   * @return
   */
  protected EStructuralFeature getSourceContainementFeature(EObject element, EObject result, IContext context) {
    return element.eContainingFeature();
  }

  /**
   * Default implementation can return null if element is not attached
   * @param element
   * @param result
   * @param context
   * @return
   */
  protected EStructuralFeature getTargetContainementFeature(EObject element, EObject result, EObject container, IContext context) {
    return element.eContainingFeature();
  }

  public EStructuralFeature retrieveTargetContainementFeature(EObject element, EObject result, EObject container, IContext context) {
    return getTargetContainementFeature(element, result, container, context);
  }

  protected void attachRelated(EObject element, EObject result, IContext context) {
    // Nothing here
  }

  /**
   * @param element
   * @param context
   */
  protected Collection<EObject> transformElement(EObject element, IContext context) {
    EObject transitioned = transformDirectElement(element, context);
    if (LogHelper.getInstance().hasDebug()) {
      LogHelper.getInstance().debug(
          NLS.bind("Element ''{0}'' {1} is transformed to ''{2}'' {3} [{4}]", new Object[] { LogHelper.getInstance().getText(element),
                                                                                            EObjectLabelProviderHelper.getMetaclassLabel(element, true),
                                                                                            LogHelper.getInstance().getText(transitioned),
                                                                                            EObjectLabelProviderHelper.getMetaclassLabel(transitioned, true),
                                                                                            getClass().getSimpleName() }),
          new Object[] { element, transitioned }, Messages.Activity_Transformation);
    }
    return Collections.singleton(transitioned);
  }

  /**
   * @return
   */
  protected abstract EClass getSourceType();

  /**
   * @return
   */
  public abstract EClass getTargetType(EObject element, IContext context);

  protected EObject transformDirectElement(EObject element, IContext context) {
    EClass clazz = getTargetType(element, context);
    EObject result = null;

    if (clazz != null) {
      EPackage pkg = (EPackage) clazz.eContainer();
      result = pkg.getEFactoryInstance().create(clazz);
    }
    return result;
  }

  public boolean isApplicableOn(EObject element) {
    EClass sourceType = getSourceType();
    return (sourceType != null) && sourceType.isInstance(element);
  }

  /**
   * @param element
   * @param result
   * @param context
   * @return
   */
  protected boolean isFirstAttach(EObject element, EObject result, IContext context) {
    return result.eContainer() == null;
  }

  /**
   * Returns whether the source should be transformed following the given rule
   */
  public IStatus transformRequired(EObject source, IContext context) {

    EClass clazz = getTargetType(source, context);
    if (clazz == null) {
      return new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind("No rule is defined for the element ''{0}'' [{1}]", LogHelper.getInstance()
          .getText(source), source.eClass().getName()));
    }

    ISelectionContext sContext =
        SelectionContextHandlerHelper.getHandler(context).getSelectionContext(context, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION);
    EObject result = TransformationHandlerHelper.getInstance(context).getBestTracedElement(source, context, sContext);

    if (result != null) {
      if (clazz.isInstance(result)) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind("Element ''{0}'' is already transformed [{1}]", LogHelper.getInstance()
            .getText(source), source.eClass().getName()));
      }
    }
    return Status.OK_STATUS;
  }

  public String getName() {
    return this.getClass().getSimpleName();
  }

  /**
   * Register the given element as an incomplete element.
   */
  protected void registerAsIncomplete(EObject element, IContext context) {
    Object rest = context.get(ITransitionConstants.INCOMPLETE_ELEMENTS);
    if (rest == null) {
      context.put(ITransitionConstants.INCOMPLETE_ELEMENTS, new HashSet<EObject>());
    }
    ((Collection) context.get(ITransitionConstants.INCOMPLETE_ELEMENTS)).add(element);
  }

  /**
   * Returns whether element is an incomplete element.
   * 
   * An incomplete element is typically an element with cycle-dependencies
   * Apply rule will proceed in two step if it is an incomplete element.
   * We will perform transformation of all dependencing elements and in a second step, we
   * will attach them.
   * 
   * @param element
   * @param context
   * @return
   */
  protected boolean isIncomplete(EObject element, IContext context) {
    Object rest = context.get(ITransitionConstants.INCOMPLETE_ELEMENTS);
    if (rest != null) {
      return ((Collection) context.get(ITransitionConstants.INCOMPLETE_ELEMENTS)).contains(element);
    }
    return false;
  }

  /**
   * Transposer lack-fix: We need context while computing premises to know if container is in the scope. (find a better way than this... (we will
   * compute scope by IRuleScope before calling transposer premises))
   */
  @Deprecated
  protected IContext currentContext;

  @Deprecated
  public void setCurrentContext(IContext context) {
    currentContext = context;
  }

  @Deprecated
  protected IContext getCurrentContext() {
    return currentContext;
  }

}
