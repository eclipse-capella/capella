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
package org.polarsys.capella.common.helpers.argumentparser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
   
   if (_strictMode) {
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
   * @param id id of the flag
   * @param flagName name of the flag
   * @param isMandatory is this flag mandatory
   * @param nbData number of expected data for this argument
   * @throws ArgumentAnalyzerException
   */
  public void addFlag(String id, String flagName, boolean isMandatory, int nbData) throws ArgumentAnalyzerException {
    // Id must be unique
    if ( _flags.keySet().contains(id) ) {
      throw new ArgumentAnalyzerException(NLS.bind(Messages.duplicatedId, id));
    }
    
    // duplicated name for flag are only allowed with the strictMode.
    if (getNumberOfFlagDefinedWithName(flagName) != 0 && false == _strictMode ) {
      throw new ArgumentAnalyzerException(NLS.bind(Messages.duplicatedNameOnlyAllowedWithStrictMode, flagName));
    }
    
    _flags.put(id, new Flag(flagName, isMandatory, nbData));
  }
  
  /**
   * Add flag to the argument analyzer
   * @param id id of the flag
   * @param flag The new argument.
   * @throws ArgumentAnalyzerException id must be unique
   * @see {@link Flag}
   */
  public void addFlag(String id, Flag flag) throws ArgumentAnalyzerException {
    addFlag(id, flag.getName(), flag.isMandatory(), flag.getNumberOfData());
  }
  
  
  /**
   * Main job method.
   * @param arguments arguments line to analyze.
   * @throws ArgumentAnalyzerException
   */
  public void parse(String[] arguments) throws ArgumentAnalyzerException {
    // Let's clean older result
    _values.clear();
    
    if (_strictMode) {
      strictModeParsing(arguments);
    } else {
      defaultModeParsing(arguments);
    }
  }
  
  /**
   * Check is a given flag has been found during the parsing.
   * @param flagId the target flag Id.
   * @return
   */
  public boolean isArgHasBeenFound(String flagId) throws ArgumentAnalyzerException{
    if (!_flags.containsKey(flagId)) {
      throw new ArgumentAnalyzerException(
          NLS.bind(Messages.unexpectedFlag, flagId)
      );
    }
    
    return _values.containsKey(flagId);
  }
  
  /**
   * 
   * @param flagId the target flag Id
   * @return a {@link List} containing the arguments for the target flag.
   * If no arguments are expected, an empty {@link List} is returned.
   * Whether the flag is not mandatory and has not been found, <code>null</code> is returned 
   * @throws ArgumentAnalyzerException
   */
  public List<String> getFlagArgs(String flagId) throws ArgumentAnalyzerException {
    List<String> results = null;
    
    if ( isArgHasBeenFound(flagId) ) {
       results = _values.get(flagId);
    } else if ( getFlag(flagId).isMandatory() ) {
      throw new ArgumentAnalyzerException(NLS.bind(Messages.flagNotFound,flagId));
    }
    
    return results;
  }
  
  protected boolean isFlagDefined(String id) {
   return _flags.containsKey(id); 
  }
  
  /**
   * 
   * @param id
   * @return
   */
  protected Flag getFlag(String id) {
    return _flags.get(id);
  }
  
  /**
   * Return the cardinality of {@link Flag} with a specific name
   * @param flagName 
   * @return the number of occurrence of this flag
   */
  protected int getNumberOfFlagDefinedWithName(String flagName) {
    int result = 0;
    
    for (Flag flag: _flags.values()) {
      if (flag.getName().equals(flagName)) {
        result++;
      }
    }
    
    return result;
  }
  
  /**
   * check whether, at least, one {@link Flag} named have been defined
   * @param flagName 
   * @return 
   */
  protected boolean isFlagWithNameDefined(String flagName) {
    boolean b= false;
    
    for (Flag flag: _flags.values()) {
      if (flag.getName().equals(flagName)) {
        b = true;
        break;
      }
    }    
    
    return b;
  }
  
  /**
   * Return a {@link List} of Id of {@link Flag}s with a specific name
   * @param flagName
   * @return a {@link List} of flagId which are named flagName
   */
  protected List<String> getFlagIdPerName(String flagName) {
    List<String> result = new ArrayList<>();
    
    for (Entry<String, Flag> entry : _flags.entrySet()) {
      if (entry.getValue().getName().equals(flagName)) {
        result.add(entry.getKey());
      }
    }
    
    return result;
  }
  
  protected void clearResult() {
    _values.clear();
  }
  
  protected void init() {
    _flags = new LinkedHashMap<>();
    _values = new LinkedHashMap<>();
  }
  
  /**
   * parsing is based on arguments.
   * @param arguments cmd line arguments
   * @throws ArgumentAnalyzerException
   */
  protected void defaultModeParsing(String[] arguments) throws ArgumentAnalyzerException {
    String currentArg = null;
    String currentId = null;
    Flag flag = null;
    List<String> args = null;
    List<String> list;
    
    //
    // first of all, let's abruptly check command lines
    //
    for (int i = 0; i < arguments.length; i++) {
      
      currentArg = arguments[i];
      
      if ( isFlagWithNameDefined(currentArg) ) {
        
        list = getFlagIdPerName(currentArg);
        if (list.size() !=1 ) { // should not happen 
          throw new ArgumentAnalyzerException(Messages.genericError);
        }
        
        currentId = list.get(0);
        flag = getFlag(currentId);
        
        args = getArgumentData(currentId, arguments, i);
        
        _values.put(currentId, args);
        i += args.size();
        
      } else { // Undefined flag
        if (_unknownFlagAsError) {
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
    
    Iterator<Entry<String, Flag>> it = _flags.entrySet().iterator();
    String currentFlagId = null;
    List<String> missingFlags = new ArrayList<>();
    
    while (it.hasNext()) {
      Entry<String, Flag> entry = it.next();
      currentFlagId = entry.getKey();
      flag = entry.getValue();
      if (
          flag.isMandatory() &&
          _values.containsKey(currentFlagId) == false 
      ) {
        missingFlags.add(currentFlagId);
      }
    }
    
    if (!missingFlags.isEmpty()) {
      throw new ArgumentAnalyzerException(
        NLS.bind(
          Messages.mandatoryFlagNotFound,
          new Object[] {flag != null ? flag.getName() : "", currentFlagId}
        )
      );
    }
  }
  
  /**
   * parsing is based on Flags (and ordered.)
   * @param arguments cmd line arguments
   * @throws ArgumentAnalyzerException
   */
  protected void strictModeParsing(String[] arguments) throws ArgumentAnalyzerException {
    
    // We simply parse the ordered list of Flag and arguments must be compliant.
    
    Entry<String, Flag> entry;
    Flag currentFlag;
    String currentFlagId;
    int idx = 0;
    String currentFlagFromCmdLine;
    
    int foundArg = 0;
    
    Iterator<Entry<String, Flag>> it = _flags.entrySet().iterator();
    
    while (it.hasNext()) {
      entry = it.next();

      if (idx >= arguments.length) {
        throw new ArgumentAnalyzerException(Messages.strictModeParseFailed);
      }
      
      currentFlagFromCmdLine = arguments[idx];
      
      currentFlagId = entry.getKey();
      currentFlag = entry.getValue();

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
        ArrayList<String> args = getArgumentData(currentFlagId, arguments, idx);
        idx+= args.size() + 1; // For flag itself
        _values.put(currentFlagId, args);
        
      }
      
    } // END WHILE

    if (foundArg != _flags.size()) {
      throw new ArgumentAnalyzerException(Messages.strictModeParseFailed);
    }
  }
  
  /**
   * for Internal use
   * @param currentFlagId
   * @param arguments
   * @param currentIndex
   * @return
   * @throws ArgumentAnalyzerException
   */
  protected ArrayList<String> getArgumentData(String currentFlagId, String[] arguments, int currentIndex ) throws ArgumentAnalyzerException {
    
    ArrayList<String> args = new ArrayList<>();
    
    int i = currentIndex + 1;
    
    Flag flag = getFlag(currentFlagId);
    
    if (flag.getNumberOfData() == IArgumentAnalyzerConstant.UNDEFINED_NUMBER_OF_EXPECTED_DATA) {
      for (int j = i  ; j < arguments.length -1 ; j++) { 
        if (!isFlagDefined(arguments[++i])) {
          args.add(arguments[i]);
        } else { // We found a flag
          i--;
          break;
        }
      }
    } else {
      for (int j = 0 ; j < flag.getNumberOfData(); j++) { 
        if (i>= arguments.length ||  isFlagWithNameDefined(arguments[i])) {
          throw new ArgumentAnalyzerException(
              NLS.bind(Messages.expectedDataDoesNotMatch,
                  new Object[]{ flag.getName(), currentFlagId, String.valueOf(flag.getNumberOfData()) }
              )    
          );
        }
        // Add it.
        args.add(arguments[i]);
        i++;
      }
    }
    
    return args;
  }
}
