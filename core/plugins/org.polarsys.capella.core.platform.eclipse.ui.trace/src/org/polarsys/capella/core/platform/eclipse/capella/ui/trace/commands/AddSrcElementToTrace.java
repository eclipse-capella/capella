/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands;

import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 */
public class AddSrcElementToTrace extends AbstractReadWriteCommand {

  private Trace _trace;
  private TraceableElement _srcElement;

  /**
   * @param trace_p
   * @param srcElement_p
   */
  public AddSrcElementToTrace(Trace trace_p, TraceableElement srcElement_p) {
    super();
    _trace = trace_p;
    _srcElement = srcElement_p;
  }

  /**
   * @see org.polarsys.capella.common.services.command.IBusinessCommand#execute()
   */
  public void run() {
    // we can't add a trace with the same element in source and target
    if (_srcElement!= _trace.getTargetElement()) {
      _trace.setSourceElement(_srcElement);
    }
  }

  /**
   * @see org.polarsys.capella.common.services.command.IBusinessCommand#getName()
   */
  @Override
  public String getName() {
    return "AddSrcEltToTrace"; //$NON-NLS-1$
  }
}
