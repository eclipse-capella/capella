/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.interfaces.generateInterfaces;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.projection.common.CommonRule;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.tiger.ITransfo;

public class Rule_Component extends CommonRule {

  public Rule_Component() {
    super(CsPackage.Literals.COMPONENT, CsPackage.Literals.INTERFACE);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element, ITransfo transfo) {
    // Nothing to do
  }

  @Override
  protected void doAddContainer(EObject element, List<EObject> result) {
    // Nothing to do
  }

  @Override
  public void update_(EObject element_p, ITransfo transfo_p) {
    // nothing to do here
  }

  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    return Status.OK_STATUS;
  }

  @Override
  protected boolean transformIsRequired(EObject element_p, ITransfo transfo_p) {
    return true;
  }

  @Override
  public boolean requireTransformation(EObject element, ITransfo transfo) {
    return true;
  }

  @Override
  protected void doGoDeep(EObject element, List<EObject> result) {
    Component component = (Component) element;
    result.addAll(((Component)element).getContainedComponentPorts());
    result.addAll(ComponentExt.getAllSubUsedComponents(component));
  }

  @Override
  protected Object transformElement(EObject element_p, ITransfo transfo_p) {
    return null;
  }

}