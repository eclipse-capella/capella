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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.sirius.viewpoint.description.provider.DAnnotationItemProvider;

public class TitleBlockItemProvider extends DAnnotationItemProvider {
  public TitleBlockItemProvider(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  public String getString(String key) {
    return Messages.TitleBlock_type;
  }

}