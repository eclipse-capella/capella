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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.Messages;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.TraceNameHelper;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 */
public class AddTrace extends AbstractReadWriteCommand {

  private TraceableElement _srcElement, _tgtElement;
  private String _traceType;

  /**
   * @param srcElement_p
   * @param tgtElement_p
   * @param traceType_p
   */
  public AddTrace(TraceableElement srcElement_p, TraceableElement tgtElement_p, String traceType_p) {
    super();
    _srcElement = srcElement_p;
    _tgtElement = tgtElement_p;
    _traceType = traceType_p;
  }

  /**
   * @see org.polarsys.capella.common.services.command.IBusinessCommand#execute()
   */
  @Override
  public void run() {
    ResourceSet rset = _srcElement != null ? _srcElement.eResource().getResourceSet()
        : _tgtElement.eResource().getResourceSet();
    Trace trace = TraceNameHelper.getNewTraceInstanceFromTraceName(_traceType, rset);
    if (trace != null) {
      if (!_tgtElement.equals(_srcElement)) {
        trace.setTargetElement(_tgtElement);
        trace.setSourceElement(_srcElement);
        if (_srcElement instanceof Namespace) {
          addOwnedTrace((Namespace) _srcElement, trace);
        } else {
          // the srcElement is not a Component, we have to find the nearest trace component
          Namespace container = getNearestTraceContainer(_srcElement);
          if (container != null) {
            addOwnedTrace(container, trace);
          } else {
            MessageDialog.openWarning(Display.getCurrent().getActiveShell(), Messages.getString("AddTrace.warning"), //$NON-NLS-1$
                Messages.getString("AddTrace.msg1")); //$NON-NLS-1$
            trace.destroy();
          }
        }
      } else {
        // we can't add a trace with the same element in source and target
        trace.destroy();
        MessageDialog.openWarning(Display.getCurrent().getActiveShell(), Messages.getString("AddTrace.warning"), //$NON-NLS-1$
            Messages.getString("AddTrace.msg2")); //$NON-NLS-1$
      }
    }

  }

  private Namespace getNearestTraceContainer(EObject element_p) {
    if (element_p != null) {
      EObject container = element_p.eContainer();
      if (container != null && container instanceof Namespace) {
        return (Namespace) container;
      }

      return getNearestTraceContainer(container);
    }
    return null;
  }

  private void addOwnedTrace(Namespace element_p, Trace trace_p) {
    element_p.getOwnedTraces().add(trace_p);
  }

  /**
   * @see org.polarsys.capella.common.services.command.IBusinessCommand#getName()
   */
  @Override
  public String getName() {
    return "AddTrace"; //$NON-NLS-1$
  }
}
