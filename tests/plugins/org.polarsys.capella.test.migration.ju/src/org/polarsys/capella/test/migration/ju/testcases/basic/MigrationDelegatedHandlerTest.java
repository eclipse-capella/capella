package org.polarsys.capella.test.migration.ju.testcases.basic;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.utils.ReflectUtil;
import org.polarsys.capella.core.data.migration.aird.AirdMigrationRunnable;
import org.polarsys.capella.core.data.migration.aird.SiriusMigrationContribution;
import org.polarsys.capella.core.data.migration.capella.ModelMigrationRunnable;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.kitalpha.emde.xmi.XMIExtensionHelperImpl;

/**
 * This test ensures that XmlHelper used while migration is still the intended one. 
 *
 * For aird files, the aird helper shall be used to ensure that sirius silent migration is launched
 * For capella files, the aird helper shall not be used
 */
public class MigrationDelegatedHandlerTest extends BasicTestCase {
  
  @Override
  public void test() throws Exception {
    IProject project = TestHelper.createCapellaProject("test");
    IFile aird = project.getFile("test.aird");
    IFile capella = project.getFile("test.capella");
    AirdMigrationRunnable run = new AirdMigrationRunnable(aird);
    
    // Ensure that aird helper is is defined in AirdMigrationRunnable (ie the aird one)
    XMLResource resource = run.doCreateResource(EcoreUtil2.getURI(aird), new MigrationContext());
    XMIExtensionHelperImpl helper = (XMIExtensionHelperImpl) ReflectUtil.invokeInvisibleMethod(resource, "createXMLHelper");
    assertEquals("The aird xmlHelper while migration is no longer the intended one", AirdMigrationRunnable.class, helper.getClass().getEnclosingClass());
    
    // Ensure that capella helper is defined in ModelMigrationRunnable (ie the capella one)
    XMLResource resource2 = run.doCreateResource(EcoreUtil2.getURI(capella), new MigrationContext());
    XMIExtensionHelperImpl helper2 = (XMIExtensionHelperImpl) ReflectUtil.invokeInvisibleMethod(resource2, "createXMLHelper");
    assertEquals("The capella xmlHelper while migration is no longer the intended one", ModelMigrationRunnable.class, helper2.getClass().getEnclosingClass());

    // Ensure that aird have a field containing the Sirius version
    assertTrue(resource.getDefaultLoadOptions().containsKey(SiriusMigrationContribution.SIRIUS_VERSION));

  }
  
}
