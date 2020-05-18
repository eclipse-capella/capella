/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.Wizard;

import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellacore.Trace;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands.AddSrcElementToTrace;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands.AddTgtElementToTrace;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.commands.AddTrace;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.Messages;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.components.TraceTreeViewer.TraceType;
import org.polarsys.capella.common.helpers.TransactionHelper;

/**
 * <code>AddTraceWizard</code> main wizard to add trace
 *  
 * @deprecated
 */
public class AddTraceWizard extends Wizard {

  public static String PAGE_CAPELLA_MODEL="pageCapellaModel"; //$NON-NLS-1$
  public static String PAGE_TRACE_TYPE="pageTraceType"; //$NON-NLS-1$
  
  /** Content type of the TraceTreeviewer */
  public TraceType _traceDestinationType;
  
  //Argument to add trace
  private Trace _selectedTrace;
  private String _traceType;
  private NamedElement _workingNamedElement,_currentNamedElement;
  

  /**
   * @param traceDestinationType_p
   */
  public AddTraceWizard(NamedElement workingNamedElement_p, TraceType traceDestinationType_p) {
    super();
    _traceDestinationType = traceDestinationType_p;
    _workingNamedElement = workingNamedElement_p;
    setWindowTitle(Messages.getString("TraceTreeViewer.window_title")); //$NON-NLS-1$
  }

  /**
   * @see org.eclipse.jface.wizard.Wizard#performFinish()
   */
  @Override
  public boolean performFinish() {
    if(_currentNamedElement==null){
      ViewCapellaModelPage capellaModelPage=(ViewCapellaModelPage)this.getPage(PAGE_CAPELLA_MODEL);
      capellaModelPage.setMessage(Messages.getString("AddTraceWizard.warning_named_element"), IMessageProvider.WARNING); //$NON-NLS-1$
      return false;
    }
    
    //Add to an existing trace
    if(_selectedTrace!=null){
      if(_traceDestinationType.equals(TraceType.SOURCE_ELEMENT)){
        TransactionHelper.getExecutionManager(_selectedTrace).execute(new AddSrcElementToTrace(_selectedTrace,_currentNamedElement));
      } else {
        TransactionHelper.getExecutionManager(_selectedTrace).execute(new AddTgtElementToTrace(_selectedTrace,_currentNamedElement));
      }
      return true;
    }
    
    //perform add trace
    if(_traceType!=null){
      if(_traceDestinationType.equals(TraceType.SOURCE_ELEMENT)){
        TransactionHelper.getExecutionManager(_currentNamedElement).execute(new AddTrace(_currentNamedElement,_workingNamedElement,_traceType));
      } else {
        TransactionHelper.getExecutionManager(_workingNamedElement).execute(new AddTrace(_workingNamedElement,_currentNamedElement,_traceType));
      }
    }
    return true;

  }
  
  /**
   * @see org.eclipse.jface.wizard.Wizard#canFinish()
   */
  @Override
  public boolean canFinish() {
    if(this.getContainer().getCurrentPage() == this.getPage(PAGE_CAPELLA_MODEL)) 
      return this.getPage(PAGE_CAPELLA_MODEL).isPageComplete();
    return false;
  }



  /**
   * @param traceType_p the traceType to set
   */
  public void setTraceType(String traceType_p) {
    _traceType = traceType_p;
  }


  /**
   * @param currentNamedElement_p the currentNamedElement to set
   */
  public void setCurrentNamedElement(NamedElement currentNamedElement_p) {
    _currentNamedElement = currentNamedElement_p;
  }
  
  /**
   * @param selectedTrace_p the selectedTrace to set
   */
  public void setSelectedTrace(Trace selectedTrace_p) {
    _selectedTrace = selectedTrace_p;
  }

  /**
   * @return the currentNamedElement
   */
  public NamedElement getCurrentNamedElement() {
    return _currentNamedElement;
  }

  /**
   * @return the workingNamedElement
   */
  public NamedElement getWorkingNamedElement() {
    return _workingNamedElement;
  }

  /**
   * @param workingNamedElement_p the workingNamedElement to set
   */
  public void setWorkingNamedElement(NamedElement workingNamedElement_p) {
    _workingNamedElement = workingNamedElement_p;
  }

  
}
