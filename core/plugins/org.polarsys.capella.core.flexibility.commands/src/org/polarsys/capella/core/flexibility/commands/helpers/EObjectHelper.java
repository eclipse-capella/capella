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
package org.polarsys.capella.core.flexibility.commands.helpers;

import java.lang.reflect.Field;
import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
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

  public String getID(EObject object_p) {
    return "_id_" + getIdentifier(object_p);
  }

  public String getVariable(EObject object_p) {
    return "_" + getIdentifier(object_p);
  }

  public String getIdentifier(EObject object_p) {
    return getIdentifier(object_p, true);
  }

  public String getIdentifier(EObject object_p, boolean isLast_p) {
    String name = object_p.eClass().getName().toUpperCase();
    try {
      name = EObjectLabelProviderHelper.getText(object_p);
    } catch (Exception e) {

    }

    if (object_p instanceof Part) {
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
      } catch (Exception exception_p) {
      }
    }

    if (clazzes.contains(object_p.eClass())) {
      String className =
          object_p.eClass().getName()
              .replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
      String MAJ = object_p.eClass().getName().replaceAll("[a-z]", "");
      name = name.replace(className, MAJ);
    }

    if (namings.contains(name)) {
    }

    name = name.toUpperCase();
    name = name.replace(" ", "_");
    name = name.replace(":", "");
    name = name.replace("[", "");
    name = name.replace("]", "");

    name = name.replace("EPBSA", "EPBS");

    if (!isLast_p) {
      name = name.replace("PHYSICAL_SYSTEM", "");
      name = name.replace("LOGICAL_SYSTEM", "");
      name = name.replace("SYSTEM_FUNCTIONS", "");
      name = name.replace("LOGICAL_FUNCTIONS", "");
      name = name.replace("PHYSICAL_FUNCTIONS", "");
    }

    name = name.replace("COMPONENT_EXCHANGE_ALLOCATION", "cea");
    return name;
  }

  public String getName(Object object_p) {
    if (object_p instanceof EObject) {
      return EObjectLabelProviderHelper.getText((EObject) object_p);
    } else if (object_p instanceof EList) {
      int i = 0;
      EList list = (EList) object_p;
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
    } else if (object_p instanceof Enumerator) {
      return ((Enumerator) object_p).getName();
    }
    return object_p.toString();

  }

  public String getIDValue(EObject object_p) {
    return EcoreUtil.getID(object_p);
  }

  public String getID2(EObject object_p) {
    return getID2(object_p, true);
  }

  /**
   * @param object_p
   * @return
   */
  public String getID2(EObject object_p, boolean isLast_p) {
    if (object_p instanceof Project) {
      return "";
    }
    if (object_p instanceof SystemEngineering) {
      return getIdentifier(object_p, isLast_p);
    }

    if (object_p.eContainer() != null) {
      String parent = getID2(object_p.eContainer(), false);

      String identifier = getIdentifier(object_p, isLast_p);
      if (parent.length() > 0) {
        if (parent.endsWith("__")) {
          return parent + identifier;
        }
        return parent + "__" + identifier;
      }
    }
    return getIdentifier(object_p, isLast_p);
  }

}
