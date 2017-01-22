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
package org.polarsys.capella.core.model.handler.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.handler.ModelHandlerPlugin;

/**
 * Default implementation of the delete command helper, using extension mechanism 
 *
 * Should not be instantiated outside this plugin
 */
class CompoundDeleteHelper implements IDeleteHelper {

  private Collection<IDeleteHelper> _helpers = null;

  protected Collection<IDeleteHelper> getHelpers() {
    if (_helpers == null) {
      _helpers = new ArrayList<IDeleteHelper>();

      //Read extension point looking for instances of IDeleteHelper
      for (IConfigurationElement element : ExtensionPointHelper
          .getConfigurationElements(ModelHandlerPlugin.PLUGIN_ID, "deleteCommandProvider")) {
        try {
          IDeleteHelper helper = (IDeleteHelper) element.createExecutableExtension("class");
          if (helper != null) {
            _helpers.add(helper);
          }
        } catch (Exception exception_p) {
          Logger.getLogger(IReportManagerDefaultComponents.MODEL).error(exception_p.getMessage(), exception_p);
        }
      }
    }
    return _helpers;
  }

  /**
   * Returns a set with all expandedSelection from helpers
   * {@inheritDoc}
   */
  @Override
  public Set<?> getExpandedSelection(Collection<?> selection_p) {
    ArrayList<Object> unary = new ArrayList<Object>(1);
    LinkedList<Object> toVisit = new LinkedList<Object>();
    HashSet<Object> visited = new HashSet<Object>();
    toVisit.addAll(selection_p);

    while (!toVisit.isEmpty()) {
      Object item = toVisit.removeFirst();
      if (!visited.contains(item)) {
        visited.add(item);

        if (unary.size() == 1) {
          unary.set(0, item);
        } else if (unary.size() > 1) {
          unary.clear();
          unary.add(item);
        } else {
          unary.add(item);
        }

        for (IDeleteHelper helper : getHelpers()) {
          Collection<?> result = helper.getExpandedSelection(unary);
          if (result != null) {
            toVisit.addAll(result);
            //A little backward compatibility checking
            if (unary.size() > 1) {
              toVisit.addAll(unary);
            }
          }
        }
      }
    }
    return visited;
  }

  /**
   * {@inheritDoc}
   * If one helper returns true, compound helper will return true
   */
  @Override
  public boolean isDeleteSemanticStructure(EObject sourceObject_p, EObject linkObject_p, EStructuralFeature feature_p) {
    for (IDeleteHelper helper : getHelpers()) {
      if (helper.isDeleteSemanticStructure(sourceObject_p, linkObject_p, feature_p)) {
        return true;
      }
    }
    return false;
  }

  /**
   * {@inheritDoc}
   * If one helper returns true, compound helper will return true
   */
  @Override
  public boolean isDeleteElement(EObject sourceObject_p, EObject linkObject_p, EStructuralFeature feature_p) {
    for (IDeleteHelper helper : getHelpers()) {
      if (helper.isDeleteElement(sourceObject_p, linkObject_p, feature_p)) {
        return true;
      }
    }
    return false;
  }

  /**
   * {@inheritDoc}
   * Return a composition of all additional elements provided by helpers.
   * (avoid new collection if there is only one helper returning result)
   */
  @Override
  public Collection<EObject> getAdditionalElements(EObject sourceObject_p, EObject linkObject_p, EStructuralFeature feature_p) {
    Collection<EObject> init = null;
    boolean isFirstInitialization = false;
    for (IDeleteHelper helper : getHelpers()) {
      Collection<EObject> additionnalElements = helper.getAdditionalElements(sourceObject_p, linkObject_p, feature_p);
      if ((additionnalElements != null) && !additionnalElements.isEmpty()) {
        if (init == null) {
          init = additionnalElements;
          isFirstInitialization = true;

        } else {
          if (isFirstInitialization) {
            init = new HashSet<EObject>(init);
          }
          init.addAll(additionnalElements);
        }
      }
    }
    return init;
  }

  /**
   * Return a composition of all additional commands provided by helpers.
   * (avoid new collection if there is only one helper returning result)
   * {@inheritDoc}
   */
  @Override
  public Collection<Command> getAdditionalCommands(EObject sourceObject_p, EObject linkObject_p, EStructuralFeature feature_p) {
    Collection<Command> init = null;
    boolean isFirstInitialization = false;
    for (IDeleteHelper helper : getHelpers()) {
      Collection<Command> additionnalCommands = helper.getAdditionalCommands(sourceObject_p, linkObject_p, feature_p);
      if ((additionnalCommands != null) && !additionnalCommands.isEmpty()) {
        if (init == null) {
          init = additionnalCommands;
          isFirstInitialization = true;

        } else {
          if (isFirstInitialization) {
            init = new HashSet<Command>(init);
          }
          init.addAll(additionnalCommands);
        }
      }
    }
    return init;
  }
}
