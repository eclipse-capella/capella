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
package org.polarsys.capella.core.flexibility.commands.helpers;

import java.lang.reflect.Field;
import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.ui.services.helper.EObjectImageProviderHelper;
import org.polarsys.capella.core.data.capellacore.KeyValue;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.information.datavalue.util.DataValueNaminghelper;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.model.helpers.naming.NamingConstants;

/**
 *
 */
public class EObjectHelper {

  public static EObjectHelper getInstance() {
    return new EObjectHelper();
  }

  public String getID(EObject object) {
    return "_id_" + getQualifiedIdentifier(object);
  }

  public String getVariable(EObject object) {
    return "_" + getQualifiedIdentifier(object);
  }

  protected String getIdentifier(EObject object, boolean isLast) {
    String name = object.eClass().getName().toUpperCase();
    try {

      if (object instanceof KeyValue) {
        name = ((KeyValue) object).getKey();

      } else if (object instanceof DataValue) {
        String prefix = DataValueNaminghelper.getReferencePrefix((DataValue) object).replace(" ", "");
        if (prefix.isEmpty()) {
          prefix = object.eContainingFeature().getName().replace("owned", "");
        }
        name = prefix + (((((DataValue) object).getName() == null) || (((DataValue) object).getName().isEmpty())) ? "" : "_" + ((DataValue) object).getName());

      } else {
        name = EObjectLabelProviderHelper.getText(object);
      }

    } catch (Exception e) {
    }

    if (object instanceof Part) {
      name = "part_" + name;
    }

    HashSet<EClass> clazzes = new HashSet<EClass>();
    clazzes.add(OaPackage.Literals.OPERATIONAL_ANALYSIS);
    clazzes.add(CtxPackage.Literals.SYSTEM_ANALYSIS);
    clazzes.add(LaPackage.Literals.LOGICAL_ARCHITECTURE);
    clazzes.add(PaPackage.Literals.PHYSICAL_ARCHITECTURE);
    clazzes.add(EpbsPackage.Literals.EPBS_ARCHITECTURE);

    clazzes.add(OaPackage.Literals.OPERATIONAL_ACTIVITY);
    clazzes.add(CtxPackage.Literals.SYSTEM_FUNCTION);
    clazzes.add(LaPackage.Literals.LOGICAL_FUNCTION);
    clazzes.add(PaPackage.Literals.PHYSICAL_FUNCTION);
    clazzes.add(EpbsPackage.Literals.CONFIGURATION_ITEM);

    HashSet<String> namings = new HashSet<String>();

    Field[] files = NamingConstants.class.getDeclaredFields();
    for (Field field : files) {
      try {
        String value = (String) field.get(null);
        namings.add(value);
      } catch (Exception exception) {
      }
    }

    if (clazzes.contains(object.eClass())) {
      String className =
          object.eClass().getName()
              .replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
      String MAJ = object.eClass().getName().replaceAll("[a-z]", "");
      name = name.replace(className, MAJ);
    }

    if (namings.contains(name)) {
    }

    name = name.toUpperCase();
    name = name.replace(" ", "_");
    name = name.replace(".", "_");
    name = name.replace(":", "");
    name = name.replace("[", "");
    name = name.replace("]", "");

    name = name.replace("EPBSA", "EPBS");

    if (!isLast) {
      name = name.replace("PHYSICAL_SYSTEM", "");
      name = name.replace("LOGICAL_SYSTEM", "");
      name = name.replace("SYSTEM_FUNCTIONS", "");
      name = name.replace("LOGICAL_FUNCTIONS", "");
      name = name.replace("PHYSICAL_FUNCTIONS", "");
    }

    name = name.replace("COMPONENT_EXCHANGE_ALLOCATION", "cea");
    return name;
  }

  public String getName(Object object) {
    if (object instanceof EObject) {
      return EObjectLabelProviderHelper.getText((EObject) object);
    } else if (object instanceof EList) {
      int i = 0;
      EList list = (EList) object;
      String result = "{";
      for (Object res : list) {
        result += getName(res);
        if (i < (list.size() - 1)) {
          result += ", ";
        }
        i++;
      }
      result += "}";
      return result;
    } else if (object instanceof Enumerator) {
      return ((Enumerator) object).getName();
    }
    return object.toString();

  }

  public String getIDValue(EObject object) {
    return EcoreUtil.getID(object);
  }

  public String getQualifiedIdentifier(EObject object) {
    return getQualifiedIdentifier(object, true);
  }

  /**
   * @param object
   * @return
   */
  protected String getQualifiedIdentifier(EObject object, boolean isLast) {
    if (object instanceof Project) {
      return "PROJECT_" + getIdentifier(object, isLast);
    }
    if ((object instanceof SystemEngineering)) {
      if ((((Project) object.eContainer()).getOwnedModelRoots().size() == 1)) {
        return isLast ? getIdentifier(object, isLast) : "";
      }
      return getIdentifier(object, isLast);
    }

    if ((object.eContainer() != null)) {
      String parent = getQualifiedIdentifier(object.eContainer(), false);

      String identifier = getIdentifier(object, isLast);
      if (parent.length() > 0) {
        if (parent.endsWith("__")) {
          return parent + identifier;
        }
        return parent + "__" + identifier;
      }
    }
    return getIdentifier(object, isLast);
  }
}
