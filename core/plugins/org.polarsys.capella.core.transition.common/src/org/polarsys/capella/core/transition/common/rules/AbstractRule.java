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

import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
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
  public List<EObject> retrieveRootElements(EObject source_p, IContext context_p) {
    setCurrentContext(context_p);
    List<EObject> result = new ArrayList<EObject>();
    retrieveRootElement(source_p, result, context_p);
    return result;
  }

  /**
   * @see org.polarsys.capella.core.transition.common.rules.IRuleScope#retrieveContainer(java.lang.Object, java.lang.Object)
   */
  public List<EObject> retrieveContainers(EObject source_p, IContext context_p) {
    setCurrentContext(context_p);
    List<EObject> result = new ArrayList<EObject>();
    retrieveContainer(source_p, result, context_p);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  public List<EObject> retrieveRequiredElements(EObject element_p, IContext context_p) {
    setCurrentContext(context_p);
    List<EObject> result = new ArrayList<EObject>();
    retrieveRequired(element_p, result, context_p);
    return result;
  }

  /**
   * @see org.polarsys.capella.core.transition.common.rules.IRuleScope#retrieveRelatedElements(java.lang.Object, java.lang.Object)
   */
  public List<EObject> retrieveRelatedElements(EObject element_p, IContext context_p) {
    setCurrentContext(context_p);
    List<EObject> result = new ArrayList<EObject>();
    retrieveCurrent(element_p, result, context_p);
    retrieveGoDeep(element_p, result, context_p);

    return result;
  }

  protected void retrieveCurrent(EObject source_p, List<EObject> result_p, IContext context_p) {
    setCurrentContext(context_p);
    result_p.add(source_p);
  }

  protected void retrieveRequired(EObject element_p, List<EObject> result_p, IContext context_p) {
    // Nothing here
  }

  protected void retrieveRootElement(EObject source_p, List<EObject> result_p, IContext context_p) {
    EObject container = TransformationHandlerHelper.getInstance(context_p).getLevelElement(source_p, context_p);
    if (container != null) {
      result_p.add(container);
    }
  }

  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    // Nothing here
  }

  protected void retrieveContainer(EObject element_p, List<EObject> result_p, IContext context_p) {
    EObject container = element_p.eContainer();
    if (container != null) {
      result_p.add(container);
    }
  }

  /**
   * ------------ Premises computing ------------
   */

  public List<IPremise> getPremises(EObject element_p) {
    ArrayList<IPremise> needed = new ArrayList<IPremise>();
    ArrayList<EObject> previous = new ArrayList<EObject>();
    premicesContainement(element_p, needed);
    premicesRelated(element_p, needed);
    previous.add(element_p);

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
                    LogHelper.getInstance().getText(element_p)), Messages.Activity_Transformation);
          }
        }
      }
    }

    if (LogHelper.getInstance().hasDebug()) {
      LogHelper.getInstance().debug(NLS.bind("Premises of ''{0}'' .", LogHelper.getInstance().getText(element_p)), previous, Messages.Activity_Transformation);
    }

    return needed;
  }

  /**
   * @param element_p
   * @param needed_p
   */
  protected void premicesRelated(EObject element_p, ArrayList<IPremise> needed_p) {
    // Nothing here
  }

  /**
   * @param element_p
   * @param needed_p
   */
  protected void premicesContainement(EObject element_p, ArrayList<IPremise> needed_p) {
    IPremise premise = createDefaultContainementPremice(element_p);
    if (premise != null) {
      needed_p.add(premise);
    }
  }

  /**
   * Return a premise for the container (by default, if container isn't in the scope of transformation, we find recursively a container in the scope)
   */
  protected ContainmentPremise<EObject> createDefaultContainementPremice(EObject eObject_p) {
    EObject parent = eObject_p.eContainer();
    IContext context = getCurrentContext();
    if (context != null) {
      IScopeHandler scope = ScopeHandlerHelper.getInstance(context);
      if (scope != null) {
        parent = eObject_p.eContainer();
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
   * Return a premise for any elements by eObject.eGet(reference_p) available in the scope
   */
  protected List<PrecedencePremise<EObject>> createDefaultPrecedencePremices(Collection<EObject> referencedElements_p, String name_p) {
    ArrayList<PrecedencePremise<EObject>> premices = new ArrayList<PrecedencePremise<EObject>>();

    IContext context = getCurrentContext();
    if (context != null) {
      IScopeHandler scope = ScopeHandlerHelper.getInstance(context);
      if (scope != null) {
        for (EObject obj : referencedElements_p) {
          if (scope.isInScope(obj, context)) {
            premices.add(createPrecedencePremice(obj, name_p));
          }
        }
      }
    }
    return premices;
  }

  /**
   * Return a premise for any elements by eObject.eGet(reference_p) available in the scope
   */
  protected List<PrecedencePremise<EObject>> createDefaultCriticalPremices(Collection<EObject> referencedElements_p, String name_p) {
    ArrayList<PrecedencePremise<EObject>> premices = new ArrayList<PrecedencePremise<EObject>>();

    IContext context = getCurrentContext();
    if (context != null) {
      IScopeHandler scope = ScopeHandlerHelper.getInstance(context);
      if (scope != null) {
        for (EObject obj : referencedElements_p) {
          if (scope.isInScope(obj, context)) {
            premices.add(createCriticalPremice(obj, name_p));
          }
        }
      }
    }
    return premices;
  }

  /**
   * Return a premise for any elements by eObject.eGet(reference_p) available in the scope
   */
  public List<PrecedencePremise<EObject>> createDefaultPrecedencePremices(EObject eObject_p, EReference reference_p) {
    ArrayList<PrecedencePremise<EObject>> premices = new ArrayList<PrecedencePremise<EObject>>();

    IContext context = getCurrentContext();
    if (context != null) {
      IScopeHandler scope = ScopeHandlerHelper.getInstance(context);
      if (scope != null) {

        if (reference_p.isMany()) {
          for (Object obj : ((EList) eObject_p.eGet(reference_p))) {
            if ((obj != null) && (obj instanceof EObject)) {
              if (scope.isInScope((EObject) obj, context)) {
                premices.add(createPrecedencePremice((EObject) obj, reference_p.getName()));
              }
            }
          }
        } else {
          Object obj = eObject_p.eGet(reference_p);
          if ((obj != null) && (obj instanceof EObject)) {
            if (scope.isInScope((EObject) obj, context)) {
              premices.add(createPrecedencePremice((EObject) obj, reference_p.getName()));
            }
          }
        }
      }
    }
    return premices;
  }

  /**
   * Return critical premises for all targeted elements from the given reference_p
   * @param eObject_p
   * @param reference_p
   * @return
   */
  public List<PrecedencePremise<EObject>> createDefaultCriticalPremices(EObject eObject_p, EReference reference_p) {
    List<PrecedencePremise<EObject>> premices = createDefaultPrecedencePremices(eObject_p, reference_p);
    for (PrecedencePremise<EObject> premice : premices) {
      premice.setCritical(true);
    }
    return premices;
  }

  protected PrecedencePremise<EObject> createPrecedencePremice(EObject eObject_p, String string_p) {
    PrecedencePremise<EObject> premice = null;
    premice = new PrecedencePremise<EObject>(eObject_p, string_p);
    return premice;
  }

  protected ContainmentPremise<EObject> createContainmentPremice(EObject eObject_p) {
    ContainmentPremise<EObject> premice = null;
    premice = new ContainmentPremise<EObject>(eObject_p);
    return premice;
  }

  protected ContainmentPremise<EObject> createCriticalPremice(EObject eObject_p, String string_p) {
    ContainmentPremise<EObject> premice = null;
    premice = new ContainmentPremise<EObject>(eObject_p);
    return premice;
  }

  /**
   * ------------ Apply ------------
   * (handle both, complete and incomplete rules)
   * in an incomplete rule, we perform only transformation, and we attach them when the complete rule will be triggered
   */

  public void apply(EObject element_p, IContext context_p) throws Exception {

    Boolean isComplete = ((Boolean) context_p.get(ITransitionConstants.TRANSPOSER_APPLY_IS_COMPLETE));
    boolean isCompleteRule = isComplete == null ? true : isComplete.booleanValue();
    if (!isCompleteRule) {
      registerAsIncomplete(element_p, context_p);
    }

    try {
      if (applyRequired(element_p, context_p).isOK()) {

        if (!isCompleteRule || (isCompleteRule && !isIncomplete(element_p, context_p))) {

          IStatus transformRequired = transformRequired(element_p, context_p);

          if (transformRequired.isOK()) {
            for (EObject result : transformElement(element_p, context_p)) {
              if (isValidTargetElement(element_p, result, context_p)) {
                if (!isRegisteredTargetElement(element_p, result, context_p)) {
                  registerTargetElement(element_p, result, context_p);
                }
              }
            }
          } else {
            LogHelper.getInstance().log("Element is not transitioned: " + transformRequired.getMessage(), transformRequired, element_p,
                transformRequired.getPlugin());
          }
        }

        if (isCompleteRule) {
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

    } catch (Exception e) {
      LogHelper.getInstance().error(e.getClass().getName() + " [" + e.getMessage() + "]", e.getCause(), Messages.Activity_Transformation);
      e.printStackTrace();
      throw e;
    }

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
   * @param context_p
   * @return
   */
  public IStatus applyRequired(EObject element_p, IContext context_p) {
    Object applyRequired = context_p.get(ITransitionConstants.TRANSPOSER_APPLY_REQUIRED);
    if ((applyRequired != null) && ((Boolean) applyRequired).booleanValue()) {
      return Status.OK_STATUS;
    }
    return new Status(IStatus.WARNING, Messages.Activity_Transformation, "Apply is not required for this transition");
  }

  protected Collection<EObject> retrieveTracedElements(EObject source_p, IContext context_p) {
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
   * @return
   */
  protected boolean isRegisteredTargetElement(EObject element_p, EObject result_p, IContext context_p) {
    return false;
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   */
  protected void registerTargetElement(EObject sourceElement, EObject targetElement_p, IContext context_p) {
    if (targetElement_p != null) {
      ((Collection) context_p.get(ITransitionConstants.TRANSFORMED_ELEMENTS)).add(targetElement_p);
    }
    TraceabilityHandlerHelper.getInstance(context_p).attachTraceability(sourceElement, targetElement_p, context_p);
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
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  protected boolean isContainementAttached(EObject element_p, EObject result_p, IContext context_p) {
    return result_p.eContainer() != null;
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

    EStructuralFeature sourceFeature = getSourceContainementFeature(element_p, result_p, context_p);
    EStructuralFeature targetFeature = getTargetContainementFeature(element_p, result_p, container, context_p);

    if (container != null) {
      if (AttachmentHelper.getInstance(context_p).isApplicable(container.eClass(), targetFeature)) {
        AttachmentHelper.getInstance(context_p).attachElementByReference(element_p.eContainer(), container, element_p, result_p, (EReference) sourceFeature,
            (EReference) targetFeature);
      }
    }
  }

  public EObject _getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    return getBestContainer(element_p, result_p, context_p);
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
      ISelectionContext sContext =
          SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION, element_p,
              result_p);

      bestContainer = TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(container, context_p, sContext);
    }
    return bestContainer;
  }

  protected EObject getSourceContainer(EObject element_p, EObject result_p, IContext context_p) {
    return element_p.eContainer();
  }

  public EObject _getDefaultContainer(EObject element_p, EObject result_p, IContext context_p) {
    return getDefaultContainer(element_p, result_p, context_p);
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

  public EStructuralFeature _getSourceContainementFeature(EObject element_p, EObject result_p, IContext context_p) {
    return getSourceContainementFeature(element_p, result_p, context_p);
  }

  /**
   * Default implementation can return null if element_p is not attached
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  protected EStructuralFeature getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return element_p.eContainingFeature();
  }

  public EStructuralFeature _getTargetContainementFeature(EObject element_p, EObject result_p, EObject container_p, IContext context_p) {
    return getTargetContainementFeature(element_p, result_p, container_p, context_p);
  }

  protected void attachRelated(EObject element_p, EObject result_p, IContext context_p) {
    // Nothing here
  }

  /**
   * @param element_p
   * @param context_p
   */
  protected Collection<EObject> transformElement(EObject element_p, IContext context_p) {
    EObject transitioned = transformDirectElement(element_p, context_p);
    if (LogHelper.getInstance().hasDebug()) {
      LogHelper.getInstance().debug(
          NLS.bind("Element ''{0}'' {1} is transformed to ''{2}'' {3} [{4}]", new Object[] { LogHelper.getInstance().getText(element_p),
                                                                                            EObjectLabelProviderHelper.getMetaclassLabel(element_p, true),
                                                                                            LogHelper.getInstance().getText(transitioned),
                                                                                            EObjectLabelProviderHelper.getMetaclassLabel(transitioned, true),
                                                                                            getClass().getSimpleName() }),
          new Object[] { element_p, transitioned }, Messages.Activity_Transformation);
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
  public abstract EClass getTargetType(EObject element_p, IContext context_p);

  protected EObject transformDirectElement(EObject element_p, IContext context_p) {
    EClass clazz = getTargetType(element_p, context_p);
    EObject result = null;

    if (clazz != null) {
      EPackage pkg = (EPackage) clazz.eContainer();
      result = pkg.getEFactoryInstance().create(clazz);
    }
    return result;
  }

  public boolean isApplicableOn(EObject element_p) {
    EClass sourceType = getSourceType();
    return (sourceType != null) && sourceType.isInstance(element_p);
  }

  /**
   * @param element_p
   * @param result_p
   * @param context_p
   * @return
   */
  protected boolean isFirstAttach(EObject element_p, EObject result_p, IContext context_p) {
    return result_p.eContainer() == null;
  }

  /**
   * Returns whether the source should be transformed following the given rule
   */
  public IStatus transformRequired(EObject source_p, IContext context_p) {

    EClass clazz = getTargetType(source_p, context_p);
    if (clazz == null) {
      return new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind("No rule is defined for the element ''{0}'' [{1}]", LogHelper.getInstance()
          .getText(source_p), source_p.eClass().getName()));
    }

    ISelectionContext sContext =
        SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p, ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION);
    EObject result = TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(source_p, context_p, sContext);

    if (result != null) {
      if (clazz.isInstance(result)) {
        return new Status(IStatus.WARNING, Messages.Activity_Transformation, NLS.bind("Element ''{0}'' is already transformed [{1}]", LogHelper.getInstance()
            .getText(source_p), source_p.eClass().getName()));
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
  protected void registerAsIncomplete(EObject element_p, IContext context_p) {
    Object rest = context_p.get(ITransitionConstants.INCOMPLETE_ELEMENTS);
    if (rest == null) {
      context_p.put(ITransitionConstants.INCOMPLETE_ELEMENTS, new HashSet<EObject>());
    }
    ((Collection) context_p.get(ITransitionConstants.INCOMPLETE_ELEMENTS)).add(element_p);
  }

  /**
   * Returns whether element is an incomplete element.
   * 
   * An incomplete element is typically an element with cycle-dependencies
   * Apply rule will proceed in two step if it is an incomplete element.
   * We will perform transformation of all dependencing elements and in a second step, we
   * will attach them.
   * 
   * @param element_p
   * @param context_p
   * @return
   */
  protected boolean isIncomplete(EObject element_p, IContext context_p) {
    Object rest = context_p.get(ITransitionConstants.INCOMPLETE_ELEMENTS);
    if (rest != null) {
      return ((Collection) context_p.get(ITransitionConstants.INCOMPLETE_ELEMENTS)).contains(element_p);
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
  protected void setCurrentContext(IContext context_p) {
    currentContext = context_p;
  }

  @Deprecated
  protected IContext getCurrentContext() {
    return currentContext;
  }

}
