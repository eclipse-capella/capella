/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.common.flexibility.wizards.policy;

import org.polarsys.capella.common.flexibility.wizards.schema.IGroupRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IPropertyRenderer;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyGroup;

/**
 *
 */
public class AbstractRendererPolicy implements IRendererPolicy {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean match(IProperty property) {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean match(IPropertyGroup property) {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IPropertyRenderer createRenderer(IProperty property) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IGroupRenderer createRenderer(IPropertyGroup group) {
    return null;
  }

}
