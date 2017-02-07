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
package org.polarsys.capella.core.transition.system.topdown.rules.cs;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.transition.common.constants.Messages;
import org.polarsys.capella.core.transition.common.handlers.transformation.TransformationHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.handlers.level.ILevelHandler.Level;
import org.polarsys.capella.core.transition.system.topdown.handlers.level.LevelHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class BlockArchitectureRule extends org.polarsys.capella.core.transition.system.rules.cs.BlockArchitectureRule {

  protected EObject transformDirectElement2(EObject element_p, IContext context_p, EClass clazz_p, Level level_p) {
    //Retrieve the existing architecture if any
    EObject result = null;
    EClass targetType = clazz_p;

    EObject root = TransformationHandlerHelper.getInstance(context_p).getLevelElement(element_p, context_p);
    SystemEngineering target =
        (SystemEngineering) TransformationHandlerHelper.getInstance(context_p).getBestTracedElement(root, context_p,
            CapellamodellerPackage.Literals.SYSTEM_ENGINEERING);
    if (target != null) {
      for (ModellingArchitecture archi : target.getOwnedArchitectures()) {
        if ((targetType != null) && targetType.isInstance(archi)) {
          result = archi;
          break;
        }
      }
    }

    if (result == null) {
      EClass clazz = targetType;
      result = null;

      if (clazz != null) {
        EPackage pkg = (EPackage) clazz.eContainer();
        result = pkg.getEFactoryInstance().create(clazz);
      }

    }

    //Theoretically, this should not be performed here, but log message requires a valid name
    if (result instanceof AbstractNamedElement) {
      //We don't set the name as the source element, we use the metaclass name
      ((AbstractNamedElement) result).setName(EObjectLabelProviderHelper.getMetaclassLabel(result, false) + Messages.TransitionedElement_Suffix);
    }

    LevelHandlerHelper.getInstance(context_p).addScope(level_p, result, context_p);

    return result;
  }

  /**
   * Create source and target architecture
   * {@inheritDoc}
   */
  @Override
  protected Collection<EObject> transformElement(EObject element_p, IContext context_p) {
    Collection<EObject> elements = new LinkedList<EObject>();
    EObject item = null;

    for (Level level : LevelHandlerHelper.getInstance(context_p).getLevels(context_p)) {
      EClass clazz = LevelHandlerHelper.getInstance(context_p).getLevel(context_p, level);
      if (clazz != null) {
        item = transformDirectElement2(element_p, context_p, clazz, level);
        elements.add(item);
      }
    }

    return elements;

  }
}
