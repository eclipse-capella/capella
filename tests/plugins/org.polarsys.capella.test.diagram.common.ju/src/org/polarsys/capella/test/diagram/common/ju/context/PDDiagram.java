/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.context;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacore.AbstractDependenciesPkg;
import org.polarsys.capella.core.model.helpers.AbstractDependenciesPkgExt;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.core.sirius.analysis.InformationServices;
import org.polarsys.capella.core.sirius.analysis.constants.IDNDToolNameConstants;
import org.polarsys.capella.core.sirius.analysis.constants.IToolNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;

public class PDDiagram extends CommonDiagram {

  public PDDiagram(SessionContext context, DDiagram diagram) {
    super(context, diagram);
  }

  public static PDDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {

    String name = IDiagramNameConstants.PACKAGE_DEPENDENCIES_DIAGRAM_NAME;

    return (PDDiagram) new CreateDiagramStep(executionContext, targetIdentifier, name) {
      @Override
      public DiagramContext getResult() {
        return new PDDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public static PDDiagram openDiagram(SessionContext executionContext, String name) {
    return (PDDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new PDDiagram(getExecutionContext(), diagram);
      }
    }.run().open();
  }

  public void insertDataPackages(String parentContainerId, String... elementsToBeInsertedIds) {
    insertElements(parentContainerId, IToolNameConstants.TOOL_PD_INSERT_REMOVE_DATA_PACKAGES, elementsToBeInsertedIds);
  }

  public void removeDataPackages(String parentContainerId, String... elementsToBeRemovedIds) {
    removeElements(parentContainerId, IToolNameConstants.TOOL_PD_INSERT_REMOVE_DATA_PACKAGES, elementsToBeRemovedIds);
  }

  public void insertInterfacePackages(String parentContainerId, String... elementsToBeInsertedIds) {
    insertElements(parentContainerId, IToolNameConstants.TOOL_PD_INSERT_REMOVE_INTERFACE_PACKAGES,
        elementsToBeInsertedIds);
  }

  public void removeInterfacePackages(String parentContainerId, String... elementsToBeRemovedIds) {
    removeElements(parentContainerId, IToolNameConstants.TOOL_PD_INSERT_REMOVE_INTERFACE_PACKAGES,
        elementsToBeRemovedIds);
  }

  public void dragAndDropInterfacePackage(String containerId, String elementId) {
    dragAndDropFromExplorer(elementId, containerId, IDNDToolNameConstants.TOOL_PD_DND_INTERFACEPKG_FROM_EXPLORER);
  }

  /**
   * Method that computes all packages that depend on or are a dependency for a given package
   * 
   * @param packageID
   *          Id of the semantic package we want the dependencies for
   * @return A list of all dependencies for the package identified by the supplied ID
   */
  private List<AbstractDependenciesPkg> getAllDependenciesOfPackage(String packageID) {

    AbstractDependenciesPkg semanticPackage = getSessionContext().getSemanticElement(packageID);
    Collection<AbstractDependenciesPkg> deps = AbstractDependenciesPkgExt.getDependencies(semanticPackage);
    Collection<AbstractDependenciesPkg> inverseDeps = AbstractDependenciesPkgExt
        .getInverseDependencies(semanticPackage);

    List<AbstractDependenciesPkg> allDependencies = new ArrayList<>(deps);
    allDependencies.addAll(inverseDeps);

    return allDependencies;
  }

  /**
   * Method that computes all package elements that are displayed on the current diagram context
   * 
   * @param packageList
   *          A list of package elements
   * 
   * @return List of all package elements that are displayed on the current diagram context
   */
  private List<AbstractDependenciesPkg> getAlreadyInsertedPackages(List<AbstractDependenciesPkg> packageList) {

    List<AbstractDependenciesPkg> alreadyInsertedPackages = new ArrayList<>();
    for (AbstractDependenciesPkg pkg : packageList) {
      if (DiagramServices.getDiagramServices().getDiagramElement(getDiagram(), pkg) != null) {
        alreadyInsertedPackages.add(pkg);
      }
    }

    return alreadyInsertedPackages;
  }

  /**
   * Method that executes an insert/remove command on a list of packages
   * 
   * @param sameResourceSetPackages
   *          A list of package elements that belong to the same resource set. This is needed to retrieve the execution
   *          manager who will execute the insert/remove command. Usually, packages that you want to insert or remove
   *          are part of the same resource set.
   * @param packagesToBeDisplayed
   *          A list of packages that will remain displayed on the diagram after the command has been called.
   * @param allDependenciesOfAPackage
   *          A list of all packages that depend on or are a dependency for a certain package.
   */
  private void executeInsertRemoveCommandOnDependencies(List<AbstractDependenciesPkg> sameResourceSetPackages,
      List<AbstractDependenciesPkg> packagesToBeDisplayed, List<AbstractDependenciesPkg> allDependenciesOfAPackage) {

    AbstractReadWriteCommand insertRemoveCommand = new AbstractReadWriteCommand() {

      @Override
      public void run() {
        InformationServices.getService().showHidePDDependentPkgs(getDiagram(), packagesToBeDisplayed,
            allDependenciesOfAPackage);
      }
    };

    ExecutionManager manager = TransactionHelper.getExecutionManager(sameResourceSetPackages);
    manager.execute(insertRemoveCommand);
  }

  /**
   * Method that will call an insert command on packages identified by the supplied IDs
   * 
   * @param parentContainerId
   *          The ID of the container element in which the elements will be inserted
   * @param elementsToBeInsertedIds
   *          IDs to identify the package elements that will be inserted
   */
  public void insertDependencies(String parentContainerId, String... elementsToBeInsertedIds) {

    List<AbstractDependenciesPkg> dependencyPackagesToInsert = Arrays.asList(elementsToBeInsertedIds).stream()
        .map(id -> getSessionContext().getSemanticElement(id)).map(AbstractDependenciesPkg.class::cast)
        .collect(Collectors.toList());

    List<AbstractDependenciesPkg> allDependencies = getAllDependenciesOfPackage(parentContainerId);
    List<AbstractDependenciesPkg> dependenciesToBeDisplayed = getAlreadyInsertedPackages(allDependencies);

    /*
     * Basically, to insert/remove packages we add or remove from the list of already inserted dependencies. The
     * insert/remove command defined in .odesign will check which elements should be added or removed from the diagram.
     * It will make a comparison between the existing diagram elements and the list supplied by the user, in this case,
     * the "dependenciesToBeDisplayed" variable.
     */
    dependenciesToBeDisplayed.addAll(dependencyPackagesToInsert);

    executeInsertRemoveCommandOnDependencies(dependencyPackagesToInsert, dependenciesToBeDisplayed, allDependencies);

    DiagramHelper.refreshDiagram(getDiagram());

    for (AbstractDependenciesPkg pkg : dependencyPackagesToInsert) {
      hasView(pkg.getId());
    }
  }

  /**
   * Method that will call a remove command on packages identified by the supplied IDs
   * 
   * @param parentContainerId
   *          The ID of the container element the supplied elements will be removed from
   * @param elementsToBeInsertedIds
   *          IDs to identify the package elements that will be inserted
   */
  public void removeDependencies(String parentContainerId, String... elementsToBeRemovedIds) {

    List<AbstractDependenciesPkg> dependencyPackagesToRemove = Arrays.asList(elementsToBeRemovedIds).stream()
        .map(id -> getSessionContext().getSemanticElement(id)).map(AbstractDependenciesPkg.class::cast)
        .collect(Collectors.toList());

    List<AbstractDependenciesPkg> allDependencies = getAllDependenciesOfPackage(parentContainerId);
    List<AbstractDependenciesPkg> dependenciesToBeDisplayed = getAlreadyInsertedPackages(allDependencies);

    /*
     * Basically, to insert/remove packages we add or remove from the list of already inserted dependencies. The
     * insert/remove command defined in .odesign will check which elements should be added or removed from the diagram.
     * It will make a comparison between the existing diagram elements and the list supplied by the user, in this case,
     * the "dependenciesToBeDisplayed" variable.
     */
    dependenciesToBeDisplayed.removeAll(dependencyPackagesToRemove);

    executeInsertRemoveCommandOnDependencies(dependencyPackagesToRemove, dependenciesToBeDisplayed, allDependencies);

    DiagramHelper.refreshDiagram(getDiagram());

    for (AbstractDependenciesPkg pkg : dependencyPackagesToRemove) {
      hasntView(pkg.getId());
    }
  }

  public void checkIfDependencyIsCorrect(AbstractDependenciesPkg subPkg, AbstractDependenciesPkg superPkg) {

    /*
     * Get the edge representation of the subPkg The dependency link has the same semantic object as the package
     * representation
     */
    DDiagramElement dependencyEdge = DiagramHelper.getDDiagramElementByEClass(getDiagram(), subPkg,
        DiagramPackage.Literals.DEDGE);
    DDiagramElement superPkgView = getView(superPkg);

    assertTrue("Target Dependency for subPackage " + subPkg.getName() + " is not " + superPkg.getName(),
        superPkgView == ((DEdge) dependencyEdge).getTargetNode());
  }

}
