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