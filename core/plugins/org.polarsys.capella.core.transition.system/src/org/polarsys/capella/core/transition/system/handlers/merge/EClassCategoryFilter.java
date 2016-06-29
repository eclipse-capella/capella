/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
    super(context, clazz);
    this.name = name;
    this.clazz = clazz;
  }

  public EClassCategoryFilter(IContext context, EClass clazz, EClass iconClazz) {
    super(context, iconClazz);
    this.clazz = clazz;
  }

  public EClassCategoryFilter(IContext context, EClass clazz, EClass iconClazz, String name) {
    super(context, iconClazz);
    this.clazz = clazz;
    this.name = name;
  }

  @Override
  public boolean keepElement(Object element) {
    return clazz.isInstance(element);
  }
}
