/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IConverter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.common.tools.api.interpreter.TypedValidation;
import org.eclipse.sirius.common.tools.api.interpreter.ValidationResult;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.polarsys.capella.common.ui.toolkit.browser.category.CategoryRegistry;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;

import com.google.common.base.CaseFormat;

public class CapellaInterpreter implements IInterpreterProvider, IInterpreter, TypedValidation {

  /** The Capella interpreter prefix. */
  public static final String PREFIX = TitleBlockHelper.CAPELLA_PREFIX;

  @Override
  public boolean provides(String expression) {
    return expression != null && expression.startsWith(getPrefix());
  }

  @Override
  public IInterpreter createInterpreter() {
    return this;
  }

  @Override
  public String getPrefix() {
    return PREFIX;
  }

  @Override
  public String getVariablePrefix() {
    return "";
  }

  @Override
  public boolean supportsValidation() {
    return true;
  }

  @Override
  public Collection<IInterpreterStatus> validateExpression(IInterpreterContext context, String expression) {
    return analyzeExpression(context, expression).getStatuses();
  }

  @Override
  public Object evaluate(EObject target, String expression) throws EvaluationException {
    Object result = null;
    EObject resolvedTarget = CapellaAdapterHelper.resolveDescriptorOrBusinessObject(target);
    if (resolvedTarget != null && expression != null && expression.startsWith(PREFIX)) {
      // extract the capella command
      String command = expression.substring(PREFIX.length(), expression.length()).trim();

      // get all the categories for target and match the command name from category with the command in TitleBlock
      Set<ICategory> categories = CategoryRegistry.getInstance().gatherCategories(resolvedTarget).stream()
          .filter(category -> CaseFormat.UPPER_UNDERSCORE
              .to(CaseFormat.LOWER_CAMEL, category.getName().trim().replaceAll(" ", "_")).equals(command))
          .collect(Collectors.toSet());

      if (!categories.isEmpty()) {
        ICategory category = categories.iterator().next();
        if (category != null) {
          // execute the command
          result = category.compute(resolvedTarget);
        }
      }
      else {
        throw new EvaluationException();
      }
    }
    return result;
  }

  @Override
  public IConverter getConverter() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setVariable(String name, Object value) {
    // TODO Auto-generated method stub

  }

  @Override
  public void unSetVariable(String name) {
    // TODO Auto-generated method stub

  }

  @Override
  public Object getVariable(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<String, ?> getVariables() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void clearVariables() {
    // TODO Auto-generated method stub

  }

  @Override
  public void addImport(String dependency) {
    // TODO Auto-generated method stub

  }

  @Override
  public Collection<String> getImports() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void removeImport(String dependency) {
    // TODO Auto-generated method stub

  }

  @Override
  public void clearImports() {
    // TODO Auto-generated method stub

  }

  @Override
  public void setProperty(Object key, Object value) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setModelAccessor(ModelAccessor modelAccessor) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setCrossReferencer(ECrossReferenceAdapter crossReferencer) {
    // TODO Auto-generated method stub

  }

  @Override
  public void activateMetamodels(Collection<MetamodelDescriptor> metamodels) {
    // TODO Auto-generated method stub

  }

  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

  @Override
  public ValidationResult analyzeExpression(IInterpreterContext context, String expression) {
    // TODO Auto-generated method stub
    return null;
  }
}


