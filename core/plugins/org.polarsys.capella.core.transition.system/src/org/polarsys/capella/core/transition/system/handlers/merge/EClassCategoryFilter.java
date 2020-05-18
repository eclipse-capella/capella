/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.handlers.merge;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class EClassCategoryFilter extends EObjectCategoryFilter {

  EClass clazz;

  public EClassCategoryFilter(IContext context, EClass clazz) {
    super(context, clazz);
    this.clazz = clazz;
  }

  public EClassCategoryFilter(IContext context, EClass clazz, String name) {
    super(context, clazz, name);
    this.name = name;
    this.clazz = clazz;
  }
  
  public EClassCategoryFilter(IContext context, EClass clazz, String name, String description) {
    super(context, clazz, name, description);
    this.name = name;
    this.clazz = clazz;
  }

  public EClassCategoryFilter(IContext context, EClass clazz, EClass iconClazz) {
    super(context, iconClazz);
    this.clazz = clazz;
  }

  public EClassCategoryFilter(IContext context, EClass clazz, EClass iconClazz, String name) {
    super(context, iconClazz, name);
    this.clazz = clazz;
    this.name = name;
  }

  @Override
  public boolean keepElement(Object element) {
    return clazz.isInstance(element);
  }
}
