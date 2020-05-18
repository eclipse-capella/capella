/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
 * Should not be instantiated outside this plug-in
 */
class CompoundDeleteHelper implements IDeleteHelper {

  private Collection<IDeleteHelper> helpers = null;

  protected Collection<IDeleteHelper> getHelpers() {
    if (helpers == null) {
      helpers = new ArrayList<>();

      //Read extension point looking for instances of IDeleteHelper
      for (IConfigurationElement element : ExtensionPointHelper
          .getConfigurationElements(ModelHandlerPlugin.PLUGIN_ID, "deleteCommandProvider")) {
        try {
          IDeleteHelper helper = (IDeleteHelper) element.createExecutableExtension("class");
          if (helper != null) {
            helpers.add(helper);
          }
        } catch (Exception exception) {
          Logger.getLogger(IReportManagerDefaultComponents.MODEL).error(exception.getMessage(), exception);
        }
      }
    }
    return helpers;
  }

  /**
   * Returns a set with all expandedSelection from helpers
   * {@inheritDoc}
   */
  @Override
  public Set<?> getExpandedSelection(Collection<?> selection) {
    ArrayList<Object> unary = new ArrayList<>(1);
    LinkedList<Object> toVisit = new LinkedList<>();
    HashSet<Object> visited = new HashSet<>();
    toVisit.addAll(selection);

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
  public boolean isDeleteSemanticStructure(EObject sourceObject, EObject linkObject, EStructuralFeature feature) {
    for (IDeleteHelper helper : getHelpers()) {
      if (helper.isDeleteSemanticStructure(sourceObject, linkObject, feature)) {
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
  public boolean isDeleteElement(EObject sourceObject, EObject linkObject, EStructuralFeature feature) {
    for (IDeleteHelper helper : getHelpers()) {
      if (helper.isDeleteElement(sourceObject, linkObject, feature)) {
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
  public Collection<EObject> getAdditionalElements(EObject sourceObject, EObject linkObject, EStructuralFeature feature) {
    Collection<EObject> result = new HashSet<>();
    for (IDeleteHelper helper : getHelpers()) {
      Collection<EObject> additionnalElements = helper.getAdditionalElements(sourceObject, linkObject, feature);
      if ((additionnalElements != null) && !additionnalElements.isEmpty()) {
    	  result.addAll(additionnalElements);
      }
    }
    return result;
  }

  /**
   * Return a composition of all additional commands provided by helpers.
   * (avoid new collection if there is only one helper returning result)
   * {@inheritDoc}
   */
  @Override
  public Collection<Command> getAdditionalCommands(EObject sourceObject, EObject linkObject, EStructuralFeature feature) {
    Collection<Command> result = new HashSet<>();
    for (IDeleteHelper helper : getHelpers()) {
      Collection<Command> additionnalCommands = helper.getAdditionalCommands(sourceObject, linkObject, feature);
      if ((additionnalCommands != null) && !additionnalCommands.isEmpty()) {
    	  result.addAll(additionnalCommands);
      }
    }
    return result;
  }
}
