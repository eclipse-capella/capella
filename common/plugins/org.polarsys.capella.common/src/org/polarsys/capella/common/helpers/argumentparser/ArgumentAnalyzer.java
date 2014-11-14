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
package org.polarsys.capella.common.helpers.argumentparser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.osgi.util.NLS;

/**
 * Utility class in order to analyze arguments
 */
public class ArgumentAnalyzer {

  /** expected flags (flagId, Flag)*/
  protected Map<String , Flag> _flags;

  /** collected values (flagId,values) */
  protected Map<String, List<String>> _values;
  
  /** default setting about parser behavior in case of unknown flags */ 
  final protected static boolean _UNKNOWN_FLAG_AS_ERROR_DEFAULT = false;
  
  /** should we stop on unknown flags */
  final protected boolean _unknownFlagAsError;
  
  /** Arguments must be given in a specific order and unknown flag are treated as error */
  final protected boolean _strictMode; 
  
  /**
   * Constructor for command line without any flag but data passed.
   * @param numberOfArgument number of expected data
   */
  public ArgumentAnalyzer(int numberOfArgument) {
    
    init();
    
    _strictMode = true;
    _unknownFlagAsError = true;
        
  }
  
  /**
   * Constructor
   * Unknown flags are not treated as error.
   * @param strictMode arguments must be given in a specific order and thus, unknown flags are not allowed
   */
  public ArgumentAnalyzer(boolean strictMode) {
    
   init();
   
   _strictMode = strictMode;
   
   if (true == _strictMode) {
     // We can not manage both unknown flags and ordered ones. 
     _unknownFlagAsError = true;
   } else {
     // We keep default behavior
     _unknownFlagAsError = _UNKNOWN_FLAG_AS_ERROR_DEFAULT;
   }
    
  }
  

  /**
   * Constructor
   * @param stictMode arguments must be given in a specific order and thus, unknown flags are not allowed
   * @param unknowFlagAsError should we treat unknown flag as error
   * @throws ArgumentAnalyzerException when strictMode is set to true 
   * and unknowFlagAsError to false (these two notions are not really compatible.)
   */
  public ArgumentAnalyzer(boolean strictMode, boolean unknowFlagAsError) 
    throws ArgumentAnalyzerException {
    
    init();
    
    _strictMode = strictMode;
    
    if (true == _strictMode && false == unknowFlagAsError ) {
      // We can not manage both unknown flags and ordered ones.
      throw new ArgumentAnalyzerException(Messages.inconsistentOptions);
    }
     
    _unknownFlagAsError = unknowFlagAsError;

  }

  
  /**
   * Add flag to the argument analyzer
   * @param id_p id of the flag
   * @param flagName_p name of the flag
   * @param isMandatory_p is this flag mandatory
   * @param nbData_p number of expected data for this argument
   * @throws ArgumentAnalyzerException
   */
  public void addFlag(String id_p, String flagName_p, boolean isMandatory_p, int nbData_p) throws ArgumentAnalyzerException {

    // Id must be unique
    if ( _flags.keySet().contains(id_p) ) {
      throw new ArgumentAnalyzerException(NLS.bind(Messages.duplicatedId, id_p));
    }
    
    // duplicated name for flag are only allowed with the strictMode.
    if (getNumberOfFlagDefinedWithName(flagName_p) != 0 && false == _strictMode ) {
      throw new ArgumentAnalyzerException(NLS.bind(Messages.duplicatedNameOnlyAllowedWithStrictMode, flagName_p));
    }
    
    _flags.put(id_p, new Flag(flagName_p, isMandatory_p, nbData_p));

    return;
  }
  
  /**
   * Add flag to the argument analyzer
   * @param id_p id of the flag
   * @param flag_p The new argument.
   * @throws ArgumentAnalyzerException id_p must be unique
   * @see {@link Flag}
   */
  public void addFlag(String id_p, Flag flag_p) throws ArgumentAnalyzerException {
    addFlag(id_p, flag_p.getName(), flag_p.isMandatory(), flag_p.getNumberOfData());
    return;
  }
  
  
  /**
   * Main job method.
   * @param arguments_p arguments line to analyze.
   * @throws ArgumentAnalyzerException
   */
  public void parse(String[] arguments_p) throws ArgumentAnalyzerException {
 
    // Let's clean older result
    _values.clear();
    
    if ( true == _strictMode ) {
      strictModeParsing(arguments_p);
    } else {
      defaultModeParsing(arguments_p);
    }
    
    return;
  }
  
  /**
   * Check is a given fag has been found during parse ops.
   * @param flagId_p the target flag Id.
   * @return
   */
  public boolean isArgHasBeenFound(String flagId_p) throws ArgumentAnalyzerException{
    
    if (!_flags.containsKey(flagId_p)) {
      throw new ArgumentAnalyzerException(
          NLS.bind(Messages.unexpectedFlag, flagId_p)
      );
    }
    
    return _values.containsKey(flagId_p);
  }
  
  /**
   * 
   * @param flagId_p the target flag Id
   * @return a {@link List} containing the arguments for the target flag.
   * If no arguments are expected, an empty {@link List} is returned.
   * Whether the flag is not mandatory and has not been found, <code>null</code> is returned 
   * @throws ArgumentAnalyzerException
   */
  public List<String> getFlagArgs(String flagId_p) throws ArgumentAnalyzerException {
    
    List<String> results = null;
    
    if ( isArgHasBeenFound(flagId_p) ) {
       results = _values.get(flagId_p);
    } else if ( getFlag(flagId_p).isMandatory() ) {
      throw new ArgumentAnalyzerException(NLS.bind(Messages.flagNotFound,flagId_p));
    }
    
    return results;
  }
  
  protected boolean isFlagDefined(String id_p) {
   return _flags.containsKey(id_p); 
  }
  
  /**
   * 
   * @param id_p
   * @return
   */
  protected Flag getFlag(String id_p) {
    return _flags.get(id_p);
  }
  
  /**
   * Return the cardinality of {@link Flag} with a specific name
   * @param flagName_p 
   * @return the number of occurrence of this flag
   */
  protected int getNumberOfFlagDefinedWithName(String flagName_p) {
    
    int result = 0;
    
    for (Flag flag: _flags.values()) {
      if (flag.getName().equals(flagName_p)) {
        result++;
      }
    }
    
    return result;
  }
  
  /**
   * check whether, at least, one {@link Flag} named have been defined
   * @param flagName_p 
   * @return 
   */
  protected boolean isFlagWithNameDefined(String flagName_p) {
    
    boolean b= false;
    
    for (Flag flag: _flags.values()) {
      if (flag.getName().equals(flagName_p)) {
        b = true;
        break;
      }
    }    
    
    return b;
  }
  
  /**
   * Return a {@link List} of Id of {@link Flag}s with a specific name
   * @param flagName_p
   * @return a {@link List} of flagId which are named flagName_p
   */
  protected List<String> getFlagIdPerName(String flagName_p) {
    
    List<String> result = new ArrayList<String>();
    
    for (String currentId: _flags.keySet()) {
      if (_flags.get(currentId).getName().equals(flagName_p)) {
        result.add(currentId);
      }
    }
    
    return result;
  }
  
  protected void clearResult() {
    
    _values.clear();
    
    return;
  }
  
  protected void init() {
    
    _flags = new LinkedHashMap<String, Flag>();
    _values = new LinkedHashMap<String, List<String>>();
    
    return;
  }
  
  /**
   * parsing is based on arguments.
   * @param arguments_p cmd line arguments
   * @throws ArgumentAnalyzerException
   */
  protected void defaultModeParsing(String[] arguments_p) throws ArgumentAnalyzerException {
    
    String currentArg = null;
    String currentId = null;
    Flag flag = null;
    List<String> args = null;
    List<String> list;
    
    //
    // first of all, let's abruptly check command lines
    //
    for (int i = 0; i < arguments_p.length; i++) {
      
      currentArg = arguments_p[i];
      
      if ( isFlagWithNameDefined(currentArg) ) {
        
        list = getFlagIdPerName(currentArg);
        if (list.size() !=1 ) { // should not happen 
          throw new ArgumentAnalyzerException(Messages.genericError);
        }
        
        currentId = list.get(0);
        flag = getFlag(currentId);
        
        args = getArgumentData(currentId, arguments_p, i);
        
        _values.put(currentId, args);
        i += args.size();
        
      } else { // Undefined flag
        if ( _unknownFlagAsError == true ) {
          // We directly exit on the first unknown flag. 
          // It will be quickly raised to too incomprehensible in case of flag with arguments...
          throw new ArgumentAnalyzerException(
              NLS.bind(Messages.unknownFlagFoundButNotAllowed, currentArg)
          );
        }
      }
      
    } // END OF LOOP on arguments list
    
    //
    // On a second hand, let's check whether all
    // mandatory flags have been found.
    //
    
    Iterator<String> it = _flags.keySet().iterator();
    String currentFlagId = null;
    List<String> missingFlags = new ArrayList<String>();
    
    while (it.hasNext()) {
      currentFlagId = it.next();
      flag = _flags.get(currentFlagId);
      if (
          flag.isMandatory() == true &&
          _values.containsKey(currentFlagId) == false 
      ) {
        missingFlags.add(currentFlagId);
      }
    }
    
    if (!missingFlags.isEmpty()) {
      throw new ArgumentAnalyzerException(
        NLS.bind(
          Messages.mandatoryFlagNotFound,
          new Object[] {flag.getName(), currentFlagId}
        )
      );
    }
    
    return;
  }
  
  /**
   * parsing is based on Flags (and ordered.)
   * @param arguments_p cmd line arguments
   * @throws ArgumentAnalyzerException
   */
  protected void strictModeParsing(String[] arguments_p) throws ArgumentAnalyzerException {
    
    // We simply parse the ordered list of Flag and arguments_p must be compliant.
    
    
    Flag currentFlag;
    String currentFlagId;
    int idx = 0;
    String currentFlagFromCmdLine;
    
    int foundArg = 0;
    
    Iterator<String> it = _flags.keySet().iterator();
    
    while (it.hasNext()) {
      
      if (idx >= arguments_p.length) {
        throw new ArgumentAnalyzerException(Messages.strictModeParseFailed);
      }
      
      currentFlagFromCmdLine = arguments_p[idx];
      
      currentFlagId = it.next();
      currentFlag = _flags.get(currentFlagId);

      boolean currentMatch = true;
      
      if ( 
          !currentFlag.isMandatory() &&  
          currentFlag.getName().equals(currentFlagFromCmdLine)
      ) {
        currentMatch = false;
        
      } else if (
          currentFlag.isMandatory() && 
          !currentFlag.getName().equals(currentFlagFromCmdLine)
      ) {
        throw new ArgumentAnalyzerException(Messages.strictModeParseFailed);
      }
      
      if ( currentMatch ) { 
        foundArg++;
        ArrayList<String> args = getArgumentData(currentFlagId, arguments_p, idx);
        idx+= args.size() + 1; // For flag itself
        _values.put(currentFlagId, args);
        
      }
      
    } // END WHILE

    if (foundArg != _flags.size()) {
      throw new ArgumentAnalyzerException(Messages.strictModeParseFailed);
    }
    
    return;
  }
  
  /**
   * for Internal use
   * @param currentFlagId
   * @param arguments_p
   * @param currentIndex_p
   * @return
   * @throws ArgumentAnalyzerException
   */
  protected ArrayList<String> getArgumentData(String currentFlagId, String[] arguments_p, int currentIndex_p ) throws ArgumentAnalyzerException {
    
    ArrayList<String> args = new ArrayList<String>();
    
    int i = currentIndex_p + 1;
    
    Flag flag = getFlag(currentFlagId);
    
    if (flag.getNumberOfData() == IArgumentAnalyzerConstant.UNDEFINED_NUMBER_OF_EXPECTED_DATA) {
      for (int j = i  ; j < arguments_p.length -1 ; j++) { 
        if (!isFlagDefined(arguments_p[++i])) {
          args.add(arguments_p[i]);
        } else { // We found a flag
          i--;
          break;
        }
      }
    } else {
      for (int j = 0 ; j < flag.getNumberOfData(); j++) { 
        if (i>= arguments_p.length ||  isFlagWithNameDefined(arguments_p[i])) {
          throw new ArgumentAnalyzerException(
              NLS.bind(Messages.expectedDataDoesNotMatch,
                  new Object[]{ flag.getName(), currentFlagId, String.valueOf(flag.getNumberOfData()) }
              )    
          );
        }
        // Add it.
        args.add(arguments_p[i]);
        i++;
      }
    }
    
    return args;
  }
  
}
