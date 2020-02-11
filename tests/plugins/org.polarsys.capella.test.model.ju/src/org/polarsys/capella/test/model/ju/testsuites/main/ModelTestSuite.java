/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.model.ju.testsuites.main;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.model.ju.activityExplorer.ActivityExplorerTestSuite;
import org.polarsys.capella.test.model.ju.components.CompositionTestSuite;
import org.polarsys.capella.test.model.ju.crossreferencer.InvolverInvolvedDerivedFeaturesTest;
import org.polarsys.capella.test.model.ju.crossreferencer.MoveElementToNewResourceTest;
import org.polarsys.capella.test.model.ju.crossreferencer.TraceableElementDerivedFeaturesTest;
import org.polarsys.capella.test.model.ju.crossreferencer.TypeDerivedFeaturesTest;
import org.polarsys.capella.test.model.ju.derivedfeature.DerivedFeatureTestCase;
import org.polarsys.capella.test.model.ju.diffmerge.DiffMergeTestSuite;
import org.polarsys.capella.test.model.ju.dnd.DnDTestSuite;
import org.polarsys.capella.test.model.ju.helpers.CapellaAdapterHelperTestCase;
import org.polarsys.capella.test.model.ju.helpers.ChildrenFeaturesItemProviderTest;
import org.polarsys.capella.test.model.ju.helpers.ComponentExtTestCase;
import org.polarsys.capella.test.model.ju.helpers.ComponentPkgExtTestCase;
import org.polarsys.capella.test.model.ju.helpers.PartExtTestCase;
import org.polarsys.capella.test.model.ju.helpers.PhysicalComponentExtTestCase;
import org.polarsys.capella.test.model.ju.obfuscate.ObfuscateModelTestCase;
import org.polarsys.capella.test.model.ju.propertyviews.PropertyViewsTest;
import org.polarsys.capella.test.model.ju.rename.RenameModelTestSuite;
import org.polarsys.capella.test.model.ju.sortContent.SortContentTestSuite;
import org.polarsys.capella.test.model.ju.sortSelection.SortSelectionTestSuite;
import org.polarsys.capella.test.model.ju.testcase.LCDecomposition.LCDecompositionWithCommunicationLink;
import org.polarsys.capella.test.model.ju.testcase.LCDecomposition.LCDecompositionWithInternalInterface;
import org.polarsys.capella.test.model.ju.testcase.copyPasteLayout.CopyPasteLayout;
import org.polarsys.capella.test.model.ju.testcase.copyPasteLayout.InvalidCapellaCopyPaste;
import org.polarsys.capella.test.model.ju.testcase.copyPasteModel.CopyPasteModelTestSuite;
import org.polarsys.capella.test.model.ju.testcase.dialoglabel.NewDiagramDialogLabel;
import org.polarsys.capella.test.model.ju.testcases.datalisteners.DataListenerTestSuite;
import org.polarsys.capella.test.model.ju.testcases.delete.DeleteElementTestSuite;
import org.polarsys.capella.test.model.ju.testcases.interfacescenario.message.ISMessage;

import junit.framework.Test;

/**
 * @author Hakim Sellou
 */
public class ModelTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new ModelTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CopyPasteModelTestSuite());
    tests.add(new LCDecompositionWithCommunicationLink());
    tests.add(new LCDecompositionWithInternalInterface());
    tests.add(new ISMessage());
    tests.add(new SortSelectionTestSuite());
    tests.add(new SortContentTestSuite());
    tests.add(new NewDiagramDialogLabel());
    tests.add(new RenameModelTestSuite());
    tests.add(new ObfuscateModelTestCase());
    tests.add(new DeleteElementTestSuite());
    tests.add(new DataListenerTestSuite());
    tests.add(new CapellaAdapterHelperTestCase());
    tests.add(new ChildrenFeaturesItemProviderTest());
    tests.add(new CopyPasteLayout());
    tests.add(new InvalidCapellaCopyPaste());
    tests.add(new PropertyViewsTest());
    tests.add(new MoveElementToNewResourceTest());
    tests.add(new InvolverInvolvedDerivedFeaturesTest());
    tests.add(new TypeDerivedFeaturesTest());
    tests.add(new TraceableElementDerivedFeaturesTest());
    tests.add(new DerivedFeatureTestCase());
    tests.add(new DnDTestSuite());
   
    tests.add(new ComponentExtTestCase());
    tests.add(new ComponentPkgExtTestCase());
    tests.add(new PartExtTestCase());
    tests.add(new PhysicalComponentExtTestCase());

    tests.add(new DiffMergeTestSuite());
    tests.add(new CompositionTestSuite());
    tests.add(new ActivityExplorerTestSuite());
    return tests;
  }

}
