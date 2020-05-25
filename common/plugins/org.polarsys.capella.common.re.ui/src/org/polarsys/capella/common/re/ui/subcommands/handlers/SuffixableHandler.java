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

package org.polarsys.capella.common.re.ui.subcommands.handlers;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.ui.util.ExecutionEventUtil;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.attributes.AttributesHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class SuffixableHandler extends SubCommandHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    ISelection selection = getSelection(event);
    if(selection instanceof IStructuredSelection){
      IStructuredSelection structuredSelection = (IStructuredSelection)selection;
      IRendererContext rcontext = ExecutionEventUtil.getRendererContext(event);
      IContext context = (IContext) rcontext.getPropertyContext().getSource();
      
      for (Object selectedItem : structuredSelection.toList()) {
        AttributesHandlerHelper.getInstance(context).setManualSuffixable((EObject) selectedItem,
            !AttributesHandlerHelper.getInstance(context).isSuffixable(selectedItem, context), context);
      }
      IProperty property = rcontext.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIXES);
      rcontext.getPropertyContext().setCurrentValue(property, rcontext.getPropertyContext().getCurrentValue(property));      
    }
    return null;
  }

  @Override
  public void setEnabled(Object evaluationContext) {
    Collection<Object> selectedObjects = getSelectedObjects((IEvaluationContext) evaluationContext);
    if (selectedObjects.isEmpty()) {
      setBaseEnabled(false);
    } else {
      if (selectedObjects.iterator().next() instanceof CatalogElementLink) {
        setBaseEnabled(true);
        super.setEnabled(evaluationContext);
        return;
      }

      IRendererContext rendererContext = ExecutionEventUtil.getRendererContext((IEvaluationContext) evaluationContext);
      if (rendererContext == null) {
        setBaseEnabled(false);
      } else {

        Collection scopeElements =
            (Collection) rendererContext.getPropertyContext().getCurrentValue(
                rendererContext.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__SCOPE));

        Collection<Object> values = new HashSet<Object>(selectedObjects);
          if (scopeElements != null) {
            values.removeAll(scopeElements);
          }
          setBaseEnabled(values.isEmpty());
      }
    }
  }
}
