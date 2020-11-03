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
package org.polarsys.capella.test.framework.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EElementPresence;
import org.eclipse.emf.diffmerge.diffdata.EReferenceValuePresence;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.impl.policies.DefaultDiffPolicy;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMatchPolicy;
import org.eclipse.emf.diffmerge.impl.policies.DefaultMergePolicy;
import org.eclipse.emf.diffmerge.impl.scopes.RootedModelScope;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.test.framework.helpers.diffFile.FileDiff;
import org.polarsys.capella.test.framework.helpers.diffFile.Interval;
import org.polarsys.capella.test.framework.helpers.diffModel.StructureDiffComparison;

/** Provides API to check differences between various artefacts. Useful to check if the result of a test equals the expected result.<br>
  * Following artefacts are currently supported:<br>
  * <ul><li>files</li>
  * <li>capella models</li></ul>
  * 
  * @author Erwan Brottier
  */
public class ComparisonHelper {

	/** @param rootElement1 a node of the first model
	  * @param rootElement2 a node of another model
	  * @return true if subgraph rootElement1 and rootElement2 equals (regardless of ID element attribute) */
	public static boolean doesModelFragmentsEqual(EObject rootElement1, EObject rootElement2) {
    IEditableModelScope sourceScope = new RootedModelScope(Arrays.asList(new EObject[] {rootElement1}));
    IEditableModelScope targetScope = new RootedModelScope(Arrays.asList(new EObject[] {rootElement2}));
    EComparison comparison = new EComparisonImpl(sourceScope, targetScope);  	  	
		StructureDiffComparison matchPolicy = new StructureDiffComparison();
  	comparison.compute(matchPolicy, new DefaultDiffPolicy(), new DefaultMergePolicy(), new NullProgressMonitor());
    Collection<IDifference<EObject>> diffInSource = comparison.getDifferences(Role.REFERENCE);
    Collection<IDifference<EObject>> diffInTarget= comparison.getDifferences(Role.TARGET);
    return diffInSource.size() == 0 && diffInTarget.size() == 0;
	}

	/** @param project1 the root of a capella model
	  * @param project2 another root of a capella model
	  * @return true if models equals (regardless of ID element attribute) */
	public static boolean doesModelsEqual(Project project1, Project project2) {
    IEditableModelScope sourceScope = new RootedModelScope(Arrays.asList(new EObject[] {project1}));
    IEditableModelScope targetScope = new RootedModelScope(Arrays.asList(new EObject[] {project2}));
    EComparison comparison = new EComparisonImpl(sourceScope, targetScope);  	  	
		StructureDiffComparison matchPolicy = new StructureDiffComparison();
  	comparison.compute(matchPolicy, new DefaultDiffPolicy(), new DefaultMergePolicy(), new NullProgressMonitor());
  	Collection<IDifference<EObject>> diffInSource = comparison.getDifferences(Role.REFERENCE);
  	Collection<IDifference<EObject>> diffInTarget= comparison.getDifferences(Role.TARGET);
    return diffInSource.size() == 0 && diffInTarget.size() == 0;
	}
	
	/** @param diffs diff/merge differences
	  * @param matchPolicy the match policy used to compute diff parameter
	  * Prompt in console the diffs in a human readable manner. */
  public static void promptDiff(List<IDifference<EObject>> diffs, StructureDiffComparison matchPolicy) {
  	IMatchPolicy<EObject> defaultMathPolicy = new DefaultMatchPolicy();
  	for (IDifference<EObject> diff : diffs) {
			if (diff instanceof EElementPresence) {				
				EElementPresence p = (EElementPresence) diff;
				EObject element = p.getElement();
				System.out.println("Element : "+element);				 //$NON-NLS-1$
				System.out.println("  [DEFAULT  ID] => "+defaultMathPolicy.getMatchID(element, null)); //$NON-NLS-1$
				System.out.println("  [SPECIFIC ID] => "+matchPolicy.getIdentifier(element)); //$NON-NLS-1$
			} else if (diff instanceof EAttributeValuePresence) {
				EAttributeValuePresence p = (EAttributeValuePresence) diff;
				System.out.println("Attribute : "+p.getFeature().getName()+" in "+p.getFeature().eContainer());  //$NON-NLS-1$//$NON-NLS-2$
			} else if (diff instanceof EReferenceValuePresence) {
				EReferenceValuePresence p = (EReferenceValuePresence) diff;
				System.out.println("Reference : "+p.getFeature().getName()+" in "+p.getFeature().eContainer()); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
  }
	
	
	/** @param file1 one file
	  * @param file2 another file
	  * @return the list of all differences between the two given files */	
  public static List<FileDiff> getFileDifferences(File file1, File file2) throws IOException {
  	List<FileDiff> res = new ArrayList<FileDiff>();
  	BufferedReader reader1 = new BufferedReader(new FileReader(file1));
  	BufferedReader reader2 = new BufferedReader(new FileReader(file2));
  	char[] buf1 = new char[1024];
  	char[] buf2 = new char[1024];
    boolean moreCheck = true;
    long offset = 0;
    long file1Size = 0;
    long file2Size = 0;
    FileDiff.State currentState = FileDiff.State.equality;
    FileDiff currentFileDiff = null;
    int numRead1 = 0;
    int numRead2 = 0;
    while (moreCheck) {
    	numRead1 = reader1.read(buf1);
    	numRead2 = reader2.read(buf2);
    	if (numRead1 > 0)
    		file1Size += numRead1;
    	if (numRead2 > 0)
    		file2Size += numRead2;
    	int nbCharToCheck = numRead1;    	
    	if (numRead2 < numRead1)
    		nbCharToCheck = numRead2;
    	for (int i = 0; i < nbCharToCheck; i++) {      		
    		if (currentState == FileDiff.State.equality) {
    			if (buf1[i] != buf2[i]) {
    				currentFileDiff = new FileDiff(new Interval(offset, offset));
    				res.add(currentFileDiff);
    				currentState = FileDiff.State.difference;
    				currentFileDiff.addChar(buf1[i]);
    			}
    		} else if (currentState == FileDiff.State.difference) {
    			if (buf1[i] == buf2[i]) {
    				currentFileDiff.getFileOffsetRange().bornSup = offset-1;    				    				
    				currentState = FileDiff.State.equality;
    			} else {
    				currentFileDiff.addChar(buf1[i]);
    			}
    		}
    		offset++;
			}
    	moreCheck = nbCharToCheck > 0;
    }
    if (file1Size != file2Size) {
    	if (currentState == FileDiff.State.equality) {
    		res.add(new FileDiff(new Interval(offset, -1)));
    	} else if (currentState == FileDiff.State.difference) {
    		currentFileDiff.getFileOffsetRange().bornSup = -1;
    	}    	
    }
    reader1.close();
    reader2.close();
    return res;
  }
	
}
