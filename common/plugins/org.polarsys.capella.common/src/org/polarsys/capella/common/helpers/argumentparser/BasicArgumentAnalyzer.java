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

package org.polarsys.capella.common.helpers.argumentparser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;

/**
 * Specialization of the {@link ArgumentAnalyzer} class for the case
 * where no flags are defined (but some arguments.)
 */
public class BasicArgumentAnalyzer extends ArgumentAnalyzer {

  /**
   * Constructor
   * @param numberOfArgument
   */
  public BasicArgumentAnalyzer(int numberOfArgument) {
    
    super(true); //Strict mode
    
    // Let's set a default flag
    Flag flag = new Flag(ICommonConstants.EMPTY_STRING, true, numberOfArgument);
    _flags.put(IArgumentAnalyzerConstant.NOFLAG_ID, flag);
    
  }
  
  /**
   * @see org.polarsys.capella.common.helpers.argumentparser.ArgumentAnalyzer#parse(java.lang.String[])
   */
  @Override
  public void parse(String[] arguments) throws ArgumentAnalyzerException {
    
    int nbOfExpectedData = _flags.get(IArgumentAnalyzerConstant.NOFLAG_ID).getNumberOfData();
    
    if (
        nbOfExpectedData != IArgumentAnalyzerConstant.UNDEFINED_NUMBER_OF_EXPECTED_DATA &&
        arguments.length != nbOfExpectedData
    ) {
      throw new ArgumentAnalyzerException(
          NLS.bind(Messages.expectedDataDoesNotMatchBasicCase,
          new Object[]{ String.valueOf( arguments.length), String.valueOf(nbOfExpectedData)} )
      );
    }
    
    ArrayList<String> args = getArgumentData(IArgumentAnalyzerConstant.NOFLAG_ID, arguments, -1);
    
    if ( 
        nbOfExpectedData != IArgumentAnalyzerConstant.UNDEFINED_NUMBER_OF_EXPECTED_DATA &&
        args.size() != nbOfExpectedData
    ) {
      throw new ArgumentAnalyzerException(
          NLS.bind(Messages.expectedDataDoesNotMatchBasicCase,
          new Object[]{ String.valueOf(args.size()), String.valueOf(nbOfExpectedData)} )
      );
    }
    
    _values.put(IArgumentAnalyzerConstant.NOFLAG_ID, args);
    
  }
  
  public List<String> getArgs() {
    return _values.get(IArgumentAnalyzerConstant.NOFLAG_ID);
  }
  
  /**
   * @see org.polarsys.capella.common.helpers.argumentparser.ArgumentAnalyzer#addFlag(java.lang.String, org.polarsys.capella.common.helpers.argumentparser.ArgumentAnalyzer.Flag)
   */
  @Override
  public void addFlag(String id, Flag flag) throws ArgumentAnalyzerException {
    throw new ArgumentAnalyzerException(Messages.opsNotSupported);
  }
  
  /**
   * @see org.polarsys.capella.common.helpers.argumentparser.ArgumentAnalyzer#addFlag(java.lang.String, java.lang.String, boolean, int)
   */
  @Override
  public void addFlag(String id, String flagName, boolean isMandatory, int nbData) throws ArgumentAnalyzerException {
    throw new ArgumentAnalyzerException(Messages.opsNotSupported);
  }
  
  @Override
  public List<String> getFlagArgs(String id) throws ArgumentAnalyzerException {
    throw new ArgumentAnalyzerException(Messages.opsNotSupported);
  }

  @Override
  public boolean isArgHasBeenFound(String flagId) throws ArgumentAnalyzerException {
    throw new ArgumentAnalyzerException(Messages.opsNotSupported);
  }
  
}
