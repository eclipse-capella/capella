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
package org.polarsys.capella.test.platform.ju.testcases;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.junit.Assert;
import org.polarsys.capella.common.libraries.LibrariesPackage;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;
import org.polarsys.capella.core.ui.resources.CapellaUIResourcesPlugin;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * This test makes sure every class in Capella has an image for its properties wizard. 
 */
public class PropertiesWizardIconTest extends BasicTestCase {
  
  List<EClass> IGNORED_ECLASSES = Arrays.asList( //
      InteractionPackage.eINSTANCE.getAbstractCapabilityExtend(),
      InteractionPackage.eINSTANCE.getAbstractCapabilityExtensionPoint(),
      InteractionPackage.eINSTANCE.getAbstractCapabilityGeneralization(),
      InteractionPackage.eINSTANCE.getAbstractCapabilityInclude(),
      InteractionPackage.eINSTANCE.getArmTimerEvent(),
      InteractionPackage.eINSTANCE.getCancelTimerEvent(),
      InteractionPackage.eINSTANCE.getCreationEvent(),
      InteractionPackage.eINSTANCE.getDestructionEvent(),
      InteractionPackage.eINSTANCE.getEventReceiptOperation(),
      InteractionPackage.eINSTANCE.getEventSentOperation(),
      InteractionPackage.eINSTANCE.getFragmentEnd(),
      InteractionPackage.eINSTANCE.getExecutionEnd(),
      InteractionPackage.eINSTANCE.getExecutionEvent(),
      InteractionPackage.eINSTANCE.getGate(),
      InteractionPackage.eINSTANCE.getInteractionOperand(),
      InteractionPackage.eINSTANCE.getInteractionState(),
      InteractionPackage.eINSTANCE.getMessageEnd(),
      InteractionPackage.eINSTANCE.getSequenceMessageValuation(),
      InteractionPackage.eINSTANCE.getStateFragment(),
      
      OaPackage.eINSTANCE.getCapabilityConfiguration(),
      OaPackage.eINSTANCE.getCommunityOfInterest(),
      OaPackage.eINSTANCE.getCommunityOfInterestComposition(),
      OaPackage.eINSTANCE.getConcept(),
      OaPackage.eINSTANCE.getConceptCompliance(),
      OaPackage.eINSTANCE.getConceptPkg(),
      OaPackage.eINSTANCE.getItemInConcept(),
      OaPackage.eINSTANCE.getLocation(),
      OaPackage.eINSTANCE.getOrganisationalUnit(),
      OaPackage.eINSTANCE.getOrganisationalUnitComposition(),
      OaPackage.eINSTANCE.getRoleAssemblyUsage(),
      OaPackage.eINSTANCE.getSwimlane(),
      
      RePackage.eINSTANCE.getCatalogElement(),
      RePackage.eINSTANCE.getCatalogElementPkg(),
      RePackage.eINSTANCE.getCompliancyDefinition(),
      RePackage.eINSTANCE.getCompliancyDefinitionPkg(),
      RePackage.eINSTANCE.getGroupingElementPkg(),
      RePackage.eINSTANCE.getRecCatalog(),
      
      DeploymentPackage.eINSTANCE.getConnectionInstance(),
      DeploymentPackage.eINSTANCE.getDeploymentAspect(),
      DeploymentPackage.eINSTANCE.getComponentInstance(),
      DeploymentPackage.eINSTANCE.getPortInstance(),
      DeploymentPackage.eINSTANCE.getInstanceDeploymentLink(),
      
      CtxPackage.eINSTANCE.getCapabilityExploitation(),
      CtxPackage.eINSTANCE.getSystemCommunication(),
      CtxPackage.eINSTANCE.getSystemCommunicationHook(),
      
      InformationPackage.eINSTANCE.getCollectionValueReference(),
      InformationPackage.eINSTANCE.getKeyPart(),
      
      FaPackage.eINSTANCE.getComponentExchangeEnd(),
      FaPackage.eINSTANCE.getExchangeContainment(),
      FaPackage.eINSTANCE.getFunctionalChainReference(),
      FaPackage.eINSTANCE.getFunctionalExchangeSpecification(),
      FaPackage.eINSTANCE.getFunctionSpecification(),
      FaPackage.eINSTANCE.getExchangeLink(),
      
      
      SharedmodelPackage.eINSTANCE.getGenericPkg(),
      SharedmodelPackage.eINSTANCE.getSharedPkg(),
      
      LibrariesPackage.eINSTANCE.getLibraryReference(),
      LibrariesPackage.eINSTANCE.getModelInformation(),
      LibrariesPackage.eINSTANCE.getModelVersion(),

      PaPackage.eINSTANCE.getPhysicalArchitecturePkg(),
      PaPackage.eINSTANCE.getPhysicalNode(),
      
      LaPackage.eINSTANCE.getLogicalArchitecturePkg(),
      
      CapellacorePackage.eINSTANCE.getKeyValue(),
      CapellacorePackage.eINSTANCE.getNamingRule(),

      CommunicationPackage.eINSTANCE.getMessageReference(),
      CommunicationPackage.eINSTANCE.getSignalInstance(),

      DatavaluePackage.eINSTANCE.getOpaqueExpression(),
      
      CsPackage.eINSTANCE.getPhysicalPathReference(),
      CsPackage.eINSTANCE.getPhysicalLinkEnd(),
      
      CapellamodellerPackage.eINSTANCE.getFolder(),
      CapellamodellerPackage.eINSTANCE.getProject(),
      CapellamodellerPackage.eINSTANCE.getLibrary(),
      CapellamodellerPackage.eINSTANCE.getSystemEngineeringPkg(),
      
      EpbsPackage.eINSTANCE.getEPBSArchitecturePkg()
  );
  
  @Override
  public void test() throws Exception {
    Set<EClass> eClassesWithoutImage = new HashSet<EClass>();
    
    EPackage.Registry.INSTANCE.keySet().stream() //
    .filter(nsURI -> nsURI.startsWith("http://www.polarsys.org/capella")) //
    .forEach(nsURI -> {  //
      EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
      eClassesWithoutImage.addAll(findEClassesWithoutImageByPackage(ePackage));
    });
    
    int numberOfEClassesWithoutImage = eClassesWithoutImage.size();
    StringBuilder message = new StringBuilder();
    message.append("There are " + numberOfEClassesWithoutImage + " classes without image for its properties wizard. ");
    if (numberOfEClassesWithoutImage > 0) {
      for (EClass eClass : eClassesWithoutImage) {
        message.append(eClass.getName() + "; ");
      }
    }
    
    Assert.assertEquals(message.toString(), 0, numberOfEClassesWithoutImage);
  }

  private Set<EClass> findEClassesWithoutImageByPackage(EPackage ePackage) {
    EFactory eFactory = ePackage.getEFactoryInstance();
    return ePackage.getEClassifiers().stream() //
        .filter(EClass.class::isInstance) //
        .map(EClass.class::cast) //
        .filter(c -> !IGNORED_ECLASSES.contains(c)) // Filter the ECLASS which is ignored
        .filter(c ->
        {
          try {
            EObject eObject = eFactory.create(c);
            return CapellaUIResourcesPlugin.getDefault().getPNGImage(eObject) == null;
          } catch (IllegalArgumentException e) {
            return false; // In case the class is abstract or it is not possible to create an instance, it does not need an image.
          }
        }) //
        .collect(Collectors.toSet());
  }
}
