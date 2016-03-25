/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Union;
import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.data.information.communication.Exception;
import org.polarsys.capella.core.data.information.communication.Message;
import org.polarsys.capella.core.data.information.communication.Signal;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.sharedmodel.GenericPkg;

/**
 * GenericPkg helpers
 */
public class GenericPkgExt {

  /**
   * Retrieves all interfaces from the current GenericPkg
   * @param pkg
   *          the current GenericPkg
   * @return List<Interface>
   */
  public static List<Interface> getAllInterfaces(GenericPkg pkg) {
    List<Interface> lst = new ArrayList<Interface>();

    for (CapellaElement elt : pkg.getCapellaElements()) {
      if (elt instanceof Interface) {
        lst.add((Interface) elt);
      } else if (elt instanceof InterfacePkg) {
        lst.addAll(InterfacePkgExt.getAllInterfaces((InterfacePkg) elt));
      } else if (elt instanceof GenericPkg) {
        lst.addAll(getAllInterfaces((GenericPkg) elt));
      }
    }

    return lst;
  }

  /**
   * Retrieves all classes from the current GenericPkg
   * @param pkg
   *          the current GenericPkg
   * @return List<Class>
   */
  public static List<Class> getAllClasses(GenericPkg pkg) {
    List<Class> lst = new ArrayList<Class>();

    for (CapellaElement elt : pkg.getCapellaElements()) {
      if (elt instanceof Class) {
        lst.add((Class) elt);
      } else if (elt instanceof DataPkg) {
        lst.addAll(DataPkgExt.getAllClasses((DataPkg) elt));
      } else if (elt instanceof GenericPkg) {
        lst.addAll(getAllClasses((GenericPkg) elt));
      }
    }

    return lst;
  }

  /**
   * Retrieves all unions from the current GenericPkg
   * @param pkg
   *          the current GenericPkg
   * @return List<Class>
   */
  public static List<Union> getAllUnions(GenericPkg pkg) {
    List<Union> lst = new ArrayList<Union>();

    for (CapellaElement elt : pkg.getCapellaElements()) {
      if (elt instanceof Union) {
        lst.add((Union) elt);
      } else if (elt instanceof DataPkg) {
        lst.addAll(DataPkgExt.getAllUnions((DataPkg) elt));
      } else if (elt instanceof GenericPkg) {
        lst.addAll(getAllUnions((GenericPkg) elt));
      }
    }

    return lst;
  }
  
  /**
   * Retrieves all signals from the current GenericPkg
   * @param pkg
   *          the current GenericPkg
   * @return List<Signal>
   */
  public static List<Signal> getAllSignals(GenericPkg pkg) {
    List<Signal> lst = new ArrayList<Signal>();

    for (CapellaElement elt : pkg.getCapellaElements()) {
      if (elt instanceof Signal) {
        lst.add((Signal) elt);
      } else if (elt instanceof DataPkg) {
        lst.addAll(DataPkgExt.getAllSignals((DataPkg) elt));
      } else if (elt instanceof GenericPkg) {
        lst.addAll(getAllSignals((GenericPkg) elt));
      }
    }

    return lst;
  }

  /**
   * Retrieves all message from the current GenericPkg
   * @param pkg
   *          the current GenericPkg
   * @return List<Message>
   */
  public static List<Message> getAllMessages(GenericPkg pkg) {
    List<Message> lst = new ArrayList<Message>();

    for (CapellaElement elt : pkg.getCapellaElements()) {
      if (elt instanceof Message) {
        lst.add((Message) elt);
      } else if (elt instanceof DataPkg) {
        lst.addAll(DataPkgExt.getAllMessages((DataPkg) elt));
      } else if (elt instanceof GenericPkg) {
        lst.addAll(getAllMessages((GenericPkg) elt));
      }
    }

    return lst;
  }

  /**
   * Retrieves all datatypes from the current GenericPkg
   * @param pkg
   *          the current GenericPkg
   * @return List<DataType>
   */
  public static List<DataType> getAllDataTypes(GenericPkg pkg) {
    List<DataType> lst = new ArrayList<DataType>();

    for (CapellaElement elt : pkg.getCapellaElements()) {
      if (elt instanceof DataType) {
        lst.add((DataType) elt);
      } else if (elt instanceof DataPkg) {
        lst.addAll(DataPkgExt.getAllDataTypes((DataPkg) elt));
      } else if (elt instanceof GenericPkg) {
        lst.addAll(getAllDataTypes((GenericPkg) elt));
      }
    }

    return lst;
  }

  /**
   * Retrieves all datatypes from the current GenericPkg
   * @param pkg
   *          the current GenericPkg
   * @return List<DataType>
   */
  public static List<DataValue> getAllDataValues(GenericPkg pkg) {
    List<DataValue> lst = new ArrayList<DataValue>();

    for (CapellaElement elt : pkg.getCapellaElements()) {
      if (elt instanceof DataValue) {
        lst.add((DataValue) elt);
      } else if (elt instanceof DataPkg) {
        lst.addAll(DataPkgExt.getAllDataValues((DataPkg) elt));
      } else if (elt instanceof GenericPkg) {
        lst.addAll(getAllDataValues((GenericPkg) elt));
      }
    }

    return lst;
  }

  /**
   * Retrieves all Actors from the current GenericPkg
   * @param pkg
   *          the current GenericPkg
   * @return List<Actor>
   */
  public static List<Actor> getAllActors(GenericPkg pkg) {
    List<Actor> lst = new ArrayList<Actor>();

    for (CapellaElement elt : pkg.getCapellaElements()) {
      if (elt instanceof Actor) {
        lst.add((Actor) elt);
      } else if (elt instanceof ActorPkg) {
        lst.addAll(ActorPkgExt.getAllActors((ActorPkg) elt));
      } else if (elt instanceof GenericPkg) {
        lst.addAll(getAllActors((GenericPkg) elt));
      }
    }
    return lst;
  }

  public static List<Unit> getAllUnits(GenericPkg pkg) {
    List<Unit> lst = new ArrayList<Unit>();
    for (CapellaElement elt : pkg.getCapellaElements()) {
      if (elt instanceof Unit) {
        lst.add((Unit) elt);
      } else if (elt instanceof DataPkg) {
        lst.addAll(DataPkgExt.getAllUnits((DataPkg) elt));
      } else if (elt instanceof GenericPkg) {
        lst.addAll(getAllUnits((GenericPkg) elt));
      }
    }
    return lst;
  }

  public static List<Exception> getAllExceptions(GenericPkg pkg) {
    List<Exception> lst = new ArrayList<Exception>();
    for (CapellaElement elt : pkg.getCapellaElements()) {
      if (elt instanceof Exception) {
        lst.add((Exception) elt);
      } else if (elt instanceof DataPkg) {
        lst.addAll(DataPkgExt.getAllExceptions((DataPkg) elt));
      } else if (elt instanceof GenericPkg) {
        lst.addAll(getAllExceptions((GenericPkg) elt));
      }
    }
    return lst;
  }
}
