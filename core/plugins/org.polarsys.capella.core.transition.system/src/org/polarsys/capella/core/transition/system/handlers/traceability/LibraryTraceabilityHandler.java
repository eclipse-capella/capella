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
  public void attachTraceability(EObject sourceElement_p, EObject targetElement_p, IContext context_p) {
    //Nothing here. we don't transform, so we don't attach traceability
  }

  public static boolean isLibraryElement(EObject source_p, IContext context_p) {

    IModel sourceModel = null;
    if (!context_p.exists(TRANSITION_SOURCE_ROOT_MODEL)) {
      EObject root = (EObject) context_p.get(ITransitionConstants.TRANSITION_SOURCE_ROOT);
      sourceModel = ILibraryManager.INSTANCE.getModel(root);
      context_p.put(TRANSITION_SOURCE_ROOT_MODEL, sourceModel);
    } else {
      sourceModel = (IModel) context_p.get(TRANSITION_SOURCE_ROOT_MODEL);
    }

    IModel targetModel = null;
    if (!context_p.exists(TRANSITION_TARGET_ROOT_MODEL)) {
      EObject root = (EObject) context_p.get(ITransitionConstants.TRANSITION_TARGET_ROOT);
      targetModel = ILibraryManager.INSTANCE.getModel(root);
      context_p.put(TRANSITION_TARGET_ROOT_MODEL, targetModel);
    } else {
      targetModel = (IModel) context_p.get(TRANSITION_TARGET_ROOT_MODEL);
    }

    IModel transformedModel = null;
    if (!context_p.exists(TRANSITION_TRANSFORMATION_ROOT_MODEL)) {
      EObject root = (EObject) context_p.get(ITransitionConstants.TRANSFORMATION_TARGET_ROOT);
      transformedModel = ILibraryManager.INSTANCE.getModel(root);
      context_p.put(TRANSITION_TRANSFORMATION_ROOT_MODEL, transformedModel);
    } else {
      transformedModel = (IModel) context_p.get(TRANSITION_TRANSFORMATION_ROOT_MODEL);
    }

    IModel currentModel = ILibraryManager.INSTANCE.getModel(source_p);
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
  public Collection<EObject> retrieveTracedElements(EObject source_p, IContext context_p) {
    if (isLibraryElement(source_p, context_p)) {
      return Collections.singleton(source_p);
    }
    return Collections.emptyList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<EObject> retrieveSourceElements(EObject source_p, IContext context_p) {
    if (isLibraryElement(source_p, context_p)) {
      return Collections.singleton(source_p);
    }
    return Collections.emptyList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Deprecated
  public Collection<EObject> retrieveTracedElements(EObject source_p, IContext context_p, EClass clazz_p) {
    if (isLibraryElement(source_p, context_p)) {
      return Collections.singleton(source_p);
    }
    return Collections.emptyList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Deprecated
  public String getId(EObject element_p, IContext context_p) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Deprecated
  public boolean isTraced(EObject element_p, IContext context_p) {
    if (isLibraryElement(element_p, context_p)) {
      return true;
    }
    return false;
  }

}
