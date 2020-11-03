/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.links.helpers;

import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * 
 */
public class LinkInfo {
  /**
   * LinkStyle definitions.
   */
  public static enum LinkStyle {
    LINE_SOLID, LINE_DASHED, LINE_SOLID_WITH_EMPTY_ARROW, LINE_SOLID_WITH_FILLED_ARROW
  }

  public final ModelElement _sourceElement;

  public final ModelElement _targetElement;

  public final LinkStyle _linkStyle;

  public LinkInfo(ModelElement sourceElement, ModelElement targetElement, LinkStyle linkStyle) {
    _sourceElement = sourceElement;
    _targetElement = targetElement;
    _linkStyle = linkStyle;
  }
}
