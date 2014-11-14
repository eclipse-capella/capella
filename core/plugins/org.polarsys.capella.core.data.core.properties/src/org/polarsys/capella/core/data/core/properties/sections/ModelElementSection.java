/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.core.properties.sections;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.model.handler.provider.IReadOnlySectionHandler;
import org.polarsys.capella.core.ui.properties.sections.AbstractSection;

/**
 * The ModelElement section.
 */
public abstract class ModelElementSection extends AbstractSection {

  /**
   * @param element_p
   * @return {@link IReadOnlySectionHandler}
   */
  @Override
  protected IReadOnlySectionHandler register(EObject element_p) {
    IReadOnlySectionHandler readOnlyHandler = super.register(element_p);
    if (null != readOnlyHandler) {
      if (readOnlyHandler.isLockedByOthers(element_p)) {
        setEnabled(false);
      } else {
        setEnabled(true);
      }
    }
    return readOnlyHandler;
  }
}
