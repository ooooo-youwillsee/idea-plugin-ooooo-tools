package com.ooooo.action;

import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.actionSystem.impl.ActionButtonWithText;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.jcef.JBCefBrowser;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.VK_ENTER;

/**
 * @author <a href="https://github.com/ooooo-youwillsee">ooooo</a>
 * @since 1.0.0
 */
public class WebBrowserJPanel extends JPanel {

  private int NAV_PANEL_HEIGHT = 30;

  private WebBrowser webBrowser;

  private JBCefBrowser browser;

  private JBTextField urlTextField;

  private ActionButtonWithText forwardButton;

  private ActionButtonWithText backButton;

  public WebBrowserJPanel(JBCefBrowser browser, WebBrowser webBrowser) {
    this.webBrowser = webBrowser;
    this.browser = browser;
    init();
  }

  private void init() {
    // settings
    setVisible(true);
    setLayout(new BorderLayout());

    // nav
    add(getNavPanel(), BorderLayout.NORTH);
    // browser
    add(getBrowserPanel(), BorderLayout.CENTER);
  }

  private Component getBrowserPanel() {
    JComponent component = browser.getComponent();
    component.setName("browserPanel");
    component.setSize(new Dimension(getWidth(), getHeight() - NAV_PANEL_HEIGHT));
    return component;
  }

  private Component getNavPanel() {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel.setName("navPanel");


    // forwardButton
    forwardButton = new ActionButtonWithText(new AnAction() {
      @Override
      public void actionPerformed(@NotNull AnActionEvent e) {
        webBrowser.forward();
      }
    }, new Presentation("Forward"), ActionPlaces.EDITOR_TAB, new Dimension());
    panel.add(forwardButton);

    // backButton
    backButton = new ActionButtonWithText(new AnAction() {
      @Override
      public void actionPerformed(@NotNull AnActionEvent e) {
        webBrowser.back();
      }
    }, new Presentation("Back"), ActionPlaces.EDITOR_TAB, new Dimension());
    backButton.setName("backButton");
    panel.add(backButton);

    urlTextField = new JBTextField();
    urlTextField.setName("urlTextField");
    urlTextField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (VK_ENTER == e.getKeyCode()) {
          webBrowser.open(urlTextField.getText());
        }
      }
    });
    panel.add(urlTextField);
    panel.setSize(new Dimension(getWidth(), NAV_PANEL_HEIGHT));
    return panel;
  }

  public void setUrl(String url) {
    urlTextField.setText(url);
  }
}
