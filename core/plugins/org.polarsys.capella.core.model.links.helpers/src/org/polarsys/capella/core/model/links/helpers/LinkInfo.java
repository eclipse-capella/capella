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

  public LinkInfo(ModelElement sourceElement_p, ModelElement targetElement_p, LinkStyle linkStyle_p) {
    _sourceElement = sourceElement_p;
    _targetElement = targetElement_p;
    _linkStyle = linkStyle_p;
  }
}
