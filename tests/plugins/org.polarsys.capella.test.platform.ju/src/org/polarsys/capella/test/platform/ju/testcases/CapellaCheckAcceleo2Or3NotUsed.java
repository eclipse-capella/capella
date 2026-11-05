/*******************************************************************************
 * Copyright (c) 2019, 2026 THALES GLOBAL SERVICES.
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

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class CapellaCheckAcceleo2Or3NotUsed extends BasicTestCase {
	private static final String symbolicNameOfBundleWithOdesignFiles = "org.polarsys.capella.core.sirius.analysis";
	private static final Pattern symbolicNameOfBundleWithAcceleo2Pattern = Pattern
			.compile("org\\.eclipse\\.sirius\\.query\\.legacy");

	private static final Pattern acceleo2SyntaxPattern = Pattern.compile("<%.*%>");
	private static final String acceleo2DetectionMessageTemplate = "Found %d Acceleo 2 queries in file '%s':%s";
	private static final String acceleo2DependencyDetectionMessageTemplate = "Found %d dependencies to Acceleo 2 bundles in file '%s':%s";

	private static final Pattern acceleo3SyntaxPattern = Pattern.compile("\\[.*/\\]");
	private static final String acceleo3DetectionMessageTemplate = "Found %d Acceleo 3 queries in file '%s':%s";

	private URL manifestFile;
	private List<URL> odesignFiles;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		final Bundle siriusAnalysisBundle = Platform.getBundle(symbolicNameOfBundleWithOdesignFiles);
		assertNotNull("Bundle not found: " + symbolicNameOfBundleWithOdesignFiles, siriusAnalysisBundle);

		this.manifestFile = siriusAnalysisBundle.getEntry("META-INF/MANIFEST.MF");
		assertNotNull("Manifest file not found in bundle: " + symbolicNameOfBundleWithOdesignFiles, manifestFile);

		final Enumeration<URL> odesignFileEntries = siriusAnalysisBundle.findEntries("/", "*.odesign", true);
		assertNotNull("No odesign files found in bundle: " + symbolicNameOfBundleWithOdesignFiles, odesignFileEntries);

		this.odesignFiles = Collections.list(odesignFileEntries);
	}

	@Override
	public void test() throws Exception {
		checkPresenceOfPatternInFiles(Collections.singletonList(manifestFile), symbolicNameOfBundleWithAcceleo2Pattern,
				acceleo2DependencyDetectionMessageTemplate);
		checkPresenceOfPatternInFiles(odesignFiles, acceleo2SyntaxPattern, acceleo2DetectionMessageTemplate);
		checkPresenceOfPatternInFiles(odesignFiles, acceleo3SyntaxPattern, acceleo3DetectionMessageTemplate);
	}

	private static void checkPresenceOfPatternInFiles(final List<URL> files, final Pattern pattern,
			final String messageTemplate) throws IOException {
		for (final URL file : files) {
			try (final LineNumberReader reader = new LineNumberReader(
					new InputStreamReader(file.openStream(), StandardCharsets.UTF_8))) {
				final Map<Integer, String> matchingLines = new LinkedHashMap<>();
				String currentLine;
				while ((currentLine = reader.readLine()) != null) {
					if (pattern.matcher(currentLine).find()) {
						matchingLines.put(reader.getLineNumber(), currentLine);
					}
				}
				assertTrue(messageTemplate.formatted(matchingLines.size(), file.getFile(),
						matchingLines.entrySet().stream()
								.map(entry -> "Line %d: %s".formatted(entry.getKey(), entry.getValue()))
								.collect(Collectors.joining("\n\t", "\n\t", ""))),
						matchingLines.isEmpty());
			}
		}
	}
}
