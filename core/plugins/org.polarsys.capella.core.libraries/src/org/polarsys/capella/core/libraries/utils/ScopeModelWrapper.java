/**
 * Copyright (c) THALES, 2011. All rights reserved.
 */
package org.polarsys.capella.core.libraries.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.libraries.IAbstractModel;
import org.polarsys.capella.shared.id.handler.IScope;

public class ScopeModelWrapper implements IScope {

  IAbstractModel _model;

  public ScopeModelWrapper(IAbstractModel model_p) {
    _model = model_p;

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Resource> getResources() {
    ArrayList<Resource> resources = new ArrayList<Resource>();
    for (EObject object : _model.getContents()) {
      if (object != null) {
        Resource resource = object.eResource();
        if (resource != null) {
          resources.add(resource);
        }
      }
    }

    return resources;
  }

}
