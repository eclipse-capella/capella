/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.providers;

import org.eclipse.emf.edit.provider.IItemProviderDecorator;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.polarsys.capella.core.data.gen.edit.decorators.CustomDecoratorAdapterFactory;
import org.polarsys.capella.core.data.gen.edit.decorators.ForwardingItemProviderAdapterDecorator;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;

public class TitleBlockItemDecoratorAdapterFactory extends CustomDecoratorAdapterFactory {
  public TitleBlockItemDecoratorAdapterFactory() {
    super(new TitleBlockAdapterFactory());
  }

  @Override
  protected IItemProviderDecorator createItemProviderDecorator(Object target, Object Type) {
    if (target instanceof DAnnotation && TitleBlockHelper.isTitleBlockAnnotation((DAnnotation) target)) {
      return new TitleBlockItemProviderDecorator(this);
    }
    return new ForwardingItemProviderAdapterDecorator(this);
  }

  public boolean isFactoryForType(Object type) {
    return decoratedAdapterFactory.isFactoryForType(type);
  }
}