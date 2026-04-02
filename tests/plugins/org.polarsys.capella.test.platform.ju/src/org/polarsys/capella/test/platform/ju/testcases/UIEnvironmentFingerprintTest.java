/*******************************************************************************
 * Copyright (c) 2026 Obeo.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.polarsys.capella.test.platform.ju.testcases;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public class UIEnvironmentFingerprintTest extends BasicTestCase {

  private static final String START_MARKER = "=== CAPELLA UI ENVIRONMENT FINGERPRINT START ===";
  private static final String END_MARKER = "=== CAPELLA UI ENVIRONMENT FINGERPRINT END ===";

  @Override
  public void test() throws Exception {
    assertTrue("Workbench must be running in UI mode", PlatformUI.isWorkbenchRunning());

    Display display = PlatformUI.getWorkbench().getDisplay();
    assertNotNull("Display must be available", display);

    String report = buildReport(display);
    System.out.println(report);

    assertTrue("Fingerprint start marker is missing", report.contains(START_MARKER));
    assertTrue("Fingerprint end marker is missing", report.contains(END_MARKER));
    assertTrue("Display section is missing", report.contains("[display]"));
    assertTrue("Fonts section is missing", report.contains("[fonts]"));
    assertTrue("Commands section is missing", report.contains("[commands]"));
    assertTrue("At least one monitor is expected", display.getMonitors().length > 0);
  }

  private String buildReport(Display display) {
    StringBuilder builder = new StringBuilder();
    appendLine(builder, START_MARKER);
    appendRuntime(builder);
    appendDisplay(builder, display);
    appendWorkbench(builder, display);
    appendFonts(builder, display);
    appendCommands(builder);
    appendLine(builder, END_MARKER);
    return builder.toString();
  }

  private void appendRuntime(StringBuilder builder) {
    appendSection(builder, "runtime");
    appendKeyValue(builder, "java.version", System.getProperty("java.version"));
    appendKeyValue(builder, "java.vendor", System.getProperty("java.vendor"));
    appendKeyValue(builder, "java.home", System.getProperty("java.home"));
    appendKeyValue(builder, "os.name", System.getProperty("os.name"));
    appendKeyValue(builder, "os.version", System.getProperty("os.version"));
    appendKeyValue(builder, "os.arch", System.getProperty("os.arch"));
    appendKeyValue(builder, "platform.os", Platform.getOS());
    appendKeyValue(builder, "platform.ws", Platform.getWS());
    appendKeyValue(builder, "platform.arch", Platform.getOSArch());
    appendKeyValue(builder, "platform.nl", Platform.getNL());

    for (String env : Arrays.asList("DISPLAY", "XDG_SESSION_TYPE", "XDG_CURRENT_DESKTOP", "GDK_SCALE",
        "GDK_DPI_SCALE", "GTK_THEME", "LANG", "LC_ALL")) {
      appendKeyValue(builder, "env." + env, getenvOrMissing(env));
    }
  }

  private void appendDisplay(StringBuilder builder, Display display) {
    appendSection(builder, "display");
    appendKeyValue(builder, "display.bounds", rectangleToString(display.getBounds()));
    appendKeyValue(builder, "display.clientArea", rectangleToString(display.getClientArea()));
    appendKeyValue(builder, "display.dpi", display.getDPI().x + "x" + display.getDPI().y);
    appendKeyValue(builder, "display.depth", Integer.toString(display.getDepth()));

    Monitor[] monitors = display.getMonitors();
    appendKeyValue(builder, "display.monitorCount", Integer.toString(monitors.length));
    Monitor primary = display.getPrimaryMonitor();
    for (int i = 0; i < monitors.length; i++) {
      Monitor monitor = monitors[i];
      String prefix = "monitor[" + i + "]";
      appendKeyValue(builder, prefix + ".bounds", rectangleToString(monitor.getBounds()));
      appendKeyValue(builder, prefix + ".clientArea", rectangleToString(monitor.getClientArea()));
      appendKeyValue(builder, prefix + ".zoom", Integer.toString(monitor.getZoom()));
      appendKeyValue(builder, prefix + ".primary", Boolean.toString(monitor.equals(primary)));
    }
  }

  private void appendWorkbench(StringBuilder builder, Display display) {
    appendSection(builder, "workbench");

    IWorkbench workbench = PlatformUI.getWorkbench();
    appendKeyValue(builder, "workbench.running", Boolean.toString(PlatformUI.isWorkbenchRunning()));
    appendKeyValue(builder, "workbench.windowCount", Integer.toString(workbench.getWorkbenchWindows().length));

    IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
    appendKeyValue(builder, "workbench.activeWindow", booleanPresence(window));
    if (window == null) {
      return;
    }

    Shell shell = window.getShell();
    appendKeyValue(builder, "shell.present", booleanPresence(shell));
    if (shell != null) {
      appendKeyValue(builder, "shell.bounds", rectangleToString(shell.getBounds()));
      appendKeyValue(builder, "shell.clientArea", rectangleToString(shell.getClientArea()));
      appendKeyValue(builder, "shell.maximized", Boolean.toString(shell.getMaximized()));
      appendKeyValue(builder, "shell.minimized", Boolean.toString(shell.getMinimized()));
      appendKeyValue(builder, "shell.fullScreen", Boolean.toString(shell.getFullScreen()));
      appendKeyValue(builder, "shell.monitor", rectangleToString(shell.getMonitor().getBounds()));
    }

    appendKeyValue(builder, "display.activeShell", booleanPresence(display.getActiveShell()));
    if (display.getActiveShell() != null) {
      appendKeyValue(builder, "display.activeShell.bounds", rectangleToString(display.getActiveShell().getBounds()));
    }

    IWorkbenchPage page = window.getActivePage();
    appendKeyValue(builder, "workbench.activePage", booleanPresence(page));
    if (page == null) {
      return;
    }

    IEditorPart editor = page.getActiveEditor();
    appendKeyValue(builder, "workbench.activeEditor", editor == null ? "MISSING" : editor.getSite().getId());
    IViewPart view = page.getActivePart() instanceof IViewPart ? (IViewPart) page.getActivePart() : null;
    appendKeyValue(builder, "workbench.activeView", view == null ? "MISSING" : view.getSite().getId());
  }

  private void appendFonts(StringBuilder builder, Display display) {
    appendSection(builder, "fonts");
    appendFont(builder, "display.systemFont", display.getSystemFont());
    appendFont(builder, "jface.defaultFont", JFaceResources.getDefaultFont());
    appendFont(builder, "jface.dialogFont", JFaceResources.getDialogFont());
    appendFont(builder, "jface.textFont", JFaceResources.getTextFont());
    appendFont(builder, "jface.bannerFont", JFaceResources.getBannerFont());
    appendFont(builder, "jface.headerFont", JFaceResources.getHeaderFont());

    FontRegistry fontRegistry = JFaceResources.getFontRegistry();
    Map<String, String> fontPreferenceValues = new LinkedHashMap<>();
    fontPreferenceValues.put(JFaceResources.DEFAULT_FONT, getRegistryValue(fontRegistry, JFaceResources.DEFAULT_FONT));
    fontPreferenceValues.put(JFaceResources.DIALOG_FONT, getRegistryValue(fontRegistry, JFaceResources.DIALOG_FONT));
    fontPreferenceValues.put(JFaceResources.TEXT_FONT, getRegistryValue(fontRegistry, JFaceResources.TEXT_FONT));
    fontPreferenceValues.put(JFaceResources.BANNER_FONT, getRegistryValue(fontRegistry, JFaceResources.BANNER_FONT));
    fontPreferenceValues.put(JFaceResources.HEADER_FONT, getRegistryValue(fontRegistry, JFaceResources.HEADER_FONT));

    for (Map.Entry<String, String> entry : fontPreferenceValues.entrySet()) {
      appendKeyValue(builder, "fontPreference." + entry.getKey(), entry.getValue());
    }
  }

  private void appendCommands(StringBuilder builder) {
    appendSection(builder, "commands");
    appendCommand(builder, "fc-match sans", "fc-match", "sans");
    appendCommand(builder, "fc-match serif", "fc-match", "serif");
    appendCommand(builder, "fc-match monospace", "fc-match", "monospace");
    appendCommand(builder, "fc-match Segoe UI", "fc-match", "Segoe UI");
    appendCommand(builder, "xrandr --current", "xrandr", "--current");
    appendCommand(builder, "xdpyinfo", "xdpyinfo");
    appendCommand(builder, "xset q", "xset", "q");
  }

  private void appendCommand(StringBuilder builder, String label, String... command) {
    ProcessBuilder processBuilder = new ProcessBuilder(command);
    processBuilder.redirectErrorStream(true);
    try {
      Process process = processBuilder.start();
      String output;
      try (BufferedReader reader = new BufferedReader(
          new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
        output = reader.lines().collect(Collectors.joining("\\n"));
      }
      int exitCode = process.waitFor();
      appendMultiline(builder, label, "exit=" + exitCode + (output.isEmpty() ? "" : "\\n" + output));
    } catch (Exception exception) {
      appendKeyValue(builder, label, "UNAVAILABLE: " + exception.getClass().getSimpleName() + ": " + exception.getMessage());
    }
  }

  private void appendFont(StringBuilder builder, String label, Font font) {
    if (font == null || font.isDisposed()) {
      appendKeyValue(builder, label, "MISSING");
      return;
    }

    String fontDescription = Arrays.stream(font.getFontData()).map(this::fontDataToString).collect(Collectors.joining(" | "));
    appendKeyValue(builder, label, fontDescription);
  }

  private String getRegistryValue(FontRegistry fontRegistry, String key) {
    if (fontRegistry == null || !fontRegistry.hasValueFor(key)) {
      return "MISSING";
    }
    FontData[] fontData = fontRegistry.getFontData(key);
    if (fontData == null || fontData.length == 0) {
      return "MISSING";
    }
    return Arrays.stream(fontData).map(this::fontDataToString).collect(Collectors.joining(" | "));
  }

  private String fontDataToString(FontData fontData) {
    return fontData.getName() + "/" + fontData.getHeight() + "/" + styleToString(fontData.getStyle()) +
        (fontData.getLocale() == null || fontData.getLocale().isEmpty() ? "" : "/" + fontData.getLocale());
  }

  private String styleToString(int style) {
    if (style == SWT.NONE) {
      return "NORMAL";
    }
    StringBuilder styleBuilder = new StringBuilder();
    if ((style & SWT.BOLD) != 0) {
      styleBuilder.append("BOLD");
    }
    if ((style & SWT.ITALIC) != 0) {
      if (styleBuilder.length() > 0) {
        styleBuilder.append('+');
      }
      styleBuilder.append("ITALIC");
    }
    return styleBuilder.toString();
  }

  private String rectangleToString(Rectangle rectangle) {
    return rectangle == null ? "MISSING" : rectangle.x + "," + rectangle.y + " " + rectangle.width + "x" + rectangle.height;
  }

  private String booleanPresence(Object value) {
    return value == null ? "MISSING" : "present";
  }

  private String getenvOrMissing(String key) {
    String value = System.getenv(key);
    return value == null ? "MISSING" : value;
  }

  private void appendSection(StringBuilder builder, String section) {
    appendLine(builder, "[" + section + "]");
  }

  private void appendKeyValue(StringBuilder builder, String key, String value) {
    appendLine(builder, key + "=" + value);
  }

  private void appendMultiline(StringBuilder builder, String key, String value) {
    appendLine(builder, key + " <<EOF");
    for (String line : value.split("\\n", -1)) {
      appendLine(builder, line);
    }
    appendLine(builder, "EOF");
  }

  private void appendLine(StringBuilder builder, String line) {
    builder.append(line).append(System.lineSeparator());
  }
}
