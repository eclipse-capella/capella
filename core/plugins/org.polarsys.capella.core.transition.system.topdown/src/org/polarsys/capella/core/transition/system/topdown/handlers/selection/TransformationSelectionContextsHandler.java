/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.handlers.selection;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.FinalizableElement;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.options.IOptionsHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.capella.core.transition.common.handlers.selection.DefaultSelectionContextsHandler;
import org.polarsys.capella.core.transition.common.handlers.selection.ISelectionContext;
import org.polarsys.capella.core.transition.common.handlers.selection.SelectionContextHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.core.transition.system.topdown.handlers.level.LevelHandlerHelper;
import org.polarsys.capella.core.transition.system.topdown.handlers.scope.PropertyValuesScopeRetriever;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TransformationSelectionContextsHandler extends DefaultSelectionContextsHandler {

  @Override
  public ISelectionContext getSelectionContext(IContext context_p, String id_p) {
    if (ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION.equals(id_p)) {
      return SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p, ITopDownConstants.SELECTION_CONTEXT__TARGET_ARCHITECTURE);
    }
    return super.getSelectionContext(context_p, id_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISelectionContext getSelectionContext(IContext context_p, String id_p, EObject element_p, EObject result_p) {

    if (ITransitionConstants.SELECTION_CONTEXT__TRANSFORMATION.equals(id_p)) {

      // For some elements, we don't transition them into target architecture, 
      //but in its source architecture level. (interfaces, exchanges items, data or property values)
      boolean transition = true;

      EObject parent = element_p;
      while ((parent != null) && transition) {

        String transitionKind = (String) context_p.get(ITopDownConstants.TRANSITION_KIND);
        IOptionsHandler options = OptionsHandlerHelper.getInstance(context_p);

        if (parent instanceof ExchangeItem) {
          //We put in targetArchi only if ExchangeItem transition or preference is enabled
          transition =
              ITopDownConstants.TRANSITION_TOPDOWN_EXCHANGEITEM.equals(transitionKind)
                  || options.getBooleanValue(context_p, ITopDownConstants.TRANSITION_TOPDOWN, ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM,
                      ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM_DEFAULT.booleanValue());

        } else if (parent instanceof Interface) {
          //We put in targetArchi only if Interface transition or preference is enabled
          transition =
              ITopDownConstants.TRANSITION_TOPDOWN_INTERFACE.equals(transitionKind)
                  || options.getBooleanValue(context_p, ITopDownConstants.TRANSITION_TOPDOWN, ITopDownConstants.OPTIONS_TRANSITION__INTERFACE,
                      ITopDownConstants.OPTIONS_TRANSITION__INTERFACE_DEFAULT.booleanValue());

        } else if ((parent instanceof DataType) || (parent instanceof DataValue) || (parent instanceof Collection)
                   || (parent instanceof org.polarsys.capella.core.data.information.Class)) {

          //We put in targetArchi only if DataType transition or preference is enabled
          transition =
              ITopDownConstants.TRANSITION_TOPDOWN_DATA.equals(transitionKind) || ITopDownConstants.TRANSITION_TOPDOWN_STATEMACHINE.equals(transitionKind)

                  || (ITopDownConstants.TRANSITION_TOPDOWN_EXCHANGEITEM.equals(transitionKind) && options.getBooleanValue(context_p,
                      ITopDownConstants.TRANSITION_TOPDOWN, ITopDownConstants.OPTIONS_TRANSITION__DATATYPE,
                      ITopDownConstants.OPTIONS_TRANSITION__DATATYPE_DEFAULT.booleanValue()))

                  || (options.getBooleanValue(context_p, ITopDownConstants.TRANSITION_TOPDOWN, ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM,
                      ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM_DEFAULT.booleanValue()) && options.getBooleanValue(context_p,
                      ITopDownConstants.TRANSITION_TOPDOWN, ITopDownConstants.OPTIONS_TRANSITION__DATATYPE,
                      ITopDownConstants.OPTIONS_TRANSITION__DATATYPE_DEFAULT.booleanValue()));

        } else if (PropertyValuesScopeRetriever.getInstance(context_p).isProperty(parent)) {
          transition =
              PropertyValuesScopeRetriever.getInstance(context_p).getOwnedElements(context_p).contains(parent)
                  || PropertyValuesScopeRetriever.getInstance(context_p).getAppliedElements(context_p).contains(parent);

        }

        if (!transition) {
          if (TopDownTransformationHelper.getInstance(context_p).isTracedInTarget(element_p, context_p)) {
            transition = true;
          }
        }
        if (parent instanceof FinalizableElement) {
          if (((FinalizableElement) parent).isFinal()) {
            transition = false;
          }
        }

        parent = parent.eContainer();
      }

      ISelectionContext context = LevelHandlerHelper.getInstance(context_p).getSelectionContext(result_p, context_p);
      if (transition) {
        return SelectionContextHandlerHelper.getHandler(context_p).getSelectionContext(context_p, ITopDownConstants.SELECTION_CONTEXT__TARGET_ARCHITECTURE);
      }
      return context;
    }

    return getSelectionContext(context_p, id_p);
  }
}
