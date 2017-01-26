/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.command.recorder.internal;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

import org.eclipse.core.resources.IProject;

import org.polarsys.capella.common.command.recorder.core.manager.utils.OperationEnum;
import org.polarsys.capella.common.command.recorder.core.project.AbstractProjectRecorder;
import org.polarsys.capella.common.command.recorder.core.project.AbstractProjectRecorderManager;
import org.polarsys.capella.common.command.recorder.core.writer.TXTWriterHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Recorder Manager for Capella
 *
 */
public class CapellaProjectRecorderManager extends AbstractProjectRecorderManager {
  
  /**
   * Constructor 
   */
  public CapellaProjectRecorderManager(Collection<String> natureIdsToMatch) {
    super(natureIdsToMatch);    
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected AbstractProjectRecorder createProjectRecorder(IProject project) {
    return new CapellaProjectRecorder(this, project);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void writeOperation(Writer writer) throws IOException {
    
    OperationEnum current = OperationEnum.getOperationEnum(_hlc.getClosingOperation());
    
    String str = 
      current.getLiteral() +
      ICommonConstants.WHITE_SPACE_CHARACTER +
      _hlc.getLabel()
    ;
    
    TXTWriterHelper.writeEntry(writer, str);
    
    if ( null != _hlc.getDate() ) {
    	TXTWriterHelper.writeExtraDataLine(writer,_hlc.getDate().toString());
    }
    
    
    return;
  }    
}
