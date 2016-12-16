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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.KeyPart;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.information.communication.Exception;
import org.polarsys.capella.core.data.information.datavalue.NumericReference;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;

/**
 * Class helpers
 */
public class ClassExt {

  static public boolean containsKeyPart(Class class_p, Property property_p) {
    return (getRelatedKeyPart(class_p, property_p) != null);
  }

  /**
   * @param context a Capella Element
   * @return all the classes contained in the current and previous architectures
   */
  public static Collection<Class> getAllClasses(final EObject context) {
    return QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_CLASSES, context);
  }

  /**
   * @see #getClassDependencies(Class)
   */
  public static Map<AbstractDependenciesPkg, Collection<EObject>> getClassDependencies2(Class class_p) {

    Map<AbstractDependenciesPkg, Collection<EObject>> result =
        new HashMap<AbstractDependenciesPkg, Collection<EObject>>();

    // types and cardinalities for properties
    for (Property aProperty : class_p.getContainedProperties()) {
      AbstractDependenciesPkgExt.checkDependenciesAndAddToResult(result, aProperty.getType());
      checkDependenciesAndAddToResult(result, aProperty.getOwnedMaxCard());
      checkDependenciesAndAddToResult(result, aProperty.getOwnedMinCard());
    }

    // Operations
    for (Operation anOperation : class_p.getContainedOperations()) {
      // thrown exceptions
      if (anOperation instanceof Service) {
        for (Exception anException : (((Service) anOperation).getThrownExceptions())) {
          AbstractDependenciesPkgExt.checkDependenciesAndAddToResult(result, anException);
        }
      }
      // parameters, type and cardinalities
      for (Parameter aParameter : anOperation.getOwnedParameters()) {
        AbstractDependenciesPkgExt.checkDependenciesAndAddToResult(result, aParameter.getType());
        checkDependenciesAndAddToResult(result, aParameter.getOwnedMaxCard());
        checkDependenciesAndAddToResult(result, aParameter.getOwnedMinCard());
      }
    }

    // superClasses
    for (Generalization aGeneralization : class_p.getSuperGeneralizations()) {
      AbstractDependenciesPkgExt.checkDependenciesAndAddToResult(result, aGeneralization.getSuper());
    }

    return result;
  }

  /**
   * Added to handle numeric references in cardinalities. FIXME: I don't take chains of references into account.
   * @param result_p
   * @param card
   */
  private static void checkDependenciesAndAddToResult(Map<AbstractDependenciesPkg, Collection<EObject>> result_p,
      NumericValue card) {
    if (card instanceof NumericReference) {
      AbstractDependenciesPkgExt.checkDependenciesAndAddToResult(result_p,
          ((NumericReference) card).getReferencedValue());
    }
  }

  /**
   * Find all packages upon which a certain class depends on. E.g. If a class C has a property that has a certain type defined in package X, C depends on X
   * @param class_p
   * @return Packages that this class depends on
   */
  public static Collection<AbstractDependenciesPkg> getClassDependencies(Class class_p) {
    return getClassDependencies2(class_p).keySet();
  }

  /**
   * This method returns the class package of the current class
   * @param class_p
   * @return
   */
  public static DataPkg getOwnerDataPkg(Class class_p) {
    if (class_p.eContainer() instanceof Class) {
      return getOwnerDataPkg((Class) class_p.eContainer());
    } else if (class_p.eContainer() instanceof DataPkg) {
      return (DataPkg) class_p.eContainer();
    }

    return null;
  }

  static public KeyPart getRelatedKeyPart(Class class_p, Property property_p) {
    for (KeyPart keyPart : class_p.getKeyParts()) {
      if ((keyPart.getProperty() != null) && keyPart.getProperty().equals(property_p)) {
        return keyPart;
      }
    }
    return null;
  }

  /**
   * This method returns the root class package of the current class
   * @param class_p
   * @return
   */
  public static DataPkg getRootOwnerDataPkg(Class class_p) {
    if (class_p.eContainer() instanceof Class) {
      return getRootOwnerDataPkg((Class) class_p.eContainer());
    } else if (class_p.eContainer() instanceof DataPkg) {
      return DataPkgExt.getRootDataPkg((DataPkg) class_p.eContainer());
    }

    return null;
  }

  /**
   * @param class_p
   * @return
   */
  public static List<Association> getOutgoingAssociations(Classifier classif_p) {
    List<Association> result = new ArrayList<Association>();
    List<Association> allAssociations = getAllAssociations(classif_p);
    for (Association assoc : allAssociations) {
      if (AssociationExt.isUnidirectional(assoc)
          && AssociationExt.getOwnedMembersClassifiers(assoc).contains(classif_p)) {
        result.add(assoc);
      }
    }
    return result;

  }

  /**
   * @param classif_p
   * @return
   */
  public static List<Association> getIncomingAssociations(Classifier classif_p) {
    List<Association> result = new ArrayList<Association>();
    List<Association> allAssociations = getAllAssociations(classif_p);
    for (Association assoc : allAssociations) {
      if (AssociationExt.isUnidirectional(assoc)
          && AssociationExt.getNavigableMembersClassifiers(assoc).contains(classif_p)) {
        result.add(assoc);
      }
    }
    return result;

  }

  /**
   * @param class_p_p
   * @return
   */
  public static List<Association> getNondirectionalAssociations(Classifier classif_p) {
    List<Association> allAssociations = getAllAssociations(classif_p);
    List<Association> result = new ArrayList<Association>();
    for (Association assoc : allAssociations) {
      if (AssociationExt.isNondirectional(assoc)
          && AssociationExt.getOwnedMembersClassifiers(assoc).contains(classif_p)) {
        result.add(assoc);
      }
    }
    return result;
  }

  /**
   * @param class_p_p
   * @return
   */
  public static List<Association> getBidirectionalAssociations(Classifier classif_p) {
    List<Association> allAssociations = getAllAssociations(classif_p);
    List<Association> result = new ArrayList<Association>();
    for (Association assoc : allAssociations) {
      if (AssociationExt.isBidirectional(assoc)
          && AssociationExt.getNavigableMembersClassifiers(assoc).contains(classif_p)) {
        result.add(assoc);
      }
    }
    return result;
  }

  public static List<Association> getAllAssociationsButIncoming(Classifier classif_p) {
    List<Association> result = new ArrayList<Association>();
    result.addAll(getOutgoingAssociations(classif_p));
    result.addAll(getBidirectionalAssociations(classif_p));
    result.addAll(getNondirectionalAssociations(classif_p));
    return result;
  }

  public static List<Association> getAllAssociations(Classifier classif_p) {
    List<Association> result = new ArrayList<Association>();
    BlockArchitecture arch = BlockArchitectureExt.getRootBlockArchitecture(classif_p);
    if (arch != null) {
      DataPkg dataPkg = arch.getOwnedDataPkg();
      result = DataPkgExt.getAllAssociations(dataPkg);
    }
    return result;
  }
}
