/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

  protected EObject transformDirectElement2(EObject element, IContext context, EClass clazzz, Level level) {
    //Retrieve the existing architecture if any
    EObject result = null;
    EClass targetType = clazzz;

    EObject root = TransformationHandlerHelper.getInstance(context).getLevelElement(element, context);
    SystemEngineering target =
        (SystemEngineering) TransformationHandlerHelper.getInstance(context).getBestTracedElement(root, context,
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

      if (clazz != null && target != null) {
        EPackage pkg = (EPackage) clazz.eContainer();
        result = pkg.getEFactoryInstance().create(clazz);
        //we need to attach it here
        //in some cases where two architectures are created to once,
        //we must retrieve the existing one through the previous 'for'
        target.getOwnedArchitectures().add((ModellingArchitecture)result);
      }
    }

    //Theoretically, this should not be performed here, but log message requires a valid name
    if (result instanceof AbstractNamedElement) {
      //We don't set the name as the source element, we use the metaclass name
      ((AbstractNamedElement) result).setName(EObjectLabelProviderHelper.getMetaclassLabel(result, false) + Messages.TransitionedElement_Suffix);
    }

    LevelHandlerHelper.getInstance(context).addScope(level, result, context);

    return result;
  }

  /**
   * Create source and target architecture
   * {@inheritDoc}
   */
  @Override
  protected Collection<EObject> transformElement(EObject element, IContext context) {
    Collection<EObject> elements = new LinkedList<>();
    EObject item = null;

    for (Level level : LevelHandlerHelper.getInstance(context).getLevels(context)) {
      EClass clazz = LevelHandlerHelper.getInstance(context).getLevel(context, level);
      if (clazz != null) {
        item = transformDirectElement2(element, context, clazz, level);
        elements.add(item);
      }
    }

    return elements;
  }
}
