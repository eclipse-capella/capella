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
package org.polarsys.capella.test.diagram.common.ju.wrapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.InvalidArgumentException;

/**
 * Abstract class for wrapper on {@link AbstractToolDescription} kind of objects. Such wrapper aims to simplify the
 * creation of command from Tools, command context setting as well as pre-control operations.
 */
public abstract class AbstractToolWrapper {

  /** Internal class in order to describe generic arguments given to the wrapper */
  public class ArgumentData {
    public ArgumentData(ArgumentType type) {
      _type = type;
    }

    public ArgumentData(ArgumentType type, EClass eClass) {
      _type = type;
      _eClass = eClass;
    }

    public ArgumentType getType() {
      return _type;
    }

    public EClass getEClass() {
      return _eClass;
    }

    protected ArgumentType _type;
    protected EClass _eClass = null;
  }

  /** the target tool */
  protected final AbstractToolDescription _tool;

  public AbstractToolDescription getTool() {
    return _tool;
  }

  /** The argument list in order to create the command */
  protected Map<ArgumentType, Object> _arguments;

  /** List with the needed type of arguments in order to create command */
  protected final List<ArgumentData> _argumentTypes;

  /**
   * constructor
   * 
   * @param tool
   */
  public AbstractToolWrapper(AbstractToolDescription tool) {

    _tool = tool;
    _arguments = new HashMap<ArgumentType, Object>();
    _argumentTypes = getArgumentTypes();
  }

  /**
   * return the list of need argument.
   * 
   * @return
   */
  public abstract List<ArgumentData> getArgumentTypes();

  /**
   * check is the context defined by the arguments allows to create the corresponding command for this tool
   * 
   * @return
   */
  public abstract boolean isContextOk();

  /**
   * Create the command corresponding to the wrapped tool.
   * 
   * @return
   * @throws InvalidArgumentException
   */
  abstract public Command createCommand();

  /**
   * check is all needed arguments to the command have been set with a compliant object type.
   * 
   * @return
   * @see {@link ArgumentType}
   */
  final public boolean isArgumentsAreSet() {
    return checkArguments().isOK();
  }

  final public IStatus checkArguments() {
    boolean ret = true;
    MultiStatus result = new MultiStatus(FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), Status.OK, "isArgumentsAreSet", null);
    Iterator<ArgumentData> it = getArgumentTypes().iterator();

    while (it.hasNext() && ret) {
      ArgumentData currentArgumentData = it.next();
      ArgumentType currentType = currentArgumentData.getType();
      
      if (((currentType.equals(ArgumentType.COLLECTION) && _arguments.get(currentType) == null))
          || (currentType.equals(ArgumentType.PREDECESSOR) && (_arguments.get(currentType) == null))
          || (currentType.equals(ArgumentType.STARTINGENDPREDECESSOR) && (_arguments.get(currentType) == null))
          || (currentType.equals(ArgumentType.FINISHINGENDPREDECESSOR) && (_arguments.get(currentType) == null))) {
        result.add(new Status(Status.OK, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), currentType + " is ok"));
        return result;
      }
      
      if (!_arguments.containsKey(currentType) || (_arguments.get(currentType) == null)) {
        result.add(new Status(Status.ERROR, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), currentType + " is not set or null"));
        
      } else {
        ret = checkValue(currentType);
        if (ret) {
          result.add(new Status(Status.OK, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), currentType + " is ok"));

        } else {
          result.add(new Status(Status.ERROR, FrameworkUtil.getBundle(this.getClass()).getSymbolicName(), currentType + " is not value"));
        }
      }
    }
    return result;
  }

  /**
   * Write accessor on argument values.
   * 
   * @param argumentType
   *          the target argument
   * @param value
   *          its value
   */
  final public void setArgumentValue(ArgumentType argumentType, Object value) {
    if (isArgumentTypeRequired(argumentType)) {
      _arguments.put(argumentType, value);
    }
    return;
  }

  /**
   * Accessor on the argument values.
   * 
   * @param argumentType
   *          the target argument type
   * @return the corresponding value, if sets
   * @throws InvalidArgumentException
   */
  final public Object getArgumentValue(ArgumentType argumentType) throws InvalidArgumentException {

    if (!isArgumentTypeRequired(argumentType)) {
      throw new InvalidArgumentException(NLS.bind(Messages.argumentNotInTheScopeOfWrapper,
          new Object[] { argumentType.getLiteral(), _tool.getName() }));
    }

    return _arguments.get(argumentType);
  }

  /**
   * Check is the argument ArgumentType is well initialized
   * 
   * @param argumentType
   *          the target ArgumentType
   * @return
   * @throws InvalidArgumentException
   */
  final public boolean checkValue(ArgumentType argumentType) throws InvalidArgumentException {

    boolean ret = true;

    if (!isArgumentTypeRequired(argumentType)) {
      throw new InvalidArgumentException(NLS.bind(Messages.argumentNotInTheScopeOfWrapper,
          new Object[] { argumentType.getLiteral(), _tool.getName() }));
    }

    if (argumentType == ArgumentType.COLLECTION) {
      return checkValueForCollectionType(argumentType);
    }

    Object matchingArgument = _arguments.get(argumentType);

    // FIXME DDiagram case should be to incorporate with a cleaner approach, even if
    // it seems to be "as" the Sirius approach
    if (matchingArgument instanceof DDiagram) {
      return true;
    }

    if (!argumentType.getClassType().isAssignableFrom(matchingArgument.getClass())) {
      // the argument is not the good type of object
      ret = false;
    }
    // Let's check additional data for EObject
    if (argumentType.getClassType().equals(EObject.class)) {
      EObject eObject = (EObject) matchingArgument;
      EClass eClass = eObject.eClass();

      ArgumentData argData = getArgumentData(argumentType);
      EClass tgtEclass = argData.getEClass();

      if ((null != tgtEclass) && !tgtEclass.isSuperTypeOf(eClass)) {
        ret = false;
      }
    }

    return ret;
  }

  /**
   * @param argumentType
   * @return
   * @see #checkValue
   */
  @SuppressWarnings("unchecked")
  final protected boolean checkValueForCollectionType(ArgumentType argumentType) {

    boolean ret = true;

    Object matchingArgument = _arguments.get(argumentType);

    if (!argumentType.getClassType().isAssignableFrom(matchingArgument.getClass())) {
      // the argument is not the good type of object
      ret = false;
    }

    Collection<? extends EObject> col = (Collection<? extends EObject>) matchingArgument;

    if (!col.isEmpty()) {

      ArgumentData argData = getArgumentData(argumentType);
      EClass tgtEclass = argData.getEClass();

      // Let's test all element
      Iterator<? extends EObject> it = col.iterator();
      EObject eObject = null;
      EClass eClass = null;
      while (it.hasNext()) {
        eObject = it.next();
        eClass = eObject.eClass();

        if ((null != tgtEclass) && !tgtEclass.isSuperTypeOf(eClass)) {
          ret = false;
        }
      }
    } else {
      // Let's consider that an empty list does not fit
      ret = false;
    }

    // Let's check additional data for EObject
    if (argumentType.getClassType().equals(EObject.class)) {
      EObject eObject = (EObject) matchingArgument;
      EClass eClass = eObject.eClass();

      ArgumentData argData = getArgumentData(argumentType);
      EClass tgtEclass = argData.getEClass();

      if ((null != tgtEclass) && !tgtEclass.isSuperTypeOf(eClass)) {
        ret = false;
      }
    }

    return ret;
  }

  /** reset the argument values list */
  final public void clearArgumentValues() {
    _arguments.clear();

    return;
  }

  /**
   * Check if the {@link ArgumentType} argumentType is required
   * 
   * @param argumentType
   *          the {@link ArgumentType} to check
   * @return true whether required
   */
  protected boolean isArgumentTypeRequired(ArgumentType argumentType) {

    boolean ret = false;

    for (ArgumentData current : getArgumentTypes()) {
      if (current.getType().equals(argumentType)) {
        ret = true;
        break;
      }
    }

    return ret;
  }

  /**
   * return the {@link ArgumentData} corresponding to the {@link ArgumentType} argumentType
   * 
   * @param argumentType
   *          the target ArgumentType_Enum
   * @return null whether not found
   */
  protected ArgumentData getArgumentData(ArgumentType argumentType) {
    ArgumentData ret = null;

    for (ArgumentData current : getArgumentTypes()) {
      if (current.getType().equals(argumentType)) {
        ret = current;
        break;
      }
    }

    return ret;
  }

}
