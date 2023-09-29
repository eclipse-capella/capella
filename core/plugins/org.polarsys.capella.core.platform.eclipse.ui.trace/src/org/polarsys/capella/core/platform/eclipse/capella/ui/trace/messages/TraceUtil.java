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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.ui.services.UIUtil;
import org.polarsys.capella.common.ui.toolkit.ToolkitPlugin;
import org.polarsys.capella.core.data.capellacommon.GenericTrace;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands.AddSrcElementToTrace;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands.AddTgtElementToTrace;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands.AddTrace;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands.DeleteTrace;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands.RemoveSourceTraceElement;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands.RemoveTargetTraceElement;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.extension.TraceExtensionManager;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.components.TraceTreeViewer.TraceType;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.SourceElementContentProvider;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.TargetElementContentProvider;

public class TraceUtil {

  private static boolean _isVoidTrace = false;

  public static void addTraces(TraceableElement workingElement_p, TraceableElement currentElement_p, String traceType_p,
      TraceType traceDestinationType_p, Object element_p) {
    if ((element_p == null) || !(element_p instanceof TraceableElement)) {
      if (element_p instanceof Trace) {
        Trace trace = (Trace) element_p;
        if (traceDestinationType_p.equals(TraceType.SOURCE_ELEMENT)) {
          executeCommand(trace, new AddSrcElementToTrace(trace, currentElement_p));
          setIsVoidTrace(trace.getSourceElement() == null);
        } else {
          executeCommand(trace, new AddTgtElementToTrace(trace, currentElement_p));
          setIsVoidTrace(trace.getTargetElement() == null);
        }
      } else if ((element_p == null) || (element_p instanceof Class)) {
        if (traceType_p != null) {
          if (traceDestinationType_p.equals(TraceType.SOURCE_ELEMENT)) {
            executeCommand(currentElement_p, new AddTrace(currentElement_p, workingElement_p, traceType_p));
          } else {
            executeCommand(currentElement_p, new AddTrace(workingElement_p, currentElement_p, traceType_p));
          }
        }
      }
    }
  }

  public static void removeTraces(TraceType traceDestinationType_p, TreeViewer treeViewer_p) {
    Object selectedElement = treeViewer_p.getSelection();
    if ((selectedElement instanceof IStructuredSelection) && (((IStructuredSelection) selectedElement).size() == 1)) {
      boolean isSourceElement = traceDestinationType_p.equals(TraceType.SOURCE_ELEMENT);
      ITreeContentProvider provider = isSourceElement ? (SourceElementContentProvider) treeViewer_p.getContentProvider()
          : (TargetElementContentProvider) treeViewer_p.getContentProvider();
      Object elem = ((IStructuredSelection) selectedElement).getFirstElement();

      if (elem instanceof Trace) {
        Trace traceToDelete = (Trace) elem;
        boolean confirmDeletion = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(),
            Messages.getString("TraceTreeViewer.confirmation"), //$NON-NLS-1$
            Messages.getString("TraceTreeViewer.delete_trace_question")); //$NON-NLS-1$
        if (confirmDeletion) {
          removeTrace(traceToDelete, isSourceElement);
          treeViewer_p.refresh();
        }
      } else if (elem instanceof TraceableElement) {
        TraceableElement eltToDelete = (TraceableElement) elem;
        boolean confirmDeletion = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(),
            Messages.getString("TraceTreeViewer.confirmation"), //$NON-NLS-1$
            Messages.getString("TraceTreeViewer.delete_src_named_elt_question") + " " //$NON-NLS-1$ //$NON-NLS-2$
                + Messages.getString("TraceTreeViewer.question_end")); //$NON-NLS-1$
        if (confirmDeletion) {
          Object parent = provider.getParent(elem);
          if (parent instanceof Trace) {
            Trace trace = (Trace) parent;
            if (isSourceElement) {
              executeCommand(trace, new RemoveSourceTraceElement(trace, eltToDelete));
              setIsVoidTrace(trace.getSourceElement() == null);
            } else {
              executeCommand(trace, new RemoveTargetTraceElement(trace, eltToDelete));
              setIsVoidTrace(trace.getTargetElement() == null);
            }
            // Traceable element is removed now remove its parent
            removeTrace(trace, isSourceElement);
            treeViewer_p.refresh();
          }
        }
      } else if (elem instanceof Class) {
        boolean confirmDeletion = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(),
            Messages.getString("TraceTreeViewer.confirmation"), //$NON-NLS-1$
            Messages.getString("TraceTreeViewer.delete_traceinstance_question")); //$NON-NLS-1$
        if (confirmDeletion) {
          Object[] traces = provider.getChildren(elem);
          for (Object trace : traces) {
            Trace traceToDelete = (Trace) trace;
            removeTrace(traceToDelete, isSourceElement);
          }
          treeViewer_p.refresh();
        }
      }
    }
  }

  /**
   * @param isSourceElementTraceType
   * @param traceToDelete
   */
  private static void removeTrace(Trace traceToDelete, boolean isSourceElementTraceType) {
    TraceableElement owningElt = (TraceableElement) traceToDelete.eContainer();
    executeCommand(owningElt, new DeleteTrace(traceToDelete));
    boolean containsFlag = isSourceElementTraceType ? owningElt.getIncomingTraces().contains(traceToDelete)
        : owningElt.getOutgoingTraces().contains(traceToDelete);
    boolean emptyFlag = isSourceElementTraceType ? traceToDelete.getSourceElement() == null
        : traceToDelete.getTargetElement() == null;
    setIsVoidTrace(containsFlag && emptyFlag);
  }

  /**
   * Checks whether the current NamedElement contains a trace to element
   * 
   * @param currentElement_p
   *          the current element
   * @param element_p
   *          the element to check
   * @return true if currentElement has a trace to element
   */
  public static boolean containsTraceElement(TraceableElement currentElement_p, TraceableElement element_p) {
    if (currentElement_p.equals(element_p)) {
      return true;
    }
    for (AbstractTrace trace : currentElement_p.getIncomingTraces()) {
      if (trace.getSourceElement() == element_p) {
        return true;
      }
      if (trace.getTargetElement() == element_p) {
        return true;
      }
    }
    for (AbstractTrace trace : currentElement_p.getOutgoingTraces()) {
      if (trace.getSourceElement() == element_p) {
        return true;
      }
      if (trace.getTargetElement() == element_p) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param element_p
   */
  public static boolean canAddRemoveItemsToTrace(Object element_p, ResourceSet context) {
    if (element_p instanceof EObject) {
      if ((element_p instanceof GenericTrace)
          || TraceExtensionManager.eINSTANCE.canAddRemoveItemsToTrace((EObject) element_p)) {
        return true;
      }
    }
    if (element_p instanceof Class) {
      Class<?> clazz = (Class<?>) element_p;
      if (TraceExtensionManager.eINSTANCE.isAssignableFrom(clazz, context)) {
        return true;
      }
      if (GenericTrace.class.isAssignableFrom(clazz)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param element_p
   */
  public static boolean canEnableAddItem(Object element_p) {
    if (element_p instanceof AbstractNamedElement) {
      return false;
    }
    if (element_p instanceof Class) {
      return true;
    }
    return false;
  }

  /**
   * @param _isVoidTrace_p
   *          the _isVoidTrace to set
   */
  public static void setIsVoidTrace(boolean _isVoidTrace_p) {
    TraceUtil._isVoidTrace = _isVoidTrace_p;
  }

  /**
   * @return the _isVoidTrace
   */
  public static boolean isIsVoidTrace() {
    return _isVoidTrace;
  }

  /**
   * @param currentElement_p
   */
  @Deprecated
  public static void findAndSelectElement(CapellaElement currentElement_p) {
    UIUtil.getInstance().selectInPackageExplorer(currentElement_p);
  }

  /**
   * @param command_p
   */
  public static void executeCommand(EObject context_p, AbstractReadWriteCommand command_p) {
    if (ToolkitPlugin.getDefault().isTransactionRunning()) {
      command_p.run();
    } else {
      TransactionHelper.getExecutionManager(context_p).execute(command_p);
    }
  }
}
