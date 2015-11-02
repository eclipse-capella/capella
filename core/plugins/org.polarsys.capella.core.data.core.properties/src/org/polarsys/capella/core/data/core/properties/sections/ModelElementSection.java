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
package org.polarsys.capella.core.data.core.properties.sections;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.model.handler.provider.IReadOnlySectionHandler;
import org.polarsys.capella.core.ui.properties.sections.AbstractSection;

/**
 * The ModelElement section.
 */
public abstract class ModelElementSection extends AbstractSection {

  /**
   * @param element
   * @return {@link IReadOnlySectionHandler}
   */
  @Override
  protected IReadOnlySectionHandler register(EObject element) {
    IReadOnlySectionHandler readOnlyHandler = super.register(element);
    if (null != readOnlyHandler) {
      if (readOnlyHandler.isLockedByOthers(element)) {
        setEnabled(false);
      } else {
        setEnabled(true);
      }
    }
    return readOnlyHandler;
  }
}
