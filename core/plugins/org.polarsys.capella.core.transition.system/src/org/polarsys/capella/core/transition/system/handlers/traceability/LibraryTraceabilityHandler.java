/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.system.handlers.traceability;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.traceability.ITraceabilityHandler2;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class LibraryTraceabilityHandler implements ITraceabilityHandler2 {

  public static final String TRANSITION_SOURCE_ROOT_MODEL = "TRANSITION_SOURCE_ROOT_MODEL";
  public static final String TRANSITION_TARGET_ROOT_MODEL = "TRANSITION_TARGET_ROOT_MODEL";
  public static final String TRANSITION_TRANSFORMATION_ROOT_MODEL = "TRANSITION_TRANSFORMATION_ROOT_MODEL";

  /**
   * {@inheritDoc}
   */
  @Override
  public void attachTraceability(EObject sourceElement, EObject targetElement, IContext context) {
    //Nothing here. we don't transform, so we don't attach traceability
  }

  public static boolean isLibraryElement(EObject source, IContext context) {

    IModel sourceModel = null;
    if (!context.exists(TRANSITION_SOURCE_ROOT_MODEL)) {
      EObject root = (EObject) context.get(ITransitionConstants.TRANSITION_SOURCE_ROOT);
      sourceModel = ILibraryManager.INSTANCE.getModel(root);
      context.put(TRANSITION_SOURCE_ROOT_MODEL, sourceModel);
    } else {
      sourceModel = (IModel) context.get(TRANSITION_SOURCE_ROOT_MODEL);
    }

    IModel targetModel = null;
    if (!context.exists(TRANSITION_TARGET_ROOT_MODEL)) {
      EObject root = (EObject) context.get(ITransitionConstants.TRANSITION_TARGET_ROOT);
      targetModel = ILibraryManager.INSTANCE.getModel(root);
      context.put(TRANSITION_TARGET_ROOT_MODEL, targetModel);
    } else {
      targetModel = (IModel) context.get(TRANSITION_TARGET_ROOT_MODEL);
    }

    IModel transformedModel = null;
    if (!context.exists(TRANSITION_TRANSFORMATION_ROOT_MODEL)) {
      EObject root = (EObject) context.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
      transformedModel = ILibraryManager.INSTANCE.getModel(root);
      context.put(TRANSITION_TRANSFORMATION_ROOT_MODEL, transformedModel);
    } else {
      transformedModel = (IModel) context.get(TRANSITION_TRANSFORMATION_ROOT_MODEL);
    }

    IModel currentModel = ILibraryManager.INSTANCE.getModel(source);
    if (currentModel != null) {
      if (!currentModel.equals(sourceModel) && !currentModel.equals(targetModel) && !currentModel.equals(transformedModel)) {
        return true;
      }
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<EObject> retrieveTracedElements(EObject source, IContext context) {
    if (isLibraryElement(source, context)) {
      return Collections.singleton(source);
    }
    return Collections.emptyList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<EObject> retrieveSourceElements(EObject source, IContext context) {
    if (isLibraryElement(source, context)) {
      return Collections.singleton(source);
    }
    return Collections.emptyList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Deprecated
  public Collection<EObject> retrieveTracedElements(EObject source, IContext context, EClass clazz) {
    if (isLibraryElement(source, context)) {
      return Collections.singleton(source);
    }
    return Collections.emptyList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Deprecated
  public String getId(EObject element, IContext context) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Deprecated
  public boolean isTraced(EObject element, IContext context) {
    if (isLibraryElement(element, context)) {
      return true;
    }
    return false;
  }

}
