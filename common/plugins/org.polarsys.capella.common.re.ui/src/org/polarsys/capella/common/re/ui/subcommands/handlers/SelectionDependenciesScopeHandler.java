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
package org.polarsys.capella.common.re.ui.subcommands.handlers;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.jface.viewers.StructuredSelection;

import org.polarsys.capella.common.flexibility.properties.loader.PropertiesLoader;
import org.polarsys.capella.common.flexibility.properties.property.PropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.ui.util.ExecutionEventUtil;

/**
 *
 */
public class SelectionDependenciesScopeHandler extends SelectionCommandHandler {

  @Override
  protected String getScope() {
    return "org.polarsys.capella.common.re.retrieve.dependenciesElements";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(Object evaluationContext_p) {
    IEvaluationContext evaluationContext = (IEvaluationContext) evaluationContext_p;
    IRendererContext rendererContext = ExecutionEventUtil.getRendererContext(evaluationContext);
    Object variable = ((EvaluationContext) evaluationContext_p).getDefaultVariable();

    if (!(variable instanceof List)) {
      setBaseEnabled(false);
    } else {
      List selection = (List) variable;
      if (selection.isEmpty()) {
        setBaseEnabled(false);
      } else {

        if (rendererContext == null) {
          setBaseEnabled(false);
        } else {

          String scope = getScope();
          String propertyId = scope;

          if ((scope == null) || scope.isEmpty()) {
            setBaseEnabled(false);
          } else {

            IProperties delegatedProperties = new PropertiesLoader().getProperties(scope);
            Object source = getPropertySource(new StructuredSelection(selection), rendererContext);
            IPropertyContext delegatedContext = new PropertyContext(delegatedProperties, source);
            IProperty property = delegatedProperties.getProperty(propertyId);
            Object current = delegatedContext.getCurrentValue(property);
            setBaseEnabled((current instanceof Collection) && !((Collection) current).isEmpty());
          }

        }
      }
    }
    super.setEnabled(evaluationContext_p);
  }

}
