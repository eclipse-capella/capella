/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.crossreferencer;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellacore.Trace;

/**
 * Trace <-outgoing/incoming-> TraceableElement test.
 */
public class TraceableElementDerivedFeaturesTest extends AbstractReflectiveCrossReferencerTest {
  /**
   * Test for all possible values for {@link Trace} and {@link TraceableElement}.
   */
  public void testTraceableElementDerivedFeaturesForAll() throws Exception {
    final int traceableElementsCount = getSubTypesCount(ModellingcorePackage.Literals.TRACEABLE_ELEMENT);
    final int tracesCount = getSubTypesCount(ModellingcorePackage.Literals.ABSTRACT_TRACE);
    assertTrue("Ensure traceable elements were found", traceableElementsCount > 0);
    System.out.println("Traceable element types count = " + traceableElementsCount);
    assertTrue("Ensure traces were found", tracesCount > 0);
    System.out.println("Trace types count = " + tracesCount);
    new ProgressMonitorDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell()).run(false, false,
        new IRunnableWithProgress() {
          @Override
          public void run(final IProgressMonitor progressMonitor_p)
              throws InvocationTargetException, InterruptedException {
            progressMonitor_p.beginTask(getName(), traceableElementsCount);
            executeTestCommand(new AbstractReadWriteCommand() {
              @Override
              public void run() {
                int tracePosition = 0;
                // Cycle trough source elements.
                for (int sourceTraceableElementTypePosition = 0; sourceTraceableElementTypePosition < traceableElementsCount; sourceTraceableElementTypePosition++) {
                  // Create trace.
                  AbstractTrace trace = (AbstractTrace) createType(
                      getSubType(ModellingcorePackage.Literals.ABSTRACT_TRACE, tracePosition));
                  tracePosition = (tracePosition + 1) % tracesCount;
                  // Create source.
                  TraceableElement source = (TraceableElement) createType(
                      getSubType(ModellingcorePackage.Literals.TRACEABLE_ELEMENT, sourceTraceableElementTypePosition));
                  trace.setSourceElement(source);
                  assertEquals("Ensure outgoing trace is valid", trace, source.getOutgoingTraces().get(0));
                  // Cycle through target elements.
                  for (int targetTraceableElementTypePosition = 0; targetTraceableElementTypePosition < traceableElementsCount; targetTraceableElementTypePosition++) {
                    TraceableElement target = (TraceableElement) createType(getSubType(
                        ModellingcorePackage.Literals.TRACEABLE_ELEMENT, targetTraceableElementTypePosition));
                    trace.setTargetElement(target);
                    assertEquals("Ensure incoming trace is valid", trace, target.getIncomingTraces().get(0));
                    // Free memory.
                    removeFromResource(target);
                  }
                  // Free memory.
                  trace.setSourceElement(null);
                  removeFromResource(source);
                  removeFromResource(trace);
                  // Progress.
                  progressMonitor_p.worked(1);
                }
              }
            });
            progressMonitor_p.done();
          }
        });
  }

  @Override
  protected List<EClass> getRootTypes() {
    return Arrays.asList(ModellingcorePackage.Literals.ABSTRACT_TRACE, ModellingcorePackage.Literals.TRACEABLE_ELEMENT);
  }

  @Override
  public void test() throws Exception {
    testTraceableElementDerivedFeaturesForAll();
  }
}
