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
package org.polarsys.capella.core.platform.sirius.clipboard.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 * A Read/Write command that provides objects as a feedback for its execution.
 */
public abstract class AbstractResultCommand extends AbstractReadWriteCommand {

  // The list that stores the EObjects resulting from the command execution
  protected final List<Object> _cmdResult = new ArrayList<Object>();

  protected void setResults(List<?> cmdResult_p) {
    _cmdResult.clear();
    _cmdResult.addAll(cmdResult_p);
  }

  public List<Object> getResults() {
    return Collections.unmodifiableList(_cmdResult);
  }

  protected void setSingleResult(Object singleResult_p) {
    if (null != singleResult_p)
      setResults(Collections.singletonList(singleResult_p));
    else
      setResults(Collections.emptyList());
  }

  public Object getSingleResult() {
    Object result = null;
    if (!_cmdResult.isEmpty())
      result = _cmdResult.get(0);
    return result;
  }
}
