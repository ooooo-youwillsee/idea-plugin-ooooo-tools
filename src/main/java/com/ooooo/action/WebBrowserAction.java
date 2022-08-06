package com.ooooo.action;

import com.intellij.ide.plugins.UIComponentVirtualFile;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author <a href="https://github.com/ooooo-youwillsee">ooooo</a>
 * @since 1.0.0
 */
public class WebBrowserAction extends AnAction {

  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    Project project = e.getProject();
    if (project == null) {
      return;
    }

    FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);

    String fileName = RandomStringUtils.randomAlphabetic(6);
    WebBrowser webBrowser = new WebBrowserImpl();
    JComponent component = webBrowser.getComponent();
    webBrowser.open(WebBrowser.DEFAULT_URL);

    UIComponentVirtualFile uiComponentVirtualFile = new UIComponentVirtualFile(fileName, new UIComponentVirtualFile.Content() {
      @Override
      public @NotNull JComponent createComponent() {
        return component;
      }

      @Override
      public @Nullable JComponent getPreferredFocusedComponent() {
        return null;
      }
    });

    fileEditorManager.openFile(uiComponentVirtualFile, true);
  }


}
