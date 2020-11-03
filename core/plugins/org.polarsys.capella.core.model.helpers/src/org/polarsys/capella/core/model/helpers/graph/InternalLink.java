/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.helpers.graph;

import java.util.Objects;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.fa.FunctionPort;

public class InternalLink {

  private FunctionPort source;

  private FunctionPort target;

  public InternalLink(FunctionPort source, FunctionPort target) {
    this.source = source;
    this.target = target;
  }

  public FunctionPort getSource() {
    return source;
  }

  public FunctionPort getTarget() {
    return target;
  }

  @Override
  public String toString() {
    return ICommonConstants.PARENTHESIS_OPEN_CHARACTER + Objects.toString(source) + Objects.toString(target)
        + ICommonConstants.PARENTHESIS_CLOSE_CHARACTER;
  }
}
