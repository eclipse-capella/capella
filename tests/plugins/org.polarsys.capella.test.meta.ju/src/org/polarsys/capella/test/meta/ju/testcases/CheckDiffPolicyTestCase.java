/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.meta.ju.testcases;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy;
import org.eclipse.emf.diffmerge.impl.scopes.RootedModelScope;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.TestConstants;
import org.polarsys.capella.test.framework.api.AbstractProvider;
import org.polarsys.capella.test.framework.api.BasicTestCaseWithoutTestModel;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;
import org.polarsys.capella.test.framework.helpers.ComparisonHelper;
import org.polarsys.capella.test.framework.helpers.TestHelper;
import org.polarsys.capella.test.framework.helpers.diffModel.StructureDiffComparison;

/** 
  * Verify that the diff policy @see StructureDiffComparison is ok.
  * 
  * @author Erwan Brottier
  */
public class CheckDiffPolicyTestCase extends BasicTestCaseWithoutTestModel { 

	private static final String MODEL_PATH = "model";
	
  @Override
  public void test() throws Exception {
  	// -- SCENARIO -- //    
    File relativeModelsFolder = getFileOrFolderInTestPlugin(MODEL_PATH); //$NON-NLS-1$
    List<String> projectNames = new ArrayList<String>();
    for (File file : new File(FileHelper.getFileFullUrl(relativeModelsFolder+"/").getFile()).listFiles()) //$NON-NLS-1$
			if (file.isDirectory())
				projectNames.add(file.getName());
    for (String projectName : projectNames) {
    	// copy the project in the workspace
    	ModelProviderHelper.getInstance().getModelProvider().requireTestModel(projectName, this);
    	IProject eclipseProject = AbstractProvider.getEclipseProjectForTestModel(projectName, this); 
      IFile melodyModellerFile = eclipseProject.getFile(projectName + "." + CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION); //$NON-NLS-1$
      Project project = TestHelper.getProjectFromMelodyModeller(melodyModellerFile);
      StructureDiffComparison compOp = new StructureDiffComparison();
      IEditableModelScope sourceScope = new RootedModelScope(Arrays.asList(new EObject[] {project}));
      IEditableModelScope targetScope = new RootedModelScope(Arrays.asList(new EObject[] {project}));
      // check that each object has a unique ID according to the diff policy
      // if not, prompt the objects with same ID and make the test fail
      TreeIterator<EObject> objects = project.eAllContents();
      HashMap<String, List<Object>> matchId2Objects = new HashMap<String, List<Object>>(); 
      while (objects.hasNext()) {
      	EObject o = objects.next();
      	String matchId = (String) compOp.getMatchID(o, sourceScope);
      	List<Object> list = matchId2Objects.get(matchId);
      	if (list == null) {
      		list = new ArrayList<Object>();
      		matchId2Objects.put(matchId, list);
      	}
      	list.add(o);
      }
      boolean errorDetected = false;
      for (String matchId : matchId2Objects.keySet()) {
      	List<Object> list = matchId2Objects.get(matchId);
      	if (list.size() > 1) {
      		errorDetected = true;
      		if (TestConstants.DEBUG) {
      			System.out.println("multiple objects for matchID "+matchId); //$NON-NLS-1$
      			for (Object object : list) {
      				System.out.println("  "+object); //$NON-NLS-1$
      			}      			
      		}
      	}
  		}
      assertFalse("several objects in project "+projectName+" have the same ID according to the diff policy used for oracle. Diff policy for oracle must be verified and other test cases can not be trust", errorDetected); //$NON-NLS-1$ //$NON-NLS-2$
      // launch the diff
      IComparison comparison = new EComparisonImpl(sourceScope, targetScope);  	  	
    	comparison.compute(compOp, new DefaultDiffPolicy(), new DefaultMergePolicy(), new NullProgressMonitor());
      List<IDifference> diffInSource = comparison.getDifferences(Role.REFERENCE);
      List<IDifference> diffInTarget= comparison.getDifferences(Role.TARGET);
      boolean diffsDetected = diffInSource.size() > 0 || diffInTarget.size() > 0;
      if (diffsDetected && TestConstants.DEBUG) {
      	System.out.println("-- SOURCE --"); //$NON-NLS-1$
      	ComparisonHelper.promptDiff(diffInSource, compOp);
      	System.out.println("-- TARGET --"); //$NON-NLS-1$
      	ComparisonHelper.promptDiff(diffInTarget, compOp);        	
      }
      assertFalse("iso comparison found differences. Diff policy for oracle must be verified and other test cases can not be trust", diffsDetected); //$NON-NLS-1$			
		}
  }
      
}
