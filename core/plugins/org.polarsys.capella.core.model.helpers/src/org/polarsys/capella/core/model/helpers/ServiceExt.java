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
package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.information.communication.Exception;
import org.polarsys.capella.core.data.information.communication.Message;
import org.polarsys.capella.core.data.information.communication.MessageReference;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Structure;

/**
 */
public class ServiceExt {

  static public List<Exception> getExceptionsFromRootComponentArchitecture(Service service_p) {
    List<Exception> list = new ArrayList<Exception>();
    Structure rootPkg = getRootOwnerPkg(service_p);
    if (null != rootPkg) {
      ComponentArchitecture compArch = StructureExt.getRootComponentArchitecture(rootPkg);
      list.addAll(getExceptionsFromComponentArchitecture(compArch));
    }

    return list;
  }

  static public List<Message> getMessageFromRootComponentArchitecture(Service service_p) {
    List<Message> list = new ArrayList<Message>(1);
    Structure rootPkg = getRootOwnerPkg(service_p);
    if (null != rootPkg) {
      ComponentArchitecture compArch = StructureExt.getRootComponentArchitecture(rootPkg);
      list.addAll(getMessagesFromComponentArchitecture(compArch));
    }

    return list;
  }
  
  public static ComponentArchitecture getRootComponentArchitecture(Service service_p) {
    ComponentArchitecture root = null;
    Structure rootPkg = getRootOwnerPkg(service_p);
    if (null != rootPkg) {
      root = StructureExt.getRootComponentArchitecture(rootPkg);
    }

    return root;
  }

  static public List<Message> getMessagesFromComponentArchitecture(ComponentArchitecture compArch_p) {
    List<Message> list = new ArrayList<Message>(1);
    if (null != compArch_p) {
      DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(compArch_p);
      if (null != dataPkg) {
        list.addAll(DataPkgExt.getAllMessages(dataPkg));
      }
    }
    return list;
  }
  
  static public List<Exception> getExceptionsFromComponentArchitecture(ComponentArchitecture compArch_p) {
    List<Exception> list = new ArrayList<Exception>(1);
    if (null != compArch_p) {
      DataPkg dataPkg = DataPkgExt.getDataPkgOfComponentArchitecture(compArch_p);
      if (null != dataPkg) {
        list.addAll(DataPkgExt.getAllExceptions(dataPkg));
      }
    }
    return list;
  }

  static public List<Message> getMessageFromRootComponent(Service service_p) {
    List<Message> list = new ArrayList<Message>(1);
    Component parentComp = getRootComponent(service_p);
    if (null != parentComp) {
      list.addAll(getMessagesFromComponent(parentComp));
    }
    return list;
  }
  
  static public List<Exception> getExceptionsFromRootComponent(Service service_p) {
    List<Exception> list = new ArrayList<Exception>(1);
    Component parentComp = getRootComponent(service_p);
    if (null != parentComp) {
      list.addAll(getExceptionsFromComponent(parentComp));
    }
    return list;
  }

  static public List<Message> getMessagesFromComponent(Component component_p) {
    List<Message> list = new ArrayList<Message>(1);
    if (null != component_p) {
      if (component_p instanceof LogicalComponent) {
        DataPkg dataPkg = ((LogicalComponent)component_p).getOwnedDataPkg();
        if (null != dataPkg) {
          list.addAll(DataPkgExt.getAllMessages(dataPkg));
        }
      }
    }
    return list;
  }
  
  static public List<Exception> getExceptionsFromComponent(Component component_p) {
    List<Exception> list = new ArrayList<Exception>(1);
    if (null != component_p) {
      if (component_p instanceof LogicalComponent) {
        DataPkg dataPkg = ((LogicalComponent)component_p).getOwnedDataPkg();
        if (null != dataPkg) {
          list.addAll(DataPkgExt.getAllExceptions(dataPkg));
        }
      }
    }
    return list;
  }

  static public boolean isReferencing(Service service_p, Message message_p) {
    for (MessageReference ref : service_p.getMessageReferences()) {
      if (ref.getMessage().equals(message_p))
        return true;
    }
    return false;
  }
  
  static public boolean isThrowing(Service service_p, Exception exception_p) {
    if(null != service_p) {
      return service_p.getThrownExceptions().contains(exception_p);
    }
    return false;
  }
  
  static public List<Message> getFilteredMessages(Service currentService_p, List<Message> messageList_p) {
    List<Message> list = new ArrayList<Message>(1);
    for (Message message : messageList_p) {
      if (ServiceExt.isReferencing(currentService_p, message))
        continue;
      list.add(message);
    }
    return list;
  }
  
  static public List<Exception> getFilteredExceptions(Service currentService_p, List<Exception> exceptionList_p) {
    List<Exception> list = new ArrayList<Exception>(1);
    for (Exception exception : exceptionList_p) {
      if (ServiceExt.isThrowing(currentService_p, exception))
        continue;
      list.add(exception);
    }
    return list;
  }
  
  static public List<Message> getMessagesFromParentHierarchy(Service currentService_p) {
    List<Message> list = new ArrayList<Message>(1);
    Classifier owningClass = (Classifier) currentService_p.eContainer();
    if (owningClass instanceof Class) {
      DataPkg rootClassPkg = ClassExt.getRootOwnerDataPkg((Class) owningClass);
      list.addAll(DataPkgExt.getMessagesFromParentHierarchy(rootClassPkg));
    }
    else if (owningClass instanceof Interface) {
      InterfacePkg rootInterfacePkg = InterfaceExt.getRootOwnerInterfacePkg((Interface) owningClass);
      list.addAll(DataPkgExt.getMessagesFromParentHierarchy(rootInterfacePkg));
    }
    return list;
  }
  
  static public List<Exception> getExceptionsFromParentHierarchy(Service currentService_p) {
    List<Exception> list = new ArrayList<Exception>(1);
    Classifier owningClass = (Classifier) currentService_p.eContainer();
    if (owningClass instanceof Class) {
      DataPkg rootClassPkg = ClassExt.getRootOwnerDataPkg((Class) owningClass);
      list.addAll(DataPkgExt.getExceptionsFromParentHierarchy(rootClassPkg));
    }
    else if (owningClass instanceof Interface) {
      InterfacePkg rootInterfacePkg = InterfaceExt.getRootOwnerInterfacePkg((Interface) owningClass);
      list.addAll(DataPkgExt.getExceptionsFromParentHierarchy(rootInterfacePkg));
    }
    return list;
  }

  static private Structure getRootOwnerPkg(Service service_p) {
    Structure rootPkg = null;
    Classifier owningClass = (Classifier) service_p.eContainer();
    if (owningClass instanceof Class) {
      // Get from the owning class
      rootPkg = ClassExt.getRootOwnerDataPkg((Class) owningClass);
    }
    else if (owningClass instanceof Interface) {
      // Get from the owning interface
      rootPkg = InterfaceExt.getRootOwnerInterfacePkg((Interface) owningClass);
    }
    return rootPkg;
  }

  static private Component getRootComponent(Service service_p) {
	  Component rootCpnt = null;
	  Classifier owningClass = (Classifier) service_p.eContainer();
	  if (owningClass instanceof Class) {
	    DataPkg rootClassPkg = ClassExt.getRootOwnerDataPkg((Class) owningClass);
	    rootCpnt = DataPkgExt.getRootComponent(rootClassPkg);
	  }
	  else if (owningClass instanceof Interface) {
	    InterfacePkg rootInterfacePkg = InterfaceExt.getRootOwnerInterfacePkg((Interface) owningClass);
	    rootCpnt = InterfacePkgExt.getRootComponent(rootInterfacePkg);
	  }
	  return rootCpnt;
  }
}
