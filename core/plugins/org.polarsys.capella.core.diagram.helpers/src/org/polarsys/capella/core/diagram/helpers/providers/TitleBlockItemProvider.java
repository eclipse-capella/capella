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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.sirius.viewpoint.description.provider.DAnnotationItemProvider;

public class TitleBlockItemProvider extends DAnnotationItemProvider {
  public TitleBlockItemProvider(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }
  public String getString(String key)
  {
    return "Title Block";
  }
}