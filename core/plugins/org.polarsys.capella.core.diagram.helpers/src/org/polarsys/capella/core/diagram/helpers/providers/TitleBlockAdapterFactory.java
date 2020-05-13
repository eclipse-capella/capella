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
package org.polarsys.capella.core.diagram.helpers.providers;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.provider.DescriptionItemProviderAdapterFactory;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;

public class TitleBlockAdapterFactory extends DescriptionItemProviderAdapterFactory {

  protected TitleBlockItemProvider dTitleBlockItemProvider;

  public Adapter createTitleBlockItemProvider() {
    if (dTitleBlockItemProvider == null) {
      dTitleBlockItemProvider = new TitleBlockItemProvider(this);
    }
    return dTitleBlockItemProvider;
  }

  @Override
  public Adapter createAdapter(Notifier target) {
    if (target instanceof DAnnotation && TitleBlockHelper.isTitleBlockAnnotation((DAnnotation) target)) {
      return createTitleBlockItemProvider();
    }
    return modelSwitch.doSwitch((EObject) target);
  }
}
