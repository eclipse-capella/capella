/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.libraries.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.shared.id.handler.IScope;

public class ScopeModelWrapper implements IScope {

  IModel _model;

  public ScopeModelWrapper(IModel model_p) {
    _model = model_p;
  }

  @Override
  public List<Resource> getResources() {
    if (_model instanceof CapellaModel) {
    	return Collections.unmodifiableList(new ArrayList<Resource>(((CapellaModel) _model).getEditingDomain().getResourceSet().getResources()));
    }
    return Collections.emptyList();
  }
}
